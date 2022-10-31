<template>
  <div class="postTableArea">
    <table>
      <thead>
        <tr>
          <th style="width:50px;">번호</th>
          <th style="width:200px;">제목</th>
          <th style="width:80px;">글쓴이</th>
          <th style="width:100px;">작성일</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>안녕하세요 ㅁㄶㄴㅇ한ㅇㅁㄴㅇ후;ㅁㄴㅇ흐;</td>
          <td>고래고래고라니</td>
          <td>1234-56-78</td>
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
    }
  },
  components: {
  },
  methods: {
    fnPostWrite () {
      this.$store.dispatch('setFnPost', 'write')
    }
  }
}
</script>

<style scoped>
</style>
