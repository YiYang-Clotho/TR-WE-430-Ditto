package com.ditto.cookiez.entity.dto;

import lombok.Data;

/**
 * @author Zhihao Liang
 * @date 2020/9/23 19:14
 * @email s3798366@student.rmit.edu.au
 */
@Data
public class StepDTO {
    private String stepContent;
    private String imgPath;
    private Integer imgId;
    private Integer stepOrder;
}
