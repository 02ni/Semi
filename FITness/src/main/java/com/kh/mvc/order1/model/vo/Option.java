package com.kh.mvc.order1.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
	private int optNo;
	
	private String optName;
	
	private int optPrice;
	
	private int optPrice1;

	public void setOptNo(int optNo) {
		this.optNo = optNo;
	}
	
	public void setOptName(String optName) {
		this.optName = optName;
	}
	
	public  void setPrice(int optPrice) {
		this.optPrice = optPrice;
		this.optPrice1 = (int)(optPrice/2);
	}
}
