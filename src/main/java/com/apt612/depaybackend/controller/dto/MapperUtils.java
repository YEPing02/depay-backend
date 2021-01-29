package com.apt612.depaybackend.controller.dto;

import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperUtils {

    private  Mapper mapper;
@Autowired
    public MapperUtils(Mapper mapper) {
        this.mapper = mapper;
    }

    public  <T> T map(Object origin, Class<T> generateClass) {
        return mapper.map(origin, generateClass);
    }

    public  <T> List<T> mapList(List origin, Class<T> generateClass) {
        List<Object> list = new ArrayList<>();
        List<T> mapped = new ArrayList<>();
        for (Object o : list) {
            mapped.add(mapper.map(o, generateClass));
        }
        return mapped;
    }
}
