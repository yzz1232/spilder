package demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.dao.SpliderMapper;
import demo.entity.Splider;
import demo.splider.LagouPageProcessor;

@Controller
public class TestController {
	
	
	@Autowired
	private LagouPageProcessor lagou;
	
	@Autowired
	private SpliderMapper spliderMapper;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String test(){
		Map<String,Object> map = new HashMap<String,Object>();
		lagou.process();
		return "hello";
	}
	
	@RequestMapping("/getAllInfo")
	@ResponseBody
	public List<Splider> getAllInfo(){
		List<Splider> list = spliderMapper.getAllInfo();
		return list;
	}
	
	@RequestMapping("/index")
	public String toIndex(){
		return "test";
	}
}
