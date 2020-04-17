# Shiro 学习

## Shiro 简介


## Shiro 简单应用开发

#### 1、添加依赖
依赖包：`shiro-core`

#### 2、初始化
1. 通过 `SecurityUtils` 设置 `SecurityManager`
2. `SecurityManager` 需要手动设置 `Realm`

#### 3、应用
1. 通过 `SecurityUtils` 获取 `Subject`
2. 然后进行 `login(token)`、`hasRole(rolename)`等操作

## Shiro + SpringBoot

#### 1、添加依赖
依赖包：`shiro-spring`、`spring-boot-starter-web`

#### 2、初始化
1. 自定义 `Realm` 并注入到 `Spring` 的 `IoC` 容器
2. 配置 `SecurityManager`
   + 设置 `Realm(此时 Realm 中 AuthenticationToken 一般是 UsernamePasswordToken 类型)`

3. 配置 `ShiroFilterFactoryBean`
   + 设置 `SecurityManager`
   + 设置 `LoginUrl/UnauthorizedUrl`
   + 设置 `FilterChainDefinitionMap`（设置了 `url` 的过滤器）

#### 3、应用
1. 登录时需要通过 `Subject( Subject = SecurityUtils.getSubject)` 进行 `login` 操作(此时的 `login` 会通过 `Realm` 设置的身份认证信息进行身份认证)，未通过则会抛出异常
2. 注销时需要通过 `Subject` 进行 `logout` 操作
3. 其他 `url` 会根据设置 `FilterChainDefinitionMap` 进行过滤并进行相应的认证

## Shiro + JWT + SpringBoot

#### 1、添加依赖
依赖包：`shiro-spring`、`spring-boot-starter-web`、`java-jwt`

#### 2、初始化
1. 自定义 `JWTToken(implements AuthenticationToken)`
2. 自定义 `JWTUtil` 类，其作用是封装对 JWT类型Token 的生成（签名）、验证等方法
3. 自定义 `JTWFilter(extends BasicHttpAuthenticationFilter)`，重写 `isAccessAllowed` 方法，原因在于 `Subject.login()` 时传入 `JWTToken` 的实例
4. 自定义 `Realm` 并注入到 `Spring` 的 `IoC` 容器
5. 配置 `SecurityManager`，并注入到 `Spring` 的 `IoC` 容器
   + 设置 `Realm(此时 Realm 中 AuthenticationToken 是 JWTToken 类型)`

6. 配置 `ShiroFilterFactoryBean`，并注入到 `Spring` 的 `IoC` 容器
   + 设置 `SecurityManager`
   + 设置 `LoginUrl/UnauthorizedUrl`
   + 设置 `FilterChainDefinitionMap`（设置了 `url` 的过滤器）
7. 配置 `AuthorizationAttributeSourceAdvisor`，并注入到 `Spring` 的 `IoC` 容器
   + + 设置 `SecurityManager`

其它说明：
还有一些应用配置了其他的 `Bean`，如 `lifecycleBeanPostProcessor`、`defaultAdvisorAutoProxyCreator`等，暂时不清楚为什么需要配置这些？？？

