package io.robusta.fora.business;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

import io.robusta.fora.domain.Comment;
import io.robusta.fora.domain.Tag;


public class TagBusiness {

	
	EntityManager em;
	
	public TagBusiness(EntityManager em) {
		this.em = em;
	}


	private final static Logger logger = Logger.getLogger(TagBusiness.class
			.getName());

	
	public Tag getOrCreateTag(String tagName){
		Tag tag = em.find(Tag.class, tagName);
		if (tag == null){
			logger.info("Creating new Tag : " +tagName);
			tag = new Tag(tagName);
			em.persist(tag);
		}
		return tag;
	}
	
	
	public void tagComment(String commentId, String tagName) {
		CommentBusiness commentBusiness = new CommentBusiness(em);
		logger.info("tagging comment " + commentId + " with " + tagName);
		Comment comment = commentBusiness.getCommentById(commentId);
		
		Tag tag = this.getOrCreateTag(tagName);
		comment.getTags().add(tag);
	}
	
	
	
	

}
