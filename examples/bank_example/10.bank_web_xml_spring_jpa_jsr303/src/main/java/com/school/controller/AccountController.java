package com.school.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.school.domain.Account;
import com.school.domain.User;
import com.school.exception.BankException;
import com.school.repository.AccountRepository;
import com.school.service.AccountService;
import com.school.vo.ResultVo;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService service;

	@Autowired
	AccountRepository accRepo;


	@RequestMapping("/MyAccount")
	public String MyAccount(HttpSession session, Model model){
		User user = (User)session.getAttribute("User");
		Set<Account> accounts = service.viewMyAccount(user.getId());
		model.addAttribute("accounts", accounts);
		return "/account/MyAccount";
	}

	@RequestMapping("/createAccount")
	public String createAccount(HttpSession session) {
		User user = (User)session.getAttribute("User");
		service.createAccount(user.getId());
		return "redirect:/school/account/MyAccount";
	}


	@ResponseBody
    @RequestMapping(value="/deposit")
//	public String deposit(@RequestBody Account account, HttpSession session) {
	public ResultVo deposit(@ModelAttribute Account account, HttpSession session){
		User user = (User)session.getAttribute("User");
		try{
          service.deposit(user.getId(), account.getAccountNumber(), account.getAmount());
		}catch(BankException e){
			return new ResultVo(false, e.getMessage());
		}

		return new ResultVo(true, "입금이 완료되었습니다.");
	}

    @RequestMapping("/withdrawal")
   	public @ResponseBody ResultVo withdrawal(@ModelAttribute Account account, HttpSession session) {
   		User user = (User)session.getAttribute("User");
		try{
          service.withdrawal(user.getId(), account.getAccountNumber(), account.getAmount());
		}catch(BankException e){
			return new ResultVo(false, e.getMessage());
		}
		return new ResultVo(true, "입금이 완료되었습니다.");
   	}

    @RequestMapping(value="/transfer", produces="text/html; charset=utf-8")
    @ResponseBody
   	public String transfer(HttpSession session, @RequestParam("myAccountNumber") String myAccountNumber, @RequestParam("otherAccountNumber") String otherAccountNumber, @RequestParam("amount") int amount) {
    	String redirectScript = "location.href = '/school/account/MyAccount';";
    	String resultMsg = String.format("alert('%d 금액이 이체되었습니다.(%s -> %s)');", amount, myAccountNumber, otherAccountNumber) ;
    	User user = (User)session.getAttribute("User");
    	try{
    	service.transfer(user.getId(), myAccountNumber, otherAccountNumber, amount);
    	}catch(BankException e){
    		resultMsg = String.format("alert('%s');", e.getMessage()) ;
    	}
    	return  String.format("<script>%s %s</script>", resultMsg, redirectScript);
    }

    @RequestMapping("/transfer2")
   	public ResponseEntity<String> transfer2(HttpSession session,  @RequestParam("myAccountNumber") String myAccountNumber, @RequestParam("otherAccountNumber") String otherAccountNumber, @RequestParam("amount") int amount) throws URISyntaxException {
    	String redirectScript = "location.href = '/school/account/MyAccount';";
    	String resultScript = String.format("alert('%d 금액이 이체되었습니다.(%s -> %s)');", amount, myAccountNumber, otherAccountNumber) ;
    	User user = (User)session.getAttribute("User");
    	try{
    	service.transfer(user.getId(), myAccountNumber, otherAccountNumber, amount);
    	}catch(BankException e){
    		resultScript = String.format("alert('%s');", e.getMessage()) ;
    	}

        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.setLocation(new URI("/school/account/MyAccount"));
        responseHeaders.set("Content-Type", "text/html; charset=utf-8");

        resultScript = String.format("<script>%s %s</script>", resultScript, redirectScript);
//        return new ResponseEntity<String>(resultScript, responseHeaders, HttpStatus.FOUND);
        return new ResponseEntity<String>(resultScript, responseHeaders, HttpStatus.OK);
//    	return  String.format("<script>%s %s</script>", resultMsg, redirectScript);
    }

    @RequestMapping("/removeAccount/{accountNumber}")
   	public String removeAccount(@PathVariable("accountNumber") String accountNumber, HttpSession session ) {
    	User user = (User)session.getAttribute("User");
    	service.removeAccount(user.getId(), accountNumber);
   		return "redirect:/school/account/MyAccount";
   	}


    @RequestMapping("/withdrawalValidator")
    public String withdrawalValidator(@ModelAttribute("account") Account account){
    	return "/account/withdrawalValidator";
    }

    @RequestMapping("withdrawalValidatorProc")
    public String withdrawalValidatorProc(@Valid Account account, BindingResult bindingResult, Model model, HttpSession session) {
    	User user = (User)session.getAttribute("User");
        Account resultAccount = accRepo.findByAccountNumber(account.getAccountNumber());
        if(resultAccount == null){
        	bindingResult.rejectValue("accountNumber", "error.account", " 계좌번호가  없습니다.");
        }else if((long)resultAccount.getUser().getId() != (long)user.getId()) bindingResult.rejectValue("accountNumber", "error.account", "본인의 계좌만 출금이 가능합니다.");

        if (bindingResult.hasErrors()) {
            model.addAttribute("account", account);
            return "/account/withdrawalValidator";
        }

   		return "redirect:/school/account/MyAccount";
    }

//    @ExceptionHandler(BankException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    String handleException(BankException ex) {
//    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
//        return ex.getMessage()+ "---------------";
//    }

}
