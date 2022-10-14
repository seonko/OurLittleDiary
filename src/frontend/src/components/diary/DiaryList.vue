<template>
  <CreateDiaryModal v-if="modalVal" @modalClose="modalClose" />
  <body>
    <div class="py-5">
      <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3">
          <div class="col">
            <div class="card" @click="goToDiary">
              <img class="card-img-top" :src="require('@/assets/common/noImage.png')">
              <div class="card-body">
                <h5 class="card-title">Card Title</h5>
                <p class="card-text">Card Content</p>
                <!-- <a href="#" class="btn btn-primary">Link</a> -->
                <small class="text-muted">날짜</small>
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
    },
    goToDiary () {
      this.$router.push('/diary')
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
