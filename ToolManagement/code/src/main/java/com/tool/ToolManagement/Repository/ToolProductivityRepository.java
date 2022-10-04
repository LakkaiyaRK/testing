package com.tool.ToolManagement.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tool.ToolManagement.model.ToolProductivity;
import com.tool.ToolManagement.model.Tools;

public interface ToolProductivityRepository extends MongoRepository<ToolProductivity, String> {

	public ToolProductivity findByToolsId(String id);
	
	public ToolProductivity findOneById(String id);
	
	public List<ToolProductivity> findByToolsIdIn(List<String> tools);
}
