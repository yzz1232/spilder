package demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import demo.dao.SpliderMapper;
import demo.entity.Splider;
import demo.service.SpliderService;

@Service
public class SpliderServiceImpl implements SpliderService{

	@Autowired
	private SpliderMapper spliderMapper;
	
	@Cacheable("searchSplider")  
	public List<Splider> getAllInfo() {
		return spliderMapper.getAllInfo();
	}
	
	public void insert(){
		
	}
}
