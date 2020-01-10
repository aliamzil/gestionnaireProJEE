package com.example.demo.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.example.demo.enums.Role;


@Entity
public class AgendaGroupe extends Agenda {
	private int idAgendaGroupe;
	private String titre;
	
	
	private HashMap<User, Role> lstu = new HashMap<User, Role>();
	
	@OneToMany
	private List<Sondage> lstsnd = new ArrayList<Sondage>();

	public AgendaGroupe(int idAgendaGroupe, String titre) {
		super();
		this.idAgendaGroupe = idAgendaGroupe;
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

	public HashMap<User, Role> getLstu() {
		return lstu;
	}

	public void setLstu(HashMap<User, Role> lstu) {
		this.lstu = lstu;
	}

	public List<Sondage> getLstsnd() {
		return lstsnd;
	}

	public void setLstsnd(List<Sondage> lstsnd) {
		this.lstsnd = lstsnd;
	}
}
