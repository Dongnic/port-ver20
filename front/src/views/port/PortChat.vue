<template>
  <!-- layout  -->
  <div id="grid">
    <ServerList :chatRoomList="chatRoomList" :activeChatRoom="activeChatRoom" @changeRoom="changeRoom"/>
    <ServerName :chatRoomName="chatRoomInfo.title" />
    <ChannelList />
    <ChannelInfo channelName="CRB's Channel" />
    <ChannelData :userInfo="userInfo" :activeChatRoom="activeChatRoom" :chatRoomInfo="chatRoomInfo" />
    <UserInfo :userInfo="userInfo" />
    <UserList :onlineUserList="onlineUserList" :offlineUserList="offlineUserList" />
    <!-- <p>----------------------------------
    jwtToken, userInfo, userList, chatRoomList, activeChatRoom, activeChannel
    ----------------------------------</p>
    <h6>
      <br/>-------jwtToken -------
      <br/>{{jwtToken}}
      <br/>-------userInfo -------
      <br/>{{userInfo}}
      <br/>-------userList -------
      <br/>{{userList}}
      <br/>-------chatRoomList -------
      <br/>{{chatRoomList}}
      <br/>-------activeChatRoom -------
      <br/>{{activeChatRoom}}
      <br/>-------activeChannel -------
      <br/>{{activeChannel}}
    </h6> -->
  </div>
</template>

<script>
// Componenets
import ServerList from '@/components/server-list/server-list'
import ServerName from '@/components/server-name/server-name'
import ChannelList from '@/components/channel-list/channel-list'
import UserInfo from '@/components/user-info/user-info'
import ChannelInfo from '@/components/channel-info/channel-info'
import ChannelData from '@/components/channel-data/channel-data'
import UserList from '@/components/user-list/user-list'
import { useStore } from 'vuex'
import { computed } from 'vue'
import { useRoute } from 'vue-router'

export default {
  name: 'PortChat',
  setup () {
    console.log('================= PortChat setup() ==================')
    const store = useStore()
    const route = useRoute()

    const setToken = () => store.dispatch('module1/changeJwtToken', route.query.token)
    setToken()
    // const getUserInfo = () => store.dispatch('module1/getUserInfo')
    // getUserInfo()
    // const getUserList = () => store.dispatch('module1/getUserList')
    // getUserList()
    // const getChatRoomList = () => store.dispatch('module1/getChatRoomList', userInfo.value.id)
    // getChatRoomList()
    const getChatRoomInfo = () => store.dispatch('module1/getChatRoomInfo')
    // getChatRoomInfo()

    const jwtToken = computed(() => store.getters['module1/getJwtToken'])
    const userInfo = computed(() => store.getters['module1/getUserInfo'])
    const onlineUserList = computed(() => store.getters['module1/getOnlineUserList'])
    const offlineUserList = computed(() => store.getters['module1/getOfflineUserList'])
    const chatRoomList = computed(() => store.getters['module1/getChatRoomList'])
    const chatRoomInfo = computed(() => store.getters['module1/getChatRoomInfo'])
    const activeChatRoom = computed(() => store.getters['module1/getActiveChatRoom'])
    const activeChannel = computed(() => store.getters['module1/getActiveChannel'])

    console.log('jwtToken : ', jwtToken)
    console.log('userInfo : ', userInfo)
    console.log('onlineUserList : ', onlineUserList)
    console.log('offlineUserList : ', offlineUserList)
    console.log('chatRoomList : ', chatRoomList)
    console.log('chatRoomInfo : ', chatRoomInfo)
    console.log('activeChatRoom : ', activeChatRoom)
    console.log('activeChannel : ', activeChannel)
    return {
      jwtToken,
      userInfo,
      chatRoomList,
      chatRoomInfo,
      activeChatRoom,
      activeChannel,
      onlineUserList,
      offlineUserList,
      getChatRoomInfo
    }
  },
  mounted () {
    console.log('=========mounted==========')
    console.log('= mounted = this.$route.query.token : ', this.$route.query.token)
  },
  components: {
    ServerList,
    ServerName,
    ChannelList,
    UserInfo,
    ChannelInfo,
    ChannelData,
    UserList
  },
  methods: {
    changeRoom (no) {
      console.log(' PortChat changeRoom ', no)
      this.$store.dispatch('module1/changeActiveChatRoom', no)
    }
  }
}
</script>

<style scoped>
#grid {
  display: grid;
  grid-template-columns: 71px 240px auto 240px;
  grid-template-rows: 46px auto 52px;
  grid-template-areas:
    "sl sn ci ci"
    "sl cl cd ul"
    "sl ui cd ul";

  height: 100vh;
}
</style>
