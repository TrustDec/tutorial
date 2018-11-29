package com.devmgr.tutorial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/tvseries")
public class TvSeriesController {
    @GetMapping
//    public Map<String, Object> sayHello() {
//        Map<String, Object> result = new HashMap<>();
//        HashMap map1 = new HashMap();
//        map1.put("name", "c80d1b66e49667c4f33ed319d00c7194");
//        map1.put("age", 18);
//        map1.put("sex", 1);
//        map1.put("token", "5bab36d9959d69031c86b96c");
//        result.putAll(map1);
//        return result;
//    }
//    @GetMapping
    public List<TvSeriesDto> getAll(){
        List<TvSeriesDto> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,Calendar.OCTOBER,2,0,0);
        list.add(new TvSeriesDto(1,"WestWorld",1,calendar.getTime()));
        calendar.set(2011,Calendar.SEPTEMBER,2,0,0);
        list.add(new TvSeriesDto(1,"Person of Interest",5,calendar.getTime()));
        return list;
    }
}
