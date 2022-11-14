<template>
  <body>
    <div class="bg-light me-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden">
      <div class="cal">
        <h2>
          <router-link to="#" @click="onClickPrev(currentMonth)">◀</router-link>
          {{currentYear}}년 {{currentMonth}}월
          <router-link to="#" @click="onClickNext(currentMonth)">▶</router-link>
        </h2>
        <table class="table">
            <thead>
              <tr>
                <td v-for="(weekName, idx) in weekNames" v-bind:key="idx">
                  {{weekName}}
                </td>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(row, idx) in currentCalendarMatrix" :key="idx">
                <td class="day" v-for="(day, idx2) in row" :key="idx2" @click="onClickDay(currentYear, currentMonth, day)" style="padding:20px;">
                  <div>
                    <span v-if="isToday(currentYear, currentMonth, day)" class="today">
                      {{day}}
                    </span>
                    <span v-else>
                      {{day}}
                    </span>
                  </div>
                </td>
              </tr>
            </tbody>
        </table>
      </div>
    </div>
  </body>
</template>

<script>
export default {
  data () {
    return {
      weekNames: ['일', '월', '화', '수', '목', '금', '토'],
      rootYear: 1904,
      rootDayOfWeekIndex: 5, // 2000년 1월 1일은 토요일
      currentYear: new Date().getFullYear(),
      currentMonth: new Date().getMonth() + 1,
      currentDay: new Date().getDate(),
      currentMonthStartWeekIndex: null,
      currentCalendarMatrix: [],
      endOfDay: null,
      memoDatas: []
    }
  },
  mounted () {
    this.init()
  },
  methods: {
    init () {
      this.currentMonthStartWeekIndex = this.getStartWeek(this.currentYear, this.currentMonth)
      this.endOfDay = this.getEndOfDay(this.currentYear, this.currentMonth)
      this.initCalendar()
    },
    initCalendar () {
      this.currentCalendarMatrix = []
      let day = 1
      for (let i = 0; i < 6; i++) {
        const calendarRow = []
        for (let j = 0; j < 7; j++) {
          if (i === 0 && j < this.currentMonthStartWeekIndex) {
            calendarRow.push('')
          } else if (day <= this.endOfDay) {
            calendarRow.push(day)
            day++
          } else {
            calendarRow.push('')
          }
        }
        this.currentCalendarMatrix.push(calendarRow)
      }
    },
    getEndOfDay (year, month) {
      switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
          return 31
        case 4:
        case 6:
        case 9:
        case 11:
          return 30
        case 2:
          if (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0)) {
            return 29
          } else {
            return 28
          }
        default:
          console.log('unknown month ' + month)
          return 0
      }
    },
    getStartWeek (targetYear, targetMonth) {
      let year = this.rootYear
      let month = 1
      let sumOfDay = this.rootDayOfWeekIndex
      while (true) {
        if (targetYear > year) {
          for (let i = 0; i < 12; i++) {
            sumOfDay += this.getEndOfDay(year, month + i)
          }
          year++
        } else if (targetYear === year) {
          if (targetMonth > month) {
            sumOfDay += this.getEndOfDay(year, month)
            month++
          } else if (targetMonth === month) {
            return sumOfDay % 7
          }
        }
      }
    },
    onClickPrev (month) {
      month--
      if (month <= 0) {
        this.currentMonth = 12
        this.currentYear -= 1
      } else {
        this.currentMonth -= 1
      }
      this.init()
    },
    onClickNext (month) {
      month++
      if (month > 12) {
        this.currentMonth = 1
        this.currentYear += 1
      } else {
        this.currentMonth += 1
      }
      this.init()
    },
    isToday (year, month, day) {
      const date = new Date()
      return year === date.getFullYear() && month === date.getMonth() + 1 && day === date.getDate()
    },
    onClickDay (year, month, day) {
      if (month < 10) {
        month = '0' + month
      }
      if (day < 10) {
        day = '0' + day
      }
      this.$store.dispatch('setPostDay', year + '-' + month + '-' + day)
      this.$store.dispatch('setFnPost', null)
    }
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
}
.day:hover {
  cursor: pointer;
  background-color: rgb(151, 148, 148);
}
.today {
  -moz-border-radius:15px 15px 15px 15px;
  border-radius:15px 15px 15px 15px;
  border:solid 1px #ffffff;
  background-color:#2b6bd1;
  padding:10px;
  color:#ffffff;
}
</style>
