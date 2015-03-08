package step02;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CommonDao<T> {
	public static SessionFactory FACTORY;
	static{
		FACTORY = new Configuration().configure("step02/hibernate.cfg.xml").buildSessionFactory();
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
		T vo = (T) session.get(clazz, id);
		session.close();
		return vo;
	}	
	//select by keyword
	public List<T> selectByKeyword(String propery, String keyword){
		Session session = factory.openSession()	;
		String query = "from " + objName 
				+ String.format(" where %s = '%s'", propery, keyword);
		List<T> list = session.createQuery(query).list();
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
		String query = "from " + objName; 
		List<T> list = session.createQuery(query).list();
		session.close();
		return list;
	}
	
	
	//count
	
	//list paging
	
}
