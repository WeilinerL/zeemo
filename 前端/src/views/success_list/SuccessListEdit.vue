<template>
  <div class="success-list-edit">
    <b-button @click="show=true" variant="success">编辑此页</b-button>

    <b-modal
      v-model="show"
      title="编辑成功案例页面"
      size="xl"
      :header-bg-variant="headerBgVariant"
      :body-bg-variant="bodyBgVariant"
      :footer-bg-variant="footerBgVariant"
    >
      <b-container fluid class="text-center">
        <b-row>
          <h5>成功案例展示</h5>
        </b-row>

        <h6 style="color: #f86c6b;">*删除和增加成功案例点击之后就生效 若视图未改变 请刷新*</h6>

        <b-row class="mb-1">
          <b-col>成功案例照片</b-col>
          <b-col>案例简介</b-col>
          <b-col>操作</b-col>
        </b-row>

        <div v-for="successListObject in successList">
          <hr>
          <b-row class="mb-1">
            <b-col>
              <img :src="successListObject.strPicture" alt="案例图片" style="width: 36px; height: 36px;">
            </b-col>
            <b-col>
              {{successListObject.strContent}}
            </b-col>
            <b-col>
              <b-button
                variant="primary"
                size="sm"
                class="m-1"
                :key="successListObject.strContent"
                v-deleteSuccessList="successListObject.strContent"
              >
                删除
              </b-button>
            </b-col>
          </b-row>

          <hr>

          <b-row>
            <b-col>
             <h6 style="color: #f86c6b;">*1.若要更换图片请直接删除该案例再重新添加*</h6>
            </b-col>
            <b-col>
              <b-form-textarea
                v-model="successListObject.strContent"
                id="textarea-small"
                size="sm"
                placeholder="点击编辑案例简介"
              ></b-form-textarea>
            </b-col>
            <b-col></b-col>
          </b-row>
        </div>

        <hr>

        <b-row>
          <b-col>
            <h6 style="color: #f86c6b;">*图片大小不能超过1MB*</h6>
              <b-form-file
              v-model="addSuccessList.strPicture"  
              accept="image/jpeg, image/png" 
              class="mt-3" 
              plain></b-form-file>
          </b-col>
          <b-col>
            <b-form-textarea
            v-model="addSuccessList.strContent"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑案例简介"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-button
              variant="primary"
              size="sm"
              class="m-1"
              v-addSuccessList
            >
              增加案例
            </b-button>
          </b-col>
        </b-row>

      </b-container>

      <div slot="modal-footer" class="w-100">
        <b-button
          variant="primary"
          size="sm"
          class="float-right m-1"
          @click="show=false"
        >
          关闭
        </b-button>
        <b-button
          variant="primary"
          size="sm"
          class="float-right m-1"
          @click="confirmChange"
        >
          确认修改
        </b-button>
      </div>
    </b-modal>

    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="successListInfoPicCheck">
      案例图片不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="successListInfoContentCheck">
      案例简介不能为空!
    </b-modal>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  inject: ['reload'], // 引入方法
  name: 'success-list-edit',
  props: {
    successListEdit: {
      type: Array,
      required: true
    }
  },
  data () {
    return {
      show: false,
      headerBgVariant: 'info',
      bodyBgVariant: 'light',
      footerBgVariant: 'warning',
      successList: [],
      addSuccessList: {
        strPicture: null,
        strContent: ''
      },
      successListInfoPicCheck: false,
      successListInfoContentCheck: false,
      dataFlag: true
    }
  },
  methods: {
    getSuccessList() {
      axios.get("http://49.235.209.42:8080/success_list/get_all_success_list")
      .then((data) => {
        // console.log(data);
        this.successList = data.data;
      })
    },
    successListAddCheck() {
      if(this.addSuccessList.strPicture == null) {
        this.successListInfoPicCheck = true;
        return false;
      }else if(this.addSuccessList.strContent == '') {
        this.successListInfoContentCheck= true;
        return false;
      }
      return true;
    },
    successListInfoEdit() {
      axios.post("http://49.235.209.42:8080/success_list/success_list_edit",this.successList)
      .then((data) => {
        // console.log(data);
      })
    },
    confirmChange() {
      this.successListInfoEdit();
      this.show = false;
      setTimeout(() => {
        this.reload();
      },"60");//0.5秒后执行函数，只执行一次。经测试此等待时间有助于刷新状态
    }
  },
  updated() {
    if(this.dataFlag) {
      this.successList = this.successListEdit;
    }
    this.dataFlag = false;
  },
  directives: {
    'deleteSuccessList': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          axios.delete("http://49.235.209.42:8080/success_list/delete_success_list/" + binding.value)
          .then((data) => {
            // console.log(data);
            if(data.data.result == "success") {
              setTimeout(() => {
                // console.log("删除");
                vnode.context.getSuccessList();
              },"10");//0.01秒后执行函数，只执行一次。
            }
          })
        }
      }
    },
    'addSuccessList': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          if(vnode.context.successListAddCheck()) {
            var file = vnode.context.addSuccessList.strPicture;
            var content = vnode.context.addSuccessList.strContent;
            // console.log("顾问图片:",file)
            let formData = new FormData();  
            formData.append("picture",file);
            axios.post("http://49.235.209.42:8080/success_list/add_success_list/" + content,formData)
            .then((data) => {
              // console.log(data);
              if(data.data.result == 'success') {
                vnode.context.getSuccessList();
                vnode.context.addSuccessList = {
                  strPicture: null,
                  strContent: ''
                }
              }
            })
          }
        }
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
