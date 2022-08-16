<template>
  <div style="color: #666;font-size: 14px;">
    <div style="padding-bottom: 20px">
      <b>欢迎你！{{ user.nickname }}</b>
    </div>
    <el-card>
      感谢青哥
      <el-divider />

    </el-card>
    <div style="height: 1px; background: #ddd; margin: 20px 0"/>

    <el-row :gutter="30">
      <el-col :span="12">
        <div style="padding: 20px 0; font-size: 20px">小白复习专用</div>
        <div>
          这是一款专门针对设计小白的框架，代码简单，结构清晰
        </div>
        <div class="m-10" style="margin: 10px;"><el-button type="danger"><i class="el-icon-coin"></i> 测试</el-button></div>
        <div class="m-10" style="margin: 10px;"><el-button type="primary"><i class="el-icon-s-custom"></i> 交流</el-button></div>
        <div class="m-10" style="margin: 10px;">
          <el-button type="primary"><i class="el-icon-link"></i> <a style="color: #fff" href="https://github.com/xiaobaiaw/studyqg/tree/master">GitHub源码</a></el-button>
        </div>
      </el-col>
      <el-col :span="12">
        <div style="padding: 20px 0; font-size: 20px">技术栈</div>
        <el-row>
          <el-col :span="12" style="line-height: 30px">
            <div><b>后端</b></div>
            <div>SpringBoot2</div>
            <div>Hutool</div>
            <div>Poi</div>
            <div>Lombok</div>
            <div>Mybatis/Mybatis-plus</div>
          </el-col>
          <el-col :span="12" style="line-height: 30px">
            <b>前端</b>
            <div>Vue2</div>
            <div>Vue-Router</div>
            <div>VueX</div>
            <div>ElementUI</div>
            <div>Axios</div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  }
}
</script>