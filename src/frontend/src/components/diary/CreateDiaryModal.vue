<template>
  <div class="black-bg">
    <div class="white-bg">
      <div>
        <p>다이어리 이름</p>
        <input type="text" v-model="diaryName" required>
      </div>
      <!-- <div>
        <p>참여 인원</p>
        <input type="text" v-model="diaryMember" required>
      </div> -->
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
      // diaryMember: '',
      thumbnail: '',
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
    thumbnailUpload (e) {
      const files = e.target.files || e.dataTransfer.files
      this.createImage(files[0])
      this.mFile = files[0]
    },
    createImage (file) {
      const reader = new FileReader()
      reader.onload = (e) => {
        this.thumbnail = e.target.result
      }
      reader.readAsDataURL(file)
    },
    createDiary (e) {
      const formData = new FormData()
      formData.append('mFile', this.mFile)
      formData.append('diaryName', this.diaryName)
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
</style>
