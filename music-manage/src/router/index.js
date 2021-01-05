import Vue from 'vue'
import VueRouter from 'vue-router'

const Index = () => import('@/views/index')

const SingerManage = () => import('@/components/SingerManage')

const SongManage = () => import('@/components/SongManage')

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
      },
      {
        path: 'user',
        component: UserManage,
        meta: { title: '用户管理'}
      },
      {
        path: 'songs',
        component: SongManage,
        meta: { title: '歌曲管理'}
      }
    ]
  }
]

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
