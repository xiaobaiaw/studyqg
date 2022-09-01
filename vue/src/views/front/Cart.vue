<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 400px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
        <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe size="mediun" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="waresImg" label="商品图片">
        <template v-slot="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.waresImg" :preview-src-list="[scope.row.waresImg]"></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="waresName" label="商品"></el-table-column>
      <el-table-column prop="num" label="商品价格" width="100px">
        <template v-slot="scope">
          <span style="color: orangered">￥ {{ scope.row.price }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="num" label="商品数量">
        <template v-slot="scope">
          <el-input-number v-model="scope.row.num" :min="1" :max="100" label="数量" @change="changeNum(scope.row)"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column prop="time" label="添加时间"></el-table-column>
      <el-table-column label="操作"  width="180" align="center">
        <template slot-scope="scope">
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>
    <div style="margin: 10px 0;text-align: right">
      <div>当前已选商品总价: <span style="color:orangered;">￥ {{ totalPrice }}</span></div>
      <el-button style="color:white; background-color: orangered;" size="medium" @click="settleAccount"><i class="el-icon-coin"></i> 结 算</el-button>
    </div>
  </div>
</template>

<script>
import E from "wangeditor";

export default {
  name: "Cart",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
      form: {},
      totalPrice: 0.00,
      dialogFormVisible: false,
      multipleSelection: [],
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.load()
  },
  methods: {
    settleAccount() {
      if (!this.multipleSelection || !this.multipleSelection.length) {
        this.$message.error("请选择需要删除的数据")
        return
      }
      let data = {name: this.multipleSelection[0].waresName, totalPrice: this.totalPrice, carts: this.multipleSelection}
      this.request.post("/orders", data).then(res => {
        if (res.code === '200') {
          this.$message.success("结算成功")
           this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    load() {
      this.request.get("/cart/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    changeNum(row) {
      this.request.post("/cart/num/" + row.id + "/" + row.num).then(res => {
        // this.load()
        this.totalPrice = 0.00
        //计算总价格
        this.multipleSelection.forEach(item => {
          item.num = row.num  // 更新选中的数组数量
          this.totalPrice += item.price * row.num //有坑 数字发生变化需要获取最新的值，进行计算
        })
        this.totalPrice = this.totalPrice.toFixed(2)
      })
    },
    save() {
      this.request.post("/cart", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
      this.$nextTick(() => {
        if(this.$refs.img) {
          this.$refs.img.clearFiles();
        }
        if(this.$refs.file) {
          this.$refs.file.clearFiles();
        }
      })
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
      this.$nextTick(() => {
        if(this.$refs.img) {
          this.$refs.img.clearFiles();
        }
        if(this.$refs.file) {
          this.$refs.file.clearFiles();
        }
      })
    },
    del(id) {
      this.request.delete("/cart/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      this.totalPrice = 0.00
      this.multipleSelection = val
      //进行计算操作
      if (val && val.length) {
        val.forEach(item => {
          this.totalPrice += item.num * item.price
        })
        this.totalPrice = this.totalPrice.toFixed(2)
      }
    },
    delBatch() {
      if (!this.multipleSelection.length) {
        this.$message.error("请选择需要删除的数据")
        return
      }
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
      this.request.post("/cart/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    reset() {
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
      window.open("http://localhost:9090/cart/export")
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    }
  }
}
</script>


<style>
.headerBg {
  background: #eee!important;
}
</style>