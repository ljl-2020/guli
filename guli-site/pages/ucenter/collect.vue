<template>
  <div>

    <article class="col-7 f1" style="float: left;margin-top: 50px;">
      <section>
        <div>
          <section class="c-infor-tabTitle c-tab-title">
            <a href="javascript: void(0)" title="我的收藏" class="current">收藏列表</a>
          </section>
        </div>
        <div class="mt40">
          <section v-if="courseCollectList.length === 0" class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 m110 vam">您还没有收藏哦</span>
          </section>

          <!--表格-->
          <el-table v-if="courseCollectList.length > 0" :data="courseCollectList" element-loading-text="数据加载中" border fit highlight-current-row>
            <el-table-column label="课程信息" align="center" >
              <template slot-scope="scope">
                <div class="info" >
                  <div class="pic" >
                    <img :src="scope.row.cover" alt="scope.row.courseTitle" width="100">
                  </div>
                  <div class="title">
                    <p> 课时：{{ scope.row.lessonNum }} </p>
                    <a href="">{{ scope.row.courseTitle }}</a>
                    <input :value="scope.row.courseId" hidden>
                  </div>
                </div>
              </template>
            </el-table-column>
            <el-table-column label="讲师名称" width="100" align="center">
              <template slot-scope="scope">
                {{ scope.row.teacherName }}
              </template>
            </el-table-column>

            <el-table-column label="价格" width="100" align="center">
              <template slot-scope="scope">
                {{ scope.row.price }}
              </template>
            </el-table-column>

            <el-table-column label="创建时间" width="100" align="center">
              <template slot-scope="scope">
                {{ scope.row.gmtCreate.substr(0,10) }}
              </template>
            </el-table-column>
            <el-table-column label="操作" width="150" align="center">
              <template slot-scope="scope">
                <router-link :to="'/course/'+scope.row.courseId">
                  <el-button style="width: 58px;margin-right: 5px;" type="text" size="mini" icon="el-icon-edit" >去学习</el-button>
                </router-link>
                <i class="el-icon-delete" style="cursor:pointer" title="删除订单" @click="removeById(scope.row.courseId)" />
              </template>
            </el-table-column>
          </el-table>
        </div>
      </section>
    </article>
    <h3/>
  </div>
</template>
<script>
import courseApi from '~/api/course'
export default {
  data() {
    return {
      courseCollectList: []
    }
  },
  created() {
    this.fetchOrderList()
  },
  methods: {
    fetchOrderList() {
      courseApi.list().then(response => {
        this.courseCollectList = response.data.items
      })
    },
    removeById(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        courseApi.removeCollect(id).then(response => {
          this.fetchOrderList()
          this.$message({
            type: 'success',
            message: response.message
          })
        })
      }).catch((err) => {
        if (err === 'cancel') {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        }
      })
    }

  }
}
</script>
