const diaryStore = ({
  state: {
    diaryId: null,
    fnPost: null
  },
  getters: {
    diaryId (state) {
      return state.diaryId
    },
    fnPost (state) {
      return state.fnPost
    }
  },
  mutations: {
    setDiaryId (state, _diaryId) {
      state.diaryId = _diaryId
    },
    setFnPost (state, _fnPost) {
      state.fnPost = _fnPost
    }
  },
  actions: {
    setDiaryId: ({ commit }, _diaryId) => {
      commit('setDiaryId', _diaryId)
    },
    setFnPost: ({ commit }, _fnPost) => {
      commit('setFnPost', _fnPost)
    }
  }
})

export default diaryStore
