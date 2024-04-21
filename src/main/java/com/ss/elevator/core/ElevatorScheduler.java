package com.ss.elevator.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ElevatorScheduler {
    private List<Elevator> elevators;
    private final List<TargetOrder> targetOrders;
    private final Thread runOrdersThread;

    public ElevatorScheduler() {
        this.targetOrders = new ArrayList<>();
        this.elevators = new ArrayList<>();
        this.runOrdersThread = new Thread(() -> {
            try {
                runOrdersAccept();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        this.runOrdersThread.start();
    }

    public void acceptElevators(List<Elevator> elevators) {
        this.elevators = elevators;
        this.elevators.forEach(e -> e.setElevatorScheduler(this));
    }

    public void acceptOuterOrder(TargetOrder targetOrder) {
        boolean accepted = computeWeight4Elevator(targetOrder);
        if (!accepted) {
            this.targetOrders.add(targetOrder);
        }
    }

    /**
     * 批量获取任务
     */
    public void acceptOuterOrders(Set<TargetOrder> targetOrders) {
        for (TargetOrder targetOrder : targetOrders) {
            boolean accepted = computeWeight4Elevator(targetOrder);
            if (!accepted) {
                this.targetOrders.add(targetOrder);
            }
        }
    }

    private boolean computeWeight4Elevator(TargetOrder targetOrder) {
        // 计算权重
        long weight = Long.MAX_VALUE;
        Elevator acceptElevator = null;
        for (Elevator elevator : elevators) {
            long ew = elevator.checkOrder(targetOrder);
            if (ew < weight) {
                weight = ew;
                acceptElevator = elevator;
            }
        }
        if (acceptElevator != null) {
            return acceptElevator.acceptOuterOrder(targetOrder);
        }
        return false;
    }

    /**
     * 时刻运行的线程任务
     */
    public void runOrdersAccept() {
        List<TargetOrder> notAcceptOrders = new ArrayList<>();
        List<TargetOrder> targetOrders = this.targetOrders;
        this.targetOrders.clear();
        for (TargetOrder targetOrder : targetOrders) {
            boolean accepted = computeWeight4Elevator(targetOrder);
            if (!accepted) {
                notAcceptOrders.add(targetOrder);
            }
        }

        if (!notAcceptOrders.isEmpty()) {
            // 计算中间可能产生指令
            this.targetOrders.addAll(notAcceptOrders);
        }
    }
}
