<template>
  <router-view :adminInfo="adminInfo" @Login="login" @Logout="logout" v-if="isRouterAlive"></router-view>
</template>

<script>
export default {
  name: 'app',
  provide() { // 注册一个方法
    return {
      reload: this.reload
    }
  },
  data() {
    return{
      adminInfo: "",
      isRouterAlive: true
    }
  },
  methods: {
    login(message) {
      this.adminInfo = message;
    },
    logout(message) {
      this.adminInfo = '';
    },
    reload() {
      this.isRouterAlive = false
      this.$nextTick(function() {
        this.isRouterAlive = true
      })
    }
  }
}
</script>

<style lang="scss">
  // CoreUI Icons Set
  @import '~@coreui/icons/css/coreui-icons.min.css';
  /* Import Font Awesome Icons Set */
  $fa-font-path: '~font-awesome/fonts/';
  @import '~font-awesome/scss/font-awesome.scss';
  /* Import Simple Line Icons Set */
  $simple-line-font-path: '~simple-line-icons/fonts/';
  @import '~simple-line-icons/scss/simple-line-icons.scss';
  /* Import Flag Icons Set */
  @import '~flag-icon-css/css/flag-icon.min.css';
  /* Import Bootstrap Vue Styles */
  @import '~bootstrap-vue/dist/bootstrap-vue.css';
  // Import Main styles for this application
  @import 'assets/scss/style';
</style>
