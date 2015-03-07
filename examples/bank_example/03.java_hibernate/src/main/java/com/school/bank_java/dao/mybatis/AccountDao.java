package com.school.bank_java.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.school.bank_java.dao.IAccountDao;
import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;

public class AccountDao implements IAccountDao {
	private String id_prefix = "com.school.bank_java.mybatis.mapper.";
	private SqlSession session;

	public AccountDao(SqlSession session){
		this.session = session;
	}

//	public List<String> selectAccount_numbers(int user_uid) {
//		return session.selectList(id_prefix + "selectAccount_numbers", user_uid);
//	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IAccountDao#selectAccountVoByUid(int)
	 */
	@Override
	public AccountVo selectAccountVoByUid(int uid) {
		return (AccountVo)session.selectOne(id_prefix + "selectAccountVoByUid", uid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IAccountDao#selectAcocuntDetailByUid(int)
	 */
	@Override
	public List<AccountDetailVo> selectAcocuntDetailByUid(int uid) {
		return session.selectList(id_prefix + "selectAcocuntDetailByUid", uid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IAccountDao#insertAccountDetail(com.school.bank_java.vo.AccountDetailVo)
	 */
	@Override
	public int insertAccountDetail(AccountDetailVo vo) {
		return session.insert(id_prefix+ "insertAccountDetail", vo);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IAccountDao#selectAccountDetailByUid(int)
	 */
	@Override
	public AccountDetailVo selectAccountDetailByUid(int uid) {
		return session.selectOne(id_prefix + "selectAccountDetailByUid", uid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IAccountDao#selectAcocuntDetailListByAccountUid(int)
	 */
	@Override
	public List<AccountDetailVo> selectAcocuntDetailListByAccountUid(int accountUid) {
		return session.selectList(id_prefix + "selectAcocuntDetailListByAccountUid", accountUid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IAccountDao#insertAccount(com.school.bank_java.vo.AccountVo)
	 */
	@Override
	public int insertAccount(AccountVo vo) {
		return session.insert(id_prefix+ "insertAccount", vo);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IAccountDao#selectAccountCountByUserUid(int)
	 */
	@Override
	public int selectAccountCountByUserUid(int useruid) {
		return session.selectOne(id_prefix+"selectAccountCountByUserUid", useruid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IAccountDao#selectAccountList(int)
	 */
	@Override
	public List<AccountVo> selectAccountList(int user_uid) {
		return session.selectList(id_prefix + "selectAccountList", user_uid);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IAccountDao#updateAccount(com.school.bank_java.vo.AccountVo)
	 */
	@Override
	public int updateAccount(AccountVo accountVo) {
		return session.update(id_prefix + "updateAccount", accountVo);
	}

	/* (non-Javadoc)
	 * @see com.school.bank_java.dao.mybatis.IAccountDao#selectAccountVoByAccountNumber(java.lang.String)
	 */
	@Override
	public AccountVo selectAccountVoByAccountNumber(String accountNumber) {
		return session.selectOne(id_prefix + "selectAccountVoByAccountNumber", accountNumber);
	}


}
