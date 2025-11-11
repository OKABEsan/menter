package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.User;
import com.example.demo.model.UserSearchForm;
import com.example.demo.service.UserService;

/**
 * 一覧を表示し検索の入力を受け画面へ繋ぐクラス
 */
@Controller
public class StudentSearchAdminController {
	@Autowired
	private UserService userService;

	/**
	 * ユーザー検索フォームに検索用情報を繋ぎ、検索フォーム画面に返す（検索前の空のフォームを表示）
	 * @param usersearchform
	 * @return /student/search/admin"
	 */
	@GetMapping("/student/search/admin")
	public String studentSearch(@ModelAttribute UserSearchForm form) {

		return "studentsearchadmin";
	}

	/**
	 * 検索結果とデータベースを繋ぎ、検索結果URLに返す（検索結果を表示）
	 * @return "/student/search/result"
	 */
	@GetMapping("/student/search/result")
	public String studentSearchResult(@ModelAttribute UserSearchForm form, Model model) {
		//検索フォームの情報が空の場合
		if (form.isEmpty()) {
			//検索ページへ返す
			return "studentsearchadmin";

		} else {
			//フォームの値を検索してリストへ入れる
			List<User> list = userService.search(form);
			//検索結果をhtmlへ繋ぐ処理
			model.addAttribute("userList", list);

			//検索結果の表示
			return "/student/search/result";

		}

	}
}
