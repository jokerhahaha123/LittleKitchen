import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/Home/Home'
import UserInfo from '@/pages/UserInfo/UserInfo'
import User from '@/components/User'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/info',
      name: 'UserInfo',
      component: UserInfo
    },
    {
      path: '/user',
      name: 'User',
      component: User
    }
  ]
})
