package com.autoGenerate.service.impl;

import com.autoGenerate.model.entity.Comment;
import com.autoGenerate.mapper.CommentMapper;
import com.autoGenerate.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-08-14
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
