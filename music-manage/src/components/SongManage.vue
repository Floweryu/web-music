<template>
  <div class="singer">
    <el-card class="body">
      <el-table
        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        size="small"
        border
        stripe
      >
        <el-table-column prop="pic" label="图片" min-width="10%" align="center" />
        <el-table-column prop="name" label="姓名" min-width="10%" align="center" />
        <el-table-column prop="sex" label="性别" min-width="5%" align="center" />
        <el-table-column prop="birth" label="生日" min-width="10%" align="center" />
        <el-table-column prop="location" label="地区" min-width="10%" align="center" />
        <el-table-column prop="introduction" label="简介" min-width="20%" align="center" />
        <el-table-column label="操作" min-width="10%" align="center">
          <template slot-scope="scope">
            <el-button
              @click="edit(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-plus"
              style="width: 50px; padding: 7px 0;"
              >编辑
            </el-button>
            <el-button
              @click="delete (scope.$index, scope.row)"
              size="mini"
              type="danger"
              icon="el-icon-plus"
              style="width: 50px; padding: 7px 0;"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <page-separate
      :page-sizes="pageSizes"
      :page-size="pageSize"
      :total-count="tableData.length"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import { formatDate } from '@/utils/index'
import { pageSeparate } from '@/utils/mixin'

export default {
  name: 'SingerManage',
  mixins: [pageSeparate],
  data() {
    return {
      tableData: []
    }
  },
  created() {
    this.$http.singer.getAllSinger().then(res => {
      if (res.code === 0 && res.data) {
        let data = res.data
        data.forEach(item => {
          if (item.birth) {
            let time = new Date(item.birth)
            item.birth = formatDate(time, 'yyyy-MM-dd')
          }
        })
        this.tableData = res.data
      }
    })
  },
  methods: {
    edit() {},
    delete() {}
  }
}
</script>

<style scoped></style>
