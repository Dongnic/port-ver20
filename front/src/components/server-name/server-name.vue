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
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { computed } from 'vue'
import ExpandIcon from 'vue-material-design-icons/ChevronDown'
export default {
  setup () {
    const store = useStore()
    const getUserList = () => store.dispatch('module1/getUserList')
    getUserList()
    const userList = computed(() => store.getters['module1/getUserList'])
    return { userList, getUserList }
  },
  props: {
    chatRoomInfo: Object,
    author: Number,
    userid: Number
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
    location: 'top'
  }),
  methods: {
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
  .settings:hover{
    background-color: var(--quinary);
  }
  </style>
