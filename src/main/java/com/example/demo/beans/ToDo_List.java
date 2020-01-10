/**
 *  /!\ CLASSE OBSOLETE !
 */
package com.example.demo.beans;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.example.demo.enums.StatusTache;

@Entity
public class ToDo_List extends DbObject {
	private String tache;
	private StatusTache statustache;
	
	@ManyToOne
	private User user;
	public ToDo_List(String tache, StatusTache statustache) {
		super();
		this.tache = tache;
		this.statustache = StatusTache.ouverte;
	}
	public ToDo_List() {
		super();
	}
	public String getTache() {
		return tache;
	}
	public void setTache(String tache) {
		this.tache = tache;
	}
	public StatusTache getStatustache() {
		return statustache;
	}
	public void setStatustache(StatusTache statustache) {
		this.statustache = statustache;
	}
	public User getIduser() {
		return user;
	}
	public void setIduser(User user) {
		this.user = user;
	}
	
}
