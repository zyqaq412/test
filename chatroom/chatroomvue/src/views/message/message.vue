<template>

  <div class="chatroom">
    <el-row>
      <el-col :span="6">
        <el-card class="chatroom-users">
          <div class="chatroom-users-header">在线用户</div>
          <div class="chatroom-users-body">
            <div class="chatroom-user" v-for="user in users" :key="user.id" @click="sengById(user)">{{user.username}}</div>
          </div>
        </el-card>
        <el-card class="chatroom-users">
          <div class="chatroom-users-header" >公告</div>
          <div id="gg">
<!--            <vue-markdown :source="notice.time" :options="md"></vue-markdown>-->
            <vue-markdown :source="notice.gg" :options="md"></vue-markdown>
          </div>
        </el-card>
        <el-card class="chatroom-users">
          <div class="chatroom-users-header">输入预览</div>
          <div>
            <vue-markdown :source="tomsg.messageInput" :options="md"></vue-markdown>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card class="chatroom-message">
          <div class="chatroom-message-header">{{ roomName }}</div>
          <div class="chatroom-message-body" id="message-box">
            <div class="chatroom-message-item" v-for="message in messages" :key="message.id">
              <div class="chatroom-message-sender">{{ message.sender }}</div>
              <div class="chatroom-message-time">{{ message.time }}</div>
<!--              <div class="chatroom-message-text">{{ message.text }}</div>-->
              <div class="chatroom-message-text">
                <vue-markdown :source="message.text" :options="md"></vue-markdown>
              </div>

            </div>
          </div>
        </el-card>
        <div class="chatroom-input">
<!--          <el-input v-model="tomsg.messageInput" placeholder="请输入消息" @keyup.enter.native="sendMessage"/>
          <el-button type="primary" @click="sendMessage">发送</el-button>-->
          <textarea v-model="tomsg.messageInput" @keydown.tab.prevent="insertTab"></textarea>
          <el-button type="primary" @click="sendMessage">发送</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

window.onbeforeunload = function (e) {
  return e;
};

import MarkdownIt from 'markdown-it'
import VueMarkdown from 'vue-markdown'
export default {

  components: {
    VueMarkdown
  },
  data() {

    return {
      notice:{
        gg:"",
        time:""
      },
      tomsg: {
        user:{
          username:'',
          id:''
        },
        messageInput: ''
      },
      sendMsg: {
        code: "",
        id: "",
        msg: "",
        data: {}
      },
      id: "",
      roomName: "聊天室",
      messages: [
        /*{ id: 1, sender: "张三", time: "10:30", text: "大家好啊！" },
        { id: 2, sender: "李四", time: "10:31", text: "你好，很高兴见到你！" },
        { id: 3, sender: "王五", time: "10:32", text: "我也很高兴见到你们！" },*/
      ],
      users: [
        {
          id:'',username:''
        }
      ],
      md:new MarkdownIt({
        html: true,
        highlight: function (str, lang) {
          if (lang && hljs.getLanguage(lang)) {
            try {
              return '<pre class="hljs"><code>' +
                  hljs.highlight(lang, str, true).value +
                  '</code></pre>';
            } catch (__) {}
          }
          return '<pre class="hljs"><code>' + md.utils.escapeHtml(str) + '</code></pre>';
        }
      })
    };
  },
  created() {
    this.tomsg.user = this.$store.state.user

/*    this.websocket = new WebSocket('ws://localhost:8080/chatroom?username='
        + this.tomsg.user.username+"&id="+this.tomsg.user.id);*/
    this.websocket = new WebSocket('ws://47.113.146.226:8080/chatroom?username='
        + this.tomsg.user.username+"&id="+this.tomsg.user.id);
    // 监听 WebSocket 连接打开事件
    this.websocket.addEventListener('open', this.onOpen);
    // 监听 WebSocket 消息事件
    this.websocket.addEventListener('message', this.onMessage);
    // 监听 WebSocket 连接关闭事件
    this.websocket.addEventListener('close', this.onClose);
    // 监听 WebSocket 连接错误事件
    this.websocket.addEventListener('error', this.onError);
  },
  mounted() {

  },
  methods: {
    insertTab(event) {
      const input = event.target;

      const start = input.selectionStart;
      const end = input.selectionEnd;

      input.value = input.value.substring(0, start) + '\t' + input.value.substring(end);

      input.selectionStart = input.selectionEnd = start + 1;
    },
    sengById(user){
      alert("id："+user.id+"\t用户名："+user.username)
    },
    onOpen(event) {
      console.log('WebSocket 连接已打开', event);
    },
    onMessage(event) {
      console.log('收到 WebSocket 消息', event);
      const temp = JSON.parse(event.data)
      console.log('temp', temp);
      console.log('code', temp.code);
      if (temp.code == 401) {
        this.$message.error('登录过时请重新登录');
        this.$router.push('/login');
        return
      } else if (temp.code === 1) {
        console.log('返回id');
        this.id = temp.data
      } else if (temp.code === 2) {
        console.log('消息');
        const newMessage = temp.data;
        newMessage.id = this.messages.length + 1;
        this.messages.push(newMessage)

      } else if (temp.code === 0) {
        console.log('list', temp.data);
        this.messages = temp.data
      } else if (temp.code === 3) {
        this.users = temp.data
      } else if (temp.code === 4){
        this.notice = temp.data
      }

      if (temp.id == this.id) {

        setTimeout(() => {
          this.moveHuaLun()
        }, 20);

      }

    },
    onClose(event) {
      console.log('WebSocket 连接已关闭', event);
      this.$message.error('WebSocket 连接已关闭');
      this.$router.push('/login');
    },
    onError(event) {
      console.error('WebSocket 连接错误', event);
    },
    sendMessage() {
      if (this.tomsg.messageInput.trim() === "") {
        return;
      }
      this.sendMsg.code = 2;
      this.sendMsg.data = this.tomsg;
      // 发送消息到 WebSocket 服务器
      this.websocket.send(JSON.stringify(this.sendMsg));
      this.tomsg.messageInput = "";
    },
    moveHuaLun() {
      //获取消息框元素
      const messageBox = document.getElementById("message-box");
      if (messageBox) {
        //将滚动条滚动到消息框底部
        messageBox.scrollTop = messageBox.scrollHeight;
      } else {
        console.error("Element with ID 'message-box' not found.");
      }
    }

  },
  beforeUnmount() {
    // 关闭 WebSocket 连接
    this.websocket.close();
  },
};
</script>

<style>
#gg{
  height: 100px;
}
textarea {
  width: 1200px;
  height: 130px;
  resize: none; /* 禁止拖动 */
}
.chatroom {
  margin-left: 100px;
  margin-right: 100px;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.chatroom-message {
  height: 450px;
  margin-bottom: 10px;
}

.chatroom-message-header {

  font-weight: bold;
  margin-bottom: 5px;
  font-size: 18px;
}

.chatroom-message-body {
  max-height: 360px; /* 最大高度为400像素 */
  overflow-y: auto; /* 显示垂直滚动条，只有在内容溢出时才显示 */
  height: calc(100% - 30px);
  overflow-y: auto;
}

.chatroom-message-item {
  margin-bottom: 10px;
}

.chatroom-message-sender {
  font-weight: bold;
  margin-right: 5px;
}

.chatroom-message-time {

  font-weight: normal;
}

.chatroom-message-text {
  word-break: break-all;
  border: 1px solid black;
  background-color: lightgray;
  padding: 5px;
}

.chatroom-input {
  display: flex;
  margin-top: 40px;
  justify-content: space-between;
}

.chatroom-users {
  max-height: 400px; /* 最大高度为400像素 */
  overflow-y: auto; /* 显示垂直滚动条，只有在内容溢出时才显示 */
  height: 100%;
}

.chatroom-users-header {
  /* 指定文本的粗细程度 */
  width: 100px;
  font-weight: bold;
  margin-bottom: 5px;
  font-size: 18px;
}

.chatroom-users-body {
  height: calc(100% - 30px);
  overflow-y: auto;
}

.chatroom-user {
  margin-bottom: 5px;
  cursor: pointer;
}
</style>
