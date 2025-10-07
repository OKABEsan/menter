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

		if (authentication != null && authentication.isAuthenticated()) {

			return "redirect:/index";

		}

		return "redirect:/login";

	}

	@GetMapping("/index")

	public String index() {

		return "index";
	}

}
