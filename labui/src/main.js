import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import router from './router' //引入路由配置
import store from './store' //引入 Vuex 状态管理
import axios from 'axios'
import VueSession from 'vue-session'
import Blob from './vendor/Blob.js'
import Export2Excel from './vendor/Export2Excel.js'
Vue.use(VueSession)

Vue.config.productionTip = false
Vue.prototype.$axios = axios
new Vue({
  render: h => h(App),
  // render: x => x(App)
  // 这里的render: x => x(App)是es6的写法
  // 转换过来就是：  暂且可理解为是渲染App组件
  // render:(function(x){
  //  return x(App);
  // });
  router, //使用路由配置
  store, //使用 Vuex 进行状态管理
  
}).$mount('#app')