<template>
  <div>
    <!--<Header></Header>-->
    <div class="all">
      <!--<IDCard></IDCard>-->
      <div>
        <el-header class="el-header">
          <div style="margin-left: 10px; height: 50px;">
            <el-image :src="imageUrl" class="el-avatar--circle"></el-image>
            <div style=" float: left;margin-top: 90px;">
              <div>
                <label class="label">{{nickName}}</label>
                <el-popover
                  placement="right"
                  width="400"
                  trigger="click">
                  <el-form label-position="left" ref="form" :model="form" label-width="80px">
                    <el-form-item label="头像">
                      <el-upload
                        class="avatar-uploader"
                        action="http://localhost:8080/littlekitchen/uploadImage"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                      </el-upload>
                    </el-form-item>
                    <el-form-item label="昵称">
                      <el-input v-model="form.name" placeholder="哈哈哈"></el-input>
                    </el-form-item>
                    <el-form-item label="性别">
                      <el-select v-model="form.region" placeholder="请选择性别">
                        <el-option label="男" value="male"></el-option>
                        <el-option label="女" value="female"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="出生日期">
                      <el-col :span="11">
                        <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
                      </el-col>
                    </el-form-item>
                    <el-form-item label="邮箱">
                      <el-input v-model="form.email" placeholder="example@example.com"></el-input>
                    </el-form-item>

                    <el-form-item label="个性签名">
                      <el-input type="textarea" v-model="form.desc" placeholder="我喜欢做饭，但不喜欢洗碗"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <el-button type="primary" @click="onSubmit">修改</el-button>
                      <el-button @click="onCancel">取消</el-button>
                    </el-form-item>
                  </el-form>
                  <el-button slot="reference" size="small" type="primary" v-if="isSelf">编辑</el-button>
                </el-popover>
              </div>
            </div>
            <div class="area">
              <div class="dataarea">
                <div class="gdataarea clear">
                  <div class="gdata left">
                    <p class="num">{{followNum}}</p>
                    <p class="title">关注的人</p>
                  </div>
                  <div class="gdata left">
                    <p class="num">{{followedNum}}</p>
                    <p class="title">粉丝</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-header>
      </div>
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1">菜谱</el-menu-item>
        <el-menu-item index="2" v-if="isSelf">收藏</el-menu-item>
        <el-menu-item  index="3" v-if="isSelf">创建菜谱</el-menu-item >
      </el-menu>
      <div class="content">
        <div id="display">
          <Menu v-if="display===1" :getUserId="userid"></Menu>
          <Collections v-else-if="display===2" :getUserId="userid"></Collections>
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
import Header from './header'
import Form from './Form'
import Menu from './Menu'
import Collections from './Collections'
/* eslint-disable */
export default {
  name: 'UserInfo',
  props: ['getUserId'],
  components: {Collections, Menu, Form, Header},
  data () {
    return {
      fanNumber: 0,
      activeIndex: '1',
      display: 1,
      menu: [],
      collections: [],
      nickName: '',
      userid: this.getUserId,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      isSelf: true,
      imageUrl: '',
      followNum: 0,
      followedNum:0
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      this.display = parseInt(key)
    },
    onSubmit () {
      this.$http.post('http://localhost:8080/littlekitchen/user/'+this.userid+'/updateInfo', {
        nickname: this.form.name,
        gender: this.form.region !== 'female',
        birthdays: this.form.date1,
        photo: this.imageUrl,
        description: this.form.desc
      })
        .then(function (response) {
          alert( '修改成功！')
          console.log('msg: ',response.data)
        })
        .catch(function (error) {
          alert( '修改失败！')
          console.log(error)
        })
    },
    onCancel () {
      location.reload()
    },
    gotoInfo () {
      this.$http.get('http://localhost:8080/littlekitchen/user/'+this.userid+'/info') // 把url地址换成你的接口地址即可
        .then(res => {
          this.imageUrl = res.data.user.photo;
          this.followNum = res.data.followNum;
          this.followedNum = res.data.followedNum;
          this.isSelf = res.data.isSelf;
          console.log('user=', res.data, this.isSelf)
        })
        .catch(err => {
          alert(err + '请求失败')
        })
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = res
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  },
  mounted () {
    this.display = 1;
    this.userid =this.getUserId;
    this.gotoInfo()
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
  }
  .el-header {
    border: 1px solid  #dfdfdf;
    color: #333;
    display: inline-block;
    height: 180px !important;
    bottom: 10px;
    width: 100%;
  }
  .el-avatar--circle{
    width: 50px;
    height: 50px;
    float: left;
    margin-top: 90px;
  }
  .label{
    font-size: 18px;
    margin-left: 10px;
  }
  .area {
    float: left;
    margin-top: 80px;
    overflow: hidden;
  }
  .dataarea {
    font-size: 14px;
  }
  .gdataarea {
    padding-left: 25px;
  }

  .num{
    font-weight:bolder;
    color:#c10000;
  }
  .title{
    color:#3bc5ff;
    margin-left: 1px;
  }
  .gdata{
    margin:10px;
    float: left;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 50px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    border: 1px dashed #d9d9d9;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
