<template>
    <div id="editor"></div>
</template>
<script>
import Quill from 'quill'
export default {
  setup () {
    const toolbarOptions = [['bold', 'italic', 'underline', 'strike']]
    setTimeout(() => {
      const bindings = {
        enter: {
          key: 'enter',
          handler: function () {
            console.log('enter')
          }
        }
      }
      const quill = new Quill('#editor', {
        modules: {
          toolbar: toolbarOptions,
          keyboard: {
            bindings: bindings
          }
        },
        placeholder: 'Write your note',
        theme: 'snow'
      })
      return {
        quill,
        toolbarOptions,
        bindings
      }
    }, 100)
  }
}
</script>
