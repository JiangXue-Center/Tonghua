package com.hf.artwork.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hf.data.config.DruidDataSourceConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.hf.artwork.mapper.artwork", sqlSessionFactoryRef = "artworkSqlSessionFactory")
public class ArtworkDataSourceConfig {

    @Value("spring.datasource.druid.tonghua-artwork.url")
    private String url;

    @Autowired
    private DruidDataSourceConfig druidDataSourceConfig;

    @Bean(name = "artworkDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.tonghua-artwork")
    @Primary
    public DataSource artworkDataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(druidDataSourceConfig.getUsername());
        datasource.setPassword(druidDataSourceConfig.getPassword());
        datasource.setDriverClassName(druidDataSourceConfig.getDriverClassName());
        datasource.setInitialSize(druidDataSourceConfig.getInitialSize());
        datasource.setMinIdle(druidDataSourceConfig.getMinIdle());
        datasource.setMaxActive(druidDataSourceConfig.getMaxActive());
        datasource.setMaxWait(druidDataSourceConfig.getMaxWait());
        datasource.setMinEvictableIdleTimeMillis(druidDataSourceConfig.getMinEvictableIdleTimeMillis());
        datasource.setTimeBetweenEvictionRunsMillis(druidDataSourceConfig.getTimeBetweenEvictionRunsMillis());
        datasource.setFilters(druidDataSourceConfig.getFilters());
        datasource.setValidationQuery(druidDataSourceConfig.getValidationQuery());
        datasource.setTestOnBorrow(druidDataSourceConfig.isTestOnBorrow());
        datasource.setTestOnReturn(druidDataSourceConfig.isTestOnReturn());
        return datasource;
    }

    @Bean(name = "artworkSqlSessionFactory")
    @Primary
    public SqlSessionFactory artworkSqlSessionFactory(@Qualifier("artworkDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "artworkTransactionManager")
    @Primary
    public DataSourceTransactionManager artworkTransactionManager(@Qualifier("artworkDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "artworkSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate artworkSqlSessionTemplate(@Qualifier("artworkSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
