<script>
export default {
  data () {
    return {
      code: ''
    }
  },
  mounted () {
    this.axios.get('/api/login/oauth?code=' + this.$route.query.code)
      .then((res) => {
        this.$router.push('/login')
      })
  }
}
</script>
