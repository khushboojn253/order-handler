package service;

import entity.NumberCount;
import entity.Order;
import entity.WeightCount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  CSVHandler csvHandler;


  /**
   * Parse records from CSV and persist in database
   */
  public Optional<List<Order>> parseRecordsFromCSV(){
    List<List<String>> records = csvHandler.readFromCSV("../../../resources/test-file.csv");
    Optional<List<Order>> orderList = Optional.of(new ArrayList<>());
    //Can apply validation logic
    records.forEach(record -> 
                    orderList.get().add(enrichOrder(record.get(1), record.get(2), Double.valueOf(record.get(3)),
                        deriveCountryFromPhoneNumber(record.get(2)))));
   return orderList;
  }

  /**
   * Persist orders in database
   */
  @Transactional
  public void persistOrders() {
    Optional<List<Order>> orderList = parseRecordsFromCSV();
    if(orderList.isPresent() && !orderList.get().isEmpty())
    orderRepository.saveAll(orderList);
  }

  /**
   * Determines country based on phone number
   * @param number
   * @return Country
   */
  public String deriveCountryFromPhoneNumber(final String number) {
     if(Pattern.matches("\\(237\\)\\ ?[2368]\\d{7,8}$", number)){
       return "Cameroon";
     }
    if(Pattern.matches(" \\(251\\)\\ ?[1-59]\\d{8}$", number)){
      return "Ethiopia";
    }
    if(Pattern.matches("\\(212\\)\\ ?[5-9]\\d{8}$", number)){
      return "Morocco";
    }
    if(Pattern.matches("\\(258\\)\\ ?[28]\\d{7,8}$", number)){
      return "Mozambique";
    }
    if(Pattern.matches("\\(256\\)\\ ?\\d{9}$", number)){
      return "Uganda";
    }
    return "";
  }

  /**
   * Creates order object with all the necessary atributes
   * @param email
   * @param number
   * @param weight
   * @param country
   * @return Order
   */
  public Order enrichOrder(final String email, final String number, final Double weight, final String country ) {
    Order order = Order.builder()
                  .email(email)
                  .phoneNumber(number)
                  .weight(weight)
                  .country(country)
                  .build();
    return order;
  }

  /**
   * Get total number of orders per country
   * @return Orders List
   */
  public List<NumberCount> getTotalOrdersPerCountry() {
    Optional<List<NumberCount>> orders = Optional.ofNullable(
        orderRepository.getTotalOrdersPerCountry());
    if(orders.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    return  orders.get();
  }

  /**
   * Get total weight of orders per country
   * @return Orders List
   */
  public List<WeightCount> getTotalWeightPerCountry(){
    Optional<List<WeightCount>> orders = Optional.ofNullable(
        orderRepository.getTotalWeightPerCountry());
    if(orders.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    return  orders.get();
  }

  /**
   * Get Orders based on the optional parameter
   * @param name
   * @return Orders List
   */
  public List<Order> getOrders(Object name) {
    Pageable page = PageRequest.of(0, 10);

    Optional<List<Order>> orders = Optional.ofNullable(
        orderRepository.getOrders(name,page));
    if(orders.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    return  orders.get();
  }
}
