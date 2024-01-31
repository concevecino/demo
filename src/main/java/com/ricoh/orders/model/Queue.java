package com.ricoh.orders.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Queue implements INamedEntity, ITenantEntity {
	
	private static final long serialVersionUID = 1L;
	
	public enum QueueTag {
		REQUEST_NAME(6000),		// Name is requested in order to get a ticket (office)
		REQUEST_PHONE(6001),	// Name is requested in order to get a ticket (office)
		PRINTERLESS(6002),		// kiosk will not print tickets. paperles!!	
		REQUEST_EMAIL(6003)	// Name is requested in order to get a ticket (office)
		;
		
		int val;
		QueueTag(int val) {
			this.val = val;
		}
		public int getVal() {
			return val;
		}		
	};


	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = false, length=12)
	private String shortName;
	
	@Column(nullable = true)
	private String openingHours;
	
	/**
	 * At this hour, the queue will be reset everyday by a background thread. This thread would delete all pending tickets.
	 */
	@Column(nullable = true)
	private Integer resetQueueHour = 23;
	
	/**
	 * When did the last reset happen. Used to calculate when the next reset needs to happen.
	 */
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	Date lastReset;	

	
	@Column(nullable = true)
	Integer numberOfStations;
	
	
	@Column(nullable = true)
	private String image1Url;	
	
	@ManyToOne
	Tenant tenant;
	
	 
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="queue_generic", joinColumns = {@JoinColumn(name="queue_id")}, inverseJoinColumns = {@JoinColumn(name="generic_id")} )	
	Set<Generic> configuration;
	
	
	 
	@Column(nullable = true)
	private String addressLine1;

	@Column(nullable = true)
	private String addressLine2;

	@Column(nullable = true)
	private String addressLine3;

	@Column(nullable = true)
	private String zip;

	@Column(nullable = true)
	private String email;

	@Column(nullable = true)
	private String phone;
	
	@Column(nullable = true)
	private String beaconUUID;
	
	@Column(nullable = true)
	private String webcamURL;	
	
	@Column(nullable = true)
	Double lat;
	
	@Column(nullable = true)
	Double lon;

	@ManyToOne(fetch=FetchType.LAZY )
	Generic country;

	@ManyToOne(fetch=FetchType.LAZY )
	Generic province;
	
	@ManyToOne(fetch=FetchType.LAZY )
	Generic city;	
	
	 
	
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

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public Set<Generic> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Set<Generic> configuration) {
		this.configuration = configuration;
	}
	
	public String getImage1Url() {
		return image1Url;
	}

	public void setImage1Url(String image1Url) {
		this.image1Url = image1Url;
	}


	public Integer getResetQueueHour() {
		return resetQueueHour;
	}

	public void setResetQueueHour(Integer resetQueueHour) {
		this.resetQueueHour = resetQueueHour;
	}

	 	

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the addressLine3
	 */
	public String getAddressLine3() {
		return addressLine3;
	}

	/**
	 * @param addressLine3 the addressLine3 to set
	 */
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the beaconUUID
	 */
	public String getBeaconUUID() {
		return beaconUUID;
	}

	/**
	 * @param beaconUUID the beaconUUID to set
	 */
	public void setBeaconUUID(String beaconUUID) {
		this.beaconUUID = beaconUUID;
	}

	/**
	 * @return the webcamURL
	 */
	public String getWebcamURL() {
		return webcamURL;
	}

	/**
	 * @param webcamURL the webcamURL to set
	 */
	public void setWebcamURL(String webcamURL) {
		this.webcamURL = webcamURL;
	}

	/**
	 * @return the lat
	 */
	public Double getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(Double lat) {
		this.lat = lat;
	}

	/**
	 * @return the lon
	 */
	public Double getLon() {
		return lon;
	}

	/**
	 * @param lon the lon to set
	 */
	public void setLon(Double lon) {
		this.lon = lon;
	}

	/**
	 * @return the country
	 */
	public Generic getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Generic country) {
		this.country = country;
	}

	public Generic getProvince() {
		return province;
	}

	public void setProvince(Generic province) {
		this.province = province;
	}

	public Generic getCity() {
		return city;
	}

	public void setCity(Generic city) {
		this.city = city;
	}

	public Integer getNumberOfStations() {
		return numberOfStations;
	}

	public void setNumberOfStations(Integer numberOfStations) {
		this.numberOfStations = numberOfStations;
	}

	 	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public Date getLastReset() {
		return lastReset;
	}

	public void setLastReset(Date lastReset) {
		this.lastReset = lastReset;
	}
	
		
	@Override
	public String toString() {
		return "Queue [id=" + id + ", name=" + name + "]";
	}
 	
}
