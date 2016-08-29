package demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import demo.entity.Zhihu;

public interface ZhihuMapper {
	
	@Insert("insert into zhihu(`url`) values(#{url})")
	int insert(String url);
	
	@Select("select * from zhihu")
	public List<Zhihu> getAllInfo();
}
