<template>
  <div class="postWriteArea">
    <input class="writeTitle form-control" type="text" v-model="title" placeholder="제목" required />
    <div class="postInfoArea">
      <p>{{nickname}}  |  {{todayYear}}-{{todayMonth}}-{{todayDay}}</p>
    </div>
    <textarea class="writeContent form-control" v-model="content" placeholder="내용을 입력하세요." required />
    <button class="btn" style="float:right;" @click="createPost()">등록</button>
  </div>
</template>

<script>
export default {
  data () {
    return {
      title: '',
      content: '',
      nickname: this.$store.state.memberStore.nickname,
      todayYear: new Date().getFullYear(),
      todayMonth: new Date().getMonth() + 1,
      todayDay: new Date().getDate()
    }
  },
  methods: {
    createPost () {
      this.requestBody = {
        title: this.title,
        content: this.content,
        diaryId: this.$store.state.diaryStore.diaryId,
        postId: parseInt(this.$store.state.diaryStore.fnPost.substr(4))
      }
      this.axios.post('/api/post/write', this.requestBody)
        .then((response) => {
          alert('일기가 저장되었습니다.')
          this.$store.dispatch('setFnPost', null)
        })
        .catch((error) => {
          console.log(error)
        })
    }
  }
}
</script>

<style scoped>
.writeTitle{
  width: 600px;
}
.writeContent{
  width: 600px;
  height: 400px;
  resize: none;
}
</style>
