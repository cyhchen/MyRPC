package com.cxy.version2.server;

import com.cxy.version2.common.Blog;
import com.cxy.version2.service.BlogService;

public class BlogServiceImpl implements BlogService {
	@Override
	public Blog getBlogById(Integer id){
		Blog blog = Blog.builder().id(id).userId(11).title("My Blog").build();
		System.out.println("查询了" + id + "博客");
		return blog;
	}

}
