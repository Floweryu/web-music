<template>
  <dir class="table">
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
        <el-button size="mini">确定</el-button>
      </span>
    </el-dialog>
  </dir>
</template>

<script>
export default {
  name: 'UserManage',
  data() {
    return {
      centerDialogVisible: false,
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
    // this.addUser
  },

  methods: {
    //添加用户
    // addUser() {
    //   this.$http.user.addUser().then((res) => {
    //   })
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
