<template>
  <el-row>
    <el-col :span="8" v-for="item in menu" :key="item.menuid">
      <el-card>
        <img :src="item.cover" class="image">
        <div>
          <span style="font-size: 15px; color:#3bc5ff;">{{item.title}}</span>
          <div class="bottom clearfix">
            <el-button type="text" class="button" style="padding: 1px">
              <el-collapse v-model="activeNames" @change="handleChange">
              <el-collapse-item title="详情" name="1" >
                <span><time class="time">{{ item.createTime }}</time><span style="color: #999; font-size: 15px; margin-left: 10px;"> 点赞数：{{item.thumbupNumber}}</span></span>
                <el-button size="small" type="danger" style="margin-left:5px;">删除</el-button>
                <div>{{item.description}}</div>
              </el-collapse-item>
            </el-collapse>
            </el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
/* eslint-disable */
export default {
  name: 'Menu',
  data () {
    return {
      activeIndex: '0',
      activeNames: ['1'],
      menu: []
    }
  },
  methods: {
    handleChange () {
      this.$http.get('/littlekitchen/updates/1/list') // 把url地址换成你的接口地址即可
        .then(res => {
          let len = parseInt(res.data.menu.length)
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
            tmp.thumbupNumber = res.data.menu[i].thumbupNumber
            this.menu.push(tmp)
          }
          // console.log('new=',res.data.menu, this.menu.length)
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
</style>
