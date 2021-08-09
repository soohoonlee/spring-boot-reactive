package me.ssoon.springbootreactive.repository;

import me.ssoon.springbootreactive.model.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}
