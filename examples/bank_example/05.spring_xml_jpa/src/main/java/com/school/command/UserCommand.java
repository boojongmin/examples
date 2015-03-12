package com.school.command;

import java.util.Scanner;

import com.school.login.LoginManager;
import com.school.service.UserService;
import com.school.vo.User;

public class UserCommand implements Command<UserService>{

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
					System.exit(0);
					break;
				default:
					User vo = service.doLogin(command);
					if(vo == null){
						System.out.println("등록된 아이디가 아닙니다.");
					}else{
						LoginManager.loginMap.put("User", vo);
						System.out.println("로그인이에 성공하였습니다.");
					}
					break;
			}
		}


	}
}
