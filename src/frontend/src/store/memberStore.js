const memberStore = ({
  state: {
    memberId: null,
    nickname: null
  },
  getters: {
    memberId (state) {
      return state.memberId
    },
    nickname (state) {
      return state.nickname
    }
  },
  mutations: {
    setMemberId (state, _memberId) {
      state.memberId = _memberId
    },
    setNickname (state, _nickname) {
      state.nickname = _nickname
    }
  },
  actions: {
    setMemberId: ({ commit }, _memberId) => {
      commit('setMemberId', _memberId)
    },
    setNickname: ({ commit }, _nickname) => {
      commit('setNickname', _nickname)
    }
  }
})

export default memberStore
