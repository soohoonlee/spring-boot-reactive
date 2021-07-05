package me.ssoon.springbootreactive.controller;

import me.ssoon.springbootreactive.model.Dish;
import me.ssoon.springbootreactive.service.KitchenService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.*;

@RestController
public class ServerController {

    private final KitchenService kitchenService;

    public ServerController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping(value = "/server", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> serveDishes() {
        return this.kitchenService.getDishes();
    }

    @GetMapping(value = "/served-dishes", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> deliverDishes() {
        return this.kitchenService.getDishes().map(Dish::deliver);
    }
}
