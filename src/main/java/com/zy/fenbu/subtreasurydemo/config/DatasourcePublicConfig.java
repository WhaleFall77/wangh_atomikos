package com.zy.fenbu.subtreasurydemo.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.zy.fenbu.subtreasurydemo.config.bean.PublicBeanConfig;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@MapperScan(basePackages = {"com.zy.fenbu.subtreasurydemo.publicdata"}, sqlSessionTemplateRef = "publicSessionTemplate")
public class DatasourcePublicConfig {

//    @Primary
//    @Bean(name="publicDataSource")
//    @Qualifier
//    @ConfigurationProperties("public.spring.datasource")
//    public DataSource publicDataSource (){
//        return DataSourceBuilder.create().build();
//    }

    @Resource
    private PublicBeanConfig publicBeanConfig;


    @Bean(name="publicDataSource")
    @Qualifier
//    @ConfigurationProperties("public.spring.datasource")
    public DataSource publicDataSource () throws SQLException {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(publicBeanConfig.getUrl());
        mysqlXADataSource.setUser(publicBeanConfig.getUsername());
        mysqlXADataSource.setPassword(publicBeanConfig.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("publicDataSource");

        return xaDataSource;
    }
    @Primary
    @Bean
    public SqlSessionFactory publicSessionFactory(@Qualifier("publicDataSource") DataSource dataSource) throws Exception{
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:/mapper/public/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    @Bean(name = "publicTransactionManager")
//    public DataSourceTransactionManager bank2TransactionManager(@Qualifier("publicDataSource") DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }


    /*
     * 使用这个来做总事务 后面的数据源就不用设置事务了
     *
    */
    @Bean(name = "transactionManager")
    @Primary
    public JtaTransactionManager regTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

    @Bean
    public SqlSessionTemplate publicSessionTemplate(@Qualifier("publicSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
        return template;
    }

}
