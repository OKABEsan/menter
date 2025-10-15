package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Springがアプリ起動時にSecurityConfigを自動的に読み込む。
@EnableWebSecurity //URLごとのセキュリティを有効にする。
@EnableMethodSecurity //メソッドごとのアクセス制御を有効化
public class SecurityConfig {

	@Bean //メソッドの戻り値をSpringの管理下に置く
	//パスワードを安全に保存・認証するための暗号化ツール
	public PasswordEncoder passwordEncoder() {
		//パスワードの暗号化クラス
		return new BCryptPasswordEncoder();
	}

	@Configuration
	@Order(1) //どの順番で実行するかを指定する
	public static class StudentConfigurationAdapter {

		@Bean
		//リクエスト（URL)が来た時にどんな順番で何をチェックするかをまとめたルールブック
		public SecurityFilterChain configureStudent(HttpSecurity http) throws Exception {

			http

					//リクエストの許可・認証ルールを定義します
					.authorizeHttpRequests(auth -> auth
							//静的リソース(CSS,JS,画像など)は全てアクセス許可
							.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
							.requestMatchers("/css/**","/js/**","/img/**").permitAll()
							//"/login"と"/register"はログインしていなくてもアクセスOK
							.requestMatchers("/login", "register").permitAll()
							//それ以外のURLは全て認証(ログイン)済みのユーザーのみアクセス可能
							.anyRequest().authenticated())
					//フォームを使ったログイン設定
					.formLogin(login -> login
							//HTMlフォームのinput name="email"をユーザー名として扱う
							.usernameParameter("username")
							//HTMlフォームのinput name="password"をパスワードとして扱う
							.passwordParameter("password")
							//ログインフォームの送信先URL
							.loginProcessingUrl("/login")
							//ログインページのURL
							.loginPage("/login")
							//ログイン失敗時の遷移先
							.failureUrl("/login?error")
							//ログイン成功時の遷移先
							.defaultSuccessUrl("/index")
							//ログインページやエラー画面は誰でも見られる
							.permitAll())
					//ログアウト用のURL
					.logout(logout -> logout
							//ログアウト成功後のリダイレクト先
							.logoutUrl("/logout")
							//ログアウト成功後のリダイレクト先
							.logoutSuccessUrl("/login?logout")
							//セッションIDを削除（再利用防止）
							.deleteCookies("JSESSIONID"));
			//上記で設定した内容を反映し、SecurityFilterCainオブジェクトとして構築
			return http.build();
		}
	}
}
