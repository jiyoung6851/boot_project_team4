package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class JobaplytbDTO {
	private String puserid;
	private String cuserid;
	private int csrno;
	private int jobno;
	private int prono;
	private int prchk;
	private Timestamp adate;
	private Timestamp mdate;
	
	//add columns
	private String jobtitle;
	private String jobsubtitle;
	
	private String protitle;
	private String prstitle; 
	
	private int imgno;
	private String uuid;
	private String uploadpath;
	private String filename;
}