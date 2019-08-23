package com.yfming.leyou.item.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Date 2019/8/23 9:54
 * @Created by minfy
 */
@RequestMapping("spec")
public interface SpecificationApi {

    @GetMapping("{id}")
    String querySpecificationByCategoryId(@PathVariable("id") Long id);

}

