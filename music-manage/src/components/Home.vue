<template>
  <div>
    <div class="body">
      <div class="container circle">
        <div class="box1 circle center"></div>
        <div class="box2 circle center"></div>
        <div class="box3 circle center"></div>
        <div class="box4 circle center"></div>
        <div class="box5 circle center"></div>
        <div class="box6 circle">
          <div class="coil" style="--i:0"></div>
          <div class="coil" style="--i:1"></div>
          <div class="coil" style="--i:2"></div>
          <div class="coil" style="--i:3"></div>
          <div class="coil" style="--i:4"></div>
          <div class="coil" style="--i:5"></div>
          <div class="coil" style="--i:6"></div>
          <div class="coil" style="--i:7"></div>
        </div>
      </div>
    </div>
    <div class="content">
      <p class="font">应用软件实践</p>
    </div>
    <div class="content">
      <p class="font">北京化工大学</p>
    </div>
    <div class="el-table">
      <el-table border="true" :data="tableData" fit="true">
        <el-table-column prop="duty" label="职责" width="100"></el-table-column>
        <el-table-column prop="name" label="姓名" width="100"></el-table-column>
        <el-table-column prop="class" label="班级" width="100"></el-table-column>
        <el-table-column prop="id" label="学号" width="100"></el-table-column>
        <el-table-column prop="division" label="分工" width="300"></el-table-column>
      </el-table>
    </div>
    <div class="content">
      <el-link class="fontLink" href="https://github.com/Pink-Smile/web-music" type="primary">Github项目地址</el-link>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tableData: [
        {
          duty: '组长',
          name: '孟铃翔',
          class: '计科1703',
          id: '2017040314',
          division: '歌曲管理的前后端编写'
        },
        {
          duty: '组员',
          name: '车子杰',
          class: '计科1703',
          id: '2017040314',
          division: '用户管理的前后端编写以及登录页面'
        },
        {
          duty: '组员',
          name: '张俊峰',
          class: '计科1703',
          id: '2017040314',
          division: '歌手管理的前后端编写，搭建框架'
        }
      ]
    }
  }
}
</script>

<style scoped>
.el-table {
  margin: auto;
  width: 750px;
  padding: 10px;
  margin-top: 10px;
  border-radius: 2%;
}
.fontLink {
  font-size: 40px;
  font-weight: 1000;
  color: darkgray;
}
.font {
  font-size: 40px;
  font-weight: 1000;
}
.content {
  margin: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.body {
  /* 弹性布局 让页面元素垂直+水平居中 */
  display: flex;
  justify-content: center;
  align-items: center;
  /* 让页面始终占浏览器可视区域高度 */
  height: 40vh;
}
/* 定义一下几个盒子相同的部分 */
.circle {
  /* 圆形 */
  border-radius: 50%;
}
.center {
  position: absolute;
  top: 50%;
  left: 50%;
  /* 元素走自身高度/宽度 的一半 */
  transform: translate(-50%, -50%);
}
.container {
  /* 相对定位 */
  position: relative;
  width: 300px;
  height: 300px;
  border: 1px solid rgb(18, 20, 20);
  background-color: #384c50;
  /* 盒子阴影 默认是外部阴影 写了inset 就是内部阴影 */
  box-shadow: 0 0 32px 8px rgb(18, 20, 20), 0 0 4px 1px rgb(18, 20, 20) inset;
}
.container .box1 {
  width: 238px;
  height: 238px;
  background-color: rgb(22, 26, 27);
  box-shadow: 0 0 4px 1px #52fefe;
}
.container .box2 {
  width: 220px;
  height: 220px;
  background-color: #fff;
  box-shadow: 0 0 5px 1px #52fefe, 0 0 5px 4px #52fefe inset;
}
.container .box3 {
  width: 180px;
  height: 180px;
  background-color: #073c4b;
  box-shadow: 0 0 5px 4px #52fefe, 0 0 6px 2px #52fefe inset;
}
.container .box4 {
  width: 120px;
  height: 120px;
  border: 1px solid #52fefe;
  background-color: #fff;
  box-shadow: 0 0 2px 1px #52fefe, 0 0 10px 5px #52fefe inset;
}
.container .box5 {
  width: 70px;
  height: 70px;
  border: 5px solid #1b4e5f;
  box-shadow: 0 0 7px 5px #52fefe, 0 0 10px 10px #52fefe inset;
}
.container .box6 {
  position: relative;
  width: 100%;
  height: 100%;
  /* 动画 名称 时长 linear 是匀速运动 infinite是无限次播放 */
  animation: rotate 3s linear infinite;
}
.container .box6 .coil {
  position: absolute;
  width: 30px;
  height: 20px;
  /* calc方法自动计算位移距离 */
  top: calc(50% - 110px);
  left: calc(50% - 15px);
  background-color: #073c4b;
  box-shadow: 0 0 5px #52fefe inset;
  /* calc方法自动计算数值 var函数调用了我们刚刚给元素定义的--i属性值 然后分别乘以45度 算出各自的度数 */
  transform: rotate(calc(var(--i) * 45deg));
  /* 这个是旋转的中心 */
  transform-origin: center 110px;
}
</style>
