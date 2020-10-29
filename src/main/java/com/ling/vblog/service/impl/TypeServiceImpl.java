package com.ling.vblog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ling.vblog.entity.Type;
import com.ling.vblog.mapper.TypeMapper;
import com.ling.vblog.service.TypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
