package projectForum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class ProjectForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectForumApplication.class, args);
        System.out.println("[" + new Date() + "] - started up successfully. Do what you have to do.");
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");

        return jpaVendorAdapter;
    }

    private Properties getJpaProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.format_sql", "true");
            }
        };
    }
}

@RestController
class GreetingController {

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
            return "hello, " + name + "!";
    }
}


