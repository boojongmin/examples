package com.school.bank_java.command;

import java.util.Scanner;

import com.school.bank_java.login.LoginManager;
import com.school.bank_java.service.UserService;
import com.school.bank_java.vo.UserVo;

public class LoginCommand implements Command<UserService>{
	
	@Override
	public void run(UserService service) {
		System.out.println("커맨트를 입력해주세요");
		/*
		 *    ===============================
		 *     1: 로그인(아이디 입력)
		 *     2: 종료
		 *    =============================== 
		 */
		StringBuffer sb = new StringBuffer();
		sb.append("===============================     \n");
		sb.append("   로그인할 아이디를 입력해주세요          \n");
		sb.append("   'quit' =  종료                    \n");
		sb.append("===============================     \n");
		System.out.println(sb.toString());
		
		Scanner sc = new Scanner(System.in);
		if(sc.hasNextLine()){
			String command = sc.nextLine();
			
			switch(command){
				case "quit": 
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					UserVo vo = service.doLogin(command);
					if(vo == null){
						System.out.println("등록된 아이디가 아닙니다.");
					}else{
						LoginManager.loginMap.put("UserVo", vo);
						System.out.println("로그인이에 성공하였습니다.");
					}
					break;
			}
		}
		
		
	}
}
