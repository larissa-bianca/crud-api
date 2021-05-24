package com.menu.api.item;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;

public class Item {
	
	    private final Long id;

	    @NotNull(message = "name is required")
	    @Pattern(regexp="^[a-zA-Z ]+$", message = "name must be a string")
	    private final String name;

	    //Never use floating point numbers to represent monetary values
	    @NotNull(message = "price is required")
	    @Positive(message = "price must be positive")
	    private final Long price;

	    @NotNull(message = "description is required")
	    @Pattern(regexp="^[a-zA-Z ]+$", message = "description must be a string")
	    private final String description;

	    @NotNull(message = "image is required")
	    @URL(message = "image must be a URL")
	    private final String image;
	    
	    public Item( Long id,String name, Long price, String description, String image) {
	        this.id = id;
	        this.name = name;
	        this.price = price;
	        this.description = description;
	        this.image = image;
	    }
	 
	    //To tell Spring which Item entity denotes a unique identifier, we the @Id annotation
	    @Id
	    public Long getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public Long getPrice() {
	        return price;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public String getImage() {
	        return image;
	    }

	    //update an item's properties, preserving the id.
	    public Item updateWith(Item item) {
	        return new Item(
	            this.id,
	            item.name,
	            item.price,
	            item.description,
	            item.image
	        );
	    }
}
