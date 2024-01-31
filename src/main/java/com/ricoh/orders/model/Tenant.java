package com.ricoh.orders.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
@JsonInclude(Include.NON_NULL)
public class Tenant implements INamedEntity  {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false, length=12)
	private String shortName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Temporal(TemporalType.TIMESTAMP)
	Date creationDate;
	
	
	@Column(nullable = false)
	private String uuid;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	

	@Override
	public String toString() {
		return "Tenant [id=" + id + ", name=" + name + "]";
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
