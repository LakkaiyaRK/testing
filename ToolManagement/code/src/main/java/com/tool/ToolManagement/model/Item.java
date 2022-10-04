package com.tool.ToolManagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "item")
public class Item {

	@Id
	private String id;

	private Integer item_code;

	private String item_name;

	private String item_description;
	
	
	
	public Item() {
		
	}
	
	public Item(Integer item_code, String item_name, String item_description) {
		this.item_code=item_code;
		this.item_name=item_name;
		this.item_description=item_description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getItem_code() {
		return item_code;
	}

	public void setItem_code(Integer item_code) {
		this.item_code = item_code;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	@Override
	public String toString() {
		return "users [id=" + id + ", item_code=" + item_code + ", item_name=" + item_name + ",item_description=" + item_description + "]";
	}
}
