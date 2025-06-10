package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.entities.Certificate;
import com.hibernate.entities.Students;
import com.hibernate.util.HibernateUtil;
public class App 
{
    public static void main( String[] args )
	{
		System.out.println( "Hello World!" );
		System.out.println("We are going to learn hibernate in this project !!!");
		
		//crate student objects 
		Students student= new Students();
		student.setName("Shubh ");
		student.setCollege("Banglore College");
		student.setEmail("shubh@gmail.com");
		student.setPhone("9808009090");
		student.setAbout("I am the CSE student of this college banglore");
		student.setActive(true);
		
		
		Certificate certificate= new Certificate();
		certificate.setTitle("Java Certification");
		certificate.setLink("com.java");
		certificate.setStudents(student);
		
		Certificate certificate1= new Certificate();
		certificate1.setTitle("Python Certification");
		certificate1.setLink("com.python");
		certificate1.setStudents(student);
		
		
		student.getCertificates().add(certificate);
		student.getCertificates().add(certificate1);
		
		
	SessionFactory factory= HibernateUtil.getSessionFactory();
	System.out.println( "Hibenate Session Factory : "+factory );
	
	 Session session = factory.openSession();
	  Transaction transaction = null;
	  try {
		    transaction = session.beginTransaction();
		    session.persist(student);
		    transaction.commit(); // only commit if everything goes well
		    System.out.println("Student saved successfully !!!");
		} catch (Exception e) {
		    if (transaction != null) {
		        transaction.rollback();
		    }
		    e.printStackTrace();
		} finally {
		    session.close();
		}

	}
}