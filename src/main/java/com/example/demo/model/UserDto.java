package com.example.demo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * ユーザーのデータを一時的に保持し受け渡しをするクラス
 */
public class UserDto {
	private Integer id;
	@NotBlank(message = "名前は必須です")
	private String username; // ユーザー名を保存するための場所

	@NotNull
	@Min(value = 0, message = "年齢は0歳以上で入力してください")
	private Integer age;

	@NotBlank(message = "生年月日は必須です")
	@Pattern(regexp = "^\\d{4}/\\d{2}/\\d{2}$", message = "生年月日はyyyy/mm/ddの型式で入力してください")
	private String birthday;

	private String password; // パスワードを保存するための場所

	@NotBlank(message = "メールアドレスは必須です")
	private String email; // メールアドレスを保存するための場所

	@Pattern(regexp = "^0\\d{1,4}-\\d{1,4}-\\d{3,4}$", message = "電話番号の型式が正しくありません")
	private String tel;

	@NotEmpty
	private String plan;

	@NotEmpty
	private String role;

	// 以下は各値を取得するためのメソッド（ゲッター）です。
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username; // ユーザー名を返す
	}

	public void setUsername(String username) {
		this.username = username; // ユーザー名を設定する
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getPassword() {
		return password; // パスワードを返す
	}

	public void setPassword(String password) {
		this.password = password; // パスワードを設定する
	}

	public String getEmail() {
		return email; // メールアドレスを返す
	}

	public void setEmail(String email) {
		this.email = email; // メールアドレスを設定する
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
