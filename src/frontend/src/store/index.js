import axios from 'axios'
import { createStore } from 'vuex'

export default createStore({
  state: {
    token: null
  },
  getters: {
    isLogin (state) {
      return state.token != null
    }
  },
  mutations: {
    setToken (state, _token) {
      state.token = _token
      // 로그인 이후 모든 axios 요청 header에 토큰 넣는다
      axios.defaults.headers.common.Authorization = _token
    }
  },
  actions: {
    setToken: ({ commit }, _token) => {
      commit('setToken', _token)
    }
  },
  modules: {
  }
})
