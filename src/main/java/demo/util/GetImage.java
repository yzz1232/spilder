package demo.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class GetImage {
	
	public static void main(String[] args) throws Exception {
		String url = "https://pic1.zhimg.com/3a6c25ac3864540e80cdef9bc2a73900_s.jpg";
		String[] zz = url.split("(\\.)");
		System.out.println(zz.length);
	}
	
	public void setImg(String url) throws Exception{
		byte[] img = getImageFromNetByUrl(url);
		String[] zz = url.split("\\.\\w+");
		writeImageToDisk(img,String.valueOf(new Date().getTime())+"."+url.split("\\.")[3]);
	}
	
	public static byte[] getImageFromNetByUrl(String strUrl) throws Exception{
		URL url = new URL(strUrl);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5*1000);
		InputStream instream = conn.getInputStream();
		byte[] btImg = readInputStream(instream);
		return btImg;	
	}
	
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len=inStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	
	public static void writeImageToDisk(byte[] img,String fileName) throws Exception {
		System.out.println("D:" +File.separator +"img"+File.separator+ fileName);
		File file = new File("D:" +File.separator +"img"+File.separator+ fileName);
		FileOutputStream fops = new FileOutputStream(file);
		fops.write(img);
		fops.flush();
		fops.close();
	}
	
}
