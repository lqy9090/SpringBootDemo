package com.example.demo.jsonplaceholder;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author: qiuyi
 * @Description:
 * @DateTime: 2022/12/19 16:29
 **/

@FeignClient(
        value = "jsonplaceholder",
        url = "https://jsonplaceholder.typicode.com/"
)
public interface JSONPlaceHolderClient {

    @GetMapping("posts")
    List<Post> getPosts();

    @GetMapping("posts/{postId}")
    Post getPost(@PathVariable("postId") Integer postId);
}
