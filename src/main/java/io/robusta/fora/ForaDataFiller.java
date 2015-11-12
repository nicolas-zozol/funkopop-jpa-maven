package io.robusta.fora;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import io.robusta.fora.domain.Comment;
import io.robusta.fora.domain.MetaData;
import io.robusta.fora.domain.Tag;
import io.robusta.fora.domain.Topic;
import io.robusta.fora.domain.User;

//This singleton EJB is created on startup. 
// Immediate action must be init in @PostConstruct method


public class ForaDataFiller {

	private static Logger logger = Logger.getLogger(ForaDataFiller.class.getName());


	EntityManager em;

	MetaData metaData;
	

	// ids for topics
	public static final long TROLL_ID = 1L;
	public static final long US_ID = 2L;
	public static final long SPICE_ID = 3L;

	// some String ids for comments
	public static final String NOT_OK = "Not_Ok";
	public static final String You_dont_know_enough = "Dont_know";

	/*
	 * private List<User> users = new ArrayList<User>(); private List<Topic>
	 * topics = new ArrayList<Topic>(); private List<Comment> comments = new
	 * ArrayList<Comment>(); private List<Tag> tags = new ArrayList<Tag>();
	 */
	private Topic troll;
	private Topic games;
	private Topic spices;

	private Tag violence;
	private Tag fun;
	private Tag science;

	// private Admin nicolas, leonard;

	public ForaDataFiller() {

	}

	//@PostConstruct
	public void initSingleton() {
		getOrCreateMetaData();
		if (!this.metaData.isDataFilled()) {
			logger.info("Database looks empty, let's fill it");
			deleteAll();
			this.initDataSource();
		} else {
			
			if (this.metaData.isAskDelete()) {
				logger.warning("DELETING ALL database, as it is asked");
				deleteAll();
				// this.initDataSource();
				logger.info("Deleted everything ; Restart the server to refill");
			} else {
				logger.info("Database looks full, no database operation");
			}

		}
	}

	public MetaData getOrCreateMetaData() {
		this.metaData = em.find(MetaData.class, 1L);
		if (this.metaData == null) {
			this.metaData = new MetaData();
			em.persist(this.metaData);
		}
		return this.metaData;
	}

	public void deleteAll() {

		// em.createQuery("DELETE FROM Comment").executeUpdate();
		logger.warning("DELETING ALL Topics with their related Comments joins");
		em.createQuery("DELETE FROM Topic").executeUpdate();
		logger.warning("DELETING ALL Comments");
		em.createQuery("DELETE FROM Comment").executeUpdate();
		logger.warning("DELETING ALL Admins");
		em.createQuery("DELETE FROM Admin").executeUpdate();
		logger.warning("DELETING ALL users");
		em.createQuery("DELETE FROM User").executeUpdate();
		logger.warning("DELETING ALL Tags");
		em.createQuery("DELETE FROM Tag").executeUpdate();
		logger.info("Fininshed to delete All, setting no more delete on Metadata");
		this.metaData.setDataFilled(false);
		this.metaData.setAskDelete(false);
	}

	public void initDataSource() {

		initTags();
		//initUsers();
		//initTopics();
		//initComments();

		if (!this.metaData.isDataFilled()) {
			this.metaData.setDataFilled(true);
		}

	}

	private void initTags() {

		logger.info("Filling database with Tags");

		violence = new Tag("violence");
		fun = new Tag("fun");
		science = new Tag("science");

		em.persist(violence);
		em.persist(fun);
		em.persist(science);

	}

	private void initUsers() {

		logger.info("Filling database with Users");

		User nicolas = new User("Nicolas");
		// Admin nicolas = new Admin(1L, "Nicolas","Star Wars rocks !");

		User leonard = new User( "Leonard");
		// Admin leonard = new Admin(2L,"Leonard","Star Trek rocks");

		User sheldon = new User( "Sheldon");
		User raj = new User("Raj");
		User howard = new User( "Howard");
		User penny = new User("Penny");
		User emy = new User( "Emy");
		User bernie = new User( "Bernie");

		emy.setFemale();
		bernie.setFemale();
		penny.setFemale();

		em.persist(nicolas);
		em.persist(leonard);
		em.persist(sheldon);
		em.persist(raj);
		em.persist(howard);
		em.persist(penny);
		em.persist(emy);
		em.persist(bernie);
	}

	private void initTopics() {
		troll = new Topic();
		troll.setTitle("Star Trek > Star Wars");
		troll.setUser(leonard());

		games = new Topic();
		games.setTitle("American Football is the best game");
		games.setUser(penny());

		spices = new Topic();
		spices.setTitle("Ketchup is not a Spice");
		spices.setUser(emy());
		// Usually, we would like to make business stuff with a addComment
		// method
		// Collections.addAll(this.topics, troll, games, spices);
		em.persist(troll);
		em.persist(games);
		em.persist(spices);
	}

	public List<User> getUsers() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	public User findByName(String name) {
		String jpql = "SELECT u FROM User u WHERE u.name = :name";
		TypedQuery<User> query = em.createQuery(jpql, User.class).setParameter("name", name);
		User result = query.getSingleResult();
		return result;
	}

	public User nicolas() {
		return findByName("Nicolas");
		//return em.find(Admin.class, 1L);
	}

	public User leonard() {
		//return em.find(Admin.class, 2L);
		return findByName("Leonard");
	}

	public User sheldon() {
		//return em.find(User.class, 3L);
		return findByName("Sheldon");
	}

	public User raj() {
		//return em.find(User.class, 4L);
		return findByName("Raj");
	}

	public User howard() {
		//return em.find(User.class, 5L);
		return findByName("Howard");
	}

	// nicolas, leonard, sheldon, raj, howard, penny, emy, bernie

	public User penny() {
		//return em.find(User.class, 6L);
		return findByName("Penny");
	}

	public User emy() {
		//return em.find(User.class, 7L);
		return findByName("Emy");
	}

	public User bernie() {
		//return em.find(User.class, 8L);
		return findByName("Bernie");
	}

	public List<Topic> getTopics() {
		TypedQuery<Topic> query = em.createQuery("SELECT t FROM Topic t", Topic.class);
		return query.getResultList();
	}

	public List<Comment> getComments() {

		TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c", Comment.class);
		return query.getResultList();
	}

	public List<Tag> getTags() {
		TypedQuery<Tag> query = em.createQuery("SELECT t FROM Tag t", Tag.class);
		return query.getResultList();
	}

	private void initComments() {
		initTrollComments();
		initGamesComments();
		initSpicesComments();
	}

	private void initTrollComments() {

		Comment c1 = new Comment();
		c1.setId(NOT_OK);
		c1.setUser(nicolas());
		c1.setContent("I am not ok");

		Comment c2 = new Comment();
		c2.setId(You_dont_know_enough);
		c2.setUser(leonard());
		c2.setContent("You don't know enough about heroes");

		Comment c3 = new Comment();
		c3.setId("3");
		c3.setAnonymous(true);
		c3.setContent("What ? You stupid !");
		c3.getTags().add(violence);

		troll.addComments(c1, c2, c3);
		em.persist(c1);
		em.persist(c2);
		em.persist(c3);

	}

	private void initGamesComments() {

		Comment c1 = new Comment(penny(), "There are so many strategies");
		Comment c2 = new Comment(leonard(), "What ? These guys are stupid !");
		Comment c3 = new Comment(penny(), "They know how to count to 4");
		Comment c4 = new Comment(sheldon(), "So why do they call it football and play with hands ?");

		games.addComments(c1, c2, c3, c4);

		em.persist(c1);
		em.persist(c2);
		em.persist(c3);
		em.persist(c4);
	}

	private void initSpicesComments() {

		Comment c1 = new Comment(emy(), "It misses spices, let's add ketchup");
		Comment c2 = new Comment(raj(), "What ? You stupid ! It's not a spice !");
		Comment c3 = new Comment(emy(), "But there is spicy vinegar");
		Comment c4 = new Comment(bernie(), "Vinegar is not a spice, it's a fruit");
		Comment c5 = new Comment(emy(), "A liquid fruit ? Doesn't make sense !");
		Comment c6 = new Comment(sheldon(),
				"And it is a bit <strong>violent</strong> <script type='text/javascript'>alert('you are fired!')</script>");

		spices.addComments(c1, c2, c3, c4, c5, c6);

		em.persist(c1);
		em.persist(c2);
		em.persist(c3);
		em.persist(c4);
		em.persist(c5);
		em.persist(c6);
	}

	public List<Comment> getTrollComments() {
		return troll.getComments();
	}

	public int getTotalCommentsCount() {
		int count = 0;
		for (Topic s : this.getTopics()) {
			count += s.getComments().size();
		}
		return count;
	}

	// obviously not very interesting
	public Map<Comment, User> getUserByComment() {
		Map<Comment, User> map = new HashMap<>();
		for (Comment c : this.getComments()) {
			map.put(c, c.getUser());
		}
		return map;
	}

	// more interesting
	public Map<User, List<Comment>> getCommentsByUser() {

		Map<User, List<Comment>> map = new HashMap<User, List<Comment>>();
		for (Comment c : this.getComments()) {
			// can be null, no problem
			User u = c.getUser();
			if (!map.containsKey(u)) {
				List<Comment> comments = new ArrayList<>();
				map.put(u, comments);
			}

			// add the comment to the list
			List<Comment> comments = map.get(u);
			comments.add(c);
		}
		return map;
	}

	public void fillMany(int size) {

		// int size = 24;
		int userSize = size;
		int topicSize = userSize * 3;
		int commentSize = userSize * 12;
		int tagSize = userSize;

		logger.info("Creating " + userSize + " random users");
		for (int i = 0; i < userSize; i++) {
			User u = new User();
			u.setEmail("user" + i + "@fora.com");
			u.setName("John Doe - " + i);
			em.persist(u);
		}

		// creating tags
		logger.info("Creating " + tagSize + " random tags");
		for (long i = 4; i < tagSize + 4; i++) {
			Tag tag = new Tag();

			tag.setName("Tag " + i);
			em.persist(tag);
		}

		// create Topics
		logger.info("Creating " + topicSize + " random topics");
		for (long i = 4; i < topicSize + 4; i++) {
			Topic t = new Topic();
			t.setTitle("Topic " + i);
			User u = getRandomItem(User.class, this.getUsers());
			t.setUser(u);
			em.persist(t);

			// adding random tags to the Topic
			if (getRandomTrue(70)) {
				Tag tag = getRandomItem(Tag.class, this.getTags());
				t.getTags().add(tag);
			}

			if (getRandomTrue(50)) {
				Tag tag = getRandomItem(Tag.class, this.getTags());
				t.getTags().add(tag);
			}

			if (getRandomTrue(20)) {
				Tag tag = getRandomItem(Tag.class, this.getTags());
				t.getTags().add(tag);
			}
		}

		// Create comments
		logger.info("Creating " + commentSize + " random comments");
		for (int i = 0; i < commentSize; i++) {
			Comment c = new Comment();
			c.setUser(getRandomItem(User.class, this.getUsers()));
			c.setContent("My comment says " + i);
			Topic t = getRandomItem(Topic.class, this.getTopics());
			t.getComments().add(c);
			em.persist(c);

			// adding tags
			if (getRandomTrue(20)) {
				Tag tag = getRandomItem(Tag.class, this.getTags());
				c.getTags().add(tag);
			}

			if (getRandomTrue(5)) {
				Tag tag = getRandomItem(Tag.class, this.getTags());
				c.getTags().add(tag);
			}

		}

	}

	public <T> T getRandomItem(Class<T> clazz, List<T> list) {

		int length = list.size();

		try {
			int index = new Random().nextInt(length);
			return list.get(index);
		} catch (RuntimeException e) {
			System.out.println("length is " + length);
			throw e;
		}

	}

	public boolean getRandomTrue(int percent) {
		float f = ((float) percent) / 100;
		return Math.random() < f;
	}

}