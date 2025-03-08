package com.amstech.balloon.decoration.system.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


/**
 * The persistent class for the decoration_banner database table.
 * 
 */
@Entity
@Table(name="decoration_banner")
@NamedQuery(name="DecorationBanner.findAll", query="SELECT d FROM DecorationBanner d")
public class DecorationBanner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String url;

	//bi-directional many-to-one association to Decoration
	@ManyToOne
	@JsonIgnore
	private Decoration decoration;

	public DecorationBanner() {
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Decoration getDecoration() {
		return this.decoration;
	}

	public void setDecoration(Decoration decoration) {
		this.decoration = decoration;
	}

}