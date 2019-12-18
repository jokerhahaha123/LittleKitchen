<template>
  <div id="login" style=" margin:10px 100px 10px 100px; ">
    <!--<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"style="border-bottom-color:#ffffff!important;">-->
      <!--<img src="../../assets/logo3.png"  style="width: 9.8% ;float: left; outline:none">-->
    <!--</el-menu>-->
      <el-divider contentPosition="center"><span style="font-size: 38px">登录</span></el-divider>
      <div class="login_form">
        <el-input  prefixIcon="el-icon-s-custom" placeholder="请输入账号" v-model="email" style="width: 25%;margin-top: 7%" ></el-input><br/>
        <el-input  prefixIcon="el-icon-lock" placeholder="请输入密码" v-model="password" style="width: 25%;margin-top: 2%" showPassword ></el-input><br/>
        <el-button type="primary" style="width: 12%;margin-top: 2%" @click.native="doLogin">登录</el-button>
        <el-button type="primary" plain style="width: 12%;margin-top: 2%" @click.native="gotoRegister">注册</el-button>
        </div>
    </div>
</template>

<script>
  import axios from 'axios';
  import qs from 'qs';
  import Header from '../../components/header'
export default {
  name: 'login',
  components:{Header,
  },
  data() {
    return {
      userAvatar:"",
      email: '',
      password: '',
      isBtnLoading: false
    }
  },
  created () {
//    if(JSON.parse( localStorage.getItem('user')) && JSON.parse( localStorage.getItem('user')).userName){
//      this.userName = JSON.parse( localStorage.getItem('user')).userName;
//      this.password = JSON.parse( localStorage.getItem('user')).password;
//    }
    if(sessionStorage.nickname){//已登录后再次url访问登录页面
      location.href='/#/userInfo'
    }
  },
  methods: {
      doLogin() {
        var emailRegex = /^[a-zA-Z1-9]\w{1,}@[a-zA-Z1-9]{2,}(\.(edu|com|cn|net)){1,2}$/;
      if (!this.email) {
        this.$alert('请输入邮箱');
        return;
      }
        if(!emailRegex.test(this.email)){
          this.$alert('邮箱格式错误');
          return;
      }
      if (!this.password) {
        this.$alert('请输入密码');
        return;
      }
      axios({
        method:"post",
        url:"/littlekitchen/login",
        data:{
          email:this.email,
          password:this.password,
        },
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
        }
      }).then((response)=>{
        console.log(response.data);
       // console.log(response.data.message);
        if (response.data.message == 1){
          sessionStorage['avatarUrl']=response.data.photo;
          sessionStorage['nickName'] = response.data.nickname;
          sessionStorage['userId'] = response.data.userid;
          location.href = '/';
          alert("登录成功");
        }
        else {
          alert("邮箱或密码错误");
        }
      }).catch((error)=>{
        console.log("error")
      })
    },
    gotoRegister(){
      location.href='/#/register'
    }
  },
}
</script>

<style>

</style>
