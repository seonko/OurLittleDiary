<template>
  <div v-if="this.$store.state.diaryStore.fnPost === null"></div>
  <div v-else-if="this.$store.state.diaryStore.fnPost === 'write'"></div>
  <div v-else class="postReplyArea">
    <ul v-if="replyList.length" class="list_comment list-group">
      <li v-for="(row, idx) in replyList" :key="idx">
        <div class="commentSection">
          <b>{{ row.member.nickname }}</b>
          <span class="datetimeArea">{{ row.contentCreateDate }}</span>
          <span class="commentBtnArea" v-if="row.member.id === this.$store.state.memberStore.memberId">
            <button class="btn btn-sm" @click="fnReplyEditToggle(row.id, row.content)">수정</button>
            <button class="btn btn-sm" @click="fnCommentDelete(row.id)">삭제</button>
          </span>
          <div v-if="!isEditMode[row.id]" class="replyContentArea" style="padding:10px">
            <p style="white-space: pre-line;">{{ row.content }}</p>
          </div>
          <div v-else>
            <textarea class="editReplyArea form-control" v-model="editReplyContent"></textarea>
            <button class="ReplyEditBtn btn" @click="fnReplyEdit(row.id, row.contentCreateDate)">수정</button>
          </div>
        </div>
      </li>
    </ul>
    <div v-else>
      <p>등록된 댓글이 없습니다.</p>
    </div>
    <div class="writeReplyArea">
      <textarea class="writeReplyContent form-control" v-model="content"></textarea>
      <button class="btn_postComment btn" @click="fnWriteReply()">등록</button>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      replyList: [],
      content: '',
      replyId: null,
      editReplyContent: '',
      isEditMode: {}
    }
  },
  methods: {
    getReplyList () {
      if (this.$store.state.diaryStore.fnPost !== null && this.$store.state.diaryStore.fnPost !== 'write' && this.$store.state.diaryStore.fnPost.substr(0, 6) !== 'update') {
        this.axios.get('/api/replyList', {
          params: {
            postId: parseInt(this.$store.state.diaryStore.fnPost.substr(4))
          }
        })
          .then((response) => {
            this.replyList = response.data
            for (let i = 0; i < this.replyList.length; i++) {
              this.isEditMode[this.replyList[i].id] = false
            }
          })
          .catch((error) => {
            console.log(error)
          })
      } else {
        this.replyList = []
      }
    },
    fnWriteReply () {
      this.requestBody = {
        content: this.content,
        postId: parseInt(this.$store.state.diaryStore.fnPost.substr(4))
      }
      this.axios.post('/api/reply/write', this.requestBody)
        .then((response) => {
          alert('댓글이 등록되었습니다.')
          this.getReplyList()
          this.content = ''
        })
        .catch((error) => {
          console.log(error)
        })
    },
    fnReplyEditToggle (id, content) {
      this.editReplyContent = content
      if (this.isEditMode[id]) {
        this.isEditMode[id] = false
      } else {
        this.isEditMode[id] = true
      }
    },
    fnReplyEdit (id, contentCreateDate) {
      this.requestBody = {
        content: this.editReplyContent,
        replyId: id,
        postId: parseInt(this.$store.state.diaryStore.fnPost.substr(4)),
        contentCreateDate: contentCreateDate
      }
      this.axios.post('/api/reply/write', this.requestBody)
        .then((res) => {
          alert('댓글이 수정되었습니다.')
          this.isEditMode[id] = false
          this.getReplyList()
        })
        .catch((err) => {
          console.log(err)
        })
    },
    fnCommentDelete (replyId) {
      if (confirm('댓글을 삭제하시겠습니까?')) {
        this.axios.delete('/api/reply/delete', {
          params: {
            replyId: replyId
          }
        })
          .then((res) => {
            alert('댓글이 삭제되었습니다.')
            this.getReplyList()
          })
          .catch((err) => {
            console.log(err)
          })
      }
    }
  },
  computed: {
    check_fnPost () {
      this.getReplyList()
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
.postReplyArea{
  margin-top: 10px;
  width: 600px;
}
.replyContentArea{
  margin-top: 10px;
  margin-bottom: 10px;
}
.datetimeArea{
  font-size:12px;
  margin-left: 10px;
}
.commentBtnArea{
  position: absolute;
  right: 20px;
}
.editReplyArea{
  margin-top:20px;
  width: 100%;
  resize: none;
}
.ReplyEditBtn{
  float: right;
}
</style>
