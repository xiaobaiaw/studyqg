<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 40px">
      <el-col :span="6">
        <el-card style="color: #409EFF">
          <div style="color: #409EFF;"><i class="el-icon-user-solid"/>用户总数</div>
          <div style="padding: 10px 0; text-align: center;font-weight: bold">
            100
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #F56C6C">
          <div style="color: #F56C6C;"><i class="el-icon-money"/>销售总量</div>
          <div style="padding: 10px 0; text-align: center;font-weight: bold">
            ￥ 100000
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #67C23A">
          <div style="color: #67C23A;"><i class="el-icon-bank-card"/>收益总额</div>
          <div style="padding: 10px 0; text-align: center;font-weight: bold">
            ￥ 1000
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #E6A23C">
          <div style="color: #E6A23C;"><i class="el-icon-s-shop"/>门店总数</div>
          <div style="padding: 10px 0; text-align: center;font-weight: bold">
            20
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height:450px;"></div>
      </el-col>
      <el-col :span="12">
        <div id="pie" style="width: 500px; height:400px;"></div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  name: "Home",
  data() {
    return{

    }
  },
  mounted() { // 页面元素渲染之后再触发
    var option = {
      title: {
        text: '各季度会员数量统计',
        subtext: '趋势图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: {
        type: 'category',
        data: ['第一季度','第二季度','第三季度','第四季度']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name:'星巴克',
          data: [],
          type: 'line'
        },
        {
          name:'星巴克',
          data: [],
          type: 'bar'
        },
        {
          name:'瑞幸咖啡',
          data: [],
          type: 'line'
        },
        {
          name:'瑞幸咖啡',
          data: [],
          type: 'bar'
        }
      ]
    };
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);

    this.request.get("/echarts/members").then(res => {
      //清空
      // option.xAxis.data = res.data.x
      option.series[0].data = res.data
      option.series[1].data = res.data
      option.series[2].data = [2,3,4,5]
      option.series[3].data = [2,3,4,5]
      myChart.setOption(option);
    })



    //饼图
    var pieOption = {
      title: {
        text: '各季度会员数量统计',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: "星巴克",
          type: 'pie',
          radius: '50%',
          center: ['25%', '60%'],
          label:{            //饼图图形上的文本标签
            normal:{
              show:true,
              position:'inner', //标签的位置
              textStyle : {
                fontWeight : 300 ,
                fontSize : 14,    //文字的字体大小
                color: "#fff"
              },
              formatter:'{c} ({d}%)'
            }
          },
          data: [],  // 填空
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        },
        {
          name: "瑞辛咖啡",
          type: 'pie',
          radius: '60%',
          center: ['75%', '50%'],
          label:{            //饼图图形上的文本标签
            normal:{
              show:true,
              position:'inner', //标签的位置
              textStyle : {
                fontWeight : 300 ,
                fontSize : 14,    //文字的字体大小
                color: "#fff"
              },
              formatter:'{d}%'
            }
          },
          data: [{name: "第一季度", value: 3},
            {name: "第二季度", value: 2},
            {name: "第三季度", value: 1},
            {name: "第四季度", value: 6},
          ],  // 填空
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };

    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);

    this.request.get("/echarts/members").then(res => {

      pieOption.series[0].data = [
        {name: "第一季度", value: res.data[0]},
        {name: "第二季度", value: res.data[1]},
        {name: "第三季度", value: res.data[2]},
        {name: "第四季度", value: res.data[3]},
      ]
      pieChart.setOption(pieOption)
    })
  }
}
</script>

<style scoped>

</style>