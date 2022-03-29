package com.springboot.blog.payload;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(value = "Blog API Login information")
@Data
public class LoginDto {


    @ApiModelProperty(value = "Blog login usernameOrEmail")
    private String userNameOrEmail;

    @ApiModelProperty(value = "Blog login password")
    private String password;
}
