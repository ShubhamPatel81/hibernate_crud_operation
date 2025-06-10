package com.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import com.hibernate.entities.Students;
import com.hibernate.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


public class StudentService {
	
	private SessionFactory factory = HibernateUtil.getSessionFactory();
	
	
	// save
	public void saveStudent(Students students) {
		try(Session session = factory.openSession()) {
			 Transaction transaction= session.beginTransaction();
			 session.persist(students);
			 transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//get by id 
	public Students getById(long studentId) {
		
		try(Session session = factory.openSession()) {
			
		Students students=	session.get(Students.class, studentId);
			return students;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//update 
	public Students update(long studentId, Students students) {
		
		try(Session session = factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Students oldStudents= session.get(Students.class, studentId);
			if (oldStudents != null) {
				oldStudents.setName(students.getName());
				oldStudents.setAbout(students.getAbout());
				oldStudents.setActive(students.isActive());
				oldStudents.setCollege(students.getCollege());
				oldStudents.setPhone(students.getPhone());
				oldStudents =session.merge(oldStudents);
			}		
			transaction.commit();
			return oldStudents;
		} 
	}
	
	//delete 
	public void deleteStudents(long studentId) {
		
		try(Session session= factory.openSession()) {
			Transaction transaction= session.getTransaction();
			Students students = session.get(Students.class, studentId);
			if(students != null ) {
				session.remove(students);
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	//HQL(Hibernate Query Language)[JPA]
	
	//database independent query 
	
	// get all the students using HQL
	
	public  List<Students> getAllStudentsHQL() {
		
		try (Session session = factory.openSession()){
			String queryHql= "FROM Students";//query created
			Query<Students> query = session.createNamedQuery(queryHql, Students.class);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//get Students by name
	public Students getStudentsByName(String name) {
		try(Session session = factory.openSession()){
			String getByNameHql= "FROM Students WHERE name=:studentName";
			Query<Students> query=session.createQuery(getByNameHql, Students.class);
			query.setParameter("studentName", name);
			return query.uniqueResult();
		}
	}
	
	//criteria api:
	//get all Students in same college
	public List<Students> getStudentsByCriteria(String college) {
		try (Session session = factory.openSession()){
		   HibernateCriteriaBuilder criteriaBuilder= session.getCriteriaBuilder();
		   CriteriaQuery<Students> query= criteriaBuilder.createQuery(Students.class);
		   Root<Students> root= query.from(Students.class);
		   query.select(root).where( criteriaBuilder.equal(root.get("college"), college));
		   
		   Query<Students> query2= session.createQuery(query);
		   return query2.getResultList();
		   
		} 
	}
	
	
	// HQL using pagination
	public List<Students> getStudentWithPagintaion(int pageNo, int pageSize) {
		try (Session session = factory.openSession()){
			String pageQuery="FROM Students";
			
			Query<Students> query= session.createQuery(pageQuery,Students.class);
			query.setFirstResult((pageNo-1)*pageSize);
			
			query.setMaxResults(pageSize);
			return query.list();
			
		}
	}
	
}
