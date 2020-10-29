package com.ling.vblog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.vblog.entity.Blog;
import com.ling.vblog.mapper.BlogMapper;
import com.ling.vblog.service.BlogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 凌孔燃
 * @since 2020-10-24
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
