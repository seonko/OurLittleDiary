export default [
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
