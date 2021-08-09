package me.ssoon.springbootreactive.repository;

import me.ssoon.springbootreactive.model.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartRepository extends ReactiveCrudRepository<Cart, String> {
}
