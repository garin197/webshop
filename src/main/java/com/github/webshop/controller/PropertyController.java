package com.github.webshop.controller;

import com.github.webshop.service.PropertyService;
import com.github.webshop.service.PropertyvalueService;
import com.github.webshop.util.HashMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyvalueService propertyvalueService;

    @ResponseBody
    @GetMapping("/list")
    public Map<String, Object> list(HttpServletRequest request, @RequestParam("cid") Integer categoryId)throws Exception {
        request.setCharacterEncoding("utf-8");
        Map<String, Object> result = HashMapUtil.getFormatMap(propertyService.getRowCountByCategoryId(request));
        List list = propertyService.getPropertyListByCategoryId(categoryId);
        result.put("data", list);
        return result;
    }

    @ResponseBody
    @GetMapping("/valList")
    public Map<String, Object> valueList(HttpServletRequest request, @RequestParam("cid") Integer categoryId)throws Exception {
        Map<String, Object> result = HashMapUtil.getFormatMap(propertyService.getRowCountByCategoryId(request));
        result.put("data", propertyvalueService.get_propertyvalue_list_by(request));
        return result;
    }

    /**
     * 插入一个属性值
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/value")
    public Integer Value(HttpServletRequest request) throws Exception{
        return propertyvalueService.handlePropertyValue(request);
    }

//    @ResponseBody
//    @PostMapping("/modifyValue")
//    public Integer modifyValue(HttpServletRequest request) throws Exception{
//        request.setCharacterEncoding("utf-8");
//        int flag=1;
//        return flag;
//    }

    @ResponseBody
    @PostMapping("/edit")
    public Integer edit(HttpServletRequest request) throws Exception{
        String propertyValue=request.getParameter("propertyValue");
        int flag=propertyService.EditProperty(request);
        return flag;
    }

    @PostMapping("/add")
    public void add(HttpServletRequest request, @RequestParam("cid") Integer categoryId) throws Exception {
        request.setCharacterEncoding("utf-8");
        request.setAttribute("categoryId",categoryId);
        propertyService.AddProperty(request);
    }

    @PostMapping("/del")
    public void del(@RequestParam("id") Integer propertyId,Map map) throws Exception {
        int n=propertyService.deleteProperty(propertyId);
        String msg="failed";
        if (n>0){
            msg="success";
        }
        map.put("msg",msg);
    }
}
