package com.example.demo.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.UserDao;
import com.example.demo.beans.elementagenda;

@Service
public class ServiceElementAgenda {

	@Autowired
	UserDao userDao;
	
	public void modifier(elementagenda ea, String titre, String description, LocalDateTime date_debut, LocalDateTime date_fin, String lieu) {
		ea.setTitre(titre);
		ea.setDescription(description);
		ea.setDate_debut(date_debut);
		ea.setDate_fin(date_fin);
		ea.setLieu(lieu);
	}
	public void visualisation() {
		
	}
	public <T> void supprimer(T a) {
		elementagenda ea = (elementagenda) a;
		userDao.delete(ea.getClass(), ea.getId());
	}

}
