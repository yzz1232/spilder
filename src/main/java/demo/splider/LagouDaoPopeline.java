package demo.splider;

import org.springframework.beans.factory.annotation.Autowired;

import demo.dao.SpliderMapper;
import demo.entity.Splider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

public class LagouDaoPopeline implements PageModelPipeline<Splider> {

	@Autowired
	private SpliderMapper spliderMapper;
	
	public void process(Splider t, Task task) {
		spliderMapper.insertSpilder(t);
	}

}
