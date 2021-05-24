package com.menu.api.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//CrudRepository is a Spring interface declaring generic CRUD operations

@Repository
public interface InMemoryItemRepository extends CrudRepository<Item, Long> {

}
