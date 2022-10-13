<template>
  <div class="container">
    <!-- ServerButton은 채널 버튼
         isHome = 홈 로고
         hasNotifications = 알림유무
         mentions = 읽지 않은 메세지 갯수
         selected = 현재 선택한 채널
    -->
    <HomeButton  />
    <div class="separator"></div>
    <div id="server">
      <ServerButton selected hasNotifications :mentions="3" />
      <ServerButton :mentions="9" />
      <ServerButton hasNotifications :mentions="0" />
      <ServerButton hasNotifications :mentions="12" />
      <ServerButton />
      <div class="separator"></div>
      <server-button-3
        v-for="(chatRoom, index) in chatRoomList"
        :key="index"
        :no="chatRoom.id"
        :chatRoomInfo="chatRoom"
        :selected="chatRoom.id === activeChatRoom"
        @changeRoom="changeRoom"
      >
      </server-button-3>
              <v-btn
                fab
                color="cyan accent-2"
                bottom
                left
                absolute
                @click="dialog = !dialog"
              >
                <v-icon>mdi-plus</v-icon>
              </v-btn>

          <v-dialog
            v-model="dialog"
          >
           <v-card
      color="blue-grey darken-1"
      dark
      :loading="isUpdating"
    >
      <template v-slot:progress>
        <v-progress-linear
          absolute
          color="green lighten-3"
          height="4"
          indeterminate
        ></v-progress-linear>
      </template>
      <v-img
        height="200"
        src="https://cdn.vuetifyjs.com/images/cards/dark-beach.jpg"
      >
        <v-row>
          <v-col
            class="text-right"
            cols="12"
          >
            <v-menu
              bottom
              left
              transition="slide-y-transition"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  icon
                  v-bind="attrs"
                  v-on="on"
                >
                  <v-icon>mdi-dots-vertical</v-icon>
                </v-btn>
              </template>
              <v-list>
                <v-list-item @click="isUpdating = true">
                  <v-list-item-action>
                    <v-icon>mdi-cog</v-icon>
                  </v-list-item-action>
                  <v-list-item-content>
                    <v-list-item-title>Update</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>
          <v-row
            class="pa-4"
            align="center"
            justify="center"
          >
            <v-col class="text-center">
              <h3 class="text-h5">
                {{ name }}
              </h3>
              <span class="grey--text text--lighten-1">{{ title }}</span>
            </v-col>
          </v-row>
        </v-row>
      </v-img>
      <v-form>
        <v-container>
          <v-row>

            <v-col
              cols="12"
              md="6"
            >
              <v-text-field
                v-model="name"
                :disabled="isUpdating"
                filled
                color="blue-grey lighten-2"
                label="Name"
              ></v-text-field>
            </v-col>

            <v-col
              cols="12"
              md="6"
            >
              <v-text-field
                v-model="title"
                :disabled="isUpdating"
                filled
                color="blue-grey lighten-2"
                label="Title"
              ></v-text-field>
            </v-col>
            
            <v-col cols="12">

              <v-autocomplete
                v-model="friends"
                :disabled="isUpdating"
                :items="people"
                filled
                chips
                color="blue-grey lighten-2"
                label="Select"
                item-text="name"
                item-value="name"
                multiple
              >
                <template v-slot:selection="data">
                  <v-chip
                    v-bind="data.attrs"
                    :input-value="data.selected"
                    close
                    @click="data.select"
                    @click:close="remove(data.item)"
                  >
                    <v-avatar left>
                      <v-img :src="data.item.avatar"></v-img>
                    </v-avatar>
                    {{ data.item.name }}
                  </v-chip>
                </template>


                <template v-slot:item="data">
                  <template>
                    <v-list-item-avatar>
                      <img :src="data.item.avatar">
                    </v-list-item-avatar>
                    <v-list-item-content>
                      <v-list-item-title v-html="data.item.name"></v-list-item-title>
                      <v-list-item-subtitle v-html="data.item.group"></v-list-item-subtitle>
                    </v-list-item-content>
                  </template>
                </template>
              
              
              </v-autocomplete>
            </v-col>

          </v-row>
        </v-container>
      </v-form>
      <v-divider></v-divider>
      <v-card-actions>
        <v-switch
          v-model="autoUpdate"
          :disabled="isUpdating"
          class="mt-0"
          color="green lighten-2"
          hide-details
          label="Auto Update"
        ></v-switch>
        <v-spacer></v-spacer>
        <v-btn
          :disabled="autoUpdate"
          :loading="isUpdating"
          color="blue-grey darken-3"
          depressed
          @click="isUpdating = true"
        >
          <v-icon left>
            mdi-update
          </v-icon>
          Update Now
        </v-btn>
      </v-card-actions>
    </v-card>
          </v-dialog>
    </div>
  </div>
</template>

<script>
import HomeButton from './home-button.vue'
import ServerButton from './server-button.vue'
import ServerButton3 from './server-button3.vue'

export default {
  props: {
    chatRoomList: Array,
    activeChatRoom: Number
  },
  data () {
    const srcs = {
      1: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
      2: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
      3: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
      4: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
      5: 'https://cdn.vuetifyjs.com/images/lists/5.jpg'
    }
    return {
      autoUpdate: true,
      friends: ['Sandra Adams', 'Britta Holt'],
      isUpdating: false,
      name: 'Midnight Crew',
      people: [
        { header: 'Group 1' },
        { name: 'Sandra Adams', group: 'Group 1', avatar: srcs[1] },
        { name: 'Ali Connors', group: 'Group 1', avatar: srcs[2] },
        { name: 'Trevor Hansen', group: 'Group 1', avatar: srcs[3] },
        { name: 'Tucker Smith', group: 'Group 1', avatar: srcs[2] },
        { divider: true },
        { header: 'Group 2' },
        { name: 'Britta Holt', group: 'Group 2', avatar: srcs[4] },
        { name: 'Jane Smith ', group: 'Group 2', avatar: srcs[5] },
        { name: 'John Smith', group: 'Group 2', avatar: srcs[1] },
        { name: 'Sandra Williams', group: 'Group 2', avatar: srcs[3] }
      ],
      title: 'The summer breeze',
      dialog: false,
      isobject: 'object'
    }
  },
  components: {
    HomeButton,
    ServerButton,
    ServerButton3
  },
  watch: {
    activeChatRoom (newTab) {
      console.log('chatRoom: ' + newTab)
    }
  },
  methods: {
    changeRoom (no) {
      console.log(' list changeRoom ', no)
      this.$emit('changeRoom', no)
    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;

  grid-area: sl;
  background-color: var(--tertiary);

  padding: 11px 0;
  max-height: 100vh;
  overflow-y: scroll;

  &::-webkit-scrollbar {
    display: none;
  }
}

.separator {
  width: 32px;
  margin-bottom: 8px;
  border-bottom: solid 2px var(--grey);
}
</style>
