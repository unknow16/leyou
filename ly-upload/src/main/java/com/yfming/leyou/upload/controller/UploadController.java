package com.yfming.leyou.upload.controller;

import com.yfming.leyou.upload.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * - 请求方式：上传肯定是POST
 * - 请求路径：/upload/image
 * - 请求参数：文件，参数名是file，SpringMVC会封装为一个接口：MultipleFile
 * - 返回结果：上传成功后得到的文件的url路径
 *
 * 图片上传是文件的传输，如果也经过Zuul网关的代理，文件就会经过多次网路传输，造成不必要的网络负担。在高并发时，可能导致网络阻塞，Zuul网关不可用。这样我们的整个系统就瘫痪了。
 *
 * 所以，我们上传文件的请求就不经过网关来处理了。
 *
 *
 * #### Zuul的路由过滤
 *
 * Zuul中提供了一个ignored-patterns属性，用来忽略不希望路由的URL路径，示例：
 *
 * ```properties
 * zuul.ignored-patterns: /upload/**
 * ```
 *
 * 路径过滤会对一切微服务进行判定。
 *
 * Zuul还提供了`ignored-services`属性，进行服务过滤：
 *
 * ```properties
 * zuul.ignored-services: upload-servie
 * ```
 *
 * 我们这里采用忽略服务：
 *
 * ```yaml
 * zuul:
 *   ignored-services:
 *     - upload-service # 忽略upload-service服务
 * ```
 *
 * 上面的配置采用了集合语法，代表可以配置多个
 *
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传图片功能
     * @param file
     * @return
     */
    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String url = this.uploadService.upload(file);
        if (StringUtils.isBlank(url)) {
            // url为空，证明上传失败
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // 返回200，并且携带url路径
        return ResponseEntity.ok(url);
    }
}