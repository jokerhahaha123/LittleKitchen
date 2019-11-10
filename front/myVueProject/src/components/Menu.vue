<template>
  <el-row>
    <el-col :span="8" v-for="item in menu" :key="item.index">
      <el-card>
        <img :src="item.cover" class="image">
        <div>
          <span style="font-size: 15px; color:#3bc5ff;">{{item.title}}</span>
          <div class="bottom clearfix">
            <el-button type="text" class="button" style="padding: 1px">
              <el-collapse v-model="activeNames" @change="handleChange">
              <el-collapse-item title="详情" name="1" >
                <span><time class="time">{{ item.createDate }}</time><span style="color: #999; font-size: 15px; margin-left: 10px;"> 点赞数：{{item.thumbupNumber}}</span></span>
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
export default {
  name: 'Menu',
  data () {
    return {
      activeIndex: '0',
      activeNames: ['1'],
      menu: [
        {
          title: '香辣鸡爪，好吃',
          description: 'description',
          cover: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
          createDate: '2019.10.24',
          thumbupNumber: 0
        },
        {
          title: '香辣鸡爪，好吃',
          description: 'description',
          cover: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
          createDate: '2019.10.24',
          thumbupNumber: 0
        },
        {
          title: '香辣鸡爪，好吃',
          description: 'description',
          cover: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',
          createDate: '2019.10.24',
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
  .image {
    width: 200px;
    height: 200px;
    display: block;
  }
</style>
