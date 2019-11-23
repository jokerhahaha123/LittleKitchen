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
        <div id="display">
          <Menu v-if="display===1"></Menu>
          <Collections v-else-if="display===2"></Collections>
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

/*eslint-disable*/
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
      axios.get('/littlekitchen/updates/list'
      ).then((res) =>
          this.menu =res.data
      ).catch(err => {
        console.log('error')
      })
      console.log('go to works', key, this.display)
    },
    goToCollections (key) {
      axios.get('/littlekitchen/user/favorites'
      ).then((res) =>
          this.collections =res.data
      ).catch(err => {
        console.log('error')
      })
      console.log('go to collections', key, this.display)
    },
    createMenu (key) {
      axios.post('/littlekitchen/user/addmenu'
      ).then((res) =>
          this.collections =res.data
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
  .el-menu-demo{
    border: 1px solid #dfdfdf;
    /*margin-top: 2px;*/
    /*margin-left: 10px;*/
  }


</style>
