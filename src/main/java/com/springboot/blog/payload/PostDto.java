package com.springboot.blog.payload;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Api(value = "Post Model information")
@Data
public class PostDto {


    @ApiModelProperty(value = "Blog post id")
    private long id;

    @ApiModelProperty(value = "Blog post Title")
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @ApiModelProperty(value = "Blog post description ")
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 chatacters")
    private String description;

    @ApiModelProperty(value = "Blog post content")
    @NotEmpty
    private String content;

    @ApiModelProperty(value = "Blog post comment")
    private Set<CommentDto> comments;

}
