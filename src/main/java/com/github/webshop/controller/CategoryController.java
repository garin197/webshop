package com.github.webshop.controller;

import com.github.webshop.pojo.Category;
import com.github.webshop.service.CategoryService;
import com.github.webshop.util.HashMapUtil;
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
        int flag = 0;
        String message = "成功";
        Map<String, Object> map = new HashMap<>();
        if (categoryService.FindCategoryByName(request)>0){//判断是否存在
            message="已存在|already exist";
            map.put("status", 1);
            map.put("message", message);
            return map;
        }
        int n = categoryService.AddCategory(request);
        if (n <= 0) {
            flag = 2;
            message = "失败";
        }
        map.put("status", flag);
        map.put("message", message);
        return map;
    }

    @ResponseBody
    @RequestMapping("/list")//分页式
    public Map<String, Object> list(HttpServletRequest request) throws Exception {
        logger.info("后台-分页获取分类列表");
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
    @RequestMapping("/all")//分页式
    public Map<String, Object> all(HttpServletRequest request) throws Exception {
        logger.info("后台-获取所有分类");
        request.setCharacterEncoding("utf-8");
        List<Category> list = categoryService.getCategoryListWithoutLimit(request);
        Map result = HashMapUtil.getFormatMap(categoryService.getRowCount());
        result.put("data", list);
        return result;
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
        List list=categoryService.FindCategoryOnLike(statement);
        result.put("count",list.size());
        result.put("data",list);
        return result;
    }
}
