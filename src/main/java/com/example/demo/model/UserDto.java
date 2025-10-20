package com.example.demo.model;

import jakarta.validation.constraints.NotEmpty;

/**
 * ユーザーのデータを一時的に保持し受け渡しをするクラス
 */
public class UserDto {
	@NotEmpty // ユーザー名は空であってはならないというルール
	private String username; // ユーザー名を保存するための場所

	@NotEmpty
	private Integer age;

	@NotEmpty
	private String birthday;

	@NotEmpty // パスワードは空であってはならないというルール
	private String password; // パスワードを保存するための場所

	@NotEmpty // メールアドレスは空であってはならないというルール
	private String email; // メールアドレスを保存するための場所

	@NotEmpty
	private String tel;

	@NotEmpty
	private String plan;

	// 以下は各値を取得するためのメソッド（ゲッター）です。
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

}
