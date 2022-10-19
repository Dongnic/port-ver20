<template>
  <div class="main">
    <h2>설정</h2>
    <div class="main-background">
      <div class="input-area">
        <div class="flex-column">
          <div class="input-area-box">
            <h3>디스플레이</h3>
            <v-radio-group v-model="displayMode" displaymode>
              <v-radio label="라이트 모드" value="lightmode" v-model="lightmode"></v-radio>
              <v-radio label="다크 모드" value="darkmode" v-model="darkmode"></v-radio>
            </v-radio-group>
          </div>
          <div class="input-area-box">
            <h3>알림</h3>
            <v-radio-group v-model="alertonoff" alertonoff>
              <v-radio label="켜기" value="on"></v-radio>
              <v-radio label="끄기" value="off"></v-radio>
            </v-radio-group>
          </div>
        </div>
      </div>
      <v-btn
      @click="updateSetting"
      elevation="1"
      large class="v-btn-custom"
      >수정</v-btn>
    </div>
  </div>
</template>

<script>
// import img from '../../assets/images/profile.png'

export default {
  data () {
    return {
      displayMode: null,
      alertonoff: null,
      lightmode: '',
      darkmode: ''
    }
  },
  props: {
    isShow: Boolean
  },
  methods: {
    updateSetting () {
      console.log(this.displayMode)
      console.log(this.alertonoff)
    }
  }
}
</script>

<style scoped>
.v-btn-custom {
  bottom: 50px;
    right: 60px;
    position: absolute;
}
</style>
<style lang="sass" scoped>
@import "@/assets/scss/mypage.scss"
</style>
