package me.ssoon.springbootreactive.service;

import me.ssoon.springbootreactive.model.Item;
import me.ssoon.springbootreactive.repository.ItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.domain.Example.of;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class InventoryService {

    private final ItemRepository itemRepository;

    private final ReactiveFluentMongoOperations fluentMongoOperations;

    public InventoryService(ItemRepository itemRepository, ReactiveFluentMongoOperations fluentMongoOperations) {
        this.itemRepository = itemRepository;
        this.fluentMongoOperations = fluentMongoOperations;
    }

    public Flux<Item> search(String partialName, String partialDescription, boolean useAnd) {
        if (partialName != null) {
            if (partialDescription != null) {
                if (useAnd) {
                    return itemRepository.findByNameContainingAndDescriptionContainingAllIgnoreCase(partialName, partialDescription);
                } else {
                    return itemRepository.findByNameContainingOrDescriptionContainingAllIgnoreCase(partialName, partialDescription);
                }
            } else {
                return itemRepository.findByNameContaining(partialName);
            }
        } else {
            if (partialDescription != null) {
                return itemRepository.findByDescriptionContainingIgnoreCase(partialDescription);
            } else {
                return itemRepository.findAll();
            }
        }
    }

    public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (useAnd)
                ? ExampleMatcher.matchingAll()
                : ExampleMatcher.matchingAny()
                    .withStringMatcher(CONTAINING)
                    .withIgnoreCase()
                    .withIgnorePaths("price");

        Example<Item> probe = of(item, matcher);

        return itemRepository.findAll(probe);
    }

    public Flux<Item> searchByFluentExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (useAnd)
                ? ExampleMatcher.matchingAll()
                : ExampleMatcher.matchingAny()
                .withStringMatcher(CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price");

        return fluentMongoOperations.query(Item.class)
                .matching(query(byExample(of(item, matcher))))
                .all();
    }

}
