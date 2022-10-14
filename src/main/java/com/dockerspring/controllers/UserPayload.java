package com.dockerspring.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPayload {
    private String name;
    private String email;
    private String password;
    private Integer age;
}
