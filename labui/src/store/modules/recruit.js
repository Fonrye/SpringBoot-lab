import { recruit } from '@/api/recruit'//引入登录 api 接口

const recruits = {
  actions: {
    // 登录
    FindallList() { 
      return new Promise((resolve, reject) => { //封装一个 Promise
          findallList().then(response => { //使用 login 接口进行网络请求
          commit('') //提交一个 mutation，通知状态改变
          resolve(response) //将结果封装进 Promise
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
}
export default recruits