package tsuyoyo.fikaflag

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan
class FikaFlagApplication {

    static void main(String[] args) {
        SpringApplication.run FikaFlagApplication, args
    }
}
