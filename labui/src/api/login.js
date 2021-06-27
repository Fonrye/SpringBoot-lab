import request from '@/utils/request' //引入封装好的 axios 请求

export function login(userId, pwd) { //登录接口
  return request({ //使用封装好的 axios 进行网络请求
    url: '/userlogin/login',
    method: 'post',
    data: { //提交的数据
      userId,
      pwd
    }
  })
}