## vue3新特性

```js
主要改进:
	提升运行时性能: 重写vDom (虚拟dom)
	提升网络性能: tree-shaking机制
	完全的typeScript支持
	便利性改进
	生态圈改进


5,
```

#### 提升运行时性能

```js
大幅提升运行时性能
	什么是vDom
    	虚拟dom,提升性能
    	//在web操作中最慢的操作就是dom操作也就是html元素操作,比如 获取元素,删除元素.添加元素等
    效果如何
    	提升30%-300%
    核心思想
    	跳过静态节点,只处理动态节点
    	//页面中的静态节点,在浏览器第一遍渲染完后就不管了(因为静态节点是不会变的),只盯着动态节点
```

#### 使用tree-shaking机制

```js
大幅提升网络性能
 	什么是tree
 		依赖树,所有的依赖都是tree的节点
 		//简单来说 咱平常写代码的使用用到一个东西需要先 import进来 import进来之后就可以用它里面的东西了但是这时候就有一个问题,你所引入的库它里面有大量的东西可能包含几百项功能,但是你真正用到的就是其中的一项两项 但是程序不知道你用了几项所有程序只能在你打包的时候把整个库打包给你
 	如何shaking
 		通过代码反向检测哪些特性被用到
        //通过代码反向检测我用到了那些东西,我该打包哪些东西,我不该打包拿些东西
 	效果如何
 		只打包必要的依赖项

```

#### 底层typescript支持

```js
为什么使用ts
	强大的类型系统,类型提示
		大型应用必备
    Vue3支持情况如何
    	完全用typescript重写
    开发工具支持
    	使用javaScript也有好处
        //也有代码的提示啊 参数的类型的信息
```

#### 便利性改进

```js
便利性改进: 方便终端用户使用
	Fragment: 模板更简单
    	//fragment 虚拟的父级,我们都知道在template模板底下是必须要有一个根节点的(和el绑定的元素),不能写两个标签,
    	//vue3 就使用了fragment你在template下面随便写节点 vue自己在外面套一个虚拟父级, 就没有根节点的要求了
    	//自己尝试了一下不行,还是需要一个根节点不知道为啥
    Teleport: 布局更灵活
    	//传送的意思,传送 传输(就好比科幻电影里面的传送门),它可以帮助你在组件内部的某一块东西给他扔到别的地方去显示,
    	//举个栗子 在一条新闻下面有很多条评论,但是前三条评论可能要放到别地地方去显示,很常见的
    Suspense: 强大的异步组件
    	//可以异步的加载组件,比方说一个页面里面用了好多的组件,加载比较慢可能10秒20秒,使用了异步加载组件后就可以先加载页面本身的内容,让本身的内容出来,然后在去加载异步的组件,(在vue2里面也是可以使用扩号impot,去加载异步的组件但是那时候的组件本身是无法适应分包加载的, 而Suspense是将组件本身变成异步的
    composition-api: 逻辑重用
    	//组成,改变组件内部的成分
    	//以前的重用一般说的都是组件重用, 逻辑重用我们一般都是将这个逻辑封装成一个函数,这样没错,但是用起来不方便我们要在所有用这个逻辑的页面上import一遍
    	//composition-api它可以帮助你把你的各种逻辑数据代码函数混入到你的组件里面 ,可以说子啊一定程度上它是一种高阶组件的体现 相当于替代了mixin
    	代替了mixin //为什么替代mixin mixin太危险了 它容易把你整个组件搞乱了
```

#### 生态圈支持

```js
//正在升级中,2020 5月10号 现在还没有出来
生态圈支持.vue3相关配套全面升级
	//选择一个语言和一个库的时候就是在选择它背后的生态圈
	vue-router@next
		//专门为vue3而生的vue-router
	vuex@next//也是为了配合vue3
	vue-cli-plugin-vue-next
		生成vue3项目的cli工具
     text-utils@next
		//专门做测试的工具 大型项目必备的技能 帮助你将项目上线出问题的可能降到最低 
		//这是一个帮公司省钱的工具
	 DevTools.vite
		//DevTools 就是浏览器右上角的小vue图标 
		//vite 它是一个服务器工具 它可以启动一个服务器
```



### vue3.0创建项目和原始的目录结构

```js
vue3.0创建项目和vue2.0是不一样的首相是要将vue2.0的脚手架卸载掉然后安装vue3,0的脚手架 命令就在网上查吧
vue创建项目
	vue create vue3xdemo
然后在下载时会给你一个提示选择我们的预处理器我们就先按照默认的下载,然后就等待下载完成即可

//原始的目录结构
node_modules:安装的依赖包
public	public中的静态资源会被复制到输出目录(dist)
src	资源目录 开发都在这个目录下面
babel.config	应该是es6转es5的配置
package.json	管理文件
//vue已经给我们创建了一个初始的项目结构,但是这个结构还不完善,我们需要新建一下几个目录,新建的目录都在src目录下
views 用于存放我们的页面
store	放置vuex程序
api		放置所有的接口程序
utils	放置工具函数
router	放置路由信息
styles	放置全局样式
components	这个已经有了,用来存放我们页面中的组件,我们可以直接把组件新建在components目录下
assets	这个也有了,用来存放我们的资源文件,视频,音频,图片等

```

### Vue 3.0的目录结构

```
dist:	生成打包后的文件
node_modules	安装的依赖包
public	public中的静态资源会被复制到输出目录(dist)中
src
	api:	与后端交互使用相关方法和配置
		index.js	将api文件夹中的文件统一出口,方便模块使用
	assets	放置一些静态资源,例如图片,图标,字体
	components	一些公共组件
	router	vue-router的相关配置
	store vuex相关配置
		modules	模块化的vuex
		global	全局vuex使用的配置
		index.js	导出vuex所有配置
	views	所有的路由组件
		about	路由对应的文件
	App.vue	路由组件的顶层路由
	main.js	vue的入口文件
	utlis.js	封装的工具函数,例如:时间格式化等
env.local	本地启动项目运行环境配置npx vue-cli-serveice serve 配置环境变量文件
babel.config.js	应该是es6转es5的配置吧
vue.config.js	替代了vue2.0中的build目录
```

### vue创建项目手动添加配置项

```js
vue create mydemo //新建项目 首先会提示你选择一个perset(预设)
一,除最后两个,其他选项都是你之前保存的预设配置,
如果没有配置保存过,则之后两个选项
	default(bable,eslint):默认设置(直接enter)非常适合快速创建一个新项目的原型,没有任何辅助功能的npm包
	Manually select features:自定义配置(按方向键)是我们所需要的面向生产的项目,提供可选功能的npm包
二,手动选择需要添加的配置项
    1?check the features needed for projevt:(press <space> to select,<a> to toggle all,<i> to invert selection)
    2 >()bable //转码器,可以将ES6代码转为ES5代码,从而在现有环境执行,
    3 () TypeScript //TypeScript是一个javaScript(后缀.js)的超集(后缀.ts)包含并扩展了javaScript的语法,需要被编译输出为javaScript在浏览器运行,目前较少人再用
    4 () progressive Web App (PWA) support //渐进式Web应用程序
    5 () Router //vue-router (vue的路由) (每个项目中都必须要装它)
    6 () Vuex // vuex (vue的状态管理模式)
    7 () css Pre-processors // css预处理器(如:less,sass)
    8 () Linter/Formatter //代码风格检测和格式化(如:ESlint)
    9 () Unit Testing //单元测试(unit texts)
    10() E2E Testing //e2e(end to end)测试
    //点击空格添加配置项 添加配置后在括号里面会有一个星号
选择完后直接enter,然后会提示你选择对应功能的具体工具包,选择自己擅长或者使用广泛的(方便遇到问题时百度)
//我选择的是 bable Router Vuex cssPre-Processors 和Linter/Fromatter,选择完后点击enter会给出你几个提示,第一个提示你是否使用router 第二个提示你选择那个css预处理器(我用less),第三个提示你提供一个插键化的javaScript代码检测工具, ESLint+Prettier使用较多,第四个何时检测(默认),第五个如何存放配置(默认),第六个是否保存本次配置(保存的话需要你起个名), 然后就等待搭建项目就ok了
简介:
1,Vue-Router 利用了浏览器自身的hash模式和history模式的特性来实现前端路由(通过调用浏览器的接口)
2,css预处理器 主要为css解决浏览器兼容,简化css代码等问题
3,ESLint: 提供一个插件化的javaScript代码检测工具 ESLint+Prettier //使用较多
```



### vue.config.js配置

​	vue3比vue2版本少了好多东西,没有build目录也没有webpack的配置,那么问题来了,如何去开发我们的项目呢,比如设置d代理,打包的问题?

​	vue3.0项目中需要配置其他参数是,需要新建文件 vue.confing.js,(这文件是固定这么写的),如package.json在同一级目录下

vue.config.js中的配置项

```js
//项目部署的基础路径,我们默认假设你的应用将会部署子啊域名的根部 比如: https://www.my-app.com/
//如果你的应用是部署在一个子路径下,那么你需要在这里指定子路径,比如你的应用部署子啊 https://ww.my-app.com/dist/
//那么 baseURL对应的就是dist
baseURL:"dist/"
//将构建好的文件输入到哪里
outputDir:"dist"
//放置静态资源的地方(js/css/img/font)
assetsaDir:""
 // 用于多页配置，默认是 undefined
  pages: {
    index: {
      // 入口文件
      entry: 'src/main.js',　　/*这个是根入口文件*/
      // 模板文件
      template: 'public/index.html',
      // 输出文件
      filename: 'index.html',
      // 页面title
      title: 'Index Page'
    },
    // 简写格式
    // 模板文件默认是 `public/subpage.html`
    // 如果不存在，就是 `public/index.html`.
    // 输出文件默认是 `subpage.html`.
    subpage: 'src/main.js'　　　　/*注意这个是*/
  },
   // 是否在保存的时候使用 `eslint-loader` 进行检查。
  // 有效的值：`ture` | `false` | `"error"`
  // 当设置为 `"error"` 时，检查出的错误会触发编译失败。
  lintOnSave: true,
// 使用带有浏览器内编译器的完整构建版本
// 查阅 https://cn.vuejs.org/v2/guide/installation.html#运行时-编译器-vs-只包含运行时
runtimeCompiler: false,
 // babel-loader 默认会跳过 node_modules 依赖。
  // 通过这个选项可以显式转译一个依赖。
  transpileDependencies: [/* string or regex */],
// 是否为生产环境构建生成 source map？
  productionSourceMap: true,
// 调整内部的 webpack 配置。
  // 查阅 https://github.com/vuejs/vue-docs-zh-cn/blob/master/vue-cli/webpack.md
  chainWebpack: () => {},
  configureWebpack: () => {},
// CSS 相关选项
  css: {
    // 将组件内的 CSS 提取到一个单独的 CSS 文件 (只用在生产环境中)
    // 也可以是一个传递给 `extract-text-webpack-plugin` 的选项对象
    extract: true,

    // 是否开启 CSS source map？
    sourceMap: false,

    // 为预处理器的 loader 传递自定义选项。比如传递给
    // sass-loader 时，使用 `{ sass: { ... } }`。
    loaderOptions: {},

    // 为所有的 CSS 及其预处理文件开启 CSS Modules。
    // 这个选项不会影响 `*.vue` 文件。
    modules: false
  },
  // 在生产环境下为 Babel 和 TypeScript 使用 `thread-loader`
  // 在多核机器下会默认开启。
  parallel: require('os').cpus().length > 1,
// PWA 插件的选项。
  // 查阅 https://github.com/vuejs/vue-docs-zh-cn/blob/master/vue-cli-plugin-pwa/README.md
  pwa: {},
///
///devServer 配置服务器代理 
///
devServer:{ //开发服务器
    proxy:{ //配置代理
        "/api":{ //代理的项目
            target:"http://www.text.com",  //要访问的跨域的域名
            ws:true,//是否启用websockets
            secure:false,//使用的是http协议则设置false,https协议则设置为true
            changOrigin:true, //开启代理:在本地创建一个虚拟服务端,然后发送请求的数据,并同时接受请求的数据,这样客户端和服务端进行数据的交互就不会有跨域问题了
                
             //注意:如果只是修改域名,则不需要写pathRewrite,但是如果要写,则必须写成:                                        pathRewrite: {’^/allin’: ‘/allin’},相当于吧/allin标识还替换成/allin
            pathRewrite:{ //路径重写
                
            }
        }
    }
}

```

### vue的键盘事件

```js
///vue的键盘事件
v-on:keydown 按下任意键触发
v-on:keypress 除shift,fn,capsLock外的任意键被按住,(连续触发)
v-on:keyup  释放任意键触发
我们还可以绑定某个按键的keycode值,比如 v-on:keyup.13="enter" ; 只有回车键触发,
    也可以绑定别名: 如: v-on:keyup.enter="enter"; 这样也是回车键触发
 	//组合写法,
    vue中也支持组合的写法,例如: @keyup.ait.67="function"  //ait+c 触发此事件
							@click.ctrl="function" //ctrl+click 触发此事件
	//注意
	但是,如果是在自己封装的组件或者是使用一些第三方ui库时候,会发现并不起效果,这时就需要用到 .native 修饰符了
    如: <a-input @keyup.enter.native="teachenter"> //它的作用是在组件的根元素上直接监听一个原生事件        
```

### vue的鼠标事件

//vue的鼠标事件最典型的就是click 单击事件了 这个是最常用的

| click             | 单击事件, 在元素上按下并释放任意鼠标按键     |
| ----------------- | -------------------------------------------- |
| dblclick          | 双击事件, 在元素上双击鼠标按钮               |
| contextmenu       | 右键点击, 在右键菜单显示前触发               |
| mousedown         | 在元素上按下任意鼠标按钮                     |
| mouseenter        | 指针移到有事件监听的元素内触发               |
| mouseleave        | 指针移除元素范围外(不冒泡)                   |
| mousemove         | 指针在元素内移动时持续触发                   |
| mouseover         | 指针移到有事件监听的元素或者它的子元素内     |
| mouseout          | 指针移除元素,或者移到它的子元素上            |
| mouseuo           | 在元素上释放任意鼠标按键                     |
| pointerlockchange | 鼠标被锁定或者解除锁定时触发                 |
| pointerlockerror  | 可能因为一些技术的原因鼠标锁定被禁止时处触发 |
| select            | 有文本被选中时处罚外                         |
| wheel             | 滚轮向任意方向滚动                           |
|                   |                                              |

### vue的事件修饰符

```
vue.js 为v-on提供了事件修饰符来处理DOM事件细节
vue.js通过由点(.)表示的指令后缀来调用修饰符
示例:
v-on:click.stop  //阻止单击事件冒泡
v-on:submit.prevent //提交事件不再重载页面
v-on:click.stop.prevent //修饰符可以串联
v-on:click.capture //添加事件侦听器时使用事件捕获模式
v-on:click.self  //只当事件在该元素本身(而不是子元素)触发时触发回调
v-on:click.once //click事件最少触发一次
```



### axios的请求配置

```js
//aixos的配置选项,只有url是必须的,如果没有指定method,请求将默认使用get
{
    //url是用于请求的服务器URL
    url:'/user',
    //method是创建请求时使用的方法
    method:"get" //default
    //baseURL将自动加在url前面,除非url是一个绝对URL
    //它可以通过设置一个baseURL便于axios实例的方法传递相对URL
   	baseURL:"https://my-web.com"
    //params是即将与请求一起发送的URL参数
    params:{
        id:12345
    },
    //data是作为请求主体被发送的数据只适用于 put,post和patch使用
    data:{id:123}
	//timeout指定请求超时的毫秒数
    //如果请求超过timmeout的时间,请求将被中断
    timeout:1000
    //表示服务器请求的数据类型,可以是'arraybuffer', 'blob', 'document', 'json', 'text', 'stream'
    responseType:"json" //default
}

//params适用于get传递参数
//data适用于post传递参数

///axios常用的方法
//执行get请求
axios.get("url",{
    params:{
        name:123
    }
}).then(res=>{
    //成功之后的执行
}).catch(err=>{
    //请求失败时执行
}).finally(()=>{
    //总是执行
})

//执行post请求
 axios.post("/user",{
　　　　 firstName:"Fred",
 　　　　lastName:"ssass"
　　 })
　　.then(function (response) {
　　　　 console.log(response);
　　})
　　.catch(function (error) {
　　　　 console.log(error);
　　 })

///执行并发请求
function getUserAccount(){
    return axios.get("/user/12");
  }
  function getUserPermissions(){
    return axios.get("/user/123/permission")
  }
  axios.all([getUserAccount(),getUserPermissions()])
    .then(axios.spread(function(acct,perms){
      //两个请求都成功
    }))

//通过传递相关配置来进行请求
//post
axios({
    method:"post",
    url:"",
    data:{
        //发送的参数
    }
})
//get
axios({
    params:{
        //发送的参数
    },
    url:"",
}).then(res=>{
    //请求成功后执行
})
```

### vue3.0路由的坑

```js
//在vue3.0出来后使用路由需要自己下载, npm install vue-router 
//但是你下载完后 写好路由配置就是不能用,页面上报错,让我卡了半天 我想这么简单的东西我都搞不定
//最终我知道哪里的事情了 好像是下载路由后它没有清除掉原有的其他路由,这就尴尬了 我们需要在使用动态路由时,清空一下之前的,然后咱们的路由就可以使用了
//我们需要添加一个自定义方法,来清空之前的,
//在router.js中增加方法:
router.selfaddRoutes = function (params){
  router.matcher = new Router().matcher;
  router.addRoutes(params)
}
//就是将vue原来的其他路由替换成咱下载的路由
```

### vue简单的使用ant.design

```js
//ant.design是vue的一个ui框架
1,下载ant.design
 npm i ant-design-vue
2,引入 这个框架是可以按需引入也可以整个引入就看自己的需求了
	//完整引入 main.js
	import Aant from 'ant-design-vue';
	import "ant-design-vue/dist/antd.css"
	Vue.use(Aant);
	//由于我的不会使用所以完整引入我是写不出来任何东西的
	//按需引入 按钮组件
	import {Button} from 'ant-design-vue'
	import "ant-design-vue/dist/antd.css"	
	Vue.use(Button.name,Button);
	//使用 这个是可以全局使用的
	<a-button type="primary">按钮<a-button>
	
```

### axios的使用

```js
在vue3中axios一般的使用是要在api文件夹下卖新建config.js文件,配置一些请求的通选项,同时还对get和post请求进一步封装
1,下载axios
npm i axios
2,config文件中的配置
import axios from 'axios' //引入axios

// 这个baseUrl要根据实际情况进行改变
axios.defaults.baseURL = "/"
axios.defaults.headers.common["Content-Type"] =
    "application/json; charset=UTF-8"
axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*"

// 请求拦截器 添加token
axios.interceptors.request.use(
    config => {
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

// 响应拦截器即异常处理
axios.interceptors.response.use(
    response => {
        return response
    },
    error => {
        //失败的提示信息
        return Promise.resolve(error)
    }
)

export default {
    // get请求
    get(url, param) {
        return new Promise((resolve, reject) => {
            axios({
                method: "get",
                url,
                params: param,
            })
                .then(res => {
                    resolve(res)
                })
                .catch(error => {
                  //失败的提示信息
                    reject(error)
                })
        })
    },
    // post请求
    post(url, param) {
        return new Promise((resolve, reject) => {
            axios({
                method: "post",
                url,
                data: param,
            })
                .then(res => {
                    resolve(res)
                })
                .catch(error => {
                    //失败的提示信息
                    reject(error)
                })
        })
    },
    // all get 并发请求
    allGet(fnArr) {
        return axios.all(fnArr)
    },
}
//学习的配置
//在api文件夹下新建login.js文件,这里面的函数的作用就是请求后台的login接口
代码如下: 比较简单
import service from './config.js' //引入axios中的方法
var login=(params)=>{ //login实例化的时候 返回axios中的post方法,url就是login参数在使用的时候传过去
    return service.post('login', params);
}
export default login; //将这个类导出

```

### mock的使用

```js
//接着上面的axiso ,有了接口,但是我们没有后台,自己写东西的时候没法请求后台,不过现在前端已经非常强大了,没有后台,我们可以使用mock来模拟后端
//安装mock前,还有一个小问题,就是设置eslint的规则,默认的esline的规则非常严格,我们甚至不能在页面上使用console.log(),这就会给我们调试带来困难,因此我们要禁用一些eslint规则
打开package.json，找到 eslintConfig 项，在找到其下的rules。配置 "no-console": "off" 。就可以关闭eslint对console的限制
///
///mock是啥
///
mock是一个测试工具.mock会拦截ajax请求并可以按照一定规则返回数据以前需要后台返回给我们的数据，现在我们可以使用mock返回了。mock的功能很强大，可以模拟出后端的增删改查等功能。非常方便我们前端进行测试.
//安装
npm i mockjs
//使用
在main.js的同级目录下新建mock.js文件,然后就可以在mock.js文件中定义要拦截的ajax请求并返回数据
//实例: login页面的请求接口
import Mock from 'mockjs' //引入接口调试
Mock.mock('/login','post',(options)=>{
    let data=JSON.parse(options.body)
    let name=data.name;
    let password=data.password;
    if(name==="admin" && password==='admin'){
        return 1;
    }else{
        return 2
    }
})
//这段代码的作用是拦截login接口请求,当账号和密码是admin的时候,就返回请求成功的数据,否者就返回请求失败的数据
//mock的使用
//怎么使用mock呢 非常简单,我们直接在main.js文件中引入mock即可
//配置 在main.js中添加链接
import './mock.js'

```

### js-cookie

```js
//cookie也是存储数据的地方,可以设置过期时间一把它是和vuex配合使用的,等下写vuex的时候在代码里面说一下用法
//下载cookie
npm i js-cookie
```

### vuex

```js
//vuex是vue里面的全局状态管理,在我上个项目里面,使用vuex是为了保持网站的登录状态,比如我们的index页面要求用户必须登录才能够访问,这里就要用vuex了,vuex实例化后交store
1,安装
    npm i vuex
2,使用
	//在store文件夹下,新建index.js
代码如下:
import Vue from 'vue'
import Vuex from 'vuex'//引入vuex
import Cookie from 'js-cookie' //
Vue.use(Vuex); //在Vue中引入vuex

//new关键词一定不能少不然就报错
const store=new Vuex.Store({
    state:{
        name:""
    },
    mutations:{
        //定义loginIn方法 调用这个方法,我们就可以吧用户的用户名存在store里,同时也存在cookie里,cookie的有效期是一天
        loginIn(state,name){
            state.name=name;
            //设置过期时间是一天
            //set:设置,属性名,属性值
            Cookie.set('name',name,{
                expires:1, //过期的时间
            })
        },
        //定义loginOut方法,调用这个方法,我们就可以把用具存在store里的name清空,同时也将存在cookie里的值清空
        loginOut(state){
            state.name='',
            //在cookie中清除这个属性
            Cookie.remove('name')
        }
    }
})
export default store;

3,配置
	//修改main.js,把store引入main.js中,然后在new Vue函数中配置
import store from './store/index.js'
……
new Vue({
 router,
 store,
 render: h => h(App),
}).$mount('#app')
//好了这样我们就可以在项目中全局使用strore了
```

### 路由拦截 	

```js
//上面说cookie的时候说了想要现在用户的状态,加上路由拦截就更加完善了,应该是这三个一起使用
//凡是没有登录的用户(cookie和veux中没有数据)要访问index.vue的时候,统一让他们重定向到login页面,让其登录
//修改main.js,添加如下代码
//设置路由守卫,没用登录的用户是不能直接访问首页的 试试看
import Cookies from 'js-cookie' //引入cookies
router.beforeEach((to, from, next) => {
  let name = Cookies.get('name') || store.state.name;
  if (name) {
    store.commit('loginIn', name);
    next()
  } else {
    if (to.path == '/login') {
      next()
    } else {
      next({
        name: "login",
      })
      store.commit('loginOut')
    }

  }

})
router.afterEach(() => { })
//好了做到这一步就完成了登录到首页这之间的数据处理了,ok
```

### 安装css预处理器

```js
//vue3.0对预处理器做的实在是太好了,基本上什么配置都不用直接下载了就可以在页面上用了,如果不行就换一个都一样
1,下载less
npm i less less-loader
//全局引入 在main.js中
import './../theme/theme.less'
2,下载sass
npm install node-sass --save-dev
npm install sass-loader --save-dev
3,下载stylus
npm install stylus --save 
npm install stylus-loader --save
//现在就用一点最简单的就行了等以后研究好了再详细补充更多的设置,可以不用但是看见了一定要知道这是干什么的
```

### 配置less全局变量

```js
到上一步就可以在页面上使用less了 但是还不能使用less全局变量还须配置:
首先下载  npm i style-resources-loader 我也不知道是啥 下就行了
然后在根目录下的vue.config.js文件中复制代码如下

const path = require('path')
 
module.exports = {
    chainWebpack: config => {
        const types = ['vue-modules', 'vue', 'normal-modules', 'normal']
        types.forEach(type => addStyleResource(config.module.rule('less').oneOf(type)))
    }
}
 
function addStyleResource(rule) {
    rule.use('style-resource')
        .loader('style-resources-loader')
        .options({
            patterns: [path.resolve(__dirname, "./src/_theme.less")] //这里配置的是全局变量的文件需更换
        })
//配置完成后重启一下项目就可以了
   //less全局变量的定义
    配置全局变量前面都要加@然后跟上变量名 如: @back1:red, 
        
```

### Vue插槽的使用 slot slot-scope

```json
首先讲解一下什么是插槽: 插槽,也就是slot,是组件的一个HTML模板,这个模板显示不显示,以及怎样显示有父级组件来决定,实际上slot的核心问题就出现了,是显示不显示和怎样显示
由于插槽是一块模板,所以,对于任何一个组件,从模板种类的角度来分,其实都可以分为非插槽模板和插槽模板两大类
非插槽模板指的是html模板,指的是div,span,ul,table 这些,非插槽模板的显示与隐藏以及怎样显示有插件自身控制
插槽模板是slot,它是一个空盒子,因为它显示与隐藏以及最后用什么样的html模板显示由父组件控制,但是插槽显示的位置却由子组件自身决定,slot写在组件template的哪块,父组件传来的模板将来就显示显示在哪块
//匿名插槽
比如说在index页面中使用子组件,子组件叫做child, 一般child标签只会显示子组件的内容,你在child标签中添加内容是不显示的但是你在子组件页面 中添加slot标签 那么你在父组件添加内容就会在子组件slot标签的位置上显示,
//具名插槽:
匿名插槽没有name值,所有是匿名插槽,那么 插槽添加了name属性,就变成了具名插槽,具名查抄可以在一个组件中出现N次,出现在不同位置
//作用于插槽/带数据的插槽
我也是上网查资料看的但是这里我想写我的理解,带数据的插槽应该算是子组件向父组件传值, 将子组件的数据传递给父组件的插槽里面, 父组件使用 slot-scope="data" 来接收, 子组件通过 <slot :data="data" /> 传值,
父组件中 data等于子组件的data属性 key和value

我现在是有点疑问: slot的核心说明是 slot是一个html模板 这个没错, 这个模板的显示和不显示是有父级组件来决定的但是我在实践中不是这么理解的, 插槽是父组件中书写html模板,在子组件中定义显示的位置,那这不应该是,由子组件来决定这个插槽显示和不显示吗? 我工作也有一段时间了但是一直都没有人给我一起讨论,没有互相学习的人,学习进展有点慢
```

### vue路由守卫

```js
vue-router提供了导航钩子:
全局前置导航钩子 beforEach
全局后置导航钩子 afterEach
他们会在路由即将改变前和改变后进行触发,
所以一般我们判断用户是否登录需要在beforEach导航钩子中进行判断
导航钩子有三个参数:
	1,to:即将要进入的目标路由对象
	2,from:当前导航即将要离开的路由对象
	3,next: 调用该方法后,才能进入下一个钩子函数(afterEach);
		next() //直接进to所指向路由
		next(false) //中断当前路由
		next('route') //跳转制定路由
		next("error") //跳转错误路由
to和from对象信息中就是你路由中配置的信息,name对应name path对应path
//简单案例
router.beforeEach((to,from,next)=>{
    conost id=store.state.userid;
    if(id){
        next()
    }else{
        if(to.path==="/login"){
            next()
        }else{
            next('/login)
        }
    }
})
```



