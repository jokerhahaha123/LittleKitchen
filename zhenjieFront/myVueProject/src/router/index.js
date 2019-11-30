import Vue from 'vue'
import Router from 'vue-router'
import login from '@/pages/login/login'
import register from '@/pages/register/register'

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
      path:'/',
      name:'login',
      component:login
    },
    {
      path: '/temp',
      name: 'temp',
      component: temp
    },


  ]
})
