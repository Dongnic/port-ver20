<template>
  <div class="user-list">
    <div class="title-users">On-line {{ getOnlineUsersCount }}</div>
    <!-- 스크립트에서 정의한 컴포넌트 이름 사용
    -> v-for문으로 Userlist의 onlineUsers의 데이터를 가져온다. -->
    <UserRow
      v-for="user in onlineUserList"
      :key="user.id"
      :nickName="user.username"
      :isBot="user.isBot"
      :profileimage="user.profileimage"
      :isOnline="true"
    />

    <div class="title-users">Off-line {{ getOfflineUsersCount }}</div>
    <!-- 잠시 주석 -->
    <UserRow
      v-for="user in offlineUserList"
      :key="user.id"
      :nickName="user.username"
      :isBot="user.isBot"
      :profileimage="user.profileimage"
      :isOnline="false"
    />
  </div>
</template>

<script>
import UserRow from './user-row'

export default {
  // 상위 compo로부터 Userlist를 Object(객체)(키:값) 형식으로 데이터를 받는다.
  props: {
    onlineUserList: Object,
    offlineUserList: Object
  },
  // 현재페이지에서 쓸 컴포넌트 정의
  components: {
    UserRow
  },
  // 어떤 data를 template안에 {{}} 표현식으로 가공해서 표현해야할 때 연산이
  // 너무 길어지거나 복잡해지면 유지보수하기가 힘들고 가독성도 떨어지게 된다.
  // 그런 복잡한 식이 될 경우 computed 속성을 사용하면 간결하게 표현할 수 있음.
  // 만약 계산이 필요한 경우 methods로도 쓸 수 있지만 computed로 쓰는게 나음.
  // computed는 데이터에 종속되기 때문에 그 데이터가 변경하지 않는 한 그 전의
  // 결과값을 캐싱해서 저장해둔다. 데이터가 변하지 않는다면 캐싱된 값을 보여준다.
  // 그러나 계산식을 method로 만든다면 method가 호출될 때마다 → 함수를 실행 →
  // → 굳이 데이터가 변하지도 않는데 똑같은 함수실행을 계속할 필요는 없으므로
  // using 'computed' is better!!! computed -> getter에 가까움
  computed: {
    getOnlineUsersCount () {
      return this.onlineUserList.length
    },
    getOfflineUsersCount () {
      return this.offlineUserList.length
    }
  }
}
</script>

<style lang="scss" scoped>
.user-list {
  grid-area: ul;

  display: flex;
  flex-direction: column;

  padding: 3px 6px 10px 16px;
  background-color: var(--secondary);
  max-height: calc(100vh - 46px);
  overflow-y: scroll;

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
.title-users {
  margin-top: 20px;
  color: var(--grey);
  font-weight: 500;
  text-transform: uppercase;
}

</style>
