package com.example.demo.controller; // このファイルが属するパッケージ（フォルダ）

// 必要なクラスをインポートします
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;

@Controller // このクラスがWebコントローラーであることを示します
/**
 * 新規登録の入力を受け、処理するクラス
 */
public class RegisterController {

	// Spring が自動的に UserService の実装を注入します
	@Autowired
	private UserService userService;

	@GetMapping("/register") // "/register"というURLに対するGETリクエストを処理します
	/**
	 * データと画面を繋ぐ
	 * @return mav
	 */
	public ModelAndView registerForm() {
		ModelAndView mav = new ModelAndView(); // ModelAndViewオブジェクトを作成します
		mav.addObject("user", new UserDto()); // 新しいUserDtoオブジェクトを"ユーザー"という名前で追加します
		mav.setViewName("register"); // 表示するビュー（HTMLファイル）の名前を"register"に設定します
		return mav; // ModelAndViewオブジェクトを返します
	}

	@PostMapping("/register") // "/register"というURLに対するPOSTリクエストを処理します
	/**
	 * 入力された一時的に保持されたデータを認証し処理する
	 * @param userDto
	 * @return register login
	 */
	public String register(@ModelAttribute UserDto userDto, BindingResult bindingResult) {
		//パスワードが存在しないときか、空文字の場合
		if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
			bindingResult.rejectValue("password", "error.password", "パスワードは必須です");
			return "register";
		}

		User existing = userService.findByUsername(userDto.getUsername()); // ユーザー名で既存のユーザーを検索します

		if (existing != null) {
			// ユーザーが既に存在する場合の処理
			System.out.println("既存ユーザー情報あり：" + existing);
			return "register"; // ユーザーが存在するため、再度登録画面を表示します
		}
		//1を入力した場合
		if ("1".equals(String.valueOf(userDto.getRole()))) {
			//ROLE_ADMINを保持する
			userDto.setRole("ROLE_ADMIN");
			//それ以外の場合ROLE_STUDENT
		} else {
			//ROLE_STUDENTを保持する
			userDto.setRole("ROLE_STUDENT");
		}

		userService.register(userDto); // ユーザーが存在しない場合、新しいユーザーを保存します
		return "login"; // 登録が成功した場合、ログイン画面を表示します
	}
}
