package org.jianyun.store.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "administrative_limits_info", type = "country_info")
public class Country implements Serializable{
	/**
	 * version
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String code;
	@Field(searchAnalyzer = "ik_max_word",analyzer = "ik_smart" ,type=FieldType.Text)
	private String chineseName;
	private String englishName;
}
