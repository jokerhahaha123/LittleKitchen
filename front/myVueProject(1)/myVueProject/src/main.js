// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import BootstrapVue from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@/assets/icon/iconfont.css'
import toastRegistry from '@/assets/toast/index'
import axios from 'axios';
Vue.prototype.$http = axios;

axios.defaults.baseURL='/api';
axios.defaults.headers.post['Content-Type']='application/json';

Vue.use(ElementUI);
Vue.use(BootstrapVue);
Vue.use(toastRegistry);

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
