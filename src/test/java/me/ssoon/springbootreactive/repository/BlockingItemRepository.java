package me.ssoon.springbootreactive.repository;

import me.ssoon.springbootreactive.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface BlockingItemRepository extends CrudRepository<Item, String> {
}
