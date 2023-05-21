package demo.src.test.java.com.example.demo.service;

import demo.src.main.java.com.example.entity.NumberCount;
import demo.src.main.java.com.example.entity.Order;
import demo.src.main.java.com.example.entity.WeightCount;
import demo.src.main.java.com.example.repository.OrderRepository;
import demo.src.main.java.com.example.service.OrderServiceImpl;
import java.util.ArrayList;
import java.util.List;


@RunWith(JUnit5.class)
public class OrderServiceImplTest {

  @InjectMocks
  @Spy
  OrderServiceImpl orderService;
  @Mock
  OrderRepository orderRepository;

  @Before
  public void setup(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testOrders_whenPresent_returnsOrderList(){
    List<Order> orders = new ArrayList<>();
    order.add(new Order(1,"abc@gmail.com",98.0,"98765432","Uganda"));
    order.add(new Order(2,"bcd@gmail.com",28.0,"87612345","Uganda"));

    when(orderRepository.getOrders("Uganda",PageRequest.of(0, 10))).thenReturn(orders);

    assertEquals(2,orderService.getOrders("Uganda").size());
  }

  @Test
  public void testOrders_whenNotPresent_returnsEmptyList(){
    when(orderRepository.getOrders("Uganda",PageRequest.of(0, 10))).thenReturn(null);

    assertTrue(orderService.getOrders("Uganda").isEmpty());
  }

  @Test
  public void testTotalWeight_whenPresent_returnsCountryWeightList(){
    List<WeightCount> countries = new ArrayList<>();
    countries.add(new WeightCount("Morocco", 98.7);
    countries.add(new WeightCount("Ethiopia", 55.7);

    when(orderRepository.getTotalWeightPerCountry()).thenReturn(countries);

    assertEquals(2,orderService.getTotalWeightPerCountry().size());
  }

  @Test
  public void testTotalWeight_whenNotPresent_returnsEmptyList(){
    when(orderRepository.getTotalWeightPerCountry()).thenReturn(null);

    assertTrue(orderService.getTotalWeightPerCountry().isEmpty());
  }

  @Test
  public void testTotalOrders_whenPresent_returnsCountryOrderCountList(){
    List<NumberCount> countries = new ArrayList<>();
    countries.add(new NumberCount("Morocco", 9);
    countries.add(new NumberCount("Ethiopia", 6);

    when(orderRepository.getTotalOrdersPerCountry()).thenReturn(countries);

    assertEquals(2,orderService.getTotalOrdersPerCountry().size());
  }

  @Test
  public void testTotalOrders_whenNotPresent_returnsEmptyList(){
    when(orderRepository.getTotalOrdersPerCountry()).thenReturn(null);

    assertTrue(orderService.getTotalOrdersPerCountry().isEmpty());
  }
}
