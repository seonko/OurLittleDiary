import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import diaryRoutes from './diaryRoutes'
import memberRoutes from './memberRoutes'
import store from '../store/index'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: [
    ...routes,
    ...memberRoutes,
    ...diaryRoutes
  ]
})

export default router

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!store.getters.isLogin) {
      alert('로그인이 필요한 요청입니다.')
      next('/login')
    } else {
      next()
    }
  } else {
    next()
  }
})
