package com.kh.mvc.order1.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {
	private int voucherNo;
	private int  gymNo;
	private String cate;
	//1일권
	private int price;
	private int price3;
	private int price6;
	private int price12;

	public void setVoucherNo(int voucherNo) {
		this.voucherNo = voucherNo;
	}

	public void setGymNo(int gymNo) {
		this.gymNo = gymNo;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}
	//개월계산(3개월은 5%, 6개월은 10%, 12개월 15%)
	public void setPrice(int price) {
		this.price = price;
		this.price3 = (int)(price * 3 * 0.95);
		this.price6 = (int)(price * 6 * 0.90);
		this.price12 = (int)(price * 12 * 0.85);
		
	}
}
