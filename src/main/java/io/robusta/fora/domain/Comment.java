package io.robusta.fora.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Comment implements Serializable, HasTag {

	
	private static final long serialVersionUID = -1622931234744081145L;


	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	String id;
	
	String content;

	@ManyToOne
	User user = null;
	boolean anonymous = true;
	@OneToMany
	List<Tag>tags = new ArrayList<Tag>();
	
	int score;
	
	
	public Comment() {
		this.id = UUID.randomUUID().toString();
	}
	public Comment(String email, String content) {
		User user = new User();
		user.setEmail(email);
		user.setId(new Random().nextLong());
		this.id = UUID.randomUUID().toString();
		this.content = content;
	}
	
	
	
	
	public Comment( User user, String content) {
		this.id =  UUID.randomUUID().toString();
		this.content = content;
		this.user = user;
		
		if (user != null){
			this.anonymous=false;
		}
		this.score = 0;
	}
	
	//@XmlTransient
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
		this.anonymous = (user == null);
		
	}


	public boolean isAnonymous() {
		return anonymous;
	}


	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	
	/*public List<Comment> getComments() {
		return comments;
	}*/
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	@Override
	public boolean isTagged() {
		return this.tags == null || this.tags.isEmpty();
	}


	@Override
	public List<Tag> getTags() {
		return this.tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	@Override
	public String toString() {
		if (user != null){
			return this.user+" said :"+ this.content;
		}else{
			return "Anonymous said :"+ this.content;
		}
		
	}
	
	public void up(){
		this.score++;
	}
	
	public void down(){
		this.score--;
	}
	
	
	public int getScore() {
		return score;
	}
	
}
