package com.app.vlog.Services;

import com.app.vlog.Dto.CommentDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface CommentService {

    // get all
    public List<CommentDto>getAllComments();

    // get one by Comment By ID

    public CommentDto getCommentById(Integer commentId);

    // update comment by Comment ID
    public CommentDto updateCommentById(Integer commentId, CommentDto commentDto);

    // delete comment by  Comment ID
    public void deleteCommentById(Integer commentId);

    // create new Comment
    public  CommentDto createComment(CommentDto commentDto,Integer postId, long userId);

    public List<CommentDto> getCommentsByUser(long userId);

    public List<CommentDto>getCommentsByPost(Integer postId);

}
