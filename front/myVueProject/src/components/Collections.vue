<template>
  <el-row>
    <el-col :span="8" v-for="item in collections" :key="item.index">
      <el-card >
        <div style="height: 50px;">
          <el-image :src="item.userAvatar" class="el-avatar--circle"></el-image>
          <label class="label">{{item.nickName}}</label>
        </div>
        <img :src="item.url" class="image">
        <div style="padding: 14px;">
          <span style="font-size: 20px">{{item.title}}</span>
          <div class="bottom clearfix">
            <span><time class="time">{{ item.time }}</time><span style="color: #999; font-size: 15px; margin-left: 10px;"> 点赞数：{{item.thumbupNumber}}</span></span>
            <el-button type="text" class="button">取消关注</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'Collections',
  data () {
    return {
      activeIndex: '0',
      collections: [
        {
          title: '香辣鸡爪，好吃',
          url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
          time: '2019.10.24',
          thumbupNumber: 0,
          userAvatar: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
          nickName: '香辣鸡爪'
        },
        {
          title: '香辣鸡爪，好吃',
          description: 'description',
          url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
          time: '2019.10.24',
          thumbupNumber: 0,
          userAvatar: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
          nickName: '香辣鸡爪'
        },
        {
          title: '香辣鸡爪，好吃',
          description: 'description',
          url: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
          time: '2019.10.24',
          thumbupNumber: 0,
          userAvatar: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
          nickName: '香辣鸡爪'
        }
      ]
    }
  }
}
</script>

<style scoped>
  .image {
    width: 200px;
    height: 200px;
    display: block;
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
    font-size: 18px;
    text-align: center;
    color:#3bc5ff;
    margin-top: 10px;
    /*border: 1px solid red;*/
  }
</style>
