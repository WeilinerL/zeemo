<template>
  <div class="consultants-edit">
    <b-button @click="show=true" variant="success">编辑此页</b-button>

    <b-modal
      v-model="show"
      title="编辑顾问介绍页面"
      size="xl"
      :header-bg-variant="headerBgVariant"
      :body-bg-variant="bodyBgVariant"
      :footer-bg-variant="footerBgVariant"
    >
      <b-container fluid class="text-center">
        <b-row>
          <h5>顾问介绍展示</h5>
        </b-row>

        <h6 style="color: #f86c6b;">*1.若要更改顾问介绍姓名 请先删除该顾问介绍 再增加相应的顾问介绍信息 2.删除和增加顾问介绍点击之后就生效 若视图未改变 请刷新*</h6>

        <b-row class="mb-1">
          <b-col>顾问照片</b-col>
          <b-col>顾问姓名</b-col>
          <b-col>顾问头衔</b-col>
          <b-col>顾问简介</b-col>
          <b-col>操作</b-col>
        </b-row>

        <div v-for="consultanObject in consultant">
          <hr>
          <b-row class="mb-1">
            <b-col>
              <img :src="consultanObject.strPicture" alt="顾问图片" style="width: 36px; height: 36px;">
            </b-col>
            <b-col>
              {{consultanObject.strName}}
            </b-col>
            <b-col>
              <pre>{{consultanObject.strTitle}}</pre>
            </b-col>
            <b-col>
              {{consultanObject.strIntroduction}}
            </b-col>
            <b-col>
              <b-button
                variant="primary"
                size="sm"
                class="m-1"
                :key="consultanObject.strName"
                v-deleteConsultant="consultanObject.strName"
              >
                删除
              </b-button>
            </b-col>
          </b-row>

          <hr>

          <b-row>
            <b-col>
             <h6 style="color: #f86c6b;">*1.若要更换图片请直接删除该顾问信息再重新添加*</h6>
            </b-col>
            <b-col>
              
            </b-col>
            <b-col>
              <b-form-textarea
                v-model="consultanObject.strTitle"
                id="textarea-small"
                size="sm"
                placeholder="点击编辑顾问头衔"
              ></b-form-textarea>
            </b-col>
            <b-col>
              <b-form-textarea
              v-model="consultanObject.strIntroduction"
                id="textarea-small"
                size="sm"
                placeholder="点击编辑顾问简介"
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
              v-model="addConsultant.strPicture"  
              accept="image/jpeg, image/png" 
              class="mt-3" 
              plain></b-form-file>
          </b-col>
          <b-col>
            <b-form-textarea
            v-model="addConsultant.strName"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑顾问姓名"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-textarea
            v-model="addConsultant.strTitle"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑顾问头衔"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-textarea
            v-model="addConsultant.strIntroduction"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑顾问简介"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-button
              variant="primary"
              size="sm"
              class="m-1"
              v-addConsultant
            >
              增加顾问
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

    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="consultantsInfoPicCheck">
      顾问照片不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="consultantsInfoNameCheck">
      顾问姓名不能为空!
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="consultantsInfoTitleCheck">
      顾问头衔不能为空!
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="consultantsInfoIntroCheck">
      顾问简介不能为空!
    </b-modal>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  inject: ['reload'], // 引入方法
  name: 'consultants-edit',
  props: {
    consultantEdits: {
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
      consultant: [],
      addConsultant: {
        strPicture: null,
        strName: '',
        strTitle: '',
        strIntroduction: ''
      },
      consultantsInfoPicCheck: false,
      consultantsInfoNameCheck: false,
      consultantsInfoTitleCheck: false,
      consultantsInfoIntroCheck: false,
      dataFlag: true
    }
  },
  methods: {
    getConsultants() {
      axios.get("http://49.235.209.42:8080/consultant/get_consultants")
      .then((data) => {
        // console.log(data);
        this.consultant = data.data;
      })
    },
    consultantEdit() {
      axios.post("http://49.235.209.42:8080/consultant/consultant_edit",this.consultant)
      .then((data) => {
        // console.log(data);
      })
    },
    confirmChange() {
      this.consultantEdit();
      this.show = false;
      setTimeout(() => {
        this.reload();
      },'60');//0.5秒后执行函数，只执行一次。经测试此等待时间有助于刷新状态
    },
    serviceFlowAddCheck() {
      if(this.addConsultant.strPicture == null) {
        this.consultantsInfoPicCheck = true;
        return false;
      }else if(this.addConsultant.strName == '') {
        this.consultantsInfoNameCheck= true;
        return false;
      }else if(this.addConsultant.strTitle == '') {
        this.consultantsInfoTitleCheck= true;
        return false;
      }else if(this.addConsultant.strIntroduction == '') {
        this.consultantsInfoIntroCheck = true;
        return false;
      }
      return true;
    }
  },
  updated() {
    if(this.dataFlag) {
      this.consultant = this.consultantEdits;
    }
    this.dataFlag = false;
  },
  directives: {
    'deleteConsultant': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          axios.delete("http://49.235.209.42:8080/consultant/delete_consultant/" + binding.value)
          .then((data) => {
            // console.log(data);
            if(data.data.result == "success") {
              setTimeout(() => {
                // console.log("删除");
                vnode.context.getConsultants();
              },'10');//0.01秒后执行函数，只执行一次。
            }
          })
        }
      }
    },
    'addConsultant': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          if(vnode.context.serviceFlowAddCheck()) {
            var file = vnode.context.addConsultant.strPicture;
            var name = vnode.context.addConsultant.strName;
            // console.log("顾问图片:",file)
            vnode.context.addConsultant.strPicture = ''
            let formData = new FormData();  
            formData.append("picture",file);
            // console.log(name);
            axios.post("http://49.235.209.42:8080/consultant/add_consultant/" + name + "/" + vnode.context.addConsultant.strTitle + "/" + vnode.context.addConsultant.strIntroduction,formData)
            .then((data) => {
              // console.log(data);
              if(data.data.result == 'success') {
                vnode.context.getConsultants();
                vnode.context.addConsultant = {
                  strPicture: null,
                  strName: '',
                  strTitle: '',
                  strIntroduction: ''
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
