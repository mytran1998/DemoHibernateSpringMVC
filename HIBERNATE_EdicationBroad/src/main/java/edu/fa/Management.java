package edu.fa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import edu.fa.model.Address;
import edu.fa.model.Course;
import edu.fa.model.Fresher;
import edu.fa.model.Group;
import edu.fa.model.Syllabus;

public class Management {

	public static void main(String[] args) {
		
		//createCourseSyllabues(); 
		//getCourseSyllabues(1);
		//createFresherAndAddress(); 
		//createFresherAndCourse();
		//createFresherAndGroup();
		createGroup();
		testSecondLevelCache();
		ConnectionUtil.getSessionFactory().close();
	}
	
	private static void testSecondLevelCache() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group g1 = (Group)session.get(Group.class, 1);
			System.out.println(g1);
			session.getTransaction().commit();
			session.close();
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			g1 = null;
			g1 = (Group)session.get(Group.class, 1);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void useNamedQuery() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.getNamedQuery(Constants.GROUP_BY_NAME);
			query.setString("name", "Java");
			
			System.out.println(query.list());
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void useCriteria() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Criteria groups = session.createCriteria(Group.class);
			// Dieu kien
			groups.add(Restrictions.eq("id", 1));
			
			/*
			 * // Dieu kien Or groups.add(Restrictions.or(Restrictions.eq("id", 1) ,
			 * Restrictions.like("name", "Java")));
			 * 
			 * // Hoac cach khac SimpleExpression eq = Restrictions.eq("id", 1);
			 * SimpleExpression like = Restrictions.like("name", "Java"); LogicalExpression
			 * logic = Restrictions.or(eq, like); groups.add(logic);
			 */
			
			System.out.println(groups.list());
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void deleteGroupUsingHQL() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "DELETE from Group WHERE id = :id";
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 1);
			
			int result = query.executeUpdate();
			System.out.println("Number record delete: " + result);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void updateGroupUsingHQL() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "UPDATE Group SET name = :name WHERE id = :id";
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 1);
			query.setParameter("name", "Java Update");
			
			int result = query.executeUpdate();
			System.out.println("Number record update: " + result);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void queryGroupUsingHQL() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "FROM Group WHERE id = :id and name like :name"; // Condition binding
			Query query = session.createQuery(queryStr);
			//query.setInteger(0, 1);
			
			query.setParameter("id", 1);
			query.setParameter("name", "Java");
			
			List<Group> groups = (List<Group>)query.list();
			System.out.println(groups);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void getGroup() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Group group = (Group)session.get(Group.class, 1);
			System.out.println(group);
			/*
			 * group.setName("New Java"); session.update(group); session.delete(group);
			 * session.getTransaction().commit();
			 */
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void createGroup() {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group g1 = new Group("Java");
			Group g2 = new Group("Python");
			session.save(g1); 
			session.save(g2);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void createFresherAndGroup() {
		Fresher f1 = new Fresher();
		Fresher f2 = new Fresher();
		Group g1 = new Group("Nhom 1");
		Group g2 = new Group("Nhom 2");
		
		Set<Fresher> freshers = new HashSet<Fresher>();
		freshers.add(f1);
		freshers.add(f2);
		Set<Group> groups = new HashSet<Group>();
		groups.add(g1);
		groups.add(g2);
		f1.setName("AAA");
		f2.setName("BBB");
		
		f1.setGroups(groups);
		f2.setGroups(groups);
		g1.setFreshers(freshers);
		g2.setFreshers(freshers);
		
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(f1);
			session.save(f2);
			session.save(g1);
			session.save(g2);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	private static void createFresherAndCourse() {
		List<Course> courses = new ArrayList<Course>();
		courses.add(new Course("Java"));
		courses.add(new Course("Hibernate"));
		
		Fresher fresher = new Fresher("Nam My", courses);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			for(Course course : courses) {
				session.save(course);
			}
			
			session.save(fresher);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	private static void createFresherAndAddress() {
		Address address = new Address("Duy Tan", "Ha Noi");
		Fresher fresher = new Fresher("Nam My", address);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(address);
			session.save(fresher);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void getCourseSyllabues(int id) {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Course course = (Course) session.get(Course.class, id);
			System.out.println("Name: " + course.getName());
			System.out.println(course.getLstSyllabus());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void createCourseSyllabues() {
		List<Syllabus> lstSyllabus = new ArrayList<Syllabus>();
		lstSyllabus.add(new Syllabus("Hibernate online content", 30));
		lstSyllabus.add(new Syllabus("Hibernate offline content", 50));
		
		Course course = new Course("Hibernate", new Date(), lstSyllabus);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(course);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
