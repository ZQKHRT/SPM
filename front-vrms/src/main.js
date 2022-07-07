// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'; //引入element的js库
import 'element-ui/lib/theme-chalk/index.css'; //引入element样式
import axios from 'axios' //引入axios他就是ajax技术
import './assets/css/main.css'
Vue.prototype.$http=axios //挂载axios在vue对象中，这样axios成为vue对象中的属性
Vue.use(ElementUI) //启用elementUI组件在Vue对象中，就是挂载ElementUI在vue中
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
