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
        <el-table-column prop="pic" label="图片" min-width="5%" align="center">
          <template slot-scope="scope">
            <el-upload
              class="avatar-uploader"
              :action="uploadUrl(scope.row.id)"
              :before-upload="beforeUpload"
              :on-success="handleImgSuccess"
              name="file"
            >
              <img :src="scope.row.pic" class="avatar" />
            </el-upload>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="姓名" min-width="8%" align="center" />
        <el-table-column prop="_sex" label="性别" min-width="5%" align="center" />
        <el-table-column prop="birth" label="生日" min-width="8%" align="center" />
        <el-table-column prop="location" label="地区" min-width="8%" align="center" />
        <el-table-column prop="introduction" show-overflow-tooltip label="简介" min-width="30%" align="center" />
        <el-table-column label="操作" min-width="10%" align="center">
          <template slot-scope="scope">
            <el-button
              @click="edit(scope.$index, scope.row)"
              size="mini"
              type="primary"
              icon="el-icon-plus"
              style="width: 50px; padding: 5px 0;"
              >编辑
            </el-button>
            <el-button
              @click="delete (scope.$index, scope.row)"
              size="mini"
              type="danger"
              icon="el-icon-plus"
              style="width: 50px; padding: 5px 0;"
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
    this.getAllSingers()
  },
  methods: {
    getAllSingers() {
      this.$http.singer.getAllSinger().then(res => {
        if (res.code === 0 && res.data) {
          let data = res.data
          data.forEach(item => {
            if (item.birth) {
              let time = new Date(item.birth)
              item.birth = formatDate(time, 'yyyy-MM-dd')
            }
            switch (item.sex) {
              case 0:
                item._sex = '女'
                break
              case 1:
                item._sex = '男'
                break
              case 2:
                item._sex = '组合'
                break
            }
          })
          this.tableData = res.data
        }
      })
    },
    // 图片上传地址
    uploadUrl(id) {
      return `${process.env.VUE_APP_BASE_URL}/admin/singer/updatePic?id=${id}`
    },
    // 校验图片格式
    beforeUpload(file) {
      const isPic = file.type === 'image/jpeg' || file.type === 'image/png'
      if (!isPic) {
        this.$message.error('上传的头像图片必须为 jpg 或 png 格式')
        return false
      }

      const size = file.size / 1024 / 1024
      if (size >= 2) {
        this.$message.error('上传的头像图片大小必须小于 2M ')
        return false
      }
      return true
    },
    // 上传图片成功后
    handleImgSuccess(res, file) {
      if (res.code === 0) {
        this.getAllSingers()
        this.$notify({
          message: '图片上传并更新成功',
          type: 'success'
        })
      } else {
        this.$notify.error({
          message: '图片上传失败'
        })
      }
      console.log('res', res)
      console.log('file', file)
    },
    edit() {},
    delete() {}
  }
}
</script>

<style>
.el-tooltip__popper {
  max-width: 300px;
}
.avatar-uploader {
  width: 50px;
  height: 50px;
}
.el-upload--text {
  width: 50px;
  height: 50px;
}
.avatar {
  width: 50px;
  height: 50px;
  display: block;
}
</style>
