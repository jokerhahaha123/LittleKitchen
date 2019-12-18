<template>
  <div>
    <Header @getPropFromHeader = "getPropFromHeader"  @getPropUserId="getPropUserId"></Header>
    <Updates @getPropFromHeader = "getPropFromHeader" @getPropUserId="getPropUserId" v-if="contentIndex === 1"></Updates>
    <Details :getMenuId="menuId"  @getPropUserId="getPropUserId" v-else-if="contentIndex === 2"></Details>
    <Home  @getPropFromHeader = "getPropFromHeader" v-if="contentIndex === 0" @getPropUserId="getPropUserId"></Home>
    <UserInfo :getUserId="userid" v-if="contentIndex === 3" @getPropFromHeader = "getPropFromHeader" ></UserInfo>
    <SearchRes :getkeyword="keyword" @getPropFromHeader = "getPropFromHeader"  @getPropUserId="getPropUserId" v-if="contentIndex === 4" ></SearchRes>
  </div>

</template>

<script>
  import Header from '../components/header';
  import Updates from '../components/updates';
  import Details from '../components/details';
  import Home from "../components/Home";
  import UserInfo from "../components/UserInfo";
  import SearchRes from "../components/searchRes"

  export default {
    name: "temp",
    components: {UserInfo, Home, Header, Updates, Details,SearchRes},
    data() {
      return {
        contentIndex: 0, // 0：首页 1：作品动态 2：详情 3.个人中心 4.搜索
        menuId:0,
        userid:0,
        keyword:"",
      }
    },
    methods:{
      getPropFromHeader:function (prop,menuId,keyword) {
        console.log("getPropFromHeader:",prop,menuId,keyword);
        this.contentIndex = prop;
        this.menuId = menuId;
        this.keyword = keyword;
      },
      getPropUserId:function (prop,userid) {
        if(prop ===this.contentIndex){
          location.reload();
        }
        this.userid = userid;
        this.contentIndex = prop;
      },
    }
  }
</script>

<style scoped>

</style>
