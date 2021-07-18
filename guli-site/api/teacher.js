import request from '~/utils/request'
export default{
  // 获取所有讲师列表
  getList() {
    return request({
      url: '/api/edu/teacher/list',
      method: 'get'
    })
  },

  // 获取讲师详情
  getById(id) {
    return request({
      url: `/api/edu/teacher/get/${id}`,
      method: 'get'
    })
  }
}
