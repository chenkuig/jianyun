package org.jianyun.store.respfores;

import java.util.List;

import org.jianyun.store.bean.Country;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CountrySearchRepository extends ElasticsearchRepository<Country, String>{
	List<Country> findByChineseName(String chineseName);
}
