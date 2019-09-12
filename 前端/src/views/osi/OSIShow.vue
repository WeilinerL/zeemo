<template>
  <div class="osi-show animated fadeIn">
    <div class="container-fluid">
      <b-row>
        <el-button type="success" icon="el-icon-arrow-left" @click="back" style="margin-top: 36px; margin-left: 36px;">返回</el-button>
      </b-row>
      
      <b-row>
        <b-card class="text-center" style="max-width: 300px;margin: 0 auto; border: none;">
          <div>
            <h4 style="display: inline-block;margin: 16px;">
              {{article.strTitle}}
            </h4>
            <h5>{{article.strAuthor}}</h5>
            <p>{{article.strTime}}</p>
          </div>
        </b-card>
      </b-row> 
    </div>
    
    <div class="container-fluid">
      <b-row class="justify-content-center">
        <b-col cols="12">
          {{article.strContent}}
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'osi-show',
  data () {
    return {
     article: {
      intOsiId: '',
      strType: '',
      strTitle: '',
      strAuthor: '',
      strTime:'',
      strContent: ''
     }
    }
  },
  methods: {
    getArticle(title) {
      axios.get("http://49.235.209.42:8080/oversea_study_info/article/" + title)
      .then((data) => {
        this.article = data.data;
      })
    },
    back() {
      //this.$router.go() safari 不兼容
      this.$router.push({path: "/oversea_study_info"});
    }
  },
  created() {
    this.getArticle(this.$route.params.articleTitle);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.osi-show{
  background-color: #fff;
  height: 720px;
  overflow: scroll;
}

</style>
