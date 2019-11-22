<template>
    <div>
        <el-header class="el-header">
          <div style="margin-left: 10px; height: 50px;">
            <el-image src="https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg" class="el-avatar--circle"></el-image>
            <div style=" float: left;margin-top: 90px;">
              <div>
                <label class="label">{{nickName}}</label>
                <!--<el-button size="small" type="primary">编辑</el-button>-->
                <el-popover
                  placement="right"
                  width="400"
                  trigger="click">
                  <el-form label-position="left" ref="form" :model="form" label-width="80px">
                    <el-form-item label="头像">
                      <el-upload
                        class="avatar-uploader"
                        action="https://jsonplaceholder.typicode.com/posts/"
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
                  <el-button slot="reference" size="small" type="primary">编辑</el-button>
                </el-popover>
              </div>
            </div>
            <div class="area">
              <div class="dataarea">
                <!--<p class="gtitle"><i class="el-icon-date el-icon&#45;&#45;left"></i>个人数据</p>-->
                <div class="gdataarea clear">
                  <div class="gdata left">
                    <p class="num">20</p>
                    <p class="title">点赞数量</p>
                  </div>
                  <div class="gdata left">
                    <p class="num">200</p>
                    <p class="title">关注的人</p>
                  </div>
                  <div class="gdata left">
                    <p class="num">3000</p>
                    <p class="title">粉丝</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-header>
    </div>
</template>

<script>
export default {
  name: 'IDCard',
  data () {
    return {
      nickName: '',
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
      imageUrl: ''
    }
  },
  methods: {
    onSubmit () {
      this.$http.post('/user', {
        firstName: 'Fred',
        lastName: 'Flintstone'
      })
        .then(function (response) {
          console.log(response)
        })
        .catch(function (error) {
          console.log(error)
        })
      console.log('submit!')
    },
    onCancel () {
      location.reload()
      // this.$router.push({ path: '/info' })
    },
    gotoInfo () {
      this.$http.get('http://localhost:8081/littlekitchen/user/1/info') // 把url地址换成你的接口地址即可
        .then(res => {
          // this.cardList[0].cover = res.data.menu[0].cover
          console.log('user=', res.data)
        })
        .catch(err => {
          console.log(err)
          alert(err + '请求失败')
        })
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
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
    this.gotoInfo()
  }
}
</script>

<style scoped>
  .el-header {
    /*background-color: #B3C0D1;*/
    border: 1px solid  #dfdfdf;
    color: #333;
    /*background-image: url("https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png");*/
    /*background-repeat:repeat-x;*/
    display: inline-block;
    height: 180px !important;
    bottom: 10px;
    width: 100%;
    /*line-height: 160px;*/
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
    /*color:lightskyblue;*/
  }
  .area {
    /*border: 1px solid #dfdfdf;*/
    float: left;
    margin-top: 80px;
    /*width: 250px;*/
    /*height: 180px;*/
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
