package com.school.command;

import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school.login.LoginManager;
import com.school.service.AccountService;
import com.school.vo.Account;
import com.school.vo.User;

public class AccountCommand implements Command<AccountService> {
    Logger logger = LoggerFactory.getLogger(AccountCommand.class);

	@Override
	public void run(AccountService service) {
		User user = LoginManager.loginMap.get("User");
		StringBuffer sb = new StringBuffer();
		sb.append("===============================     \n");
		sb.append("   1: 계좌 생성         \n");
		sb.append("   2: 내 계좌 보기           \n");
		sb.append("   3: 입금                     \n");
		sb.append("   4: 출금                    \n");
		sb.append("   5: 이체                    \n");
		sb.append("   6: 계좌삭제                    \n");
		sb.append("   7: 로그아웃                    \n");
		sb.append("===============================     \n");
		System.out.println(sb.toString());

		Scanner scanner = new Scanner(System.in);
		if(scanner.hasNextLine()){
			String command = scanner.nextLine();
			try{
              if("1".equals(command)){
            	  service.createAccount(user.getId());
                  System.out.println("계좌가 생성되었습니다.");
              }else if("2".equals(command)){
                  Set<Account> list = service.viewMyAccount(user.getId());
                  list.forEach( (x) -> System.out.println(
                      String.format(">>> 계좌번호 : %s \t 잔액 : %d", x.getAccountNumber(), x.getAmount())
                  ));
              }else if("3".equals(command)){
                  System.out.println("입금할 계좌번호를 입력해 주세요");
                  String accountNumber = scanner.nextLine();
                  System.out.println("입금할 금액을 입력해 주세요");
                  String amount = scanner.nextLine();
                  service.deposit(user.getId(), accountNumber, Integer.parseInt(amount));
                  System.out.println("입금되었습니다.");
              }else if("4".equals(command)){
                  System.out.println("출금할 계좌번호를 입력해 주세요");
                  String accountNumber = scanner.nextLine();
                  System.out.println("출금할 금액을 입력해 주세요");
                  String amount = scanner.nextLine();
                  service.withdrawal(user.getId(), accountNumber, Integer.parseInt(amount));
                  System.out.println("출금되었습니다.");
              }else if("5".equals(command)){
                  System.out.println("이체할 본인의 계좌번호를 입력해 주세요");
                  String myAccountNumber = scanner.nextLine();
                  System.out.println("이체될 상대 계좌번호를 입력해 주세요");
                  String otherAccountNumber = scanner.nextLine();
                  System.out.println("이체할 금액을 입력해 주세요");
                  String amount = scanner.nextLine();
                  service.transfer(user.getId(), myAccountNumber, otherAccountNumber, Integer.parseInt(amount));
                  System.out.println("이체되었습니다.");
              }else if("6".equals(command)){
                  System.out.println("삭제할 본인의 계좌번호를 입력해 주세요");
                  String myAccountNumber = scanner.nextLine();
                  service.removeAccount(myAccountNumber);
                  System.out.println("삭제되었습니다.");
              }else if("7".equals(command)){
            	  LoginManager.loginMap.clear();
            	  System.out.println("로그아웃되었습니다.");
              }
			}catch(Exception e){
				logger.error("[오류발생]\n"+ e.getMessage());
				throw e;
			}
		}
	}
}
