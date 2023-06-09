import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

// 定义路由规则
const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    /*{ path: '/message', component: ()=>import('../views/message/message.vue') }*/
    { path: '/message', component: ()=>import('../pages/chatroom.vue') },
    { path: '/login', component: ()=>import('../views/login/login.vue') },
    { path: '/register', component: ()=>import('../views/register/register.vue') },
    {path: '/test', component: ()=>import('../暂存/test.vue')}
]

// 创建 router 实例
const router = new VueRouter({
    mode: 'history', // 路由模式
    routes // 路由规则
})

// 导出 router 实例
export default router
