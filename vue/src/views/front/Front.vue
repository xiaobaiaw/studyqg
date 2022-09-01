<template>
  <div>
    <!--头部-->
    <div style="display: flex; height: 60px; line-height: 60px;border-bottom: #8c939d">
      <div style="width: 300px; display: flex; padding-left: 30px;">
        <div style="width: 60px;">
          <img src="../../assets/一人之下logo.jpg" alt="" style="width: 50px; position: relative; top: 10px; right: 0;">
        </div>
        <div style="flex: 1;">欢迎来到实力至上主义系统</div>
      </div>
      <div style="flex: 1">
        <!--导航菜单-->
<!--        <ul style="list-style: none; background-color: #98FB98;">-->
<!--          <li class="item">-->
<!--            <el-dropdown size="medium">-->
<!--              <span>更多菜单<i class="el-icon-arrow-down el-icon-right"></i></span>-->
<!--              <el-dropdown-menu slot="dropdown">-->
<!--                <el-dropdown-item>炁体源流</el-dropdown-item>-->
<!--                <el-dropdown-item>通天箓</el-dropdown-item>-->
<!--                <el-dropdown-item>双全手</el-dropdown-item>-->
<!--                <el-dropdown-item>神灵明</el-dropdown-item>-->
<!--              </el-dropdown-menu>-->
<!--            </el-dropdown>-->
<!--          </li>-->
<!--          <li class="item"><a href="/">菜单2</a></li>-->
<!--          <li class="item"><a href="/">菜单3</a></li>-->
<!--          <li class="item"><a href="/">菜单4</a></li>-->
<!--        </ul>-->

        <el-menu :default-active="'1'" class="el-menu-demo" mode="horizontal" router>
          <el-menu-item index="/front/home">首页</el-menu-item>
          <el-menu-item index="/front/video">视频播放</el-menu-item>
          <el-menu-item index="/front/article">文章列表</el-menu-item>
          <el-menu-item index="/front/orders">我的订单</el-menu-item>
          <el-menu-item index="/front/cart">我的购物车</el-menu-item>
        </el-menu>
      </div>
      <div style="width: 200px">
        <div v-if="!user.username" style="text-align: right; padding-right: 30px">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
            <div style="display: inline-block">
              <img :src="user.avatarUrl" alt=""
                   style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
              <span>{{ user.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            </div>
            <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <router-link to="/front/password">修改密码</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <router-link to="/front/person">个人信息</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <span style="text-decoration: none" @click="logout">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>

    <div style="width: 1000px; margin: 0 auto">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "Front",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {

  },
  methods: {
    logout() {
      this.$store.commit("logout")
      this.$message.success("退出成功")
    }
  }
}
</script>

<style>
.item{
  display: inline-block;
  width: 100px;

  text-align: center;
  cursor: pointer
}
.item a {
  color: 	#1E90FF;
}
.item:hover{
  background-color: 	LightPink;
}
</style>
