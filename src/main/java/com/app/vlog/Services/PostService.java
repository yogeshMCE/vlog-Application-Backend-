package com.app.vlog.Services;

import com.app.vlog.Dto.PostDto;
import com.app.vlog.Dto.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    // create
    public PostDto createPost(PostDto postDto, long user_id, Integer category_id);

    //update post
    public PostDto updatePost(PostDto postDto, Integer post_id) throws IOException;

    // get All Post
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get Post By id
   public PostDto getPostById(Integer post_id);

    // delete post by id
    public void deletePostById(Integer post_id);
    // get posts by user
    List<PostDto> getPostByUser(long user_id);

    // get post by category
    List<PostDto>getPostByCategory(int category_id);

    List<PostDto> searchPost(String keyword);
}
