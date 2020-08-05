# [springboot2.0集成RestTemplate](https://www.cnblogs.com/eternityz/p/12241380.html)

### 获取restTemplate实例,封装方法

```java
package com.quant.api.utils.restTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @program: api
 * @description:
 * @author: TheEternity Zhang
 * @create: 2019-06-04 18:00
 */
public class RestTemplateUtil {
    /**
     * 发送表单参数的post请求
     *
     * @param url      请求url
     * @param param    参数
     * @param respType 返回类型
     * @return T
     */
    public static <T> T postForm(String url, Map<String, List<Object>> param, Class<T> respType) {
        return getRestInstance().postForEntity(url, getHttpEntity(param, false), respType).getBody();
    }

    /**
     * 发送表单参数的异步post请求
     *
     * @param url      请求url
     * @param callback 回调接口
     * @param respType 返回类型
     */
    public static <T> void asyncPostForm(String url, Map<String, List<Object>> param,
                                         Class<T> respType, ListenableFutureCallback<ResponseEntity<T>> callback) {
        getAsyncRestInstance().postForEntity(url, getHttpEntity(param, false), respType).addCallback(callback);
    }

    /**
     * 发送表单有参数get请求
     *
     * @param url      请求url
     * @param param    参数对象
     * @param respType 返回类型
     * @return T
     */
    public static <T> T getForm(String url, Class<T> respType, Map<String,String> param) {
        return getRestInstance().getForEntity(url, respType, param).getBody();
    }

    /**
     * @Description: 发送表单无参数的get请求
     * @Param: [url, param, respType]
     * @return: T
     * @Author: tonyzhang
     * @Date: 2019-01-18 17:23
     */
    public static <T> T getForm(String url, Class<T> respType) {
        return getRestInstance().getForObject(url, respType);
    }


    /**
     * 获取HttpEntity实例对象
     *
     * @param param  参数对象
     * @param isJson true 发送json请求,false发送表单请求
     * @return HttpEntity
     */
    private static <P> HttpEntity<P> getHttpEntity(P param, boolean isJson) {
        HttpHeaders headers = new HttpHeaders();
        if (isJson) {
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        } else {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }

        return new HttpEntity<>(param, headers);
    }

    /*-----------------生产单例对象，方便自定义如何构造对象------------------*/

    private static RestTemplate restInit() {
        //设置连接超时和读取超时时间
        SimpleClientHttpRequestFactory factory=new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        RestTemplate restTemplate = new RestTemplate(factory);
        FormHttpMessageConverter fastConverter = new FormHttpMessageConverter();
        WxMappingJackson2HttpMessageConverter wmc=new WxMappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(fastConverter);
        restTemplate.getMessageConverters().add(wmc);
        return restTemplate;
    }



    private static AsyncRestTemplate asyncRestInit() {
        return new AsyncRestTemplate();
    }

    private static RestTemplate getRestInstance() {
        return RestSingle.INSTANCE;
    }

    private static AsyncRestTemplate getAsyncRestInstance() {
        return AsyncRestSingle.INSTANCE;
    }

    private static class RestSingle {
        private static final RestTemplate INSTANCE = restInit();
    }

    private static class AsyncRestSingle {
        private static final AsyncRestTemplate INSTANCE = asyncRestInit();
    }
}
```

### 增加一个MessageConverter

```java
package com.quant.api.utils.restTemplate;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: api
 * @description: 封装转换器, 添加更多类型的支持
 * @author: TheEternity Zhang
 * @create: 2019-06-05 11:13
 */
public class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public WxMappingJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes=new ArrayList<>();
        //添加text/html类型的支持
        mediaTypes.add(MediaType.TEXT_HTML);
        //添加text/plain类型的支持.微信接口会用到
        mediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(mediaTypes);
    }
}
```

# 参考

### 简介：

spring框架提供的RestTemplate类可用于在应用中调用rest服务，它简化了与http服务的通信方式，统一了RESTful的标准，封装了http链接，我们只需要传入url及返回值类型即可。相较于之前常用的HttpClient，RestTemplate是一种更优雅的调用RESTful服务的方式。

RestTemplate默认依赖JDK提供http连接的能力（HttpURLConnection），如果有需要的话也可以通过setRequestFactory方法替换为例如Apache HttpComponents、Netty或OkHttp等其它HTTP library。

其实spring并没有真正的去实现底层的http请求（3次握手），而是集成了别的http请求，spring只是在原有的各种http请求进行了规范标准，让开发者更加简单易用，底层默认用的是jdk的http请求。

### RestTemplate的优缺点：

#### 优点：

连接池、超时时间设置、支持异步、请求和响应的编解码

#### 缺点：

依赖别的spring版块、参数传递不灵活

# springboot集成RestTemplate：

导入依赖：（其实他是spring集成好的，这个一般的springboot项目已经由此包了）

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

在启动类同包下创建RestTemplate.java类

```java
@Configuration
public class RestTemplateConfig {
 
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }
 
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);
        return factory;
    }
 
}
```

然后在Service类中注入使用即可

```java
@Service
public class demoService {
 
    @Autowired
    private RestTemplate restTemplate;
 
    public String get(Integer id){
        return restTemplate.getForObject("http://localhost:8080/user?userId=id",String.class);
    }
}
```

RestTemplate定义了36个与REST资源交互的方法，其中的大多数都对应于HTTP的方法。 
 其实，这里面只有11个独立的方法，其中有十个有三种重载形式，而第十一个则重载了六次，这样一共形成了36个方法。

```java
delete() 在特定的URL上对资源执行HTTP DELETE操作

exchange() 在URL上执行特定的HTTP方法，返回包含对象的ResponseEntity，这个对象是从响应体中映射得到的

execute() 在URL上执行特定的HTTP方法，返回一个从响应体映射得到的对象

getForEntity() 发送一个HTTP GET请求，返回的ResponseEntity包含了响应体所映射成的对象

getForObject() 发送一个HTTP GET请求，返回的请求体将映射为一个对象

postForEntity() POST 数据到一个URL，返回包含一个对象的ResponseEntity，这个对象是从响应体中映射得到的

postForObject() POST 数据到一个URL，返回根据响应体匹配形成的对象

headForHeaders() 发送HTTP HEAD请求，返回包含特定资源URL的HTTP头

optionsForAllow() 发送HTTP OPTIONS请求，返回对特定URL的Allow头信息

postForLocation() POST 数据到一个URL，返回新创建资源的URL

put() PUT 资源到特定的URL
```

### getForEntity

get请求就和正常在浏览器url上发送请求一样

下面是有参数的get请求

```java
@GetMapping("getForEntity/{id}")
public User getById(@PathVariable(name = "id") String id) {
    ResponseEntity<User> response = restTemplate.getForEntity("http://localhost/get/{id}", User.class, id);
    User user = response.getBody();
    return user;
}
```

### getForObject

getForObject 和 getForEntity 用法几乎相同,指示返回值返回的是 响应体,省去了我们 再去 getBody()

```java
@GetMapping("getForObject/{id}")
public User getById(@PathVariable(name = "id") String id) {
    User user = restTemplate.getForObject("http://localhost/get/{id}", User.class, id);
    return user;
}
```

### postForEntity

```java
@RequestMapping("saveUser")
public String save(User user) {
    ResponseEntity<String> response = restTemplate.postForEntity("http://localhost/save", user, String.class);
    String body = response.getBody();
    return body;
}
```

### postForObject

用法与 getForObject 一样

如果遇到 postForObject 方法在 Controller 接受不到参数问题 请参考的的另一篇博客 :

https://blog.csdn.net/weixin_40461281/article/details/83472648

### exchange

```java
@PostMapping("demo")
public void demo(Integer id, String name){

    HttpHeaders headers = new HttpHeaders();//header参数
    headers.add("authorization",Auth);
    headers.setContentType(MediaType.APPLICATION_JSON);

    JSONObject obj = new JSONObject();//放入body中的json参数
    obj.put("userId", id);
    obj.put("name", name);

    HttpEntity<JSONObject> request = new HttpEntity<>(content,headers); //组装

    ResponseEntity<String> response = template.exchange("http://localhost:8080/demo",HttpMethod.POST,request,String.class);
}
```

其余的方法用法也都差不多 , 在此就不细说了