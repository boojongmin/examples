package com.school.bank_java.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.school.bank_java.dao.AccountDao;
import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;
import com.school.bank_java.vo.UserVo;
@Transactional
public class HAccountDao implements AccountDao {


	private SessionFactory sessionFactory;

	public HAccountDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public AccountVo selectAccountVoByUid(int uid) {
		Session session = sessionFactory.getCurrentSession();
		return (AccountVo) session.get(AccountVo.class, uid);
	}

	@Override
	public List<AccountDetailVo> selectAcocuntDetailByUid(int uid) {
		Session session = sessionFactory.getCurrentSession();
		List<AccountDetailVo> list = session.createCriteria(AccountDetailVo.class).add(Restrictions.eq("accountUid", uid)).list();
		return list;
	}

	@Override
	public int insertAccountDetail(AccountDetailVo vo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(vo);
		return 1;
	}

	@Override
	public AccountDetailVo selectAccountDetailByUid(int uid) {
		Session session = sessionFactory.getCurrentSession();
		return (AccountDetailVo) session.get(AccountDetailVo.class, uid);
	}

	@Override
	public List<AccountDetailVo> selectAcocuntDetailListByAccountUid( int accountUid) {
		Session session = sessionFactory.getCurrentSession();
		List<AccountDetailVo> list = session.createCriteria(AccountDetailVo.class).add(Restrictions.eq("accountVo.uid", accountUid)).list();
		return list;
	}

	@Override
	public int insertAccount(AccountVo vo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(vo);
		return 1;
	}

	@Override
	public int selectAccountCountByUserUid(int useruid) {
		Session session = sessionFactory.getCurrentSession();
		int count = ((Long)session.createCriteria(AccountVo.class).add(Restrictions.eq("userVo.uid", useruid)).setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return count;
	}

	@Override
	public List<AccountVo> selectAccountList(int user_uid) {
		Session session = sessionFactory.getCurrentSession();
		List list2 = session.createCriteria(UserVo.class).list();
		UserVo vo = (UserVo) session.createCriteria(UserVo.class).add(Restrictions.eq("uid", user_uid)).uniqueResult();
		List<AccountVo> list =null;
		if(vo != null){
			list =  vo.getAccountList();
		}
		return 	list;
	}

	@Override
	public int updateAccount(AccountVo accountVo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(accountVo);
		return 1;
	}

	@Override
	public AccountVo selectAccountVoByAccountNumber(String accountNumber) {
		Session session = sessionFactory.getCurrentSession();
		AccountVo vo = (AccountVo) session.createCriteria(AccountVo.class).add(Restrictions.eq("accountNumber", accountNumber)).uniqueResult();
		return vo;
	}

}
