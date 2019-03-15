package com.github.webshop.controller;

import com.github.webshop.pojo.Category;
import com.github.webshop.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    private Logger logger = Logger.getLogger(CategoryController.class);

    @ResponseBody
    @PostMapping("/add")
    public Map<String, Object> t(HttpServletRequest request) throws Exception {
        logger.info("后台-添加分类");
        request.setCharacterEncoding("utf-8");
        int n = categoryService.AddCategory(request);
        Map<String, Object> map = new HashMap<>();
        int flag = 0;
        String message = "成功";
        if (n <= 0) {
            flag = 2;
            message = "失败";
        }
        map.put("status", flag);
        map.put("message", message);
        return map;
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(HttpServletRequest request) throws Exception {
        logger.info("后台-添加分类");
        request.setCharacterEncoding("utf-8");
        List<Category> list = categoryService.getCategoryList(request);
        Map categoryResult = new HashMap<String, Object>();
        categoryResult.put("code", 0);
        categoryResult.put("message", "");
        categoryResult.put("count", categoryService.getRowCount());
        categoryResult.put("data", list);
        return categoryResult;
    }

    @ResponseBody
    @PostMapping("/del")
    public Integer del(@RequestParam(value = "id") Integer id) {
        return categoryService.deleteCategory(id);
    }

    @ResponseBody
    @PostMapping("/edit")
    public Integer edit(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        String categoryName = request.getParameter("categoryName");
        String categoryId = request.getParameter("categoryId");
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryId(new Integer(categoryId));
        int i = categoryService.EditCategory(category);
        return i;
    }

    @ResponseBody
//    @PostMapping("/search")
    @RequestMapping("/search")
    public Map<String,Object> search(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        String statement = request.getParameter("statement");
        Map<String,Object> result=new HashMap<>();
        result.put("code",0);
        result.put("message","");
        List list=categoryService.FindCategory(statement);
        result.put("count",list.size());
        result.put("data",list);
        return result;
    }
}
