package com.epam.lab.beseda.configuration;

import com.epam.lab.beseda.util.DatabaseConfigure;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:database.properties"})
@ComponentScan({"com.epam.lab.beseda.dao.entitydao","com.epam.lab.beseda.dao.util"})
public class TestConfiguration {


    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        EmbeddedPostgres database = null;
        try {
            database = EmbeddedPostgres.builder().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return database.getPostgresDatabase();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan("com.epam.lab.beseda.entity");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        return factoryBean;
    }

    @Bean
    public EntityManager getEntityManager() {
        EntityManager em=getEntityManagerFactoryBean().getNativeEntityManagerFactory().createEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);
        return em;
    }

    @Bean
    public JpaTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.default_schema",env.getProperty("hibernate.default_schema"));
                setProperty("hibernate.dialect",  env.getProperty("hibernate.dialect"));
                setProperty("hibernate.globally_quoted_identifiers",  "true");
                setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
                setProperty("hibernate.globally_quoted_identifiers", "true");
            }
        };
    }

    @Bean(initMethod = "createEmptyDatabase",destroyMethod = "deleteDatabase")
    public DatabaseConfigure getTestDatabaseConfigure(){
        return new DatabaseConfigure(getDataSource());
    }

}
