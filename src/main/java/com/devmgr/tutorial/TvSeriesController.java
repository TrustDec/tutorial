package com.devmgr.tutorial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tvseries")
public class TvSeriesController {
    @GetMapping
    public Map<String,Object> sayHello(){
        Map<String,Object> result = new HashMap<>();
        HashMap map1=new HashMap();
        map1.put("name","张三");
        map1.put("age",18);
        map1.put("sex",true);
        map1.put("message","hello,world");
        result.putAll(map1);
        return result;

    }


}
