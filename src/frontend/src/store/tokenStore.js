const tokenStore = ({
  state: {
    access_token: null
  },
  getters: {
    isLogin (state) {
      return state.access_token != null
    },
    getToken (state) {
      return state.access_token
    }
  },
  mutations: {
    setToken (state, _token) {
      state.access_token = _token
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
