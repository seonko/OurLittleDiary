<template>
  <CreateDiaryModal v-if="modalVal" @modalClose="modalClose" />
  <body class="text-center">
    <p>diaryList</p>
    <button @click="modalOpen">다이어리 생성</button>
  </body>
</template>

<script>
import CreateDiaryModal from '@/components/diary/CreateDiaryModal'
export default {
  data () {
    return {
      modalVal: false
    }
  },
  components: {
    CreateDiaryModal
  },
  methods: {
    modalOpen () {
      this.modalVal = true
    },
    modalClose () {
      this.modalVal = false
    }
  }
}
</script>

<style scoped>
</style>
