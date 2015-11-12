package io.robusta.fora.business;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.robusta.fora.domain.Comment;
import io.robusta.fora.domain.Topic;
import io.robusta.fora.domain.User;


public class CommentBusiness {

	private final static Logger logger = Logger.getLogger(CommentBusiness.class
			.getName());

	@PersistenceContext
	EntityManager em;
	
	

	public CommentBusiness(EntityManager em) {
		this.em = em;
	}

	public Comment getCommentById(String id) {

		return em.find(Comment.class, id);

	}

	public List<Comment> findAll() {
		TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c",
				Comment.class);
		return query.getResultList();
	}

	public List<Comment> findByUser(User u) {

		TypedQuery<Comment> query = em.createQuery(
				"SELECT c FROM Comment c WHERE c.user=:u", Comment.class);
		query.setParameter("user", u);
		return query.getResultList();
	}

	public Comment createComment(Topic t, String content, User u,
			boolean anonymous) {

		logger.info("Creating comment by " + u + " in topic '" + t.getTitle()
				+ "'");

		Comment c = new Comment();
		c.setAnonymous(anonymous);
		if (!anonymous) {
			c.setUser(u);
		}
		c.setContent(content);
		em.persist(c);
		t.getComments().add(c);

		return c;
	}
}
