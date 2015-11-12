package io.robusta.fora.business;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import io.robusta.fora.domain.Admin;
import io.robusta.fora.domain.Comment;
import io.robusta.fora.domain.Topic;
import io.robusta.fora.domain.User;


public class UserBusiness {

	private final static Logger logger = Logger.getLogger(UserBusiness.class
			.getName());

	
	EntityManager em;

	
	
	
	
	public UserBusiness(EntityManager em) {
		this.em = em;
	}


	public User findByEmail(String email) {


		String jpql = "SELECT u FROM User u WHERE u.email= :email";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}
	
	
	public User findByName(String name) {

		String jpql = "SELECT u FROM User u WHERE u.name= :name";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("name", name);
		return query.getSingleResult();

	}
	
	public User findById(Long id) {

		return em.find(User.class, id);

	}

	
	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}
	
	public long countUsers(){
		return this.findAll().size();
	}
	

	public User createUser(String email, String name, boolean admin, boolean male) {
		
		logger.info("Creating user " + email);
		User u;
		if (admin){
			u = new Admin();
		}else{
			u = new User();
		}
		
		u.setEmail(email);
		u.setName(name);
		if (!male){
			u.setFemale();
		}
		

		u.setId(this.countUsers() +1);
		em.persist(u);

		logger.info("Created user " + email+" with id :"+u.getId());
		return u;
	}

	

	public void deleteUser(User user) {
		logger.info("deleting user " + user);
		
		TopicBusiness topicBusiness = new TopicBusiness(em);
		CommentBusiness commentBusiness = new CommentBusiness(em)
				;
		List<Topic> topics = topicBusiness.findByUser(user);
		List<Comment> comments = commentBusiness.findByUser(user);
		
		
		logger.info("Removing user "+user+" from "+topics.size()+" topics");
		for (Topic topic : topics) {
				
				topic.setUser(null);
		}

		logger.info("Removing user "+user+" from "+comments.size()+" comments");
		for (Comment comment : comments) {			
				comment.setUser(null);
				comment.setAnonymous(true);
		}
		
		em.remove(user);
	}

}
