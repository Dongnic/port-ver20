<template>
  <div class="container">
    <!-- ServerButton은 채널 버튼
         isHome = 홈 로고
         hasNotifications = 알림유무
         mentions = 읽지 않은 메세지 갯수
         selected = 현재 선택한 채널
    -->
    <HomeButton  />
    <div class="separator"></div>
    <div id="server">
      <ServerButton selected hasNotifications :mentions="3" />
      <ServerButton :mentions="9" />
      <ServerButton hasNotifications :mentions="0" />
      <ServerButton hasNotifications :mentions="12" />
      <ServerButton />
      <div class="separator"></div>
      <server-button-3
        v-for="(chatRoom, index) in chatRoomList"
        :key="index"
        :no="chatRoom.id"
        :chatRoomInfo="chatRoom"
        :selected="chatRoom.id === activeChatRoom"
        @changeRoom="changeRoom"
      >
      </server-button-3>
    </div>
  </div>
</template>

<script>
import HomeButton from './home-button.vue'
import ServerButton from './server-button.vue'
import ServerButton3 from './server-button3.vue'

export default {
  props: {
    chatRoomList: Array,
    activeChatRoom: Number
  },
  components: {
    HomeButton,
    ServerButton,
    ServerButton3
  },
  watch: {
    activeChatRoom (newTab) {
      console.log('chatRoom: ' + newTab)
    }
  },
  methods: {
    changeRoom (no) {
      console.log(' list changeRoom ', no)
      this.$emit('changeRoom', no)
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;

  grid-area: sl;
  background-color: var(--tertiary);

  padding: 11px 0;
  max-height: 100vh;
  overflow-y: scroll;

  &::-webkit-scrollbar {
    display: none;
  }
}

.separator {
  width: 32px;
  margin-bottom: 8px;
  border-bottom: solid 2px var(--grey);
}
</style>
