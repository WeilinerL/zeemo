<template>
  <div class="oss animated fadeIn">
    <div class="container" style="margin-top: 76px; margin-bottom: 76px;">
      <b-row style="margin: 0 auto;" class="justify-content-center">
        <b-col sm="6" lg="4" class="justify-content-center text-center" style="border: none;"
        v-for="(ossObject,index) in ossShow"
        :key="index"
        >
          <b-card :class="ossObject.bacColor">
            <i class="icon-pin font-2xl" style="float: left;"></i>
            <b-card-title>{{ossObject.ossTitle}}</b-card-title>
            <b-card-text>{{ossObject.content}}</b-card-text>
          </b-card>
        </b-col>
      </b-row>
      <div class="oss-edit text-right d-md-down-none" v-if="edit_oss">
        <oss-edit 
          v-bind:ossInfo="ossInfo"
        ></oss-edit>
      </div> 
      <b-row>
        <b-card class="text-center" style="max-width: 300px;margin: 0 auto; border: none;">
          <div>
            <div style="width: 16px; height: 16px; background-color: #17a2b8; display: inline-block;"></div>
            <h4 style="display: inline-block;margin: 3px;">
              服务流程
            </h4>
            <p>Service Flow</p>
          </div>
        </b-card>
      </b-row>
      <b-container>
        <el-timeline style="max-width: 700px; margin: 0 auto;"  class="font-xl"
        >
          <el-timeline-item hide-timestamp color="#4dbd74"
          v-for="(SFObject,index) in serviceFlowShow"
          :key="index"
          >
            
            <b-card style="max-width: 600px; overflow: scroll;margin: 0 auto;" :class="SFObject.sfbackColor">
              <i class="icon-pin font-2xl" style="float: left;"></i>
              <b-card-img class="d-block" :src="SFObject.sfbackPic" style="width: 76px;height: 76px; border: 1px solid green; border-radius: 50em;
              margin: 0 auto; position: relative; left: -12px;"></b-card-img>
              <b-card-title class="text-center m-0">
                {{SFObject.sftitle}}
              </b-card-title>
              <el-divider content-position="center">{{index + 1}}</el-divider>
              <b-card-text style="line-height: 1.5;">
                <pre>{{SFObject.sfcontent}}</pre>
              </b-card-text>
            </b-card>
          </el-timeline-item>

        </el-timeline>
      </b-container>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import OssEdit from './OssEdit'

export default {
  name: 'oss',
  props: {
    adminInfo: {
      type: String,
      required: true
    }
  },
  components: {
    OssEdit
  },
  data () {
    return {
      edit_oss: false,
      ossShow: [],
      serviceFlowShow: [],
      ossInfo: {}
    }
  },
  methods: {
    getOss() {
      axios.get("http://49.235.209.42:8080/overseas_study_service/getoss")
      .then((data) => {
        // console.log(data);
        this.ossShow = data.data;
        this.ossInfo.ossShow = this.ossShow;
        // console.log(this.oss);
      })
    },
    getServiceFlows() {
      axios.get("http://49.235.209.42:8080/overseas_study_service/get_service_flows")
      .then((data) => {
        // console.log(data);
        this.serviceFlowShow = data.data;
        this.ossInfo.serviceFlowShow = this.serviceFlowShow;
        // console.log(this.oss);
      })
    }
  },
  created() {
    this.getOss();
    this.getServiceFlows();
    if(this.adminInfo != '') {
      this.edit_oss = true;
    }else {
      this.edit_oss = false;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
