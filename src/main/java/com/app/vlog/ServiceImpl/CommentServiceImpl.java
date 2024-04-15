package com.app.vlog.ServiceImpl;

import com.app.vlog.Dto.CommentDto;
import com.app.vlog.Exceptions.ResourseNotFoundException;
import com.app.vlog.Models.Comment;
import com.app.vlog.Models.Post;
import com.app.vlog.Models.User;
import com.app.vlog.Repositories.CommentRepo;
import com.app.vlog.Repositories.PostRepo;
import com.app.vlog.Repositories.UserRepo;
import com.app.vlog.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Override
    public List<CommentDto> getAllComments() {
       List<Comment>commentList= commentRepo.findAll();
       return commentList.stream().map((comment->modelMapper.map(comment,CommentDto.class))).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {
        Comment comment= commentRepo.findById(commentId)
                                    .orElseThrow(()->new ResourseNotFoundException("Comment","Comment_Id",commentId));

        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto updateCommentById(Integer commentId, CommentDto commentDto) {
        Comment comment= commentRepo.findById(commentId)
                                    .orElseThrow(()->new ResourseNotFoundException("Comment","Comment_Id",commentId));

        comment.setContent(commentDto.getContent());
        Comment updatedComment= commentRepo.save(comment);
        return modelMapper.map(updatedComment,CommentDto.class);

    }

    @Override
    public void deleteCommentById(Integer commentId) {
        Comment comment= commentRepo.findById(commentId)
                .orElseThrow(()->new ResourseNotFoundException("Comment","Comment_Id",commentId));
        commentRepo.delete(comment);
    }

    @Override
    public List<CommentDto> getCommentsByUser(long userId) {
        User user= userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("User","userId",userId));
        List<Comment>commentList= commentRepo.findByUser(user);
        return commentList.stream().map(comment -> modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentsByPost(Integer postId) {
        Post post= postRepo.findById(postId).orElseThrow(()-> new ResourseNotFoundException("Post","postId",postId));
        List<Comment>commentList= commentRepo.findByPost(post);
        return commentList.stream().map(comment -> modelMapper.map(comment,CommentDto.class)).collect(Collectors.toList());

    }

    @Override
    public CommentDto createComment(CommentDto commentDto,Integer postId,long userId) {
        User user= userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("User","userId",userId));
        Post post= postRepo.findById(postId).orElseThrow(()-> new ResourseNotFoundException("Post","postId",postId));
        Comment comment= modelMapper.map(commentDto,Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment SavedComment= commentRepo.save(comment);
        return modelMapper.map(SavedComment,CommentDto.class);

    }
}
