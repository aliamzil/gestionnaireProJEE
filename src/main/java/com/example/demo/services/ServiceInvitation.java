package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.GenericDAO;
import com.example.demo.beans.Invitation;

@Service
public class ServiceInvitation {
	@Autowired
	@Qualifier("genericDAO")
	GenericDAO dao;

	@Autowired
	ServiceEvenement se;
	@Autowired
	ServiceAgendaGroupe sag;

//	public void accept(Invitation i) {
//		if(i.getEvent()!=null) {
//			se.adduser(i.getEvent(), i.getDestinataire());
//		}
//		if(i.getAgGroupe()!=null) {
//			sag.adduser(i.getAgGroupe(), i.getDestinataire());
//		}
//		dao.delete(i.getClass(), i.getId());
//	}
	public void reffuse(Invitation i) {
		dao.delete(i.getClass(), i.getId());
	}
}