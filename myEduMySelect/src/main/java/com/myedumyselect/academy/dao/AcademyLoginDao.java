package com.myedumyselect.academy.dao;

//import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;

import com.myedumyselect.academy.vo.AcademyLoginVo;

@Mapper
public interface AcademyLoginDao {
	
	// 로그인
	public AcademyLoginVo loginProcess(AcademyLoginVo login);
	/*
	// 로그인 실패 횟수
	public int updateacademyLoginFailCount(AcademyLoginVo login);

	// 로그인 제한 상태
	public int updateAccountBannedDate(String academyId, Date bannedDate);
	*/
	// 학원회원 아이디 중복확인
	public int idCheck(String academyId);

	// 학원회원 이메일 중복확인
	public int emailCheck(String academyEmail);

	// 회원가입
	public int academyInsert(AcademyLoginVo login);

	// 마이페이지
	public AcademyLoginVo academyMyPage(AcademyLoginVo login);

	/// 회원정보 수정
	public int academyUpdate(AcademyLoginVo login);
	
}
