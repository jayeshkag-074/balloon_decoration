package com.amstech.balloon.decoration.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to ContactUs
	@OneToMany(mappedBy="location")
	private List<ContactUs> contactUses;

	//bi-directional many-to-one association to Decoration
	@OneToMany(mappedBy="location")
	private List<Decoration> decorations;

	//bi-directional many-to-one association to City
	@ManyToOne
	private City city;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="location")
	private List<User> users;

	public Location() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ContactUs> getContactUses() {
		return this.contactUses;
	}

	public void setContactUses(List<ContactUs> contactUses) {
		this.contactUses = contactUses;
	}

	public ContactUs addContactUs(ContactUs contactUs) {
		getContactUses().add(contactUs);
		contactUs.setLocation(this);

		return contactUs;
	}

	public ContactUs removeContactUs(ContactUs contactUs) {
		getContactUses().remove(contactUs);
		contactUs.setLocation(null);

		return contactUs;
	}

	public List<Decoration> getDecorations() {
		return this.decorations;
	}

	public void setDecorations(List<Decoration> decorations) {
		this.decorations = decorations;
	}

	public Decoration addDecoration(Decoration decoration) {
		getDecorations().add(decoration);
		decoration.setLocation(this);

		return decoration;
	}

	public Decoration removeDecoration(Decoration decoration) {
		getDecorations().remove(decoration);
		decoration.setLocation(null);

		return decoration;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setLocation(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setLocation(null);

		return user;
	}

}