<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" labelPosition="left" style="margin-left: 5px">
    <el-form-item label="菜谱标题" prop="title">
      <el-input v-model="ruleForm.title" type="text"></el-input>
    </el-form-item>
    <el-form-item label="菜谱封面" required>
      <el-upload
        class="avatar-uploader"
        action="http://localhost:8080/littlekitchen/uploadImage"
        :show-file-list="false"
        :on-success="handleCoverSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="cover" :src="cover" class="avatar" style="width: 200px;height: 200px;">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item label="菜谱描述" prop="desc">
      <el-input v-model="ruleForm.desc"></el-input>
    </el-form-item>
    <el-form-item label="菜系" prop="type">
      <el-checkbox-group v-model="ruleForm.type">
        <el-checkbox v-for="item in options" :label="item.label" name="type" :key="item.index"></el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item label="用料" prop="material">
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入菜谱用料"
        v-model="ruleForm.material">
      </el-input>
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
          <img v-if="imageUrl" :src="urls[index]" class="avatar" style="width: 100px; height: 100px">
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
      options: [
        {
          value: '选项1',
          label: '鲁菜',
          index: '1'
        }, {
          value: '选项2',
          label: '川菜',
          index: '2'
        }, {
          value: '选项3',
          label: '粤菜',
          index: '3'
        }, {
          value: '选项4',
          label: '苏菜',
          index: '4'
        }, {
          value: '选项5',
          label: '浙菜',
          index: '5'
        }, {
          value: '选项6',
          label: '徽菜',
          index: '6'
        }, {
          value: '选项7',
          label: '湘菜',
          index: '7'
        }, {
          value: '选项8',
          label: '闽菜',
          index: '8'
        }
      ],
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
        ],
        material: [
          { required: true, message: '请输入菜谱用料', trigger: 'blur' },
          { min: 1, max: 350, message: '长度在 1 到 350 个字符', trigger: 'blur' }
        ]
      },
      stepToDo: [],
      textarea:'',
      description: '请输入菜谱步骤'
    }
  },
  methods: {
    submitForm (formName) {
      if(this.cover === ''){
        alert('请选择菜谱封面！')
      }
      let step_res = '';
      let picture_res = '';
      if(this.stepToDo.length === 0){
        alert("至少添加一个步骤")
      }else{
        let len = this.stepToDo.length;
        for (let i = 0; i < len; i++) {
          if(this.stepToDo[i].description!=='' && i<len-1){
            step_res+=this.stepToDo[i].description+';'
          }else{
            step_res+=this.stepToDo[i].description
          }
          if(this.urls[i] !== '' && i<len-1){
            picture_res+=this.urls[i]+";"
          }else{
            picture_res+=this.urls[i]
          }
        }

      }
      this.$refs[formName].validate((valid) => {
        let typeid =0;
        for(let i=0;i<8;i++){
          if(this.options[i].label === this.ruleForm.type[0]){
            typeid =this.options[i].index;
          }
        }
        if (valid) {
          this.$http.post('/littlekitchen/createNew', {
            title: this.ruleForm.title,
            description: this.ruleForm.desc,
            type : typeid,
            cover: this.cover,
            photo: this.imageUrl,
            material: this.ruleForm.material,
            picture : picture_res,
            step: step_res
          }).then(function (response) {
              alert('创建成功！')
          }).catch(function (error) {
              alert('创建失败！')
          })
        } else {
          console.log('error submit!!');
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    removeDomain (item) {
      var index = this.materials.indexOf(item);
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
      this.cover = res
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = res;
      this.urls.push(res)

    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

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
      this.stepToDo.push({
        description: '',
        key: Date.now()
      })
    },
    removeStep (item) {
      var index = this.stepToDo.indexOf(item);
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
