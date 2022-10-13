<template>
  <div class="user-role">
    <!-- v-bind:class
        클래스명을 값에 따라 그때 그때 다르게 적용할 때 사용.
        v-bind:class="{ condition(조건식) }으로 사용"
        {적용될 클래스명 : true or false의 조건식}
        결론 = isBot이 트루이면 클래스명이 botAvatar이므로
        그에 맞는 css적용.-->
    <div class="avatar" :class="{ botAvatar: isBot }" :style="{ 'background-Image' : 'url(' + profileimage + ')' }">
      <v-badge
        bordered
        dot
        bottom
        :color="isOnline ? 'green' : 'red'"
        offset-x="-31"
        offset-y="10"
        overlap
      ></v-badge>
    </div>
    <!-- String타입의 닉네임 출력 -->
    <strong>{{ nickName }}</strong>
    <!-- v-if로 isBot이 true이면 bot출력. <-------이란 뜻인가요??????? -->
    <div class="bot" v-if="isBot">bot</div>
  </div>
</template>

<script>
// 상위 compo로 부터 String타입의 닉네임과 isBot값을 받음.
export default {
  props: {
    isBot: Boolean,
    nickName: String,
    profileimage: String,
    isOnline: Boolean
  }
}
</script>

<style lang="scss" scoped>
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

  .bot {
    margin-left: 9px;
    background-color: var(--discord);
    color: var(--white);
    padding: 4px 5px;
    border-radius: 4px;
    text-transform: uppercase;
    font-size: 11px;
    font-weight: bold;
  }

  .avatar {
    width: 32px;
    height: 32px;
    flex-shrink: 0;
    background-color: var(--primary);
    border-radius: 50px;
    background-size: cover;
  }

  .botAvatar {
    background-color: var(--mention-detail);
  }
}
</style>
