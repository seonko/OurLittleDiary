<template>
    <body class="text-center">
      <main class="form-signin w-100 m-auto">
        <form @submit.prevent="login()">
          <img class="logo" :src="require('@/assets/common/logo.png')">
          <div class="form-floating">
            <input type="email" class="form-control" v-model="email" required="required" placeholder="email">
            <label for="floatingInput">Email address</label>
          </div>
          <div class="form-floating">
            <input type="password" class="form-control" v-model="password"  required="required" placeholder="Password">
            <label for="floatingPassword">Password</label>
          </div>
          <button class="w-100 btn btn-outline-dark btn-lg" type="submit">로그인</button>
          <router-link class="mt-5 text-black" to="/findPw">Forgot Password?</router-link><br>
          <a @click="socialLogin('kakao')">
            <img class="oauthButton" :src="require('@/assets/member/icon_kakao.png')"/>
          </a>
          <a @click="socialLogin('google')">
            <img class="oauthButton" :src="require('@/assets/member/icon_google.png')"/>
          </a>
          <a @click="socialLogin('naver')">
            <img class="oauthButton" :src="require('@/assets/member/icon_naver.png')"/>
          </a>
          <p class="mt-5 text-muted">아직 계정이 없으신가요?</p>
          <p class="text-muted">지금 바로 <router-link to="/signUp" class="text-black">회원 가입</router-link> 해보세요.</p>
        </form>
      </main>
    </body>
  </template>

<script>
export default {
  data () {
    return {
      requestBody: {},
      email: '',
      password: ''
    }
  },
  methods: {
    login (event) {
      this.requestBody = {
        username: this.email,
        password: this.password
      }

      this.axios.post('/login', this.requestBody)
        .then((res) => {
          this.$router.push('/diaryList')
        })
        .catch((err) => {
          alert('로그인 실패')
          console.log(err)
          this.$router.push('/login')
        })
    },
    socialLogin (provider) {
      window.location.replace(
        'http://narangnorang.com/oauth2/authorization/' + provider
      )
    }
  }
}
</script>

<style scoped>
  body {
    align-items: center;
    padding-top: 200px;
    padding-bottom: 0px;
    background-color: white;
    margin: auto;
  }

  .logo {
    max-width: 200px;
    height: auto;
  }
  .form-signin {
    max-width: 330px;
    padding: 15px;
  }

  .form-signin .form-floating:focus-within {
    z-index: 2;
  }

  .form-signin input[type="email"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  .form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }

  .btn {
    margin-bottom: 20px;
  }

  .btn-lg {
    font-weight: bold;
  }

  .text-black {
    text-decoration: none;
    color: black;
    font-weight: bold;
  }

  .oauthButton {
    margin: 15px;
    margin-top: 30px;
  }
  .oauthButton:hover {
    cursor: pointer;
    filter: brightness(70%);
  }
</style>
