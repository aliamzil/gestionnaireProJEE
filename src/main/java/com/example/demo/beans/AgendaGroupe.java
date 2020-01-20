package com.example.demo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class AgendaGroupe extends Agenda {
	
//	@Autowired
//	UserDao userDao;
	
	private int idAgendaGroupe;
	
	private String titre;
	
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

//	public void putLstu(User u, Role role) {
//		ListUsers lu = new ListUsers(this,u,role);
//		userDao.create(lu);
//	}

	public List<Sondage> getLstsnd() {
		return lstsnd;
	}

	public void setLstsnd(List<Sondage> lstsnd) {
		this.lstsnd = lstsnd;
	}
}
