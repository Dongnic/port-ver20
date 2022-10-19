<template>
  <div class="list-container .user-role">
    <div class="category">
      <div>최근 DM</div>
    </div>
    <UserRow
      v-for="user in DMUserList"
      :key="user.id"
      :nickName="user.username"
      :profileimage="user.profileimage"
      :isOnline="true"
      @click="toDM(user.id)"
    />
  </div>
</template>

<script>
import UserRow from '@/components/user-list/user-row'
import { computed } from 'vue'
import { useStore } from 'vuex'
export default {
  setup () {
    const store = useStore()
    const dmUserList = computed(() => store.getters['module1/getDmUserList'])
    return { dmUserList }
  },
  components: {
    UserRow
  },
  data () {
    return {
      channelCount: 3,
      activeChannel: 1
    }
  },
  methods: {
    addChannel () {
      this.channelCount++
    },
    removeChannel () {
      if (this.channelCount > 1) this.channelCount--
      if (this.channelCount < this.activeChannel) this.activeChannel = this.channelCount
    },
    changeChannel (no) {
      this.activeChannel = no
    }
  }
}
</script>

<style lang="scss" scoped>
.list-container {
  grid-area: cl;
  display: flex;
  flex-direction: column;

  padding: 24px 10px 0 16px;
  background-color: var(--secondary);
  overflow-y: scroll;

  &::-webkit-scrollbar {
    width: 8px;
  }

  &::-webkit-scrollbar-thumb {
    background-color: var(--tertiary);
    border-radius: 4px;
  }

  &::-webkit-scrollbar-track {
    background-color: var(--secondary);
  }
}

.category {
  display: flex;
  align-items: center;
  justify-content: space-between;

  margin-bottom: 6px;
  color: var(--grey);
  font-size: 12px;
  font-weight: 500;
  text-transform: uppercase;

  .category-icon {
    color: var(--symbol);
    cursor: pointer;
    &:hover {
      color: var(--white);
      transition: all 0.2s;
    }
  }
}
.user-role {
  display: flex;
  margin-top: 5px;
  padding: 5px;
  display: flex;
  align-items: center;
  cursor: pointer;
  border-radius: 5px;
  background: transparent;
  transition: all ease-out 0.2s;

  &:hover {
    background-color: rgba(255, 255, 255, 0.1);
  }

  strong {
    margin-left: 13px;
    font-weight: 500;
    color: var(--white);
    opacity: 0.7;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
  }
  .avatar {
    width: 32px;
    height: 32px;
    flex-shrink: 0;
    background-color: var(--primary);
    border-radius: 50px;
    background-size: cover;
  }

}
</style>
