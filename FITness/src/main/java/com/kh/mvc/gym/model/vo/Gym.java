package com.kh.mvc.gym.model.vo;

import java.io.BufferedReader;
import java.util.List;

import com.kh.mvc.board.model.vo.Reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gym{
	private int no;
	private String gymName;
	private String address;
	private String gymPhone;
	private String content;
	private String time;
	private String img;
	private String thumb;
	private String category;
	private String status;
	private String ceoPhone;
	private String ceoEmail;
	private int price;
	private int grade;
	private List<Voucher> vouchers;
	private List<Review> reviews;

}

