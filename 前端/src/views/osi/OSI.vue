<template>
  <div class="osi animated fadeIn">
    <div class="container-fluid" style="margin-top: 66px;">
      <b-row>
        <b-card class="text-center" style="max-width: 300px;margin: 0 auto; border: none;">
          <div>
            <div style="width: 16px; height: 16px; background-color: #17a2b8; display: inline-block;"></div>
            <h4 style="display: inline-block;margin: 3px;">
              留学信息
            </h4>
            <p>Oversea Stuty Information</p>
          </div>
        </b-card>
      </b-row> 

      <div class="osi-edit text-right d-md-down-none" v-if="edit_osi">
        <osi-edit
          v-bind:osIEdit="osIEdit"
        ></osi-edit>
      </div>

      <div class="container-fluid">
        <b-row class="justify-content-center">
          <b-col sm="6" lg="4" v-for="osi in osInfo">
            <b-card
              :header="osi.type"
              header-tag="header"
              style="height: 300px; overflow: scroll;"
            >
              <b-card-text>
                <b-list-group>
                  <b-list-group-item v-for="article in osi.articleList" v-openArticle="osi.type + '/' + article" v-if="article != ''">
                    {{article}}
                  </b-list-group-item>
                </b-list-group>
              </b-card-text>
            </b-card>
          </b-col>
        </b-row>
      </div>

      <router-view></router-view>

    </div>
  </div>
</template>

<script>
import axios from 'axios'
import OsiEdit from './OsiEdit'

export default {
  name: 'osi',
  props: {
    adminInfo: {
      type: String,
      required: true
    }
  },
  components: {
    OsiEdit
  },
  data () {
    return {
      edit_osi: false,
      osInfo: [],
      osIEdit: []
    }
  },
  methods: {
    getArticles() {
      axios.get("http://49.235.209.42:8080/oversea_study_info/articles/")
      .then((data) => {
        // console.log(data);
        this.osInfo = data.data;
        this.osIEdit = this.osInfo;
      })
    }
  },
  created() {
    this.getArticles();
    if(this.adminInfo != '') {
      this.edit_osi = true;
    }else {
      this.edit_osi = false;
    }
  },
  directives: {
    'openArticle': {
      bind(el,binding,vnode) {
        el.onmouseover = function() {
          el.style.backgroundColor = "#20a8d8";
          el.style.cursor = "pointer";
        }
        el.onmouseleave = function() {
          el.style.color = "#000";
          el.style.backgroundColor = "#fff";
        }
        el.onclick = function() {
          // console.log(binding.value);
          vnode.context.$router.push({ 
            name: 'article', 
            params: { 
              articleTitle: binding.value
            }
          });
        }
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
