import axios from 'axios'
import store from '../store/index.js'

const instance = axios.create()

instance.interceptors.response.use(
  (res) => {
    if (res.headers.authorization) {
      store.dispatch('setToken', res.headers.authorization)
    }
    return res
  }
)

export default instance
