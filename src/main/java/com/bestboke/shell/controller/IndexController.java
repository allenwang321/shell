package com.bestboke.shell.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestboke.shell.constant.AddConstant;
import com.bestboke.shell.pojo.ServicesInfo;
import com.bestboke.shell.tools.ShellMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

public class IndexController {

    @Autowired
    private AddConstant addConstant;

    @Autowired
    private ShellMain shellMain;

    @PostMapping("add")
    @ResponseBody
    public JSONArray add(String ip) throws Exception{
        JSONArray result = new JSONArray();
        List<ServicesInfo> info = addConstant.getInfo(ip);
        for (ServicesInfo servicesInfo : info){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ip",servicesInfo.getHost());
            jsonObject.put("body",shellMain.doShell(servicesInfo));
            result.add(jsonObject);
        }
        return  result;

    }

}
