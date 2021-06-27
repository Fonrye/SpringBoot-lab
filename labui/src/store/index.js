import Vue from 'vue' //引入 Vue
import Vuex from 'vuex' //引入 Vuex
import user from './modules/user' //引入 user module
import menu from './modules/menu'
import recruit from './modules/recruit'
Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    user, //使用 user.js 中的 action
	menu,
	recruit
  }
})

export default store