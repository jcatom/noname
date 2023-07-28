package cc.jml1024.noname.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Evil
 */
@Configuration
@MapperScan(basePackages = "cc.jml1024.noname.mapper")
public class MybatisConfig {
}
