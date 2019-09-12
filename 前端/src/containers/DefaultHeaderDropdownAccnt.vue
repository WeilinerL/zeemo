<template>
  <div class="drop">
    <AppHeaderDropdown right no-caret>
      <template slot="header">
        <span style="margin-right: 20px;">默认管理员</span>
      </template>
      <template slot="dropdown">
        <b-dropdown-item @click="logoutModal = true"><i class="fa fa-lock"/> 退出登录</b-dropdown-item>
      </template>
    </AppHeaderDropdown>
    <b-modal title="通知" class="modal-danger" v-model="logoutModal" @ok="confirm = true" ok-variant="danger">
      您确定要退出登录吗？
    </b-modal>
  </div>
</template>

<script>
import { HeaderDropdown as AppHeaderDropdown } from '@coreui/vue'
export default {
  name: 'DefaultHeaderDropdownAccnt',
  props: {
    adminInfo: {
      type: String,
      required: true
    }
  },
  components: {
    AppHeaderDropdown
  },
  data: () => {
    return { 
      itemsCount: 5,
      logoutModal: false,
      confirm: false
    }
  },
  methods: {
    logout() {
      if(this.confirm) {
        this.$emit("Logout","logout");
      }
    }
  },
   watch: {
    confirm: {
      handler(newObject,oldObject) {
        // console.log(this.confirm);
        if(this.confirm) {
          this.logout();
        }
      },
      deep: true, //深度监听对象内部变化
      immediate: true
    }
  }
}
</script>
