package org.jianyun.api;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jianyun.producer.kafka.ProducerTest;
import org.jianyun.store.bean.Country;
import org.jianyun.store.respfores.CountrySearchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={JianyunApplication.class, AppTest.class})
@Slf4j
public class AppTest {
	    @Autowired
	    private JavaMailSender mailSender;
	    @Autowired
	    private CountrySearchRepository countrySearchRepository;
	    @Autowired
	    ProducerTest producerTest;
	    @Value("${kafka.topic.topicname}")
	    private String topic ;
	    @Value("${kafka.topic.groupid}")
	    private String groupId;
	    @Test
	    public void sendSimpleMail() throws Exception {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("xxxxx@qq.com");
	        message.setTo("xxxxx9@qq.com");
	        message.setSubject("主题：简单邮件");
	        message.setText("测试邮件内容");
	        mailSender.send(message);
	    }
	   
	    @Test
	    public  void findByName() throws Exception{
	 	   List<Country> list = countrySearchRepository.findByChineseName("中 国");
	 	   if (!CollectionUtils.isEmpty(list)) {
	 		   for (Country country : list) {
	 			   log.info(JSON.toJSONString(country));
	 		   }
	 	   }
	    }
	    @Test
	    public  void insertCountry() throws Exception{
	    	   Country country = new Country();
	    	   country.setChineseName("中国");
	    	   country.setEnglishName("china");
	    	   country.setCode("001");
		 	   countrySearchRepository.save(country);
		}
	    @Test
		public void pushMessage(){
	    	producerTest.sendMessage(topic, "welcome to kafka!");
	    }
}
