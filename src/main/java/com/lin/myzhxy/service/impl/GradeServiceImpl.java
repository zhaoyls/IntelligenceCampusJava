package com.lin.myzhxy.service.impl;

import com.lin.myzhxy.mapper.GradeMapper;
import com.lin.myzhxy.pojo.Grade;
import com.lin.myzhxy.service.GradeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage; // 6 啊
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    /**
     * gradeService.getGradeByOpr(page, gradeName);具体实现
     * @param pageParam
     * @param gradeName
     * @return
     */
    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> pageParam, String gradeName) {

        // 省去写sql
        QueryWrapper<Grade> queryWrapper = new QueryWrapper();

        // 模糊匹配
        if (!StringUtils.isEmpty(gradeName)) {
            queryWrapper.like("name",gradeName);
        }

        // 根据id降序
        queryWrapper.orderByDesc("id");

        // 查询结果
        Page<Grade> page = baseMapper.selectPage(pageParam, queryWrapper);
        return page;
    }

    @Override
    public List<Grade> getGrades() {
        return  baseMapper.selectList(null);
    }
}
