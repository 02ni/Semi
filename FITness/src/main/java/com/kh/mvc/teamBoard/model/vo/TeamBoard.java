package com.kh.mvc.teamBoard.model.vo;

import java.util.Date;
import java.util.List;

import com.kh.mvc.board.model.vo.Reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamBoard {
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
	
	private List<Reply> replies;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private int replyCount;
	
	private String secretCheck;   
}
