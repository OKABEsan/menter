package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.UserDto;
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
	
	//データを保存するリクエストを送る
	@PostMapping("/student/save")
	/**
	 * フォームのデータを保存するメソッド,バリデーション機能でチェックする
	 *
	 * @param userDto
	 * @return　redirect:/studentindex
	 */
	public String saveUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		//フォームにエラーがある場合

		try {
			if (bindingResult.hasErrors()) {
				//エラー内容の表示
				bindingResult.getAllErrors().forEach(e -> System.out.println("エラー内容" + e.getDefaultMessage()));
				model.addAttribute("user", userDto);
				model.addAttribute("errorMessage", "生徒情報を更新できませんでした。");
				//生徒編集画面に留まる
				return "studentedit";
				//フォームにエラーがない場合	
			} else {

				//渡してきたデータを保存する
				userService.save(userDto);
				redirectAttributes.addFlashAttribute("successMessage", "生徒情報を更新しました");
			}

			//例外処理
		} catch (Exception e) {
			model.addAttribute("errorMessage", "生徒情報を更新できませんでした。");
			//発生したエラーの場所と種類をコンソールに表示
			e.printStackTrace();
			return "studenteditt";

		}
		return "redirect:/student/index";
	}

}
