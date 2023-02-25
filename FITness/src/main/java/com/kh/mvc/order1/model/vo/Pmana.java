package com.kh.mvc.order1.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pmana {
	private int manaNo;
	
	private int gymNo;
	
	private int userNo;
	
	private int optNo;
	
	private int voucherNo;
	
	private String sDate;
	
	private String eDate;
	
	private String pDate;
	
	private String status;
	
	private int tPrice;
	
	private String payMethod;
	
};
