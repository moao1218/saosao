package cn.saosao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value="cn.saosao.mapper")
@SpringBootApplication
public class SaosaoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaosaoProjectApplication.class, args);
	}

}
