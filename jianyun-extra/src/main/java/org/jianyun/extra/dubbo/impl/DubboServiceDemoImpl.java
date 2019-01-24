package org.jianyun.extra.dubbo.impl;

import org.jianyun.extra.dubbo.DubboServiceDemo;

import com.alibaba.dubbo.config.annotation.Service;
@Service(version="1.0.0")
public class DubboServiceDemoImpl implements DubboServiceDemo{

	@Override
	public String sayHello() {
		return String.format("[%s] : Hello", "welcome to dubbo");
	}

}
