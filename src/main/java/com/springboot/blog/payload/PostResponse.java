package com.springboot.blog.payload;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Api(value = "Blog App Post response information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    @ApiModelProperty(value = "Post API response content")
    private List<PostDto> content;

    @ApiModelProperty(value = "Post API response current pageNo")
    private int pageNo;

    @ApiModelProperty(value = "Post API response total page size")
    private int pageSize;
    @ApiModelProperty(value = "Post API response total elements")
    private long totalElement;

    @ApiModelProperty(value = "Post API response total pages")
    private int totalPages;

    @ApiModelProperty(value = "Post API response is lastpage")
    private boolean last;
}
