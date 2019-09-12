(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d20f367"],{b355:function(t,a,e){"use strict";e.r(a);var n=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"summer-camp animated fadeIn"},[e("div",{staticClass:"container-fluid",staticStyle:{"margin-top":"56px",color:"#f86c6b"}},[e("b-row",{staticClass:"justify-content-center text-center"},[e("b-col",{attrs:{lg:"9"}},[e("b-card",{staticClass:"mb-2",staticStyle:{"object-fit":"cover"},attrs:{title:t.mainObjectShow.strContent,"img-src":t.mainObjectShow.strImg,"img-alt":"Image","img-top":"",tag:"article","img-height":"210px"}}),e("i",{staticClass:"icon-arrow-down font-2xl"})],1)],1)],1),t.edit_summer_camp?e("div",{staticClass:"success-list-edit text-right d-md-down-none mr-3"},[e("summer-camp-edit",{attrs:{mainObjectShow:t.mainObjectShow,advantagesShow:t.advantagesShow}})],1):t._e(),e("div",{staticClass:"container-fluid mt-3 mb-3"},[e("b-row",{staticClass:"justify-content-center text-center"},t._l(t.advantagesShow,function(a){return e("b-col",{attrs:{sm:"6",lg:"4"}},[e("b-card",{attrs:{header:a.strTitle,"header-tag":"header","img-src":a.strImg,"img-alt":"Image","img-top":""}},[e("b-card-text",[t._v("\n              "+t._s(a.strContent)+"\n            ")])],1)],1)}),1)],1)])},s=[],i=e("bc3a"),c=e.n(i),o=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"summer-camp-edit"},[e("b-button",{attrs:{variant:"success"},on:{click:function(a){t.show=!0}}},[t._v("编辑此页")]),e("b-modal",{attrs:{title:"编辑成功案例页面",size:"xl","header-bg-variant":t.headerBgVariant,"body-bg-variant":t.bodyBgVariant,"footer-bg-variant":t.footerBgVariant},model:{value:t.show,callback:function(a){t.show=a},expression:"show"}},[e("b-container",{staticClass:"text-center",attrs:{fluid:""}},[e("b-row",[e("h5",[t._v("第一部分 主要图片及文字展示")])]),e("b-row",{staticClass:"mb-1"},[e("b-col",[t._v("第一幅大图")]),e("b-col",[t._v("图片底部文字")])],1),e("div",[e("hr"),e("b-row",{staticClass:"mb-1"},[e("b-col",[e("img",{staticStyle:{width:"36px",height:"36px"},attrs:{src:t.mainObject.strImg,alt:"大图图片"}})]),e("b-col",[t._v("\n            "+t._s(t.mainObject.strContent)+"\n          ")])],1),e("hr"),e("b-row",{staticClass:"mb-1"},[e("b-col",[e("h6",{staticStyle:{color:"#f86c6b"}},[t._v("*1.背景图片最好选择1280*600 以长方形为宜 否则会变得扭曲 2.图片大小不能超过10MB(ps:更换图片会引起相应文件夹变动需要重新登录)*")]),e("b-form-file",{staticClass:"mt-3",attrs:{accept:"image/jpeg, image/png",plain:""},model:{value:t.mainObjectEdit.strImg,callback:function(a){t.$set(t.mainObjectEdit,"strImg",a)},expression:"mainObjectEdit.strImg"}})],1),e("b-col",[e("b-form-textarea",{attrs:{id:"textarea-small",size:"sm",placeholder:"点击编辑图片底部文字"},model:{value:t.mainObjectEdit.strContent,callback:function(a){t.$set(t.mainObjectEdit,"strContent",a)},expression:"mainObjectEdit.strContent"}})],1)],1)],1),e("hr"),e("b-row",[e("h5",[t._v("第二部分 选择优点展示")])]),e("h6",{staticStyle:{color:"#f86c6b"}},[t._v("*删除和增加选择优点点击之后就生效 若视图未改变 请刷新*")]),e("b-row",{staticClass:"mb-1"},[e("b-col",[t._v("优点图片")]),e("b-col",[t._v("优点名称")]),e("b-col",[t._v("优点内容")]),e("b-col",[t._v("操作")])],1),t._l(t.advantages,function(a){return e("div",[e("hr"),e("b-row",{staticClass:"mb-1"},[e("b-col",[e("img",{staticStyle:{width:"36px",height:"36px"},attrs:{src:a.strImg,alt:"案例图片"}})]),e("b-col",[t._v("\n            "+t._s(a.strTitle)+"\n          ")]),e("b-col",[t._v("\n            "+t._s(a.strContent)+"\n          ")]),e("b-col",[e("b-button",{directives:[{name:"deleteAdvantage",rawName:"v-deleteAdvantage",value:a.strTitle,expression:"advantage.strTitle"}],key:a.strTtile,staticClass:"m-1",attrs:{variant:"primary",size:"sm"}},[t._v("\n              删除\n            ")])],1)],1),e("hr"),e("b-row",[e("b-col",[e("h6",{staticStyle:{color:"#f86c6b"}},[t._v("*1.若要更换图片请直接删除该优点再重新添加*")])]),e("b-col",[e("b-form-textarea",{attrs:{id:"textarea-small",size:"sm",placeholder:"点击编辑优点名称"},model:{value:a.strTitle,callback:function(e){t.$set(a,"strTitle",e)},expression:"advantage.strTitle"}})],1),e("b-col",[e("b-form-textarea",{attrs:{id:"textarea-small",size:"sm",placeholder:"点击编辑优点简介"},model:{value:a.strContent,callback:function(e){t.$set(a,"strContent",e)},expression:"advantage.strContent"}})],1),e("b-col")],1)],1)}),e("hr"),e("b-row",[e("b-col",[e("h6",{staticStyle:{color:"#f86c6b"}},[t._v("*1.增加图片会引起相应文件夹变动需要重新登录 2.图片大小不能超过10MB*")]),e("b-form-file",{staticClass:"mt-3",attrs:{accept:"image/jpeg, image/png",plain:""},model:{value:t.addAdvantage.strPicture,callback:function(a){t.$set(t.addAdvantage,"strPicture",a)},expression:"addAdvantage.strPicture"}})],1),e("b-col",[e("b-form-textarea",{attrs:{id:"textarea-small",size:"sm",placeholder:"点击编辑优点名称"},model:{value:t.addAdvantage.strTitle,callback:function(a){t.$set(t.addAdvantage,"strTitle",a)},expression:"addAdvantage.strTitle"}})],1),e("b-col",[e("b-form-textarea",{attrs:{id:"textarea-small",size:"sm",placeholder:"点击编辑优点简介"},model:{value:t.addAdvantage.strContent,callback:function(a){t.$set(t.addAdvantage,"strContent",a)},expression:"addAdvantage.strContent"}})],1),e("b-col",[e("b-button",{directives:[{name:"addAdvantage",rawName:"v-addAdvantage"}],staticClass:"m-1",attrs:{variant:"primary",size:"sm"}},[t._v("\n            增加优点\n          ")])],1)],1)],2),e("div",{staticClass:"w-100",attrs:{slot:"modal-footer"},slot:"modal-footer"},[e("b-button",{staticClass:"float-right m-1",attrs:{variant:"primary",size:"sm"},on:{click:function(a){t.show=!1}}},[t._v("\n        关闭\n      ")]),e("b-button",{staticClass:"float-right m-1",attrs:{variant:"primary",size:"sm"},on:{click:t.confirmChange}},[t._v("\n        确认修改\n      ")])],1)],1),e("b-modal",{staticClass:"modal-danger",attrs:{title:"通知","ok-only":!0},model:{value:t.advantageInfoPicCheck,callback:function(a){t.advantageInfoPicCheck=a},expression:"advantageInfoPicCheck"}},[t._v("\n    优点图片不能为空！\n  ")]),e("b-modal",{staticClass:"modal-danger",attrs:{title:"通知","ok-only":!0},model:{value:t.advantageInfoTitleCheck,callback:function(a){t.advantageInfoTitleCheck=a},expression:"advantageInfoTitleCheck"}},[t._v("\n    优点名称不能为空!\n  ")]),e("b-modal",{staticClass:"modal-danger",attrs:{title:"通知","ok-only":!0},model:{value:t.advantageInfoContentCheck,callback:function(a){t.advantageInfoContentCheck=a},expression:"advantageInfoContentCheck"}},[t._v("\n    优点简介不能为空!\n  ")])],1)},r=[],l={inject:["reload"],name:"summer-camp-edit",props:{mainObjectShow:{type:Object,required:!0},advantagesShow:{type:Array,required:!0}},data:function(){return{show:!1,headerBgVariant:"info",bodyBgVariant:"light",footerBgVariant:"warning",mainObject:{},mainObjectEdit:{strImg:null,strContent:""},advantages:[],addAdvantage:{strPicture:null,strTitle:"",strContent:""},advantageInfoPicCheck:!1,advantageInfoTitleCheck:!1,advantageInfoContentCheck:!1,dataFlag:!0}},methods:{getMainObject:function(){var t=this;c.a.get("http://localhost:8080/summer_camp/welcome").then(function(a){t.mainObject=a.data})},getAdvantages:function(){var t=this;c.a.get("http://localhost:8080/summer_camp/advantages").then(function(a){t.advantages=a.data})},mainObjectImgEdit:function(){if(null!=this.mainObjectEdit.strImg){var t=new FormData;t.append("picture",this.mainObjectEdit.strImg),c.a.post("http://localhost:8080/summer_camp/mainObjectImgEdit/"+this.mainObject.strTitle,t).then(function(t){})}},mainObjectContentEdit:function(){""!=this.mainObjectEdit.strContent&&c.a.post("http://localhost:8080/summer_camp/mainObjectContentEdit/"+this.mainObject.strTitle+"/"+this.mainObjectEdit.strContent).then(function(t){})},advantageEdit:function(){c.a.post("http://localhost:8080/summer_camp/advantageEdit/",this.advantages).then(function(t){})},advantageAddCheck:function(){return null==this.addAdvantage.strPicture?(this.advantageInfoPicCheck=!0,!1):""==this.addAdvantage.strTitle?(this.advantageInfoTitleCheck=!0,!1):""!=this.addAdvantage.strContent||(this.advantageInfoContentCheck=!0,!1)},confirmChange:function(){var t=this;setTimeout(function(){t.mainObjectImgEdit()},"10"),setTimeout(function(){t.mainObjectContentEdit()},"10"),setTimeout(function(){t.advantageEdit()},"10"),this.show=!1,setTimeout(function(){t.reload()},"60")}},updated:function(){this.dataFlag&&(this.mainObject=this.mainObjectShow,this.advantages=this.advantagesShow),this.dataFlag=!1},directives:{deleteAdvantage:{bind:function(t,a,e){t.onclick=function(){c.a.delete("http://localhost:8080/summer_camp/delete_summer_camp/"+a.value).then(function(t){"success"==t.data.result&&setTimeout(function(){e.context.getAdvantages()},"10")})}}},addAdvantage:{bind:function(t,a,e){t.onclick=function(){if(e.context.advantageAddCheck()){var t=new FormData;t.append("picture",e.context.addAdvantage.strPicture),c.a.post("http://localhost:8080/summer_camp/add_advantage/"+e.context.addAdvantage.strTitle+"/"+e.context.addAdvantage.strContent,t).then(function(t){"success"==t.data.result&&(e.context.getAdvantages(),e.context.addAdvantage={strPicture:null,strTitle:"",strContent:""})})}}}}}},d=l,m=e("2877"),g=Object(m["a"])(d,o,r,!1,null,"41decd92",null),h=g.exports,u={name:"summer-camp",props:{adminInfo:{type:String,required:!0}},components:{SummerCampEdit:h},data:function(){return{edit_summer_camp:!1,mainObjectShow:{},advantagesShow:[]}},methods:{getMainObject:function(){var t=this;c.a.get("http://localhost:8080/summer_camp/welcome").then(function(a){t.mainObjectShow=a.data})},getAdvantages:function(){var t=this;c.a.get("http://localhost:8080/summer_camp/advantages").then(function(a){t.advantagesShow=a.data})}},created:function(){this.getMainObject(),this.getAdvantages(),""!=this.adminInfo?this.edit_summer_camp=!0:this.edit_summer_camp=!1}},b=u,v=Object(m["a"])(b,n,s,!1,null,"70ba0a6e",null);a["default"]=v.exports}}]);
//# sourceMappingURL=chunk-2d20f367.05455819.js.map