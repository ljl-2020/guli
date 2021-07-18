<template>
  <div>
    讲师列表
    <div v-for="(item,index) in items" :key="index">
      <a :href="'teacher/'+item.id">{{ item.name }}</a>
    </div>
  </div>
</template>
<script>
import teacherApi from '~/api/teacher'
export default {
//     // 异步获取数据
//   asyncData() {
//     return teacherApi.getList().then(response => {
//       return {
//         items: response.data.items
//       }
//     })
//   },
  // 异步调用
//   asyncData() {
//     const response = teacherApi.getList()
//     return {
//       items: response.data.items // 报错：response未定义
//     }
//   },

  // 同步调用
  async asyncData() {
    console.log('asyncData')
    const response = await teacherApi.getList()
    console.log(response)
    return {
      items: response.data.items
    }
  },

  data() {
    return {
      test: 'test'
    }
  }
}
</script>

