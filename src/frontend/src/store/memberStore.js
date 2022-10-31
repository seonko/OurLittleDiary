const memberStore = ({
  state: {
    memberId: null
  },
  getters: {
    memberId (state) {
      return state.memberId
    }
  },
  mutations: {
    setMemberId (state, _memberId) {
      state.memberId = _memberId
    }
  },
  actions: {
    setMemberId: ({ commit }, _memberId) => {
      commit('setMemberId', _memberId)
    }
  }
})

export default memberStore
