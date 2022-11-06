<template>
  <div class="postReadArea">
    <strong style="font-size:30px;">{{ title }}</strong>
    <div class="postInfoArea">
      <p>{{nickname}}  |  {{postDate}}</p>
    </div>
    <div class="postContentArea">
      <p style="white-space: pre-line;">{{ content }}</p>
    </div>
    <div>
      <button class="btn" @click="updatePost">수정</button>
      <button class="btn" @click="deletePost">삭제</button>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      postId: parseInt(this.$store.state.diaryStore.fnPost.substr(4)),
      title: '',
      content: '',
      nickname: '',
      postDate: '',
      writerId: ''
    }
  },
  methods: {
    getPost () {
      this.axios.get('/api/getPost', {
        params: {
          postId: parseInt(this.$store.state.diaryStore.fnPost.substr(4))
        }
      })
        .then((response) => {
          this.title = response.data.title
          this.content = response.data.content
          this.nickname = response.data.member.nickname
          this.postDate = response.data.contentCreateDate.substr(0, 16)
          this.writerId = response.data.member.id
        })
        .catch((error) => {
          console.log(error)
        })
    },
    updatePost () {
      this.$store.dispatch('setFnPost', 'update' + parseInt(this.$store.state.diaryStore.fnPost.substr(4)))
    },
    deletePost () {
      if (confirm('일기를 삭제하시겠습니까?')) {
        this.axios.delete('/api/deletePost/' + parseInt(this.$store.state.diaryStore.fnPost.substr(4)))
          .then((response) => {
            alert('일기가 삭제되었습니다.')
            this.$store.dispatch('setFnPost', null)
          })
          .catch((error) => {
            console.log(error)
          })
      }
    }
  },
  created () {
    this.getPost()
  },
  computed: {
    check_fnPost () {
      this.getPost()
      return this.$store.getters.fnPost
    },
    getContent () {
      return this.content.split('\n').join('<br>')
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
.writeTitle{
  width: 600px;
}
.writeContent{
  width: 600px;
  height: 400px;
  resize: none;
}
</style>
