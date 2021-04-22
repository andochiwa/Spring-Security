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
2. 实现`UserDetailsService`接口

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加密密码
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

```java
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User("andochiwa",
                new BCryptPasswordEncoder().encode("andochiwa"),
                role);
    }
}
```

# 权限设置

1. 在配置类中配置访问地址权限
2. 在`UserDetailsService`中把返回的对象设置权限

## 权限设置方法

1. `hasAuthority(String)` 针对单个权限设置
2. `hasAnyAuthority(String..)` 针对多个权限设置
3. `hasRole(String)` 针对单个角色设置
4. `hasAnyRole(String..)` 针对多个角色设置

# 注解使用

## @Secured

判断是否具有角色，匹配的是"Role_"，在controller上加上注解

需要先开启注解`@EnableGlobalMethodSecurity(securedEnabled = true)`

## @PreAuthorize

方法执行前进行权限验证，可以将用户的roles/permissions参数传到方法中

需要先开启注解`@EnableGlobalMethodSecurity(prePostEnabled = true)`

## @PostAuthorize

方法执行后进行权限验证，适合验证带有返回值的权限

`@EnableGlobalMethodSecurity(prePostEnabled = true)`

## @PreFilter

对传入的数据进行过滤

## @PostFilter("filterObject.name == 'andochiwa'")

权限验证后对数据进行过滤，留下用户名为andochiwa的数据