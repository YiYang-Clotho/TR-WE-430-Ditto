package com.ditto.cookiez.service.impl;

import com.ditto.cookiez.entity.Amount;
import com.ditto.cookiez.mapper.AmountMapper;
import com.ditto.cookiez.service.IAmountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2020-09-29
 */
@Service
public class AmountServiceImpl extends ServiceImpl<AmountMapper, Amount> implements IAmountService {

}
