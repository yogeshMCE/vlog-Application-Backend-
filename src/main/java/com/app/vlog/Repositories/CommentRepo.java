package com.app.vlog.Repositories;

import com.app.vlog.Models.Comment;
import com.app.vlog.Models.Post;
import com.app.vlog.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findByPost(Post post);
    List<Comment>findByUser(User user);

}
