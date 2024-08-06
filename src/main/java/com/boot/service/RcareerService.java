package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.RcareerDTO;

public interface RcareerService {
    public void addRcareer(HashMap<String, String> param);
    public ArrayList<RcareerDTO> selectRcareer(HashMap<String, String> param); // 추가
}
