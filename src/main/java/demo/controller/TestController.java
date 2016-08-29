package demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import demo.dao.LoginMapper;
import demo.dao.SpliderMapper;
import demo.dao.ZhihuMapper;
import demo.entity.Login;
import demo.entity.Splider;
import demo.entity.Zhihu;
import demo.server.MultipleDataSource;
import demo.service.LoginService;
import demo.service.SpliderService;
import demo.service.impl.SpringContextHelper;
import demo.splider.LagouPageProcessor;
import demo.splider.ZhihuPageProcessor2;
import demo.util.GetImage;

@Controller
public class TestController {
	
	@Autowired
	private LagouPageProcessor lagou;
	
	@Autowired
	private ZhihuPageProcessor2 zhihu;
	
	@Autowired
	private SpliderMapper spliderMapper;
	
	@Autowired
	private SpliderService spliderService;
	
	@Autowired
	private ZhihuMapper zhihuMapper;
	
	@Autowired
	private SpringContextHelper context;
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping("/hello")
	@ResponseBody
	public String test(){
		
		return loginService.test();
	}
	
	@RequestMapping("/getAllInfo")
	@ResponseBody
	public List<Splider> getAllInfo(){
		List<Splider> list = spliderService.getAllInfo();
		return list;
	}
	
	@RequestMapping("/index")
	@ResponseBody
	public String toIndex(){
		DriverManagerDataSource d = (DriverManagerDataSource)context.Bean("DrivenManagerDataSource");
		
		return d.getUrl();
	}
	
	@RequestMapping("/img")
	public void getImg() throws Exception{
		GetImage getImag = new GetImage();
		List<Zhihu> list = zhihuMapper.getAllInfo();
		for(int i=0;i<list.size();i++){
			getImag.setImg(list.get(i).getUrl());
		}
		System.out.println(list.size());
	}
	
	@RequestMapping("/filesUpload")
	public void fileUpload(HttpServletRequest request, @RequestParam("files")  MultipartFile[] files) throws IllegalStateException, IOException{
		for(int i=0;i<files.length;i++){
			String filePath = "D:\\file\\" + files[i].getOriginalFilename();
			files[i].transferTo(new File(filePath));
			
		}
	}
}
