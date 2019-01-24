package org.jianyun.store;

import java.util.List;

import org.jianyun.store.bean.Country;
import org.jianyun.store.respfores.CountrySearchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * Unit test for simple App.
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j*/
public class AppTest {
   /*@Autowired
   private CountrySearchRepository countrySearchRepository;
   @Test
   void findByName() {
	   List<Country> list = countrySearchRepository.findByChineseName("xian");
	   if (!CollectionUtils.isEmpty(list)) {
		   for (Country country : list) {
			   log.info(JSON.toJSONString(country));
		   }
	   }
   }*/
}
