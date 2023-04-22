package com.lin.myzhxy.service.impl;

import com.lin.myzhxy.mapper.ClazzMapper;
import com.lin.myzhxy.pojo.Clazz;
import com.lin.myzhxy.service.ClazzService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {


    @Override
    public IPage<Clazz> getClazzsByOpr(Page<Clazz> pageParam, Clazz clazz) {
        QueryWrapper<Clazz> queryWrapper=new QueryWrapper<>();
        String gradeName = clazz.getGradeName();
        if (!StringUtils.isEmpty(gradeName)) {
            queryWrapper.like("grade_name",gradeName);
        }

        String name = clazz.getName();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByDesc("id");
//  ORDER BY id DESC
//==>  Preparing: SELECT COUNT(1) FROM tb_clazz
//==> Parameters:
//<==    Columns: COUNT(1)
//                <==        Row: 7
//                ==>  Preparing: SELECT id,name,number,introducation,headmaster,telephone,email,grade_name FROM tb_clazz ORDER BY id DESC LIMIT ?,?
//==> Parameters: 0(Long), 3(Long)
//                <==    Columns: id, name, number, introducation, headmaster, telephone, email, grade_name
//                <==        Row: 7, 四年一班, 30, 小赵的三年二班好, 小飞, 13866666666, xiaofei@163.com, 四年级
//                <==        Row: 6, 三年二班, 30, 小赵的三年二班好, 小赵, 13866666666, xiaozhao@163.com, 三年级
//                <==        Row: 5, 三年一班, 30, 小花的三年一班好, 小花, 13866666666, xiaohua@163.com, 三年级
//                <==      Total: 3
        Page<Clazz> clazzPage = baseMapper.selectPage(pageParam, queryWrapper);

        return clazzPage;
    }

    @Override
    public List<Clazz> getClazzs() {
        return baseMapper.selectList(null);
    }
}
