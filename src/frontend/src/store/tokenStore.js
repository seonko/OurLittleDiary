import axios from '../plugins/axios'

const tokenStore = ({
  state: {
    atk: null
  },
  getters: {
    isLogin (state) {
      return state.atk != null
    }
  },
  mutations: {
    setToken (state, _token) {
      state.atk = _token
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
