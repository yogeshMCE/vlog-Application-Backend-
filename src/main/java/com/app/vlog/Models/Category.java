package com.app.vlog.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;
    @Column(name ="title",nullable = false,unique = true)
    private String category_title;
    @Column(name = "description",length = 100)
    private String category_description;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts;

}
