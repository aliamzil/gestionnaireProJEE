package com.example.demo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public abstract class Agenda extends DbObject {
	
	
	private String intitule;
	
	private String description;
	
	@OneToMany
	private List<Action> todolist = new ArrayList<Action>();
	
//  Dans Agenda Groupe
//	private List<Sondage> sondagelist = new ArrayList<Sondage>();
    // --- ATRIBUTS CONFIG --- //
	private String css_user;
	private int taille_police;
	// --- --- --- --- --- --- //
	public Agenda(String intitule, String description) {
		super();
		this.intitule = intitule;
		this.description = description;
	}
	public Agenda() {
		super();
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Action> getTodolist() {
		return todolist;
	}
	public void setTodolist(List<Action> todolidt) {
		this.todolist = todolidt;
	}
//  Dans Agenda Groupe
//	public List<Sondage> getSondagelist() {
//		return sondagelist;
//	}
//	public void setSondagelist(List<Sondage> sondagelist) {
//		this.sondagelist = sondagelist;
//	}
	/**
	 * M�thodes de configuration / personalisation
	 */
	public String getCss_user() {
		return css_user;
	}
	public void setCss_user(String css_user) {
		this.css_user = css_user;
	}
	public int getTaille_police() {
		return taille_police;
	}
	public void setTaille_police(int taille_police) {
		this.taille_police = taille_police;
	}
}
