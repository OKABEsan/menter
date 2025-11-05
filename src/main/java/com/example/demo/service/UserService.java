package com.example.demo.service; // このファイルが属するパッケージ（フォルダ）

import java.util.List;

// 必要なクラスをインポートします
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.repository.UserRepository;

/**
 * 画面とデータベースの間でデータを橋渡しするクラス
 */
@Service // ServiceクラスだよとSpringnに教える
public class UserService implements UserDetailsService { // UserDetailsServiceインターフェースを実装しています

	@Autowired // Springが自動的にUserRepositoryの実装を注入します
	private UserRepository userRepository;

	@Autowired // Springが自動的にPasswordEncoderの実装を注入します
	private PasswordEncoder passwordEncoder;

	@Override // UserDetailsServiceインターフェースのメソッドを上書きします
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// ユーザーが見つからない場合、例外をスローします
		User user = userRepository.findByUsername(username);

		// ユーザーが見つからない場合、例外をスローします
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		//権限と取得してきた役職が生徒の場合
		if ("ROLE_STUDENT".equals(user.getRole())) {
			System.out.println("生徒一覧" + user.getUsername());
			//権限と取得してきた役職が講師の場合
		} else if ("ROLE_ADMIN".equals(user.getRole())) {
			System.out.println("講師一覧" + user.getUsername());
		}

		return new UserPrincipal(user); // ユーザーが見つかった場合、UserPrincipalを作成し返します
	}

	/**
	 * 	//新たにメソッドを追加します
	 * @param username
	 * @return userRepository.findByUsername(username);
	 */
	public User findByUsername(String username) {
		return userRepository.findByUsername(username); // ユーザー名でユーザーを検索し返します
	}

	/**
	 * //ユーザー一覧からロール名見つけ、新たにメソッドへ追加する
	 * @param role
	 * @return userRepository.findByRole(role);
	 */
	public List<User> findByrole(String role) {
		return userRepository.findByRole(role);
	}

	/**
	 * ユーザ一覧からIDを見つけ、中身があれば、Repositoryへ渡す
	 * @param id
	 * @return　userRepository.findById(id).orElse(null);
	 */
	public User findById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	/**
	 * 生徒一覧から情報を見つけてロックをかけて取り出し、中身があればRepositoryへ渡す
	 * @param id
	 * @return userRepository.findByIdWithLock(id).orElse(null);
	 */
	@Transactional
	public User findByIdWithLock(Integer id) {
		return userRepository.findByIdWithLock(id).orElse(null);
	}

	/**
	 * //データベース処理を一つの処理としてまとめる(新規登録）
	 * @param userDto
	 */
	@Transactional
	public void register(UserDto userDto) {

		// UserDtoからUserへの変換
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setAge(userDto.getAge());
		user.setBirthday(userDto.getBirthday());
		user.setTel(userDto.getTel());
		user.setPlan(userDto.getPlan());
		// パスワードをハッシュ化してから保存
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setRole(userDto.getRole());

		// データベースへの保存
		userRepository.save(user); // UserRepositoryを使ってユーザーをデータベースに保存します
	}

	/**
	 * /データベース処理を一つの処理としてまとめる(更新）
	 * @param userDto
	 */
	@Transactional
	public void save(UserDto userDto) {
		//データベースからDtoのIDを見つけて取り出し変数userへ代入する
		User user = userRepository.findById(userDto.getId()).get();
		user.setUsername(userDto.getUsername());
		user.setAge(userDto.getAge());
		user.setBirthday(userDto.getBirthday());
		user.setTel(userDto.getTel());
		user.setPlan(userDto.getPlan());
		user.setEmail(userDto.getEmail());
		user.setRole(userDto.getRole());
		//パスワードが存在していてかつDtoで取得したパスワードが空文字でない場合
		if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
			//BCrypt方式を変数passWordEncoderへ代入
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			//ユーザーIDにDtoのパスワードをそのまま設定する
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}
		userRepository.save(user); // UserRepositoryを使ってユーザーをデータベースに保存します

	}

}
