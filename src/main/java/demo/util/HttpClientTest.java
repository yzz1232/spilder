package demo.util;

import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;

public class HttpClientTest {
	
	private static String url = "https://www.zhihu.com/login/email";
	static HttpClient httpClient = new HttpClient();  
	public static void main(String[] args) throws HttpException, IOException {
		PostMethod postMethod = new PostMethod(url); 
		NameValuePair[] data = {new NameValuePair("email","875930090@qq.com"),new NameValuePair("password","zzy1994,./")};
		postMethod.setRequestBody(data);
		
		httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		System.out.println(httpClient.executeMethod(postMethod));
		Cookie[] cookies = httpClient.getState().getCookies();
		
		
	}
}
