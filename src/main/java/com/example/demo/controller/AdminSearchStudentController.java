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
@Controller
public class AdminSearchStudentController {
	@Autowired
	private UserService userService;

	/**
	 * ユーザー検索フォームに検索用情報を繋ぎ、検索フォーム画面に返す（検索前の空のフォームを表示）
	 * @param usersearchform
	 * @return /admin/search/student"
	 */
	@GetMapping("/admin/search/student")
	public String adminSearch(@ModelAttribute UserSearchForm form) {

		return "adminsearchstudent";
	}

	/**
	 * 検索結果とデータベースを繋ぎ、検索結果URLに返す（検索結果を表示）
	 * @return "/student/search/result"
	 */
	@GetMapping("/admin/search/result")
	public String admintSearchResult(@ModelAttribute UserSearchForm form, Model model) {
		//検索フォームの情報が空の場合
		if (form.isEmpty()) {
			//検索ページへ返す
			return "adminsearchstudent";

		} else {
			//フォームの値を検索してリストへ入れる
			List<User> list = userService.search(form);
			//検索結果をhtmlへ繋ぐ処理
			model.addAttribute("userList", list);

			//検索結果の表示
			return "adminSearchResult";

		}

	}
}


