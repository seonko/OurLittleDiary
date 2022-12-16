export default [
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/member/LoginView.vue')
  },
  {
    path: '/login/oauth',
    name: 'oauthProc',
    component: () => import('../views/member/OauthProcView.vue')
  },
  {
    path: '/signUp',
    name: 'signUp',
    component: () => import('../views/member/SignUpView.vue')
  }
]
