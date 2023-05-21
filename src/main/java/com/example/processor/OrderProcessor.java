package demo.src.main.java.com.example.processor;

import demo.src.main.java.com.example.entity.Order;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class OrderProcessor implements ItemProcessor<Order, Order>{

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcessor.class);

  /**
   * Enum to store countries
   */
  enum Country {
    CAMEROON("Cameroon"),
    ETHIOPIA("Ethiopia"),
    MOROCCO("Morocco"),
    MOZAMBIQUE("Mozambique"),
    UGANDA("Uganda");
    private final String name;

    Country(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  /**
   * Takes the order and enriches/transforms it and then
   * return the transformed Order
   * @param order
   * @return Order
   * @throws Exception
   */
  @Override
  public Order process(final Order order) throws Exception {
    Order newOrder = Order.builder()
        .email(order.getEmail())
        .number(order.getNumber())
        .weight(order.getWeight())
        .country(deriveCountryFromPhoneNumber(order.getNumber()))
        .build();

    return newOrder;
  }

  /**
   * Determines country based on phone number
   * @param number
   * @return Country
   */
  public String deriveCountryFromPhoneNumber(final String number) {
    if(Pattern.matches("\\(237\\)\\ ?[2368]\\d{7,8}$", number)){
      return Country.CAMEROON.getName();
    }
    if(Pattern.matches(" \\(251\\)\\ ?[1-59]\\d{8}$", number)){
      return Country.ETHIOPIA.getName();
    }
    if(Pattern.matches("\\(212\\)\\ ?[5-9]\\d{8}$", number)){
      return Country.MOROCCO.getName();
    }
    if(Pattern.matches("\\(258\\)\\ ?[28]\\d{7,8}$", number)){
      return Country.MOZAMBIQUE.getName();
    }
    if(Pattern.matches("\\(256\\)\\ ?\\d{9}$", number)){
      return Country.UGANDA.getName();
    }
    return "";
  }
}
