package com.example.demo.beans;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AgendaPerso extends Agenda {
	
	@OneToOne
	private User user;
	
	public AgendaPerso(String intitule, String description, User user) {
		super(intitule, description);
		this.user = user;
	}

	public AgendaPerso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
