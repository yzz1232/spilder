package demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.LoginMapper;
import demo.dao.SpliderMapper;
import demo.entity.Login;
import demo.entity.Splider;
import demo.server.MultipleDataSource;
import demo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private SpliderMapper spliderMapper;
	
	public String test() {
		List<Splider> lagouList = spliderMapper.getAllInfo();
		MultipleDataSource.setDataSourceKey("dataSource2");
		List<Login> loginList = loginMapper.getAllInfo();
		return String.valueOf(loginList.size());
	}

}
