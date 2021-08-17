package me.ssoon.springbootreactive.infra;

import me.ssoon.springbootreactive.model.Item;
import me.ssoon.springbootreactive.repository.BlockingItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RepositoryDatabaseLoader {

    @Bean
    CommandLineRunner initialize(BlockingItemRepository repository) {
        return args -> {
            repository.save(new Item("Alf alarm clock", "kids clock", 19.99));
            repository.save(new Item("Smurf TV tray", "kids TV tray", 24.99));
        };
    }
}
