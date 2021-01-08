<!--
 * @Author Jeffrey
 * @Date: 2021-01-08 
-->
<template>
  <div class="song">
    <!-- <header-top @input-data="searchData" @add-singer="addSinger" -->
    <el-card class="body">
      <el-table
        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        size="small"
        border
        stripe
      >
        <el-table-column prop="pic" label="歌曲封面" min-width="10%" align="center" />
        <el-table-column prop="name" label="歌名" min-width="5%" align="center" />
        <el-table-column prop="singerName" label="歌手" min-width="5%" align="center" />
        <el-table-column prop="url" label="路径" min-width="10%" align="center" />
        <el-table-column prop="introduction" label="简介" min-width="30%" align="center" />
        <el-table-column label="操作" min-width="10%" align="center">
          <template slot-scope="scope">
            <el-button
              @click="editSong(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-edit"
              style="width: 50px; padding: 7px 0;"
              >编辑
            </el-button>
            <el-button
              @click="deleteSong(scope.$index, scope.row)"
              size="mini"
              type="danger"
              icon="el-icon-delete"
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
    <song-dialog
      ref="child"
      :is-edit-button="isEditButton"
      :edit-value="editValue"
      :dialog-visible="dialogFormVisible"
      @dialog-cancel="dialogCancel"
    />
  </div>
</template>

<script>
// import HeaderTop from '@/components/common/HeaderTop'
import { pageSeparate } from '@/utils/mixin'
import SongDialog from '@/components/dialog/SongDialog.vue'

export default {
  name: 'SongManage',
  components: {
    // HeaderTop
    SongDialog
  },
  mixins: [pageSeparate],
  data() {
    return {
      tableData: [],
      isEditButton: false,
      dialogFormVisible: false,
      editValue: {},
      selectRows: []
    }
  },
  created() {
    this.getAllSongsWithSingerName()
  },
  methods: {
    // 编辑歌曲信息
    editSong(index, row) {
      this.isEditButton = true
      this.dialogFormVisible = true
      this.editValue = row
    },
    // 接收弹窗的取消事件
    dialogCancel() {
      this.dialogFormVisible = false
    },
    // 获取带歌手名的歌曲列表
    getAllSongsWithSingerName() {
      this.$http.songs.getAllSongsWithSingerName().then(res => {
        if (res.code === 0 && res.data) {
          this.tableData = res.data
        }
      })
    },
    // 删除歌曲时的API操作
    deleteSongApi(data) {
      let query = {
        params: {
          id: data
        }
      }
      this.$http.songs
        .deleteSong(query)
        .then(res => {
          if (res.code === 0) {
            this.$notify({
              message: '删除歌曲成功',
              type: 'success'
            })
            this.getAllSongsWithSingerName()
          }
        })
        .catch(err => {
          this.$notify.error({
            message: err + ': 删除歌曲失败'
          })
        })
    },
    // 删除歌手信息
    async deleteSong(index, row) {
      this.$confirm('此操作将永久删除歌曲, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async () => {
          let data = row.id
          await this.deleteSongApi(data)
        })
        .catch(err => {
          console.log(err)
          this.$notify.error({
            message: err + ': 取消删除操作'
          })
        })
    }
  }
}
</script>

<style scoped></style>
