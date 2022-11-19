import axios from 'axios'
import store from '../store/index.js'
// import router from '../router/index.js'

const axiosInstance = axios.create()

axiosInstance.interceptors.request.use(
  function (config) {
    if (store.getters.isLogin) {
      config.headers.Authorization = store.getters.getToken
    }
    return config
  },
  function (error) {
    return Promise.reject(error)
  }
)

axiosInstance.interceptors.response.use(
  function (res) {
    // if (res.headers.authorization) {
    //   if (res.headers.authorization === 'Login Invalidate') {
    //     alert('로그인 만료')
    //     router.push('/login')
    //   } else {
    //     store.dispatch('setToken', res.headers.authorization)
    //   }
    // }
    return res
  },
  function (err) {
    if (err.response.headers.authorization) {
      store.dispatch('setToken', err.response.headers.authorization)
      return axiosInstance.request(err.config)
    }
    return Promise.reject(err)
  }
)

export default axiosInstance
