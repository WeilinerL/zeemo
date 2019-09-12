<template>
  <div class="index-edit">
    <b-button @click="show=true" variant="success">编辑此页</b-button>

    <b-modal
      v-model="show"
      title="编辑主页"
      size="xl"
      :header-bg-variant="headerBgVariant"
      :body-bg-variant="bodyBgVariant"
      :footer-bg-variant="footerBgVariant"
    >
      <b-container fluid class="text-center">
        <b-row>
          <h5>第一部分 欢迎图片及背景文字</h5>
        </b-row>
        <b-row class="mb-1">
          <b-col cols="3">背景图片</b-col>
          <b-col>谚语标题</b-col>
          <b-col>谚语作者</b-col>
        </b-row>
        <b-row class="mb-1 text-center">
          <b-col cols="3">
            <img :src="welcomeEdit[0].backPic" alt="背景图片" style="width: 36px; height: 36px;">
          </b-col>
          <b-col>
            {{welcomeEdit[0].proverbTitle}}
          </b-col>
          <b-col>
            {{welcomeEdit[0].proverbContent}}
          </b-col>
        </b-row>
        <b-row class="mb-1">
          <b-col cols="3">
            <h6 style="color: #f86c6b;">*1.背景图片最好选择1200*300 以长方形为宜 否则会变得扭曲 2.图片大小不能超过1MB*</h6>
            <b-form-file v-model="editInfo.file" accept="image/jpeg, image/png" class="mt-3" plain></b-form-file>
          </b-col>
          <b-col>
            <b-form-textarea
              id="textarea-small"
              size="sm"
              placeholder="点击编辑谚语标题"
              v-model="editInfo.proverbTitle"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-textarea
              id="textarea-small"
              size="sm"
              placeholder="点击编辑谚语作者"
              v-model="editInfo.proverbContent"
            ></b-form-textarea>
          </b-col>
        </b-row>

        <b-row>
          <h5>第二部分 团队特点介绍</h5>
        </b-row>
        <h6 style="color: #f86c6b;">*1.若要更改团队特点标题 请先删除该特点 再增加相应的特点 2.删除和增加特点点击之后就生效 若视图未改变 请刷新*</h6>
        <b-row class="mb-1">
          <b-col cols="3">特点标题</b-col>
          <b-col>内容</b-col>
          <b-col>操作</b-col>
        </b-row>
        <hr>
        <div v-for="feature in teamFeatureEdit">
          <b-row class="mb-1 text-center">
            <b-col cols="3">
              {{feature.strName}}
            </b-col>
            <b-col>
              <pre>{{feature.strContent}}</pre>
            </b-col>
            <b-col>
              <b-button
                variant="primary"
                size="sm"
                class="m-1"
                :key="feature.strName" 
                v-deleteFeature="feature.strContent"
              >
                删除
              </b-button>
            </b-col>
          </b-row>
          <hr>
          <b-row class="mb-1">
            <b-col cols="3"></b-col>
            修改内容:
            <b-col>
              <b-form-textarea
                id="textarea-small"
                size="sm"
                placeholder="点击编辑特点"
                v-model="feature.strContent"
              ></b-form-textarea>
            </b-col>
            <b-col></b-col>
          </b-row>
          <hr>
        </div>

        <b-row>
          <b-col cols="3">
            <b-form-textarea
              id="textarea-small"
              size="sm"
              placeholder="点击编辑特点名称"
              v-model="addFeature.strName"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-textarea
                id="textarea-small"
                size="sm"
                placeholder="点击编辑特点"
                v-model="addFeature.strContent"
              ></b-form-textarea>
          </b-col>
          <b-col>
            <b-button
              variant="primary"
              size="sm"
              class="m-1"
              v-addFeature
            >
              增加特点
            </b-button>
          </b-col>
        </b-row>

        <b-row>
          <h5>第三部分 联系我们</h5>
        </b-row>
        <b-row class="mb-1">
          <b-col>电话</b-col>
          <b-col>网址</b-col>
          <b-col>地址</b-col>
          <b-col>微信公众号</b-col>
          <b-col>二维码</b-col>
        </b-row>
        <div v-for="contact in contactUsEdit">
          <b-row class="mb-1 text-center">
            <b-col>
              {{contact.tel}}
            </b-col>
            <b-col>
              {{contact.net}}
            </b-col>
            <b-col>
              {{contact.address}}
            </b-col>
            <b-col>
              {{contact.weChat}}
            </b-col>
            <b-col>
              <img :src="codeSourceEdit" alt="二维码" style="width: 36px; height: 36px;">
            </b-col>
          </b-row>
          <b-row class="mb-1">
            <b-col>
              <b-form-textarea
                id="textarea-small"
                size="sm"
                placeholder="点击编辑联系电话"
                v-model="contact.tel"
              ></b-form-textarea>
            </b-col>
            <b-col>
              <b-form-textarea
                id="textarea-small"
                size="sm"
                placeholder="点击编辑网址"
                v-model="contact.net"
              ></b-form-textarea>
            </b-col>
            <b-col>
              <b-form-textarea
                id="textarea-small"
                size="sm"
                placeholder="点击编辑公司地址"
                v-model="contact.address"
                ></b-form-textarea>
              </b-col>
            <b-col>
              <b-form-textarea
                id="textarea-small"
                size="sm"
                placeholder="点击编辑微信公众号"
                v-model="contact.weChat"
              ></b-form-textarea>
            </b-col>
            <b-col>
              <h6 style="color: #f86c6b;">*图片大小不能超过1MB*</h6>
              <b-form-file v-model="QRCode" accept="image/jpeg, image/png" class="mt-3" plain></b-form-file>
            </b-col>
          </b-row>
        </div>
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
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="teamFeatureTitleCheck">
      特点名不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="teamFeatureContentCheck">
      特点内容不能为空!
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  inject: ['reload'], // 引入方法
  props: {
    indexInfo: {
      type: Object,
      required: true
    }
  },
  name: 'index-edit',
  data () {
    return {
      show: false,
      headerBgVariant: 'info',
      bodyBgVariant: 'light',
      footerBgVariant: 'warning',
      teamFeatureTitleCheck: false,
      teamFeatureContentCheck: false,
      teamFeatureEdit: [],
      welcomeEdit: [{
        backPic: '',
        proverbTitle: '',
        proverbContent: '',
      }],
      contactUsEdit: [],
      editInfo: {
        file: null,
        proverbTitle: '',
        proverbContent: '',
      },
      addFeature: {
        intIndexId: '',
        strType: '',
        strName: '',
        strContent: ''
      },
      QRCode: null,
      codeSourceEdit: '',
      dataFlag: true
    }
  },
  methods: {
    getTeamFeature() {
      axios.get("http://49.235.209.42:8080/index/team_feature")
      .then((data) => {
        // console.log(data);
        this.teamFeatureEdit = data.data;
      })
    },
    picUpload() {
      //图片上传
      if(this.editInfo.file != null) {
        let formData = new FormData(); 
        formData.append("picture",this.editInfo.file);
        // const instance=axios.create({
        //   withCredentials: true
        //  });
        axios.post("http://49.235.209.42:8080/index/background_pic",formData)
        .then((data) => {
          // console.log(data);
        })
      }
    },
    qrPicUpload() {
      //图片上传
      if(this.QRCode != null) {
        let formData = new FormData();  
        formData.append("qrcode",this.QRCode);
        // const instance=axios.create({
        //   withCredentials: true
        //  });
        axios.post("http://49.235.209.42:8080/index/qr_code",formData)
        .then((data) => {
          // console.log(data);
        })
      }
    },
    proverbTitle() {
      //谚语标题上传
      let data = {
        'proverbTitle': this.editInfo.proverbTitle
      }
      if(this.editInfo.proverbTitle != '') {
        axios.get("http://49.235.209.42:8080/index/proverb_title",{
          params: data
        })
        .then((data) => {
          // console.log(data);
        })
      }
    },
    proverbContent() {
      //谚语内容上传
      let data = {
        'proverbContent': this.editInfo.proverbContent
      }
      if(this.editInfo.proverbContent != '') {
        axios.get("http://49.235.209.42:8080/index/proverb_content",{
          params: data
        })
        .then((data) => {
          // console.log(data);
        })
      }
    },
    teamFeatureAddCheck() {
      if(this.addFeature.strName == '') {
        this.teamFeatureTitleCheck = true;
        return false;
      }else if(this.addFeature.strContent == '') {
        this.teamFeatureContentCheck = true;
        return false;
      }
      return true;
    },
    teamFeatureUpload() {
      //团队特点上传
      axios.post("http://49.235.209.42:8080/index/team_feature_edit",this.teamFeatureEdit)
      .then((data) => {
        // console.log(data);
      })
    },
    contactEdit() {
      //联系方式编辑
      // console.log(this.contactUsEdit);
      axios.post("http://49.235.209.42:8080/index/contact_us_edit",this.contactUsEdit[0])
      .then((data) => {
        // console.log(data);
      })
    },
    confirmChange() {
      setTimeout(() => {
        this.picUpload();
        this.qrPicUpload();
      },'10');//0.01秒后执行函数，只执行一次。
      setTimeout(() => {
        this.proverbTitle();
      },'10');//0.01秒后执行函数，只执行一次。
      setTimeout(() => {
        this.proverbContent();
      },'10');//0.01秒后执行函数，只执行一次。
      setTimeout(() => {
        this.teamFeatureUpload();
      },'10');//0.01秒后执行函数，只执行一次。
      setTimeout(() => {
        this.contactEdit();
      },'10');//0.01秒后执行函数，只执行一次。
      this.show = false;
      setTimeout(() => {
        this.reload();
      },'60');//0.5秒后执行函数，只执行一次。经测试此等待时间有助于刷新状态
    }
  },
  updated() {
    // this.getWelCome();
    // this.getTeamFeature();
    // this.getContactUs();
    // this.getQRCode();
    // console.log(this.indexInfo);
    if(this.dataFlag) {
      this.welcomeEdit = this.indexInfo.welcome;
      this.teamFeatureEdit = this.indexInfo.teamFeature;
      this.contactUsEdit = this.indexInfo.contactUs;
      this.codeSourceEdit = this.indexInfo.codeSource;
    }
    this.dataFlag = false;
  },
  watch: {
    // editInfo: {
    //   handler(newObject,oldObject) {
    //     // console.log(newObject);
    //     this.welcomeEditFlag = true;
    //   },
    //   deep: true, //深度监听对象内部变化
    //   // immediate: true
    // }
  },
  directives: {
    'deleteFeature': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          axios.delete("http://49.235.209.42:8080/index/delete_feature/" + binding.value)
          .then((data) => {
            if(data.data.result == "success") {
              // vnode.context.teamFeature.pop(binding.value);
              setTimeout(() => {
                vnode.context.getTeamFeature();
              },'10');//0.01秒后执行函数，只执行一次。
            }
          })
        }
      }
    },
    'addFeature': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          if(vnode.context.teamFeatureAddCheck()) {
            axios.post("http://49.235.209.42:8080/index/add_feature",vnode.context.addFeature)
            .then((data) => {
              // console.log(data);
              if(data.data.result == 'success') {
                vnode.context.getTeamFeature();
                vnode.context.addFeature = {
                  intIndexId: '',
                  strType: '',
                  strName: '',
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
