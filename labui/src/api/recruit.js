import request from '@/utils/request' //引入封装好的 axios 请求

export function findallList() { 
  return request({ //使用封装好的 axios 进行网络请求
    url: '/recruit/list',
    method: 'get',
    data: { //提交的数据
     
    }
  })
}