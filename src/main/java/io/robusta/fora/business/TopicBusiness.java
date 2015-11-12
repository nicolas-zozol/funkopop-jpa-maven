package io.robusta.fora.business;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.robusta.fora.domain.Topic;
import io.robusta.fora.domain.User;


public class TopicBusiness {

	private final static Logger logger = Logger.getLogger(TopicBusiness.class
			.getName());


	
	EntityManager em;

	
	
	public TopicBusiness(EntityManager em) {
		this.em = em;
	}

	public Topic getTopicById(long id) {
		return em.find(Topic.class, id);
	}

	public List<Topic> getAllTopics() {
		TypedQuery<Topic> query = em.createQuery("SELECT t FROM Topic t", Topic.class);
		 return query.getResultList();
	}

	public int countTopics() {
		return this.getAllTopics().size();

	}
	
	public List<Topic> findByUser(User u) {
		TypedQuery<Topic> query = em.createQuery("SELECT t FROM Topic t WHERE t.user=:u", Topic.class);
		query.setParameter("user", u);
		return query.getResultList();
	}
}
