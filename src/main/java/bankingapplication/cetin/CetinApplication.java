package bankingapplication.cetin;

import bankingapplication.cetin.dao.AccountRepository;
import bankingapplication.cetin.entity.Account;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CetinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CetinApplication.class, args);
    }

    @Autowired
    private AccountRepository accountRepository;

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            Account account1=new Account(1L,"cetin",1000d);
            Account account2=new Account(2L,"polina",1000d);
            accountRepository.save(account1);
            accountRepository.save(account2);
        };}
}
