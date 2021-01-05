<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" @click="centerDialogVisible = true"> 添加用户 </el-button>
      </div>
    </div>
    <el-dialog title="添加用户" :visible.sync="centerDialogVisible" width="400px" center>
      <el-form :model="registerForm" ref="registerForm" label-width="80px">
        <el-form-item prop="name" label="用户名" size="mini">
          <el-input v-model="registerForm.name" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" size="mini">
          <el-input v-model="registerForm.password" placeholder="密码"></el-input>
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
        <el-form-item prop="introduction" label="简介" size="mini">
          <el-input v-model="registerForm.introduction" placeholder="简介" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
        <el-button size="mini" @click="addUser">确定</el-button>
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
        <el-table-column prop="username" label="姓名" min-width="10%" align="center" />
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
              style="width: 50px; padding: 7px 0"
              >编辑
            </el-button>
            <el-button
              @click="delete (scope.$index, scope.row)"
              size="mini"
              type="danger"
              icon="el-icon-plus"
              style="width: 50px; padding: 7px 0"
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
      tableData: [],
      registerForm: {
        //添加框
        name: '',
        password: '',
        sex: '',
        birth: '',
        location: '',
        introduction: ''
      }
    }
  },
  created() {
    this.$http.user.getAllUsers().then((res) => {
      if (res.code === 0 && res.data) {
        let data = res.data
        data.forEach((item) => {
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
  methods: {
    //添加用户
    // addUser() {
    //   this.$http.user.addUser().then(res => {
    //     if (res.code === 0 && res.data) {
    //     let data = res.data
    //     data.forEach(item => {
    //       if (item.birth) {
    //         let time = new Date(item.birth)
    //         item.birth = formatDate(time, 'yyyy-MM-dd')
    //       }
    //     })
    //     this.tableData = res.data
    //   }
    //   })
    //   this.centerDialogVisible = false;
    // }
    //     let d = this.registerForm.birth;
    //     let dateTime = d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate();
    //     let params = new URLSearchParams();
    //     params.append('name',this.registerForm.name);
    //     params.append('sex',this.registerForm.sex);
    //     params.append('password',this.registerForm.password);
    //     params.append('birth',dateTime);
    //     params.append('location',this.registerForm.location);
    //     params.append('introduciton',this.registerForm.introduction);
    //     addUser(params)
    //     .then(res => {
    //         if(res.code == 1){
    //             this.notify("添加成功","success");
    //         }else{
    //             this.notify("添加失败","error");
    //         }
    //     })
    //     .catch(err => {
    //         console.log(err);
    //     });
    //     this.centerDialogVisible=false;
    // }
  }
}
</script>

<style scoped>
.handle-box {
  margin-bottom: 20;
}
</style>
