package com.tool.ToolManagement.model;

public class ToolProductivityDto {
	
	private String id;

	private String tools;

	private Integer new_life;
	
	private Integer new_life_price;

	private Integer tool_after_res;
	
	private Integer price_after_res;

	private Integer no_of_res;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public Integer getNew_life() {
		return new_life;
	}

	public void setNew_life(Integer new_life) {
		this.new_life = new_life;
	}

	public Integer getTool_after_res() {
		return tool_after_res;
	}

	public void setTool_after_res(Integer tool_after_res) {
		this.tool_after_res = tool_after_res;
	}

	public Integer getNo_of_res() {
		return no_of_res;
	}

	public void setNo_of_res(Integer no_of_res) {
		this.no_of_res = no_of_res;
	}

	public Integer getNew_life_price() {
		return new_life_price;
	}

	public void setNew_life_price(Integer new_life_price) {
		this.new_life_price = new_life_price;
	}

	public Integer getPrice_after_res() {
		return price_after_res;
	}

	public void setPrice_after_res(Integer price_after_res) {
		this.price_after_res = price_after_res;
	}
}
