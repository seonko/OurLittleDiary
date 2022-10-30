import axios from 'axios'

const tokenStore = ({
  state: {
    atk: null,
    rtk: null
  },
  getters: {
    isLogin (state) {
      return state.token != null
    }
  },
  mutations: {
    setToken (state, _token) {
      state.atk = _token
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

export default tokenStore
