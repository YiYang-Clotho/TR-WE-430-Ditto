package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Comment;
import com.ditto.cookiez.mapper.CommentMapper;
import com.ditto.cookiez.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    CommentMapper mapper;

}
