package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ScribetbDTO {
	private String authorid;
	private int scribeno;	
	private String scribeid;
	private String usetype;	
	private Timestamp adate;
	private Timestamp mdate;
}
