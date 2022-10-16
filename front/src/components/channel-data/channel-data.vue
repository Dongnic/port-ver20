<!--template : 사용자가 브라우저에서 볼 수 있는 형태의 html로 변환해주는 속성
  입니다!!!!!!!!!!!!!!!!!!-->
<template>
  <div class="container">
    <div class="messages" id="messages">
      <!-- 메세지를 1번 반복한다.
      key는 message, authorName은 작성자
      date는 날짜를 쓰기 위함. 지정하지 않으면 현재 시간
      my github버튼을 누르면 아래 링크로 이동-->
      <ChannelMessage
        v-for="message in 1"
        :key="message"
        authorName="채널 안내"
        :date="getDate"
      >
        Well come to my discord-clone. See this project in
        <a class="link" href="https://github.com/Dongnic"
          >my github</a
        >
      </ChannelMessage>
      <!--메세지 1번 반복, 작성자는 Bot-->
      <ChannelMessage
        isBot
        hasMention
        v-for="message in 1"
        :key="message"
        authorName="Bot"
        :date="getDate"
      >
        <Mention>{{userInfo.username}}</Mention> Hii, how are you ??
      </ChannelMessage>
      <ChannelMessage>{{userInfo}}</ChannelMessage>
      <!--message 배열만큼 반복, User 1의 작성자가
      현재 날짜로-->
      <ChannelMessage
        v-for="(msg, index) in messagesArray"
        :key="index"
        :authorName="msg.userid.username"
        :profileimage="msg.userid.profileimage"
        :date="msg.regdate"
        :isMe="msg.userid.id == userInfo.id"
      >
        {{ msg.message }}
      </ChannelMessage>
      <!-- 이미지 업로드 -->  <div class="information">
        <button @click="$refs.fileRef.click">선택</button>
        <!-- <v-btn
          class="add-button uplaod-button"
          fab
          dark
          color="cyan accent-2"
          @click="$refs.fileRef.click"
        >
          <v-icon>mdi-plus</v-icon>
        </v-btn> -->
          <input type="file" @change="selectFile" multiple accept="image/*" ref="fileRef" hidden/>
        </div>
        <div class="images" v-if="files.length > 0">
          <div v-for="fileName in files" :key="fileName" class="image">
            <img :src="`${backendUrl}/image/${fileName}`" alt="이미지">
          </div>
        </div>
    </div>
    <!-- text타입으로 message 작성input tag
    enter키를 누르면 작성한 메세지를 전송-->
    <div class="input-wrapper">
      <textarea
        name="message"
        v-model="content"
        placeholder="Type a message here, and press enter."
        id="input-message"
        @keypress.enter="sendMessage"
      />
      <!--아이콘-->
      <div class="icon">
        <At :size="24" />
      </div>
    </div>
  </div>
</template>

<!-- 변수를 정의해놓은 import-->
<script>
/* eslint eqeqeq: "off" */
import At from 'vue-material-design-icons/At'
// import { io } from 'socket.io-client'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
// Components
import ChannelMessage from './channel-message'
import Mention from './channel-mention'
import $axios from 'axios'
import { useStore } from 'vuex'
import { computed } from 'vue'

export default {
  name: 'channel-data',
  props: {
    activeChatRoom: Number
  },
  setup () {
    const store = useStore()
    const messagesArray = computed(() => store.getters['module1/getChatMessageList'])
    const chatRoomInfo = computed(() => store.getters['module1/getChatRoomInfo'])
    const userInfo = computed(() => store.getters['module1/getUserInfo'])
    const chatRoomList = computed(() => store.getters['module1/getChatRoomList'])
    return { messagesArray, chatRoomInfo, userInfo, chatRoomList }
  },
  components: {
    At,
    ChannelMessage,
    Mention
  },
  data () {
    return {
      message: '',
      date: '',
      content: '',
      mapOfStomp: [],
      isConnected: false,
      files: []
    }
  },
  mounted () {
    console.log('== channel-data created==')
    this.fetchFiles()
  },
  methods: {
    sendMessage (e) {
      if (!e.ctrlKey) {
        if (this.content.trim() != '' && this.stompClient != null) {
          const chatMessage = {
            message: this.content,
            chatroomid: this.chatRoomInfo,
            userid: this.userInfo
          }
          if (this.chatRoomInfo.id == this.activeChatRoom) {
            this.stompClient.send('/pub/message', JSON.stringify(chatMessage), {})
            this.content = ''
          } else console.log('방 번호가 일치하지 않습니다')
          e.preventDefault()
        }
      } else { this.content += '\r\n' }
    },
    async fetchFiles () {
      const response = await $axios.get(`${this.backendUrl}/files`)
      this.files = response.data
    },
    selectFile (event) {
      const formData = new FormData()
      for (const file of event.target.files) {
        formData.append('files', file)
      }
      $axios.post(`${this.backendUrl}/files`, formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      }).then(() => {
        this.fetchFiles()
      }).catch(error => {
        alert(error.message)
      })
    }
  },
  computed: {
    backendUrl () {
      return process.env.VUE_APP_BACKEND_URL
    }
  },
  watch: {
    messagesArray () {
      console.log('messagesArray change ')
      this.$nextTick(() => {
        const div = document.getElementById('messages')
        div.scrollTop = div.scrollHeight
      })
      if (this.messagesArray[this.messagesArray.length - 1] != undefined) {
        console.log('마지막 메세지 번호 뽑기 ', this.messagesArray[this.messagesArray.length - 1])
        const data = {
          userid: this.userInfo.id,
          chatroomid: this.activeChatRoom,
          chatmessageid: this.messagesArray[this.messagesArray.length - 1].id
        }
        console.log(' Change message data : ', data)
        $axios
          .put('/api/chat/chatuser', data)
          .then(function (response) {
            console.log(' update messageid : ', response.data)
          })
          .catch(function (error) {
            console.log('ERROR updateMessage : ', error)
          })
      }
    },
    activeChatRoom (newChatRoom) {
      console.log('chatRoomInfo : ', newChatRoom)
      console.log('this.activeChatRoom : ', this.activeChatRoom)
      if (this.isConnected) {
        this.stompClient.disconnect(() => {
          if (this.mapOfStomp.size > 0) {
            this.stompClient.unsubscribe('/sub/' + newChatRoom)
          }
          this.isConnected = false
        })
      }
      const socket = new SockJS('http://192.168.0.20:8080/ws')
      this.stompClient = Stomp.over(socket)
      this.stompClient.connect({},
        frame => {
          console.log('success', frame)
          this.isConnected = true
          const chatMessage = {
            message: this.content,
            chatroomid: { id: this.activeChatRoom },
            userid: { id: 0 }
          }
          this.stompClient.send('/pub/message', JSON.stringify(chatMessage), {})
          console.log('chatRoomInfo.id : ', this.activeChatRoom)
          console.log('chatRoomList : ', this.chatRoomList)
          this.chatRoomList.forEach((cRoom, index) => {
            console.log(' cRoom.idcRoom.id : ', cRoom.id)
            console.log(' index index : ', index)
            this.stompClient.subscribe('/sub/' + cRoom.id, res => {
              const jsonBody = JSON.parse(res.body)
              console.log('jsonBody : ', jsonBody)
              if (jsonBody.chatroomid.id == this.chatRoomInfo.id) {
                if (jsonBody.userid.id == 0) {
                  this.$store.dispatch('module1/getOnlineUserList', this.chatRoomInfo.id)
                  this.$store.dispatch('module1/getOfflineUserList', this.chatRoomInfo.id)
                } else {
                  const msg = {
                    id: jsonBody.id,
                    userid: jsonBody.userid,
                    chatroomid: jsonBody.chatroomid,
                    message: jsonBody.message,
                    regdate: jsonBody.regdate,
                    isMe: jsonBody.userid.id == this.userInfo.id
                  }
                  this.$store.dispatch('module1/addChatMessage', msg)
                  // this.messagesArray.push(msg)
                  this.$nextTick(() => {
                    const div = document.getElementById('messages')
                    div.scrollTop = div.scrollHeight
                  })
                }
              } else this.$store.dispatch('module1/getChatRoomList', this.userInfo.id)
            }, { userid: this.userInfo.id })
            this.stompClient.send('/pub/message', JSON.stringify(chatMessage), {})
          })
        },
        err => { console.log('FAIL : ', err) })
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  grid-area: cd;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  flex-flow: column nowrap;
  background-color: var(--primary);
  flex: 1;
}

.input-wrapper {
  width: 100%;
  padding: 0 16px;
  height: 68px;

  // input,
  textarea {
    margin-top: 12px;
    width: 100%;
    height: 44px;
    padding: 0 10px 0 57px;
    border-radius: 5px;
    color: var(--white);
    background-color: var(--chat-input);

    &::placeholder {
      color: var(--grey);
      font-size: 16px;
    }
  }

  .icon {
    color: var(--grey);
    position: relative;
    top: -50%;
    left: 14px;
    transition: 0.2s;
    width: 24px;
  }
}

.messages {
  display: flex;
  flex-direction: column;
  height: calc(100vh - 46px - 68px);
  max-height: calc(100vh - 46px - 68px);
  overflow-y: scroll;

  .channelmessage:first-child {
    margin-top: 0;
  }

  &::-webkit-scrollbar {
    width: 8px;
  }

  &::-webkit-scrollbar-thumb {
    background-color: var(--tertiary);
    border-radius: 4px;
  }

  &::-webkit-scrollbar-track {
    background-color: var(--primary);
  }
}

.link {
  color: var(--grey);
  outline: none;
}
</style>
