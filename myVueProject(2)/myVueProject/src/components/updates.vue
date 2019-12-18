<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div class="body">
    <div class="all">
      <b-card no-body style="height: 100%">
        <b-tabs card style="background-color: lightblue">
          <b-tab title="作品动态" active>
            <div v-for="(article,i) in articleData.menu">
              <b-card class="card_content">
                <b-media>
                  <template v-slot:aside>
                    <b-img :src='articleData.user[i].photo' width="64" alt="placeholder" rounded="circle"></b-img>
                  </template>
                  <h5 class="mt-0 text_content">{{articleData.user[i].nickname}}</h5>
                  <b-card-text class="small text-muted text_content">{{dateFormat(article.createTime)}}</b-card-text>
                  <p>
                    {{article.title}}
                  </p>
                  <p>
                    描述：{{article.description}}
                  </p>
                  <div>
                    <b-img :src='article.cover' rounded width="500" fluid alt="Responsive image"
                           style="cursor: pointer;" @click="goToDetails(article.menuid)"></b-img>
                  </div>
                  <!--两个图标-->
                  <i class="iconfont icon-praise" v-if="!articleData.isThumbUp[i]" @click="addThumbup(article.menuid)">{{articleData.ThumbUpNum[i]}}</i>
                  <i class="iconfont icon-praise_fill" v-if="articleData.isThumbUp[i]"
                     @click="deleteThumbup(article.menuid)">{{articleData.ThumbUpNum[i]}}</i>
                  <i class="iconfont icon-collection" v-if="!articleData.isFavorite[i]"
                     @click="addfavorite(article.menuid)">{{articleData.favoriteNum[i]}}</i>
                  <i class="iconfont icon-collection_fill" v-if="articleData.isFavorite[i]"
                     @click="deletefavorite(article.menuid)">{{articleData.favoriteNum[i]}}</i>
                </b-media>
              </b-card>
            </div>

          </b-tab>

          <b-tab title="关注的人">
            <!--关注的人列表-->
            <b-list-group flush>
              <b-list-group-item href="#" style="padding: 30px" v-for="(follow,i) in follows" :key="i">
                <!--关注的人的头像+昵称+个人签名-->
                <b-media>
                  <template v-slot:aside>
                    <b-img :src='follow.photo' style="cursor: pointer;" width="64" height="64" alt="头像" rounded="circle"
                           class="avatar_img" @click="goToUserInfo(follow.userid)"></b-img>
                  </template>
                  <b-row>
                    <b-col lg="8" class="pb-2">
                      <h5 class="mt-0 text_content">{{follow.nickname}}</h5>
                      <p class="mb-0 text_content">
                        {{follow.description}}
                      </p>
                    </b-col>
                    <b-col lg="4" class="pb-2">
                      <b-button style="vertical-align: center; margin-top: 10%" variant="outline-secondary" @click="cancelFollow(follow.userid)">取消关注</b-button>
                    </b-col>
                  </b-row>
                </b-media>
              </b-list-group-item>
            </b-list-group>
          </b-tab>
        </b-tabs>
      </b-card>
    </div>
  </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'updates',
        data() {
            return {
                //关注的人的信息
                follows: [],
                //关注的人的作品对象
                articleData: {},
                time: ''
            }
        },

        methods: {
            //获取关注的人的列表
            getFollows() {
                axios.get('http://localhost:8080/littlekitchen/updates/follows/'
                ).then((res) =>
                    this.follows = res.data.followUsers
                ).catch(err => {
                    console.log('error')
                })
            },

            //获取作品
            getArticles() {
                axios.get('http://localhost:8080/littlekitchen/updates/list/'
                ).then((res) => {
                        this.articleData = res.data;
                    }
                ).catch(err => {
                    console.log('error')
                })
            },


            //点赞
            addThumbupRequest(menuId) {
                axios.get('http://localhost:8080/littlekitchen/updates/addthumbup/' + menuId
                ).then((res) =>
                    this.successToThumbup()
                ).catch(err => {
                    console.log('error')
                })
            },

            //取消点赞
            deleteThumbupRequest(menuId) {
                axios.get('http://localhost:8080/littlekitchen/updates/deletethumbup/' + menuId
                ).then((res) =>
                    console.log(res.data.thumbupNumber)
                ).catch(err => {
                    console.log('error')
                })
            },

            //收藏
            addfavoriteRequest(menuId) {
                axios.get('http://localhost:8080/littlekitchen/updates/addfavorite/' + menuId
                ).then((res) =>
                    this.successToFavorite()
                ).catch(err => {
                    console.log('error')
                })
            },

            //取消收藏
            deletefavoriteRequest(menuId) {
                axios.get('http://localhost:8080/littlekitchen/updates/deletefavorite/' + menuId
                ).then((res) =>
                    console.log(res.data.favoriteNumber)
                ).catch(err => {
                    console.log('error')
                })
            },

            // 取消关注
            cancelFollowRequest(followId){
                axios.get('http://localhost:8080/littlekitchen/user/'+followId+'/deletefollow'
                ).then((res) =>
                    console.log("取关成功")
                ).catch(err => {
                    console.log('error')
                })

            },

            addThumbup(menuId) {
                this.addThumbupRequest(menuId);
                this.getArticles();
            },

            deleteThumbup(menuId) {
                this.deleteThumbupRequest(menuId);
                this.getArticles();
            },

            addfavorite(menuId) {
                this.addfavoriteRequest(menuId);
                this.getArticles();
            },

            deletefavorite(menuId) {
                this.deletefavoriteRequest(menuId);
                this.getArticles();
            },

            successToThumbup() {
                this.$toast({
                    text: '点赞成功',
                    type: 'success'
                })
            },

            successToFavorite() {
                this.$toast({
                    text: '收藏成功',
                    type: 'success'
                })
            },

            // 取消关注
            cancelFollow(userId){
                this.cancelFollowRequest(userId);
                this.getFollows();
                this.getArticles();
            },

            // 格式化日期
            dateFormat(time) {
                let timeStamp = new Date(time);
                return timeStamp.toLocaleDateString().replace(/\//g, "-") + " " + timeStamp.toTimeString().substr(0, 8);
            },

            goToDetails(menuId) {
                this.$emit("getPropFromHeader", 2, menuId);
            },

            goToUserInfo(userId) {
                this.$emit("getPropUserId", 3, userId);
            },


        },

        //挂载阶段获取初始化信息
        mounted() {
            this.getFollows();
            this.getArticles();
        }
    }
</script>

<style scoped>

  /*背景颜色*/
  .body {
    /*background-color: lightblue;*/
  }

  /*
  整体布局
  左右俩边留空
  */
  .all {
    margin: 0 300px 0 300px;
  }

  /*所有字体内容垂直居中，左对齐*/
  .text_content, p {
    vertical-align: center;
    text-align: left;
  }

  /*卡片之间隔开*/
  .card_content {
    margin: 4px;
  }

</style>
