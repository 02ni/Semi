package com.kh.mvc.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int no;
	
	private String id;
	
	private String password;
	
	private String role;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String status;
	
	private Date enrollDate;

	private Date modifyDate;
	
	private int count;
	
	private int replyCount;
	
	private int rowNum;
	
	private String gymName;
}
