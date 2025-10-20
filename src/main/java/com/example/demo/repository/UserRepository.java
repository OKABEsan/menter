package com.example.demo.repository;

//必要なツールをインポートしています
import org.springframework.data.jpa.repository.JpaRepository;

//Userクラスを使うためにインポートしています
import com.example.demo.model.User;

/**
 * データベース操作を行うクラス。保存、更新、削除、検索
 */
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * ユーザー名でユーザーを探すメソッド。ユーザー名をパラメータとして渡すと、そのユーザー名を持つユーザーをデータベースから探して返します。
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
}
