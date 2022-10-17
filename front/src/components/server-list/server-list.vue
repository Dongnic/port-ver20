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
      <!-- 채널 생성버튼 -->
      <v-btn
        class="add-button"
        fab
        dark
        color="cyan accent-2"
        @click="dialog = !dialog"
      >
        <v-icon>mdi-plus</v-icon>
      </v-btn>
      <!-- 채널 나가기 -->
      <v-btn
        class="add-button"
        fab
        dark
        color="red accent-2"
        @click="outRoom"
      >
        <v-icon>mdi-minus</v-icon>
      </v-btn>
      <!-- 방 생성 모달창 -->
      <v-dialog
        v-model="dialog"
      >
      {{inviteList}}
        <!-- 전체 감싸는 카드  -->
      <v-card
          color="blue-grey-darken-1"
          dark
          :loading="isUpdating"
        >
          <template v-slot:progress>
            <v-progress-linear
              absolute
              color="green-lighten-3"
              height="4"
              indeterminate
            ></v-progress-linear>
          </template>
          <button @click="$refs.fileRef.click" type="button">선택</button>
          <input type="file" @change="selectFile" accept="image/*" ref="fileRef" hidden/>
          <v-img v-if="imgLink != ''" height="200" cover :src="imgLink">
            <v-row>
              <v-col class="text-end" cols="12">
                <v-menu location="bottom start" origin="overlap" transition="slide-y-transition">
                </v-menu>
              </v-col>
              <v-row
                class="pa-4"
                align="center"
                justify="center"
              >
                <v-col class="text-center">
                  <h3 class="text-h5">
                    {{ title }}
                  </h3>
                  <span class="text-grey-lighten-1">{{ discribe }}</span>
                </v-col>
              </v-row>
            </v-row>
          </v-img>
          <v-form>
            <v-container>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    v-model="title"
                    :disabled="isUpdating"
                    filled
                    color="blue-grey-lighten-2"
                    label="방 이름"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    v-model="discribe"
                    :disabled="isUpdating"
                    filled
                    color="blue-grey-lighten-2"
                    label="용도"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-autocomplete
                    v-model="inviteList"
                    :disabled="isUpdating"
                    :items="people"
                    filled
                    chips
                    closable-chips
                    color="blue-grey-lighten-2"
                    label="초대"
                    item-title="name"
                    item-value="id"
                    multiple
                  >
                    <template v-slot:chip="{ props, item }">
                      <v-chip
                        v-bind="props"
                        :prepend-avatar="item.raw.avatar"
                        :text="item.raw.name"
                      ></v-chip>
                    </template>
                    <template v-slot:item="{ props, item }">
                      <v-list-item v-if="typeof item.raw !== 'object'" v-bind="props"></v-list-item>
                      <v-list-item
                        v-else
                        v-bind="props"
                        :prepend-avatar="item.raw.avatar"
                        :title="item.raw.name"
                        :subtitle="item.raw.group"
                      ></v-list-item>
                    </template>
                  </v-autocomplete>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              :loading="isUpdating"
              color="blue-grey-lighten-3"
              prepend-icon="mdi-update"
              @click="createChatRoom"
            >
              생성
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
import HomeButton from './home-button.vue'
import ServerButton from './server-button.vue'
import ServerButton3 from './server-button3.vue'
import $axios from 'axios'
export default {
  props: {
    chatRoomList: Array,
    activeChatRoom: Number,
    userInfo: Object
  },
  data () {
    const srcs = {
      1: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
      2: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
      3: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
      4: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
      5: 'https://cdn.vuetifyjs.com/images/lists/5.jpg'
    }
    return {
      autoUpdate: true,
      isUpdating: false,
      dialog: false,
      title: '코덕',
      discribe: '코딩을 좋아하는 방',
      inviteList: [],
      imageLink: 'https://cdn.vuetifyjs.com/images/cards/dark-beach.jpg',
      people: [
        { header: 'Group 1' },
        { name: '유동준', group: 'Google', avatar: srcs[1], id: 1 },
        { name: '정기준', group: 'GitHub', avatar: srcs[2], id: 3 },
        { name: '앨런 쿠퍼', group: 'Naver', avatar: srcs[3], id: 4 },
        { name: 'FanaTic', group: 'Naver', avatar: srcs[2], id: 5 },
        { divider: true },
        { header: 'Group 2' },
        { name: '윤정초이', group: 'GitHub', avatar: srcs[4], id: 6 },
        { name: '기준 정', group: 'Google', avatar: srcs[5], id: 7 }
      ]
    }
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
    },
    selectFile (event) {
      console.log('target[0] : ', event.target.files[0])
      console.log('imageLink : ', this.imageLink)
      this.imageLink = URL.createObjectURL(event.target.files[0])
    },
    // 채팅방 생성
    createChatRoom: function () {
      console.log('createChatRoom')
      console.log('title : ', this.title)
      console.log('id : ', this.userInfo.id)
      if (this.title == null) {
        alert('필수값 누락')
        return
      }
      if (this.userInfo.id == null) {
        alert('로그인 하세요')
        return
      }
      // const vm = this
      const data = {
        title: this.title,
        discribe: this.discribe,
        userid: this.userInfo.id,
        inviteList: this.inviteList
      }
      console.log(data)
      const vm = this
      $axios
        .post('/api/chat/room', data)
        .then(function (response) {
          alert('CREATE SUCESS')
          vm.dialog = false
          console.log('생성된 방번호(data) : ', response.data)
          console.log('data type : ', typeof response.data)
          vm.changeRoom(response.data)
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    outRoom () {
      if (confirm('정말 방에서 나가시겠습니까?')) {
        $axios
          .delete('/api/chat/room/' + this.activeChatRoom + '/' + this.userInfo.id)
          .then(response => {
          // console.log(' 삭제결과 : ', response.data)
            alert(response.data)
            this.$store.dispatch('module1/getChatRoomList', this.userInfo.id)
          })
          .catch(error => {
            console.log(' DELETE FAIL : ', error)
          })
      }
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
.add-button {
  display: flex;
  flex-shrink: 0;
  align-items: center;
  justify-content: center;
  width: 48px ;
  height: 48px ;
  margin-bottom: 8px;
  border-radius: 50%;
  background-color: var(--primary);
  cursor: pointer;
  position: relative;
  transition: 0.2s;
  &.active,
  &:hover {
    border-radius: 16px;
    background-color: var(--discord);
  }
}
.v-btn--size-default {
  min-width: 48px !important;
}
.separator {
  width: 32px;
  margin-bottom: 8px;
  border-bottom: solid 2px var(--grey);
}
</style>
