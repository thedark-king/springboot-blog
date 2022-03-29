package com.springboot.blog.payload;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(value = "Blog API app signup or login information ")
@Data
public class SignupDto {

    @ApiModelProperty(value = "the app signup user name")
    private String name;

    @ApiModelProperty(value = "the app signup user username")
    private String username;

    @ApiModelProperty(value = "the app signup user email id")
    private String email;

    @ApiModelProperty(value = "the app signup user login password")
    private String password;


}
