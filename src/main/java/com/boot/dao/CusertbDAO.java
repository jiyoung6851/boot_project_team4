package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.CusertbDTO;

@Mapper
public interface CusertbDAO {
	public void cRegisterInsert(HashMap<String, String> param);
	public ArrayList<CusertbDTO> cloginYn(HashMap<String, String> param);
	public int idchk(String puserid);
	
    public ArrayList<CusertbDTO> CPasswordYn(HashMap<String, String> param);
    public CusertbDTO CInfoView(HashMap<String, String> param);
    public void CModify(HashMap<String, String> param);
    public void CDelete(HashMap<String, String> param);
}
