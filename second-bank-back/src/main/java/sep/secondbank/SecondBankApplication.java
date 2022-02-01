package sep.secondbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SecondBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondBankApplication.class, args);
    }

}
