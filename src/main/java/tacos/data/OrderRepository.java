package tacos.data;

import tacos.TacoOrder;

// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Date;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    // Добавляем специализированные методы, которых нет в crud:
    // они обрабатываюся по значению имени в контексте (предметно-ориентированный язык)

    // метод формируется так: ГЛАГОЛ, НЕОБЯЗАТЕЛЬНОЕ ПОДЛЕЖАЩЕЕ, СЛОВО By, ПРЕДИКАТ
    // - глагол: find
    // - подлежащего нет: по умолчанию TacoOrder
    // - By
    // - предикат: DeliveryZip
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
    
    // read, find, get - синонимы
    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
        String deliveryZip,
        Date startDate,
        Date endDate);

    // List<TacoOrder> findByDeliveryToAndDeliveryCityAllIgnoresCase(String deliveryTo, String deliveryCity);

    // List<TacoOrder> findByDeliveryCityOrderByDeliveryTo(String city);

    // но можно явно указывать запрос:
    // @Query("Order o where o.deliveryCity='Seattle'")
    // List<TacoOrder> readOrdersDeliveredInSeattle();

}