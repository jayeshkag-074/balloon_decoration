package com.amstech.balloon.decoration.system.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


/**
 * The persistent class for the colour database table.
 * 
 */
@Entity
@NamedQuery(name="Colour.findAll", query="SELECT c FROM Colour c")
public class Colour implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Decoration
	@ManyToOne
	@JsonIgnore
	private Decoration decoration;

	public Colour() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Decoration getDecoration() {
		return this.decoration;
	}

	public void setDecoration(Decoration decoration) {
		this.decoration = decoration;
	}

}