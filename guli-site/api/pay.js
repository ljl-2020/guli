import request from '~/utils/request'

export default {

  moniPay(orderNo) {
    return request({

      url: `/api/trade/weixin-pay/auth/pay/${orderNo}`,
      method: 'put'
    })
  },
  getOrder(orderNo) {
    return request({

      url: `/api/trade/weixin-pay/auth/get/${orderNo}`,
      method: 'get'
    })
  },
  createNative(orderNo) {
    return request({

      url: `/api/trade/weixin-pay/create-native/${orderNo}`,
      method: 'get'
    })
  }
}
