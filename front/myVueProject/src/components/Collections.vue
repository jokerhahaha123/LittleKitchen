<template>
  <el-row>
    <el-col :span="8" v-for="item in collections" :key="item.index">
      <el-card >
        <div style="height: 50px;">
          <el-image :src="item.userAvatar" class="el-avatar--circle"></el-image>
          <label class="label">{{item.nickName}}</label>
        </div>
        <img :src="item.cover" class="image">
        <div style="padding: 14px;">
          <span style="font-size: 20px">{{item.title}}</span>
          <div class="bottom clearfix">
            <span><time class="time">{{ item.createTime }}</time><span style="color: #999; font-size: 15px; margin-left: 10px;"> 点赞数：{{item.thumbupNumber}}</span></span>
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
      collections: []
    }
  },
  methods: {
    handleChange () {
      this.$http.get('http://localhost:8081/littlekitchen/user/favorities') // 把url地址换成你的接口地址即可
        .then(res => {
          let len = parseInt(res.data.menu.length)
          // this.cardList[0].cover = res.data.menu[0].cover
          for (let i = 0; i < len; i++) {
            let tmp = {}
            tmp.menuid = res.data.menu[i].menuid
            tmp.userid = res.data.menu[i].userid
            tmp.userAvatar = res.data.menu[i].userAvatar
            tmp.cover = res.data.menu[i].cover
            tmp.title = res.data.menu[i].title
            tmp.createTime = res.data.menu[i].createTime
            tmp.type = res.data.menu[i].type
            tmp.description = res.data.menu[i].description
            tmp.thumbupNumber = res.data.menu[i].thumbupNumber
            this.collections.push(tmp)
          }
          console.log('new=', res.data.menu, this.collections.length)
        })
        .catch(err => {
          console.log(err)
          alert(err + '请求失败')
        })
    }
  },
  mounted () {
    this.handleChange()
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
