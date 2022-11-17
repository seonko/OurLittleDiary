<template>
  <body class="text-center">
  <main class="form-signin w-100 m-auto">
    <form @submit.prevent="signUp">
      <h1 class="h3 mb-3 fw-normal"><b>회원가입</b></h1>
      <div class="form-floating">
        <input type="email" class="form-control" id="email" v-model="email" placeholder="Email" required>
        <label for="email" class="mt">이메일 아이디</label>
      </div>
      <br>
      <button type="button" class="btn btn-dark" @click="checkEmailDuplicate">인증 메일 발송</button><br>
      <div class="form-floating">
        <br>
        <input type="text" class="form-control" id="authNoBox" v-model="authCodeCheck" placeholder="인증 번호" :disabled="emailDuplicatedCheck" :style="{'border-color':authCodeBoxColor}" @blur="checkAuthCode">
        <label for="authNoBox" class="mt-4" :style="{color:authCodeBoxColor}">인증 번호 입력</label>
      </div>
      <div class="form-floating">
        <br>
        <input type="password" class="form-control" id="password" v-model="password" placeholder="Password" :style="{'border-color':pwCheckResultColor}" required>
        <label for="password" class="mt-4" :style="{color:pwCheckResultColor}">비밀번호</label>
      </div>
      <div class="form-floating">
        <input type="password" class="form-control" id="password2" v-model="passwordForChecking" placeholder="Password Check" :style="{'border-color':pwCheckResultColor}" required>
        <label for="passwordForChecking" :style="{color:pwCheckResultColor}">비밀번호 재입력</label>
      </div>
      <span class="pw-info">비밀번호는 영문, 숫자, 특수문자를 포함한 <br> 8~20자리 이내로 입력해 주세요.</span>
      <br>
      <br>
      <div class="form-floating">
        <input type="text" class="form-control" id="name" v-model="nickname" placeholder="Nickname" :style="{'border-color':nicknameCheckResultColor}" @blur="checkNicknameDuplicate" required>
        <label for="nickname" :style="{color:nicknameCheckResultColor}">닉네임</label>
      </div>
      <span v-if="nicknameDuplicatedCheckResult && nicknameDuplicatedCheckResult !== null" :style="{color: 'red'}">
        이미 사용 중인 닉네임입니다.
      </span>
      <br>
      <br>
      아이디 검색을 허용하시겠습니까?
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" v-model="searchable" value=true checked>
        <label class="form-check-label" for="inlineRadio1">예</label>
      </div>
      <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" v-model="searchable" value=false>
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
      email: '',
      password: '',
      passwordForChecking: '',
      nickname: '',
      emailDuplicatedCheck: true,
      searchable: true,
      authCode: '',
      authCodeCheck: '',
      authCodeCorrect: null,
      authCodeBoxColor: '',
      pwCheckResult: null,
      pwCheckResultColor: '',
      nicknameDuplicatedCheckResult: null,
      nicknameCheckResultColor: ''
    }
  },
  methods: {
    sendEmail (email) {
      this.axios.get('/api/sendEmail/' + email)
        .then((res) => {
          this.authCode = res.data
        })
    },
    checkEmailDuplicate () {
      if (this.email === '') {
        alert('이메일을 입력해 주세요.')
      } else {
        this.axios.get('/api/checkEmailDuplicate/' + this.email)
          .then((res) => {
            this.emailDuplicatedCheck = res.data
            if (res.data) {
              alert('해당 이메일로 가입된 계정이 이미 존재합니다.')
            } else {
              alert('인증 코드를 전송하였습니다.')
              this.sendEmail(this.email)
            }
          })
      }
    },
    checkNicknameDuplicate () {
      if (this.nickname !== '') {
        this.axios.get('/api/checkNicknameDuplicate/' + this.nickname)
          .then((res) => {
            this.nicknameDuplicatedCheckResult = res.data
          })
          .catch((err) => {
            console.log(err)
          })
      } else {
        this.nicknameDuplicatedCheckResult = false
      }
    },
    checkAuthCode () {
      this.authCodeCorrect = (this.authCode === this.authCodeCheck)
      this.authCodeBoxColor = (this.authCodeCheck === '') ? '' : (this.authCodeCorrect) ? 'green' : 'red'
    },
    signUp (event) {
      const requestBody = {
        email: this.email,
        password: this.password,
        nickname: this.nickname,
        searchable: this.searchable
      }
      this.axios.post('/api/signUp', requestBody)
        .then((res) => {
          alert('회원가입 완료')
          this.$router.push('/login')
        })
        .catch((error) => {
          console.log(error)
        })
    }
  },
  watch: {
    password (newPassword) {
      if (this.password === '' || this.passwordForChecking === '') {
        this.pwCheckResult = null
      } else {
        this.pwCheckResult = (newPassword === this.passwordForChecking)
      }
    },
    passwordForChecking (newPasswordForChecking) {
      if (this.password === '' || this.passwordForChecking === '') {
        this.pwCheckResult = null
      } else {
        this.pwCheckResult = (this.password === newPasswordForChecking)
      }
    },
    pwCheckResult (newPwCheckResult) {
      this.pwCheckResultColor = (newPwCheckResult === null) ? '' : newPwCheckResult ? 'green' : 'red'
    },
    nicknameDuplicatedCheckResult (newNicknameDuplicatedCheckResult) {
      this.nicknameCheckResultColor = (newNicknameDuplicatedCheckResult === null) ? '' : newNicknameDuplicatedCheckResult ? 'red' : 'green'
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
.form-signin {
max-width: 300px;
padding: 5px;
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
.pw-info {
font-size: 11px;
color: rgb(151, 151, 151);
}
</style>
