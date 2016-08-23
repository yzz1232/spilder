package demo.splider;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.example.GithubRepo;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://www.lagou.com/jobs/*")
//@HelpUrl("http://www.lagou.com/zhaopin/\\w+")
public class LaGouEntity {
	
	@ExtractBy(value="//li[@class='con_list_item']/@data-positionName",notNull = true)
	private String jobName;
	@ExtractBy(value="//li[@class='con_list_item']/@data-company",notNull = true)
	private String companyName;
	@ExtractBy(value="//li[@class='con_list_item']/@data-salary",notNull = true)
	private String salay;
	@ExtractBy(value="div[@class='p_bot']/div[@class='li_b_l']/text()",notNull = true)
	private String jobYear;
	@ExtractBy(value="span[@class='add']/em/text()",notNull = true)
	private String area;

	public String getJobYear() {
		return jobYear;
	}

	public void setJobYear(String jobYear) {
		this.jobYear = jobYear;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSalay() {
		return salay;
	}

	public void setSalay(String salay) {
		this.salay = salay;
	}
	
	 public static void main(String[] args) {
	        OOSpider.create(Site.me().setSleepTime(1000),
	                 new ConsolePageModelPipeline(), LaGouEntity.class)
	                .addUrl("http://www.lagou.com/zhaopin/Java").thread(5).run();
	    }
}
