package com.data.masterandslave;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.data.masterandslave.datasource.DatasourceName;
import com.data.masterandslave.datasource.MSRoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement

@MapperScan("com.data.masterandslave.mapper")
public class QuanxianMasterSlaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuanxianMasterSlaveApplication.class, args);
    }

    @Bean(value = DatasourceName.MASTER)
    @ConfigurationProperties(prefix = "spring.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
//        return new DruidDataSource();
    }

    @Bean(value = DatasourceName.SLAVE1)
    @ConfigurationProperties(prefix = "spring.slave1")
    public DataSource slave1() {
        return DataSourceBuilder.create().build();
//        return new DruidDataSource();
    }

    @Bean(value = DatasourceName.SLAVE2)
    @ConfigurationProperties(prefix = "spring.slave2")
    public DataSource slave2() {
        return DataSourceBuilder.create().build();
//        return new DruidDataSource();
    }

    @Bean
    @Primary
    public MSRoutingDataSource myRoutingDataSource() {
        MSRoutingDataSource routingDataSource = new MSRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatasourceName.MASTER, master());
        targetDataSources.put(DatasourceName.SLAVE1, slave1());
        targetDataSources.put(DatasourceName.SLAVE2, slave2());
        routingDataSource.setDefaultTargetDataSource(master());
        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(MSRoutingDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);//放入数据源
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(myRoutingDataSource());
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/**/*.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }


}
