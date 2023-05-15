package repository;

import entity.NumberCount;
import entity.Order;
import entity.WeightCount;
import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer>{

  @Query("SELECT o.country, COUNT(o.id)) "
      + "FROM Order AS o GROUP BY o.country")
  List<NumberCount> getTotalOrdersPerCountry();

  @Query("SELECT o.country, SUM(o.weight)) "
      + "FROM Order AS o GROUP BY o.country")
  List<WeightCount> getTotalWeightPerCountry();

  @Query("SELECT o FROM Order o WHERE (:country is null or o.country = :country)")
  List<Order> getOrders(@Param("country") Object country, Pageable pageable);
}
