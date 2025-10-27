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
	 * @return redirect:/studentindex,redirect:/adminindex
	 */
	public String redirectToIndex() {
		//今ログインしている状態のユーザー認証情報を取得する
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//ログイン済のユーザーがいるかどうかチェックをする
		if (authentication != null && authentication.isAuthenticated()) {

			String role = authentication.getAuthorities().iterator().next().getAuthority();
			System.out.println("ログイン中のユーザー権限→" + role);

			if (role.equals("ROLE_STUDENT")) {

				return "redirect:/student/index";

			} else if (role.equals("ROLE_ADMIN")) {

				return "redirect:/admin/index";
			}

		}

		//そうでなければログインへ移動する
		return "redirect:/login";

	}

	@GetMapping("/student/index") //URLごとに処理するメソッドを指定する。

	/**
	 * modelにデータを入れる
	 * @param model
	 * @return studentindex
	 */
	public String studentIndex(Model model) {

		//modelにuserデータベースからみつけたstudentロールを渡す
		model.addAttribute("user", userRepository.findByRole("ROLE_STUDENT"));
		return "studentindex";

	}

	@GetMapping("/admin/index")
	/**
	 * 
	 * @param model
	 * @return adminindex
	 */
	public String adminIndex(Model model) {

		//modelにuserデータベースから見つけたadminロールを渡す
		model.addAttribute("user", userRepository.findByRole("ROLE_ADMIN"));
		return "adminindex";

	}

}
