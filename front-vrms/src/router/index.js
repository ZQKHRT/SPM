import Vue from 'vue'
import Router from 'vue-router'
import Main from'../components/Main.vue' //导入布局组件，组件名称是Main
import Administrators from '../components/Administrators'
import Logs from '../components/Logs'
import Users from '../components/Users.vue'
import Appointment from '../components/Appointment.vue'
import Address from '../components/Address.vue'
import Vaccines from '../components/Vaccines.vue'
import Login from '../components/Login.vue'
import Welcome from '../components/Welcome.vue'

Vue.use(Router)

let router =  new Router({
  routes: [{ //定义多个路由
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: Login,
    },
    {
      path: '/main',
      name: 'Main', //布局组件
      component: Main, //使用组件
      redirect: '/welcome',
      children:[//定义子组件
      {
        path: '/welcome',
        component: Welcome
      },
      {
        path:'/administrators',
        name:'Administrators',
        component:Administrators
      },
      {
        path:'/logs',
        name:'logs',
        component:Logs
      },
      {
        path:'/vaccines',
        name:'vaccines',
        component:Vaccines
      },
      {
        path:'/address',
        name:'address',
        component:Address
      },
      {
        path:'/users',
        name:'users',
        component:Users
      },
      {
       path:'/appointment',
       name:'appointment',
       component:Appointment
      }
      ]
    }
  ]
})

// 定义一个路由器
router.beforeEach((to, from, next) => {
  if (to.path == '/login') {
    next();
  } else {
    if (sessionStorage.getItem("username")) {
      next();
    } else {
      alert('该用户还未登录，请登录')
      next({
        path: '/login'
      })
    }
  }
})
export default router
