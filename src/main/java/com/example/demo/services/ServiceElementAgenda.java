package com.example.demo.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.UserDao;
import com.example.demo.beans.Action;
import com.example.demo.beans.elementagenda;

@Service
public class ServiceElementAgenda {

	@Autowired
	UserDao udao;
	
	public void modifier(elementagenda ea, String titre, String description, LocalDate date_debut, LocalDate date_fin, String lieu) {
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
		udao.delete(ea.getClass(), ea.getId());
	}

}
