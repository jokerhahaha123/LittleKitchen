import Vue from 'vue'
import Router from 'vue-router'
import login from '@/pages/login/login'
import register from '@/pages/register/register'
import home from '@/components/Home'

import temp from '@/pages/temp'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/register',
      name:'register',
      component:register,
    },
    {
      path:'/home',
      name:'home',
      component:home
    },
    {
      path: '/',
      name: 'temp',
      component: temp
    },
    {
      path:'/login',
      name:'login',
      component:login
    }


  ]
})
