package com.app.vlog.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentPostDto {
    private int commentId;
    private String content;
    private Date AddedDate;
    private UserDto user;
}
