package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Img;
import com.ditto.cookiez.mapper.ImgMapper;
import com.ditto.cookiez.service.IImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Service Class
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements IImgService {


    @Override
    public String getPathById(Integer id) {
        Img img = getById(id);
        if (img != null) {
            return img.getImgPath();
        }
        return null;
    }
}
