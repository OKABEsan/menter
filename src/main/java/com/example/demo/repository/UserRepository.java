package com.example.demo.repository;

import java.util.List;

//必要なツールをインポートしています
import org.springframework.data.jpa.repository.JpaRepository;

//Userクラスを使うためにインポートしています
import com.example.demo.model.User;

/**
 * データベース操作を行うクラス。保存、更新、削除、検索
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * 
	 * @param username
	 * @return
	 * ユーザーネームの文字を見つける
	 */
	User findByUsername(String username);
	/**
	 * 指定したロールをもつユーザー一覧を見つける
	 * @param role
	 * @return
	 */
	List<User>findByRole(String role);

}
