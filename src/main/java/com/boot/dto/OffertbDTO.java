package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class OffertbDTO {
	private int offerno;
	private String puserid;
	private String cuserid;
	private String title;
	private String content;
	private String gubun;
	private Timestamp ddate;
	private Timestamp adate;
	private Timestamp mdate;
}