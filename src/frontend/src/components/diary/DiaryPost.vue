<template>
  <CreatePost v-if="fnPost === 'write'" />
  <div v-else></div>
  <!-- <body v-else>
    <div class="bg-light me-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden">
      <div v-if="member.id === memberId">
        <div style="margin-bottom:20px;"><button class="btn" @click="fnGoEditPage()">수정</button>
        <button class="btn" @click="fnPostDelete()">삭제</button></div>
        <hr>
      </div>
      <strong style="font-size:30px;">{{ title }}</strong>
      <strong style="font-size:30px;">Front 제작은 ....</strong>
      <div class="postInfoArea">
        {{ memberName }}
        {{ datetime }}
      </div>
      <div class="postInfoArea">
        고래고래고라니
        2022-10-13
      </div>
      <div class="postContentArea">
        {{ content }}
      </div>
      <div class="postContentArea">
        Front 제작은 진짜 지옥이네
        누가 좀 해줬으면 좋겠다...
        Grid를 좀 딱딱 고정시켜주면 안되나
      </div>
    </div>
  </body> -->
</template>

<script>
import CreatePost from '@/components/diary/CreatePost'
export default {
  data () {
    return {
      fnPost: null
    }
  },
  components: {
    CreatePost
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
