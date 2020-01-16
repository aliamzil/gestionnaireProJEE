package com.example.demo.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;

import com.example.demo.enums.Role;


@Entity
public class AgendaGroupe extends Agenda {
	
	private int idAgendaGroupe;
	
	private String titre;
	
	private HashMap<Long, Role> lstu = new HashMap<Long, Role>();
	
	@OneToMany
	private List<Sondage> lstsnd = new ArrayList<Sondage>();

	public AgendaGroupe(int idAgendaGroupe, String titre) {
		super();
		this.idAgendaGroupe = idAgendaGroupe;
		this.titre = titre;
	}

	public AgendaGroupe(String intitule, String description, String titre) {
		super(intitule, description);
		this.titre = titre;
	}

	public AgendaGroupe() {
		super();
	}

	public int getIdAgendaGroupe() {
		return idAgendaGroupe;
	}

	public void setIdAgendaGroupe(int idAgendaGroupe) {
		this.idAgendaGroupe = idAgendaGroupe;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public HashMap<Long, Role> getLstu() {
		return lstu;
	}

	public void setLstu(HashMap<Long, Role> lstu) {
		this.lstu = lstu;
	}
	
	public void putLstu(User u, Role role) {
		this.lstu.put(u.getId(), role);
	}

	public List<Sondage> getLstsnd() {
		return lstsnd;
	}

	public void setLstsnd(List<Sondage> lstsnd) {
		this.lstsnd = lstsnd;
	}
}
