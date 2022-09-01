<template>
  <div style="margin-bottom: 100px">
    <div style="margin: 10px 0">
      <el-carousel height="590px" :interval="10000">
        <el-carousel-item v-for="item in imgs" :key="item">
          <img :src="item" alt="" style="width: 100%">
        </el-carousel-item>
      </el-carousel>
    </div>

    <div style="margin: 10px 0">
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in tableData" :key="item.id" style="margin-bottom: 10px">
          <div style="border: 1px solid #ccc">
            <img :src="item.img" alt="" style="width: 100%; cursor: pointer;" @click="$router.push('/front/detail?id=' + item.id)">
            <div style="color: #666; padding: 5px; font-size: 18px; cursor: pointer;"><b @click="$router.push('/front/detail?id=' + item.id)">{{ item.name }}</b></div>
              <div style="color: #666; padding: 5px; font-size: 14px">{{ item.description }}</div>
              <div style="color: orangered; padding: 5px; font-size: 14px">￥ {{ item.price }}</div>
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
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
    }
  },
  created() {
    this.load()   //获取后台的商品返回数据
  },
  methods: {
    load() {
      this.request.get("/wares/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name
        }
      }).then(res => {
        this.tableData = res.data.records
      })
    },reset() {
      this.name = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    handleFileUploadSuccess(res) {
      this.form.file = res
    },
    handleImgUploadSuccess(res) {
      this.form.img = res
    },
    download(url) {
      window.open(url)
    },
    exp() {
      window.open("http://localhost:9090/notice/export")
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    }
  }
}
</script>