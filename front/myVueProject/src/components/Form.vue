<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" labelPosition="left" style="margin-left: 5px">
    <el-form-item label="菜谱标题" prop="title">
      <el-input v-model="ruleForm.title" type="text"></el-input>
    </el-form-item>
    <el-form-item label="菜谱封面" prop="cover">
      <el-upload
        class="avatar-uploader"
        action="http://localhost:8080/littlekitchen/uploadImage"
        :show-file-list="false"
        :on-success="handleCoverSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="cover" :src="cover" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item label="菜谱描述" prop="desc">
      <el-input v-model="ruleForm.desc"></el-input>
    </el-form-item>
    <el-form-item label="菜系" prop="type">
      <el-checkbox-group v-model="ruleForm.type">
        <el-checkbox label="鲁菜" name="type"></el-checkbox>
        <el-checkbox label="川菜" name="type"></el-checkbox>
        <el-checkbox label="粤菜" name="type"></el-checkbox>
        <el-checkbox label="苏菜" name="type"></el-checkbox>
        <el-checkbox label="浙菜" name="type"></el-checkbox>
        <el-checkbox label="徽菜" name="type"></el-checkbox>
        <el-checkbox label="湘菜" name="type"></el-checkbox>
        <el-checkbox label="闽菜" name="type"></el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item label="用料" required>
      <div style="margin: 5px"
        v-for="(domain) in materials"
        :key="domain.key"
        :prop="domain.value"
        :rules="{
      required: true, message: '用料不能为空', trigger: 'blur'
    }"
      >
        <el-input v-model="domain.value" style="width: 450px" placeholder="比如：2个鸡蛋一个西红柿"></el-input>
        <el-button @click.prevent="removeDomain(domain)" type="danger" size="small">删除</el-button>
      </div>
    </el-form-item>
    <el-form-item>
      <el-button @click="addDomain" style="float: left" size="small">新增用料</el-button>
    </el-form-item>
    <el-form-item label="做法" required>
     <div style="margin: 5px; height: 60px;"
       v-for="(item, index) in stepToDo"
           :key="index"
           :prop="index + item.description">
       <el-input style="float: left; width: 400px;"
                 type="textarea"
                 :placeholder="item.description"
                 v-model="item.description">
       </el-input>
       <el-upload style="float: left;margin-left: 5px"
         class="avatar-uploader"
         action="http://localhost:8080/littlekitchen/uploadImage"
         :show-file-list="true"
         :on-success="handleAvatarSuccess"
         :before-upload="beforeAvatarUpload">
         <img v-if="imageUrl" :src="imageUrl" class="avatar">
         <i v-else class="el-icon-plus avatar-uploader-icon"></i>
       </el-upload>
       <el-button @click.prevent="removeStep(item)" type="danger" size="small">删除</el-button>
     </div>
    </el-form-item>
    <el-form-item>
      <el-button @click="addStep(imageUrl)" style="float: left" size="small">添加步骤</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
/* eslint-disable */
export default {
  name: 'Form',
  data () {
    return {
      ruleForm: {
        cover: '',
        material: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: '',
        image: '',
        title: ''
      },
      materials: [],
      urls: [],
      cover: '',
      imageUrl: '',
      rules: {
        title: [
          { required: true, message: '请输入菜谱标题', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
        ],
        date2: [
          { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
        ],
        type: [
          { type: 'array', required: true, message: '只能选择一个菜系', trigger: 'change', max: 1 }
        ],
        desc: [
          { required: true, message: '请填写菜谱描述', trigger: 'blur' },
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
        ]
      },
      stepToDo: []
    }
  },
  methods: {
    submitForm (formName) {
      console.log(this.ruleForm.title,this.ruleForm.cover,this.ruleForm.desc)
      let material_res = ''
      if(this.materials.length === 0){
        alert("至少输入一个用料")
      }else{
        let len = this.materials.length
        let isEmpty =true
        // console.log(this.materials, len)
        for (let i = 0; i < len; i++) {
          // console.log(this.materials[i].value)
          if(this.materials[i].value!==''){
            isEmpty =false
            material_res+=this.materials[i].value+';'
          }
        }
        if(isEmpty){
          alert("至少输入一个用料")
        }
      }

      let step_res = ''
      let picture_res = ''
      if(this.stepToDo.length === 0){
        alert("至少添加一个步骤")
      }else{
        let len = this.stepToDo.length
        let isEmpty =true
        // console.log(this.stepToDo, len)
        for (let i = 0; i < len; i++) {
          // console.log(this.stepToDo[i].description)
          if(this.stepToDo[i].description!==''){
            isEmpty =false
            step_res+=this.stepToDo[i].description+';'
          }
          if(this.urls[i] !== ''){
            picture_res+=this.urls[i]+";"
          }
        }
        if(isEmpty){
          alert("至少添加一个步骤")
        }
      }
      // console.log(material_res)
      // console.log(picture_res)
      // console.log(step_res)
      this.$refs[formName].validate((valid) => {
        console.log(valid)
        if (valid) {
          this.$http.post('http://localhost:8080/littlekitchen/createNew', {
            title: this.ruleForm.title,
            description: this.ruleForm.desc,
            cover: this.cover,
            photo: this.imageUrl,
            material: material_res,
            picture : picture_res,
            step: step_res
          }).then(function (response) {
              alert('创建成功！')
              console.log('msg: ', response.data)
          }).catch(function (error) {
              alert('创建失败！')
              console.log(error)
          })
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    removeDomain (item) {
      var index = this.materials.indexOf(item)
      if (index !== -1) {
        this.materials.splice(index, 1)
      }
    },
    addDomain () {
      this.materials.push({
        value: '',
        key: Date.now()
      })
    },
    handleCoverSuccess (res, file) {
      this.cover = URL.createObjectURL(file.raw)
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
    },
    deleteRow (index, rows) {
      rows.splice(index, 1)
    },
    addStep (url) {
      console.log(url,this.imageUrl)
      this.urls.push(url)
      this.stepToDo.push({
        description: '',
        key: Date.now()
      })
    },
    removeStep (item) {
      var index = this.stepToDo.indexOf(item)
      if (index !== -1) {
        this.stepToDo.splice(index, 1)
      }
    }
  }
}
</script>

<style scoped>
  .demo-ruleForm{
    /*border: 1px solid red;*/
    margin-top: 15px;
    width: 650px;
  }
  .namearea p{
    /*padding:10px;*/
    text-align: left;
    font-size:14px;
    line-height:14px;
  }
  .col{
    width: 180px;
    height: 180px;
    margin: 5px;
    /*color: white;*/
    display:inline-block;
  }
  .el-avatar--circle{
    margin-top: 2px;
    margin-bottom: 0;
    width: 100px;
    height: 100px;
  }
  .area {
    border: 1px solid #dfdfdf;
    width: 250px;
    height: 180px;
    overflow: hidden;
  }
  .dataarea {
    /*padding: 10px;*/
    /*text-align: center;*/
    font-size: 14px;
  }
  .gdataarea {
    padding-left: 25px;
  }
  .gtitle{
    width:100%;
    height:30px;
    line-height:30px;
    cursor: pointer;
    background-color: #3bc5ff;
    color: white;
    display: block;
  }
  /*p{*/
    /*line-height:38px;*/
  /*}*/
  .num{
    font-weight:bolder;
    color:#c10000;
  }
  .title{
    color:#3bc5ff;
  }
  .gdata{
    margin:10px;
    float: left;
  }
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before, .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
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
</style>
