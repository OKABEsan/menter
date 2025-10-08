package com.example.demo.controller; // このファイルが属するパッケージ（フォルダ）

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //WebリクエストをSpringに処理してもらうための入口
public class LoginController {

	@GetMapping("/login") //URLと実行するメソッドを結びつけるための仕組み。

	public String login() {

		return "/login";
	}

	@GetMapping("/") //URLと実行するメソッドを結びつけるための仕組み
	public String redirectToIndex() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		//ログイン認証が空ではないかつログイン認証された場合
		if (authentication != null && authentication.isAuthenticated()) {
			//HTMLへ移動する
			return "redirect:/index";

		}

		//そうでなければログインへ移動する
		return "redirect:/login";

	}

	@GetMapping("/index") //URLごとに処理するメソッドを指定する。

	public String index() {

		return "index";//HTMLを表示
	}

}
