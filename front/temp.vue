<template>
  <div>
    <Header @getPropFromHeader = "getPropFromHeader"  @getPropUserId="getPropUserId"></Header>
    <Updates @getPropFromHeader = "getPropFromHeader" v-if="contentIndex === 1"></Updates>
    <Details :getMenuId="menuId" v-else-if="contentIndex === 2"></Details>
    <Home  @getPropFromHeader = "getPropFromHeader" v-if="contentIndex === 0" @getPropUserId="getPropUserId"></Home>
    <UserInfo :getUserId="userid" v-if="contentIndex === 3" @getPropFromHeader = "getPropFromHeader" ></UserInfo>
  </div>

</template>

<script>
    import Header from '../components/header';
    import Updates from '../components/updates';
    import Details from '../components/details';
    import Home from "../components/Home";
    import UserInfo from "../components/UserInfo";

    export default {
        name: "temp",
        components: {UserInfo, Home, Header, Updates, Details},
        data() {
            return {
                contentIndex: 0, // 0：首页 1：作品动态 2：详情 3.个人中心
                menuId:0,
                userid:0,
            }
        },
        methods:{
            getPropFromHeader:function (prop,menuId) {
                this.contentIndex = prop;
                this.menuId = menuId;
            },
            getPropUserId:function (prop,userid) {
              if(prop ===this.contentIndex){
                location.reload();
              }
              this.userid = userid;
              this.contentIndex = prop;
          }
        }
    }
</script>

<style scoped>

</style>
