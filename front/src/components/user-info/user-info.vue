<template>
  <div class="container">
    <div class="profile">
      <div class="avatar" :style="{ 'background-Image' : 'url(' + userInfo.profileimage + ')' }">
        <v-badge
        bordered
        dot
        bottom
        color="#00ff55"
        offset-x="-31"
        offset-y="10"
        overlap
      ></v-badge>
      </div>
      <div class="user-data">
        <!-- 콧수염표현식으로 userName의 값을 화면에 출력 -->
        <strong>{{ userInfo.username }}</strong>
        <span>#{{ userInfo.id }}</span>
      </div>
    </div>
    <div class="actions">
      <!-- 각 컴포넌트(이미지-아이콘)의 사이즈 지정 -->
      <!-- <Mic :size="20" />
      <HeadPhones :size="20" /> -->
      <div class="text-center">
          <v-menu :location="location">
            <template v-slot:activator="{ props }">
              <Settings
              :size="20"
              v-bind="props" />
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in items"
                :key="index"
                class="settings"
              >
                <v-list-item-title style="cursor: pointer;" @click="change(index)">{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
    </div>
  </div>
</template>

<script>
// import Mic from 'vue-material-design-icons/Microphone'
// import HeadPhones from 'vue-material-design-icons/Headphones'
import Settings from 'vue-material-design-icons/CogOutline'

export default {
  // 상위컴포넌트로부터 userName의 값(String타입)을 받는다.
  props: {
    userInfo: Object

  },
  components: {
    Settings
  },
  data: () => ({
    items: [
      { title: '설정' },
      { title: '퇴장' }
    ],
    location: 'top'
  }),
  methods: {
    moveTo (url) {
      this.$router.push(url)
    },
    change (value) {
      console.log(' click : ', value)
      if (value === 0) {
        this.moveTo('/settings')
      } else if (value === 1) {
        this.$store.dispatch('module1/changeActiveChatRoom', 0)
        this.$store.dispatch('module1/getChatRoomList', this.userInfo.id)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  grid-area: ui;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color: var(--quaternary);
  box-shadow: rgba(0, 0, 0, 0.9) 0 1px 0 0;
}
.profile {
  display: flex;
  align-items: center;
}
.avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background-color: var(--grey);
  background-size: cover;
}
.user-data {
  display: flex;
  flex-direction: column;
  margin-left: 8px;
  font-size: 13px;
  strong {
    display: block;
    color: var(--white);
    letter-spacing: 1px;
  }
  span {
    color: var(--grey);
  }
}
.actions {
  color: var(--grey);
  span {
    cursor: pointer;
    transition: 0.2s;
    &:hover {
      color: var(--white);
    }
  }
}
.settings:hover{
    background-color: var(--quinary);
}
</style>
<style scoped>
.userBadge {
    animation:blink 2s ease-in-out infinite alternate;
}
@keyframes blink{
    0% {opacity:0.2;}
    100% {opacity:1;}
}
</style>
