<template>
  <div class="postTableArea">
    <table>
      <thead>
        <tr>
          <th style="width:50px;">번호</th>
          <th style="width:200px;">제목</th>
          <th style="width:80px;">글쓴이</th>
          <th style="width:80px;">작성시간</th>
        </tr>
      </thead>
      <tbody v-if="postList.length">
        <tr v-for="(row, idx) in postList" :key="idx">
          <td>{{ row.id }}</td>
          <td @click="fnPostRead(row.id)"><strong class="postTitle">{{ row.title }}</strong></td>
          <td>{{ row.member.nickname }}</td>
          <td>{{ row.contentCreateDate.substr(11, 5) }}</td>
        </tr>
      </tbody>
      <tbody v-else>
        <tr>
          <td colspan="6">등록된 일기가 없습니다.</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="buttonArea">
    <button class="btn btn-outline" @click="fnPostWrite()">글쓰기</button>
  </div>
</template>

<script>
export default {
  data () {
    return {
      diaryId: this.$store.state.diaryStore.diaryId,
      postList: [],
      targetYear: new Date().getFullYear(),
      targetMonth: new Date().getMonth() + 1,
      targetDay: new Date().getDate()
    }
  },
  methods: {
    getPostList () {
      let targetDay = this.targetDay
      if (targetDay < 10) {
        targetDay = '0' + targetDay
      }
      this.axios.get('/api/postList', {
        params: {
          diaryId: this.diaryId,
          targetDate: this.targetYear + '-' + this.targetMonth + '-' + targetDay
        }
      })
        .then((response) => {
          this.postList = response.data
        })
        .catch((error) => {
          console.log(error)
        })
    },
    fnPostWrite () {
      this.$store.dispatch('setFnPost', 'write')
    },
    fnPostRead (postId) {
      this.$store.dispatch('setFnPost', 'read' + postId)
    }
  },
  created () {
    this.getPostList()
  },
  computed: {
    check_fnPost () {
      this.getPostList()
      return this.$store.getters.fnPost
    }
  },
  watch: {
    check_fnPost (val) {
      this.fnPost = val
    }
  }
}
</script>

<style scoped>
.postTitle {
  cursor: pointer;
}
</style>
