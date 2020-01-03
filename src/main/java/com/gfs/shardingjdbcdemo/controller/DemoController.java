package com.gfs.shardingjdbcdemo.controller;

import com.alibaba.fastjson.JSONArray;
import com.gfs.shardingjdbcdemo.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private DemoDao demoDao;

    @GetMapping("add")
    public String demo(){
        demoDao.addDemo();
        return "success";
    }

    @GetMapping("query")
    public JSONArray query(){
        return demoDao.queryDemo();
    }
}
