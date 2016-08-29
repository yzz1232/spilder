package demo.splider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.dao.ZhihuMapper;
import demo.entity.Zhihu;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class ZhihuPipelineImpl  implements Pipeline{

	@Autowired
	private ZhihuMapper zhihuMapper; 
	
	public void process(ResultItems resultItems, Task task) {
		List<String> zhihuList = resultItems.get("zhihu");
		for(int i=0;i<zhihuList.size();i++){
			zhihuMapper.insert(zhihuList.get(i));
		}
		System.out.println("zz");
	}
}
