package cn.saosao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.saosao.pojo.Weather;
import cn.saosao.util.WeatherUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaosaoProject1ApplicationTests {

	@Test
	public void contextLoads() {
		List<Weather> checkWeather = WeatherUtil.checkWeather("广州");
	}

}
