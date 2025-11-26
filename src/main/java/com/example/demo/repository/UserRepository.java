package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Userクラスを使うためにインポートしています
import com.example.demo.model.User;

/**
 * データベースとやりとりを行いデータを取得、保存するクラス
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	//悲観ロックをかける
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	//JPQLでの命令。生徒1人を選択し生徒一覧から情報を取り出す
	@Query("SELECT u FROM User u WHERE u.id=:id")
	//情報を見つけ、情報があれば情報にロックをかける
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<User> findByIdWithLock(@Param("id") Integer id);

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
	List<User> findByRole(String role);

}
