package com.myedumyselect.academy.controller;

import com.myedumyselect.auth.SessionInfo;
import com.myedumyselect.auth.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.myedumyselect.academy.vo.AcademySignUpVo;
import com.myedumyselect.academy.service.AcademyLoginService;
import com.myedumyselect.academy.vo.AcademyLoginVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@SessionAttributes(SessionInfo.COMMON)
public class AcademyLoginController {

	@Setter(onMethod_ = @Autowired)
	private AcademyLoginService academyLoginService;

	/**
	 * 유저 계정 로그인 폼
	 */
	@GetMapping("/academyaccount/login")
	public String userAccountLoginForm(Model model) {
		model.addAttribute("loginVo", new LoginVo());
		return "/academy/login";
	}

	/**
	 * 유저 계정 로그인 POST
	 */
	@PostMapping("/academyaccount/login")
	public String userAccountLogin(@Valid @ModelAttribute LoginVo loginVo, Model model, BindingResult bindingResult) {
		// 바인딩 에러 확인
		if(bindingResult.hasErrors()) {
			return "/academy/login";
		}

		if(loginVo.getMemberTypeId() == 2){
			log.info("hihi");
			// Academy
			AcademyLoginVo academyLoginVo = academyLoginService.loginProcess(loginVo.getId(), loginVo.getPasswd());

			// 로그인 실패
			if(academyLoginVo == null) {
				bindingResult.reject("loginFailed", "아이디 및 비밀번호가 일치하지 않습니다.");
				return "/academy/login";
			}

			// 세션에 로그인 회원 정보 보관
			loginVo.setName(academyLoginVo.getAcademyName());
			model.addAttribute(SessionInfo.COMMON, loginVo);
		}

		return "redirect:/academyaccount/login";
	}

	@GetMapping(value = "/academyaccount/join/academy")
	public String joinForm(Model model) {
		log.info("academyJoin 호출 성공");
		// 모델 등록
		model.addAttribute("academySignUpDto", new AcademySignUpVo());
		return "academy/academyJoin";
	}

	@PostMapping("/academyInsert")
	public String academyInsert(@Valid @ModelAttribute AcademySignUpVo academySignUpVo,
								BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			return "academy/academyJoin";
		}

		// 저장
		try {
			log.info("academyInsert 호출 성공");
			log.info("academySignUpDto : {}", academySignUpVo);
			academyLoginService.academyInsert(academySignUpVo);
		} catch (AcademyIdDuplicateException e) {
			bindingResult.reject("duplicateAcademyId", "이미 존재하는 아이디입니다.");
			return "academy/academyJoin";
		}

		return "redirect:/userAccount/join/complete";
	}

	@GetMapping("/academyaccount/join")
	public String signUp() {
		// 회원가입 페이지로 이동
		return "/academy/join"; // join.jsp
	}

	@GetMapping("/academyaccount/join/complete")
	public String completeSignUp(@ModelAttribute AcademyLoginVo academyLoginVo) {
		// 회원가입 완료 페이지로 이동
		return "/academy/completeJoin";
	}

	@GetMapping("/academyaccount/mypage")
	public String mypage(Model model, HttpSession session) {
		LoginVo loginVo = (LoginVo) session.getAttribute(SessionInfo.COMMON);

		// 로그인 여부 확인
		if(ObjectUtils.isEmpty(loginVo)) {
			// 로그인되어 있지 않다면 로그인 페이지로 리다이렉트
			return "redirect:/userAccount/login";
		}

		// 로그인된 경우에는 학원 정보를 가져와서 모델에 추가
		String academyId = loginVo.getId();
		AcademyLoginVo academyLoginVo = academyLoginService.findById(academyId);

		if (academyLoginVo != null) {
			model.addAttribute("academyLoginVo", academyLoginVo);
		} else {
			// 학원 정보가 없을 경우 처리
			// 예: 에러 페이지로 리다이렉트 또는 다른 처리
		}

		return "/academy/mypage";
	}
}
