import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/diaryList',
    name: 'diaryList',
    component: () => import('../views/diary/DiaryListView.vue')
  },
  {
    path: '/diary',
    name: 'diary',
    component: () => import('../views/diary/DiaryView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
