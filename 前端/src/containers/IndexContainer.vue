<template>
  <div class="app">
    <AppHeader fixed style="background-color: #fff; border: none;overflow: hidden;">
      <SidebarToggler class="d-lg-none" display="md" mobile />
      <b-link class="navbar-brand" to="index">
        <img class="navbar-brand-full" src="img/logo-brand.png" width="75" height="75" alt="Logo" style="border-radius: 50em;">
      </b-link>
      <b-navbar-nav align fill class="d-md-down-none">
        <b-nav-item class="px-2" to="/index">首页</b-nav-item>
        <b-nav-item class="px-2" to="/overseas_study_service" exact>留学服务</b-nav-item>
        <b-nav-item class="px-2" to="/introduction_of_consultants" exact>顾问介绍</b-nav-item>
        <b-nav-item class="px-2" to="/success_list" exact>成功案例</b-nav-item>
        <b-nav-item class="px-2" to="/oversea_study_info" exact>留学信息</b-nav-item>
        <b-nav-item class="px-2" to="/summer_camp" exact>夏令营夏校</b-nav-item>
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto">
        <b-nav-item style="margin-right: 20px;" v-if="login_flag" class="px-2" @click="login" exact>管理员登录</b-nav-item>  
        <DefaultHeaderDropdownAccnt v-if="!login_flag" v-bind:adminInfo="adminInfo" v-on:Logout="logout($event)"/>
      </b-navbar-nav>
    </AppHeader>
    <div class="app-body" style="margin-top: 0px; background-color: #fff;">
      <AppSidebar class="d-lg-none" style="margin-top: 66px;">
        <SidebarHeader/>
        <SidebarForm/>
        <SidebarNav :navItems="nav"></SidebarNav>
        <SidebarFooter/>
        <SidebarMinimizer/>
      </AppSidebar>
      <main class="main">
        <div class="container-fluid p-0">
          <router-view :adminInfo="admin"></router-view>
        </div>
      </main>
    </div>
    <TheFooter class="justify-content-center">
      <!--footer-->
      <div>
        <span>CreCode</span>
        <span class="ml-1">&copy; 2019 creativeCode.</span>
      </div>
    </TheFooter>
  </div>
</template>

<script>
import nav from '@/_nav'
import { Header as AppHeader, SidebarToggler, Sidebar as AppSidebar, SidebarFooter, SidebarForm, SidebarHeader, SidebarMinimizer, SidebarNav, AsideToggler, Footer as TheFooter} from '@coreui/vue'
import DefaultHeaderDropdownAccnt from './DefaultHeaderDropdownAccnt'

export default {
  inject: ['reload'], // 引入方法
  name: 'IndexContainer',
  props: {
    adminInfo: {
      type: String,
      required: true
    }
  },
  components: {
    AsideToggler,
    AppHeader,
    AppSidebar,
    TheFooter,
    SidebarForm,
    SidebarFooter,
    SidebarToggler,
    SidebarHeader,
    SidebarNav,
    SidebarMinimizer,
    DefaultHeaderDropdownAccnt
  },
  data () {
    return {
      nav: nav.items,
      login_flag: true,
      admin: this.adminInfo
    }
  },
  methods: {
    login() {
      this.$router.push({
        name: 'Login',
        params: {
          route: this.$route.path
        }
      })
    },
    logout(message) {
      if(message == 'logout') {
        this.login_flag = true;
        this.admin = '';
        this.$emit("Logout","logout");
        this.reload();
      }
    }
  },
  computed: {
    
  },
  mounted() {
    if(this.admin != '') {
      this.login_flag = false;
    }
  }
}
</script>
