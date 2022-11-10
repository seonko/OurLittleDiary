<template>
  <div class="black-bg">
    <div class="white-bg">
      <div>
        <p>다이어리 이름</p>
        <input type="text" v-model="diaryName" required>
      </div>
      <div v-if="addedMemberList.length">
        <ul v-for="(row, idx) in addedMemberList" :key="idx">
          <button class="btn-nickname btn-info" v-if="idx === 0"><span style="font-weight: bold">{{ row }}</span></button>
          <button class="btn-nickname btn-info" v-else><span @click="delAddedMember(idx)">{{ row }}</span></button>
        </ul>
      </div>
      <div>
        <p>참여 인원</p>
        <input type="text" v-model="keyword" @keyup="memberSearch">
      </div>
      <div class="diary-member-container" v-if="searchedMemberList.length">
        <ul class="diary-member-list" v-for="(row2, idx2) in searchedMemberList" :key="idx2">
          <span v-if="addedMemberList.indexOf(row2) > -1" style="font-weight: bold">{{ row2 }}</span>
          <span v-else @click="addMember(row2)">{{ row2 }}</span>
        </ul>
      </div>
      <div>
        <p>다이어리 썸네일</p>
        <div>
          <div v-if="!thumbnail">
            <img :src="require('@/assets/common/noImage.png')">
            <input type="file" @change="thumbnailUpload" name="mfile">
          </div>
          <div v-else>
            <img :src="thumbnail">
            <button class="btn btn-danger" @click="remove">지우기</button>
          </div>
        </div>
      </div>
      <button @click="createDiary">생성하기</button>
      <button @click="modalClose">취소</button>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      diaryName: '',
      keyword: '',
      thumbnail: '',
      searchedMemberList: [],
      addedMemberList: [this.$store.state.memberStore.nickname],
      mFile: null
    }
  },
  methods: {
    modalClose () {
      this.$emit('modalClose')
    },
    remove () {
      this.thumbnail = ''
    },
    thumbnailUpload (event) {
      const files = event.target.files || event.dataTransfer.files
      this.createImage(files[0])
      this.mFile = files[0]
    },
    createImage (file) {
      const reader = new FileReader()
      reader.onload = (event) => {
        this.thumbnail = event.target.result
      }
      reader.readAsDataURL(file)
    },
    createDiary () {
      const formData = new FormData()
      formData.append('mFile', this.mFile)
      formData.append('diaryName', this.diaryName)
      formData.append('addedMemberList', JSON.stringify(this.addedMemberList))
      this.axios.post('/api/createDiary', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then((response) => {
          alert('다이어리 생성 완료')
          this.modalClose()
        })
        .catch((error) => {
          console.log(error)
        })
    },
    memberSearch () {
      this.axios.get('/api/diary/memberSearch', {
        params: {
          keyword: this.keyword
        }
      })
        .then((response) => {
          this.searchedMemberList = response.data
        })
        .catch((error) => {
          console.log(error)
        })
    },
    addMember (row) {
      this.addedMemberList.push(row)
    },
    delAddedMember (idx) {
      this.addedMemberList.splice(idx, 1)
    }
  }
}
</script>

<style scoped>
div {
  box-sizing: border-box;
}
.black-bg {
  width: 100%; height:100%;
  background: rgba(0,0,0,0.5);
  position: fixed; padding: 20px;
  z-index: 9998;
}
.white-bg {
  width: 100%; background: white;
  border-radius: 8px;
  padding: 20px;
}
img {
  width: 200px;
  height: 200px;
  border: 1px solid #303030;
  border-radius: 5px;
}
.diary-member-list:hover {
  cursor: pointer;
  background-color: rgb(46, 83, 247);
}
.btn-nickname:hover {
  background-color: red !important;
}
</style>
