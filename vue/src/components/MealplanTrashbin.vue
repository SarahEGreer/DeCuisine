<template>
    <div class="trash-bin" @drop="handleDrop" @dragover.prevent>
      🗑️ Drag here to remove
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      handleDrop(event) {
        const eventId = event.dataTransfer.getData('text');
        this.$emit('dropEvent', eventId);
      },
    },
  };
  </script>
  
  <style scoped>
  .trash-bin {
    position: fixed;
    bottom: 20px;
    right: 20px;
    width: 100px;
    height: 100px;
    background-color: red;
    color: white;
    text-align: center;
    line-height: 100px;
    font-size: 24px;
    border-radius: 50%;
  }
  </style>