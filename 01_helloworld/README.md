# 设置登陆的用户名和密码

## 通过配置文件`spring.security.user.*`设置

## 通过配置类设置

需要继承`WebSecurityConfigurerAdapter`类，重写`configure(auth)`方法

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加密密码
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String andochiwa = passwordEncoder.encode("andochiwa");

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("andochiwa").password(andochiwa).roles("");
    }
}
```

## 自定义实现类设置

1. 创建配置类，设置使用哪个`UserDetailsService`的实现类