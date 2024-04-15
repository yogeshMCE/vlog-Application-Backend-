package com.app.vlog.Repositories;

import com.app.vlog.Models.Category;
import com.app.vlog.Models.Post;
import com.app.vlog.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post>findByCategory(Category category);
    List<Post>findByUser(User user);
    List<Post>findByTitleContaining(String title);
    @Query("select p from Post p where p.title like :key")
    List<Post>searchPost(@Param("key")String keyword);
}
