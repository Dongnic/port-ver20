<template>
  <div class="container">
    <div class="title">{{ chatRoomName }}</div>
  </div>
</template>

<script>
export default {
  props: {
    chatRoomName: String
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
