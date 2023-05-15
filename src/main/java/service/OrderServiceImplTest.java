package test.service;

import entity.NumberCount;
import entity.Order;
import entity.WeightCount;
import java.util.ArrayList;
import java.util.List;
import repository.OrderRepository;
import service.CSVHandlerImpl;
import service.OrderServiceImpl;

@RunWith(JUnit5.class)
public class OrderServiceImplTest {

  @InjectMocks
  @Spy
  OrderServiceImpl orderService;

  @Mock
  CSVHandlerImpl csvHandler;

  @Mock
  OrderRepository orderRepository;

  @Before
  public void setup(){
    MockitoAnnotations.openMocks(this);
  }
  @Test
  public void testCountry_forCameroonNumber_returnsCameroon(){
    assertEquals("Fail - Country is not correct",
        "Cameroon", orderService.deriveCountryFromPhoneNumber( "237 209993809"));
  }

  @Test
  public void testCountry_forEthiopiaNumber_returnsEthiopia(){
    assertEquals("Fail - Country is not correct",
        "Ethiopia", orderService.deriveCountryFromPhoneNumber( "251 543636241"));
  }

  @Test
  public void testCountry_forMoroccoNumber_returnsMorocco(){
    assertEquals("Fail - Country is not correct",
        "Morocco", orderService.deriveCountryFromPhoneNumber( "212828477226"));
  }

  @Test
  public void testCountry_forMozambiqueNumber_returnsMozambique(){
    assertEquals("Fail - Country is not correct",
        "Mozambique", orderService.deriveCountryFromPhoneNumber( "258203966695"));
  }

  @Test
  public void testCountry_forUgandaNumber_returnsUganda(){
    assertEquals("Fail - Country is not correct",
        "Uganda", orderService.deriveCountryFromPhoneNumber( "256 573042485"));
  }

  @Test
  public void testCsvRecords_forGivenFile_returnsOrderList(){
    List<List<String>> records = new ArrayList<>();
    List<String> firstEntry = new ArrayList<>();
    firstEntry.add("1");
    firstEntry.add("abc@gmail.com");
    firstEntry.add("876590743");
    firstEntry.add("45.8");

    List<String> secondEntry = new ArrayList<>();
    secondEntry.add("2");
    secondEntry.add("bcd@gmail.com");
    secondEntry.add("908765432");
    secondEntry.add("60.5");

    records.add(firstEntry);
    records.add(secondEntry);

   when(csvHandler.readFromCSV(anyString())).thenReturn(records);

   assertEquals(2,orderService.parseRecordsFromCSV().get().size());
  }

  @Test
  public void testOrders_whenPresent_returnsOrderList(){
    List<Order> orders = new ArrayList<>();
    order.add(orderService.enrichOrder("abc@gmail.com","98765432",98.0,"Uganda"));
    order.add(orderService.enrichOrder("bcd@gmail.com","87612345",28.0,"Uganda"));

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
