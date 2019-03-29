package com.github.webshop.service;

import com.github.webshop.pojo.Category;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CategoryService {

    List<Category> FindCategoryOnLike(String statement);

    int FindCategoryByName(HttpServletRequest request);

    int AddCategory(HttpServletRequest request);

    int EditCategory(Category category);

    List<Category> getCategoryList(HttpServletRequest request);

    List<Category> getCategoryListWithoutLimit(HttpServletRequest request);

    int getRowCount();

    int deleteCategory(Integer id);
}
