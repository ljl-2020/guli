import request from '~/utils/request'
export default{
  // 获取课程信息
  getById(id) {
    return request({
      url: `/api/edu/course/get/${id}`,
      method: 'get'
    })
  },

  // 获取播放凭证
  getPlayAuth(vid) {
    return request({
      baseURL: 'http://localhost:8130',
      url: `/api/vod/media/get-play-auth/${vid}`,
      method: 'get'
    })
  },

  // 按条件查询课程
  getList(serarchObj) {
    console.log(serarchObj)
    return request({
      url: '/api/edu/course/list',
      method: 'get',
      params: serarchObj
    })
  },

  // 课程收藏
  save(courseId) {
    return request({
      url: `/api/edu/course-collect/save/${courseId}`,
      method: 'post'

    })
  },

  // 移出收藏
  removeCollect(courseId) {
    return request({
      url: `/api/edu/course-collect/remove/${courseId}`,
      method: 'delete'

    })
  },

  // 获取收藏列表
  list() {
    return request({
      url: `/api/edu/course-collect/list`,
      method: 'get'
    })
  },

  // 嵌套课程信息
  getSubjectNestedList() {
    return request({
      url: '/api/edu/subject/nested-list',
      method: 'get'
    })
  }

}
