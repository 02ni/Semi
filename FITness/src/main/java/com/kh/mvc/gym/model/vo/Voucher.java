package com.kh.mvc.gym.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {
	private int voucherNo;
	
	private int gymNo;
	
	private String cate;
	
	private int price;
	
	
}
