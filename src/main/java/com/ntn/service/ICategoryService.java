package com.ntn.service;


import com.ntn.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getListCategories();

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(int id);
}
