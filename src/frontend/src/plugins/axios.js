import axios from 'axios'
import store from '../store/index.js'
import router from '../router/index.js'

const instance = axios.create()

instance.interceptors.response.use(
  (res) => {
    if (res.headers.authorization) {
      if (res.headers.authorization === 'Login Invalidate') {
        alert('로그인 만료')
        router.push('/login')
      } else {
        store.dispatch('setToken', res.headers.authorization)
      }
    }
    return res
  },
  (err) => {
    store.dispatch('setToken', err.response.headers.authorization)
    return instance.request(err.config)
  }
)

export default instance
