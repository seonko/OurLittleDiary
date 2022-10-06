<template>
  <body class="text-center">
    <main class="form-signin w-100 m-auto">
      <form @keyup.enter="login()">
        <img class="logo" :src="require('@/assets/common/logo.png')">
        <p style="font-size: small;">오늘도 일상을 공유할 준비가 되셨나요?</p>
        <div class="form-floating">
          <input type="email" class="form-control" id="floatingInput" v-model="email" required="required" placeholder="abc@example.com">
          <label for="floatingInput">EMAIL ID</label>
        </div>
        <div class="form-floating">
          <input type="password" class="form-control" id="floatingPassword" v-model="password" required="required" placeholder="Password">
          <label for="floatingPassword">PASSWORD</label>
        </div>
        <button class="w-100 btn btn-outline-dark btn-lg" type="button" @click="login">LOGIN</button>
        <router-link class="mt-5 text-black" to="/findPw">비밀번호를 잊었나요?</router-link><br>
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
        <p class="text-muted"><router-link to="/signUp" class="text-black">회원 가입</router-link>을 통해 일상을 공유해 보세요!</p>
      </form>
    </main>
  </body>
</template>

<script>
export default {
  name: 'HomeView',
  components: {
  }
}
</script>

<style scoped>
body {
    align-items: center;
    padding-top: 100px;
    padding-bottom: 0px;
    background-color: white;
    margin: auto;
  }
  .logo {
    max-width: 300px;
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
