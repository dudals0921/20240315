package com.myedumyselect.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myedumyselect.academy.vo.AcademySignUpVo;
import com.myedumyselect.academy.dao.AcademyLoginDao;
import com.myedumyselect.academy.vo.AcademyLoginVo;

import lombok.Setter;

@Service
public class AcademyLoginServiceImpl implements AcademyLoginService {

	@Setter(onMethod_ = @Autowired)
	private AcademyLoginDao academyLoginDao;


	// 로그인
	@Override
	public AcademyLoginVo loginProcess(String academyId, String academyPasswd) {
		return academyLoginDao.findByIdAndPasswd(academyId, academyPasswd);
	}

	// 회원가입
	public int academyInsert(AcademySignUpVo academySignUpVo) {
		int result = 0;
		result = academyLoginDao.academyInsert(academySignUpVo.toAcademyLoginVo());
		return result;
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
	public AcademyLoginVo findById(String academyId) {
		return academyLoginDao.findById(academyId);
	}

	@Override
	public int idCheck(String id) {
		int cnt = 0;
		try {
			cnt = academyLoginDao.idCheck(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("cnt: " + cnt);
		return cnt;
	}

	@Override
	public int emailCheck(String email) {
		int cnt = 0;
		try {
			cnt = academyLoginDao.emailCheck(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("cnt: " + cnt);
		return cnt;
	}

}
