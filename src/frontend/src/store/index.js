import Vuex from 'vuex'
import tokenStore from './tokenStore.js'
import memberStore from './memberStore.js'
import diaryStore from './diaryStore.js'
import createPersistedState from 'vuex-persistedstate'

const store = new Vuex.Store({
  modules: {
    tokenStore: tokenStore,
    memberStore: memberStore,
    diaryStore: diaryStore
  },
  plugins: [
    createPersistedState({
      paths: ['memberStore', 'tokenStore', 'diaryStore']
      // storage: window.sessionStorage
    })
  ]
})

export default store
