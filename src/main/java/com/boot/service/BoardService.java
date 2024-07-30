package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.BoardtbDTO;

public interface BoardService {
	public ArrayList<BoardtbDTO> list();
//	public void write(HashMap<String, String> param);
	public void write(BoardtbDTO boardDTO);
	public BoardtbDTO modify_view(HashMap<String, String> param);
	public BoardtbDTO contentView(HashMap<String, String> param);
	public void upHit(HashMap<String, String> param);
	public void modify(HashMap<String, String> param);
	public void delete(HashMap<String, String> param);
}
