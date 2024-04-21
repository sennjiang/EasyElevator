package com.ss.elevator.core;

import com.ss.elevator.enums.ElevatorStatus;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Elevator {
    private Long curFloor;
    private ElevatorStatus status;
    private Integer direction;

    private long openTime;

    private ElevatorScheduler elevatorScheduler;

    private SortedSet<TargetOrder> targetOrders;

    private SortedSet<TargetOrder> pendingOrders;

    public Elevator() {
        this.targetOrders = new TreeSet<>();
        this.pendingOrders = new TreeSet<>();
    }

    /**
     * 接收一个电梯内命令
     */
    public void acceptInnerOrder(TargetOrder targetFloor) {
        this.targetOrders.add(targetFloor);
    }

    /**
     * 接收一个外部命令
     */
    public boolean acceptOuterOrder(TargetOrder targetOrder) {
        // 计算逻辑
        return false;
    }

    // 开门
    public void openDoor() {
        this.openTime = System.currentTimeMillis();
        this.status = ElevatorStatus.OPENING;
        // 其他开门动作
    }

    /**
     * 关门
     */
    public void closeDoor() {
        // 如 规则 开门时间大于3分钟
        if (this.openTime - System.currentTimeMillis() > 3 * 60) {
            // 放弃任务
            this.elevatorScheduler.acceptOuterOrders(this.targetOrders);
            this.elevatorScheduler.acceptOuterOrders(this.pendingOrders);
            return;
        }
        this.status = ElevatorStatus.WORKING;
        // 计算逻辑
        if (!this.targetOrders.isEmpty()) {
            // 继续执行
        } else if (!this.pendingOrders.isEmpty()) {
            // 存在任务 将待执行任务加入 执行任务中
            this.targetOrders = this.pendingOrders;
        } else {
            // 无任务
            this.status = ElevatorStatus.STOPPING;
        }

    }

    /**
     * 计算权重
     * 计算逻辑仅供参考
     */
    public long checkOrder(TargetOrder targetOrder) {
        Integer targetDirection = targetOrder.getDirection();
        Long targetFloor = targetOrder.getCurFloor();
        // 计算权重
        if (Objects.equals(this.direction, targetDirection)) {
            long f = targetFloor - this.curFloor;
            // 同一方向
            if (f * this.direction > 0) {
                return Math.abs(f);
            }else {
                return Math.abs(targetOrders.last().getCurFloor() - this.curFloor)
                        + Math.abs(targetOrders.last().getCurFloor() - targetFloor);
            }
        } else {
            return Math.abs(targetOrders.last().getCurFloor() - this.curFloor)
                    + Math.abs(targetOrders.last().getCurFloor() - targetFloor);
        }
    }

    /**
     * 配置 调度器对象
     */
    public void setElevatorScheduler(ElevatorScheduler elevatorScheduler) {
        this.elevatorScheduler = elevatorScheduler;
    }
}
