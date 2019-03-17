package com.github.webshop.controller;

import com.github.webshop.service.PropertyService;
import com.github.webshop.util.HashMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(HttpServletRequest request, @RequestParam("cid") Integer categoryId) {
        Map<String, Object> result = HashMapUtil.getFormatMap();
        List list = propertyService.getPropertyListByCategoryId(categoryId);
        result.put("data", list);

        return result;
    }

    @RequestMapping("/add")
    public void add(HttpServletRequest request, @RequestParam("cid") Integer categoryId) throws Exception {
        request.setCharacterEncoding("utf-8");
        request.setAttribute("categoryId",categoryId);
        propertyService.AddProperty(request);
    }

    @RequestMapping("/del")
    public void del(@RequestParam("id") Integer propertyId,Map map) throws Exception {
        int n=propertyService.deleteProperty(propertyId);
        String msg="failed";
        if (n>0){
            msg="success";
        }
        map.put("msg",msg);
    }
}
