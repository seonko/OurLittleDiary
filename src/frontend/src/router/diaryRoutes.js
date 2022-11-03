export default [
  {
    path: '/diaryList',
    name: 'diaryList',
    component: () => import('../views/diary/DiaryListView.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/diary/:id',
    name: 'diary',
    component: () => import('../views/diary/DiaryView.vue')
  }
]
