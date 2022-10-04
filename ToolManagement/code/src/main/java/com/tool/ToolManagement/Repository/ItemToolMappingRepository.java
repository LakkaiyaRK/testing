package com.tool.ToolManagement.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tool.ToolManagement.model.ItemToolMapping;

public interface ItemToolMappingRepository extends MongoRepository<ItemToolMapping, String>{

	public ItemToolMapping findByItemId(String id);
	
	public ItemToolMapping findOneById(String id);
	
}
