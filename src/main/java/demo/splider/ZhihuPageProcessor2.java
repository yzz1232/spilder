package demo.splider;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class ZhihuPageProcessor2 implements PageProcessor{

	@Autowired
	private ZhihuPipelineImpl zhihu;
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	
	public void process(Page page) {
		List<String> list = page.getHtml().links().all();
		page.addTargetRequests(list);
		List<String> imgList = page.getHtml().xpath("//img[@class='zm-item-img-avatar']").regex("https://pic\\d.zhimg.com/\\w*.\\w*").all();
		if(imgList == null){
			page.setSkip(true);
		}else{
			page.getResultItems().put("zhihu", imgList);
		}
		System.out.println(list.size());
	}

	public Site getSite() {
		return site;
	}
	
	public static void main(String[] args) throws HttpException, IOException {
		ZhihuPageProcessor2 zz = new ZhihuPageProcessor2();
		 String url = "https://www.zhihu.com/login/email";
		 HttpClient httpClient = new HttpClient();  
		 PostMethod postMethod = new PostMethod(url); 
		NameValuePair[] data = {new NameValuePair("email","875930090@qq.com"),new NameValuePair("password","zhuzhenyu1994")};
		postMethod.setRequestBody(data);
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		System.out.println(httpClient.executeMethod(postMethod));
		Cookie[] cookies = httpClient.getState().getCookies();
		for(int i=0;i<cookies.length;i++){
			zz.site.addCookie(cookies[i].getDomain(),cookies[i].getName(),cookies[i].getValue());
		}
		Spider
		.create(zz)
		.addUrl("https://www.zhihu.com/people/excited-vczh") // https://pic3.zhimg.com/e890b7eba_l.jpg
		.addPipeline(new ZhihuPipelineImpl())
		.thread(5)
		.run();
	}
	
	public  void process() {
		Spider
		.create(new ZhihuPageProcessor2())
		.addUrl("https://www.zhihu.com/people/excited-vczh")
		.addPipeline(zhihu)
		.thread(5)
		.run();
	}
}	
