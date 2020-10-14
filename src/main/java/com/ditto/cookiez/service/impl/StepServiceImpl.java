package com.ditto.cookiez.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ditto.cookiez.entity.Img;
import com.ditto.cookiez.entity.Step;
import com.ditto.cookiez.mapper.StepMapper;
import com.ditto.cookiez.service.IImgService;
import com.ditto.cookiez.service.IStepService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ditto.cookiez.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * Service Class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
@Service
public class StepServiceImpl extends ServiceImpl<StepMapper, Step> implements IStepService {
    @Autowired
    IImgService imgService;

    @Override
    public Step addStep(Step step) {
        save(step);
        return step;
    }

    @Override
    public Step getByOrderAndRecipeId(Integer order, Integer recipeId) {
        QueryWrapper<Step> qw = new QueryWrapper<>();
        qw.eq("recipe_id", recipeId);
        qw.eq("step_order", order);
        return list(qw).get(0);
    }

    @Override
    public Step addStep(Step step, MultipartFile file) throws IOException {
        int recipeId = step.getRecipeId();
        int order = step.getStepOrder();
        String url = FileUtil.uploadStepImgToAws(file, recipeId, order);
        Img img = new Img(url);
        imgService.save(img);
        step.setImgId(img.getImgId());
        save(step);
        return step;
    }
}
