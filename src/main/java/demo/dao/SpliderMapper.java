package demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import demo.entity.Splider;

@Component
public interface SpliderMapper {
	
	@Insert("insert into splider(`companyName`,`jobName`,`salary`,`year`,`address`,`require`,`url`) values(#{companyName},#{jobName},#{salary},#{year},#{address},#{require},#{url})")
	int insertSpilder(Splider splider);
	
	@Select("select * from splider limit 1,100")
	List<Splider> getAllInfo();
	
	
}
