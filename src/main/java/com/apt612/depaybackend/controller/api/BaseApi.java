package com.apt612.depaybackend.controller.api;

import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class BaseApi {
    protected Mapper mapper;

    public BaseApi(Mapper mapper) {
        this.mapper = mapper;
    }

    public <T> T map(Object origin, Class<T> generateClass) {
        return mapper.map(origin, generateClass);
    }

    public <T> List<T> mapList(List origin, Class<T> generateClass) {
        List<T> mapped = new ArrayList<>();
        for (Object o : origin) {
            mapped.add(mapper.map(o, generateClass));
        }
        return mapped;
    }

}
