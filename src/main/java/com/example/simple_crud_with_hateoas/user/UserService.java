package com.example.simple_crud_with_hateoas.user;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService<K, V> {
    ResponseEntity<V> create(V dto);

    ResponseEntity<EntityModel<V>> get(K id);

    ResponseEntity<V> delete(K id);

    ResponseEntity<V> update(V dto, K id);

    ResponseEntity<CollectionModel<EntityModel<V>>>getAll();
}
