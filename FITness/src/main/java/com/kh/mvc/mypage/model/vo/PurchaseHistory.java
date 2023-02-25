package com.kh.mvc.mypage.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseHistory {
	int tPrice;
	String cate;
	String gymName;
	String payMethod;
	Date pDate;
}
