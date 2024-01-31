package com.ricoh.orders.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Generic extends BaseEntity  { 
	
	public enum GenericType {
		COUNTRY(1),
		PROVINCE(2),
		CHANNEL(3),
		CITY(4),
		QUEUE_TAG(5),
		USER_ROLE(6)
		;
		
		int val;
		GenericType(int val) {
			this.val = val;
		}
		public int getVal() {
			return val;
		}		
	};
	
	 

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true, length=8)
	private String shortName;
	

	@Column(nullable = false)
	private Integer type;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne (fetch=FetchType.LAZY )
	Generic parent;

	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public void setTType(GenericType type) {
		this.type = type.getVal();
	}		

	public Generic getParent() {
		return parent;
	}

	public void setParent(Generic parent) {
		this.parent = parent;
	}
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	
	 
	
	@Override
	public String toString() {
		return "Generic [name="  + name + ", type=" + type + "]";
	}
	
}
