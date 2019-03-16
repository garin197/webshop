package com.github.webshop.service;

import com.github.webshop.pojo.Category;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
public interface CategoryService {

    List<Category> FindCategoryOnLike(String statement);

    int FindCategoryByName(HttpServletRequest request);

    int AddCategory(HttpServletRequest request);

    int EditCategory(Category category);

    List<Category> getCategoryList(HttpServletRequest request);

    int getRowCount();

    int deleteCategory(Integer id);
}
