package com.school.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.school.userinfo.UserInfo;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		String userId = (String)UserInfo.userInfo.get("userId");
		String password = (String)UserInfo.userInfo.get("password");
		List<String> roleList = (List<String>)UserInfo.userInfo.get("role");
		List<GrantedAuthority> authorities = buildUserAuthority(roleList);

		return buildUserForAuthentication(userId, password, authorities);

	}

	private User buildUserForAuthentication(String userId, String password, List<GrantedAuthority> authorities) {
		return new User(userId, password, true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<String> userList) {

		List<GrantedAuthority> setAuths = new ArrayList<GrantedAuthority>();
		for (String userRole : userList) {
			setAuths.add(new SimpleGrantedAuthority(userRole));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
	


}
