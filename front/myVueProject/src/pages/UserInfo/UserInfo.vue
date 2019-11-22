<template>
  <div>
    <Header></Header>
    <div class="all">
      <IDCard></IDCard>
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1">菜谱</el-menu-item>
        <el-menu-item index="2">收藏</el-menu-item>
        <el-menu-item  index="3">创建菜谱</el-menu-item >
      </el-menu>
      <div class="content">
        <!--<el-container>-->
          <!--<el-header class="el-header">-->
            <!--<span style="float: left; font-size: 25px;margin-left: 10px"><span style="font-size: 30px">{{nickName}}</span> 的厨房</span>-->
          <!--</el-header>-->
        <!--</el-container>-->
        <div id="display">
          <Menu v-if="display===1"></Menu>
          <!--<el-row v-if="display===1">-->
            <!--<el-col :span="8" v-for="item in menu" :key="item.index">-->
              <!--<el-card>-->
                <!--<img :src="item.url" class="image">-->
                <!--<div style="padding: 14px;">-->
                  <!--<span style="font-size: 20px">{{item.title}}</span>-->
                  <!--<div class="bottom clearfix">-->
                    <!--<span><time class="time">{{ item.time }}</time><span style="color: #999; font-size: 15px; margin-left: 10px;"> 点赞数：{{item.thumbupNumber}}</span></span>-->
                    <!--<el-button type="text" class="button">删除</el-button>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</el-card>-->
            <!--</el-col>-->
            <!--<el-col> <el-button type="primary" icon="el-icon-edit" circle></el-button></el-col>-->
          <!--</el-row>-->
          <Collections v-else-if="display===2"></Collections>
          <!--<el-row v-else-if="display===2">-->
            <!--<el-col :span="8" v-for="item in collections" :key="item.index">-->
              <!--<el-card >-->
                <!--<div style="height: 50px;">-->
                  <!--<el-image :src="item.userAvatar" class="el-avatar&#45;&#45;circle"></el-image>-->
                  <!--<label class="label">{{item.nickName}}</label>-->
                <!--</div>-->
                <!--<img :src="item.url" class="image">-->
                <!--<div style="padding: 14px;">-->
                  <!--<span style="font-size: 20px">{{item.title}}</span>-->
                  <!--<div class="bottom clearfix">-->
                    <!--<span><time class="time">{{ item.time }}</time><span style="color: #999; font-size: 15px; margin-left: 10px;"> 点赞数：{{item.thumbupNumber}}</span></span>-->
                    <!--<el-button type="text" class="button">取消关注</el-button>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</el-card>-->
            <!--</el-col>-->
          <!--</el-row>-->
          <el-row v-else="">
            <el-col :span="8">
              <Form></Form>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Header from '../../components/Header'
import Form from '../../components/Form'
import IDCard from '../../components/IDCard'
import Menu from '../../components/Menu'
import Collections from '../../components/Collections'
export default {
  name: 'UserInfo',
  components: {Collections, Menu, IDCard, Form, Header},
  data () {
    return {
      nickName: '昵称',
      fanNumber: 0,
      activeIndex: '1',
      display: 1,
      // currentDate: new Date(),
      menu: [],
      collections: []
    }
  },
  methods: {
    goToWorks (key) {
      axios.get('http://localhost:8081/littlekitchen/updates/list'
        // eslint-disable-next-line
      ).then((res) =>
      // eslint-disable-next-line
          this.menu =res.data
        // eslint-disable-next-line
      ).catch(err => {
        console.log('error')
      })
      console.log('go to works', key, this.display)
    },
    goToCollections (key) {
      axios.get('http://localhost:8081/littlekitchen/user/favorites'
        // eslint-disable-next-line
      ).then((res) =>
      // eslint-disable-next-line
          this.collections =res.data
        // eslint-disable-next-line
      ).catch(err => {
        console.log('error')
      })
      console.log('go to collections', key, this.display)
    },
    createMenu (key) {
      axios.post('http://localhost:8081/littlekitchen/user/addmenu'
        // eslint-disable-next-line
      ).then((res) =>
        // eslint-disable-next-line
          this.collections =res.data
        // eslint-disable-next-line
      ).catch(err => {
        console.log('error')
      })
      console.log('go to userInfo', key, this.display)
    },
    handleSelect (key, keyPath) {
      this.display = parseInt(key)
      console.log(key, this.display)
      if (parseInt(key) === 1) {
        this.goToWorks(key)
      } else {
        this.goToCollections(key)
      }
    }
  },
  mounted () {
    this.display = 1
  }
}
</script>

<style scoped>
  .all{
    margin:2% 20%;
  }
  .content{
    border: 1px solid #dfdfdf;
    margin-top: 5px;
  }
  /*.el-header {*/
    /*background-color: #B3C0D1;*/
    /*color: #333;*/
    /*!*text-align: center;*!*/
    /*!*height: 180px;*!*/
    /*line-height: 60px;*/
  /*}*/
  .el-menu-demo{
    border: 1px solid #dfdfdf;
    /*margin-top: 2px;*/
    /*margin-left: 10px;*/
  }
  .time {
    font-size: 15px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before, .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .el-avatar--circle{
    width: 50px;
    height: 50px;
    float: left;
    margin-left: 10px;
    margin-right: 20px;
    margin-bottom: 2px;
    /*border: 1px solid blue;*/
  }
  .label{
    float:left;
    /*font-size: 18px;*/
    /*text-align: center;*/
    margin-top: 10px;
    /*border: 1px solid red;*/
  }
</style>
