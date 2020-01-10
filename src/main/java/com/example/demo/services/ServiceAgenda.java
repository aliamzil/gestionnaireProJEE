package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.beans.Action;
import com.example.demo.beans.Agenda;
import com.example.demo.beans.User;

@Service
public class ServiceAgenda {
	public void supprimer_tache(Action tache) {
		
	}
	public void accepter_tache(Action tache,User u) {
		
	}
	public void abandoner_tache(Action tache,User u) {
		
	}
	public void valider_tache(Action tache,User u) {
		
	}
	/**
	 * M�thodes de configuration / personalisation
	 */
	public void personalisation(Agenda ag, String scc_user,int taille_police) {
		ag.setCss_user(scc_user);
		ag.setTaille_police(taille_police);
	}
}
