package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.GenericDAO;
import com.example.demo.DAO.UserDao;
import com.example.demo.beans.AgendaGroupe;
import com.example.demo.beans.Evenement;
import com.example.demo.beans.User;

@Service
public class ServiceEvenement extends ServiceElementAgenda {

	@Autowired
	@Qualifier("genericDAO")
	GenericDAO dao;
	@Autowired
	UserDao userDao;
	
	public void creer(String titre, String description, LocalDateTime date_debut, LocalDateTime date_fin, String lieu, AgendaGroupe ag) {
		Evenement e1 = new Evenement(titre, description, date_debut,date_fin, lieu, ag);
		userDao.create(e1);
	}
	public void supprimer(int id) {
		dao.delete(Evenement.class, id);
	}
//	public void Inviter(Evenement e,User u,String lib, String desc) {
//		Date datedujour = new Date();
//		Invitation invit=new Invitation(e,e.getAp().getUser(),u,lib,desc,datedujour,30);
//	}
	public void adduser(Evenement e, User u) {
		List<User> lu = e.getListe_participants();
		lu.add(u);
		e.setListe_participants(lu);
		dao.update(e);
	}
	public void Exclure(Evenement e, User u) {
		List<User> lu = e.getListe_participants();
		lu.remove(lu.indexOf(u));
		e.setListe_participants(lu);
		dao.update(e);
	}

}
