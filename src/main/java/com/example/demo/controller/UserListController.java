package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserSearchForm;
import com.example.demo.service.UserService;

/**
 * 一覧を表示し検索の入力を受け画面へ繋ぐクラス
 */
@Controller
public class UserListController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	/**
	 * 
	 * @param model
	 * @return studentindex
	 */
	@GetMapping("/student/index") //(生徒の一覧ページ）
	public String studentIndex(Model model) {
		
		List<User>list=userService.findAll();
		
		model.addAttribute("userList",list);
		
		return "studentindex";
	}


	/**
	 * ユーザー検索フォームに検索用情報を繋ぎ、検索フォーム画面に返す（検索前の空のフォームを表示）
	 * @param usersearchform
	 * @return /student/search"
	 */
	@GetMapping("/student/search")
	public String studentSearch(@ModelAttribute UserSearchForm usersearchform) {

		return "/student/search";
	}

	/**
	 * 検索結果とデータベースを繋ぎ、検索結果URLに返す（検索結果を表示）
	 * @return "/student/search/result"
	 */
	@GetMapping("/student/search/result")
	public String studentSearchResult(@ModelAttribute UserSearchForm userSearchForm, Model model) {
		//検索フォームの情報が空の場合
		if (userSearchForm.isEmpty()) {
			//検索ページへ返す
			return "/student/search";

		} else {
			return "/student/search/result";

		}
		
	}
}
