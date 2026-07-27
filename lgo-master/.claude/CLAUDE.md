项目基础信息：
1. 项目名称：LGO‑Shop乐购优选电商系统，前身基于macrozheng/mall‑master改造，完成全项目去mall‑化，Java包名：com.lgoshop；
2. 版本信息：JDK17、Maven‑3.8.8、SpringBoot‑3.5.9、MyBatis‑Plus‑3.5.7、Redis‑6.2.14；后期微服务阶段Spring‑Cloud‑Alibaba‑2023.0.1.0；SpringSecurity6采用Lambda写法，javax全部替换为jakarta包；
3. 中间件：MySQL‑8.0、RocketMQ、Elasticsearch；前期单体架构，后期拆分微服务；
4. 模块：lgo‑common公共模块、lgo‑security安全模块、lgo‑portal前台商城、lgo‑admin后台管理、lgo‑search搜索模块、lgo‑generator代码生成模块；
5. 开发约束：优先新建类和新增方法，最小改动原有业务代码；原生代码非必要不修改、不删除；代码严格遵循阿里巴巴Java开发手册；适配应届生理解水平，禁止过度抽象；
6. 迭代规则：开发Agent和审查Agent最多两轮修改迭代；超过两轮交由架构Agent评估方案；
7. 硬性约束：仅Day‑1集中升级框架版本；后续开发阶段禁止升级任何组件版本；禁止引入计划之外的中间件；
8. 整体目标：30天完成全套改造，代码提交Gitee，使用者本人吃透全部代码原理；AI仅作为开发助手。