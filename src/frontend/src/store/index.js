import Vuex from 'vuex'
import tokenStore from './tokenStore.js'
import memberStore from './memberStore.js'
// import createPersistedState from 'vuex-persistedstate'

const store = new Vuex.Store({
  modules: {
    tokenStore: tokenStore,
    memberStore: memberStore
  }
  // plugins: [
  //   createPersistedState({
  //     paths: ['memberStore', 'tokenStore']
  //   })
  // ]
})

export default store
