const diaryStore = ({
  state: {
    diaryId: null,
    fnPost: null,
    postDay: null,
    fnReply: null
  },
  getters: {
    diaryId (state) {
      return state.diaryId
    },
    fnPost (state) {
      return state.fnPost
    },
    postDay (state) {
      return state.postDay
    },
    fnReply (state) {
      return state.fnReply
    }
  },
  mutations: {
    setDiaryId (state, _diaryId) {
      state.diaryId = _diaryId
    },
    setFnPost (state, _fnPost) {
      state.fnPost = _fnPost
    },
    setPostDay (state, _postDay) {
      state.postDay = _postDay
    },
    setFnReply (state, _fnReply) {
      state.fnReply = _fnReply
    }
  },
  actions: {
    setDiaryId: ({ commit }, _diaryId) => {
      commit('setDiaryId', _diaryId)
    },
    setFnPost: ({ commit }, _fnPost) => {
      commit('setFnPost', _fnPost)
    },
    setPostDay: ({ commit }, _postDay) => {
      commit('setPostDay', _postDay)
    },
    setFnReply: ({ commit }, _fnReply) => {
      commit('setFnReply', _fnReply)
    }
  }
})

export default diaryStore
