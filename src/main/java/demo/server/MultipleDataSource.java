package demo.server;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource{

	private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();
	private static final String defaultTargetDataSource = "dataSource";
	

    public static void setDataSourceKey(String dataSourceName) {
        dataSourceKey.set(dataSourceName);
    }
    
    public static String getDataSourceKey() {
    	String dataSourceName = dataSourceKey.get();
		return dataSourceName == null ? defaultTargetDataSource : dataSourceName;
    }

	@Override
	protected Object determineCurrentLookupKey() {
		String dataSourceName = dataSourceKey.get();
		return dataSourceName == null ? defaultTargetDataSource : dataSourceName;
	}
	
}
