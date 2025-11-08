package com.example.demo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

/**
 *ユーザーの検索フォームから受けたデータを表すクラス
 */
@Data
public class UserSearchForm {

	private Integer id;

	private String username; // ユーザー名を保存するための場所

	@Min(value = 0, message = "年齢は0歳以上で入力してください")
	private Integer age;

	@Pattern(regexp = "^\\d{4}/\\d{2}/\\d{2}$", message = "生年月日は0000/00/00の型式で入力してください")
	private String birthday;

	private String email; // メールアドレスを保存するための場所

	@Pattern(regexp = "^0\\d{1,4}-\\d{1,4}-\\d{3,4}$", message = "電話番号の型式が正しくありません")
	private String tel;

	private String plan;

	private String loginRole;

	public boolean isEmpty() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	
}
