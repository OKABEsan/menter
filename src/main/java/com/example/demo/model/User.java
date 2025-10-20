package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity //データベースとDBのテーブルと繋がる
@Table(name = "user") // このクラスが対応するテーブルの名前は "user" です
/**
 * データベースのテーブルとその中身を表すクラス
 */
@Data
public class User {
	@Id // これが各ユーザを一意に識別するためのIDとなります
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDは自動的に増加します(自動で連番をふること）
	@Column(name = "id") // データベースに合わせてカラム名を修正
	private Integer id;

	@Column(name = "username", nullable = false, unique = true) // "username" カラム。各ユーザーのユーザー名を表します。同じ名前のユーザーは存在できません
	private String username;

	@Column(name = "password", nullable = false) // "password" カラム。ユーザーのパスワードを表します
	private String password;

	@Column(name = "email", nullable = false, unique = true) // "email" カラム。ユーザーのメールアドレスを表します。同じメールアドレスのユーザーは存在できません
	private String email;

	@Column(name = "age")
	private Integer age;

	@Column(name = "birthday")
	private String birthday;

	@Column(name = "tel")
	private String tel;

	@Column(name = "plan")
	private String plan;

	// 以下は各値を取得するためのメソッド（ゲッター）です。
	public Integer getId() {
		return this.id;
	}

}
