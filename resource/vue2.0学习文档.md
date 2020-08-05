### vue简介

vue是一套用于构建用户界面的渐进式框架,与其他大型框架不同的,vue被设计为可以自底向上逐层应用,vue的核心库只关注视图层,不仅容易上手,还便于与第三方库或即有项目整合

简而言之:vue.js是一个构建数据驱动的web界面的渐进式框架,Vue.js的目标是通过尽可能简单的API实现响应的数据绑定和组合的视图组件,核心是一个相应的数据绑定系统

Vue的性能如下:

​	轻量级的框架,双向数据绑定,指令,插件化

### 创建vue项目

```js
在新的电脑上创建vue项目需要先安装 webpack和vue-cli(vue脚手架)安装如下:
在小黑盒里面输入指令:
	//webpack
	npm i webpack -g
	//vue 2脚手架
	npm i vue-cli -g  
    //vue 3 脚手架
    npm install-g @vue/cli
//好到这一步就将vue的脚手架安装完成了接下来就是创建vue项目了
	//初始化vue项目(创建vue项目)
	vue init webpack demo
    //运行vue项目
    nmp run dev
```

### vue项目目录介绍

在上面的操作中我们已经创建完成vue的项目了接下来我们了解一下vue项目目录的含义与作用

| build目录    | 项目构建(webpack)相关代码                                   |
| :----------- | ----------------------------------------------------------- |
| config       | 配置目录,包括端口号等,我们初学可以使用默认的                |
| node_modules | npm加载的项目依赖模块,(第三方插件)                          |
| src          | 这里是我们要开发的目录,基本上所有要做的事情都在这个目录里面 |
| static       | 静态资源目录,如图片,字体等                                  |
| index.html   | 首页入口文件,你可以添加一些mate 信息或统计代码啥的          |
| package.json | 项目配置文件                                                |
| .xxx文件     | 其他的都是一些配置文件,git配置,语法配置等等 一般不用管      |

src目录介绍,这个目录比较重要

| assets     | 放置一些图片,如logo等                                    |
| ---------- | -------------------------------------------------------- |
| components | 目录里面放一些组件可以不写,(都要写的)                    |
| App.vue    | 项目入口文件,我们可以直接将组件写这里,而不使用components |
| main.js    | 项目的核心文件                                           |

### Vue 的指令

```js
元素绑定:
	非表单元素绑定数据: {{ }} v-text v-html,如果不包含标签,建议使用{{}},这个方便好用,包含标签就要使用v-			html了这个指令可以解析html数据
	表单元素绑定数据:v-model 数据双向绑定
基本指令:
	v-if v-show 显示和隐藏 
		v-if="true/false" 区别:v-show是采用的display:none;v-if采用的是惰性加载
	v-else
    	为 v-if 或者 v-else-if 添加“else 块”。前一兄弟元素必须有 v-if 或 v-else-if。
	v-else-if
    	表示 v-if 的 “else if 块”。可以链式调用。前一兄弟元素必须有 v-if 或 v-else-if。
	v-bind 绑定属性
		简写为 :   <img :src="user.img"> //user.img为data里面的变量
	class 动态加载
		<li v-for='(item,index) in arr' 
			:class='[index%2==0?"red":"yellow"]'>{{item}}</li>
		//判断条件 如果 下标取余2等于0 class名就等于red 否者(等于1)class名就等于yellow
	v-on 绑定事件 简写 @
        	<div @click="cli()"><div>  //点击div触发vue中methods方法中的cli事件
     v-for 循环
      	<div v-for="(item,index) in arr" :key="index"><div>
          //循环vue data中的ar数组 arr有几项数据就循环几个div
          //没一个div对应arr中的每一项数据 item是每一项 index是每一项的下标
  
        
```

### Vue的属性

```js
//vue的属性值对应的都是:{}对象
data: vue中的变量都在data中定义
watch: 作用 监听data中数据的变化
computeds:计算属性
filters:过滤器
methods:vue的方法都写在这里
props: 接受参数,接受父组件传来的参数
components:组件 当前vue的子组件(一般都是抽离出去单独写)
```

### Vue的生命周期

```js
beforeCreate //加载之前 什么都没有
created //加载完成 vue的data和methods有了,但是要挂载的元素还没有
beforeMount //挂载前 要挂载的元素找到了 页面还是空的{{}}
mounted //挂载完成 获取数据 数据就放入页面
beforUpdate //数据更新前
updated //数据更新后
beforeDestroy //销毁之前
destroyed //销毁
```

### Vue设置代理和解决跨域

```js
//现在的开发模式一般采用前后端分离模式,所以在我们请求后端接口时会请求失败,因为接口不能跨域请求,所以在vue中我们就要配置跨域代理
//找到根目录下的config文件夹打开里面的index.js文件,在index.js文件中dev配置对象中的proxytable属性,这里是一个对象,它就是vue专门来配置代理的
//下面对这个对象属性进行分析:
	proxyTable:{ //配置代理
        '/api':{  //配置所有以 '/api'开头的请求路径
            target:"代理目标的基础路径",
            changeOrigin:true, //支持跨域
		   pathRewrite:{ //重写路径: 替换target中的请求地址
               "^/api":"target属性中的地址"
           }
            
        }
    }
//配置完以上的属性后 从新起动项目, 这个时候我们就已经设置好了本地api代理了
//然后我们在请求数据时前面写上 "/api"(就相当于上面我们配置的目标基础路径) 后面跟上接口我们就可以请求成功接口了
```

### 下载Axios或其他第三方插件的过程及使用

```js
//我们就已Axios为例
//下载Axios
1,打开命令行输入: npm i axios //等待下载完成即可
2,全局引用Axios //在main.js中引入
import axios from 'axios' //在main.js中引入
Vue.prototype.$http=axios; //将axiso绑再vue的原型链上以$http使用 
//完成上面的那一步我们就可以在项目中全局使用了
3,使用:
	this.$http({
        url:""
    }).then((value){  //成功之后的回调
            
            })
  //不过写它里面的属性时是没有提示的很恶心   
    
 ///
 ///vue动画 animate.css
 ///
 animate是一个很方便的动画库,可以通过npm直接安装
1,下载
	npm install animate.css --save
 2,使用,
     使用方法是在标签上的class属性,第一个是定死 animate,第二个是你使用的动画名称
	<div class="animated bounce">Example</div>
3,我在网上没有看到引入方面的东西,可能是不需要引入的,但是 根据我的理解应该是:
	全局使用的话 在main.js中 import form 将animate 引过来 然后 Vue.use() 一下
    局部使用的话 应该也是 import '路径' 将animate引过来 
    这是我的猜想可能是 下次试一试
```

### 组件之间传值

​	父组件向子组件传值

```
1,在父组件的子组件上定义自定义属性用来接收父组件的数据
2,在子组件的props中,引入自定义属性,就可以使用了
例:
	<v-child :a="data" /> //父组件中
	props:["data"] //子组件中
	//在子组件中直接使用data就可以了
```

子组件向父组件传值

```
//子组件通过事件$emit() 触发父组件上的方法并传值
//父组件通过回调函数来接收参数
例:
子组件:
	mounted:{
		this.$emit("event1","子组件发送的数据")
		//它接受两个参数,第一个参数是字符串形式的,第二个参数就是你要传的参数
		//第一个参数在父组件中是以事件的形式
	}
父组件:
	<v-child @event1=childData($event) /> //接收子组件的数据
	//事件就是$emit的第一个参数触发的方法中必须有$event参数 这个参数就是子组件发过来的数据
	methods:{
		childData(value){
			console.log(value) //子组件发送的数据
		}
	}
```

非父子组件传值

```js
1,在main.js中创建空的实例对象
Vue.prototype.ev=new Vue();
2,发送数据
	mounted(){
		this.ev.$emit("aa","发送的数据")
	}
	
3,接收数据 //注意接收数据要子啊created方法中接收 不知道怎么回事 在mounted中不行,头痛
	created(){
		this.ev.$on("aa",(value){
		console.log(value) //接收的参数
		})
	}
	
```

### 下载css预处理器

```js
//vue中使用less
安装less依赖，npm install less less-loader --save

//vue中使用sass
npm install --save-dev sass-loader   //sass-loader依赖于node-sass
npm install --save-dev node-sass

//vue中使用stylus
npm install stylus stylus-loader --save-dev

//这个三个预处理器试了试sass和less都没成功,就stylus成功了,无语
```

### Vue代码片段

```json
"Print to console":{
		"prefix": "vue",
			"body": [
				"<template>",
				"  <div class=\"wrapper\">",
				"    $0",
				"  </div>",
				"</template>",
				"",
				"<script type=\"text\/ecmascript-6\">",
				"export default {",
				"  components: {},",
				"  props: [],",
				"  data () {",
				"    return {",
				"    };",
				"  },",
				"  watch: {},",
				"  computed: {},",
				"  methods: {},",
				"  mounted () {}",
				"};",
				"</script>",
				"<style  scoped>",
				".wrapper{}",
				"</style>",
				""
			],
			"description": "A vue file template"
	}

//在开发中有了这个代码片段之后 在页面上输入 vue 就自动生成以上的基本代码
```

