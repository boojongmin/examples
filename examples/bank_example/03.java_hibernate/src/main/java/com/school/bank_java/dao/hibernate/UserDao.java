package com.school.bank_java.dao.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.school.bank_java.dao.IUserDao;
import com.school.bank_java.hibernate.CommonDao;
import com.school.bank_java.vo.UserVo;

public class UserDao implements IUserDao {
	private SessionFactory factory;
	private CommonDao<UserVo> dao;

	public UserDao(){}
	public UserDao(SessionFactory factory){
		this.factory = factory;
        this.dao = new CommonDao(factory, UserVo.class);
	}


	@Override
	public UserVo selectUserVoByUserid(String userId) {
		Session session = dao.getSession();
		session.beginTransaction();
		UserVo vo = (UserVo) session.createCriteria(UserVo.class).add(Restrictions.eq("userId", userId)).uniqueResult();
		session.getTransaction().commit();
		return vo;
	}

	@Override
	public int insertUser(UserVo userVo) {
		dao.insert(userVo);
		return 1;
	}

}
