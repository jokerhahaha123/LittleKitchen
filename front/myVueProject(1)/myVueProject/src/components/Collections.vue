<template>
  <div class="display">
    <div class="image" v-for="item in collections" :key="item.menuid">
      <el-image :src="item.avatar" class="el-avatar--circle"></el-image>
      <label class="label">{{item.nickname}}</label>
      <img :src="item.cover" style=" height: 170px; width:230px;" @click="goToDetail(item.menuid)"/>
      <div style="margin-left: 10px; height: 80px;">
        <div style="font-size: 15px;color: blue">{{item.title}}</div>
        <el-tooltip class="item" effect="dark" :content="item.description" placement="top">
          <el-button style="border: white;margin-left: -15px;">Date：{{item.createTime}}</el-button>
        </el-tooltip>
        <el-button size="small" type="danger" style="margin-left:1px;" @click="cancelFollow(item.userid)">取消</el-button>
      </div>
    </div>
    <div v-if="collections.length === 0" class="el-icon-warning-outline" style="text-align: center">no data!</div>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  name: 'Collections',
  props: ['getUserId'],
    data () {
    return {
      activeIndex: '0',
      userid: 0,
      collections: []
    }
  },
  methods: {
    handleChange (userid) {
      console.log('collect',userid)
      this.$http.get('http://localhost:8080/littlekitchen/user/'+userid+'/favorites') // 把url地址换成你的接口地址即可
        .then(res => {
          let len = parseInt(res.data.favoriteMenus.length)
          for (let i = 0; i < len; i++) {
            let tmp = {}
            tmp.menuid = res.data.favoriteMenus[i].menuid;
            tmp.userid = res.data.favoriteMenus[i].userid;
            tmp.cover = res.data.favoriteMenus[i].cover;
            tmp.title = res.data.favoriteMenus[i].title;
            tmp.createTime = res.data.favoriteMenus[i].createTime;
            tmp.type = res.data.favoriteMenus[i].type;
            tmp.description = res.data.favoriteMenus[i].description;
            tmp.avatar = res.data.menuUserInfo[i].photo;
            tmp.nickname = res.data.menuUserInfo[i].nickname;
            tmp.thumbupNumber = res.data.favoriteMenus[i].thumbupNumber
            this.collections.push(tmp)
          }
        })
        .catch(err => {
          alert(err + '请求失败')
        })
    },
    cancelFollow (menuid) {
      this.$http.delete('http://localhost:8080/littlekitchen/updates/deletefavorite/'+menuid) // 把url地址换成你的接口地址即可
        .then(res => {
          alert('取消收藏成功')
        })
        .catch(err => {
          alert(err + '取消收藏失败')
        })
    },
    goToDetail (index) {
      console.log(index);
      // this.$router.push({ path: '/info',params:index })
    }
  },
  mounted () {
    this.userid= this.getUserId;
    this.handleChange(this.userid)
  }
}
</script>

<style scoped>
  .display{
    margin-top: 10px;
    display: flex;
    flex-wrap: wrap;
  }
  .image{
    width: 230px;
    margin: 5px;
    float: left;
    border: 1px solid #dfdfdf;
  }
  .el-avatar--circle{
    width: 40px;
    height: 40px;
    float: left;
  }
  .label{
    float:left;
    font-size: 15px !important;
    margin-top: 10px;
    margin-left: 2px;
  }
</style>
