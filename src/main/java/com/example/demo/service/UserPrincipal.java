package com.example.demo.service; // このファイルが属するパッケージ（フォルダ）

// 必要なツールをインポートしています
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Userクラスを使うためにインポートしています
import com.example.demo.model.User;

/*
 * ログインしているユーザーの情報を表すクラス
 */
public class UserPrincipal implements UserDetails {

	private User user; // Userオブジェクトを保持します。

	// コンストラクタでUserオブジェクトを受け取り、それをこのクラスのuserにセットします。
	public UserPrincipal(User user) {
		this.user = user;
	}

	// ユーザーに与えられる権限を返します。ここでは全てのユーザーに"USER"という権限を与えています。
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//ROLE_GUESTをroleNameへ代入
		String roleName=user.getRole();
		//もしロール名が"ROLE_"で始まってなかったら
		if (!roleName.startsWith("ROLE_")) {
			//getRoleで取得した値が０の場合
			if ("0".equals(String.valueOf(user.getRole()))) {
				roleName = "ROLE_STUDENT";
				//getRoleで取得した値が1の場合
			} else if ("1".equals(String.valueOf(user.getRole()))) {
				roleName = "ROLE_ADMIN";
			}
		}
		//ユーザーの持っているロールを集めて返す
		return Collections.singleton(new SimpleGrantedAuthority(roleName));
	}
	// Userオブジェクトのパスワードを返します。
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// Userオブジェクトのユーザー名を返します。
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// アカウントが有効期限切れでないことを示すために、常にtrueを返します。
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// アカウントがロックされていないことを示すために、常にtrueを返します。
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 資格情報（ここではパスワード）が有効期限切れでないことを示すために、常にtrueを返します。
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// アカウントが有効であることを示すために、常にtrueを返します。
	@Override
	public boolean isEnabled() {
		return true;
	}
}
