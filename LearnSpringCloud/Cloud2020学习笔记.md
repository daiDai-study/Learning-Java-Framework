# Cloud2020学习笔记



## 零.版本选型

### 1. 版本选型参考官网

+ 查看 Spring Boot 最新稳定版本：https://spring.io/projects/spring-boot#learn
+ 查看 Spring Cloud 最新稳定版本：https://spring.io/projects/spring-cloud#learn
+ 查看 Spring Cloud 各个大版本对应的 Spring Boot 版本：https://spring.io/projects/spring-cloud#overview
+ 查看 Spring 官网提供的不同版本依赖对应的 Spring Boot 版本范围：https://start.spring.io/actuator/info

此次版本选型与学习视频（*尚硅谷2020最新版SpringCloud(H版&alibaba)框架开发教程全套完整版从入门到精通*）一致

+ SpringBoot：2.2.2.RELEASE
+ SpringCloud：Hoxton.SR1
+ SpringCloud Alibaba：2.1.0.RELEASE

### 2. 开发参考文档：

+ SpringCloud 官网文档：https://docs.spring.io/spring-cloud/docs/
+ SpringCloud-Hoxton.SR1：https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/
+ SpringCloud 中文翻译文档：https://www.bookstack.cn/read/spring-cloud-docs/docs-index.md
+ SpringBoot 官网文档：https://docs.spring.io/spring-boot/docs
+ SpringBoot-2.2.2.RELEASE：https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/



## 一.整体架构搭建

### 1. 约定

+ 字符编码： UTF-8
+ Java 编译：Java8
+ 注解处理：开启
+ 命名：
  1. 聚合工程：org.daistudy.springcloud/cloud2020
  2. 子模块：org.daistudy.springcloud/cloud-payment-service-8001 
  3. 包命名：mapper/service/controller/entity/config

### 2. 聚合工程POM

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.daistudy.springcloud</groupId>
    <artifactId>cloud2020</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <properties>
        <!-- 基本属性 -->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.targer>1.8</maven.compiler.targer>

        <!-- 依赖版本 -->
        <mysql.version>5.1.47</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <!-- spring boot 2.2.2.RELEASE -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!-- spring cloud Hoxton.SR1 -->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!-- spring cloud alibaba 2.1.0.RELEASE -->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring.boot.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 3. 子模块POM通用

```xml
<dependencies>
    <!--spring-boot-starter-web-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--spring-boot-starter-actuator-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <!--spring-boot-starter-test-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <!--lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <!--spring-boot-devtools-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>

    <!--spring-boot-starter-jdbc-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <!--mybatis-spring-boot-starter-->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
    </dependency>
    <!--druid-spring-boot-starter-->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.10</version>
    </dependency>
    <!--mysql-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### 4. 开启热部署

+ 添加 spring-boot-webtools 依赖
+ 添加 org.springframework.boot:spring-boot-maven-plugin 插件，并将其配置的 fork 属性设置为 true
+ 配置IDEA：
  1. Settings --> Build --> Compiler：选中 ADBC（A 为 Automatically show first error in editor；D 为 Display notification on build completion； B 为 Build project automatically；C 为 Complile independent modules in parallel）
  2. Registry（Ctrl + Alt + Shilt + /）：选中 compiler.automake.allow.when.app.running 和 actionSystem.assertFocusAccessFromEdt
+ 根据 IDEA 版本，有的版本会立即生效，有的版本需要重启 IDEA