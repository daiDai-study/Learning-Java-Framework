# SpringCloud 基础学习笔记

## 一、基础概念

### 1、微服务架构

#### 1.1 定义
> 微服务是将（单体）应用拆分成一个个独立的服务单元，并通过接口来实现数据的交互。每个独立的（微）服务可以单独部署运行，服务之间可以通过 `RPC` 或 `Restful HTTP` 来相互交互，每个微服务都是由独立的小团队开发，测试，部署，上线，负责它的整个生命周期。


#### 1.2 微服务与分布式的关系
> 微服务是分布式架构的一种形式。
> 
> 系统应用部署在超过一台服务器或虚拟机上，且各分开部署的部分彼此通过各种通讯协议交互信息，就可算作分布式部署，生产环境下的微服务肯定是分布式部署的，分布式部署的应用不一定是微服务架构的，比如集群部署，它是把相同应用复制到不同服务器上，但是逻辑功能上还是单体应用。

#### 1.3 单体架构和微服务架构的优缺点
![](./imgs/单体架构的优缺点.png)
![](./imgs/微服务的优缺点.png)

#### 1.4 微服务架构体系

![](./imgs/微服务架构体系.png)

#### 1.5 微服务架构两大阵营
> Dubbo + ZooKeeper 和 Spring Cloud + Netfilx Eureka

![](./imgs/微服务两大阵营.png)

### 2、Spring Cloud

> Spring Cloud 是基于 Spring Boot 的开发便利，对 Netflix 开源组件的进一步封装，极大地简化了微服务的开发

## 二、Spring Cloud 实践

### 1、Spring Cloud 中各大部件

#### 1.0 基础概念

+ Eureka Server（注册中心==服务器端）：注册中心服务器应用（注册中心也可以注册到其他注册中心，实现注册中心的高可用）
+ Eureka Client（注册客户端）：需要注册到注册中心的应用

![](./imgs/注册中心的高可用配置.png)

#### 1.1 注册中心服务器
> 注册中心是分布式架构中最重要的部分，用于服务的注册和发现，所有微服务都必须在注册中心登记后才能被使用

1. 依赖引入：`spring-cloud-starter-netflix-eureka-server`
2. 配置应用名称（`spring.application.name`，该名称将作为注册到注册中心的客户端应用ID）
3. 配置服务器端口号（`server.port`，**Eureka服务器端默认使用**`8761`**作为注册中心的监控端口，也是Eureka客户端的通信端口**）为8761，这一步只是推荐作法，保持注册中心应用端口和监控端口一致
4. 配置取消将自己作为客户端注册到自己启用的注册中心中（默认配置注册，通过 `eureka.client.register-with-eureka = false` 进行取消）
5. 启用注册中心（添加注解 `@EnableEurekaServer`）

注意：Eureka 是服务端和客户端一体的，在启动应用时，会向作为注册中心的自己发送注册请求，但自己还没有启动成功，所以应用启动的时候会报异常错误（`Cannot execute request on any known server`），在没有进行步骤4的情况下，当应用启动完毕之后的一段时间（*心跳包机制*）之后会发现自己作为注册客户端也被注册到注册中心中了（*前提条件是注册中心的端口设置为8761，设置成其他端口还是会注册失败，为什么？？？*），如果进行了步骤4，则不会通过心跳包机制持续发送注册请求（*前提条件是注册中心的端口设置为8761，设置成其他端口还是会持续注册，并报异常，为什么？？？*）

上述问题的关键就在于，心跳包机制如何进行的？服务器端发送？客户端发送？


#### 1.2 各个服务模块（微服务）

1. 依赖引入：`spring-cloud-starter-netflix-eureka-client`和其它作为正常应用的依赖（如`spring-boot-starter-web`）
2. 配置应用名称和端口号：应用名称将作为注册客户端ID，不同应用的端口号应提前规划好
3. 配置注册中心地址：地址可以为多个（多个地址使用 `,` 分隔开 ）
4. 启用注册客户端（添加注解 `@EnableDiscoveryClient`）

问题1：`@EnableDiscoveryClient` 和 `@EnableEurekaClient` 的区别是什么？？？

问题2：当我不添加 `@EnableDiscoveryClient` 或添加 `@EnableEurekaClient` （甚至不配置注册中心地址时）时，服务都能注册到注册中心中（*前提条件是注册中心的端口设置为8761，设置成其他端口还是会注册失败，为什么？？？*）

注意1：客户端停止服务时，注册中心在一段时间内仍然保持注册记录，而且可能会错误地会一直保持注册记录

注意2：
```
# 服务端也会向客户端发送心跳包？？？
# 服务端和客户端通过心跳检查的机制进行交互
# Eureka 客户端每个一段时间向这个地址发送一次心跳包（请求数据），如果发现 Eureka 服务器是启动状态，客户端就会重新注册到注册中心
# 可以添加多个注册中心地址
```

#### 1.3 网关服务

> 网关作为微服务架构中面向客户的端口，是API调用方的统一入口，而网关服务也是一个注册客户端，需要注册到注册中心
> 
> Spring Cloud 中使用 Netflix 中的 Zuul 组件作为网关服务

1. 依赖引入：`spring-cloud-starter-netflix-eureka-client`、`spring-cloud-starter-netflix-zuul`
2. 配置应用名称和端口号：应用名称将作为注册客户端ID
3. 配置注册中心地址：地址可以为多个（多个地址使用 `,` 分隔开 ）
4. 启用注册客户端（添加注解 `@EnableDiscoveryClient`）
5. 启用 Zuul 路由服务（添加注解 `@EnableZuulProxy`）
6. 如果需要可以重新配置各个服务模块的路由路径（`zuul.routes`），默认情况下，通过url访问各个服务模块时需要统一加上应用名称


#### 1.4 配置中心

> 配置中心可以统一配置各个微服务中的配置文件中的信息，而配置中心也是一个注册客户端，需要注册到注册中心

1. 依赖引入：`spring-cloud-starter-netflix-eureka-client`、`spring-cloud-config-server`
2. 配置应用名称和端口号：应用名称将作为注册客户端ID
3. 配置注册中心地址：地址可以为多个（多个地址使用 `,` 分隔开 ）
4. 启用注册客户端（添加注解 `@EnableDiscoveryClient`）
5. 启用配置中心（添加注册 `@EnableConfigServer`）
6. 配置-各个微服务的配置文件存放git信息（地址、用户名、密码） 
7. **重新配置各个微服务**
    + pom.xml 中添加依赖： `spring-cloud-config-client`
    + 将 application.yml 删除
    + 新增 bootstrap.yml
    + bootstrap.yml 中配置应用名称和注册中心地址
    + bootstrap.yml 中配置 作为配置中心的信息
    
### 2、Spring Cloud 中服务间的通信

#### 2.1 RestTemplate + Ribbon
> RestTemplate 是 Spring Cloud 内置的一个 HTTP 客户端（对 Apache HttpClient 的封装）
> 
> Ribbon 可以对客户端进行负载均衡，其中核心组件就是 `LoadBalancerClient`

![](./imgs/Ribbon.png)

使用方式：
1. 采用硬编码的方式使用 RestTemplate（IP和端口号都需要通过硬编码设置）
    ```Java
    @GetMapping("test")
    public String test(){
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("http://localhost:8100/port", String.class);
        return json;
    }
    ```
2. 采用 Ribbon 获取服务列表（提供负载均衡）并使用 RestTemplate
    ```Java
    @Resource
    private LoadBalancerClient loadBalancerClient;

    ...
    @GetMapping("test")
    public String test(){
        RestTemplate restTemplate = new RestTemplate();
        final ServiceInstance book = loadBalancerClient.choose("book");
        final String host = book.getHost();
        final int port = book.getPort();
        String json = restTemplate.getForObject("http://"+ host +":"+ port +"/port", String.class);
        return json;
    }
    ```
3. 采用注解方式（内部原理也是Ribbon）使用 RestTemplate
    ```Java
    // 注入到IOC容器（在其他配置类中）
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    // 以下代码在某个Controller层中
    @Resource
    private RestTemplate restTemplate;

    ... 
    @GetMapping("test")
    public String test(){
        final String json = restTemplate.getForObject("http://book/port", String.class);
        return json;
    }
    ```

#### 2.2 Feign

> Feign 是一个声明web服务客户端，它的出现简化了微服务的通信过程
> 
> Feign 也集成了 Ribbon
> 
> Feign 的开发需要创建一个接口并使用 Spring MVC 注解

1. 依赖引入：`spring-cloud-starter-openfeign`
2. 启用 Feign 客户端：`@EnableFeignClients`
3. 创建一个接口，并使用 `@FeignClient` 指明哪个微服务的调用客户端
4. 在接口中创建抽象方法，并使用 Spring MVC 注解声明参数和路由

### 3、Hystrix 服务降级

> 当某个被调用的服务宕机时，可能会导致整个系统的雪崩效应
> 
> 服务降级常与熔断器结合使用
> 
> Hystrix 默认的超时时间为 1 s（可以通过配置文件信息配置）

#### 3.1 Hystrix 在 RestTemplate 中使用

1. 依赖引入：`spring-cloud-starter-netflix-hystrix`
2. 启用断路器：`@EnableCircuitBreaker`
3. 添加降级方法（方法的参数、返回值需要跟目标方法一致）
4. 目标方法上添加注解指定降级方法：`@HystrixCommand`
5. 也可以在类上添加全局默认的降级方法（该方法不需要参数，且返回 String 或任何可以被 JSON 序列化的对象）之后，在类上添加注解：`@DefaultProperties`，同时在目标方法上添加注解（此时不需要指定降级方法）

#### 3.2 Hystrix 在 Feign 中使用

1. 不需要手动引入 hystrix 的依赖，`spring-cloud-starter-openfeign`中已经有对应的依赖关系
2. 启用 hystrix ：`feign.hystrix.enabled = true`
3. 在 `@FeignClient` 的接口内部，创建一个静态的内部实现类，并使用 `@Component` 注入
4. 在 `@FeignClient` 的接口上，将 `@FeignClient` 中的 `fallback` 属性设置为上述中的实现类


#### 3.3 Hystrix 断路器原理

![](./imgs/Hystrix断路器原理.png)