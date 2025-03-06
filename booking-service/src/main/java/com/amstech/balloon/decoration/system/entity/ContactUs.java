package com.amstech.balloon.decoration.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the contact_us database table.
 * 
 */
@Entity
@Table(name="contact_us")
@NamedQuery(name="ContactUs.findAll", query="SELECT c FROM ContactUs c")
public class ContactUs implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	private String email;

	@Column(name="img_url")
	private String imgUrl;

	@Column(name="mobile_number")
	private String mobileNumber;

	//bi-directional many-to-one association to Location
	@ManyToOne
	private Location location;

	public ContactUs() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}