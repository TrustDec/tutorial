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
    public Map<String, Object> sayHello() {
        Map<String, Object> result = new HashMap<>();
        HashMap map1 = new HashMap();
        map1.put("name", "c80d1b66e49667c4f33ed319d00c7194");
        map1.put("age", 18);
        map1.put("sex", 1);
        map1.put("token", "5bab36d9959d69031c86b96c");
        result.putAll(map1);
        return result;
    }
}
