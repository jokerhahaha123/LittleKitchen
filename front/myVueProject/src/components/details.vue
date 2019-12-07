<template>
  <div id="detail">
    <div id="authorMsg">
      <el-menu class="el-menu-demo" mode="horizontal" style="border-bottom-color:#ffffff!important;">
        <el-menu-item style="width:12%" @click="userInfo">
          <img :src="userAvatar" style=" border-radius: 50%; width: 40px;height: 40px ;margin-right: 25px;">
          <span style="font-weight: bold">{{nickName}}</span>
        </el-menu-item>
        <span  class="title_time">{{time}}</span>
        <span class="title_time">{{title}}</span>
      </el-menu>
    </div>
    <div>
      <label class="subTitle cover"><i class="el-icon-dish"></i> {{description}}</label><br/>
      <img :src="cover" class="cover" style="width: 600px;height: 400px"/>
    </div>
    <div id="material">
      <label class="subTitle">&nbsp用料</label><br>
      <ol>
        <li v-for="item in materialData" style="text-align: left">{{item}}</li>
      </ol>
    </div>
    <div id="detailMsg">
      <label class="subTitle">做法</label><br/><br/>
      <el-steps direction="vertical">
        <span v-for = "item in menu" >
         <span v-for = "(temp,i) in item.stepTitle" style="display:inline-block">
           <img  v-if="item.stepPicture[i]" style="float:right;width:280px;height:180px;padding: 4px 4px" :src="item.stepPicture[i]"/>
             <el-step style="width: 480px;" :title="item.stepTitle[i]" :description="item.stepDescription[i]" ></el-step>
         </span>
       </span>
      </el-steps>
      <el-row style="float: right;margin:3% 5%">
        <el-badge :value="thumbVal" class="item">
          <el-button size="small" type="primary" @click = thumb v-if=this.isThu>点赞</el-button>
          <el-button size="small" @click = thumb v-else>点赞</el-button>
        </el-badge>
        <el-badge :value="favVal" class="item">
          <el-button size="small" type="primary" @click = favorite v-if=this.isFav>收藏</el-button>
          <el-button size="small" @click = favorite v-else>收藏</el-button>
        </el-badge>
      </el-row>
    </div>
  </div>
</template>

<script>
    import axios from 'axios'
    import qs from 'qs';
    export default {
        name: "details",
        props: ['getMenuId'],
        data(){
            return {
                nickName: "",
                userAvatar: "",
                userId:"",
                title: "",
                time: "",
                menuid: this.getMenuId,
                description: "",
                isThu:"",
                isFav:"",
                cover: "",
                materialData: [],
                thumbVal:"",
                favVal:"",
                menu: [{
                    stepTitle:[],
                    stepDescription: [],
                    stepPicture:[],
                }],
            }
        },
        created(){
            this.loadData()
        },
        methods:{
            loadData(){
                axios({
                    method:"get",
                    url:"/littlekitchen/details/"+this.menuid,
                }).then((response=> {
                    this.userId = response.data.menuDetails.userid;
                    this.menuid = response.data.menuDetails.menuid;
                    this.title = response.data.menuDetails.title;
                    this.description = response.data.menuDetails.description;
                    this.cover = response.data.menuDetails.cover;
                    this.favVal = response.data.favNum;
                    this.thumbVal = response.data.thuNum;
                    this.isFav = response.data.isFav;
                    this.isThu = response.data.isThu;
                    var timeStamp = new Date(response.data.menuDetails.createTime);
                    this.time = timeStamp.toLocaleDateString().replace(/\//g, "-") + " " + timeStamp.toTimeString().substr(0, 8);
                    this.materialData = response.data.menuDetails.material.split(";");

                    this.menu[0].stepPicture = response.data.menuDetails.picture.split(";");
                    var step = response.data.menuDetails.step.split(/[.;]/);
                    for (var i = 0; i < step.length; i++) {
                        this.menu[0].stepTitle.push("步骤 " + step[i]);
                        this.menu[0].stepDescription.push(step[i + 1])
                        i++;
                    }
                    axios({
                        method: "get",
                        url:"/littlekitchen/user/"+this.userId+"/info",
                    }).then((response => {
                        // console.log(response.data);
                        this.nickName = response.data.user.nickname;
                        this.userAvatar = response.data.user.photo;
                    })).catch((error => {
                        console.log(error)
                    }))

                })).catch((error=>{
                    console.log(error)
                }))
            },
            userInfo(){
                //location.href='/#/userInfo'
              console.log('useid',this.userId)
              this.$emit("getPropUserId",3,this.userId);
            },
            thumb(){
                if (this.isThu) {//已点赞
                    axios({
                        method: "get",
                        url: "/littlekitchen/updates/deletethumbup/" + this.menuid,
                        data: {
                            menuid: this.menuid,
                        },
                        headers: {
                            'Content-Type': 'application/json;charset=UTF-8',
                        }
                    }).then((response => {
                        console.log(response.data);
                        if (response.data.message === 1) {
                            this.isThu = false;
                            this.thumbVal = response.data.thumbupNumber;
                        }
                        else{
                            alert("取消点赞失败");
                        }
                    })).catch((error => {
                        console.log(error)
                    }))

                }
                else {//未点赞
                    axios({
                        method: "get",
                        url: "/littlekitchen/updates/addthumbup/" + this.menuid,
                        data: {
                            menuid: this.menuid,
                        },
                        headers: {
                            'Content-Type': 'application/json;charset=UTF-8',
                        }
                    }).then((response => {
                        console.log(response.data);
                        if (response.data.message === 1) {
                            this.thumbVal = response.data.thumbupNumber;
                            this.isThu = true;
                        }
                        else {
                            alert("点赞失败");
                        }

                    })).catch((error => {
                        console.log(error)
                    }))
                }
            },
            favorite(){
                if (this.isFav) {//已收藏
                    axios({
                        method: "get",
                        url: "/littlekitchen/updates/deletefavorite/" + this.menuid,
                        data: {
                            menuid: this.menuid,
                        },
                        headers: {
                            'Content-Type': 'application/json;charset=UTF-8',
                        }
                    }).then((response => {
                        console.log(response.data);
                        if (response.data.message===1) {
                            this.favVal = response.data.favoriteNumber;
                            this.isFav = false;
                        }
                        else {
                            alert("取消收藏失败");
                        }

                    })).catch((error => {
                        console.log(error)
                    }))

                }
                else {//未收藏
                    axios({
                        method: "get",
                        url: "/littlekitchen/updates/addfavorite/" + this.menuid,
                        data: {
                            menuid: this.menuid,
                        },
                        headers: {
                            'Content-Type': 'application/json;charset=UTF-8',
                        }
                    }).then((response => {
                        console.log(response.data);
                        if (response.data.message === 1) {
                            this.favVal = response.data.favoriteNumber;
                            this.isFav = true;
                        }
                        else {
                            alert("收藏失败");
                        }

                    })).catch((error => {
                        console.log(error)
                    }))
                }
            }

        }
    }
</script>

<style scoped>
  #authorMsg{
    margin:2% 20% 0 20%;
    padding: 0 25px;
  }
  #material{
    margin:0 22%;
    padding: 0 25px;
  }
  .cover{
    margin:1% 22%;
    padding: 0 25px;
  }
  #detailMsg{
    margin:2% 22%;
    padding: 0 30px;

  }
  .title_time{
    float: right;
    outline: none;
    line-height: 60px;
    padding-right: 10%;
  }
  .subTitle{
    float: left;
    font-size: large;
    font-weight: bold;
    color: #ffc478;
  }
  .item {
    margin-top: 10px;
    margin-right: 30px;
  }
  .tr td{border-bottom:1px solid #BBB;}
</style>
