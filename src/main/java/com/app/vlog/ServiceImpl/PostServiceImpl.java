package com.app.vlog.ServiceImpl;

import com.app.vlog.Dto.CategoryDto;
import com.app.vlog.Dto.PostDto;
import com.app.vlog.Dto.PostResponse;
import com.app.vlog.Exceptions.ResourseNotFoundException;
import com.app.vlog.Models.Category;
import com.app.vlog.Models.Post;
import com.app.vlog.Models.User;
import com.app.vlog.Repositories.CategoryRepo;
import com.app.vlog.Repositories.PostRepo;
import com.app.vlog.Repositories.UserRepo;
import com.app.vlog.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto, long user_id, Integer category_id) {
        User user= userRepo.findById(user_id).orElseThrow(()->new ResourseNotFoundException("user","user_id",user_id));

        Category category= categoryRepo.findById(category_id).orElseThrow(()->new ResourseNotFoundException("category","category_id",category_id));
            Post post= modelMapper.map(postDto,Post.class);
            post.setUser(user);
            post.setCategory(category);
            System.out.println(post);
            Post savedpost=postRepo.save(post);
        return modelMapper.map(savedpost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer post_id) throws IOException {
        Post post=postRepo.findById(post_id).orElseThrow(()->new ResourseNotFoundException("Post","Post_id",post_id));
        post.setFile(postDto.getFile());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post savedPost= postRepo.save(post);
        return modelMapper.map(savedPost,PostDto.class);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending() :Sort.by(sortBy).descending();
        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
       Page<Post> pageList= postRepo.findAll(p);
       List<Post>postList= pageList.getContent();
       List<PostDto> postDtoList= postList.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
       PostResponse postResponse= new PostResponse();
       postResponse.setContent(postDtoList);
       postResponse.setPageNumber(pageList.getNumber());
       postResponse.setPageSize(pageList.getSize());
       postResponse.setElementsOnCurrentPage(pageList.getNumberOfElements());
       postResponse.setTotalElements(pageList.getTotalElements());
       postResponse.setLast(pageList.isLast());
       postResponse.setTotalPages(pageList.getTotalPages());
       postResponse.setSortBy(sortBy);
       postResponse.setSortDir(sortDir);
       return postResponse;
    }

    @Override
    public PostDto getPostById(Integer post_id) {
        Post post=postRepo.findById(post_id).orElseThrow(()->new ResourseNotFoundException("Post","Post_id",post_id));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public void deletePostById(Integer post_id) {
        Post post=postRepo.findById(post_id).orElseThrow(()->new ResourseNotFoundException("Post","Post_id",post_id));
         postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostByUser(long user_id) {
        User user= userRepo.findById(user_id).orElseThrow(()->new ResourseNotFoundException("User","User_id",user_id));
        List<Post>postList= postRepo.findByUser(user);
        if(postList.isEmpty()){
            throw  new ResourseNotFoundException("Posts","User_id",user_id);
        }
        return postList.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByCategory(int category_id) {
        Category category= categoryRepo.findById(category_id).orElseThrow(()->new ResourseNotFoundException("category","category_id",category_id));
        List<Post>postList= postRepo.findByCategory(category);
        if(postList.isEmpty()){
            throw  new ResourseNotFoundException("Posts","category_id",category_id);
        }

        return postList.stream().map(post -> modelMapper.map(post,PostDto.class))
                                .collect(Collectors.toList());

    }

    @Override
    public List<PostDto> searchPost(String keyword) {
           List<Post>  postList= postRepo.searchPost("%"+keyword+"%");
           return postList.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }
}
