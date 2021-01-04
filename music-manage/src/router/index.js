import Vue from 'vue'
import VueRouter from 'vue-router'

const Index = () => import('@/views/index')

const SingerManage = () => import('@/components/SingerManage')

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    component: Index,
    children: [
      {
        path: 'singer',
        component: SingerManage,
        meta: { title: '歌手管理' }
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
