package com.gfs.shardingjdbcdemo.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface DemoDao {
    void addDemo();

    JSONArray queryDemo();
}
