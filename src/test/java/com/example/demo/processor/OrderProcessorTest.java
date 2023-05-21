package demo.src.test.java.com.example.demo.processor;

import demo.src.main.java.com.example.processor.OrderProcessor;

@RunWith(JUnit5.class)
public class OrderProcessorTest {

  @Spy
  OrderProcessor orderProcessor;

  @Before
  public void setup(){
    MockitoAnnotations.openMocks(this);
  }
  @Test
  public void testCountry_forCameroonNumber_returnsCameroon(){
    assertEquals("Fail - Country is not correct",
        "Cameroon", orderProcessor.deriveCountryFromPhoneNumber( "237 209993809"));
  }

  @Test
  public void testCountry_forEthiopiaNumber_returnsEthiopia(){
    assertEquals("Fail - Country is not correct",
        "Ethiopia", orderProcessor.deriveCountryFromPhoneNumber( "251 543636241"));
  }

  @Test
  public void testCountry_forMoroccoNumber_returnsMorocco(){
    assertEquals("Fail - Country is not correct",
        "Morocco", orderProcessor.deriveCountryFromPhoneNumber( "212828477226"));
  }

  @Test
  public void testCountry_forMozambiqueNumber_returnsMozambique(){
    assertEquals("Fail - Country is not correct",
        "Mozambique", orderProcessor.deriveCountryFromPhoneNumber( "258203966695"));
  }

  @Test
  public void testCountry_forUgandaNumber_returnsUganda(){
    assertEquals("Fail - Country is not correct",
        "Uganda", orderProcessor.deriveCountryFromPhoneNumber( "256 573042485"));
  }


}
