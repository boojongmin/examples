package step03;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class CommonDao<T> {
	public static SessionFactory FACTORY;
	static{
		FACTORY = new Configuration().configure("step03/hibernate.cfg.xml").buildSessionFactory();
	}
	
	public SessionFactory factory;
	private Class clazz;
	private String objName; //hql -> from StudentVo
	
	public CommonDao(Class<?> clazz){
		this.clazz = clazz;
		this.factory = FACTORY; 
		this.objName = clazz.getSimpleName();
	}
	//select by uid
	public T selectById(long id){
		Session session = factory.openSession();
//		T vo = (T) session.get(clazz, id);
		
		Criteria criteria = session.createCriteria(clazz);
		criteria.add(Restrictions.eq("uid", id));
		T vo = (T) criteria.uniqueResult();
		
		session.close();
		return vo;
	}	
	//select by keyword
	public List<T> selectByKeyword(String propery, String keyword){
		Session session = factory.openSession()	;
		
		Criteria criteria = session.createCriteria(clazz);
		criteria.add(Restrictions.eq(propery, keyword));
		List<T> list = (List<T>) criteria.list();
		
		session.close();
		return list;
	}
	
	//insert
	public void insertVo(T vo){
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(vo);
		tx.commit();
		session.close();
	}
	
	//deleteVo
	public void deleteVo(T vo){
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(vo);
		tx.commit();
		session.close();
	}
	//deleteAll
	public void deleteAll(){
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String query = String.format("delete from %s ", objName);
		Query createQuery = session.createQuery(query);
		createQuery.executeUpdate();
		tx.commit();
		session.close();
	}
	
	//updateVo
	public void updateVo(T vo){
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(vo);
		tx.commit();
		session.close();
	}
	
	//listAll
	public List<T> listAll(){
		Session session = factory.openSession()	;
		List<T> list = session
						.createCriteria(clazz)
						.list();
		session.close();
		return list;
	}
	
	
	//count
	public int count(){
		Session session = factory.openSession();
		//long row = (long)session.createQuery("select count(*)  from " + objName).uniqueResult();
		long row = (long)session
						.createCriteria(clazz)
						.setProjection(Projections.rowCount())
						.uniqueResult();
		return (int) row;
	}
	
	//list paging
	public List<T> getPaginList(int pageNumber, int pageSize){
		
		Session session = factory.openSession();
//		String query = "from " + objName + " order by uid asc"; 
//		Query q = session.createQuery(query);
//		q.setFirstResult((pageNumber -1) * pageSize);
//		q.setMaxResults(pageSize);
//		List<T> list = q.list();
		List<T> list = session
						.createCriteria(clazz)
						.addOrder(Order.asc("uid"))
						.setFirstResult(((pageNumber -1) * pageSize) + 1)
						.setMaxResults(pageSize)
						.list();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
