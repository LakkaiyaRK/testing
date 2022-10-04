package com.tool.ToolManagement.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tool.ToolManagement.model.Tools;

public interface ToolRepository extends MongoRepository<Tools, String> {

	public Tools findOneBySapcode(String sapcode);
	
	public Tools findOneById(String id);
	
	public List<Tools> findToolsByIdIn(List<String> tools);
	
	@Query(value = "{ 'date' : {$gte : ?0, $lte: ?1 }}")
	public List<Tools> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate );

}
