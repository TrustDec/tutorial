package com.devmgr.tutorial;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tvseries")
public class TvSeriesController {
    private static final Log log = LogFactory.getLog(TvSeriesController.class);

    @GetMapping("/tv")
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

    @GetMapping()
    public List<TvSeriesDto> getAll() {
        if (log.isTraceEnabled()) {
            log.trace("getAll();被调用了");
        }
        List<TvSeriesDto> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.OCTOBER, 2, 0, 0);
        list.add(new TvSeriesDto(1, "WestWorld", 1, calendar.getTime()));
        calendar.set(2011, Calendar.SEPTEMBER, 2, 0, 0);
        list.add(new TvSeriesDto(1, "Person of Interest", 5, calendar.getTime()));
        return list;
    }

    @GetMapping("/{id}")
    public TvSeriesDto getOne(@PathVariable int id) {
        if (log.isTraceEnabled()) {
            log.trace("getOne" + id);
        }
        if (id == 101) {
            return createWestWorld();
        } else if (id == 102) {
            return createPoi();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @PutMapping("/{id}")
    public TvSeriesDto updateOne(@PathVariable int id, @RequestBody TvSeriesDto tvSeriesDto) {
        if (log.isTraceEnabled()) {
            log.trace("updateOne" + id);
        }
        if (id == 101 || id == 102) {
            return createPoi();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @PostMapping
    public TvSeriesDto inserOne(@RequestBody TvSeriesDto tvSeriesDto) {
        if (log.isTraceEnabled()) {
            log.trace("这里应该写新增tvSeriesDto到数据库的代码，传递进来的参数:" + tvSeriesDto);
        }
        tvSeriesDto.setId(9999);
        return tvSeriesDto;
    }

    private TvSeriesDto createWestWorld() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2011, Calendar.SEPTEMBER, 2, 0, 0);
        return new TvSeriesDto(1, "Person of Interest", 5, calendar.getTime());
    }

    private TvSeriesDto createPoi() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.OCTOBER, 2, 0, 0);
        return new TvSeriesDto(1, "WestWorld", 1, calendar.getTime());
    }
}
