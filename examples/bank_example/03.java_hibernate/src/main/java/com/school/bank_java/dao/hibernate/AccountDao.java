package com.school.bank_java.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.school.bank_java.dao.IAccountDao;
import com.school.bank_java.hibernate.CommonDao;
import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;
import com.school.bank_java.vo.UserVo;

public class AccountDao implements IAccountDao {


	private SessionFactory factory;
	private CommonDao<UserVo> userDao;
	private CommonDao<AccountVo> accountDao;
	private CommonDao<AccountDetailVo> accountDetailDao;

	public AccountDao(SessionFactory factory) {
		this.factory = factory;
		this.userDao = new CommonDao(factory, UserVo.class);
        this.accountDao = new CommonDao(factory, AccountVo.class);
        this.accountDetailDao = new CommonDao(factory, AccountDetailVo.class);
	}



	@Override
	public AccountVo selectAccountVoByUid(int uid) {
		return (AccountVo) accountDao.selectById(uid);
	}

	@Override
	public List<AccountDetailVo> selectAcocuntDetailByUid(int uid) {
		Session session = accountDetailDao.getSession();
		session.beginTransaction();
		List<AccountDetailVo> list = session.createCriteria(AccountDetailVo.class).add(Restrictions.eq("accountUid", uid)).list();
		session.getTransaction().commit();
		return list;
	}

	@Override
	public int insertAccountDetail(AccountDetailVo vo) {
		accountDetailDao.insert(vo);
		return 1;
	}

	@Override
	public AccountDetailVo selectAccountDetailByUid(int uid) {
		return (AccountDetailVo) accountDetailDao.selectById(uid);
	}

	@Override
	public List<AccountDetailVo> selectAcocuntDetailListByAccountUid( int accountUid) {
		Session session = accountDetailDao.getSession();
		session.beginTransaction();
		List<AccountDetailVo> list = session.createCriteria(AccountDetailVo.class).add(Restrictions.eq("accountVo.uid", accountUid)).list();
		session.getTransaction().commit();
		return list;
	}

	@Override
	public int insertAccount(AccountVo vo) {
		accountDao.insert(vo);
		return 1;
	}

	@Override
	public int selectAccountCountByUserUid(int useruid) {
		Session session = accountDao.getSession();
		session.beginTransaction();
		int count = ((Long)session.createCriteria(AccountVo.class).add(Restrictions.eq("userVo.uid", useruid)).setProjection(Projections.rowCount()).uniqueResult()).intValue();
		session.getTransaction().commit();
		return count;
	}

	@Override
	public List<AccountVo> selectAccountList(int user_uid) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		UserVo vo = (UserVo) session.createCriteria(UserVo.class).add(Restrictions.eq("uid", user_uid)).uniqueResult();
		List<AccountVo> list =null;
		if(vo != null){
			list =  vo.getAccountList();
		}
		session.getTransaction().commit();
		return 	list;
	}

	@Override
	public int updateAccount(AccountVo accountVo) {
		accountDao.update(accountVo);
		return 1;
	}

	@Override
	public AccountVo selectAccountVoByAccountNumber(String accountNumber) {
		Session session = accountDao.getSession();
		session.beginTransaction();
		AccountVo vo = (AccountVo) session.createCriteria(AccountVo.class).add(Restrictions.eq("accountNumber", accountNumber)).uniqueResult();
		session.getTransaction().commit();
		return vo;
	}

}
