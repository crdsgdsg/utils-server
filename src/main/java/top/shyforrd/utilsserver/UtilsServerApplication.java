package top.shyforrd.utilsserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"top.shyforrd.utilsserver.mapper"})
public class UtilsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UtilsServerApplication.class, args);
    }

}
