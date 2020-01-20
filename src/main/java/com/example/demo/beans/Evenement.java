package com.example.demo.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("evenement")
public class Evenement extends elementagenda {
	
	@ManyToOne
	private AgendaGroupe ag;
	
	@ManyToMany
	private List<User> liste_participants=new ArrayList<User>();
	
//	@ElementCollection
//	protected Set<User> nickNames = new HashSet();


	public Evenement() {
		super();
	}

	public Evenement(String titre, String description, LocalDateTime date_debut, LocalDateTime date_fin, String lieu, AgendaGroupe ag) {
		super(titre, description, date_debut, date_fin, lieu);
		this.ag = ag;
	}

	public Evenement(AgendaGroupe ag, List<User> liste_participants) {
		super();
		this.ag = ag;
		this.liste_participants = liste_participants;
	}

	public AgendaGroupe getAg() {
		return ag;
	}

	public void setAg(AgendaGroupe ag) {
		this.ag = ag;
	}

	public List<User> getListe_participants() {
		return liste_participants;
	}

	public void setListe_participants(List<User> liste_participants) {
		this.liste_participants = liste_participants;
	}
	
	public void addToListe_participants(User u) {
		this.liste_participants.add(u);
	}

	
}
