<template>
  <div class="index animated fadeIn">
    <b-card
    overlay
    :img-src="welcome[0].backPic"
    img-alt="Card Image"
    img-height="300px;"
    text-variant="white"
    style="object-fit: cover; margin-top: 66px;"
  >
      <b-row class="justify-content-center d-md-down-none" style="margin-top: 96px;font-family: Verdana;">
        <b-col sm="6" md="5">
          <blockquote class="card-blockquote">
            <h3 style="font-weight: bold;">
              {{welcome[0].proverbTitle}}
            </h3>
            <footer class="text-right">
              <div style="display: inline-block;width: 56px;height: 1px;border: 1px solid #fff;vertical-align: middle;border-top: none;">
              </div>
              <cite title="Source Title" style="font-weight: bold;">
                {{welcome[0].proverbContent}}
              </cite>
            </footer>
          </blockquote>
        </b-col>
      </b-row>
      <div class="index-edit text-right" v-if="edit_index">
        <index-edit
        v-bind:indexInfo="indexInfo"
        ></index-edit>
      </div>
    </b-card>
    <div class="container">
      <b-row>
        <b-card class="text-center" style="max-width: 300px;margin: 0 auto; border: none;">
          <div>
            <div style="width: 16px; height: 16px; background-color: #17a2b8; display: inline-block;"></div>
            <h4 style="display: inline-block;margin: 3px;">
              团队优势
            </h4>
            <p>Team Advantage</p>
          </div>
        </b-card>
      </b-row>
      <b-row style="margin: 0 auto;" class="justify-content-center">
        <b-col sm="6" lg="4" class="align-self-center" style="border: none;" v-for="feature in teamFeature">
          <b-card
            class="bg-light"
            header-tag="header"
            :title="feature.strName"
            style="border: 1px solid #c8ced3; margin: 10px 0; background-color: rgba(240,243,245,0.8); height: 126px; overflow: scroll;"
          >
            <b-card-text class="font-xl">
              <pre>{{feature.strContent}}</pre>
            </b-card-text>
          </b-card>
        </b-col>
      </b-row>
      <b-row>
        <b-card class="text-center" style="max-width: 300px;margin: 0 auto; border: none;">
          <div>
            <div style="width: 16px; height: 16px; background-color: #17a2b8; display: inline-block;"></div>
            <h4 style="display: inline-block;margin: 3px;">
              服务介绍
            </h4>
            <p>Service Introduction</p>
          </div>
        </b-card>
      </b-row>
      <b-row style="max-width: 1200px; margin: 0 auto;" class="justify-content-center text-center">
        <b-col sm="6" lg="2" style="width: 185px; height: 185px;">
          <b-link to="/overseas_study_service" style="text-decoration: none;">
            <div>
              <img src="img/passport.png" alt="img1" style="width: 76px;height: 76px; margin-bottom: 8px;border-radius: 50em;background-color: #20a8d8;">
              <h5 class="mb-0">留学服务</h5>
              <p>Overseas Study Service</p>
            </div>
          </b-link>
        </b-col>
        <b-col sm="6" lg="2" style="width: 185px; height: 185px;">
          <b-link to="/introduction_of_consultants" style="text-decoration: none;">
            <div>
              <img src="img/employee.png" alt="img1" style="width: 76px;height: 76px; margin-bottom: 8px;border-radius: 50em;background-color: #f86c6b;">
              <h5 class="mb-0">顾问介绍</h5>
              <p>Introduction of consultants</p>
            </div>
          </b-link>
        </b-col>
        <b-col sm="6" lg="2" style="width: 185px; height: 185px;">
          <b-link to="/success_list" style="text-decoration: none;">
            <div>
              <img src="img/englishclass.png" alt="img1" style="width: 76px;height: 76px; margin-bottom: 8px;border-radius: 50em;background-color: #63c2de;">
              <h5 class="mb-0">成功案例</h5>
              <p>Success List</p>
            </div>
          </b-link>
        </b-col>
        <b-col sm="6" lg="2" style="width: 185px; height: 185px;">
          <b-link to="/oversea_study_info" style="text-decoration: none;">
            <div>
              <img src="img/liuxuexinxi.png" alt="img1" style="width: 76px;height: 76px; margin-bottom: 8px;border-radius: 50em;background-color: #20c997;">
              <h5 class="mb-0">留学信息</h5>
              <p>Oversea Study Information</p>
            </div>
          </b-link>
        </b-col>
        <b-col sm="6" lg="2" style="width: 185px; height: 185px;">
          <b-link to="/summer_camp" style="text-decoration: none;">
            <div>
              <img src="img/summercamp.png" alt="img1" style="width: 76px;height: 76px; margin-bottom: 8px;border-radius: 50em;background-color: #4dbd74;">
              <h5 class="mb-0">夏令营夏校</h5>
              <p>Summer Camp Summer School</p>
            </div>
          </b-link>
        </b-col>
      </b-row>
      <b-row>
        <b-card class="text-center" style="max-width: 300px;margin: 0 auto; border: none;">
          <div>
            <div style="width: 16px; height: 16px; background-color: #17a2b8; display: inline-block;"></div>
            <h4 style="display: inline-block;margin: 3px;">
              联系我们
            </h4>
            <p>Contact Us</p>
          </div>
        </b-card>
      </b-row>
      <b-row style="max-width: 1200px; margin: 0 auto;" class="justify-content-center">
        <b-col sm="6" lg="2" class="align-self-center" v-for="contact in contactUs">
          <b-card no-body style="border: none;">
            <b-card-body style="padding: 0;">
              <h6>电话: {{contact.tel}}</h6>
              <h6>网站: {{contact.net}}</h6>
              <h6>公司地址: {{contact.address}}</h6>
              <h6>微信公众号: {{contact.weChat}}</h6>
            </b-card-body>
          </b-card>
        </b-col>
        <b-col sm="6" lg="3" class="align-self-center
         text-center" style="padding: 0;">
          <b-card no-body style="border: none;">
            <b-card-body style="padding: 0;">
              <img src="img/logo-brand.png" alt="img-logo" style="width: 176px;height: 176px; margin-bottom: 8px;">
            </b-card-body>
          </b-card>
        </b-col>
        <b-col sm="6" lg="2" class="text-center" style="padding: 0;">
          <b-card no-body style="border: none;">
            <b-card-body style="padding: 0;">
              <img :src="codeSource" alt="二维码" style="width: 136px;height: 136px; margin-bottom: 8px;">
              </b-card-body>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import IndexEdit from './IndexEdit'

export default {
  name: 'index',
  props: {
    adminInfo: {
      type: String,
      required: true
    }
  },
  components: {
    IndexEdit
  },
  data () {
    return {
      edit_index: false,
      welcome:[{
        backPic: ''
      }],
      teamFeature: [],
      contactUs: [],
      codeSource: '',
      indexInfo: {}
    }
  },
  methods: {
    getWelCome() {
      axios.get("http://49.235.209.42:8080/index/welcome")
      .then((data) => {
        // console.log(data);
        this.welcome = [];
        this.welcome.push(data.data);
        this.indexInfo.welcome = this.welcome;
      })
    },
    getTeamFeature() {
      axios.get("http://49.235.209.42:8080/index/team_feature")
      .then((data) => {
        // console.log(data);
        this.teamFeature = data.data;
        this.indexInfo.teamFeature = this.teamFeature;
      })
    },
    getContactUs() {
      axios.get("http://49.235.209.42:8080/index/contact_us")
      .then((data) => {
        // console.log(data);
        this.contactUs = []
        this.contactUs.push(data.data);
        this.indexInfo.contactUs = this.contactUs;
      })
    },
    getQRCode() {
      axios.get("http://49.235.209.42:8080/index/qr_code")
      .then((data) => {
        // console.log(data);
        this.codeSource = data.data;
        this.indexInfo.codeSource = this.codeSource;
      })
    }
  },
  created() {
    this.getWelCome();
    this.getTeamFeature();
    this.getContactUs();
    this.getQRCode();
    if(this.adminInfo != '') {
      this.edit_index = true;
    }else {
      this.edit_index = false;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
