<template>
  <div class="container">
    <div class="title">{{ chatRoomInfo.title }}</div>
    <div class="icon">
      <v-menu :location="location">
        <template v-slot:activator="{ props }">
          <ExpandIcon :size="28" v-bind="props" />
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
    <!-- 유저초대 모달창 -->
    <v-dialog
        v-model="inviteDialog"
      >
        <!-- 전체 감싸는 카드  -->
      <v-card
          color="blue-green-darken-2"
          dark
          :loading="isUpdating"
        >
          <template v-slot:progress>
            <v-progress-linear
              absolute
              color="black-lighten-4"
              height="4"
              indeterminate
            ></v-progress-linear>
          </template>
          <v-form>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-autocomplete
                    v-model="inviteList"
                    :disabled="isUpdating"
                    :items="otheruserList"
                    filled
                    chips
                    closable-chips
                    clearable
                    color="black-grey-lighten-4"
                    label="초대"
                    item-title="name"
                    item-value="id"
                    multiple
                  >
                    <template v-slot:chip="{ props, item }">
                      <v-chip
                        v-bind="props"
                        :prepend-avatar="item.raw.profileimage"
                        :text="item.raw.username"
                      ></v-chip>
                    </template>
                    <template v-slot:item="{ props, item }">
                      <v-list-item v-if="typeof item.raw !== 'object'" v-bind="props"></v-list-item>
                      <v-list-item
                        v-else
                        v-bind="props"
                        :prepend-avatar="item.raw.profileimage"
                        :title="item.raw.username"
                        :subtitle="item.raw.provider"
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
              @click="inviteChatRoom"
              class="crateButton"
            >
              초대
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <!-- 방 설정 변경 모달창 -->
       <!-- 방 생성 모달창 -->
       <v-dialog
        v-model="settingDialog"
      >
        <!-- 전체 감싸는 카드  -->
      <v-card
          color="blue-black-darken-1"
          dark
          :loading="isUpdating"
        >
          <template v-slot:progress>
            <v-progress-linear
              absolute
              color="black-lighten-3"
              height="4"
              indeterminate
            ></v-progress-linear>
          </template>
          <br><div class="createTitle"><h3>방 설정</h3></div><br>
          <input type="file" @change="selectFile" accept="image/*" ref="fileRef" hidden/>
          <div class="images" v-if="imageLink.length > 0">
            <div v-for="(imglink, index) in imageLink" :key="index" class="image imgdiv">
              <img v-if="filedatas.length > 0" :src="imglink" class="titleImageBox" @click="$refs.fileRef.click" />
              <img v-else class="titleImageBox" :src="`${backendUrl}/image/${chatRoomInfo.roomimage}`" @click="$refs.fileRef.click" alt="이미지"/>
            </div>
          </div>
          <button v-else @click="$refs.fileRef.click" type="button">선택</button>
          <!-- <img style="{'height':'200px', 'background-size': 'cover'}" :src="imgLink"> -->
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
                  <h4 class="text-h5">
                    {{ title }}
                  </h4>
                  <span class="text-grey-lighten-1">{{ discribe }}</span>
                </v-col>
              </v-row>
            </v-row>
          <!-- </v-img> -->
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
                    placeholder="방 이름을 입력해주세요"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" md="6">
                  <v-text-field
                    v-model="discribe"
                    :disabled="isUpdating"
                    filled
                    color="blue-grey-lighten-2"
                    label="방 설명"
                    placeholder="방 설명을 입력해주세요"
                  ></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-autocomplete
                    v-model="deleteList"
                    :disabled="isUpdating"
                    :items="userList"
                    filled
                    chips
                    closable-chips
                    clearable
                    color="black-grey-lighten-2"
                    label="추방"
                    item-title="name"
                    item-value="id"
                    multiple
                  >
                    <template v-slot:chip="{ props, item }">
                      <v-chip
                        v-bind="props"
                        :prepend-avatar="item.raw.profileimage"
                        :text="item.raw.username"
                      ></v-chip>
                    </template>
                    <template v-slot:item="{ props, item }">
                      <v-list-item v-if="typeof item.raw !== 'object'" v-bind="props"></v-list-item>
                      <v-list-item
                        v-else
                        v-bind="props"
                        :prepend-avatar="item.raw.profileimage"
                        :title="item.raw.username"
                        :subtitle="item.raw.provider"
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
              class="crateButton"
            >
              변경
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { computed } from 'vue'
import ExpandIcon from 'vue-material-design-icons/ChevronDown'
import $axios from 'axios'
export default {
  setup () {
    const store = useStore()
    const otheruserList = computed(() => store.getters['module1/getOtherUserList'])
    const onlineuserList = computed(() => store.getters['module1/getOnlineUserList'])
    const offlineuserList = computed(() => store.getters['module1/getOfflineUserList'])
    const chatRoomInfo = computed(() => store.getters['module1/getChatRoomInfo'])
    return { otheruserList, offlineuserList, onlineuserList, chatRoomInfo }
  },
  props: {
    author: Number,
    userid: Number
  },
  mounted () {
    if (this.chatRoomInfo.id > 0) { this.$store.dispatch('module1/getOtherUserList', this.chatRoomInfo.id) }
  },
  watch: {
    inviteDialog () {
      this.$store.dispatch('module1/getOtherUserList', this.chatRoomInfo.id)
    }
  },
  components: {
    ExpandIcon
  },
  data: () => ({
    items: [
      { title: '유저 초대' },
      { title: '방 설정' },
      { title: '방 나가기' }
    ],
    location: 'top',
    inviteDialog: false,
    settingDialog: false,
    inviteList: [],
    deleteList: [],
    imageLink: ['i'],
    filedatas: []
  }),
  computed: {
    backendUrl () {
      return process.env.VUE_APP_BACKEND_URL
    },
    websocketUrl () {
      return process.env.VUE_APP_WEBSOCKET_URL
    },
    userList () {
      return [...this.offlineuserList, ...this.onlineuserList]
    },
    title () {
      return this.chatRoomInfo.title
    },
    discribe () {
      return this.chatRoomInfo.discribe
    }
  },
  methods: {
    change (index) {
      // 유저 초대
      if (index === 0) {
        this.inviteDialog = !this.inviteDialog
        // 방 설정
      } else if (index === 1) {
        this.settingDialog = !this.settingDialog
      // 방 나가기
      } else if (index === 2) {
        if (confirm('정말 방에서 나가시겠습니까?')) {
          $axios
            .delete('/api/chat/room/' + this.chatRoomInfo.id + '/' + this.userInfo.id)
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
    },
    selectFile (event) {
      this.imageLink = []
      this.filedatas = []
      for (const file of event.target.files) {
        const url = URL.createObjectURL(file)
        this.imageLink.push(url)
        this.filedatas.push('files', file)
      }
      console.log('imageLink : ', this.imageLink)
    },
    inviteChatRoom () {
      console.log('inviteChatRoom')
      const data = {
        chatroomid: this.chatRoomInfo,
        inviteList: this.inviteList
      }
      console.log(data)
      $axios
        .post('/api/chat/room/invite', data)
        .then(response => {
          alert(response.data)
          this.$store.dispatch('module1/getOnlineUserList', this.chatRoomInfo.id)
          this.$store.dispatch('module1/getOfflineUserList', this.chatRoomInfo.id)
          this.inviteDialog = false
        })
        .catch(error => {
          console.log(' err : ', error)
        })
    }
  }
}
</script>

<style scoped>
  .container {
    grid-area: sn;
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: var(--quaternary);
    padding: 0 11px 0 16px;
    box-shadow: rgba(0, 0, 0, 0.9) 0 1px 0 0;
  }
  .title {
    color: var(--white);
    font-weight: bold;
    font-size: 16px;
    letter-spacing: 2px;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  .icon {
    color: var(--white);
    cursor: pointer;
  }
  .titleImageBox{
  width: 400px;
  height: 400px;
  border-radius: 50%;
  background-size: cover;
}
.imgdiv, .createTitle{
  text-align: center;
}
  .settings:hover{
    background-color: var(--quinary);
  }
  .v-dialog{
  max-height: calc(70% - 48px);
  width: calc(70% - 48px);
  max-width: calc(1000px);
}
.v-card{
  border-radius: 20px;
}
.crateButton{
  background: var(--discord);
  margin-right: 15px;
}
  </style>
