package com.menu.api.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
// 👇 Annotation to activate Map repositories
@EnableMapRepositories
public class ItemService {
	private final CrudRepository<Item, Long> repository;
	
	public ItemService(CrudRepository<Item, Long> repository) {
		this.repository = repository;
		// 👇 Populate the in-memory store
	    this.repository.saveAll(defaultItems());
	}
	
	 //List of items 
	private static List<Item> defaultItems() {
		return List.of(
				new Item(1L, "Burger", 599L, "Tasty", "https://cdn.auth0.com/blog/whatabyte/burger-sm.png"),
				new Item(2L, "Pizza", 299L, "Cheesy", "https://cdn.auth0.com/blog/whatabyte/pizza-sm.png"),
				new Item(3L, "Tea", 199L, "Informative", "https://cdn.auth0.com/blog/whatabyte/tea-sm.png")
	    );
	}
	//List all items
	public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        Iterable<Item> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }
	
	//Find a Item by id
    public Optional<Item> find(Long id) {
        return repository.findById(id);
    }
    
    //Create a new Item
    public Item create(Item item) {
        // To ensure the item ID remains unique,
        // use the current timestamp.
        Item copy = new Item(
                new Date().getTime(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                item.getImage()
        );
        return repository.save(copy);
    }
    
    //Update a item by Id
    public Optional<Item> update( Long id, Item newItem) {
        // Only update an item if it can be found first.
        return repository.findById(id)
                .map(oldItem -> {
                   Item updated = oldItem.updateWith(newItem);
                   return repository.save(updated);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }	


}
