<template>
  <body class="text-center">
  <main class="form-signin w-100 m-auto">
    <form @submit.prevent="signUp">
      <h1 class="h3 mb-3 fw-normal"><b>회원가입</b></h1>
      <div class="form-floating">
        <input type="email" class="form-control" id="email" v-model="email" placeholder="Email" required>
        <label for="email" class="">Email address</label>
      </div>
      <br>
      <!-- <button type="button" class="btn btn-dark" @click="sendMail">인증메일보내기</button><br> -->
      <div class="form-floating">
        <br>
        <input type="text" class="form-control" id="password" v-model="password" placeholder="Password" required>
        <label for="password" class="mt-4">Password</label>
      </div>
      <!-- <div class="form-floating">
        <input type="text" class="form-control" id="password2" @input="pwCheck" v-model="passwordForChecking" placeholder="Password Check" required>
        <label for="passwordForChecking" class="">Password Check</label>
        <span id="pwCheckResult">{{ pwCheckResult }}</span>
      </div> -->
      <br>
      <div class="form-floating">
        <input type="text" class="form-control" id="name" v-model="nickname" placeholder="NICKNAME" required>
        <label for="nickname">NICKNAME</label>
        <!-- <span id="nicknameCheckResult">{{ nicknameCheckResult }}</span> -->
      </div>
      <br>
      <!-- <button type="button" class="btn btn-dark" @click="checkName">중복 체크</button> -->
      아이디 검색을 허용하시겠습니까?
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" v-model="searchable" value=true checked>
        <label class="form-check-label" for="inlineRadio1">예</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" v-model="searchable" value="false">
        <label class="form-check-label" for="inlineRadio2">아니오</label>
      </div>
      <br>
      <br>
      <button type="submit" class="w-100 btn btn-outline-dark btn-lg">회원가입</button>
    </form>
  </main>
</body>
</template>

<script>
export default {
  name: 'SignUpView',
  data () {
    return {
      requestBody: {},
      email: '',
      password: '',
      passwordForChecking: '',
      nickname: '',
      searchable: true
    }
  },
  methods: {
    signUp (event) {
      this.reqeustBody = {
        email: this.email,
        password: this.password,
        nickname: this.nickname,
        searchable: this.searchable
      }
      this.axios.post('/api/signUp', this.reqeustBody)
        .then((res) => {
          alert('회원가입 완료')
          this.$router.push('/login')
        })
        .catch((error) => {
          console.log(error)
        })
    }
  }
}
</script>

<style scoped>
body {
height: 100%;
}
body {
display: flex;
align-items: center;
padding-top: 40px;
padding-bottom: 40px;
background-color: white;
}
span {
color: blue;
}
.form-signin {
max-width: 330px;
padding: 15px;
}
.form-signin .form-floating:focus-within {
z-index: 2;
}
.btn-id {
margin-right: 10px;
}
.btn-lg {
font-weight: bold;
}
</style>
