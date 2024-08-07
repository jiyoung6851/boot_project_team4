package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.RcareerDTO;

public interface RcareerService {
    public void addRcareer(HashMap<String, String> param);
    public ArrayList<RcareerDTO> selectRcareer(HashMap<String, String> param); // 추가
    public void delete_career(HashMap<String, String> param); // 해당 이력서 경력 전체 삭제
}
