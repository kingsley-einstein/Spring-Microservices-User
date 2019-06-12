package com.spring.microservices.client.model;

import java.util.Date;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class Profile implements java.io.Serializable {
    private String name;
    private Date dob;
    private String occupation;

    public Profile() {}
}