<template>
  <div class="song">
    <el-card class="body">
      <el-table
        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        size="small"
        border
        stripe
      >
        <el-table-column prop="pic" label="封面图" min-width="10%" align="center" />
        <el-table-column prop="name" label="歌名" min-width="10%" align="center" />
        <el-table-column prop="singerId" label="歌手id" min-width="5%" align="center" />
        <el-table-column prop="singerName" label="歌手" min-width="5%" align="center" />
        <el-table-column prop="url" label="路径" min-width="10%" align="center" />
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
import { pageSeparate } from '@/utils/mixin'

export default {
  name: 'SongManage',
  mixins: [pageSeparate],
  data() {
    return {
      tableData: []
    }
  },
  created() {
    this.$http.songs.getAllSongs().then(res => {
      if (res.code === 0 && res.data) {
        let data = res.data
        data.forEach(item => {
          this.$http.singer
            .getSingerById({
              params: {
                id: item.singerId
              }
            })
            .then(res => {
              let singerName = res.data.name
              Object.defineProperty(item, 'singerName', {
                get() {
                  return singerName
                }
              })
            })
        })
        this.tableData = data
      }
      console.log(this.tableData)
    })
  },
  updated() {
    console.log(this.tableData)
  },
  methods: {
    edit() {},
    delete() {}
  }
}
</script>

<style scoped></style>
