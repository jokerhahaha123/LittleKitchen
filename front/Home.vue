<template>
<div>
  <!--<Header></Header>-->
  <div class="all">
    <div class="content">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1">推荐</el-menu-item>
        <el-menu-item index="2">最新</el-menu-item>
        <el-submenu index="3">
          <template slot="title">分类</template>
          <el-menu-item v-for="item in options" :key="item.index" :index="item.index">{{item.label}}</el-menu-item>
        </el-submenu>
      </el-menu>
      <div class="line"></div>
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
        <div v-if="cardList.length === 0" class="el-icon-warning-outline" style="text-align: center">no data!</div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import Header from './header'
/* eslint-disable */
export default {
  name: 'Home',
  components: {Header},
  data () {
    return {
      cardList: [],
      options: [
        {
          value: '选项1',
          label: '鲁菜',
          index: '3-1'
        }, {
          value: '选项2',
          label: '川菜',
          index: '3-2'
        }, {
          value: '选项3',
          label: '粤菜',
          index: '3-3'
        }, {
          value: '选项4',
          label: '苏菜',
          index: '3-4'
        }, {
          value: '选项5',
          label: '浙菜',
          index: '3-5'
        }, {
          value: '选项6',
          label: '徽菜',
          index: '3-6'
        }, {
          value: '选项7',
          label: '湘菜',
          index: '3-7'
        }, {
          value: '选项8',
          label: '闽菜',
          index: '3-8'
        }
      ],
      value: '',
      activeName: 'first',
      activeIndex: '1',
    }
  },
  methods: {
    goToCommend (url) {
      this.cardList = [];
      this.$http.get(url) // 把url地址换成你的接口地址即可
        .then(res => {
          let len = parseInt(res.data.menu.length);
          console.log(res.data)
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

          console.log('recommend =',res.data)
        })
        .catch(err => {
          alert(err + '请求失败')
        })
    },
    goToDetail (index) {
      console.log('menuid',index);
      this.$emit("getPropFromHeader",2,parseInt(index));
    },
    goToUserInfo (index) {
      console.log('userid',index);
      this.$emit("getPropUserId",3,parseInt(index));
    },
    handleSelect (key, keyPath) {
      switch (parseInt(key)) {
        case 1:
          this.goToCommend('http://localhost:8080/littlekitchen/home/recommend');
          console.log("recommand");
          break;
        case 2:
          this.goToCommend('http://localhost:8080/littlekitchen/home/new');
          console.log("new");
          break;
        default:
          let index = key.split('-')[1];
          console.log("classification",index);
          this.goToCommend('http://localhost:8080/littlekitchen/home/type/'+parseInt(index))
      }
    }
  },
  mounted () {
    this.goToCommend("http://localhost:8080/littlekitchen/home/recommend")
  }
}
</script>

<style scoped>
  .all{
    margin:2% 20%;
  }
  .el-menu-demo{
    margin-left: 10px;
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
