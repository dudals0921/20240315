package com.myedumyselect.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myedumyselect.academy.service.AcademyLoginService;
import com.myedumyselect.academy.vo.AcademyLoginVo;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
@SessionAttributes("academyLogin")
public class AcademyLoginController {
    
	@Setter(onMethod_ = @Autowired)
    private AcademyLoginService academyLoginService;
	
	@GetMapping("/userAccount/login")
	public String loginForm() {
		//model.addAttribute("userAccountLoginDto", new UserAccountLoginDto());
		return "/academy/login";
	}
	
	
	// 로그인
	@PostMapping("/userAccount/login") 
	public String loginProcess(AcademyLoginVo login, Model model, RedirectAttributes ras, HttpSession session) {
		
		
		// POST 방식으로 /useraccount/login 엔드포인트에 대한 요청을 처리하는 메서드이다.
		// 요청에서 academyLoginVO 객체, Model 객체, RedirectAttributes 객체, HttpSession 객체를 받는다
		
		/*Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");
		Date bannedUntil = (Date) session.getAttribute("bannedUntil");*/
		
		// 세션에서 loginAttempts와 bannedUntil 속성을 가져온다. 결국 얘네도 변수 이름이다.
		// loginAttempts는 로그인 시도 횟수를, bannedUntil은 계정 잠금이 해제되는 시간을 나타낸다.
		// currentTiem는 현재시간을 나타내는 변수이다 . 밑에 System.currentTimeMillis()을 통해
		// 현재 시간을 밀리초 단위로 가져온다. 결론적으로 이 변수는 계정 잠금 해제 시간을 계산할때 사용한다.
		// lockoutTime 계정이 잠금되는 시간을 나타내는 변수이다 currentTime 와 똑같은 개념이다
		// ex) 1분은 60초 1초는 1000밀리초 그래서 10분을 밀리초 단위로 계산하면 10 * 60 * 1000 이렇게된다 10분 =
		// 600000 밀리초
		// currentTime + 600000 잠금이 풀리는 시간을 계산하면 이런식이다.

		// 즉시 잠금 해제
		// session.removeAttribute("bannedUntil");

		// 계정이 잠겨 있을 때
		/*if (bannedUntil != null && new Date().before(bannedUntil)) {
			long remainingTime = (bannedUntil.getTime() - new Date().getTime()) / (1000 * 60); // 잔여 시간(분)
			ras.addFlashAttribute("errorMsg", "계정이 잠겨 있습니다. 잠금 해제 시간까지 약 " + remainingTime + "분 남았습니다.");
			return "redirect:/useraccount/login";
		}*/
		
		// 계정이 잠겨 있고, 잠금 해제 시간이 현재 시간 이후인 경우를 검사한다.
		// 계정이 잠겨 있을 때 사용자에게 알림 메시지를 추가하고, 로그인 페이지로 리다이렉트한다.

		// 로그인 시도 횟수 카운트
		/*if (loginAttempts == null) {
			loginAttempts = 0;
		}*/		
		// 로그인 시도 횟수를 확인하고, 값이 없으면 0으로 초기화한다.
		
		AcademyLoginVo academyLogin = academyLoginService.loginProcess(login);
		
		if (academyLogin != null) {
			model.addAttribute("academyLogin", academyLogin);
			//session.removeAttribute("loginAttempts"); // 로그인이 성공한 경우, 모델에 로그인 정보를 추가하고, 세션에서 로그인 시도 횟수 속성을 제거한다.
		} else {
			/*
			loginAttempts++;
			session.setAttribute("loginAttempts", loginAttempts);
			 
			if (loginAttempts >= 5) {
				long currentTime = System.currentTimeMillis();
				Date lockoutTime = new Date(currentTime + (10 * 60 * 1000)); // 10분 후 시간
				session.setAttribute("bannedUntil", lockoutTime);

				long remainingTime = (lockoutTime.getTime() - new Date().getTime()) / (1000 * 60); // 잔여 시간(분)
				ras.addFlashAttribute("loginAttempts", "5/5");
				ras.addFlashAttribute("errorMsg",
						"아이디 및 비밀번호 입력 5회 이상 실패하였습니다. 계정 로그인이 10분간 제한됩니다. 잠금 해제 시간까지 약 " + remainingTime + "분 남았습니다.");
				ras.addFlashAttribute("bannedUntil", lockoutTime);

				return "redirect:/useraccount/login";
			} else {*/
				ras.addFlashAttribute("errorMsg",
						"아이디 또는 비밀번호를 잘못 입력하셨습니다. 입력하신 내용을 다시 확인해주세요."); //로그인 시도 횟수: " + loginAttempts + "/5");

				return "redirect:/userAccount/login";
		} 	// 로그인이 실패한 경우, 로그인 시도 횟수를 증가시키고, 잠금 시간을 설정한다.
			// 잠금 시간까지 남은 시간을 계산하고, 알림 메시지를 추가하여 사용자에게 알린다.
			// 로그인 시도 횟수가 5회 이상이면 계정을 잠그고, 잠금 해제 시간까지 알려주는 알림 메시지를 추가한다.
			// 로그인 시도 횟수가 5회 미만인 경우, 실패 메시지와 함께 로그인 페이지로 리다이렉트한다.*/		 
		return "redirect:/userAcount/login"; // 로그인이 성공하거나 실패한 후에는 항상 로그인 페이지로 리다이렉트한다.
	}
	
	
	/*
	@PostMapping("/userAccount/logout")
	public String logout(HttpSession session) {
		if(session != null) {
			// 세션을 제거한다. (안에 있는 데이터도 삭제)
			session.removeAttribute(SessionConst.INFO);
		}
		
		return "redirect:/";
	}*/
	
	// 로그아웃
	@PostMapping("/userAccount/logout")
	public String logout(SessionStatus sessionStatus) {
		log.info("academy 로그아웃 처리");
		sessionStatus.setComplete();
		return "redirect:/userAccount/login";
	}
	
	// 회원가입 페이지
	@GetMapping(value = "/userAccount/join/academy")
	public String joinForm() {
		log.info("academyJoin 호출 성공");
		
		return "academy/academyJoin";
	}
	
	// 회원가입
	@PostMapping("/academyInsert")
	public String academyInsert(AcademyLoginVo login) {
		
		log.info("personalInsert 호출 성공");
		academyLoginService.academyInsert(login);
		return "redirect:/userAccount/join/complete";
	}
	/*
	@GetMapping("/")
	public String home(AcademyLoginVo academyLoginVo,
			Model model) {
		
		// 홈 페이지로 이동
		if(academyLoginVo == null) {
			return "/main/main"; 
		} else {
						
			// 세션에 로그인 회원 정보 보관			
			model.addAttribute("academyLoginVo", academyLoginVo);
			return "/main/main"; 
		}
	}
	*/
	
	@GetMapping("/userAccount/join")
	public String signUp() {
		// 회원가입 페이지로 이동
		return "/academy/join"; // join.jsp
	}

	@GetMapping("/userAccount/join/complete")
	public String completeSignUp() {
	    // 회원가입 완료 페이지로 이동
	    return "/academy/completeJoin";
	}
	
	// 마이페이지
	@GetMapping("/mypage")
	public String mypage(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		AcademyLoginVo academyLoginVo = (AcademyLoginVo) session.getAttribute("academyLogin");
		
		log.info("mypage 호출 성공");
		
	    // 로그인 여부 확인
	    if(academyLoginVo == null) {
	    	redirectAttributes.addFlashAttribute("errorMsg", "로그인 후 이용해주세요.");
	    	return "redirect:/userAccount/login";
	    }
	    
	    model.addAttribute("academyLoginVo", academyLoginVo);
	    
	    return "academy/mypage";
	   
	    /*
	    // 로그인된 경우에는 학원 정보를 가져와서 모델에 추가
	    String academyId = academyLoginVo.getAcademyId();
	    Optional<AcademyLoginVo> academyInfoOptional = academyLoginService.findById(academyId);

	    if (academyInfoOptional.isPresent()) {
	        model.addAttribute("academyLoginVo", academyInfoOptional.get());
	    } 
	    return "/academy/mypage";
	    */
	}
	
	// 마이페이지 회원정보 수정
	@PostMapping("/academyUpdate")
	public String academyUpdate(@ModelAttribute AcademyLoginVo academyLogin, HttpSession session,
			RedirectAttributes redirectAttributes) {
		
		AcademyLoginVo sessionAcademyLogin = (AcademyLoginVo) session.getAttribute("academyLogin");
		
		sessionAcademyLogin.setAcademyPasswd(academyLogin.getAcademyPasswd());
		sessionAcademyLogin.setAcademyManagerName(academyLogin.getAcademyManagerName());
		sessionAcademyLogin.setAcademyManagerEmail(academyLogin.getAcademyManagerEmail());
		sessionAcademyLogin.setAcademyManagerPhone(academyLogin.getAcademyManagerPhone());
		sessionAcademyLogin.setAcademyTargetSubject(academyLogin.getAcademyTargetSubject());
		sessionAcademyLogin.setAcademyFee(academyLogin.getAcademyFee());
		sessionAcademyLogin.setAcademyTargetGrade(academyLogin.getAcademyTargetGrade());
		sessionAcademyLogin.setAcademyKeyword1(academyLogin.getAcademyKeyword1());
		sessionAcademyLogin.setAcademyKeyword2(academyLogin.getAcademyKeyword2());
		sessionAcademyLogin.setAcademyKeyword3(academyLogin.getAcademyKeyword3());
		sessionAcademyLogin.setAcademyKeyword4(academyLogin.getAcademyKeyword4());
		sessionAcademyLogin.setAcademyKeyword5(academyLogin.getAcademyKeyword5());
		
		log.info("academyUpdate 호출 성공");
		
		int result = academyLoginService.academyUpdate(sessionAcademyLogin);
		
		if (result == 0) {
			redirectAttributes.addFlashAttribute("errorMsg", "회원정보 업데이트에 실패했습니다. 다시 시도해 주세요.");
			return "redirect:/userAccount/login";
		}
		
		session.setAttribute("academyLogin", sessionAcademyLogin);
		return "redirect:/mypage";
	}
	
	// 아이디 중복체크 
	@GetMapping("/checkDuplicate")
	@ResponseBody
    public int checkDuplicate(@RequestParam("academyId") String academyId) {
        return academyLoginService.idCheck(academyId);
    }
	
	// 이메일 중복체크
	@PostMapping("/checkEmail")
	@ResponseBody
	public int checkEmail(@RequestParam("academyManagerEmail") String academyManagerEmail) {
		return academyLoginService.emailCheck(academyManagerEmail);
	}





}
