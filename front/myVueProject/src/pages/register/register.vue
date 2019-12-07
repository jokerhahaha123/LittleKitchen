<template>
  <div id="regsiter">
    <!--<Header></Header>-->
    <div style=" margin:10px 100px 10px 100px; " >
      <el-divider contentPosition="center"><span style="font-size: 38px">注册</span></el-divider>
    </div>
    <div class="register_form">
      <el-input  prefixIcon="el-icon-message" placeholder="请输入邮箱" v-model="email" style="width: 22%;margin-top: 7%" ></el-input><br/>
      <el-input  prefixIcon="el-icon-s-custom" placeholder="请输入昵称" v-model="nickname" style="width: 22%;margin-top: 2%" ></el-input><br/>
      <el-input  prefixIcon="el-icon-lock" placeholder="请输入密码" v-model="password" style="width: 22%;margin-top: 2%"  showPassword></el-input><br/>
      <el-input  prefixIcon="el-icon-lock" placeholder="请确认密码" v-model="confirmPw" style="width: 22%;margin-top: 2%" showPassword ></el-input><br/>
      <el-button type="primary" style="width: 22%;margin-top: 2%" @click.native="doRegister">注册</el-button>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import qs from 'qs';
  import Header from '../../components/header'
  export default {
    name: 'register',
    components:{Header},
    data() {
      return {
        email: '',
        nickname: '',
        password:'',
        confirmPw:'',
      }
    },
    created () {
//      if(JSON.parse( localStorage.getItem('user')) && JSON.parse( localStorage.getItem('user')).userName){
//        this.userName = JSON.parse( localStorage.getItem('user')).userName;
//        this.password = JSON.parse( localStorage.getItem('user')).password;
//      }
    },
    methods: {
      doRegister() {
        var emailRegex = /^[a-zA-Z1-9]\w{1,}@[a-zA-Z1-9]{2,}(\.(edu|com|cn|net)){1,2}$/;
        if (!this.email) {
          this.$alert('请输入邮箱');
          return;
        }
        if(!emailRegex.test(this.email)){
          this.$alert('邮箱格式错误');
          return;
        }
        if (!this.nickname){
          this.$alert('请输入昵称')
          return;
        }
        if (!this.password) {
          this.$alert('请输入密码');
          return;
        }
        if(!this.confirmPw){
          this.$alert('请再次输入密码');
          return;
        }
        if(this.password!==this.confirmPw){
          this.$alert("两次输入的密码不同，请重新确认")
          return;
        }

        axios({
          method:"put",
          url:"/littlekitchen/register",
          data:{
            email:this.email,
            nickname:this.nickname,
            password:this.password,
          },
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          }
        }).then((response)=>{
          console.log(response.data)
          if (response.data.message == 1){//注册成功
           location.href = '/'
            sessionStorage['avatarUrl']=response.data.photo;
            sessionStorage['nickName'] = response.data.nickname;
            sessionStorage['userId'] = response.data.userid;
          }
          else {
            alert("该邮箱已注册");
          }
        }).catch((error)=>{
          console.log(error)
        })
      }
    }
  }
</script>

<style>

</style>
