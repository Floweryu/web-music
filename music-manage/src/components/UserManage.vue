<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" @click="addUserDialog"> 添加用户 </el-button>
        <div class="search">
          <el-input class="searchInput" prefix-icon="el-icon-search" placeholder="请输入用户名" v-model="registerForm.username"></el-input>
          <el-button type="primary" size="mini" icon="el-icon-search" @click="searchUser">搜索</el-button>
        </div>
      </div>
    </div>
    <el-dialog title="添加用户" :visible.sync="centerDialogVisible" width="400px" center>
      <el-form :model="registerForm" ref="registerForm" label-width="80px">
        <el-form-item prop="username" label="用户名" size="mini">
          <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" size="mini">
          <el-input v-model="registerForm.password" placeholder="密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="性别" size="mini">
          <el-radio-group v-model="registerForm.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="birth" label="生日" size="mini">
          <el-date-picker type="date" placeholder="选择日期" v-model="registerForm.birth" style="width: 100%"></el-date-picker>
        </el-form-item>
        <el-form-item prop="location" label="地区" size="mini">
          <el-input v-model="registerForm.location" placeholder="地区"></el-input>
        </el-form-item>
        <el-form-item prop="phoneNumber" label="手机号" size="mini">
          <el-input v-model="registerForm.phoneNumber" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item prop="email" label="邮箱" size="mini">
          <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item prop="introduction" label="简介" size="mini">
          <el-input v-model="registerForm.introduction" placeholder="简介" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button size="mini" @click="addUser">确定</el-button>
        <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
      </span>
    </el-dialog>
    <el-card class="body">
      <el-table
        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        size="small"
        border
        stripe
      >
        <el-table-column prop="pic" label="图片" min-width="10%" align="center" />
        <el-table-column prop="username" label="用户名" min-width="10%" align="center" />
        <el-table-column prop="password" label="密码" min-width="10%" align="center" />
        <el-table-column prop="sex" label="性别" min-width="5%" align="center" />
        <el-table-column prop="birth" label="生日" min-width="10%" align="center" />
        <el-table-column prop="location" label="地区" min-width="5%" align="center" />
        <el-table-column prop="introduction" label="简介" min-width="10%" align="center" />
        <el-table-column label="操作" min-width="10%" align="center">
          <template slot-scope="scope">
            <el-button @click="editUserDialog(scope.row)" size="mini" type="primary" icon="el-icon-plus" style="width: 50px; padding: 7px 0"
              >编辑
            </el-button>
            <el-button @click="deleteUser(scope.row)" size="mini" type="danger" icon="el-icon-plus" style="width: 50px; padding: 7px 0"
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
  name: 'UserManage',
  mixins: [pageSeparate],
  data() {
    return {
      centerDialogVisible: false,
      centerDialogVisible2: false,
      tableData: [],
      registerForm: {
        //添加框
        username: '',
        password: '',
        sex: '',
        birth: '',
        location: '',
        introduction: '',
        phoneNumber: '',
        email: ''
      }
    }
  },
  created() {
    this.listAll()
  },
  methods: {
    //添加用户
    addUser() {
      this.$http.user
        .addUser(JSON.stringify(this.registerForm))
        .then(res => {
          if (res.code === 0) {
            this.$notify({
              message: '添加成功',
              type: 'success'
            })
          }
          this.centerDialogVisible = false
          this.listAll()
        })
        .catch(err => {
          console.log(err)
        })
    },
    //展示所有用户
    listAll() {
      this.$http.user.getAllUsers().then(res => {
        if (res.code === 0 && res.data) {
          let data = res.data
          data.forEach(item => {
            if (item.birth) {
              let time = new Date(item.birth)
              item.birth = formatDate(time, 'yyyy-MM-dd')
            }
            if (item.sex == 0) {
              item.sex = '女'
            } else if (item.sex == 1) {
              item.sex = '男'
            }
          })
          this.tableData = res.data
        }
      })
    },
    edit() {},
    delete() {}
  }
}
</script>

<style scoped>
.handle-box {
  margin-bottom: 20;
  display: flex;
  justify-content: space-between;
}
.searchInput {
  /* width: 100px; */
  margin-right: 20px;
}
.search {
  display: flex;
}
</style>
