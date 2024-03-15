package com.myedumyselect.academy.service;


import com.myedumyselect.academy.vo.AcademySignUpVo;
import com.myedumyselect.academy.vo.AcademyLoginVo;

public interface AcademyLoginService {
	AcademyLoginVo loginProcess(String academyId, String academyPasswd);
	int academyInsert(AcademySignUpVo academySignUpVo);
	AcademyLoginVo findById(String academyId);
	int idCheck(String academyId);
	int emailCheck(String academyManagerEmail);

	// TODO: 회원 정보 수정
	// TODO: 로그인 제한 상태
	// TODO: 로그인 실패 횟수
}

