package com.example.demo.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.example.demo.beans.DbObject;

@Transactional
public class GenericDAO {

	@PersistenceContext
	private EntityManager em;
	
	public <T extends DbObject> void create(T entity) {
		em.persist(entity);
	}

	public <T extends DbObject> T findById(Class<T> clazz, long id) {
		return em.find(clazz, id);
	}

	public <T extends DbObject> void update(T entity) {
		if (entity.getId() > 0) {
			em.merge(entity);
		}
	}

	public <T extends DbObject> void delete(Class<T> clazz, long id) {
		T entity = em.find(clazz, id);
		em.remove(entity);
	}

	public <T extends DbObject> List<T> findAll(Class<T> clazz) {
		List<T> resultat = null;
		TypedQuery<T> query = em.createQuery("SELECT entity FROM " + clazz.getName() + " entity", clazz);
		resultat = query.getResultList();
		return resultat;
	}

	/**
	 * Permet de r�cup�rer toutes les entr�es pour une table donn�es � partir d'une
	 * entr�e, pour un nombre de r�sultat donn�
	 * 
	 * @param clazz    : le type que l'on souhaite r�cup�rer
	 * @param begin    : l'index du premier r�sultat
	 * @param nbResult : le nombre de r�sultat que l'on souhaite r�cup�rer
	 * @return une liste d'entr�es pagin�e
	 */
	public <T extends DbObject> List<T> findAll(Class<T> clazz, int begin, int nbResult) {
		List<T> resultat = null;

		// on cr�e la requ�te
		TypedQuery<T> query = em.createQuery("SELECT entity FROM " + clazz.getName() + " entity", clazz);

		// on param�tre et on ex�cute la requ�te, et on r�cup�re le r�sultat
		resultat = query.setFirstResult(begin) // On commence � ce num�ro (begin) - au Ni�me r�sultat
				.setMaxResults(nbResult) // on charge nbResult r�sultats
				.getResultList();

		return resultat;
	}

	public <T extends DbObject> void deleteAll(Class<T> clazz) {
		Query query = em.createQuery("Delete FROM " + clazz.getName());
		query.executeUpdate();
	}
	
	public <T extends DbObject> long countElementby(Class<T> clazz,String att,String vallatt) throws Exception {
		TypedQuery<Long> query=em.createQuery("SELECT COUNT(entity) FROM "+clazz.getName()+" entity WHERE entity."+att+"='"+vallatt+"'", Long.class);
		long result=query.getSingleResult();
		return result;
	}
	
}
