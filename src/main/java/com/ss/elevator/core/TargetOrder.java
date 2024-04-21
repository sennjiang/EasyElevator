package com.ss.elevator.core;

import lombok.Data;

@Data
public class TargetOrder {
    /**
     * 当前用户所在楼层
     */
    private Long curFloor;
    /**
     * 当前用户目标方向
     */
    private Integer direction;
}
