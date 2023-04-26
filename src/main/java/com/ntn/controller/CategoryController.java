package com.ntn.controller;

import com.ntn.dto.CategoryDTO;
import com.ntn.entity.Category;
import com.ntn.service.ICategoryService;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ModelMapper modelMapper;

    private JSONObject message = new JSONObject();


//    @GetMapping
//    public List<Category> getListCategories(){
//        return categoryService.getListCategories();
//    }

    // Lấy cả bảng liên quan
//    @GetMapping
//    public List<CategoryDTO> getListCategories(){
//
//        List<Category> categories = categoryService.getListCategories();
//        List<CategoryDTO> categoryDTOList=new ArrayList<CategoryDTO>();
//
//        for (Category cate:categories) {
//            CategoryDTO cateDTO= new CategoryDTO(cate.getId(), cate.getCategoryName(), cate.getProducts().toString());
//            categoryDTOList.add(cateDTO);
//            System.out.println(cate);
//        }
//
//        return  categoryDTOList;
//    }

    @GetMapping
    public List<CategoryDTO> getListCategories() {

        List<Category> categories = categoryService.getListCategories();
        List<CategoryDTO> categoryDTOList = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {
        }.getType());
        return categoryDTOList;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        try {
            categoryService.createCategory(category);
            message.put("resultText", "Create Category Successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(message.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateCategory(@RequestParam(name = "id") Integer id, @RequestBody CategoryDTO categoryDTO) {
        try {
//            productDTO.setId(id);
            Category category = modelMapper.map(categoryDTO, Category.class);

            categoryService.updateCategory(category);
            JSONObject message = new JSONObject();
            message.put("resultText", "Update Category Successfully!");

            return ResponseEntity.status(HttpStatus.OK).body(message.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.toString());
        }
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer id) {
        try {

            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body(" The category has been deleted!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.toString());
        }
    }
}
