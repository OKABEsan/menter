package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.User;
import com.example.demo.model.UserSearchForm;
/**
 * 
 */
@Mapper
public interface UserMapper {
	
	
	List<User>searchUsers(UserSearchForm form);


}
