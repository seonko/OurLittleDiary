<template>
  <header class="masthead">
      <div class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
          <div class="d-flex justify-content-center">
            <div class="text-center">
              <img class="img-fluid" src="@/assets/common/logo.png" alt="..." width="600" > <br>
                <router-link class="btn btn-outline-dark btn-lg" to="/login">LOGIN</router-link>
              </div>
          </div>
      </div>
  </header>
</template>

<script>
export default {
  name: 'HomeView',
  components: {
  }
}
</script>

<style scoped>
.btn {
  padding: 1.25rem 2rem;
  font-size: 130%;
  font-weight: bold;
  margin-top: 50px;
  width: 300px;
}

.masthead {
  position: relative;
  min-height: 35rem;
  padding: 15rem 0;
  background-position: center;
  background-repeat: no-repeat;
  background-attachment: scroll;
  background-size: 900px;
}
</style>
