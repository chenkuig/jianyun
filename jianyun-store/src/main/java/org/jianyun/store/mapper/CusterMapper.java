package org.jianyun.store.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.jianyun.store.bean.Custom;

import java.util.List;


@Mapper
public interface CusterMapper {
	List<Custom> getList();
}
