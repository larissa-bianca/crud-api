package com.menu.api.item;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

//✨ Compact imports ✨
//import org.springframework.web.bind.annotation.*; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
// 👇 Ensures all the endpoints share the same resource path.
@RequestMapping("api/menu/items")
public class ItemController {
  private final ItemService service;

  public ItemController(ItemService service) {
    this.service = service;
  }
  
  //GET controller method
  @GetMapping
  public ResponseEntity<List<Item>> findAll() {
      List<Item> items = service.findAll();
      return ResponseEntity.ok().body(items);
  }

  //GET controller method 	
  @GetMapping("/{id}")
  public ResponseEntity<Item> find(@PathVariable("id") Long id) {
	//👇 An Optional type is a polymorphic type that represents an encapsulation of an optional value.
      Optional<Item> item = service.find(id);
      return ResponseEntity.of(item);
  }
  
  //POST controller method
  //@Valid to expect a valid @RequestBody
  @PostMapping
  public ResponseEntity<Item> create(@Valid @RequestBody Item item) {
      Item created = service.create(item);
      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(created.getId())
              .toUri();
      return ResponseEntity.created(location).body(created);
  }

  // PUT controller method
  //@Valid to expect a valid @RequestBody
  @PutMapping("/{id}")
  public ResponseEntity<Item> update(
          @PathVariable("id") Long id,
          @Valid @RequestBody Item updatedItem) {

      Optional<Item> updated = service.update(id, updatedItem);

      return updated
              .map(value -> ResponseEntity.ok().body(value))
              .orElseGet(() -> {
                  Item created = service.create(updatedItem);
                  URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                          .path("/{id}")
                          .buildAndExpand(created.getId())
                          .toUri();
                  return ResponseEntity.created(location).body(created);
              });
  }

  //DELETE controller method
  @DeleteMapping("/{id}")
  public ResponseEntity<Item> delete(@PathVariable("id") Long id) {
      service.delete(id);
      return ResponseEntity.noContent().build();
  }
  
  //👇Spring throws a MethodArgumentNotValidException when a validation error happens. 
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
      List<ObjectError> errors = ex.getBindingResult().getAllErrors();
      Map<String, String> map = new HashMap<>(errors.size());
      errors.forEach((error) -> {
          String key = ((FieldError) error).getField();
          String val = error.getDefaultMessage();
          map.put(key, val);
      });
      return ResponseEntity.badRequest().body(map);
  }
}
