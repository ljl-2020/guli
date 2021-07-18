import request from '~/utils/request'
export default {

  getTopBannerAdList() {
    return request({

      url: '/api/cms/ad/list/1413344090791108609',
      method: 'get'
    })
  },

  getIndexData() {
    return request({
      url: '/api/edu/index/get',
      method: 'get'
    })
  }
}
