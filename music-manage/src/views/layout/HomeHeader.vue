<!--
 * @Author: Zhang JunFeng
 * @Date: 2021-01-03
 -->
<template>
  <div id="home-header">
    <!-- 折叠按钮 -->
    <div class="collapse-btn" @click="collapseChange">
      <i v-if="!collapse" class="el-icon-s-unfold"></i>
      <i v-else class="el-icon-s-fold"></i>
    </div>
    <div class="logo">{{ '后台管理 ' }}</div>
  </div>
</template>

<script>
import bus from '@/utils/bus'
export default {
  name: 'HomeHeader',
  data() {
    return {
      collapse: false
    }
  },
  created() {},
  methods: {
    // 发送 collpse 到 兄弟组件home-sidebar 和父组件 home
    collapseChange() {
      this.collapse = !this.collapse
      bus.$emit('collapse', this.collapse)
    }
  }
}
</script>

<style scoped>
#home-header {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 50px;
  color: #ececec;
  font-size: 20px;
}

.collapse-btn {
  float: left;
  line-height: 50px;
  padding: 0 20px;
  cursor: pointer;
}

.logo {
  float: left;
  line-height: 50px;
}
</style>
