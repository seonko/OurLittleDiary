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
          <td @click="fnPostRead(row.id)"><strong class="postTitle">{{ row.title }}</strong><span style="color:red;margin:5px">[{{ row.replyCount }}]</span></td>
          <td>{{ row.member.nickname }}</td>
          <td v-if="row.contentCreateDate.substr(0, 10) === row.datetime">
            {{ row.contentCreateDate.substr(11, 5) }}
          </td>
          <td v-else>
            {{ row.contentCreateDate.substr(5, 5) }}
          </td>
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
      postDay: this.$store.state.diaryStore.postDay
    }
  },
  methods: {
    getPostList () {
      this.axios.get('/api/postList', {
        params: {
          diaryId: this.diaryId,
          postDay: this.postDay
        }
      })
        .then((response) => {
          this.postList = response.data.sort((a, b) => a.id - b.id)
        })
        .catch((error) => {
          console.log(error)
        })
    },
    fnPostWrite () {
      const date = new Date()
      const todayYear = date.getFullYear()
      const todayMonth = date.getMonth() + 1
      const todayDay = date.getDate()
      const postDayYear = Number(this.$store.state.diaryStore.postDay.substr(0, 4))
      const postDayMonth = Number(this.$store.state.diaryStore.postDay.substr(5, 2))
      const postDayDay = Number(this.$store.state.diaryStore.postDay.substr(8, 2))
      if (todayYear >= postDayYear && todayMonth >= postDayMonth && todayDay >= postDayDay) {
        this.$store.dispatch('setFnPost', 'write')
      } else {
        alert('미래의 일기는 작성할 수 없습니다.')
      }
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
    },
    check_postDay () {
      this.getPostList()
      return this.$store.getters.postDay
    },
    check_fnReply () {
      this.getPostList()
      return this.$store.getters.fnReply
    }
  },
  watch: {
    check_fnPost (val) {
      this.fnPost = val
    },
    check_postDay (val) {
      this.postDay = val
    },
    check_fnReply (val) {
      this.fnReply = val
    }
  }
}
</script>

<style scoped>
.postTitle {
  cursor: pointer;
}
</style>
