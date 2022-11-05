import axios from '../plugins/axios.js'

const tokenStore = ({
  state: {
    access_token: null
  },
  getters: {
    isLogin (state) {
      return state.access_token != null
    }
  },
  mutations: {
    setToken (state, _token) {
      state.access_token = _token
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
