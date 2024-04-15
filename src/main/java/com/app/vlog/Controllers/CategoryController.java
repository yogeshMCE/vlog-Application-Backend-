package com.app.vlog.Controllers;

import com.app.vlog.Dto.CategoryDto;
import com.app.vlog.Dto.Response;
import com.app.vlog.Services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // create category
    @PostMapping("/")
    public ResponseEntity<Response>createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1= categoryService.createCategory(categoryDto);
        Response response = new Response(categoryDto1,true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //get all
    @GetMapping("/")
    public ResponseEntity<Response> getAllCategories(){
        List<CategoryDto> categories = categoryService.getAllCategories();
        Response response= new Response(categories,true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //get by id
    @GetMapping("/{category_id}")
    public ResponseEntity<Response>getCategoryById(@PathVariable("category_id")int id){
        CategoryDto categoryDto= categoryService.getCategoryById(id);
        Response response = new Response(categoryDto,true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    // update by id
    @PutMapping("/{category_id}")
    public ResponseEntity<Response>updateCategoryById(@Valid @PathVariable("category_id")int id,@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1= categoryService.updateCategory(categoryDto,id);
        Response response = new Response(categoryDto1,true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{category_id}")
    public ResponseEntity<Response>deleteCategoryById(@PathVariable("category_id")int id){
        categoryService.deleteCategory(id);
        String Message= "category with id :"+id+" have deleted successfully";
        Response response = new Response(Message,true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
