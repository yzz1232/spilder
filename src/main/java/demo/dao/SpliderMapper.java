package demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

import demo.entity.Splider;

@Component
public interface SpliderMapper {
	
	@Insert("insert into splider(`companyName`,`jobName`,`salary`) values(#{companyName},#{jobName},#{salary})")
	int insertSpilder(Splider splider);
}
