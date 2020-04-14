package com.mindor.util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtils
		{
		private static SessionFactory sessionFactory;
		
		public static SessionFactory getSessionFactory()
		{
		return sessionFactory;
		}
		
		public HibernateUtils(){}
		
		static
		{
		Configuration cfg = new Configuration();
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
		}
		
		public static Session getSession()
		{
		return sessionFactory.openSession();
		}
		
		public static void main(String[] args)
		{
		sessionFactory = HibernateUtils.getSessionFactory();
		System.out.println(sessionFactory);
		}

}