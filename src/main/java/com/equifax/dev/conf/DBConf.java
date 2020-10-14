package com.equifax.dev.conf;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DBConf {

    @Value("${org.hibernate.dialect.Dialect}")
    private String hibernateDialect;
    @Value("${hibernate.show.sql}")
    private String hibernateShowSql;
    @Value("${spring.datasource.username}")
    private String usuario;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.port}")
    private String port;
    @Value("${spring.datasource.url}")
    private String url;
   @Value("${spring.datasource.driverClassName}")
   private String driver;

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory(DataSource dataSource) throws Exception {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(true);
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan("com.equifax.dev");
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(getHibernateProperties());
        factoryBean.afterPropertiesSet();
        SessionFactory sf = factoryBean.getObject();
        return sf;
    }
    public Properties getHibernateProperties()
    {
        Properties properties = new Properties();
        properties.put("org.hibernate.dialect.Dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        properties.put("hibernate.username", usuario);
        properties.put("hibernate.password", password);
        properties.put("hibernate.port", port);
        properties.put("hibernate.url", url);
        properties.put("hibernate.driverClassName", driver);
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory)
    {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
        return hibernateTemplate;
    }

}
