package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PusertbDTO {
	private String puserid;
	private String ppass;
	private String pname;
	private String email;
	private String phone;
	private Timestamp adate;
	private Timestamp mdate;
}