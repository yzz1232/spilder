package demo.splider;

import java.util.List;
import java.util.ArrayList;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class LagouPageProcessor implements PageProcessor{
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	
	public Site getSite() {
		
		return site;
	}
	
	// //div[@class='li_b_l']   环境
	// //span[@class='money']   薪资
	// //div[@class='industry'] 类型
	////div[@class='p_bot']/div[@class='li_b_l']     年限
	//span[@class='add'] 区域
	//span[@class='format-time']  发布时间
	// //h2
	public void process(Page page) {
		/**
		page.putField("money", page.getHtml().xpath("//span[@class='money']/text()").all());
		page.putField("companyName", page.getHtml().xpath("//li[@class='con_list_item']/@data-company").all());
		page.putField("jobName", page.getHtml().xpath("//li[@class='con_list_item']/@data-positionName").all());  
		page.putField("jobYear",page.getHtml().xpath("div[@class='p_bot']/div[@class='li_b_l']/text()").all());
		page.putField("companyName", page.getHtml().xpath("//li[@class='con_list_item']/@data-company").all());
		page.putField("jobName", page.getHtml().xpath("//li[@class='con_list_item']/@data-positionName").all());  
		page.putField("jobYear",page.getHtml().xpath("div[@class='p_bot']/div[@class='li_b_l']/text()").all());
		page.putField("Date", page.getHtml().xpath("span[@class='format-time']/text()").all());
		page.putField("area", page.getHtml().xpath("span[@class='add']/em/text()").all());
		page.putField("huanj", page.getHtml().xpath("div[@class='li_b_l']/text()").all());
		page.putField("type",page.getHtml().xpath("div[@class='industry']/text()").all());
		*/
		
		page.putField("salay",page.getHtml().xpath("//dd[@class='job_request']/p/span[@class='red']/text()"));
		page.putField("year",page.getHtml().xpath("//dd[@class='job_request']/p/span[3]/text()"));
		page.putField("yaoqiu",page.getHtml().xpath("//dd[@class='job_bt']/p").all());
		page.putField("xueli",page.getHtml().xpath("//dd[@class='job_request']/p/span[4]/text()"));
		page.putField("job",page.getHtml().xpath("//dl/dt/h1/@title"));
		page.putField("companyName", page.getHtml().xpath("//dt/a/img/@alt"));
		page.putField("address", page.getHtml().xpath("//div[@class='work_addr']/a[2]/text()"));
		
		List<String> list = page.getHtml().links().regex("(http://www.lagou.com/jobs/\\d+)").all();
		List<String> list2 = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			list2.add(list.get(i)+".html");
		}
		page.addTargetRequests(list2);
	}	   
	
	public static void main(String[] args) {
		Spider
		.create(new LagouPageProcessor())
		//.addUrl("http://www.lagou.com/zhaopin/Java")
		//.addUrl("http://www.lagou.com/zhaopin/Java?px=default&gj=3年及以下&city=杭州&district=余杭区#filterBox")
		.addUrl("http://www.lagou.com/jobs/2183908.html")
		//.addPipeline(new JsonFilePipeline("D:\\webmagic"))
		.thread(5)
		.run();
	}

}
