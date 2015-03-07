package com.school.bank_java.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

public class CommonDao<T> {


	private SessionFactory factory;
	private Class clazz;
	private String tableName;

	public <T> CommonDao(SessionFactory factory, Class<?> clazz) {
		this.factory = 	factory;
		this.clazz = clazz;
		this.tableName = clazz.getSimpleName();
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	public int deleteAllSetTable() {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		int result = session.createQuery("delete from " + tableName).executeUpdate();
		tx.commit();
		return result;
	}



	public long count() {
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		long result = (Long) session.createCriteria(clazz).setProjection(Projections.rowCount()).uniqueResult();
		tx.commit();
		return result;
	}


	private static  int numPerPage = 10;

	public List<?> getPagingList(int requestPage){
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = (Query) session.createQuery("from "+tableName +" order by id asc");

		query.setFirstResult((requestPage-1)*numPerPage);
		query.setMaxResults(numPerPage);

		List<?> members = query.list();
		tx.commit();
		return members;
	}




	public List<?> selectList() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from "+tableName);

		List<?> list = query.list();
		session.getTransaction().commit();
		return list;
	}

	public void delete(T member) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(member);
		session.getTransaction().commit();
	}

	public void update(T selectedMember) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.update(selectedMember);
		session.getTransaction().commit();
	}

	public T selectById(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		T member = (T) session.get(clazz, id);
		session.getTransaction().commit();
		return member;
	}

	public void insert(T member) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(member);
		session.getTransaction().commit();
	}

}
