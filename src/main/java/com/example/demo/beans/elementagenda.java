package com.example.demo.beans;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@Inheritance
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING, length = 16)
public class elementagenda extends DbObject {
	
	private String titre;
	private String description;
	private LocalDateTime date_debut;
	private LocalDateTime date_fin;
	private String lieu;
	
	public elementagenda() {
		super();
	}
	
	public elementagenda(String titre, String description, LocalDateTime date_debut, LocalDateTime date_fin, String lieu) {
		super();
		this.titre = titre;
		this.description = description;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.lieu = lieu;
	}
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(LocalDateTime date_debut) {
		this.date_debut = date_debut;
	}
	public LocalDateTime getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(LocalDateTime date_fin) {
		this.date_fin = date_fin;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

}
