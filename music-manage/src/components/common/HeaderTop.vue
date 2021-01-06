<template>
  <Header>
    <el-button slot="left" size="mini" type="primary" @click="addSinger" round>添加歌手</el-button>
    <el-input
      slot="right"
      class="input"
      v-model="input"
      prefix-icon="el-icon-search"
      @clear="searchData"
      size="mini"
      clearable
      autosize
      placeholder="请输入歌手名称"
    ></el-input>
    <el-button slot="right" class="search-button" @click="searchData" type="primary" size="mini" icon="el-icon-search" round>
      搜索
    </el-button>
  </Header>
</template>

<script>
import Header from '@/components/common/Header'
export default {
  name: 'HeaderTop',
  components: {
    Header
  },
  data() {
    return {
      input: ''
    }
  },
  methods: {
    addSinger() {
      this.$emit('add-singer', true)
    },
    searchData() {
      this.$emit('input-data', this.input)
    }
  }
}
</script>

<style scoped>
.input {
  margin: 0 10px;
  width: 200px;
}

.search-button {
  margin: 0 10px;
}
</style>
