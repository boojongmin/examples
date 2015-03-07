package com.school.bank_java.command;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school.bank_java.login.LoginManager;
import com.school.bank_java.service.AccountService;
import com.school.bank_java.vo.AccountVo;
import com.school.bank_java.vo.UserVo;

public class AccountCommand implements Command<AccountService> {
    Logger logger = LoggerFactory.getLogger(AccountCommand.class);
	
	@Override
	public void run(AccountService service) {
		UserVo vo = LoginManager.loginMap.get("UserVo");
		StringBuffer sb = new StringBuffer();
		sb.append("===============================     \n");
		sb.append("   1: 계좌 생성         \n");
		sb.append("   2: 내 계좌 보기           \n");
		sb.append("   3: 입금                     \n");
		sb.append("   4: 출금                    \n");
		sb.append("   5: 이체                    \n");
		sb.append("   6: 계좌삭제                    \n");
		sb.append("===============================     \n");
		System.out.println(sb.toString());
		
		Scanner scanner = new Scanner(System.in);
		if(scanner.hasNextLine()){
			String command = scanner.nextLine();
			try{
              if("1".equals(command)){
            	  boolean result = service.createAccount(vo.getUid());
                  if(result){ System.out.println("계좌가 생성되었습니다."); }else{ System.out.println("계좌생성에 실패했습니다."); }
              }else if("2".equals(command)){
                  List<AccountVo> list = service.viewMyAccount(vo.getUid());
                  list.forEach( (x) -> System.out.println(
                      String.format(">>> 계좌번호 : %s \t 잔액 : %d", x.getAccountNumber(), x.getAmount())	
                  ));
              }else if("3".equals(command)){
                  System.out.println("입금할 계좌번호를 입력해 주세요");
                  String accountNumber = scanner.nextLine();
                  System.out.println("입금할 금액을 입력해 주세요");
                  String amount = scanner.nextLine();
                  boolean result = service.deposit(vo.getUid(), accountNumber, Integer.parseInt(amount));
                  if(result){ System.out.println("입금되었습니다."); }else{ System.out.println("입금에 실패했습니다."); }
              }else if("4".equals(command)){
                  System.out.println("출금할 계좌번호를 입력해 주세요");
                  String accountNumber = scanner.nextLine();
                  System.out.println("출금할 금액을 입력해 주세요");
                  String amount = scanner.nextLine();
                  boolean result = service.withdrawal(vo.getUid(), accountNumber, Integer.parseInt(amount));
                  if(result){ System.out.println("출금되었습니다."); }else{ System.out.println("출금에 실패했습니다."); }
              }else if("5".equals(command)){
                  System.out.println("이체할 본인의 계좌번호를 입력해 주세요");
                  String myAccountNumber = scanner.nextLine();
                  System.out.println("이체될 상대 계좌번호를 입력해 주세요");
                  String otherAccountNumber = scanner.nextLine();
                  System.out.println("이체할 금액을 입력해 주세요");
                  String amount = scanner.nextLine();
                  boolean result = service.transfer(vo.getUid(), myAccountNumber, otherAccountNumber, Integer.parseInt(amount));
                  if(result){ System.out.println("이체되었습니다."); }else{ System.out.println("이체에 실패했습니다."); }
              }
			}catch(Exception e){
				logger.error("[오류발생]\n"+ e.getMessage());
			}
		}
	}
}
