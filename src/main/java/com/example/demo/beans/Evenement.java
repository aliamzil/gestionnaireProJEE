package com.example.demo.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("evenement")
public class Evenement extends elementagenda {
	
	@ManyToOne
	private AgendaPerso ap;
	
	@OneToMany
	private List<User> liste_participants=new ArrayList<User>();

	public Evenement() {
		super();
	}

	public Evenement(String titre, String description, LocalDateTime date_debut, LocalDateTime date_fin, String lieu, AgendaPerso ap) {
		super(titre, description, date_debut, date_fin, lieu);
		this.ap = ap;
	}

	public Evenement(AgendaPerso ap, List<User> liste_participants) {
		super();
		this.ap = ap;
		this.liste_participants = liste_participants;
	}

	public AgendaPerso getAp() {
		return ap;
	}

	public void setAp(AgendaPerso ap) {
		this.ap = ap;
	}

	public List<User> getListe_participants() {
		return liste_participants;
	}

	public void setListe_participants(List<User> liste_participants) {
		this.liste_participants = liste_participants;
	}

	
}
