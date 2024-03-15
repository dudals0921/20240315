package com.myedumyselect.academy.service;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myedumyselect.academy.dao.AcademyLoginDao;
import com.myedumyselect.academy.vo.AcademyLoginVo;

import lombok.Setter;

@Service
public class AcademyLoginServiceImpl implements AcademyLoginService {
	
	@Setter(onMethod_ = @Autowired)
	private AcademyLoginDao academyLoginDao;

	/// 로그인
	@Override
	public AcademyLoginVo loginProcess(AcademyLoginVo login) {
		AcademyLoginVo academyLogin = academyLoginDao.loginProcess(login);
		return academyLogin;
	}

	/*
	// 로그인 실패 횟수
	@Override
	public int updateacademyLoginFailCount(academyLoginVo login) {
		return academyLoginDao.updateacademyLoginFailCount(login);
	}
	

	@Override
	public int updateAccountBannedDate(String academyId, Date bannedDate) {
		return academyLoginDao.updateAccountBannedDate(academyId, bannedDate);
	}
	*/
	
	// 학원회원 ID 중복체크
	@Override
	public int idCheck(String academyId) {
		int cnt = 0;
		try {
			cnt = academyLoginDao.idCheck(academyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("cnt: " + cnt);
		return cnt;
	}

	@Override
	public int emailCheck(String academyEmail) {
		int cnt = 0;
		try {
			cnt = academyLoginDao.emailCheck(academyEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("cnt: " + cnt);
		return cnt;
	}

	// 회원가입
	@Override
	public int academyInsert(AcademyLoginVo login) {
		int result = 0;
		result = academyLoginDao.academyInsert(login);
		return result;
	}

	// 마이페이지
	@Override
	public AcademyLoginVo academyMyPage(AcademyLoginVo login) {
		AcademyLoginVo academyLogin = academyLoginDao.loginProcess(login);
		return academyLogin;

	}

	// 마이페이지 정보수정
	@Override
	public int academyUpdate(AcademyLoginVo login) {
		int result = 0;
		result = academyLoginDao.academyUpdate(login);
		return result;
	}
}
