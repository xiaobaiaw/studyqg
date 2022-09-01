<template>
  <div style="padding: 10px;">
    <el-card>
      <div style="display: flex">
        <div style="width: 300px;">
          <el-image :src="wares.img" :preview-src-list="[wares.img]" style="width: 100%;"></el-image>
        </div>
        <div style="flex: 1; padding-left: 50px;">
          <div class="item1">{{ wares.name }}</div>
          <div class="item1" style="font-size: 14px;">{{ wares.description }}</div>
            <div class="item1" style="color:orangered;width: 50%;">价格 ￥ {{ wares.price }}</div>
            <div class="item1" style="font-size: 14px">库存 {{wares.store}}<span style="margin-left: 10px">{{wares.unit}}</span></div>
          <div class="item1">
            <el-input-number v-model="form.num" :min="1" :max=wares.store label="数量"></el-input-number>
            <span style="margin-left: 10px">{{wares.unit}}</span>
          </div>
          <div class="item1" style="margin-top: 50px;">
            <el-button size="medium" style="background-color: red; color: white;"><i class="el-icon-wallet"></i> 直接购买</el-button>
            <el-button size="medium" style="background-color: red; color: white;" v-on:click="addCart"><i class="el-icon-shopping-cart-2"></i> 加入购物车</el-button>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Detail",
  data() {
    return {
      id: this.$route.query.id,
      wares: {},
      form: { num: 1 },
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/wares/" + this.id).then(res => {
        this.wares = res.data
      })
    },
    addCart() {
      if (!this.user.username) {
        this.$message.warning("请登陆后操作!")
        return
      }
      this.form.waresId = this.wares.id  //商品id
      this.request.post("/cart",this.form).then(res => {
        if (res.code === "200") {
          this.$message.success("加入购物车成功")
        }else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.item1 {
  padding: 10px;
  color: #666;
}
</style>