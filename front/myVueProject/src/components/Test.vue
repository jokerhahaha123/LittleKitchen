<template>
  <div>
    <el-row :gutter="5" class="row">
      <el-col class="col" style="border: 1px solid #dfdfdf;">
        <div :body-style="{ padding: '10px' }">
          <el-image src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="el-avatar--circle"></el-image>
          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/">
            <el-button size="small" type="success" style="margin: -2px">上传头像</el-button>
          </el-upload>
        </div>
      </el-col>
      <el-col class="col" style="border: 1px solid #dfdfdf;">
        <div style="display: block" >
          <div class="namearea">
            <p>昵称：PW68061826</p>
            <p>性别：女</p>
            <p>出生日期：1998.1.10</p>
            <p>邮箱：9061446xx@qq.com</p>
            <p>创建时间：189xxxx9028</p>
            <p>自我介绍：189xxxx9028</p>
          </div>
        </div>
      </el-col>
      <el-col class="col">
        <div class="area">
          <div class="dataarea">
            <p class="gtitle"><i class="el-icon-date el-icon--left"></i>个人数据</p>
            <div class="gdataarea clear">
              <div class="gdata left">
                <p class="num">20</p>
                <p class="title">点赞数量</p>
              </div>
              <div class="gdata left">
                <p class="num">200</p>
                <p class="title">关注的人</p>
              </div>
              <div class="gdata left">
                <p class="num">3000</p>
                <p class="title">粉丝</p>
              </div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="row">
      <el-col :span="8" v-for="item in menu" :key="item.index">
        <el-card>
          <img :src="item.url" class="image">
          <div style="padding: 14px;">
            <span style="font-size: 20px">{{item.title}}</span>
            <div class="bottom clearfix">
              <span><time class="time">{{ item.time }}</time><span style="color: #999; font-size: 15px; margin-left: 10px;"> 点赞数：{{item.thumbupNumber}}</span></span>
              <el-button type="text" class="button"> <el-collapse v-model="activeNames" @change="handleChange">
                <el-collapse-item title="详情" name="1">
                  <div>与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；</div>
                  <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div>
                </el-collapse-item>
              </el-collapse></el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      <!--<el-col> <el-button type="primary" icon="el-icon-edit" circle></el-button></el-col>-->
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Test',
  data () {
    return {
      activeNames: ['0'],
      menu: [
        {
          title: '香辣鸡爪，好吃',
          description: 'description',
          url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
          time: '2019.10.24',
          thumbupNumber: 0
        },
        {
          title: '香辣鸡爪，好吃',
          description: 'description',
          url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
          time: '2019.10.24',
          thumbupNumber: 0
        },
        {
          title: '香辣鸡爪，好吃',
          description: 'description',
          url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
          time: '2019.10.24',
          thumbupNumber: 0
        }
      ]
    }
  },
  methods: {
    handleChange (val) {
      console.log(val)
    }
  }
}
</script>

<style scoped>
  .row{
    /*border: 1px solid red;*/
    width: 647px;
  }
  .namearea p{
    /*padding:10px;*/
    text-align: left;
    font-size:14px;
    line-height:14px;
  }
  .col{
    width: 180px;
    height: 180px;
    margin: 5px;
    /*color: white;*/
    display:inline-block;
  }
  .el-avatar--circle{
    margin-top: 2px;
    margin-bottom: 0;
    width: 100px;
    height: 100px;
  }
  .area {
    border: 1px solid #dfdfdf;
    width: 250px;
    height: 180px;
    overflow: hidden;
  }
  .dataarea {
    /*padding: 10px;*/
    /*text-align: center;*/
    font-size: 14px;
  }
  .gdataarea {
    padding-left: 25px;
  }
  .gtitle{
    width:100%;
    height:30px;
    line-height:30px;
    cursor: pointer;
    background-color: #3bc5ff;
    color: white;
    display: block;
  }
  /*p{*/
  /*line-height:38px;*/
  /*}*/
  .num{
    font-weight:bolder;
    color:#c10000;
  }
  .title{
    color:#3bc5ff;
  }
  .gdata{
    margin:10px;
    float: left;
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
</style>
