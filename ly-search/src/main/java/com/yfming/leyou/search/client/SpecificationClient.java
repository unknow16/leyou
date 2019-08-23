package com.yfming.leyou.search.client;

import com.yfming.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Date 2019/8/23 9:56
 * @Created by minfy
 */
@FeignClient(value = "item-service")
public interface SpecificationClient extends CategoryApi {
}
