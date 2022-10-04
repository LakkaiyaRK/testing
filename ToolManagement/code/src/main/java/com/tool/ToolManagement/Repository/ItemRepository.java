package com.tool.ToolManagement.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tool.ToolManagement.model.Item;

public interface ItemRepository extends MongoRepository<Item, String>{
	
	public Item findOneById(String id);

}
