package com.tool.ToolManagement.model;

import java.time.LocalDateTime;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tools")
public class Tools {

	@Id
	private String id;

	private Integer toolno;

	private String sapcode;

	private String description;

	private String make;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime date;

	public Tools() {

	}

	public Tools(Integer toolno, String sapcode, String description, String make) {
		this.toolno = toolno;
		this.sapcode = sapcode;
		this.description = description;
		this.make = make;
	}

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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "users [id=" + id + ", toolno=" + toolno + ", sapcode=" + sapcode + ",description=" + description
				+ ",make=" + make + "]";
	}
}
