package com.zy.fenbu.subtreasurydemo.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.zy.fenbu.subtreasurydemo.config.bean.PrivateBeanConfig;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


/**
 * private库配置装载
 */
@Configuration
@MapperScan(basePackages = {"com.zy.fenbu.subtreasurydemo.privatedata"}, sqlSessionTemplateRef = "privateSessionTemplate")
public class DatasourcePrivateConfig {
//    正常写法  这样就可以创建数据库链接
//    @Bean(name="privateDataSource")
//    @Qualifier
//    @ConfigurationProperties("private.spring.datasource")
//    public DataSource privateDataSource () {
//        DataSource dataSource = DataSourceBuilder.create().build();
//    }
    @Resource
    private PrivateBeanConfig privateBeanConfig;

    /**
     * DATA_SOURCE_PUBLIC_CONFIG 和此类的原理是一样
     * 1 创建多数据源时，需要加@Primary 指定主要的数据源，否则会报错
     * 2 如果有多数据源，在分层的时候 java 下面的 mapper 要与数据源，以及.xml文件 在目录上能够区分，多个数据源所对应的目录不能
     * 重复，否则会报错
     * 3 如果不用atomikos来统一事务，则用上面注释部分的代码来创建数据源，可以不用写单独的bean层，
     * 4 atomikos 配置数据源的方式 如下
     *      获取atomikos database对象 需要 用到xadataSource 把获取的url 和userName password 配置到 xadataSource
     *      方便atomikos 管理多个数据源的事务
     *      创建 SqlSessionFactory  SqlSessionTemplate 都和平常是一样的创建
     *      事务管理器（DataSourceTransactionManager） 不需要创建 用atomikos 默认就可以了
     *      在service 层 不要try catch ,否则事务无效，把异常throw,(具体原因不明)
     * @return
     */
    @Bean(name="privateDataSource")
    @Qualifier
//    @ConfigurationProperties("private.spring.datasource")
    public DataSource privateDataSource (){

        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(privateBeanConfig.getUrl());
        mysqlXADataSource.setUser(privateBeanConfig.getUsername());
        mysqlXADataSource.setPassword(privateBeanConfig.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);


        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("privateDataSource");

        return xaDataSource;
    }

    @Bean
    public SqlSessionFactory privateSessionFactory(@Qualifier("privateDataSource") DataSource dataSource){
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:/mapper/private/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    @Bean(name = "privateTransactionManager")
//    public DataSourceTransactionManager bank2TransactionManager(@Qualifier("privateDataSource") DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }

    @Bean
    public SqlSessionTemplate privateSessionTemplate(@Qualifier("privateSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
        return template;
    }


}
