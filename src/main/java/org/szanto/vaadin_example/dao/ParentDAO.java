package org.szanto.vaadin_example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.szanto.vaadin_example.dao.domain.Parent;

@Repository
public class ParentDAO {
	@PersistenceContext(name="test")
	EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public List<Parent> readAllParents() {
		
	    Query query = entityManager.createQuery("SELECT p FROM Parent p");
	    @SuppressWarnings("unchecked")
		List<Parent> parents = (List<Parent>) query.getResultList();
	    
		return parents;
	}
	
}
