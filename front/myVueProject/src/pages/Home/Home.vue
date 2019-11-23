<template>
<div>
  <Header></Header>
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
        <div class="image" v-for="item in cardList" :key="item.menuid" @click="goToDetail(item.menuid)">
          <el-image :src="item.cover" class="el-image"></el-image>
          <div style="margin-left: 10px; height: 50px;">
            <el-image :src="item.cover" class="el-avatar--circle"></el-image>
            <label class="label">{{item.title}}</label>
          </div>
        </div>
      </div>
    </div>
    <button @click="gotoInfo">前往个人中心</button>
  </div>
</div>
</template>

<script>
import Header from '../../components/Header'
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
      activeIndex: '1'
    }
  },
  methods: {
    goToCommend (index) {
      // console.log('点击推荐', index)
      this.$http.get('http://127.0.0.1:8080/littlekitchen/home/recommend') // 把url地址换成你的接口地址即可
        .then(res => {
          let len = parseInt(res.data.menu.length);
          for (let i = 0; i < len; i++) {
            let tmp = {}
            tmp.menuid = res.data.menu[i].menuid
            tmp.userid = res.data.menu[i].userid
            tmp.cover = res.data.menu[i].cover
            tmp.title = res.data.menu[i].title
            tmp.createTime = res.data.menu[i].createTime
            tmp.type = res.data.menu[i].type
            tmp.description = res.data.menu[i].description
            this.cardList.push(tmp)
          }
          console.log('recommend=',res.data.menu.length,this.cardList.length)
        })
        .catch(err => {
          alert(err + '请求失败')
        })
    },

    goToLatest (index) {
      this.$http.get('/littlekitchen/home/new') // 把url地址换成你的接口地址即可
        .then(res => {
          let len = parseInt(res.data.menu.length);
          // this.cardList[0].cover = res.data.menu[0].cover
          for (let i = 0; i < len; i++) {
            let tmp = {}
            tmp.menuid = res.data.menu[i].menuid
            tmp.userid = res.data.menu[i].userid
            tmp.cover = res.data.menu[i].cover
            tmp.title = res.data.menu[i].title
            tmp.createTime = res.data.menu[i].createTime
            tmp.type = res.data.menu[i].type
            tmp.description = res.data.menu[i].description
            this.cardList.push(tmp)
          }
          console.log('new=',res.data.menu, this.cardList.length)
        })
        .catch(err => {
          console.log(err)
          alert(err + '请求失败')
        })
    },
    goToClassification (str) {
      let index = str.split('-')[1]
      console.log(index)
      this.$http.get('/littlekitchen/home/type/'+parseInt(index)) // 把url地址换成你的接口地址即可
        .then(res => {
          let len = parseInt(res.data.menu.length)
          for (let i = 0; i < len; i++) {
            let tmp = {}
            tmp.menuid = res.data.menu[i].menuid
            tmp.userid = res.data.menu[i].userid
            tmp.cover = res.data.menu[i].cover
            tmp.title = res.data.menu[i].title
            tmp.createTime = res.data.menu[i].createTime
            tmp.type = res.data.menu[i].type
            tmp.description = res.data.menu[i].description
            this.cardList.push(tmp)
          }
          console.log('Classification=',res.data.menu, this.cardList.length)
        })
        .catch(err => {
          console.log(err)
          alert(err + '请求失败')
        })
    },
    goToDetail (index) {
      console.log(index)
      this.$router.push({ path: '/info',params:index })
    },
    handleSelect (key, keyPath) {
      switch (parseInt(key)) {
        case 1:
          this.goToCommend(key)
          break
        case 2:
          this.goToLatest(key)
          break
        default:
          this.goToClassification(key)
      }
      // console.log(key, keyPath)
    },
    gotoInfo () {
      this.$router.push({ path: '/info' })
    },
    gotoUser () {
      this.$router.push({ path: '/user' })
    }
  },
  mounted () {
    this.goToCommend(this.activeIndex)
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
    /*width: 1000px;*/
    /*height: 800px;*/
    border: 1px solid #dfdfdf;
    /*margin-left: 10px;*/
    margin-top: 5px;
  }
  .display{
    /*margin-left:260px ;*/
    margin-top: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    /*width: 1200px;*/
    flex-wrap: wrap;
  }
  .image{
    width: 230px;
    height: 230px;
    margin: 5px;
    border: 1px solid #dfdfdf;
  }
  .el-avatar--circle{
    width: 50px;
    height: 50px;
    float: left;
    /*border: 1px solid blue;*/
  }
  .label{
    float:left;
    font-size: 18px;
    /*text-align: center;*/
    margin-top: 10px;
    /*border: 1px solid red;*/
  }

</style>
