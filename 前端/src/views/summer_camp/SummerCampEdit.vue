<template>
  <div class="summer-camp-edit">
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
          <h5>第一部分 主要图片及文字展示</h5>
        </b-row>

        <b-row class="mb-1">
          <b-col>第一幅大图</b-col>
          <b-col>图片底部文字</b-col>
        </b-row>

        <div>
          <hr>
          <b-row class="mb-1">
            <b-col>
              <img :src="mainObject.strImg" alt="大图图片" style="width: 36px; height: 36px;">
            </b-col>
            <b-col>
              {{mainObject.strContent}}
            </b-col>
          </b-row>

          <hr>

          <b-row class="mb-1">
            <b-col>
              <h6 style="color: #f86c6b;">*1.背景图片最好选择1280*600 以长方形为宜 否则会变得扭曲 2.图片大小不能超过1MB*</h6>
              <b-form-file 
              v-model="mainObjectEdit.strImg"
              accept="image/jpeg, image/png" class="mt-3" plain></b-form-file>
            </b-col>
            <b-col>
              <b-form-textarea
                v-model="mainObjectEdit.strContent"
                id="textarea-small"
                size="sm"
                placeholder="点击编辑图片底部文字"
              ></b-form-textarea>
            </b-col>
          </b-row>
        </div>

        <hr>

        <b-row>
          <h5>第二部分 选择优点展示</h5>
        </b-row>

        <h6 style="color: #f86c6b;">*删除和增加选择优点点击之后就生效 若视图未改变 请刷新*</h6>

        <b-row class="mb-1">
          <b-col>优点图片</b-col>
          <b-col>优点名称</b-col>
          <b-col>优点内容</b-col>
          <b-col>操作</b-col>
        </b-row>

        <div v-for="advantage in advantages">
          <hr>
          <b-row class="mb-1">
            <b-col>
              <img :src="advantage.strImg" alt="案例图片" style="width: 36px; height: 36px;">
            </b-col>
            <b-col>
              {{advantage.strTitle}}
            </b-col>
            <b-col>
              {{advantage.strContent}}
            </b-col>
            <b-col>
              <b-button
                variant="primary"
                size="sm"
                class="m-1"
                :key="advantage.strTtile"
                v-deleteAdvantage="advantage.strTitle"
              >
                删除
              </b-button>
            </b-col>
          </b-row>

          <hr>

          <b-row>
            <b-col>
             <h6 style="color: #f86c6b;">*1.若要更换图片请直接删除该优点再重新添加*</h6>
            </b-col>
            <b-col>
              <b-form-textarea
                v-model="advantage.strTitle"
                id="textarea-small"
                size="sm"
                placeholder="点击编辑优点名称"
              ></b-form-textarea>
            </b-col>
            <b-col>
              <b-form-textarea
                v-model="advantage.strContent"
                id="textarea-small"
                size="sm"
                placeholder="点击编辑优点简介"
              ></b-form-textarea>
            </b-col>
            <b-col></b-col>
          </b-row>
        </div>

        <hr>

        <b-row>
          <b-col>
            <h6 style="color: #f86c6b;">*图片大小不能超过10MB*</h6>
              <b-form-file  
              v-model="addAdvantage.strPicture"
              accept="image/jpeg, image/png" 
              class="mt-3" 
              plain></b-form-file>
          </b-col>
          <b-col>
            <b-form-textarea
              v-model="addAdvantage.strTitle"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑优点名称"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-textarea
              v-model="addAdvantage.strContent"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑优点简介"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-button
              variant="primary"
              size="sm"
              class="m-1"
              v-addAdvantage
            >
              增加优点
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

    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="advantageInfoPicCheck">
      优点图片不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="advantageInfoTitleCheck">
      优点名称不能为空!
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="advantageInfoContentCheck">
      优点简介不能为空!
    </b-modal>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  inject: ['reload'], // 引入方法
  name: 'summer-camp-edit',
  props: {
    mainObjectShow: {
      type: Object,
      required: true
    },
    advantagesShow: {
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
      mainObject: {},
      mainObjectEdit: {
        strImg: null,
        strContent: ''
      },
      advantages: [],
      addAdvantage: {
        strPicture: null,
        strTitle: '',
        strContent: ''
      },
      advantageInfoPicCheck: false,
      advantageInfoTitleCheck: false,
      advantageInfoContentCheck: false,
      dataFlag: true
    }
  },
  methods: {
    getMainObject() {
      axios.get("http://49.235.209.42:8080/summer_camp/welcome")
      .then((data) => {
        // console.log(data);
        this.mainObject = data.data;
      })
    },
    getAdvantages() {
      axios.get("http://49.235.209.42:8080/summer_camp/advantages")
      .then((data) => {
        // console.log(data);
        this.advantages = data.data;
      })
    },
    mainObjectImgEdit() {
      if(this.mainObjectEdit.strImg != null) {
        let formData = new FormData();  
            formData.append("picture",this.mainObjectEdit.strImg);
        axios.post("http://49.235.209.42:8080/summer_camp/mainObjectImgEdit/" + this.mainObject.strTitle,formData)
        .then((data) => {
          // console.log(data);
        })
      }
    },
    mainObjectContentEdit() {
      if(this.mainObjectEdit.strContent != '') {
        axios.post("http://49.235.209.42:8080/summer_camp/mainObjectContentEdit/" + this.mainObject.strTitle + "/" + this.mainObjectEdit.strContent)
        .then((data) => {
          // console.log(data);
        })
      }
    },
    advantageEdit() {
      axios.post("http://49.235.209.42:8080/summer_camp/advantageEdit/",this.advantages)
      .then((data) => {
        // console.log(data);
      })
    },
    advantageAddCheck() {
      if(this.addAdvantage.strPicture == null) {
        this.advantageInfoPicCheck = true;
        return false;
      }else if(this.addAdvantage.strTitle == '') {
        this.advantageInfoTitleCheck= true;
        return false;
      }else if(this.addAdvantage.strContent == '') {
        this.advantageInfoContentCheck= true;
        return false;
      }
      return true;
    },
    confirmChange() {
      setTimeout(() => {
        this.mainObjectImgEdit();
      },"10");//0.01秒后执行函数，只执行一次。
      setTimeout(() => {
        this.mainObjectContentEdit();
      },"10");//0.01秒后执行函数，只执行一次。
      setTimeout(() => {
        this.advantageEdit();
      },"10");//0.01秒后执行函数，只执行一次。
      this.show = false;
      setTimeout(() => {
        this.reload();
      },"60");//0.5秒后执行函数，只执行一次。经测试此等待时间有助于刷新状态
    }
  },
  updated() {
    if(this.dataFlag) {
      this.mainObject = this.mainObjectShow;
      this.advantages = this.advantagesShow;
    }
    this.dataFlag = false;
  },
  directives: {
    'deleteAdvantage': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          axios.delete("http://49.235.209.42:8080/summer_camp/delete_summer_camp/" + binding.value)
          .then((data) => {
            // console.log(data);
            if(data.data.result == "success") {
              setTimeout(() => {
                // console.log("删除");
                vnode.context.getAdvantages();
              },"10");//0.01秒后执行函数，只执行一次。
            }
          })
        }
      }
    },
    'addAdvantage': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          if(vnode.context.advantageAddCheck()) {
            let formData = new FormData();  
            formData.append("picture",vnode.context.addAdvantage.strPicture);
            axios.post("http://49.235.209.42:8080/summer_camp/add_advantage/" + vnode.context.addAdvantage.strTitle + "/" + vnode.context.addAdvantage.strContent,formData)
            .then((data) => {
              // console.log(data);
              if(data.data.result == "success") {
                vnode.context.getAdvantages();
                vnode.context.addAdvantage = {
                  strPicture: null,
                  strTitle: '',
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
