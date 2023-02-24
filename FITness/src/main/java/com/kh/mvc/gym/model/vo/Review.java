package com.kh.mvc.gym.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 리뷰테이블
public class Review {
	
	private int no; //리뷰번호
	private String content;
	private Date createDate;
	private Date modifyDate;
	private String status;
	private int grade;
	private int gymNo; //센터번호
	private int writerNo; //작성자번호
	private String writerId; //작성자 아이디
	
}
