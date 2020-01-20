package com.example.demo.beans;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.example.demo.enums.Role;

@Entity
public class ListUsers extends DbObject {
	
	@ManyToOne
	private AgendaGroupe ag;
	
	@ManyToOne
	private User user;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	public ListUsers() {
		super();
	}

	public ListUsers(AgendaGroupe ag, User user, Role role) {
		super();
		this.ag = ag;
		this.user = user;
		this.role = role;
	}

	public AgendaGroupe getAg() {
		return ag;
	}

	public void setAg(AgendaGroupe ag) {
		this.ag = ag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
