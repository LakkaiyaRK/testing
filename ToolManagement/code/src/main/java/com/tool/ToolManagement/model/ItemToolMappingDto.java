package com.tool.ToolManagement.model;

import java.util.List;

public class ItemToolMappingDto {
	
	private String id;
	
	private String item;
	
	private List<String> tools;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}
	
	public void setItem(String item) {
		this.item = item;
	}

	public List<String> getTools() {
		return tools;
	}

	public void setTools(List<String> tools) {
		this.tools = tools;
	}
}
