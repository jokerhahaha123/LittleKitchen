<template>
  <div class="display">
    <div class="image" v-for="item in menu" :key="item.menuid">
      <img :src="item.cover" style=" height: 170px; width:230px;"  @click="goToDetail(item.menuid)"/>
      <div style="margin-left: 10px; height: 80px;">
        <div style="font-size: 15px;color: blue">{{item.title}}</div>
        <el-tooltip class="item" effect="dark" :content="item.description" placement="top">
          <el-button style="border: white;margin-left: -5px;">Date：{{item.createTime}}</el-button>
        </el-tooltip>
        <el-button size="small" type="danger" style="margin-left:1px;" @click="deleteMenu(item.menuid)">删除</el-button>
      </div>
    </div>
    <div v-if="menu.length === 0" class="el-icon-warning-outline" style="text-align: center">no data!</div>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  name: 'Menu',
  props: ['getUserId'],
  data () {
    return {
      activeIndex: '0',
      activeNames: ['1'],
      userid: 0,
      menu: []
    }
  },
  methods: {
    handleChange (userid) {
      console.log('menu',userid)
      this.menu = []
      this.$http.get('http://localhost:8080/littlekitchen/my/'+userid) // 把url地址换成你的接口地址即可
        .then(res => {
          let len = parseInt(res.data.length)
          for (let i = 0; i < len; i++) {
            let tmp = {}
            tmp.menuid = res.data[i].menuid;
            tmp.cover = res.data[i].cover;
            tmp.title = res.data[i].title;
            tmp.createTime = res.data[i].createTime;
            tmp.type = res.data[i].type;
            tmp.description = res.data[i].description;
            tmp.thumbupNumber = res.data[i].thumbupNumber;
            this.menu.push(tmp)
          }
        })
        .catch(err => {
          alert(err + '请求失败')
        })
    },
    deleteMenu (menuid) {
      this.$http.delete('http://localhost:8080/littlekitchen/delete/'+menuid) // 把url地址换成你的接口地址即可
        .then(res => {
          alert('删除成功')
        })
        .catch(err => {
          alert(err + '删除失败')
        })
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
</style>
