package com.tool.ToolManagement.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "item_tool_mapping")
public class ItemToolMapping {
	
	@Id
	private String id;
	
	private Item item;
	
	private List<Tools> tools;
	
	public ItemToolMapping() {
		
	}
	
	public ItemToolMapping(Item item, List<Tools> tools) {
		this.item = item;
		this.tools = tools;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Tools> getTools() {
		return tools;
	}

	public void setTools(List<Tools> tools) {
		this.tools = tools;
	}
	
	@Override
	public String toString() {
		return "users [id=" + id + ", item=" + item + ", tools=" + tools + "]";
	}
}
