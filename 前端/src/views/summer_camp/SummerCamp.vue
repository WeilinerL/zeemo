<template>
  <div class="summer-camp animated fadeIn">
    <div class="container-fluid" style=" margin-top: 56px; color: #f86c6b;">
      <b-row class="justify-content-center text-center">
        <b-col lg="9">
          <b-card
            :title="mainObjectShow.strContent"
            :img-src="mainObjectShow.strImg"
            img-alt="Image"
            img-top
            tag="article"
            img-height="210px"
            style=" object-fit: cover;"
            class="mb-2"
          >
          </b-card>
          <i class="icon-arrow-down font-2xl"></i>
        </b-col>
      </b-row>
    </div>

    <div class="success-list-edit text-right d-md-down-none mr-3" v-if="edit_summer_camp">
      <summer-camp-edit
        v-bind:mainObjectShow="mainObjectShow"
        v-bind:advantagesShow="advantagesShow"
      ></summer-camp-edit>
    </div>

    <div class="container-fluid mt-3 mb-3">
      <b-row class="justify-content-center text-center">
        <b-col sm="6" lg="4" v-for="advantage in advantagesShow"> 
          <b-card
              :header="advantage.strTitle"
              header-tag="header"
              :img-src="advantage.strImg"
              img-alt="Image"
              img-top
              style=""
            >
              <b-card-text>
                {{advantage.strContent}}
              </b-card-text>
          </b-card>
        </b-col>

      </b-row>
    </div>
  </div>
</template>

<script>
import axios from 'axios'  
import SummerCampEdit from './SummerCampEdit'

export default {
  name: 'summer-camp',
  props: {
    adminInfo: {
      type: String,
      required: true
    }
  },
  components: {
    SummerCampEdit
  },
  data () {
    return {
      edit_summer_camp: false,
      mainObjectShow: {},
      advantagesShow: []
    }
  },
  methods: {
    getMainObject() {
      axios.get("http://49.235.209.42:8080/summer_camp/welcome")
      .then((data) => {
        // console.log(data);
        this.mainObjectShow = data.data;
      })
    },
    getAdvantages() {
      axios.get("http://49.235.209.42:8080/summer_camp/advantages")
      .then((data) => {
        // console.log(data);
        this.advantagesShow = data.data;
      })
    }
  },
  created() {
    this.getMainObject();
    this.getAdvantages();
    if(this.adminInfo != '') {
      this.edit_summer_camp = true;
    }else {
      this.edit_summer_camp = false;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
