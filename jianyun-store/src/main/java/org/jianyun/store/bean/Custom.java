package com.example.demo.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Custom implements Serializable{
    /**
	 * version
	 */
	private static final long serialVersionUID = 1L;
	private Long id ;
    private String custName;
    private String custMobile;
    private Integer gender;
}
