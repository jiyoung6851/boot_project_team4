package com.boot.service;

import java.util.ArrayList;

import com.boot.dto.BoardtbDTO;
import com.boot.dto.Criteria;

public interface PageService {
	public ArrayList<BoardtbDTO> listWithPaging(Criteria cri);
//	public int getTotalCount();
	public int getTotalCount(Criteria cri);
}
