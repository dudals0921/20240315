package com.myedumyselect.academy.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.myedumyselect.academy.vo.AcademyLoginVo;

@Mapper
public interface AcademyLoginDao {
	AcademyLoginVo findByIdAndPasswd(@Param("academyId") String academyId,
									 @Param("academyPasswd")String academyPasswd);
	int academyInsert(AcademyLoginVo academyLoginVo);
	AcademyLoginVo findById(String academyId);
	AcademyLoginVo findByNumber(String academyNumber);
	int idCheck(String academyId);
	int emailCheck(String academyManagerEmail);
	// TODO: int academyUpdate(AcademyLoginVo login);
}
