<template>
    <body class="text-center">
      <div class="d-md-flex flex-md-equal w-100 my-md-3 ps-md-3">
        <div class="d-md-flex flex-md-equal w-100 my-md-3 ps-md-3">
          <DiaryCalendar/>
          <DiaryPost/>
        </div>
      </div>
      <div class="d-md-flex flex-md-equal w-100 my-md-3 ps-md-3">
        <div class="d-md-flex flex-md-equal w-100 my-md-3 ps-md-3">
          <DiaryPostList/>
          <DiaryReply/>
        </div>
      </div>
    </body>
  </template>

<script>
import DiaryCalendar from '@/components/diary/DiaryCalendar.vue'
import DiaryPost from '@/components/diary/DiaryPost.vue'
import DiaryPostList from '@/components/diary/DiaryPostList.vue'
import DiaryReply from '@/components/diary/DiaryReply.vue'

export default {
  components: {
    DiaryCalendar,
    DiaryPost,
    DiaryPostList,
    DiaryReply
  },
  data () {
    return {
    }
  },
  methods: {
  }
}
</script>

<style scoped>
</style>
