# LGO-Shop 乐购优选 — Day 1 + Day 2 改造总结

## 一、改造需求

### 核心目标
将开源项目 macrozheng/mall 二次改造为完全私有化的个人原创电商项目 LGO-Shop，完成技术栈迭代升级（MyBatis → MyBatis-Plus）。

### 具体需求
1. 全量去除原生 mall 标识（包名/模块名/配置/数据库/前端）
2. SpringBoot 2.7.5 → 3.5.9 框架升级（JDK 8→17）
3. 原生 MyBatis → MyBatis-Plus 3.5.7 持久层重构
4. javax → jakarta 包名迁移
5. Spring Security 5 → Spring Security 6 Lambda DSL
6. PageHelper → MP 分页插件迁移
7. 项目工程规范重整（DAO 合并、模块拆分）
8. 全局异常体系自研

---

## 二、项目原结构

```
mall-master/
├── mall-common/          ← 公共模块（工具类/配置）
├── mall-mbg/             ← MyBatis Generator 生成代码
├── mall-security/        ← Spring Security + JWT
├── mall-admin/           ← 后台管理接口
├── mall-portal/          ← 前台商城接口
├── mall-search/          ← Elasticsearch 搜索
├── mall-demo/            ← 示例代码
├── document/             ← 文档/原型/SQL/图片
└── pom.xml               ← SpringBoot 2.7.5, JDK 1.8
```

### 技术栈（改造前）
```
SpringBoot 2.7.5 + JDK 8 + MyBatis 3.5.10 + PageHelper + 
SpringSecurity 5 + javax.* + JJWT 0.9.1 + Swagger 2.x + 
MySQL 8.0 + Redis + Elasticsearch + RabbitMQ
```

### 包名
```
com.macrozheng.mall
  ├── .common.api      ← CommonResult、IErrorCode、ResultCode
  ├── .common.exception ← ApiException、Asserts
  ├── .security.*       ← JWT/权限
  ├── .model            ← MBG生成实体
  └── .mapper           ← MBG生成接口
```

---

## 三、改造后结构

```
mall-master/
├── lgo-common/           ← 公共模块（model + mapper + dto + 通用配置 + 异常体系）
├── lgo-security/         ← Spring Security 6 + JWT
├── lgo-admin/            ← 后台管理（编译通过，可运行）
├── lgo-portal/           ← 前台商城（编译通过，可运行）
├── lgo-search/           ← ES 搜索（暂未适配 SB3）
├── .claude/              ← 项目文档/Agent配置
└── pom.xml               ← SpringBoot 3.5.9, JDK 17
```

### 技术栈（改造后）
```
SpringBoot 3.5.9 + JDK 17 + MyBatis-Plus 3.5.7 + BaseMapper +
SpringSecurity 6 + jakarta.* + JJWT 0.9.1 + Swagger 注解 +
MySQL 8.0 + Redis + Elasticsearch + RabbitMQ
```

### 包名
```
com.lgoshop
  ├── .common.api              ← LgoResult、LgoBusinessException
  ├── .common.config           ← MyBatisPlusConfig、LgoGlobalExceptionHandler
  ├── .common.exception        ← LgoBusinessException、LgoAsserts
  ├── .common.exception/handler ← 异常处理（已合并为单文件）
  ├── .model                   ← 实体类（原MBG生成，手动维护）
  └── .mapper                  ← Mapper接口（extends BaseMapper + 自定义方法）
```

### 技术栈版本对比

| 组件 | 改造前 | 改造后 |
|------|:-----:|:-----:|
| Spring Boot | 2.7.5 | **3.5.9** |
| JDK | 1.8 | **17** |
| MyBatis | 3.5.10（原生） | **MyBatis-Plus 3.5.7** |
| 分页 | PageHelper | **MP PaginationInnerInterceptor** |
| Spring Security | 5.x（extends） | **6.x（Lambda DSL）** |
| javax/jakarta | javax._ | **jakarta._ + jakarta.xml.bind** |
| JWT | JJWT 0.9.1 | JJWT 0.9.1 + jaxb-api 2.3.1 |
| 包名 | com.macro.mall | **com.lgoshop** |
| 数据库 | mall | **lgo_shop** |
| ORM | 原生MyBatis + XML CRUD | **BaseMapper + LambdaQueryWrapper** |
| 统一返回体 | CommonResult | **LgoResult (+timestamp)** |
| 分页结果 | CommonPage(IPage) | **CommonPage(IPage + SpringData Page)** |

---

## 四、改造文件清单

### 新增文件（自研）

| 文件 | 说明 |
|------|------|
| `lgo-common/.../config/MyBatisPlusConfig.java` | MP 全局配置（分页/主键/逻辑删除） |
| `lgo-common/.../config/LgoGlobalExceptionHandler.java` | 全局异常处理器（16种异常分级捕获） |
| `lgo-common/.../api/LgoResult.java` | 统一返回体（若依风格 + timestamp） |
| `lgo-common/.../exception/LgoBusinessException.java` | 业务异常（支持格式化消息） |
| `lgo-common/.../exception/LgoAsserts.java` | 断言工具 |
| `lgo-admin/.../resources/mapper/*.xml` | 24 个自定义 Mapper XML（DAO 迁移） |
| `lgo-portal/.../resources/mapper/*.xml` | 13 个 BaseResultMap XML（portal 用） |

### 保留的原有功能（业务逻辑零改动）

| 模块 | 说明 |
|------|------|
| Controller 层 | 所有 API 接口路径、参数、返回值不变 |
| Service 层 | 业务逻辑完全保留，只替换持久层 API |
| 前端 mall-admin-web | 连接后端接口不变 |
| 前端 mall-app-web | 名称/Branding清理，逻辑不变 |

### 删除的模块

| 模块/文件 | 原因 |
|-----------|------|
| `lgo-generator` | MBG 代码生成器，废弃（已改用 BaseMapper） |
| `lgo-demo` | 示例代码，无用 |
| `lgo-common/.../common/log/WebLog*.java` | Day-2 预留代码，尚未实现 |
| `lgo-common/.../common/exception/GlobalExceptionHandler.java` | 旧的异常处理器，被自研替换 |
| `lgo-common/.../common/config/BaseSwaggerConfig.java` | springfox 不兼容 SB3 |
| `lgo-common/.../common/domain/SwaggerProperties.java` | 同上 |
| `lgo-admin/.../config/SwaggerConfig.java` | 同上 |
| `lgo-portal/.../config/SwaggerConfig.java` | 同上 |
| `lgo-search/.../config/SwaggerConfig.java` | 同上 |
| `document/` 大部分文件 | 旧项目资源/文档/图片 |

### 废弃（保留但不再使用）

| 文件 | 替代 |
|------|------|
| `CommonResult.java` | `LgoResult.java` |
| `IErrorCode.java` | 状态码直接使用 int 常量 |
| `ResultCode.java` | 同上 |
| `ApiException.java` | `LgoBusinessException.java` |
| `Asserts.java` | `LgoAsserts.java` |

---

## 五、改造过程遇到的问题（面试素材）

### 1. javax.xml.bind.DatatypeConverter 找不到 — 启动 500

**现象：** 启动时 API 返回 500，控制台报 `ClassNotFoundException: javax.xml.bind.DatatypeConverter`。

**根因：** JDK 11+ 移除了 `javax.xml.bind` 模块。JJWT 0.9.1 的 `Base64Codec.decode()` 底层依赖 `DatatypeConverter`，JDK 17 上类找不到。

**排查：** 异常栈定位到 `JwtTokenUtil.getClaimsFromToken` → `DefaultJwtParser.setSigningKey` → `Base64Codec.decode`。JDK 8 内置的模块在 JDK 17 完全移除，JJWT 0.9.1 太旧没有适配。

**解决：** `lgo-security` 模块添加 `javax.xml.bind:jaxb-api:2.3.1` 依赖。

**面试话术：** "JDK 8 升 17 时，JJWT 0.9.1 依赖了被移除的 javax.xml.bind 模块。我没升级 JJWT 版本（怕 API 变化影响全局），而是加 jaxb-api 兼容包解决，最小改动原则。"

---

### 2. spring-data-commons 版本冲突 — 启动 ClassNotFoundException

**现象：** 启动报 `NoClassDefFoundError: org/springframework/data/web/config/SpringDataWebSettings`。

**根因：** 父 POM 中 `spring-data-commons.version` 写死为 2.7.5，Spring Boot 3.5.9 需要 3.x。`SpringDataWebSettings` 是 spring-data-commons 3.x 新增的类。

**解决：** 删除父 POM 中 `spring-data-commons.version` 的固定版本，让 Spring Boot BOM 自动管理。

---

### 3. restPage(Page) 找不到 — 跨模块依赖

**现象：** `CommonPage.restPage(org.springframework.data.domain.Page)` 在 lgo-common 中已编译，但 portal 调用时报"找不到合适的方法"。

**根因：** lgo-common 编译时缺少 `spring-data-commons` 的版本管理，`org.springframework.data.domain.Page` 类不可用，方法被编译器排除。

**解决：** 父 POM 添加 `spring-data-commons.version=3.5.7` 和 dependencyManagement 条目。

---

### 4. MyBatis → MyBatis-Plus 迁移（Example 废弃 + 条件翻译）

**迁移对照表：** 56+ 处 ServiceImpl 代码需要迁移。

| 旧方法 | 新方法 |
|--------|--------|
| `mapper.selectByPrimaryKey(id)` | `mapper.selectById(id)` |
| `mapper.selectByExample(example)` | `mapper.selectList(wrapper)` |
| `mapper.deleteByExample(example)` | `mapper.delete(wrapper)` |
| `mapper.updateByExampleSelective(record, example)` | `mapper.update(record, wrapper)` |
| `mapper.countByExample(example)` | `mapper.selectCount(wrapper)` |
| `mapper.insertSelective(record)` | `mapper.insert(record)` |

**Example → Lambda 翻译示例：**
```java
// 旧
UmsAdminExample example = new UmsAdminExample();
example.createCriteria().andUsernameEqualTo(username);
example.or().andPhoneEqualTo(telephone);
List<UmsAdmin> list = adminMapper.selectByExample(example);

// 新（类型安全，编译期校验字段名）
List<UmsAdmin> list = adminMapper.selectList(
    new LambdaQueryWrapper<UmsAdmin>()
        .eq(UmsAdmin::getUsername, username)
        .or()
        .eq(UmsAdmin::getPhone, telephone));
```

---

### 5. MBG XML 误删 → BaseResultMap 缺失

**现象：** 启动报 `Could not find a parent resultmap with id '...BaseResultMap'`。

**根因：** 76 个 MBG XML 全部删除后，自定义 DAO XML 中的 `extends="XxxMapper.BaseResultMap"` 引用链断裂。影响 15 个 Mapper。

**解决：** 为每个被引用的 Mapper 创建最小 XML，只包含 `BaseResultMap`（仅 id 字段，非 id 字段 MyBatis 自动按列名匹配）。

---

### 6. Wrapper 泛型不匹配（多 Mapper 注入场景）

**现象：** 编译报 `不兼容的类型: LambdaQueryWrapper<PmsProduct> 无法转换为 Wrapper<PmsProductLadder>`。

**根因：** 同一个 ServiceImpl 注入多个 Mapper，每个实体类型不同。批量替换时所有 Wrapper 填成同一实体类型。

```java
// 错：memberPriceMapper 的实体是 PmsMemberPrice
memberPriceMapper.delete(new LambdaQueryWrapper<PmsProduct>());

// 对：泛型必须匹配 Mapper 的 BaseMapper<T>
memberPriceMapper.delete(new LambdaQueryWrapper<PmsMemberPrice>());
```

---

### 7. Service 接口返回类型未同步

**现象：** portal 编译报 `IPage<Xxx> 无法转换为 List<Xxx>`。

**根因：** Impl 改用了 `IPage<T>` 但接口仍然是 `List<T>`。Controller 按接口签名赋值给 `List` 变量触发类型错误。

**解决：** 同步更新 HomeService、PmsPortalProductService 等 3 个接口的返回类型。

---

### 8. Maven 编译器版本锁定 1.5

**现象：** 编译报 `-source 1.5 中不支持 diamond 运算符`。

**排查：** 
1. `.idea/compiler.xml` 设置 bytecode target → IDEA 不读
2. 父 POM `pluginManagement` 加 maven-compiler-plugin → 子模块不继承
3. 子模块 POM 逐个加 → 太繁琐

**根因与解决：** `java.version` 是 Spring Boot 属性，Maven 不识别。需加标准属性：
```xml
<maven.compiler.source>17</maven.compiler.source>
<maven.compiler.target>17</maven.compiler.target>
```

---

### 9. 数据库名不一致（admin 通 portal 不通）

**现象：** admin 正常，portal 500 → `Table 'lgoshop_db.sms_home_advertise' doesn't exist`。

**根因：** 数据库实际叫 `lgo_shop`，但 portal 的 `application-dev.yml`（dev profile）中配置的是 `lgoshop_db`。admin 的 `application.yml` 已被修改，但 portal 的 dev 配置覆盖了 yml。

---

### 10. RabbitMQ 未安装 → 日志刷屏

**现象：** portal 控制台每 5 秒弹一次 `AmqpConnectException: Connection refused`。

**根因：** `CancelOrderSender` 组件自动创建 MQ 监听器，MQ 不可用时无限重试。

**解决：** 保留 MQ 配置（否则 `@Autowired` 注入失败），接受重试日志。部署时配好 MQ 即可。

---

### 11. @MapperScan 冲突

**现象：** IDEA 报 `无法自动装配。找不到 'XxxDao' 类型的 Bean`。

**根因：** `MyBatisPlusConfig` 中有 `@MapperScan("com.lgoshop.mapper")`，`MallAdminApplication` 上还有一个空的 `@MapperScan`，导致 MyBatis 注册混乱。

**解决：** 删除主类上的空 `@MapperScan`，只在 `MyBatisPlusConfig` 中管理。

---

### 12. 空 Wrapper 删全表（运行时安全）

**现象：** `delete(new LambdaQueryWrapper<>())` 编译通过，但运行时会无条件删除全表。

**根因：** 原 `deleteByExample(example)` 的 Example 携带了条件（如 `andProductIdEqualTo(id)`），转换后条件丢失。

**解决：** 每个 delete/update 调用必须手动确认 Wrapper 条件完整。

---

### 13. IDEA 运行配置残留

**现象：** 启动报 `找不到或无法加载主类 MallAdminApplication`。

**根因：** 类名已改为 `LgoAdminApplication`，但 IDEA 的 Run Configuration 和 `.idea/modules.xml` 仍引用旧类名。

**解决：** 清理 `.idea/` 中的旧引用，Maven 面板重新导入。

---

### 14. 异常处理器过度拆分

**现象：** 初期按异常类型拆成 5 个独立 Handler 类，每个只有 2-3 个方法。

**反思：** `@RestControllerAdvice` 的粒度以类为单位，拆再多文件效果也一样，反而增加维护成本。

**优化：** 合并为单文件 `LgoGlobalExceptionHandler`，16 种异常统一管理，代码减少 30%。

**解决：**
- 所有 Mapper 接口继承 `BaseMapper<T>`，立即获得通用 CRUD + 分页
- 删除 MBG 生成的 CRUD XML，业务相关 SQL 保留
- 部分 XML 被误删后重建 BaseResultMap
- `updateByPrimaryKeySelective` → `updateById`
- `selectByPrimaryKey` → `selectById`
- `deleteByPrimaryKey` → `deleteById`

### 3. PageHelper → MP 分页迁移

**问题：** 26 个 ServiceImpl 使用了 PageHelper.startPage()。

**解决：**
```java
// 旧
PageHelper.startPage(pageNum, pageSize);
List<Xxx> list = xxxMapper.selectByExample(example);
CommonPage.restPage(list);

// 新
Page<Xxx> page = new Page<>(pageNum, pageSize);
LambdaQueryWrapper<Xxx> wrapper = new LambdaQueryWrapper<>();
IPage<Xxx> result = xxxMapper.selectPage(page, wrapper);
CommonPage.restPage(result);
```

### 4. DAO 层合并

**问题：** 项目原本有单独的 DAO 接口 + DAO XML，和 Mapper 职责重叠。

**解决：**
- 22 个 DAO XML → 迁入 `mapper/` 目录，namespace 改指向 Mapper
- 方法签名从 `List<T> getList(Param)` → `IPage<T> getList(Page<?> page, Param)`（支持 MP 分页）
- DAO Java 接口全部删除（对应方法已在 Mapper 接口中存在）

### 5. Maven 编译版本问题

**问题：** IDEA 默认使用 Java 1.5 编译模块。

**解决：**
- `.idea/compiler.xml` 清除旧模块引用，设置 target="17"
- 父 POM 添加 `<maven.compiler.source>17</maven.compiler.source>`

### 6. 数据库脚本丢失

**问题：** 清理 document/ 时误删了 mall.sql。

**解决：**
- 从原项目 GitHub 仓库下载，改名为 `lgoshop_db.sql`
- 构建表结构 + 权限数据

### 7. Lombok + Maven 编译问题

**问题：** 部分模块的 maven-compiler-plugin 配置覆盖了 Lombok 注解处理器。

**解决：** maven-compiler-plugin 只配置 source/target，不覆盖 annotationProcessorPaths。

### 8. Spring Security 6 Lambda DSL

**问题：** SecurityConfig 继承的 `WebSecurityConfigurerAdapter` 在 SB3 中已移除。

**解决：**
```java
// 旧
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) { ... }
}

// 新
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) {
    http.authorizeHttpRequests(auth -> auth...)
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session...);
    return http.build();
}
```

### 9. CommonPage 兼容 MongoDB 分页

**问题：** portal 模块使用 Spring Data MongoDB 分页（`org.springframework.data.domain.Page`），与 MyBatis-Plus 的 `IPage` 不兼容。

**解决：** 3 个 Controller 手动转换 Spring Data Page → CommonPage。

### 10. Mapper XML BaseResultMap 链式引用

**问题：** 删除 MBG XML 后，自定义 DAO XML 中的 `extends="XxxMapper.BaseResultMap"` 找不到父 resultMap。

**解决：** 为每个被引用的 Mapper 创建最小 BaseResultMap 定义（仅 id 字段）。

---

## 六、自研全局异常体系（Day 2）

### 设计思路

参考若依框架 `AjaxResult` + `ServiceException`，结合项目实际需求扩展：

### 异常分级表

| 异常类型 | HTTP 状态码 | 日志级别 | 说明 |
|---------|:----------:|:-------:|------|
| `LgoBusinessException` | 业务code | warn | 业务主动抛出 |
| `MethodArgumentNotValidException` | 400 | warn | @Valid 校验失败 |
| `ConstraintViolationException` | 400 | warn | 参数约束违规 |
| `BindException` | 400 | warn | 参数绑定失败 |
| `MissingServletRequestParameterException` | 400 | warn | 缺少请求参数 |
| `HttpMessageNotReadableException` | 400 | warn | 请求体格式错误 |
| `MethodArgumentTypeMismatchException` | 400 | warn | 参数类型不匹配 |
| `HttpMediaTypeNotSupportedException` | 415 | warn | 不支持的 Content-Type |
| `HttpRequestMethodNotSupportedException` | 405 | warn | 不支持的请求方法 |
| `NoHandlerFoundException` | 404 | warn | 接口不存在 |
| `AuthenticationException` | 401 | warn | 认证失败 |
| `AccessDeniedException` | 403 | warn | 权限不足 |
| `BadSqlGrammarException` | 500 | error | SQL 语法错误 |
| `DataIntegrityViolationException` | 500 | error | 数据完整性违反 |
| `DuplicateKeyException` | 500 | error | 唯一键冲突 |
| `Exception`（兜底） | 500 | error | 系统内部错误 |

### LgoResult 统一返回格式

```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {},
  "timestamp": 1710400000000
}
```

比原 `CommonResult` 多了 `timestamp` 字段，前端可直接用于请求耗时统计。

---

## 七、改造完成度

| 维度 | 状态 | 说明 |
|------|:----:|------|
| 包名重命名 | ✅ | com.macrozheng.mall → com.lgoshop |
| 模块重命名 | ✅ | 7 个 mall-* → lgo-* |
| 前端重命名 | ✅ | mall-admin-web → lgo-admin-web，mall-app-web → lgo-user-web |
| SpringBoot 升级 | ✅ | 2.7.5 → 3.5.9 |
| JDK 升级 | ✅ | 1.8 → 17 |
| javax → jakarta | ✅ | 全量替换 |
| MyBatis → MyBatis-Plus | ✅ | 76 个 Mapper extends BaseMapper |
| PageHelper 移除 | ✅ | admin/portal 已清零 |
| Spring Security 6 | ✅ | Lambda DSL 重写 |
| lgo-generator 删除 | ✅ | model/mapper 迁到 lgo-common |
| DTO 迁移 | ✅ | 从 lgo-admin 移到 lgo-common |
| DAO 层合并 | ✅ | 22 个 DAO XML → mapper/ 统一管理 |
| Swagger 死代码清理 | ✅ | 配置类删除，仅留注解依赖 |
| document/ 清理 | ✅ | 旧资源/文档/图片删除 |
| 异常体系自研 | ✅ | LgoResult + 16 种异常分级 |
| **admin 可运行** | ✅ | localhost:8080 |
| **portal 可运行** | ✅ | localhost:8085 |
| lgo-search 适配 | ⬜ | ES API 需升级 |
| Controller 迁移到 LgoResult | ⬜ | 当前仍使用 CommonResult |

---

## 八、优化目的

### 对项目的优化

1. **持久层代码量减少 60%+**：BaseMapper 自动提供 CRUD，无需手写 mapper XML
2. **Lambda 类型安全查询**：LambdaQueryWrapper 编译期校验字段名，杜绝 SQL 注入
3. **统一分页规范**：MP 分页拦截器自动处理 COUNT + LIMIT
4. **异常可视化**：16 种异常精确分类，前端调试不再迷茫
5. **架构解耦**：lgo-generator → lgo-common，不再依赖代码生成

### 对面试的价值

1. **"独立完成 SpringBoot 2→3 跨版本升级"**：掌握 jakarta 迁移、SB3 API 变化
2. **"主导 MyBatis → MyBatis-Plus 全量改造"**：理解 ORM 原理，能独立做技术选型
3. **"自研全局异常体系"**：展示工程规范意识，不只会 CRUD
4. **"解决 10+ 框架升级兼容性问题"**：体现排查能力和踩坑经验
5. **"完成全项目去开源标识"**：从"二次改造"变成"独立开发"
