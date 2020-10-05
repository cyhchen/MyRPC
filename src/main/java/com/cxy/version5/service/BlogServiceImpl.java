package com.cxy.version5.service;

import com.cxy.version5.common.Blog;

public class BlogServiceImpl implements BlogService {
	@Override
	public Blog getBlogById(Integer id){
		Blog blog = Blog.builder().id(id).userId(11).title("My Blog").build();
		System.out.println("查询了" + id + "博客");
		return blog;
	}

}
