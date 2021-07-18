import request from '~/utils/request'
export default {

  sendMessage(mobile) {
    return request({

      url: `/api/stmp/send/${mobile}`,
      method: 'get'
    })
  },

  register(member) {
    return request({

      url: '/api/ucenter/member/register',
      method: 'post',
      data: member
    })
  }
}
