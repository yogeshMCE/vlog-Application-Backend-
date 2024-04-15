package com.app.vlog.ServiceImpl;

import com.app.vlog.Dto.CategoryDto;
import com.app.vlog.Exceptions.ResourseNotFoundException;
import com.app.vlog.Models.Category;
import com.app.vlog.Repositories.CategoryRepo;
import com.app.vlog.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category= modelMapper.map(categoryDto,Category.class);
         category = categoryRepo.save(category);
         return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer category_id) {
        Category category= categoryRepo.findById(category_id)
                .orElseThrow(()->new ResourseNotFoundException("category","category_id",category_id));
        Category category1= modelMapper.map(categoryDto,Category.class);
          category1.setCategory_id(category_id);
          Category category2= categoryRepo.save(category1);
          return modelMapper.map(category2,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer category_id) {
         Category category= categoryRepo.findById(category_id)
                 .orElseThrow(()->new ResourseNotFoundException("category","category_id",category_id));
         categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer category_id) {
        Category category= categoryRepo.findById(category_id)
                .orElseThrow(()->new ResourseNotFoundException("category","category_id",category_id));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category>categoryList= categoryRepo.findAll();
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }
}
