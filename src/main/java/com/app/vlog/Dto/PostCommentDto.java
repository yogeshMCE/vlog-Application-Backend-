package com.app.vlog.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostCommentDto {
    private int postId;
    private Date AddedDate;

    @NotEmpty
    @Size(min=10, max=1000)
    private String content;

    @NotEmpty
    @Size(min=50, max=100)
    private String title;

    private String file;
    private CategoryDto category;
}
