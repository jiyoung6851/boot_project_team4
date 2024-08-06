package com.boot.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.RcareerDTO;

@Mapper
public interface RcareerDAO {
    public void createRcareer(HashMap<String, String> param);
    RcareerDAO getRcareer(String puserid);
    void updateRcareer(RcareerDAO rcareer);
    void deleteRcareer(String puserid);
}
