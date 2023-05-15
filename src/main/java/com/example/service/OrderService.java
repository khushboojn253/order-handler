package service;

import entity.NumberCount;
import entity.Order;
import entity.WeightCount;
import java.util.List;

public interface OrderService {

  List<NumberCount> getTotalOrdersPerCountry();

  List<WeightCount> getTotalWeightPerCountry();

  List<Order> getOrders(String name);
}
