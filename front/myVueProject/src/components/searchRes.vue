<template>
  <div class="all">
    <div class="content">
      <div class="display">
        <div class="image" v-for="item in cardList" :key="item.menuid">
          <img :src="item.cover" style=" height: 170px; width:230px;" @click="goToDetail(item.menuid)"/>
          <div style="margin-left: 10px; height: 50px;">
            <div style="font-size: 15px;color: blue">{{item.title}}</div>
            <el-image :src="item.avatar" class="el-avatar--circle" @click="goToUserInfo(item.userid)"></el-image>
            <label class="label">{{item.nickname}}</label>
            <el-timeline style="margin-left: 10px;margin-top: 10px;color: gray">{{item.createTime}}</el-timeline>
          </div>
        </div>
        <div v-if="cardList.length === 0" style="text-align: center">没有找到相关的内容！</div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import eventVue from '../../src/eventVue'
  export default {
    name: 'searchRes',
    props: ['getkeyword'],
    data () {
      return {
        cardList: [],
      }
    },
      mounted() {
      eventVue.$on("msg", keyword => {
        //console.log(keyword)
        this.search(keyword);
      });
    },

    methods: {
      search(keyword){
        this.cardList = [];
        axios({
          method: "get",
          url: "/littlekitchen/home/search/" + keyword,
          data: {
            keyword: keyword
          },
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          }
        }).then(res => {
          console.log("menuList:",res.data );
          let len = parseInt(res.data.menu.length);
          for (let i = 0; i < len; i++) {
            let tmp = {};
            let timeStamp = new Date(res.data.menu[i].createTime);
            tmp.menuid = res.data.menu[i].menuid;
            tmp.userid = res.data.menu[i].userid;
            tmp.cover = res.data.menu[i].cover;
            tmp.title = res.data.menu[i].title;
            tmp.createTime = timeStamp.toLocaleDateString().replace(/\//g, "-") + " " + timeStamp.toTimeString().substr(0, 8);
            tmp.type = res.data.menu[i].type;
            tmp.description = res.data.menu[i].description;
            tmp.avatar = res.data.user[i].photo;
            tmp.nickname = res.data.user[i].nickname;
            this.cardList.push(tmp)
          }
        }).catch(error => {
          console.log(error)
        })
      },
      goToDetail (index) {
        console.log('menuid', index);
        this.$emit("getPropFromHeader", 2, parseInt(index));
      },
      goToUserInfo (index) {
        console.log('userid', index);
        this.$emit("getPropUserId", 3, parseInt(index));
      },
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
