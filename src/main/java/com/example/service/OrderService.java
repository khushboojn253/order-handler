package demo.src.main.java.com.example.service;


import demo.src.main.java.com.example.entity.NumberCount;
import demo.src.main.java.com.example.entity.Order;
import demo.src.main.java.com.example.entity.WeightCount;
import java.util.List;

public interface OrderService {

  List<NumberCount> getTotalOrdersPerCountry();

  List<WeightCount> getTotalWeightPerCountry();

  List<Order> getOrders(String name);
}
