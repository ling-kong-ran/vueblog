package com.ling.vueblog;

import com.ling.vblog.entity.Blog;
import com.ling.vblog.mapper.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class VueblogApplicationTests {
    @Autowired
    BlogMapper blogMapper;

    @Test
    void contextLoads() {

    }

}
