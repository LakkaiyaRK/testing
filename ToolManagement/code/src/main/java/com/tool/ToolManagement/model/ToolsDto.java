package com.tool.ToolManagement.model;

public class ToolsDto {
	
	private String id;

	private Integer toolno;

	private String sapcode;

	private String description;

	private String make;
	
	private String date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getToolno() {
		return toolno;
	}

	public void setToolno(Integer toolno) {
		this.toolno = toolno;
	}

	public String getSapcode() {
		return sapcode;
	}

	public void setSapcode(String sapcode) {
		this.sapcode = sapcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
