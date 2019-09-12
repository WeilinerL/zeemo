<template>
  <div class="app flex-row align-items-center">
    <div class="container">
      <b-row class="justify-content-center">
        <b-col md="4">
          <b-card no-body class="p-4">
            <b-card-body>
              <b-form>
                <b-row class="justify-content-center">
                  <h1>登录</h1>
                </b-row>
                <b-row class="justify-content-center">
                  <p class="text-muted">管理员登录界面</p>
                </b-row>
                <b-input-group class="mb-3">
                  <b-input-group-prepend><b-input-group-text><i class="icon-user"></i></b-input-group-text></b-input-group-prepend>
                  <b-form-input type="text" class="form-control" placeholder="用户名" autocomplete="username email" v-model="user.strUserName"/>
                </b-input-group>
                <b-input-group class="mb-4">
                  <b-input-group-prepend><b-input-group-text><i class="icon-lock"></i></b-input-group-text></b-input-group-prepend>
                  <b-form-input type="password" class="form-control" placeholder="密码" autocomplete="current-password" v-model="user.strPassword" @keyup.enter="login"/>
                </b-input-group>
                <b-row class="justify-content-center">
                  <b-button variant="primary" class="px-4" @click="login">登录</b-button>
                </b-row>
              </b-form>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </div>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="userNameCheck">
      用户名不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="passwordCheck">
      密码不能为空!
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="loginFeedback">
      用户名或密码错误!
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Login',
  data() {
    return {
      userNameCheck: false,
      passwordCheck: false,
      loginFeedback: false,
      user: {
        strUserName: "",
        strPassword: ""
      }
    }
  },
  methods: {
    loginCheck() {
      if(this.user.strUserName == "") {
        this.userNameCheck = true;
        return false;
      }else if(this.user.strPassword == "") {
        this.passwordCheck = true;
        return false;
      }
      return true;
    },
    login() {
      if(this.loginCheck()){
        // console.log(this.user);
        axios.post("http://49.235.209.42:8080/login",this.user)
        .then((data) => {
          // console.log(data);
          if(data.data.status == "success") {
            this.$emit("Login",data.data.userName);
            // console.log(this.$route.params.route);
            this.$router.push({path: this.$route.params.route});
            // this.$router.go(-1);//返回上一页
          }
          if(data.data.status == "fail") {
            this.loginFeedback = true;
          }
        })
      }
    }
  }
}
</script>
