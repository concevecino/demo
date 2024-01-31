package com.ricoh.orders.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@JsonInclude(Include.NON_NULL)
public class User extends BaseEntity  implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Column(unique = true, length = 48, nullable = false)
	private String name;
	
	@Column(unique = false, length = 48, nullable = false)
	private String firstName;

	@Column(unique = false, length = 48, nullable = false)
	private String familyName;	

	@Column(unique = false, length = 48, nullable = true)
	private String secondFamilyName;
	
	@Column(nullable = false, length=12)
	private String shortName;

	@Column(unique = true, length = 256, nullable = true)
	private String comments;	

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(length = 80, nullable = false)
	private String password;
	

	@ManyToOne
	Tenant tenant;
	
	 
	
	 
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_roles", joinColumns = {@JoinColumn(name="user_id")}, inverseJoinColumns = {@JoinColumn(name="roles_id")} )	
	private Set<Generic> roles;
	
	
	
	
	protected User() {
		/* Reflection instantiation */
	}
	
	
	

	public User(String name, String passwordHash) {
		this.name = name;
		this.password = passwordHash;
	}

	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Generic> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Generic> roles) {
		this.roles = roles;
	}

	public void addRole(Generic role) {
		this.roles.add(role);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Generic> roles = this.getRoles();
		if (roles == null) {
			return Collections.emptyList();
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Generic role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		authorities.add(new SimpleGrantedAuthority( UserRole.Name.USER));
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getSecondFamilyName() {
		return secondFamilyName;
	}

	public void setSecondFamilyName(String secondFamilyName) {
		this.secondFamilyName = secondFamilyName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	   
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
 
	
	


	@Override
	public String toString() {
		return "User [ name=" + name + "]";
	}

 

	
	
}
