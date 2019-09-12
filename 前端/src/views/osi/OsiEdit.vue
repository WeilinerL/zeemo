<template>
  <div class="osi-edit">
    <b-button @click="show=true" variant="success">编辑此页</b-button>

    <b-modal
      v-model="show"
      title="编辑留学信息页面"
      size="xl"
      :header-bg-variant="headerBgVariant"
      :body-bg-variant="bodyBgVariant"
      :footer-bg-variant="footerBgVariant"
    >
      <b-container fluid class="text-center">
        <b-row>
          <h5>第一部分 文章分类</h5>
        </b-row>

        <h6 style="color: #f86c6b;">*删除和增加专题信息点击之后就生效 若视图未改变 请刷新*</h6>

        <b-row class="mb-1">
          <b-col>专题名称</b-col>
          <b-col>操作</b-col>
        </b-row>

        <div v-for="osi in type">
          <hr>
          <b-row class="mb-1">
            <b-col>
              {{osi.type}}
            </b-col>
            <b-col>
              <b-button
                variant="primary"
                size="sm"
                class="m-1"
                :key="osi.type"
                v-deleteType="osi.type"
              >
                删除
              </b-button>
            </b-col>
          </b-row>

        </div>

        <hr>

        <b-row>
          <b-col>
            <b-form-textarea
              v-model="addType.strType"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑专题名称"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-button
              variant="primary"
              size="sm"
              class="m-1"
              v-addType
            >
              增加专题
            </b-button>
          </b-col>
        </b-row>

        <b-row>
          <h5>第二部分 添加文章</h5>
        </b-row>

        <b-row class="mb-1">
          <b-col>分类</b-col>
          <b-col>标题</b-col>
          <b-col>操作</b-col>
        </b-row>

        <hr>

        <div v-for="osi in type">
          <b-row class="mb-1" v-for="article in osi.articleList" v-if="article != ''">
            <b-col>
              {{osi.type}}
            </b-col>
            <b-col>
              {{article}}
            </b-col>
            <b-col>
              <b-button
                variant="primary"
                size="sm"
                class="m-1"
                :key="article.type"
                v-deleteArticle="osi.type + '/' + article"
              >
                删除
              </b-button>
            </b-col>
          </b-row>

        </div>

        <hr>

        <b-row class="align-items-center">
          <b-col>
            <b-form-select 
              v-model="addTitle.strType"
              :options="options" 
              size="sm" 
              class="mt-2"
            ></b-form-select>
          </b-col>
          <b-col>
            <b-form-textarea
              v-model="addTitle.strTitle"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑文章标题"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-textarea
              v-model="addTitle.strAuthor"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑文章作者"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-form-textarea
              v-model="addTitle.strContent"
              id="textarea-small"
              size="sm"
              placeholder="点击编辑文章内容"
              style="height: 200px; width: 300px;"
            ></b-form-textarea>
          </b-col>
          <b-col>
            <b-button
              variant="primary"
              size="sm"
              class="m-1"
              v-addArticle
            >
              增加文章
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

    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="typeNameInfoCheck">
      专题名称不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="articleTypeInfoCheck">
      文章类别不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="articleTitleInfoCheck">
      文章名称不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="articleAuthorInfoCheck">
      文章作者不能为空！
    </b-modal>
    <b-modal title="通知" class="modal-danger" :ok-only="true" v-model="articleContentInfoCheck">
      文章内容不能为空！
    </b-modal>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  inject: ['reload'], // 引入方法
  name: 'osi-edit',
  props: {
    osIEdit: {
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
      type: [],
      addType: {
        strType: ''
      },
      addTitle: {
        strType: null,
        strTitle: '',
        strAuthor: '',
        strTime: '',
        strContent: ''
      },
      options: [
        { value: null, text: '请选择文章分类' }
      ],
      typeNameInfoCheck: false,
      articleTypeInfoCheck: false,
      articleTitleInfoCheck: false,
      articleAuthorInfoCheck: false,
      articleContentInfoCheck: false,
      dataFlag: true
    }
  },
  methods: {
    getArticles() {
      axios.get("http://49.235.209.42:8080/oversea_study_info/articles/")
      .then((data) => {
        // console.log(data);
        this.type = data.data;
        this.options = [
          { value: null, text: '请选择文章分类' }
        ];
        for(var i = 0; i < this.type.length; i ++) {
          var option = {};
          option.value = this.type[i].type;
          option.text = this.type[i].type;
          this.options.push(option);
        }
      })
    },
    typeAddCheck() {
      if(this.addType.strType == '') {
        this.typeNameInfoCheck = true;
        return false;
      }
      return true;
    },
    articleAddCheck() {
      if(this.addTitle.strType == null) {
        this.articleTypeInfoCheck = true;
        return false;
      }else if(this.addTitle.strTitle == '') {
        this.articleTitleInfoCheck = true;
        return false;
      } else if(this.addTitle.strAuthor == '') {
        this.articleAuthorInfoCheck = true;
        return false;
      }else if(this.addTitle.strContent == '') {
        this.articleContentInfoCheck = true;
        return false;
      }
      return true;
    },
    confirmChange() {
      this.show = false;
      setTimeout(() => {
        this.reload();
      },"60");//0.5秒后执行函数，只执行一次。经测试此等待时间有助于刷新状态
    }
  },
  updated() {
    if(this.dataFlag) {
      this.type = this.osIEdit;
      for(var i = 0; i < this.type.length; i ++) {
        var option = {};
        option.value = this.type[i].type;
        option.text = this.type[i].type;
        this.options.push(option);
      }
    }
    this.dataFlag = false;
  },
  directives: {
    'deleteType': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          axios.delete("http://49.235.209.42:8080/oversea_study_info/delete_type/" + binding.value)
          .then((data) => {
            // console.log(data);
            if(data.data.result == "success") {
              setTimeout(() => {
                // console.log("删除");
                vnode.context.getArticles();
              },"10");//0.01秒后执行函数，只执行一次。
            }
          })
        }
      }
    },
    'addType': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          if(vnode.context.typeAddCheck()) {
            axios.post("http://49.235.209.42:8080/oversea_study_info/add_type/" + vnode.context.addType.strType)
            .then((data) => {
              // console.log(data);
              if(data.data.result == "success") {
                setTimeout(() => {
                  // console.log("删除");
                  vnode.context.getArticles();
                  vnode.context.addType = {
                    strType: ''
                  };
                },"10");//0.01秒后执行函数，只执行一次。
              }
            })
          }
        }
      }
    },
    'deleteArticle': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          axios.delete("http://49.235.209.42:8080/oversea_study_info/delete_article/" + binding.value)
          .then((data) => {
            // console.log(data);
            if(data.data.result == "success") {
              setTimeout(() => {
                // console.log("删除");
                vnode.context.getArticles();
              },"10");//0.01秒后执行函数，只执行一次。
            }
          })
        }
      }
    },
    'addArticle': {
      bind(el,binding,vnode) {
        el.onclick = function() {
          if(vnode.context.articleAddCheck()) {
            axios.post("http://49.235.209.42:8080/oversea_study_info/add_article",vnode.context.addTitle)
            .then((data) => {
              // console.log(data);
              if(data.data.result == "success") {
                setTimeout(() => {
                  // console.log("删除");
                  vnode.context.getArticles();
                  vnode.context.addTitle = {
                    strType: null,
                    strTitle: '',
                    strAuthor: '',
                    strTime: '',
                    strContent: ''
                  };
                },"10");//0.01秒后执行函数，只执行一次。
              }
            })
          }
        }
      }
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
