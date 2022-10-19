<template>
  <div class="main">
    <h2>프로필</h2>
    <hr />
    <div class="main-background">
      <div class="input-area">
        <div class="flex-column">
          <div class="input-area-box">
            <h3>프로필 사진</h3>
            <v-file-input
              label="File input"
              ref="image"
              v-model="imagedata"
              accept="image/*"
              @change="uploadImg()"
              @click="removeImg()"
            />
          </div>
          <div class="input-area-box">
            <h3>소개글</h3>
            <textarea
            placeholder="소개글을 입력해주세요"
            v-model="content"
            v-on:input="check"
            ></textarea>
            <p :class="{help: true, 'is-danger': remaining==0}">{{instruction}}</p>
          </div>
          <div class="input-area-box">
            <h3>상태</h3>
            <v-select
            v-model="status"
            :items="items"
            filled
            dense
          ></v-select>
          </div>
        </div>
      </div>
      <div class="input-right">
        <div class="inner-right">
          <img v-if="imagedata != ''" :src="image" style="width: 80%; height: 80%;"/>
        </div>
      </div>
      <v-btn
       @click="updateProfile"
       elevation="1"
       large
      >수정</v-btn>
    </div>
  </div>
</template>

<script>
// import img from '../../assets/images/profile.png'

export default {
  data () {
    return {
      content: '',
      image: '',
      imagedata: '',
      status: '',
      items: ['온라인', '자리비움', '고스트'],
      limit: 200
    }
  },
  props: {
    isShow: Boolean
  },
  computed: {
    instruction () {
      return this.content === ''
        ? 'limit: ' + this.limit + ' characters'
        : 'remaining ' + this.remaining + ' characters'
    },
    remaining () {
      return this.limit - this.content.length
    }
  },
  methods: {
    uploadImg () {
      console.log('들어왔다')
      const image = this.$refs.image.files[0]

      const url = URL.createObjectURL(image)
      this.image = url
      console.log(url)
      console.log(this.image)
    },
    // replaceByDefault (e) {
    //   e.target.src = img
    // },
    check: function () {
      this.content = this.content.substr(0, this.limit)
    },
    removeImg: function () {
      const defaultImg = this.$refs.image
      this.image = defaultImg
    },
    updateProfile () {
      console.log(this.imagedata)
      console.log(this.content)
      console.log(this.status)
    }
  }
}
</script>

<style lang="sass" scoped>
@import "@/assets/scss/mypage.scss"
</style>

<style scoped>
textarea {
  resize: none;
  width: 100%;
  height: 8.25em;
  background-color: #474b51;
  border-radius: 10px;
}
.v-btn {
  margin-bottom: 30px;
  margin-left: -30px;
  position: absolute;
}
</style>
