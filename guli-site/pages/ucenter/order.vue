<template>
  <div>
    <article class="col-7 f1" style="float: left;margin-top: 50px;">
      <section>
        <div>
          <section class="c-infor-tabTitle c-tab-title">
            <a href="javascript: void(0)" title="我的订单" class="current">订单列表</a>
          </section>
        </div>
        <div class="mt40">
          <section v-if="orderList.length === 0" class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 m110 vam">您还没有订单哦</span>
          </section>

          <!--表格-->
          <el-table v-if="orderList.length > 0" :data="orderList" element-loading-text="数据加载中" border fit highlight-current-row>
            <el-table-column label="课程信息" align="center" >
              <template slot-scope="scope">
                <div class="info" >
                  <div class="pic" >
                    <img :src="scope.row.courseCover" alt="scope.row.courseTitle" width="100">
                  </div>
                  <div class="title">
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
                {{ scope.row.totalFee/100 }}
              </template>
            </el-table-column>

            <el-table-column label="创建时间" width="100" align="center">
              <template slot-scope="scope">
                {{ scope.row.gmtCreate.substr(0,10) }}
              </template>
            </el-table-column>

            <el-table-column label="订单状态" width="100" align="center">
              <template slot-scope="scope">
                <el-tag :type="scope.row.status == '0' ? 'warning':'success'" >{{ scope.row.status == 0 ? '未支付' : '已支付' }}</el-tag>
              </template>
            </el-table-column>

            <el-table-column label="操作" width="150" align="center">
              <template slot-scope="scope">
                <router-link v-if="scope.row.status === 0" :to="'/order/'+scope.row.id">
                  <el-button style="width: 58px;margin-right: 5px;" type="text" size="mini" icon="el-icon-edit" >去支付</el-button>
                </router-link>
                <router-link v-if="scope.row.status ===1 " :to="'/course/'+scope.row.courseId">
                  <el-button style="width: 58px;margin-right: 5px;" type="text" size="mini" icon="el-icon-edit" >去学习</el-button>
                </router-link>
                <i class="el-icon-delete" style="cursor:pointer" title="删除订单" @click="removeById(scope.row.id)" />
              </template>
            </el-table-column>
          </el-table>
        </div>
      </section>
    </article>
  </div>
</template>
<script>
import orderApi from '~/api/order'
export default {
  data() {
    return {
      orderList: []
    }
  },
  created() {
    this.fetchOrderList()
  },
  methods: {
    fetchOrderList() {
      orderApi.getList().then(response => {
        this.orderList = response.data.orderList
      })
    },
    removeById(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        orderApi.removeById(id).then(response => {
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
