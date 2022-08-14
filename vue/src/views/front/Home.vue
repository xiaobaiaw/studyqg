<template>
  <div>
    <div style="margin: 10px 0">
      <el-carousel height="590px" :interval="10000">
        <el-carousel-item v-for="item in imgs" :key="item">
          <img :src="item" alt="" style="width: 100%">
        </el-carousel-item>
      </el-carousel>
    </div>

    <div style="margin: 10px 0">
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in files" :key="item.id" style="margin-bottom: 10px">
          <div style="border: 1px solid #ccc">
            <img :src="item.url" alt="" style="width: 100%">
              <div style="color: #666; padding: 10px">{{ item.name }}</div>
              <div style="padding: 10px"><el-button type="primary">购买</el-button></div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "FrontHome",
  data() {
    return {
      imgs: [
          'https://img20.360buyimg.com/babel/s1580x830_jfs/t1/139306/24/28512/82178/62f4712cE6b30bdc6/096c2bee5000cda6.png!cc_1580x830.webp',
          'https://img10.360buyimg.com/babel/s1580x830_jfs/t1/43768/17/17556/83867/62d92ed9E57d9c346/f7bfb8c56bb4f096.jpg!cc_1580x830.webp',
          'https://img10.360buyimg.com/babel/s1580x830_jfs/t1/190638/24/26436/78432/62ec856fE5c5b896a/689a012d04e1cb3e.jpg!cc_1580x830.webp',
      ],
      files: []
    }
  },
  created() {
    this.request.get("/echarts/file/front/all").then(res => {
      console.log(res.data)
      this.files = res.data.filter(v => v.type === 'png' || v.type === 'jpg' || v.type === 'webp' || v.type === 'jpeg')
    })
  },
  methods: {

  }
}
</script>