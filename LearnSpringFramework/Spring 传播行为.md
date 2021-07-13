# Spring 事务传播行为（机制）

## 一、概述

Spring 的传播行为有7种，分别为 `PROPAGATION_REQUIRED`、`PROPAGATION_REQUIRES_NEW`、`PROPAGATION_NESTED`、`PROPAGATION_SUPPORTS`、`PROPAGATION_NOT_SUPPORTED`、`PROPAGATION_MANDATORY`、`PROPAGATION_NEVER`



## 二、详解

### 2.1 `PROPAGATION_REQUIRED`

> `PROPAGATION_REQUIRED` 是 `Spring` 默认的事务传播机制。

调用方（或者当前上下文）没有事务，则创建一个新事务，如果调用方（或者当前上下文）有事务，则被调用方加入到该事务。在一个调用链中，虽然每个方法在逻辑上有自己的事务，但在物理上只有一个事务。

`UnexpectedRollbackException` 当内部方法排除异常时进行了回滚，外部方法将接收到该异常

serviceA 调用 serviceB 时的各种情况，此时 serviceA 逻辑上处于外部事务，serviceB 逻辑上处于内部事务。外部事务会影响到外部事务，外部事务也会影响到内部事务。

+ 仅serviceB异常：都进行回滚
+ 除serviceB之外的serviceA异常：都进行回滚。

### 2.2 `PROPAGATION_REQUIRES_NEW`

调用方（或者当前上下文）没有事务，则创建一个新事务，如果调用方（或者当前上下文）有事务，调用方的事务挂起，被调用方新建事务执行。在一个调用链中，每个方法在逻辑上有自己的事务，且在物理上都有自己的事务。

serviceA 调用 serviceB 时的各种情况，此时 serviceA 逻辑上处于外部事务，serviceB 逻辑上处于内部事务。外部事务和内部事务互不影响。

+ 仅serviceB异常：serviceB回滚，serviceA提交
+ 除serviceB之外的serviceA异常：serviceB提交，serviceA回滚

### 2.3 `PROPAGATION_NESTED`

嵌套事务：如果上下文中存在事务则嵌套执行，如果不存在则新建事务。（save point概念）一个物理事务，多个保存点。

serviceA 调用 serviceB 时的各种情况，此时 serviceA 逻辑上处于外部事务，serviceB 逻辑上处于内部事务。

+ 仅serviceB异常：serviceB回滚，serviceA提交
+ 除serviceB之外的serviceA异常：serviceA和serviceB都回滚



摘自 [CRUD更要知道的Spring事务传播机制](https://juejin.im/post/6844904064359071758)：

>1. `PROPAGATION_REQUIRED`：Spring的默认传播级别，如果上下文中存在事务则加入当前事务，如果不存在事务则新建事务执行。【子事务和父事务相互影响】
>2. `PROPAGATION_REQUIRES_NEW`：该传播级别每次执行都会创建新事务，并同时将上下文中的事务挂起，执行完当前线程后再恢复上下文中事务。【子事务和父事务相互独立】
>3. `PROPAGATION_NESTED`：嵌套事务，如果上下文中存在事务则嵌套执行，如果不存在则新建事务。（save point概念）【子事务不影响父事务，父事务影响子事务】
>4. `PROPAGATION_SUPPORTS`：如果上下文中存在事务则加入当前事务，如果没有事务则以非事务方式执行。
>5. `PROPAGATION_NOT_SUPPORTED`：当上下文中有事务则挂起当前事务，执行完当前逻辑后再恢复上下文事务。（降低事务大小，将非核心的执行逻辑包裹执行。）
>6. `PROPAGATION_MANDATORY`：该传播级别要求上下文中必须存在事务，否则抛出异常。
>7. `PROPAGATION_NEVER`：该传播级别要求上下文中不能存在事务，否则抛出异常。

