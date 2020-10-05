package com.ditto.cookiez.entity.dto;

import com.ditto.cookiez.entity.Step;
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
    private Integer stepOrder;
    private Integer imgId;
    public StepDTO() {

    }

    public StepDTO(Step step) {
        this.stepContent = step.getStepContent();
        this.stepOrder = step.getStepOrder();

    }
}
