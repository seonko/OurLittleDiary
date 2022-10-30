// import Vue from 'vue'
import Vuex from 'vuex'
import tokenStore from './tokenStore.js'

// Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    tokenStore: tokenStore
  }
})

export default store
