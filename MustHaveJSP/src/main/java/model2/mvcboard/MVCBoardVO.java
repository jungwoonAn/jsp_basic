package model2.mvcboard;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//게터/세터 lombok.jar 추가
@Getter
@Setter
@ToString
public class MVCBoardVO {
	// 멤버 변수 선언
	private String idx;
	private String name;
	private String title;
	private String content;
	private Date postdate;
	private String ofile;
	private String sfile;
	private int downcount;
	private String pass;
	private int visitcount;		
}
