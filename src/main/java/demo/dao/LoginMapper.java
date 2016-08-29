package demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import demo.entity.Login;

public interface LoginMapper {
	
	@Select("select * from login")
	public List<Login> getAllInfo();
}
