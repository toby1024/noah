# noah
- 一个spring cloud的demo，采用event sourcing思想打造

# 使用的组件
- spring cloud eureka 服务注册和发现
- spring cloud config 动态加载配置
- spring cloud zuul api网关服务
- hystrix dashboard 监控服务状态
- spring cloud stream 消息服务

# gataway
- 结合jwt，对/auth/* 不鉴权，其余请求需要在header中带上 Authorization
```
jwt:
  header: Authorization
  secret: 6BbW0pxO0YENxn38HMUbcQ
  expiration: 604800
  tokenHead: "NOAH_"
  route:
    authentication:
      path: auth
      refresh: refresh
      register: "auth/register"
```

- 通过spring security来鉴权
````
protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()

                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/auth/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存
        httpSecurity.headers().cacheControl();
    }
```

- 登录注册都在gataway项目auth目录中,返回jwt的token
```
// cn.skio.gateway.auth.web.AuthController
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password){
        return authService.login(username, password);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String username, String password){
        return authService.register(username, password);
    }

}

```

