package com.example.demo.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.beans.Action;
import com.example.demo.beans.AgendaGroupe;
import com.example.demo.beans.AgendaPerso;
import com.example.demo.beans.Evenement;
import com.example.demo.beans.ListUsers;
import com.example.demo.beans.User;

@Repository
@Transactional
public class UserDao extends GenericDAO{

	@PersistenceContext
	private EntityManager em;
	
//	private EntityManagerFactory  emf = Persistence.createEntityManagerFactory("agentask"); 
//	private EntityManager em = emf.createEntityManager();
	
	public User chercheruser(String pseudo) {

		Query query=em.createQuery("SELECT user FROM User user WHERE user.pseudo = ?1");
		query.setParameter(1, pseudo);
		
		List<User> result=query.getResultList();
		if(result.isEmpty()) {return null;}
		else {return result.get(0);}
	}
	
	public User chercheruseremail(String email) {

		Query query=em.createQuery("SELECT user FROM User user WHERE user.email = ?1");
		query.setParameter(1, email);
		
		List<User> result=query.getResultList();
		if(result.isEmpty()) {return null;}
		else {return result.get(0);}
	}
	
	public AgendaPerso chercheragendaperso(User u) {

		Query query=em.createQuery("SELECT agendaPerso FROM AgendaPerso agendaPerso WHERE user = ?1");
		query.setParameter(1, u);
		
		List<AgendaPerso> result=query.getResultList();
		if(result.isEmpty()) {return null;}
		else {return result.get(0);}
	}
	
	public List<Action> chercheractionsperso(User u) {
		AgendaPerso ap = this.chercheragendaperso(u);
		Query query=em.createQuery("SELECT action FROM Action action WHERE ap = ?1");
		query.setParameter(1, ap);
		
		List<Action> result=query.getResultList();
		if(result.isEmpty()) {return null;}
		else {return result;}
	}
	
	public List<Evenement> chercherevent(AgendaGroupe ag) {
		Query query=em.createQuery("SELECT evenement FROM Evenement evenement WHERE ag = ?1");
		query.setParameter(1, ag);
		
		List<Evenement> result=query.getResultList();
		if(result.isEmpty()) {return null;}
		else {return result;}
	}
	
	public List<ListUsers> chercherusersag(long idag) {
		AgendaGroupe ag = this.findById(AgendaGroupe.class, idag);
		Query query=em.createQuery("SELECT listusers FROM ListUsers listusers WHERE ag = ?1");
		query.setParameter(1, ag);
		
		List<ListUsers> result=query.getResultList();
		if(result.isEmpty()) {return null;}
		else {return result;}
	}
	
	

}
