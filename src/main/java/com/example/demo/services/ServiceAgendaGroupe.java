package com.example.demo.services;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.GenericDAO;
import com.example.demo.beans.AgendaGroupe;
import com.example.demo.beans.Invitation;
import com.example.demo.beans.Sondage;
import com.example.demo.beans.User;
import com.example.demo.enums.Role;

@Service
public class ServiceAgendaGroupe extends ServiceAgenda {
	
	@Autowired
	@Qualifier("genericDAO")
	GenericDAO dao;
	
	
	public void creerAgendaGroupe(User u, String intitule, String description, String titre) {
		AgendaGroupe ag = new AgendaGroupe(intitule, description, titre);
		ag.putLstu(u, Role.Administrateur);
		dao.create(ag);
	}
	
	// M�thodes de gestion des utilisateurs membres du groupe (agenda groupe)
//	public void inviter(AgendaGroupe agGr,User exp,User u,String lib, String desc) {
//		// [x] envoyer une invitation.
//		Date datedujour = new Date();
//		Invitation invit=new Invitation(agGr,exp,u,lib,desc,datedujour,30);
//		// TODO this.lstu.put(e, r); Integration apr�s acceptation user
//	}
	
	public void exclure(User u,HashMap<User, Role> l) {
		l.remove(u);
	}
	// M�thodes de gestion des sondages
	public void supprimer_sondage(AgendaGroupe agGr,Sondage sondage) {
		agGr.getLstsnd().remove(agGr.getLstsnd().indexOf(sondage));
	}
	
	public void adduser(AgendaGroupe ag, User u) {
		ag.putLstu(u, Role.Visiteur);
		dao.update(ag);
	}
}
