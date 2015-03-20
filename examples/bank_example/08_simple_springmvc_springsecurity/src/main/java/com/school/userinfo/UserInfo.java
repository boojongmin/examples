package com.school.userinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfo {
	public static Map userInfo = new HashMap();
	static{
		userInfo.put("userId", "boojongmin");
		userInfo.put("name", "부종민");
		userInfo.put("password", "81dc9bdb52d04dc20036dbd8313ed055");
		List<String> roleList = new ArrayList();
		roleList.add("ROLE_USER");
		roleList.add("ROLE_ADMIN");
		userInfo.put("role", roleList);
	}
}
