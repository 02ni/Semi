package com.kh.mvc.order1.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.order1.model.vo.Option;
import com.kh.mvc.order1.model.vo.Pmana;
import com.kh.mvc.order1.model.vo.Voucher;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;

public class OrederDao {

	public Voucher getVouchersByNo(Connection connection, int vno) {
		Voucher voucher = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM VOUCHER V JOIN GYM G ON (V.VOUCHER_NO = G.NO) WHERE V.VOUCHER_NO=? ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, vno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				voucher = new Voucher();
				voucher.setVoucherNo(rs.getInt("VOUCHER_NO"));
				voucher.setGymNo(rs.getInt("GYM_NO"));
				voucher.setCate(rs.getString("CATE"));
				voucher.setPrice(rs.getInt("PRICE"));
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
//		System.out.println(voucher);
		return voucher;
	}


	public Option getOptionByNo(Connection connection, int cno) {
		Option option = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT  * FROM P_OPTION WHERE OPT_NO= ?";
		
		try{
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, cno);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				option = new Option();
				option.setOptNo(rs.getInt("OPT_NO"));
				option.setOptName(rs.getString("OPT_NAME"));
				option.setOptPrice(rs.getInt("OPT_PRICE"));
			}
		} catch (SQLException e) {
		
		}finally {
			close(rs);
			close(pstmt);
		}
//		System.out.println(option);
		
		return option;
	}


	public int insertPmana(Connection connection, Pmana pmana) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO P_MANA (MANA_NO, GYM_ID,USER_NO,OPT_NUM,VOUCHER_ID,P_START,P_END, P_DATE,STATUS,T_PRICE,PAY_METHOD)"
				+ "VALUES (SEQ_P_MANA.NEXTVAL,?,?,?,?,TO_DATE(?, 'YYYY-MM-DD'),TO_DATE(?, 'YYYY-MM-DD'),SYSDATE,DEFAULT,?,?)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1,pmana.getGymNo());
			pstmt.setInt(2, pmana.getUserNo());
			pstmt.setInt(3, pmana.getOptNo());
			pstmt.setInt(4, pmana.getVoucherNo());
			pstmt.setString(5, pmana.getSDate());
			pstmt.setString(6, pmana.getEDate());
//			pstmt.setString(7, pmana.getStatus());
			pstmt.setInt(7, pmana.getTPrice());
			pstmt.setString(8, pmana.getPayMethod());
			
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}

