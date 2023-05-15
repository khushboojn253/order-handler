package controller;

import entity.NumberCount;
import entity.Order;
import entity.WeightCount;
import java.util.List;
import service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {


  @Autowired
  OrderService orderService;

  @GetMapping("/country/orders")
  public List<NumberCount> getOrdersPerCountry(){
   return orderService.getTotalOrdersPerCountry();
   }

  @GetMapping("/country/weight")
  public List<WeightCount> getTotalWeightPerCountry(){
    return orderService.getTotalWeightPerCountry();
  }

  @GetMapping("/orders/{name}")
  public List<Order> getOrders(@PathVariable("name") String name){
    return orderService.getOrders(name);
  }
}
