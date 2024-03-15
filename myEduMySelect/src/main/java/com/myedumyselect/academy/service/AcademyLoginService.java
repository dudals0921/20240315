package com.myedumyselect.academy.service;

import com.myedumyselect.academy.vo.AcademyLoginVo;

public interface AcademyLoginService {
	
	// 로그인
	public AcademyLoginVo loginProcess(AcademyLoginVo login);
	
	/*
	// 로그인 실패 횟수
	public int updateAcademyLoginFailCount(academyLoginVo login);
	
	
	// 로그인 제한 상태
	public int updateAccountBannedDate(String academyId, Date bannedDate);
	*/
	
	// 회원가입
	public int academyInsert(AcademyLoginVo login);

	// 학원회원 아이디 중복체크
	public int idCheck(String id);

	// 학원회원 이메일 중복체크
	public int emailCheck(String email);

	// 마이페이지
	public AcademyLoginVo academyMyPage(AcademyLoginVo login);

	/// 회원정보 수정
	public int academyUpdate(AcademyLoginVo login);

	
}

