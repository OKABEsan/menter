package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.UserService;

@Controller
/**
 * 	ユーザーの編集機能を行うクラス
 */
public class EditController {
	//自動でUserserviceクラスの変数を追加
	@Autowired
	private UserService userService;

	@GetMapping("/student/edit/{id}")
	/**
	 * IDをもとにデータベースから1人分の情報を取り出す
	 * @param id
	 * @param model
	 * @return student/edit.html
	 */
	public String showEditForm(@PathVariable Integer id, Model model) {
		//userServiceで見つけたidをhtml上のデータベース表示に渡し、student/editページに返す。
		model.addAttribute("user", userService.findById(id));

		return "studentedit";
	}

}
