import request from '~/utils/request'
import cookie from 'js-cookie'
export default{
  submitLogin(user) {
    return request({

      url: '/api/ucenter/member/login',
      method: 'post',
      data: user
    })
  },

  getLoginInfo() {
    return request({

      url: '/api/ucenter/member/getMemberInfo',
      method: 'get',
      // 通过请求头发送token
      headers: { 'token': cookie.get('guli_token') }
    })
  }
}
