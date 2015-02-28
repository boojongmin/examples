package com.school.bank_java.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.school.bank_java.vo.AccountDetailVo;
import com.school.bank_java.vo.AccountVo;

public class AccountDao {
	private String id_prefix = "com.school.bank_java.mybatis.mapper.";
	private SqlSession session;
	
	public AccountDao(SqlSession session){
		this.session = session;
	}

//	public List<String> selectAccount_numbers(int user_uid) {
//		return session.selectList(id_prefix + "selectAccount_numbers", user_uid);
//	}

	public AccountVo selectAccountVoByUid(int uid) {
		return (AccountVo)session.selectOne(id_prefix + "selectAccountVoByUid", uid);
	}

	public List<AccountDetailVo> selectAcocuntDetailByUid(int uid) {
		return session.selectList(id_prefix + "selectAcocuntDetailByUid", uid); 
	}

	public int insertAccountDetail(AccountDetailVo vo) {
		return session.insert(id_prefix+ "insertAccountDetail", vo);
	}

	public AccountDetailVo selectAccountDetailByUid(int uid) {
		return session.selectOne(id_prefix + "selectAccountDetailByUid", uid);
	}

	public List<AccountDetailVo> selectAcocuntDetailListByAccountUid(int accountUid) {
		return session.selectList(id_prefix + "selectAcocuntDetailListByAccountUid", accountUid);
	}

	public int insertAccount(AccountVo vo) {
		return session.insert(id_prefix+ "insertAccount", vo);
	}

	public int selectAccountCountByUserUid(int useruid) {
		return session.selectOne(id_prefix+"selectAccountCountByUserUid", useruid);
	}

	public List<AccountVo> selectAccountList(int user_uid) {
		return session.selectList(id_prefix + "selectAccountList", user_uid);
	}

	public int updateAccount(AccountVo accountVo) {
		return session.update(id_prefix + "updateAccount", accountVo);
	}

	public AccountVo selectAccountVoByAccountNumber(String accountNumber) {
		return session.selectOne(id_prefix + "selectAccountVoByAccountNumber", accountNumber); 
	}


}
