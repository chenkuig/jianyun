package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.Custom;

@Mapper
public interface CusterMapper {
	List<Custom> getList();
}
