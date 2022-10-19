<template>
  <div id="grid">
    <ServerList :chatRoomList="chatRoomList" :activeChatRoom="activeChatRoom" @changeRoom="changeRoom" :userInfo="userInfo"/>
    <ServerName v-show="activeChatRoom > 0" :author="chatRoomInfo.userid" :userid="userInfo.id"/>
    <ServerName2 v-show="activeChatRoom === 0" chatRoomName="다이렉트 메세지" />
    <ChannelList v-show="activeChatRoom > 0" />
    <ChannelList2 v-show="activeChatRoom === 0" />
    <ChannelInfo v-show="activeChatRoom" :chatRoomInfo="chatRoomInfo" />
    <ChannelData v-show="activeChatRoom" :userInfo="userInfo" :activeChatRoom="activeChatRoom" :chatRoomInfo="chatRoomInfo" />
    <ChannelData v-show="activeChatRoom === 0" :userInfo="userInfo" :activeChatRoom="activeChatRoom" :chatRoomInfo="dmInfo" />
    <UserInfo :userInfo="userInfo" />
    <UserList v-show="activeChatRoom" :onlineUserList="onlineUserList" :offlineUserList="offlineUserList" :userInfo="userInfo" @changeRoom="changeRoom" />
  </div>
</template>

<script>
// Componenets
import ServerList from '@/components/server-list/server-list'
import ServerName from '@/components/server-name/server-name'
import ServerName2 from '@/components/server-name/server-name2'
import ChannelList from '@/components/channel-list/channel-list'
import ChannelList2 from '@/components/channel-list/channel-list2'
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
    const getChatRoomInfo = () => store.dispatch('module1/getChatRoomInfo')

    const jwtToken = computed(() => store.getters['module1/getJwtToken'])
    const userInfo = computed(() => store.getters['module1/getUserInfo'])
    const onlineUserList = computed(() => store.getters['module1/getOnlineUserList'])
    const offlineUserList = computed(() => store.getters['module1/getOfflineUserList'])
    const dmUserList = computed(() => store.getters['module1/getDmUserList'])
    const chatRoomList = computed(() => store.getters['module1/getChatRoomList'])
    const chatRoomInfo = computed(() => store.getters['module1/getChatRoomInfo'])
    const dmInfo = computed(() => store.getters['module1/getDmInfo'])
    const activeChatRoom = computed(() => store.getters['module1/getActiveChatRoom'])
    const activeChannel = computed(() => store.getters['module1/getActiveChannel'])

    console.log('jwtToken : ', jwtToken)
    console.log('userInfo : ', userInfo)
    console.log('onlineUserList : ', onlineUserList)
    console.log('offlineUserList : ', offlineUserList)
    console.log('dmUserList : ', dmUserList)
    console.log('chatRoomList : ', chatRoomList)
    console.log('chatRoomInfo : ', chatRoomInfo)
    console.log('dmInfo : ', dmInfo)
    console.log('activeChatRoom : ', activeChatRoom)
    console.log('activeChannel : ', activeChannel)
    return {
      jwtToken,
      userInfo,
      chatRoomList,
      chatRoomInfo,
      dmInfo,
      activeChatRoom,
      activeChannel,
      onlineUserList,
      offlineUserList,
      dmUserList,
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
    ServerName2,
    ChannelList,
    ChannelList2,
    UserInfo,
    ChannelInfo,
    ChannelData,
    UserList
  },
  methods: {
    changeRoom (no) {
      console.log(' PortChat changeRoom ', no)
      this.$store.dispatch('module1/changeActiveChatRoom', no)
      this.$store.dispatch('module1/getChatRoomList', this.userInfo.id)
      if (no === 0) this.$store.dispatch('module1/getDmUserList', this.userInfo.id)
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
