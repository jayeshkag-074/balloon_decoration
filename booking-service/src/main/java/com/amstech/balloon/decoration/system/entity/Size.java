package com.amstech.balloon.decoration.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the size database table.
 * 
 */
@Entity
@NamedQuery(name="Size.findAll", query="SELECT s FROM Size s")
public class Size implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Decoration
	@ManyToOne
	private Decoration decoration;

	public Size() {
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