<template>
  <div class="container">
    <div class="profile">
      <div class="avatar" :style="{ 'background-Image' : 'url(' + userInfo.profileimage + ')' }">
        <v-badge
        bordered
        dot
        bottom
        color="green"
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
      <Settings :size="20" />
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
    userInfo: String
  },
  components: {
    // Mic,
    // HeadPhones,
    Settings
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
</style>
