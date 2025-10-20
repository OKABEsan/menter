package com.example.demo.controller; // このファイルが属するパッケージ（フォルダ）

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.UserRepository;

@Controller //WebリクエストをSpringに処理してもらうための入口
/**
 *ログイン画面の入力を受けとり、処理するクラス 
 */
public class LoginController {
	@Autowired //自動でSQL操作クラスを作成
	private UserRepository userRepository;

	@GetMapping("/login") //URLと実行するメソッドを結びつけるための仕組み。
	/**
	 *ログインURLの保持
	 * @return /loginn
	 */
	public String login() {

		return "/login";
	}

	@GetMapping("/") //URLと実行するメソッドを結びつけるための仕組み
	/**
	 * ログインしているかチェックしてログイン中ならindex、ログインしてなければloginへ移動する。
	 * @return redirect:/index
	 */
	public String redirectToIndex() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	//ログイン済のユーザーがいるかどうかチェックをする
		if (authentication != null && authentication.isAuthenticated()) {
			
			//HTMLへ移動する
			return "redirect:/index";

		}

		//そうでなければログインへ移動する
		return "redirect:/login";

	}

	@GetMapping("/index") //URLごとに処理するメソッドを指定する。

	/**
	 * modelにデータを入れる
	 * @param model
	 * @return index
	 */
	public String index(Model model) {

		//modelにuserデータベース全てを受け渡す
		model.addAttribute("user", userRepository.findAll());
		return "index";

	}

}
