**面向对象-电梯程序设计**

<a name="heading_0"></a>**代码地址**

**[该类型的内容暂不支持下载]**

<a name="heading_1"></a>**电梯程序分析**

<a name="heading_2"></a>**1、单梯厢分析**

条件：

1、电梯只有一个

2、电梯只能上下

3、楼层高度为H （最高为H楼）最低楼层为 1

<a name="heading_3"></a>**情况1  电梯停在N楼 N = [1,H] ，M楼使用电梯 M=[1, H] ，目标为D楼**

N = 1， M =1， D = 3

电梯在一楼，人在一楼，要去3楼。

按向上按钮，电梯开门，选择楼层后，电梯上升到3**楼**。

N = 8， M = 5, D = 1

电梯在8楼，人在5楼，目标1楼

电梯向下运行至5楼，然后运行至1楼

N = 7，M = 4，D = 10

电梯向下运行至 4楼，然后向上运行至10楼

<a name="heading_4"></a>**情况2  电梯运行中 当前处于N楼 电梯目标 T 楼，M楼使用电梯 M = [1, H] ，M楼目标 D 楼**

<a name="heading_5"></a>**情况2.1  电梯向上 则M = [N + 1, T]， 电梯向下则 M = [T, N - 1], 此时 D 向上 = [M, T]  或则 D向下 = [T, M]**

解释：M 在电梯运行区间上，且M的目标在 电梯区间方向上。

电梯在向上运行，处于N楼，此时 M楼 有人要向上，M楼>N楼，即人在电梯运行方向上。（或者向下）

电梯将在M楼接人，然后运行至目标楼层，因为 T> N 电梯要向上，D > N， D 与电梯运行方向不冲突。

<a name="heading_6"></a>**情况2.2 电梯向上 则M = [N + 1, T]， 电梯向下则 M = [T, N - 1]，此时，D向下=[1,M], D向上=[M+1, H]**

解释，M 在电梯运行区间上，但是 M的目标与电梯目标相反

如，电梯运行中，处于2楼，目标10楼，，，，M = [3,10], 但是 M是要向下的，D=[1,3-1]。

电梯虽然经过3楼但不停，电梯将直接运行至10楼然后，再去完成M的目标。

<a name="heading_7"></a>**情况2.3 电梯向上 则M = [1，N]， 电梯向下则 M = [N, H]，此时 D 向上 = [M, H]  或则 D向下 = [1, M]**

解释，M 不在电梯运行区间上。电梯无法再运行中到达M楼

如：电梯运行中，处于5楼，目标10楼，但是 M = [1,N] = 3楼的用户 要使用电梯。

电梯需要先完成10楼，再去完成M楼的目标

| <p><a name="heading_8"></a>**总结**</p><p>电梯在运行中(向上or向下),可以接运行方向相同的用户。</p><p>电梯在运行中(向上or向下),必须完成电梯内目标后，才能接运行方相反的用户。</p> |
| :----------------------------------------------------------- |

<a name="heading_9"></a>**单电梯基本规则总结**

1、电梯完成目标时处于停止状态。

2、电梯处于停止状态时，可接任意目标。

3、电梯运行时，可以同时完成  人员在 电梯运行区间上，且其目标方向在电梯运行区间上的目标。

<a name="heading_10"></a>**2、多梯厢分析**

条件：

1、电梯(E)数量(K)>1 标号 [E1, Ek]

` `电梯标识为E 电梯数量K 则 第一个电梯为E1 最后一个电梯为Ek

2、电梯只能上下

3、楼层高度为H （最高为H楼）最低楼层为 L

4、单电梯满足 **单电梯基本规则**

<a name="heading_11"></a> **情况1 E1-Ek 停止在 [L, H] 楼。M楼需要电梯 M = [L, H] , 到达D楼层  D= [L, H]**

<a name="heading_12"></a> **情况1.1 E1-Ek 停止在 N 楼。M楼需要电梯 M = [L, H] , 到达D楼层  D= [L, H]**

电梯全部停止在同一楼层，M楼需要电梯。

处理逻辑： 随机一个电梯去完成M楼的目标，其他电梯仍处于停止状态。

<a name="heading_13"></a>**情况1.2 E1-Ek 停止在 [L,H] 楼。M楼需要电梯 M = [L, H] , 到达D楼层  D= [L, H]**

解释，电梯随机停在了一个楼层，此时M需要电梯到达D楼。

目标：M楼的等待时间最短 。

处理逻辑：离M楼最近的电梯去完成M楼的任务。

<a name="heading_14"></a>**情况2  E1-Ek 运行中，处于[L,H] 楼，目标[T1，Tk]。M楼需要电梯 M = [L, H] , 到达D楼层  D= [L, H]**

解释，电梯全部处于运行状态，处于[L,H] 楼，此时M需要电梯到达D楼。

目标：M楼的等待时间最短 。

处理逻辑：计算每个电梯到达M楼的时间权重，选择最小的电梯去执行任务。

权重计算逻辑：计算逻辑需满足 **单电梯基本规则 即计算时，需要按照任务执行顺序计算**

**计算逻辑1**： 满足**单电梯基本规则3直接计算电梯到达M楼的权重。**

如： 电梯运行中，当前在3楼，目标10楼。 M = 5楼，则权重计算 = 5 - 3 = 2.

**计算逻辑2：不满足满足单电梯基本规则3  先计算当前目标完成的权重，再计算累计权重。**

如： 电梯运行中，当前在5楼，目标10楼。 M = 3楼，

则权重计算 = 10-5 + 10-3 = 12.

10-5 是电梯需要完成现有目标。

10-3 是电梯停止10楼后 去完成 M楼的目标的权重。



<a name="heading_15"></a>**多电梯基本规则总结**

1、多电梯中每一个电梯满足 **单电梯基本规则**

2、多电梯，需保证人员的最短等待时间。


<a name="heading_16"></a>**对象分析(从下到上)**

<a name="heading_17"></a>**电梯对象**

显而易见的是 电梯对象

基础属性为：

当前所处楼层

当前状态：运行中, 停止中，开门中

当前运行方向: 向上 or 向下

高级属性（来源于业务分析）

**当前运行目标**： 电梯优先且必须完成的目标。

**等待执行目标**：电梯等待完成的目标。

需要衍生出一个对象，标识执行目标，

分析： 当人员，在按电梯时，有两个基本属性，当前所在楼层，目标方向（上下）。
```java
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
```

行为：

单电梯设计

**接收一个内部指令（电梯内的目标）**

需要执行

**接收一个外部指令（电梯外的目标）**

1. 电梯处于停止状态： 直接加入 **当前运行目标**
2. 电梯处于运行状态：需要判断是否执行（ 满足**单电梯基本规则3**）
3. 满足则 直接加入 **当前运行目标**
4. 不满足则 加入 **等待执行目标**

**开门与关门方法**

开门无影响

关门则有逻辑

1. 关门后判断是否有 **当前运行目标**
2. 有: 运行至对应楼层
3. 无：进入停止状态 -> 计算待执行目标.

```java
public class Elevator {
    private Long curFloor;
    private ElevatorStatus status;
    private Integer direction;

    private SortedSet<Long> targetOrders;

    private SortedSet<TargetOrder> PendingOrders;

    public Elevator() {
        this.targetOrders = new TreeSet<>();
        this.PendingOrders = new TreeSet<>();
    }

    public void acceptInnerOrder(Long targetFloor) {
        this.targetOrders.add(targetFloor);
    }

    public void acceptOuterOrder(TargetOrder targetOrder) {
        // 计算逻辑
    }
    
    public void openDoor(){

    }

    public void closeDoor(){
        // 计算逻辑
    }
}


public enum ElevatorStatus {
    STOPPING,
    WORKING,
    OPENING;
}
```

<a name="heading_18"></a>**单电梯运行方式**

<a name="heading_19"></a>**多电梯设计**

多电梯设计中，最关键的是，判断是哪个电梯去执行任务。

电梯对象保留

<a name="heading_20"></a>**电梯调度处理器对象**

高级对象，是对多电梯运行情况的抽象，承担电梯调度功能

<a name="heading_21"></a>**基本属性**

电梯对象集合

待执行的指令集合
```java
public class ElevatorScheduler {
    private List<Elevator> elevators;
    private List<TargetOrder> targetOrders;
}
```
调度器对象分析

调度器在接收一个外部指令时，需要判断此指令需要交给谁来处理。

权重的计算 分配到了 电梯对象中。so 电梯对象新增计算权重的逻辑

电梯对象新增 **checkOrder**方法
```java
public class Elevator {
    private Long curFloor;
    private ElevatorStatus status;
    private Integer direction;

    private SortedSet<Long> targetOrders;

    private SortedSet<TargetOrder> PendingOrders;

    public Elevator() {
        this.targetOrders = new TreeSet<>();
        this.PendingOrders = new TreeSet<>();
    }

    public void acceptInnerOrder(Long targetFloor) {
        this.targetOrders.add(targetFloor);
    }

    public void acceptOuterOrder(TargetOrder targetOrder) {
        // 计算逻辑
    }

    public void openDoor(){

    }

    public void closeDoor(){
        // 计算逻辑
    }

    public long  checkOrder(Long targetFloor) {
        // 计算权重
        return 0L;
    }
}
```
<a name="heading_22"></a>**调度器行为**

接收外部指令

高级行为

内部线程，计算剩余指令。

```java
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
    }

    public void acceptOuterOrder(TargetOrder targetOrder) {
        boolean accepted = computeWeight4Elevator(targetOrder);
        if (!accepted) {
            this.targetOrders.add(targetOrder);
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
```
<a name="heading_23"></a>**电梯重新设计**

多电梯下，可能存在电梯由调度器分配指令后，但是无法执行的情况。

如 电梯门一直被开启，没有关闭。

需要将待执行的任务交给调度器重新调度。
```java

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
```