<template>
    <div class="header">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item id="logo"><img src="../assets/logo.png"  style="width: 20%" alt="图片加载失败"></el-menu-item>
        <el-menu-item index="1" class="nav">首页</el-menu-item>
        <el-menu-item index="2"  class="nav" >作品动态</el-menu-item>
        <el-input prefix-icon="el-icon-search" placeholder ="请输入内容" v-model="input" clearable style="width: inherit" @keyup.enter.native="search"></el-input>
        <el-image v-if="loginAvatar" :src="loginAvatar" class="avatar" @click="userInfo"></el-image>
        <el-image :src="circleUrl" class="avatar"  @click="userInfo"  v-else ></el-image>
      </el-menu>
    </div>
</template>

<script>
// import axios from 'axios'

// eslint - disable - next - line

export default{
  props: {
    loginAvatar: String// 接收父组件数据
  },
  name: 'Header',
  data () {
    return {
      activeIndex: '1',
      activeIndex2: '1',
      input: '',
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    },
    search () {
      // axios({
      //   method: 'post',
      //   url: '',
      //   data: this.input,
      //   headers: {
      //     'Content-Type': 'application/json;charset=UTF-8'
      //   }
      // }).then((response) => {
      //   console.log(response)// 返回菜谱列表
      // }).catch((error) => {
      //   console.log('error')
      // })
    },
    userInfo () {
      location.href = '/#/UserInfo'
    }
  }
}
// eslint-disable-next-line
let docEl = document.documentElement,

  resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize'
var clientWidth = docEl.clientWidth
docEl.style.fontSize = 10 * (clientWidth / 320) + 'px'
window.addEventListener(resizeEvt, function () { clientWidth = docEl.clientWidth; docEl.style.fontSize = 10 * (clientWidth / 320) + 'px' }, false)
</script>

<style scoped>
  .header{
    margin:2% 20%;
  }
  .avatar{
    float: right;
    width: 42px;
    height: 42px;
    margin-top: 13px;
  }
  .nav{
    font-size: 20px;
  }
</style>
