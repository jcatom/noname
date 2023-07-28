package cc.jml1024.noname;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Evil
 */
@SpringBootApplication
//@ComponentScan({"cc.jml1024.noname", "cc.jml1024.spring"})
public class SubDragonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubDragonApplication.class, args);
    }

}
