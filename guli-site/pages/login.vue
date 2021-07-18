<template>
  <div class="main">
    <div class="title">
      <a class="active" href="/login">登录</a>
      <span>·</span>
      <a href="/register">注册</a>
    </div>

    <div class="sign-up-container">
      <form action="register">
        <div class="input-prepend restyle">
          <input
            v-model="user.mobile"
            type="text"
            placeholder="邮箱登录">
          <i class="iconfont icon-phone"/>
        </div>
        <div class="input-prepend">
          <input
            v-model="user.password"
            type="password"
            placeholder="密码">
          <i class="iconfont icon-password"/>
        </div>
        <div class="btn">
          <input
            type="button"
            class="sign-in-button"
            value="登录"
            @click="submitLogin()">
        </div>
      </form>
      <!-- 更多登录方式 -->
      <div class="more-sign">
        <h6>社交帐号登录</h6>
        <ul>
          <li><a id="weixin" class="weixin" href="http://localhost:8160/api/ucenter/wx/login"><i class="iconfont icon-weixin"/></a></li>
          <li><a id="qq" class="qq" target="_blank" href="http://localhost:8160/api/ucenter/qq/login"><i class="iconfont icon-qq"/></a></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'

import cookie from 'js-cookie'
import loginApi from '~/api/login'
export default {
  layout: 'sign',
  data() {
    return {
      user: {
        mobile: '',
        password: ''
      }
    }
  },

  methods: {
    // 登录
    submitLogin() {
      loginApi.submitLogin(this.user).then(response => {
        // 登录成功后将token写入cookie
        cookie.set('guli_token', response.data.token, {
          domain: 'localhost', // 设置顶级域名，让以localhost开头的其他域名都能获取这个cookie
          expires: 1 // 1天：如果是数值则单位为天，也可以是Date类型，表示有效期至Date指定时间
        })
      })
      // 跳转到首页
      //
      if (document.referrer.indexOf('register') !== -1) {
        window.location.href = '/'
      } else {
        history.go(-1) // 跳转到之前的页面
      }
    }
  }
}
</script>
