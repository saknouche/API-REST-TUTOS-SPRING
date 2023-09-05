package com.sadev.tuto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutorials")
public class Tutorial {

	//Un tuto est composé d'un titre, d'une description, d'un état publié ou non
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private Boolean isPublished;
	
	
	public Tutorial() {
		super();
	}
	
	public Tutorial(String title, String description, Boolean isPublished) {
		super();
		this.title = title;
		this.description = description;
		this.isPublished = isPublished;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Boolean getIsPublished() {
		return isPublished;
	}


	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}


	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", description=" + description + ", isPublished="
				+ isPublished + "]";
	}
	
	
	
}
