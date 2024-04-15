package com.app.vlog.Controllers;

import com.app.vlog.Dto.CommentDto;
import com.app.vlog.Dto.Response;
import com.app.vlog.Services.CommentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity<List<CommentDto>>getAllComments(){
        List<CommentDto>commentDtoList= commentService.getAllComments();
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }
    @GetMapping("/get/{commentId}")
    public ResponseEntity<CommentDto>getCommentById(@PathVariable("commentId") int commentId){
        CommentDto commentDto= commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto,
                                                   @RequestParam("userId") long userId,
                                                   @RequestParam("postId") int postId ){
       CommentDto commentDto1= commentService.createComment(commentDto,postId,userId);
       return new ResponseEntity<>(commentDto1,HttpStatus.CREATED);
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<CommentDto>updateComment(@PathVariable("commentId")int commentId,
                                                   @RequestBody CommentDto commentDto){
        CommentDto commentDto1= commentService.updateCommentById(commentId,commentDto);
        return new ResponseEntity<>(commentDto1,HttpStatus.OK);
    }
    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<List<CommentDto>>getCommentsByUser(@PathVariable("userId")int userId){
        List<CommentDto>commentDtoList= commentService.getCommentsByUser(userId);
        return new ResponseEntity<>(commentDtoList,HttpStatus.OK);
    }
    @GetMapping("/getByPost/{postId}")
    public  ResponseEntity<List<CommentDto>>getCommentsByPost(@PathVariable("postId")int postId){
        List<CommentDto>commentDtoList= commentService.getCommentsByPost(postId);
        return new ResponseEntity<>(commentDtoList,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Response>deleteComment(@PathVariable("commentId")int commentId){
        commentService.deleteCommentById(commentId);
        String Message= "Comment with CommentId :"+commentId+" Has been deleted Successfully";
        return new ResponseEntity<>(new Response(Message,true),HttpStatus.OK);
    }

}
