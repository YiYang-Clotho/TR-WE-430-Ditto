package com.ditto.cookiez.service;

import com.ditto.cookiez.entity.Step;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-16
 */
public interface IStepService extends IService<Step> {
    Step addStep(Step step);

    Step getByOrderAndRecipeId(Integer order, Integer recipeId);

    Step addStep(Step step, MultipartFile file) throws IOException;
}
