package com.kh.mvc.notice.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
	private int no;
	
	private int rowNum;
	
	private int writerNo;
	
	private String writerId;
	
	private String title;
	
	private String content;
	
	private String originalFileName;
	
	private String renamedFileName;
	
	private int readCount;
	
	private String status;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private int replyCount;
	
	private String writerName;
	
	private String secretCheck;     // 비밀글 여부
	
	private String fix; 
}