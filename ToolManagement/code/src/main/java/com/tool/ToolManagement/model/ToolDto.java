package com.tool.ToolManagement.model;

public class ToolDto {

	private String tool_id;
	
	private int No_of_tools_required;
	
	private int Total_cost;

	public String getTool_id() {
		return tool_id;
	}

	public void setTool_id(String tool_id) {
		this.tool_id = tool_id;
	}

	public int getNo_of_tools_required() {
		return No_of_tools_required;
	}

	public void setNo_of_tools_required(int no_of_tools_required) {
		No_of_tools_required = no_of_tools_required;
	}

	public int getTotal_cost() {
		return Total_cost;
	}

	public void setTotal_cost(int total_cost) {
		Total_cost = total_cost;
	}



	
}
