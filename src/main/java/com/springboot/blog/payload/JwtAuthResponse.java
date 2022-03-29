package com.springboot.blog.payload;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(value = "JWT Auth Response Information")
public class JwtAuthResponse {

    @ApiModelProperty(value = "JWT Auth accesstoken")
    private String accessToken;
    @ApiModelProperty(value = "JWT Auth value")
    private String tokenType = "Bearer";

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
