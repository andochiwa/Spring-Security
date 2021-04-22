# 基本原理

> Spring Security的本质是一条过滤器链

底层主要有三个过滤器

## FilterSecurityInterceptor

方法级的权限过滤器，基本位于过滤器链的最底部

## ExceptionTranslationFilter

异常过滤器，用来处理在认证授权过程中抛出的异常

## UsernamePasswordAuthenticationFIlter

对/login的POST请求做拦截，校验表单中的用户名和密码

# 自定义逻辑过程

1. 创建一个类继承`UsernamePasswordAuthenticationFilter`，重写三个方法

2. 实现`UserDetailsService`接口，编写查询数据库等过程，返回安全框架提供的`User`对象

3. `PasswordEncoder`接口对密码进行加密，官方推荐使用`BCryptPasswordEncoder`实现类来进行加密。

   这个实现类是bcrypt强散列方法的具体实现，基于哈希算法实现的单向加密，可以通过strength控制加密强度，默认为10

