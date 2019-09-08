package com.eyison.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author zl
 * @version 1.0
 * @email 815438426@qq.com
 * @copyright: Copyright (c) eyison
 */

@Configuration
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = "com.eyison.repository.t1", entityManagerFactoryRef = "t1EntityManager")
public class T1DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.t1")
    public DataSource t1DataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean t1EntityManager(TransactionManager transactionManager, UserTransaction userTransaction) throws Throwable {
        AtomikosJtaPlatform.setTransactionManager(transactionManager);
        AtomikosJtaPlatform.setUserTransaction(userTransaction);

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(t1DataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.eyison.model.t1");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }


}
