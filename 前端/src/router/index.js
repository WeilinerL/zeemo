import Vue from 'vue'
import Router from 'vue-router'

// Containers
const IndexContainer = () => import('@/containers/IndexContainer')

//views
const Index = () => import('@/views/index/Index')
const Login = () => import('@/views/Login')
const Oss = () => import('@/views/oss/Oss')
const Consultants = () => import('@/views/consultants/Consultants')
const SuccessList = () => import('@/views/success_list/SuccessList')
const OSI = () => import('@/views/osi/OSI')
const OSIShow = () => import('@/views/osi/OSIShow')
const SummerCamp= () => import('@/views/summer_camp/SummerCamp')

Vue.use(Router)

export default new Router({
  mode: 'hash', // https://router.vuejs.org/api/#mode
  linkActiveClass: 'open active',
  scrollBehavior: () => ({ y: 0 }),
  routes: [
    {
      path: '/',
      redirect: '/index',
      name: 'Home',
      component: IndexContainer,
      children: [
        {
          path: 'index',
          component: Index
          // children: [
          //   {
          //     path: '',
          //     component: Index
          //   }
          // ]
        },
        {
          path: '/overseas_study_service',
          name: 'oss',
          component: Oss
        },
        {
          path: '/introduction_of_consultants',
          name: 'consultants',
          component: Consultants
        },
        {
          path: '/success_list',
          name: 'success_list',
          component: SuccessList
        },
        {
          path: '/oversea_study_info',
          name: 'oversea_study_info',
          component: OSI
        },
        {
          path: '/summer_camp',
          name: 'summer_camp',
          component: SummerCamp
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
     path: '/oversea_study_info/article',
     name: 'article', 
     component: OSIShow
    }
  ]
})
