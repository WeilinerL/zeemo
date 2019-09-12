<template>
  <div class="consultants animated fadeIn">

    <div class="container-fluid" style="margin-top: 66px;">
      <b-row>
        <b-card class="text-center" style="max-width: 300px;margin: 0 auto; border: none;">
          <div>
            <div style="width: 16px; height: 16px; background-color: #17a2b8; display: inline-block;"></div>
            <h4 style="display: inline-block;margin: 3px;">
              顾问介绍
            </h4>
            <p>Introduction Of Consultants</p>
          </div>
        </b-card>
      </b-row> 

      <div class="consultants-edit text-right d-md-down-none" v-if="edit_consultant">
        <consultants-edit
          v-bind:consultantEdits="consultantEdit"
        ></consultants-edit>
      </div>
    </div>

    <div class="container-fluid">
      <b-row class="justify-content-center">
        <b-col sm="6" lg="12" class="mb-5" v-for="consultantObject in consultantShow">
          <b-card 
            no-body 
            class="overflow-hidden" 
            style="max-width: 860px; margin: 0 auto;"
            >
            <b-row no-gutters>
              <b-col md="6" class="align-self-center">
                <b-card-img :src="consultantObject.strPicture"></b-card-img>
              </b-col>
              <b-col md="6" style="background-image: url('img/lace.png'); background-size: 100% 100%;">
                <b-card-body
                  :title="consultantObject.strName"
                  style="margin-top: 66px; margin-bottom: 66px;overflow-y: scroll; max-height: 276px; max-width: 420px;"
                >
                  <h5>
                    <pre>{{consultantObject.strTitle}}</pre>
                  </h5>
                  <b-card-text>
                    {{consultantObject.strIntroduction}}
                  </b-card-text>
                </b-card-body>  
              </b-col>
            </b-row>
          </b-card>
        </b-col>

      </b-row>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import ConsultantsEdit from './ConsultantsEdit'

export default {
  name: 'consultants',
  props: {
    adminInfo: {
      type: String,
      required: true
    }
  },
  components: {
    ConsultantsEdit
  },
  data () {
    return {
      edit_consultant: false,
      consultantShow: [],
      consultantEdit: []
    }
  },
  methods: {
    getConsultants() {
      axios.get("http://49.235.209.42:8080/consultant/get_consultants")
      .then((data) => {
        // console.log(data);
        this.consultantShow = data.data;
        this.consultantEdit = this.consultantShow;
      })
    }
  },
  created() {
    this.getConsultants();
    if(this.adminInfo != '') {
      this.edit_consultant = true;
    }else {
      this.edit_consultant = false;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
