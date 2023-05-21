package demo.src.main.java.com.example.config;

import demo.src.main.java.com.example.entity.Order;
import demo.src.main.java.com.example.factory.OrderProcessorFactory;
import demo.src.main.java.com.example.processor.OrderProcessor;
import demo.src.main.java.com.example.repository.OrderRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  OrderProcessorFactory factory;

  @Value("${file.input}")
  private String fileInput;

  /**
   * Reads the data from CSV
   * @return FlatFileItemReader
   */
  @Bean
  public FlatFileItemReader reader() {
    return new FlatFileItemReaderBuilder().name("csvItemReader")
        .resource(new ClassPathResource(fileInput))
        .delimited()
        .names(new String[] { "id", "email", "number","weight"})
        .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
          setTargetType(Order.class);
        }})
        .build();
  }

  /**
   * Inserts the Order data in Database
   * @param dataSource
   * @return JdbcBatchItemWriter
   */
  @Bean
  public JdbcBatchItemWriter writer(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql("INSERT INTO order (email, number, weight, country) VALUES (:email, :number, :weight, :country)")
        .dataSource(dataSource)
        .build();
  }

  /**
   * Builds the Job with steps
   * @param orderRepository
   * @param listener
   * @param step1
   * @return Job
   */
  @Bean
  public Job importUserJob(OrderRepository orderRepository, JobCompletionNotificationListener listener, Step step1) {
    return new JobBuilder("importUserJob", orderRepository)
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(step1)
        .end()
        .build();
  }

  /**
   * Build the steps of batch job.
   * @param orderRepository
   * @param transactionManager
   * @param writer
   * @return Step
   */
  @Bean
  public Step step1(OrderRepository orderRepository, PlatformTransactionManager transactionManager, JdbcBatchItemWriter writer) {
    return new StepBuilder("step1", jobBuilder)
        .<Order, Order> chunk(10, transactionManager)
        .reader(reader())
        .processor(processor())
        .writer(writer)
        .build();
  }

  /**
   * Returns the bean of ItemProcessor given by the Factory
   * @return OrderProcessor
   */
  @Bean
  public OrderProcessor processor() {
    return factory.buildOrderProcessor("Number");
  }

}
