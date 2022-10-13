<template>
  <body>
    <div class="bg-light me-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden">
      <div class="postTableArea">
        <table class="normalPostTable table table-hover">
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
        <!-- <tbody v-if="list.length">
          <tr v-for="(row, idx) in list" :key="idx">
            <td>{{ row.id }}</td>
            <td><a @click="fnGoRetrievePage(row.id)">
            <span class="postTitle">{{ row.title }}</span>
            </a></td>
            <td>{{ row.memberName }}</td>
            <td>
              <span v-if="row.datetime.substring(0, 10) === todayDate" class="col-4 time text-muted small">
                {{ row.datetime.substring(10, 16) }}
              </span>
              <span v-else class="col-4 time text-muted small">
                {{ row.datetime.substring(2, 10) }}
              </span>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <tr>
            <td colspan="6">게시글이 없습니다.</td>
          </tr>
        </tbody> -->
        </table>
      </div>
    </div>
  </body>
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
  }
}
</script>

<style scoped>
</style>
