<template>
  <CreateDiaryModal v-if="modalVal" @modalClose="modalClose" />
  <body class="text-center">
    <div class="py-5">
      <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3">
          <div class="col" v-for="(row, idx) in diaryList" :key="idx">
            <div class="card" @click="goToDiary(row.id)">
              <img class="card-img-top" :src="'/upload/images/diary/' + row.id + '.png'">
              <div class="card-body">
                <h5 class="card-title">{{ row.diaryName }}</h5>
                <div v-for="(row2, idx2) in partMemberList" :key="idx2">
                  <div v-if="idx === idx2">
                    <p v-for="(row3, idx3) in row2" :key="idx3">{{ row3.member.nickname }}</p>
                  </div>
                </div>
                <small class="text-muted">{{ row.diaryCreateDate.substring(0, 10) }}</small>
              </div>
            </div>
          </div>
          <div class="col">
            <div class="card" @click="modalOpen">
              <div class="card-body">
                <img class="add_icon" :src="require('@/assets/common/add_icon.png')">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
import CreateDiaryModal from '@/components/diary/CreateDiaryModal'
export default {
  data () {
    return {
      modalVal: false,
      memberId: 0,
      diaryList: [],
      partMemberList: [],
      currentYear: new Date().getFullYear(),
      currentMonth: new Date().getMonth() + 1,
      currentDay: new Date().getDate()
    }
  },
  components: {
    CreateDiaryModal
  },
  created () {
    this.getDiaryList()
  },
  methods: {
    getDiaryList () {
      this.axios.get('/api/diaryList', {
        params: {
          memberId: this.$store.state.memberStore.memberId
        }
      })
        .then((response) => {
          this.diaryList = response.data.diaryList
          this.partMemberList = response.data.partMemberList
        })
        .catch((error) => {
          console.log(error)
        })
    },
    modalOpen () {
      this.modalVal = true
    },
    modalClose () {
      this.getDiaryList()
      this.modalVal = false
    },
    goToDiary (diaryId) {
      this.$router.push('/diary/' + diaryId)
      this.$store.dispatch('setDiaryId', diaryId)
      if (this.currentMonth < 10) {
        this.currentMonth = '0' + this.currentMonth
      }
      if (this.currentDay < 10) {
        this.currentDay = '0' + this.currentDay
      }
      this.$store.dispatch('setPostDay', this.currentYear + '-' + this.currentMonth + '-' + this.currentDay)
    }
  }
}
</script>

<style scoped>
.card-img-top {
  height: 15em;
  object-fit: cover;
}
.card-title {
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: 1.4em;
  height: 2.8em;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.card-text {
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: 1.4em;
  height: 4.2em;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
.card:hover {
  cursor: pointer;
  filter: brightness(80%);
}
.add_icon {
  width: 50px;
  height: 50px;
}
</style>
