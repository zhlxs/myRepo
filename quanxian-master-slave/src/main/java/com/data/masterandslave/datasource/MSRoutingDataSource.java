package com.data.masterandslave.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MSRoutingDataSource extends AbstractRoutingDataSource {
    /*
    * 决定查找key的逻辑，路由DataSource通过此方法获得DataSource的名字，这个逻辑是我们自己实现的，而该类会自动通过这个名字拿到DataSource
    * */
    @Override
    protected Object determineCurrentLookupKey() {
        String s = DBContextHolder.get();
        return s;
    }
}
