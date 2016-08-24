package demo.splider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.dao.SpliderMapper;
import demo.entity.Splider;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
@Component
public class PipelineImpl implements Pipeline{
	
	@Autowired
	private SpliderMapper spliderMapper; 
	public void process(ResultItems resultItems, Task task) {
		Splider splider = resultItems.get("lagou");
		spliderMapper.insertSpilder(splider);
	}

}
