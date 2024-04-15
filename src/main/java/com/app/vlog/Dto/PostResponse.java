package com.app.vlog.Dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostResponse {
   private String SortBy;
   private  String SortDir;
   private int pageNumber;
   private int pageSize;
   private int elementsOnCurrentPage;
   private long totalElements;
   private long totalPages;
   private boolean isLast;
   private List<PostDto> Content;


}
