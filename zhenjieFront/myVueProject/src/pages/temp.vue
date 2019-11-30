<template>
  <div>
    <Header @getPropFromHeader = "getPropFromHeader"></Header>
    <Updates @getPropFromHeader = "getPropFromHeader" v-if="contentIndex === 1"></Updates>
    <Details :getMenuId="menuId" v-else-if="contentIndex === 2"></Details>
  </div>

</template>

<script>
    import Header from '../components/header';
    import Updates from '../components/updates';
    import Details from '../components/details';

    export default {
        name: "temp",
        components: {Header, Updates, Details},
        data() {
            return {
                contentIndex: 2,
                menuId:2
            }
        },
        methods:{
            getPropFromHeader:function (prop,menuId) {
                this.contentIndex = prop;
                this.menuId = menuId;
            }
        }
    }
</script>

<style scoped>

</style>
