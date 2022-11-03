<template>
  <div class="postReadArea">
    <strong style="font-size:30px;">{{ title }}</strong>
    <div class="postInfoArea">
      <p>{{nickname}}  |  {{postDate}}</p>
    </div>
    <div class="postContentArea">
      {{ content }}
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
      postDate: ''
    }
  },
  methods: {
    getPost () {
      this.axios.get('/api/getPost', {
        params: {
          postId: parseInt(this.$store.state.diaryStore.fnPost.substr(4, 1))
        }
      })
        .then((response) => {
          this.title = response.data.title
          this.content = response.data.content
          this.nickname = response.data.member.nickname
          this.postDate = response.data.contentCreateDate.substr(0, 10)
        })
        .catch((error) => {
          console.log(error)
        })
    }
  },
  created () {
    this.getPost()
  },
  computed: {
    check_fnPost () {
      this.getPost()
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
.writeTitle{
  width: 600px;
}
.writeContent{
  width: 600px;
  height: 400px;
  resize: none;
}
</style>
