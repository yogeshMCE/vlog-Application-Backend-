package com.app.vlog.Dto;

import com.app.vlog.Models.Category;
import com.app.vlog.Models.Comment;
import com.app.vlog.Models.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {

    private int postId;
    private Date AddedDate;

    @NotEmpty
    @Size(min=10, max=1000)
    private String content;

    @NotEmpty
    @Size(min=50, max=100)
    private String title;

    private String file;

    private UserDto user;

    private CategoryDto category;

    private List<CommentPostDto>comments;


}
