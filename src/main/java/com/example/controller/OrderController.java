package demo.src.main.java.com.example.controller;

import demo.src.main.java.com.example.entity.NumberCount;
import demo.src.main.java.com.example.entity.Order;
import demo.src.main.java.com.example.entity.WeightCount;
import demo.src.main.java.com.example.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api")
public class OrderController {
  private static final Logger log = LoggerFactory.getLogger(OrderController.class);
  @Autowired
  OrderService orderService;

  @Autowired
  JobLauncher jobLauncher;

  @Autowired
  Job job;

  /**
   * End point to get total number of orders per country
   */
  @GetMapping("/country/orders")
  public List<NumberCount> getOrdersPerCountry(){
   return orderService.getTotalOrdersPerCountry();
   }

  /**
   * End point to get total weight per country
   */
  @GetMapping("/country/weight")
  public List<WeightCount> getTotalWeightPerCountry(){
    return orderService.getTotalWeightPerCountry();
  }

  /**
   * End point to get orders by param
   */
  @GetMapping("/orders/{name}")
  public List<Order> getOrders(@PathVariable("name") String name){
    return orderService.getOrders(name);
  }

  /**
   * End point to save Orders in database from
   * CSV file through batch processing
   */
  @PostMapping("/orders/save")
  public void saveOrdersFromCsv(){
   JobParameters jobParameters = new JobParametersBuilder()
       .addLong("startAt", LocalDateTime.now()).toJobParameters();
   try {
     jobLauncher.run(job, jobParameters);
   }catch(Exception e){
    log.info("Exception occured while saving data from CSV in db" + e.getMessage());
   }
  }
}
