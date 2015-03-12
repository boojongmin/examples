package com.school.bank_java.dao.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.school.bank_java.dao.UserDao;
import com.school.bank_java.vo.UserVo;

@Transactional
public class HUserDao implements UserDao {
	private SessionFactory sessionFactory;
	private CommonDao<UserVo> dao;

	public HUserDao(){}
	public HUserDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
        this.dao = new CommonDao(sessionFactory, UserVo.class);
	}


	@Override
	public UserVo selectUserVoByUserid(String userId) {
//		Session session = dao.getSession();
//		session.beginTransaction();
//		UserVo vo = (UserVo) session.createCriteria(UserVo.class).add(Restrictions.eq("userId", userId)).uniqueResult();
//		session.getTransaction().commit();
//		return vo;

		Session session = dao.getSession();
		UserVo vo = (UserVo) session.createCriteria(UserVo.class).add(Restrictions.eq("userId", userId)).uniqueResult();
		return vo;


	}

	@Override
	public int insertUser(UserVo userVo) {
//		dao.insert(userVo);
//		return 1;
		Session session = dao.getSession();
		session.save(userVo);
		return 1;
	}

}
