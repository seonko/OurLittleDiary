<template>
  <div v-if="fnPost === null"></div>
  <CreatePost v-else-if="fnPost === 'write'" />
  <ReadPost v-else-if="fnPost.substr(0, 4) === 'read'" />
</template>

<script>
import CreatePost from '@/components/diary/CreatePost'
import ReadPost from '@/components/diary/ReadPost'

export default {
  data () {
    return {
      fnPost: null
    }
  },
  components: {
    CreatePost,
    ReadPost
  },
  methods: {
  },
  computed: {
    check_fnPost () {
      return this.$store.getters.fnPost
    }
  },
  watch: {
    check_fnPost (_fnPost) {
      this.fnPost = _fnPost
    }
  }
}
</script>

<style scoped>
.postRetrieveArea{
  width: 600px;
  grid-column: 2;
  grid-row: 1;
  margin: auto;
  position: relative;
  left: 30%;
}
.postContentArea{
  margin-top: 30px;
  margin-bottom: 30px;
}
</style>
