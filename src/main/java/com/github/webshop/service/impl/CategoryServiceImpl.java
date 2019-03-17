package com.github.webshop.service.impl;

import com.github.webshop.dao.CategoryMapper;
import com.github.webshop.pojo.Category;
import com.github.webshop.pojo.Row;
import com.github.webshop.service.CategoryService;
import com.github.webshop.util.RowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 分类服务类
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoryListWithoutLimit(HttpServletRequest request) {
        return categoryMapper.findAll();
    }

    @Override
    public int FindCategoryByName(HttpServletRequest request) {
        String name=request.getParameter("categoryName");
        return categoryMapper.findByName(name);
    }

    @Override
    public List<Category> FindCategoryOnLike(String statement) {
        statement="%"+statement+"%";
        return categoryMapper.findLikeName(statement);
    }

    @Override
    public int AddCategory(HttpServletRequest request) {
        String name=request.getParameter("categoryName");
        Category category=new Category();
        category.setCategoryName(name);
        return categoryMapper.add(category);
    }

    @Override
    public int EditCategory(Category category) {
        return categoryMapper.update(category);
    }

    @Override
    public List<Category> getCategoryList(HttpServletRequest request) {
        Row row= RowUtil.getRow(request);

        return categoryMapper.findWithLimit(row);
    }

    @Override
    public int deleteCategory(Integer id) {
        return categoryMapper.delete(id);
    }

    @Override
    public int getRowCount() {
        return categoryMapper.total();
    }
}
