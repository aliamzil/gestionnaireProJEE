package com.example.demo.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


 
@Entity
@Component
public class User extends DbObject implements Serializable {

	@Column(length = 50)
	private String pseudo;

	@Column(length = 50)
	private String email;

	@Column(length = 50)
	private String pass;
	
	private LocalDate date;
	
	@ManyToMany(mappedBy = "liste_participants")
	@JsonIgnore
	public List<Evenement> lste = new ArrayList<Evenement>();
	
	/**
	 * @param pseudo
	 * @param email
	 * @param pass
	 * @param date
	 * 
	 */
	
	public User(String pseudo, String email, String pass, LocalDate date) {
		this.pseudo = pseudo;
		this.email = email;
		this.pass = pass;
		this.date = date;
	}
	public User() {
		super();
	}

	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<Evenement> getLste() {
		return lste;
	}
	public void setLste(List<Evenement> lste) {
		this.lste = lste;
	}
}