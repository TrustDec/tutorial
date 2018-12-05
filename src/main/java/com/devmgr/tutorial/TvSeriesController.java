package com.devmgr.tutorial;


import net.sf.json.JSONArray;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
    @GetMapping("chinaquake/{page}")
    public String getChina(@PathVariable int page) {
        CrawData cd = new CrawData();
        JSONArray json = cd.crawChina();
        String jsonString = json.toString();
        return jsonString;
    }
    @GetMapping("globalquake/{page}")
    public String getGlobal(@PathVariable int page) {
        CrawData cd = new CrawData();
        JSONArray json = cd.craw(page);
        String jsonString = json.toString();
        return jsonString;
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

    @PostMapping(value = "/{id}/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhoto(@PathVariable int id, @RequestParam("photo") MultipartFile imgFile) throws IOException {
        if (log.isTraceEnabled()) {
            log.trace("接收到文件" + id + "收到文件:" + imgFile.getOriginalFilename());
        }
        FileOutputStream fos = new FileOutputStream("target/" + imgFile.getOriginalFilename());
        IOUtils.copy(imgFile.getInputStream(), fos);
        fos.close();
    }

    @GetMapping(value = "/{id}/icon", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getIcon(@PathVariable int id) throws Exception {
        if (log.isTraceEnabled()) {
            log.trace("getIcon(" + id + ")");
        }
        String iconFile = "src/test/resources/wechat.png";
        InputStream is = new FileInputStream(iconFile);
        return IOUtils.toByteArray(is);
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
