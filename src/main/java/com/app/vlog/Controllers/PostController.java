package com.app.vlog.Controllers;

import com.app.vlog.Config.AppConstants;
import com.app.vlog.Dto.PostDto;
import com.app.vlog.Dto.PostResponse;
import com.app.vlog.Dto.Response;
import com.app.vlog.Services.FileService;
import com.app.vlog.Services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    // create post
    @PostMapping("/create")
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto, @RequestParam("user_id") long user_id,@RequestParam("category_id") Integer category_id){
        PostDto postDto1= postService.createPost(postDto,user_id,category_id);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }
    @PutMapping("/update/{post_id}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable("post_id") int post_id) throws IOException {
        PostDto postDto1= postService.updatePost(postDto,post_id);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }
    @GetMapping("/getByCategory/{category_id}")
    public ResponseEntity<List<PostDto>> getAllByCategory(@PathVariable("category_id")int category_id){
        List<PostDto>postDtoList= postService.getPostByCategory(category_id);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @GetMapping("/getByUser/{user_id}")
    public ResponseEntity<List<PostDto>>getAllByUser(@PathVariable("user_id")long user_id){
        List<PostDto>postDtoList= postService.getPostByUser(user_id);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @GetMapping("/getPost/{post_id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable("post_id")int post_id){
        PostDto postDto= postService.getPostById(post_id);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @GetMapping("/getAllPost")
    public ResponseEntity<PostResponse>getAllPost(@RequestParam(value = "page_size",defaultValue =AppConstants.defaultPageSize,required = false)Integer pageSize,
                                                  @RequestParam(value = "page_number",defaultValue = AppConstants.defaultPageNumber,required = false) Integer pageNumber,
                                                  @RequestParam(value = "sortBy",defaultValue = AppConstants.defaultSortBy,required = false) String sortBy,
                                                  @RequestParam(value = "sortDir",defaultValue = AppConstants.defaultSortDir,required = false)String sortDir) {
        PostResponse postResponse= postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{post_id}")
    public ResponseEntity<Response> deletePost(@PathVariable("post_id") int post_id){
        postService.deletePostById(post_id);
        String Message="Post with Post_id :"+post_id+"have been deleted successfully";
        Response response= new Response(Message,true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>>searchPost(@PathVariable("keyword")String keyword){
        List<PostDto>postDtoList= postService.searchPost(keyword);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @PostMapping("/upload-image/{post_id}")
    public  ResponseEntity<PostDto>uploadImage(@PathVariable("post_id")int post_id, @RequestParam("image")MultipartFile image) throws IOException {
         PostDto postDto= postService.getPostById(post_id);
         String fileName= fileService.uploadImage(path,image);
         postDto.setFile(fileName);
         PostDto updatePost= postService.updatePost(postDto,post_id);
         return new ResponseEntity<>(updatePost,HttpStatus.OK);

    }
    @GetMapping(value = "/download-image/{image_name}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("image_name")String fileName, HttpServletResponse response) throws IOException {
        InputStream resource= fileService.getResourse(path,fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }
}
