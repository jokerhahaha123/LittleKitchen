<template>
  <div class="headers">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <img src="../assets/logo3.png" style="width: 9.8% ;float: left; outline:none">
      <el-menu-item index="1" class="nav" @click="goToTemp">首页</el-menu-item>
      <el-menu-item index="2" class="nav" @click="goToUpdates">作品动态</el-menu-item>
      <el-input prefixIcon="el-icon-search" placeholder="请输入内容" v-model="input" clearable
                style="width: inherit;margin-top: 13px" @keyup.enter.native="search"></el-input>
      <el-dropdown v-if="loginAvatar" style="float: right;color: #409EFF;">
        <el-image :src="loginAvatar" class="avatar"></el-image>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="userInfo">个人中心</el-dropdown-item>
          <el-dropdown-item @click.native="logOut">登出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-image :src="circleUrl" @click="login" class="avatar" v-else></el-image>
    </el-menu>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: "Header",
    data() {
      return {
        activeIndex: '1',
        input: '',
        loginAvatar: "",
        circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      };
    },
    created() {
      if (sessionStorage.avatarUrl === "null"){
        this.loginAvatar = "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" ;
      }
      else if (sessionStorage.avatarUrl === undefined){
        this.loginAvatar = false;
      }
      else {
        this.loginAvatar = sessionStorage.avatarUrl;
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      search() {
        axios({
          method: "get",
          url: "/littlekitchen/home/search/"+this.input,
          data: {
            keyword: this.input
          },
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          }
        }).then((response) => {
          console.log("菜谱列表："+response.data)//返回菜谱列表
        }).catch((error) => {
          console.log("error")
        })
      },
      login() {
        location.href = '/#/login'
      },
      userInfo() {
         this.$emit("getPropUserId",3,sessionStorage.userId);
        //this.$emit("getPropUserId", 1);
      },
      logOut() {
        axios({
          method: "get",
          url: "/littlekitchen/logout",
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          }
        }).then((response) => {
          if (response.data.message == 1) {
            sessionStorage.clear();
            this.loginAvatar = sessionStorage.avatarUrl;
            alert("登出成功");
            location.href='/#/login'
          } else {
            alert("登出失败")
          }
        }).catch((error) => {
          console.log("error")
        })
      },
      // 跳转到作品动态
      goToUpdates() {
        this.$emit("getPropFromHeader", 1);
      },
      goToTemp() {
        this.$emit("getPropFromHeader", 0);
      }
    }
  }

</script>

<style scoped>
  .headers {
    margin: 2% 20%;
  }

  .avatar {
    outline: none;
    cursor: pointer;
    float: right;
    width: 42px;
    height: 42px;
    margin-top: 13px;
    border-radius: 50%;
  }

  .nav {
    font-size: 20px;
    margin-left: 5%;
  }
</style>
