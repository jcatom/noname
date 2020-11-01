package cc.jml1024.subdragon.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "cc.jml1024.subdragon.mapper")
public class MybatisConfig {
}
