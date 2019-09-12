<template>
  <div class="oss-edit">
    <b-button @click="show=true" variant="success">编辑此页</b-button>

    <b-modal
      v-model="show"
      title="编辑留学服务页面"
      size="xl"
      :header-bg-variant="headerBgVariant"
      :body-bg-variant="bodyBgVariant"
      :footer-bg-variant="footerBgVariant"
    >
      <b-container fluid class="text-center">
        <b-row>
          <h5>第一部分 留学信息展示</h5>
        </b-row>

        <h6 style="color: #f86c6b;">*1.若要更改留学信息标题 请先删除该留学信息 再增加相应的留学信息 2.删除和增加留学信息点击之后就生效 若视图未改变 请刷新*</h6>

        <b-row class="mb-1">
          <b-col>标题</b-col>
          <b-col>内容</b-col>
          <b-col>背景颜色</b-col>
          <b-col>操作</b-col>
        </b-row>

        <div v-for="ossObject in oss">
          <hr>
          <b-row class="mb-1">
            <b-col>
              {{ossObject.ossTitle}}
            </b-col>
            <b-col>
              {{ossObject.content}}
            </b-col>
            <b-col>
              <div :class="ossObject.bacColor" style="width: 22px; height: 22px; display: inline-block;"></div>
            </b-col>
            <b-col>
              <b-button
                variant="primary"
                size="sm"
                class="m-1"
                :key="ossObject.bacColor" 
                v-deleteOss="ossObject.ossTitle"
              >
                删除
              </b-button>
            </b-col>
          </b-row>
          <hr>
          <b-row class="mb-1">
            <b-col>
            </b-col>
            <b-col>
              <b-form-textarea
                id="textarea-small"
                size="sm"
                placeholder="点击编辑留学服务作者"
                v-model="ossObject.content"
              ></b-form-textarea>
            </b-col>
            <b-col>
              <b-form-select 
              v-model="ossObject.bacColor" 
              :options="options" 
              size="sm" 
              class="mt-2"
              ></b-form-select>
            </b-col>
            <b-col></b-col>
          </b-row>
        </div>

        <hr>

        <b-row>
          <b-col>
            <b-form-textarea
              v-model="addOss.ossTitle"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑留学信息标题"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-textarea
              v-model="addOss.content"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑留学信息内容"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-select 
              v-model="addOss.bacColor"
              :options="options" 
              size="sm" 
              class="mt-2"
            ></b-form-select>
          </b-col>
          <b-col>
            <b-button
              variant="primary"
              size="sm"
              class="m-1"
              v-addOss
            >
              增加留学信息
            </b-button>
            <div :class="addOss.bacColor" style="width: 36px; height: 36px; position: relative; top: -33px;"></div>
          </b-col>
        </b-row>

        <b-row class="mt-3">
          <h5>第二部分 服务流程展示</h5>
        </b-row>

        <h6 style="color: #f86c6b;">*1.若要更改服务流程标题 请先删除该服务流程再增加相应的服务流程2.删除和增加服务流程点击之后就生效 若视图未改变 请刷新*</h6>

        <b-row class="mb-1">
          <b-col>圆圈内的图片</b-col>
          <b-col>标题</b-col>
          <b-col>内容</b-col>
          <b-col>背景颜色</b-col>
          <b-col>操作</b-col>
        </b-row>

        <div v-for="SFObject in serviceFlow">
          <hr>
          <b-row class="mb-1">
            <b-col>
              <img :src="SFObject.sfbackPic" alt="背景图片" style="width: 36px; height: 36px;">
            </b-col>
            <b-col>
              {{SFObject.sftitle}}
            </b-col>
            <b-col>
              <pre>{{SFObject.sfcontent}}</pre>
            </b-col>
            <b-col>
              <div :class="SFObject.sfbackColor" style="width: 22px; height: 22px; display: inline-block;"></div>
            </b-col>
            <b-col>
              <b-button
                variant="primary"
                size="sm"
                class="m-1"
                :key="SFObject.sftitle"
                v-deleteServiceFlow="SFObject.sftitle"
              >
                删除
              </b-button>
            </b-col>
          </b-row>

          <b-row>
            <b-col>
             <h6 style="color: #f86c6b;">*1.若要更换图片请直接删除该步骤再重新添加*</h6>
            </b-col>
            <b-col>
            </b-col>
            <b-col>
              <b-form-textarea
                v-model="SFObject.sfcontent"
                id="textarea-small"
                size="sm"
                placeholder="点击编辑服务流程内容"
              ></b-form-textarea>
            </b-col>
            <b-col>
              <b-form-select 
              v-model="SFObject.sfbackColor"
              :options="options" 
              size="sm" 
              class="mt-2"
              ></b-form-select>
            </b-col>
            <b-col></b-col>
          </b-row>
        </div>

        <hr>

        <b-row>
          <b-col>
            <h6 style="color: #f86c6b;">*图片大小不能超过1MB*</h6>
              <b-form-file v-model="addServiceFlow.sfbackPic" accept="image/jpeg, image/png" class="mt-3" plain></b-form-file>
          </b-col>
          <b-col>
            <b-form-textarea
              v-model="addServiceFlow.sftitle"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑服务流程标题"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-textarea
              v-model="addServiceFlow.sfcontent"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑服务流程内容"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-select 
              v-model="addServiceFlow.sfbackColor"
              :options="options" 
              size="sm" 
              class="mt-2"
            ></b-form-select>
          </b-col>
          <b-col>
            <b-button
              variant="primary"
              size="sm"
              class="m-1"
              v-addServiceFlow
            >
              增加服务流程
            </b-button>

            <div :class="addServiceFlow.sfbackColor" style="width: 36px; height: 36px; position: relative; top: -33px;"></div>
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

    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="ossInfoTitleCheck">
      留学信息标题不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="ossInfoContentCheck">
      留学信息内容不能为空!
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="ossInfoColorCheck">
      留学信息背景颜色不能为空!
    </b-modal>

    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="serviceFlowBackPicCheck">
      服务流程背景图片不能为空!
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="serviceFlowTitleCheck">
      服务流程标题不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="serviceFlowContentCheck">
      服务流程内容不能为空!
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="serviceFlowBackColorCheck">
      服务流程背景颜色不能为空!
    </b-modal>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  inject: ['reload'], // 引入方法
  name: 'oss-edit',
  props: {
    ossInfo: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      show: false,
      headerBgVariant: 'info',
      bodyBgVariant: 'light',
      footerBgVariant: 'warning',
      options: [
        { value: null, text: '请选择背景颜色' },
        { value: 'bg-primary', text: '蓝色' },
        { value: 'bg-secondary', text: '灰色' },
        { value: 'bg-success', text: '绿色' },
        { value: 'bg-danger', text: '红色'},
        { value: 'bg-warning', text: '黄色' },
        { value: 'bg-info', text: '浅蓝色' },
        { value: 'bg-light', text: '明亮色' },
        { value: 'bg-dark', text: '黑色'},
        { value: 'bg-purple', text: '紫色'},
        { value: 'bg-pink', text: '粉色' },
        { value: 'bg-orange', text: '橙色' },
        { value: 'bg-teal', text: '蓝绿色' },
        { value: 'bg-cyan', text: '青绿色'}
      ],
      oss: [{
        ossTitle: '',
        content: '',
        bacColor: null
      }],
      addOss: {
        ossTitle: '',
        content: '',
        bacColor: null
      },
      ossInfoTitleCheck: false,
      ossInfoContentCheck: false,
      ossInfoColorCheck: false,
      serviceFlow: [{
        sfbackPic: null,
        sftitle: '',
        sfcontent: '',
        sfbackColor: null
      }],
      addServiceFlow: {
        sfbackPic: null,
        sftitle: '',
        sfcontent: '',
        sfbackColor: null
      },
      serviceFlowBackPicCheck: false,
      serviceFlowTitleCheck: false,
      serviceFlowContentCheck: false,
      serviceFlowBackColorCheck: false,
      dataFlag: true
    }
  },
  methods: {
    getOss() {
      axios.get("http://49.235.209.42:8080/overseas_study_service/getoss")
      .then((data) => {
        // console.log(data);
        this.oss = data.data;
        // console.log(this.oss);
      })
    },
    getServiceFlows() {
      axios.get("http://49.235.209.42:8080/overseas_study_service/get_service_flows")
      .then((data) => {
        // console.log(data);
        this.serviceFlow= data.data;
        // console.log(this.oss);
      })
    },
    ossInfoEdit() {
      axios.post("http://49.235.209.42:8080/overseas_study_service/ossinfo_edit",this.oss)
      .then((data) => {
        // console.log(data);
        // console.log(this.oss);
      })
    },
    serviceFlowInfoEdit() {
      axios.post("http://49.235.209.42:8080/overseas_study_service/service_flow_info_edit",this.serviceFlow)
      .then((data) => {
        // console.log(data);
        // console.log(this.oss);
      })
    },
    ossInfoAddCheck() {
      if(this.addOss.ossTitle == '') {
        this.ossInfoTitleCheck = true;
        return false;
      }else if(this.addOss.content == '') {
        this.ossInfoContentCheck = true;
        return false;
      }else if(this.addOss.bacColor == null) {
        this.ossInfoColorCheck = true;
        return false;
      }
      return true;
    },
    serviceFlowAddCheck() {
      if(this.addServiceFlow.sfbackPic == null) {
        this.serviceFlowBackPicCheck = true;
        return false;
      }else if(this.addServiceFlow.sftitle == '') {
        this.serviceFlowTitleCheck= true;
        return false;
      }else if(this.addServiceFlow.sfcontent == '') {
        this.serviceFlowContentCheck= true;
        return false;
      }else if(this.addServiceFlow.sfbackColor == null) {
        this.serviceFlowBackColorCheck = true;
        return false;
      }
      return true;
    },
    confirmChange() {
      setTimeout(() =>{
        this.ossInfoEdit();
      },"10");//0.01秒后执行函数，只执行一次。
      setTimeout(() => {
        this.serviceFlowInfoEdit();
      },'10');//0.01秒后执行函数，只执行一次。
      this.show = false;
      setTimeout(() => {
        this.reload();
      },"60");//0.5秒后执行函数，只执行一次。经测试此等待时间有助于刷新状态
    }
  },
  updated() {
    if(this.dataFlag) {
      this.oss = this.ossInfo.ossShow;
      this.serviceFlow = this.ossInfo.serviceFlowShow;
    }
    this.dataFlag = false;
  },
  directives: {
    'deleteOss': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          axios.delete("http://49.235.209.42:8080/overseas_study_service/delete_oss/" + binding.value)
          .then((data) => {
            // console.log(data);
            if(data.data.result == 'success') {
              // vnode.context.oss.pop(binding.value);
              setTimeout(() => {
                vnode.context.getOss();
              },"10");//0.01秒后执行函数，只执行一次。
            }
          })
        }
      }
    },
    'addOss': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          if(vnode.context.ossInfoAddCheck()) {
            axios.post("http://49.235.209.42:8080/overseas_study_service/add_oss",vnode.context.addOss)
            .then((data) => {
              // console.log(data);
              if(data.data.result == 'success') {
                vnode.context.getOss();
                vnode.context.addOss = {
                  ossTitle: '',
                  content: '',
                  bacColor: null
                }
              }
            })
          }
        }
      }
    },
    'deleteServiceFlow': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          axios.delete("http://49.235.209.42:8080/overseas_study_service/delete_service_flow/" + binding.value)
          .then((data) => {
            // console.log(data);
            if(data.data.result == 'success') {
              // vnode.context.oss.pop(binding.value);
              setTimeout(() => {
                vnode.context.getServiceFlows();
              },"10");//0.01秒后执行函数，只执行一次。
            }
          })
        }
      }
    },
    'addServiceFlow': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          if(vnode.context.serviceFlowAddCheck()) {
            var file = vnode.context.addServiceFlow.sfbackPic;
            // console.log("背景图片:",file)
            vnode.context.addServiceFlow.sfbackPic = ''
            let formData = new FormData();  
            formData.append("picture",file);
            axios.post("http://49.235.209.42:8080/overseas_study_service/background_pic/" + vnode.context.addServiceFlow.sftitle,formData)
            .then((data) => {
              // console.log(data);
              if(data.data.result == "success") {
                vnode.context.getServiceFlows();
              }
            })
            axios.post("http://49.235.209.42:8080/overseas_study_service/add_service_flow",vnode.context.addServiceFlow)
            .then((data) => {
              // console.log(data);
              if(data.data.result == 'success') {
                vnode.context.getServiceFlows();
                vnode.context.addServiceFlow = {
                  sfbackPic: null,
                  sftitle: '',
                  sfcontent: '',
                  sfbackColor: null
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
