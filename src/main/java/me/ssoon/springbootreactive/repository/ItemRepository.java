package me.ssoon.springbootreactive.repository;

import me.ssoon.springbootreactive.model.Item;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {

    Flux<Item> findByNameContaining(String partialName);

    @Query(value = "{ 'name' : ?0, 'age' : ?1 }")
    Flux<Item> findItemsForCustomerMonthlyReport(String name, int age);

    @Query(sort = "{ 'age' : -1 }")
    Flux<Item> findSortedStuffForWeeklyReport();
}
