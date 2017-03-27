package com.sdi.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sdi.model.Waypoint;


/**
 * This class represents a value type
 * @author alb
 */
@XmlRootElement(name="addressPoint")
public class AddressPoint implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String address;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private Waypoint waypoint;
	
	public AddressPoint() {}//crear constructor en vacio para que funcione REST
	
	public AddressPoint(String address, String city, String state, 
			String country, String zipCode, Waypoint waypoint) {
		
		super();
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
		this.waypoint = waypoint;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	@XmlElement
	public String getCity() {
		return city;
	}

	@XmlElement
	public String getState() {
		return state;
	}
	
	@XmlElement
	public String getCountry() {
		return country;
	}

	@XmlElement
	public String getZipCode() {
		return zipCode;
	}

	@XmlElement
	public Waypoint getWaypoint() {
		return waypoint;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setWaypoint(Waypoint waypoint) {
		this.waypoint = waypoint;
	}

	@Override
	public String toString() {
		return "Destination [address=" + address + ", city=" + city 
				+ ", state=" + state + ", country=" + country
				+ ", zipCode=" + zipCode + ", waypoint=" + waypoint + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((waypoint == null) ? 0 : waypoint.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressPoint other = (AddressPoint) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (waypoint == null) {
			if (other.waypoint != null)
				return false;
		} else if (!waypoint.equals(other.waypoint))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

}
