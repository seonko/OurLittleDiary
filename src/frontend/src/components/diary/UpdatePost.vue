<template>
  <div class="postUpdateArea">
    <input class="writeTitle form-control" type="text" v-model="title" placeholder="제목" required />
    <div class="postInfoArea">
      <p>{{nickname}}  |  {{contentCreateDate.substr(0, 16)}}</p>
    </div>
    <textarea class="writeContent form-control" v-model="content" placeholder="내용을 입력하세요." required />
    <div>
      <button class="btn" @click="updatePost">수정</button>
      <button class="btn" @click="cancel">취소</button>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      title: '',
      content: '',
      nickname: '',
      contentCreateDate: '',
      postId: parseInt(this.$store.state.diaryStore.fnPost.substr(6)),
      replyCount: 0
    }
  },
  methods: {
    getPost () {
      this.axios.get('/api/getPost', {
        params: {
          postId: parseInt(this.$store.state.diaryStore.fnPost.substr(6))
        }
      })
        .then((response) => {
          this.title = response.data.title
          this.content = response.data.content
          this.nickname = response.data.member.nickname
          this.contentCreateDate = response.data.contentCreateDate
          this.replyCount = response.data.replyCount
        })
        .catch((error) => {
          console.log(error)
        })
    },
    updatePost () {
      this.requestBody = {
        title: this.title,
        content: this.content,
        postId: this.postId,
        diaryId: this.$store.state.diaryStore.diaryId,
        contentCreateDate: this.contentCreateDate,
        replyCount: this.replyCount,
        datetime: this.$store.state.diaryStore.postDay
      }
      this.axios.put('/api/post/update', this.requestBody)
        .then((response) => {
          alert('일기가 수정되었습니다.')
          this.$store.dispatch('setFnPost', 'read' + this.postId)
        })
        .catch((error) => {
          console.log(error)
        })
    },
    cancel () {
      this.$store.dispatch('setFnPost', 'read' + this.postId)
    }
  },
  created () {
    this.getPost()
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
