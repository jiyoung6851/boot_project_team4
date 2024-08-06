package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.RcareerDTO;

@Mapper
public interface RcareerDAO {
    public void createRcareer(HashMap<String, String> param);
    public ArrayList<RcareerDTO> selectRcareer(HashMap<String, String> param); 
    public void updateRcareer(RcareerDTO rcareer);
    public void deleteRcareer(HashMap<String, String> param); 
}