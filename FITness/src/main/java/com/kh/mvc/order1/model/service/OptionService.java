package com.kh.mvc.order1.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.order1.model.dao.OrederDao;
import com.kh.mvc.order1.model.vo.Option;
import com.kh.mvc.order1.model.vo.Pmana;
import com.kh.mvc.order1.model.vo.Voucher;

import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;

public class OptionService {

//	public int option(String option) {
//		int result = 0;
//		
//		Connection connection = getConnection();
//		result = new OrederDao().saveOption(connection,option);
//		
//		return result;
//	}

	public Gym getGymByNo(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	public Voucher getVouchersByNo(int vno) {
		Voucher voucher = null;
		
		Connection connection = getConnection();
		
		voucher = new OrederDao().getVouchersByNo(connection,vno);
		
		return voucher;
	}

	public Option getOptionByNo(int cno) {
		Option option = null;
		
		Connection connection = getConnection();
		
		option = new OrederDao().getOptionByNo(connection,cno);
		
		return option;
	}

	public int save(Pmana pmana) {
		int result = 0;
		Connection connection =  getConnection();
		
		result = new OrederDao().insertPmana(connection,pmana);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		return result;
	}



}
