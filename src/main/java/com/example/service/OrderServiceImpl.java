package demo.src.main.java.com.example.service;

import demo.src.main.java.com.example.entity.NumberCount;
import demo.src.main.java.com.example.entity.Order;
import demo.src.main.java.com.example.entity.WeightCount;
import demo.src.main.java.com.example.repository.OrderRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  OrderRepository orderRepository;

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
