package demo.splider;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class ZhihuPageProcessor implements PageProcessor{

	
	private Site site = Site.me()
						/**
						.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0")
						//.setDomain("zhuhu.com")
						.addCookie(".zhuhu.com", "login", "M2M3ZDJlZGYwNTU0NDc1OWEwNzdiOTY0MjQ5ZDZlZGQ=|1472094707|84efba10952b839fd9a2c7feda9fc1804f87018e")
						.addCookie(".zhuhu.com", "q_c1","6d6d8f4d6fdb4118a520c395e381567a|1472094693000|1472094693000")
						.addCookie(".zhuhu.com", "z_c0","Mi4wQUFBQW44Y2lBQUFBRUVCelNQOXZDaGNBQUFCaEFsVk44LTdsVndDYV9haTAxWUlRYWJldzhZWmduUlVENHVTNWx3|1472094751|13f65c13b95fd7128ef5652b5930939fc05407c8")
						.addCookie("_xsrf","f36076fb095ebb5fcb8daf090112864a")
						.addCookie("_za","7e676b71-0b27-4f68-85b2-4e558faa4887")
						.addCookie(".zhuhu.com", "a_t", "2.0AAAAn8ciAAAAEEBzSP9vChcAAABhAlVN8-7lVwCa_ai01YIQabew8YZgnRUD4uS5lw")
						.addCookie(".zhuhu.com", "d_c0","AHAAJisAcAqPTgAQtaB-DNj_GmwUl5eN21o=|1472094928")
						.addCookie(".zhuhu.com", "_zap","3e07dd5f-ae84-4e4c-b4da-0dd00908c761")
						.addCookie(".zhuhu.com","__utma","51854390.1051483914.1472094995.1472094995.1472094995.1")
						.addCookie(".zhuhu.com","__utmb","51854390.4.10.1472094995")
						.addCookie(".zhuhu.com","__utmc","51854390")
						.addCookie(".zhuhu.com", "__utmz","51854390.1472094995.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)")
						.addCookie(".zhuhu.com","__utmv","51854390.100-1|2=registration_date=20131222=1^3=entry_date=20131222=1")
						.addCookie(".zhuhu.com","__utmt", "1")
						*/
						.setRetryTimes(3).setSleepTime(1000);
	
	
	public Site getSite() {
		
		return site;
	}
	
	public void process(Page page) {
		//List<String> list = page.getHtml().xpath("//a[@class='question_link']").all();
		List<String> list = page.getHtml().links().regex("(https://www.zhihu.com/question/\\d+)").all();
		page.addTargetRequests(list);
		page.putField("title", page.getHtml().xpath("//span[@class='zm-editable-content']/text()").toString());
		List<String> contentList =  page.getHtml().xpath("//div[@class='zm-editable-content clearfix']").all();
		List<String> authorList = page.getHtml().xpath("//a[@class='author-link']/text()").all();
		List<String> countList = page.getHtml().xpath("//span[@class='count']/text()").all();
		if(page.getResultItems().get("title")==null){
			page.setSkip(true);
		}
		
	}
	
	public static void main(String[] args) throws HttpException, IOException {
		ZhihuPageProcessor zz = new ZhihuPageProcessor();
		 String url = "https://www.zhihu.com/login/email";
		 HttpClient httpClient = new HttpClient();  
		 PostMethod postMethod = new PostMethod(url); 
		NameValuePair[] data = {new NameValuePair("email","XXX"),new NameValuePair("password","XX")};
		postMethod.setRequestBody(data);
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		System.out.println(httpClient.executeMethod(postMethod));
		Cookie[] cookies = httpClient.getState().getCookies();
		for(int i=0;i<cookies.length;i++){
			zz.site.addCookie(cookies[i].getDomain(),cookies[i].getName(),cookies[i].getValue());
		}
		Spider
		.create(zz)
		.addUrl("https://www.zhihu.com")
		.addPipeline(new ConsolePipeline())
		.thread(5)
		.run();
	}

	
	
}
