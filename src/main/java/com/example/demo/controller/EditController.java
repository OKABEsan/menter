package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
/**
 * ユーザーの編集機能を行うクラス
 * @return edit_form
 */
public class EditController {


	@GetMapping("student/edit{id}")
	/**
	 * 生徒の編集画面で処理するメソッドを指定する。
	 * @param id
	 * @return
	 */
	public String showEditForm(@PathVariable Long id,Model model) {

		return "student/edit";
	}
	
	
	
	
	
	
}
