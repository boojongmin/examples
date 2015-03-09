package com.school.bank_java.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.school.bank_java.dao.AccountDao;
import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;

public class MybatisAccountDao implements AccountDao {
	private String id_prefix = "com.school.bank_java.mybatis.mapper.";
	private SqlSession session;

	public MybatisAccountDao() { }
	public MybatisAccountDao(SqlSession session){
		this.session = session;
	}


	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.AccountDao#selectAccountVoByUid(int)
	 */
	@Override
	public AccountVo selectAccountVoByUid(int uid) {
		return (AccountVo)session.selectOne(id_prefix + "selectAccountVoByUid", uid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.AccountDao#selectAcocuntDetailByUid(int)
	 */
	@Override
	public List<AccountDetailVo> selectAcocuntDetailByUid(int uid) {
		return session.selectList(id_prefix + "selectAcocuntDetailByUid", uid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.AccountDao#insertAccountDetail(com.school.bank_java.vo.AccountDetailVo)
	 */
	@Override
	public int insertAccountDetail(AccountDetailVo vo) {
		return session.insert(id_prefix+ "insertAccountDetail", vo);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.AccountDao#selectAccountDetailByUid(int)
	 */
	@Override
	public AccountDetailVo selectAccountDetailByUid(int uid) {
		return session.selectOne(id_prefix + "selectAccountDetailByUid", uid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.AccountDao#selectAcocuntDetailListByAccountUid(int)
	 */
	@Override
	public List<AccountDetailVo> selectAcocuntDetailListByAccountUid(int accountUid) {
		return session.selectList(id_prefix + "selectAcocuntDetailListByAccountUid", accountUid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.AccountDao#insertAccount(com.school.bank_java.vo.AccountVo)
	 */
	@Override
	public int insertAccount(AccountVo vo) {
		return session.insert(id_prefix+ "insertAccount", vo);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.AccountDao#selectAccountCountByUserUid(int)
	 */
	@Override
	public int selectAccountCountByUserUid(int useruid) {
		return session.selectOne(id_prefix+"selectAccountCountByUserUid", useruid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.AccountDao#selectAccountList(int)
	 */
	@Override
	public List<AccountVo> selectAccountList(int user_uid) {
		return session.selectList(id_prefix + "selectAccountList", user_uid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.AccountDao#updateAccount(com.school.bank_java.vo.AccountVo)
	 */
	@Override
	public int updateAccount(AccountVo accountVo) {
		return session.update(id_prefix + "updateAccount", accountVo);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.AccountDao#selectAccountVoByAccountNumber(java.lang.String)
	 */
	@Override
	public AccountVo selectAccountVoByAccountNumber(String accountNumber) {
		return session.selectOne(id_prefix + "selectAccountVoByAccountNumber", accountNumber);
	}


}
