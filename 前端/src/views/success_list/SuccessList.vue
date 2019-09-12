<template>
  <div class="success-list animated fadeIn">
    <div class="container-fluid" style="margin-top: 66px;">
      <b-row>
        <b-card class="text-center" style="max-width: 300px;margin: 0 auto; border: none;">
          <div>
            <div style="width: 16px; height: 16px; background-color: #17a2b8; display: inline-block;"></div>
            <h4 style="display: inline-block;margin: 3px;">
              成功案例
            </h4>
            <p>Success List</p>
          </div>
        </b-card>
      </b-row> 

      <div class="success-list-edit text-right d-md-down-none" v-if="edit_success_list">
        <success-list-edit
          v-bind:successListEdit="successListEdit"
        ></success-list-edit>
      </div>
    </div>

    <div class="container-fluid">
      <b-row class="justify-content-center">
        <b-col sm="6" lg="4" class="mb-5" v-for="successListObject in successListShow">
          <b-card
            :img-src="successListObject.strPicture"
            img-alt="成功案例"
            img-top
            tag="article"
            style="max-width: 20rem; margin: 0 auto;"
            class="mb-2"
          >
            <b-card-text>
              {{successListObject.strContent}}
            </b-card-text>
          </b-card>
        </b-col>

      </b-row>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
import SuccessListEdit from './SuccessListEdit'

export default {
  name: 'success-list',
  props: {
    adminInfo: {
      type: String,
      required: true
    }
  },
  components: {
    SuccessListEdit
  },
  data () {
    return {
      edit_success_list: false,
      successListShow: [],
      successListEdit: []
    }
  },
  methods: {
    getSuccessList() {
      axios.get("http://49.235.209.42:8080/success_list/get_all_success_list")
      .then((data) => {
        // console.log(data);
        this.successListShow = data.data;
        this.successListEdit = this.successListShow;
      })
    },
  },
  created() {
    this.getSuccessList();
    if(this.adminInfo != '') {
      this.edit_success_list = true;
    }else {
      this.edit_success_list = false;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
