package com.example.demo.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Invitation extends DbObject {
	/**
	 * invitation event ou agGroupe toujours l'un des deux � 'null'
	 */
	
	@ManyToOne
	public Evenement event;
	
	@ManyToOne
	public AgendaGroupe agGroupe;
	
	@ManyToOne
	public User expediteur;

	/**
	 * Utilisateur de l'app => User
	 * Exterieur a l'app => Email
	 */
	
	@ManyToOne
	public User destinataire;
	
	public String mail_destinataire;

	public String libelle;
	public String description;
	public Date date_invit;
	public int duree_avant_expiration;// en heures (ou jours)

	// Inviter un autre utilisateur de l'application � un �v�nement
	public Invitation(Evenement event, User expediteur, User destinataire, String libelle, String description,
			Date date_invit, int duree_avant_expiration) {
		super();
		this.event = event;
		this.expediteur = expediteur;
		this.destinataire = destinataire;
		this.libelle = libelle;
		this.description = description;
		this.date_invit = date_invit;
		this.duree_avant_expiration = duree_avant_expiration;
	}

	// Inviter une personne n'utilisant pas l'application � un �v�nement
	public Invitation(Evenement event, User expediteur, String mail_destinataire, String libelle, String description,
			Date date_invit, int duree_avant_expiration) {
		super();
		this.event = event;
		this.expediteur = expediteur;
		this.mail_destinataire = mail_destinataire;
		this.libelle = libelle;
		this.description = description;
		this.date_invit = date_invit;
		this.duree_avant_expiration = duree_avant_expiration;
	}

	// Inviter un autre utilisateur de l'application dans un groupe
	public Invitation(AgendaGroupe agGroupe, User expediteur, User destinataire, String libelle, String description,
			Date date_invit, int duree_avant_expiration) {
		super();
		this.agGroupe = agGroupe;
		this.expediteur = expediteur;
		this.destinataire = destinataire;
		this.libelle = libelle;
		this.description = description;
		this.date_invit = date_invit;
		this.duree_avant_expiration = duree_avant_expiration;
	}

	// Inviter une personne n'utilisant pas l'application dans un groupe
	public Invitation(AgendaGroupe agGroupe, User expediteur, String mail_destinataire, String libelle,
			String description, Date date_invit, int duree_avant_expiration) {
		super();
		this.agGroupe = agGroupe;
		this.expediteur = expediteur;
		this.mail_destinataire = mail_destinataire;
		this.libelle = libelle;
		this.description = description;
		this.date_invit = date_invit;
		this.duree_avant_expiration = duree_avant_expiration;
	}

	public Invitation() {
		super();
	}

	public Evenement getEvent() {
		return event;
	}

	public void setEvent(Evenement event) {
		this.event = event;
	}

	public AgendaGroupe getAgGroupe() {
		return agGroupe;
	}

	public void setAgGroupe(AgendaGroupe agGroupe) {
		this.agGroupe = agGroupe;
	}

	public User getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(User expediteur) {
		this.expediteur = expediteur;
	}

	public User getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(User destinataire) {
		this.destinataire = destinataire; 
	}

	public String getMail_destinataire() {
		return mail_destinataire;
	}

	public void setMail_destinataire(String mail_destinataire) {
		this.mail_destinataire = mail_destinataire;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_invit() {
		return date_invit;
	}

	public void setDate_invit(Date date_invit) {
		this.date_invit = date_invit;
	}

	public int getDuree_avant_expiration() {
		return duree_avant_expiration;
	}

	public void setDuree_avant_expiration(int duree_avant_expiration) {
		this.duree_avant_expiration = duree_avant_expiration;
	}

}
