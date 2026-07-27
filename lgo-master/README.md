# LGO-Shop 乐购优选电商系统

## 项目介绍

LGO-Shop 是一套电商系统，包括前台商城系统及后台管理系统，基于 SpringBoot + MyBatis-Plus 实现，采用 Docker 容器化部署。

- 后台管理系统：商品管理、订单管理、会员管理、促销管理、运营管理、内容管理、统计报表、财务管理、权限管理、设置等模块
- 前台商城系统：首页门户、商品推荐、商品搜索、商品展示、购物车、订单流程、会员中心、客户服务、帮助中心等模块

## 项目结构

```
LGO-Shop
├── lgo-common -- 工具类及通用代码
├── lgo-generator -- MyBatis-Plus Generator 生成的数据库操作代码
├── lgo-security -- SpringSecurity 封装公用模块
├── lgo-admin -- 后台商城管理系统接口
├── lgo-search -- 基于 Elasticsearch 的商品搜索系统
└── lgo-portal -- 前台商城系统接口
```

## 技术栈

| 技术 | 说明 |
| ---- | ---- |
| SpringBoot 3.5.9 | Web 应用开发框架 |
| SpringSecurity 6 | 认证和授权框架 |
| MyBatis-Plus 3.5.7 | ORM 框架 |
| Elasticsearch | 搜索引擎 |
| RabbitMQ | 消息队列 |
| Redis | 内存数据存储 |
| MongoDB | NoSql 数据库 |
| MySQL 8.0 | 关系型数据库 |
| MinIO | 对象存储 |
| Docker | 应用容器引擎 |
| Druid | 数据库连接池 |

## 环境要求

- JDK 17
- Maven 3.8+
- MySQL 8.0
- Redis 6.x
- Elasticsearch 7.x (搜索模块)

## 快速开始

1. 导入 `document/sql/lgoshop_db.sql` 到 MySQL
2. 修改各模块 `application-dev.yml` 中的数据库连接配置
3. 启动 Redis 服务
4. 分别启动 `lgo-admin`、`lgo-portal`、`lgo-search` 模块

## 许可证

[Apache License 2.0](LICENSE)

Copyright (c) 2024-2026 LGO-Shop
