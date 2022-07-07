<template>
  <!-- 卡片组件 -->
  <el-card>
    <!-- 面包屑组件 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{path:'/welcome'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息管理</el-breadcrumb-item>
      <el-breadcrumb-item>系统日志管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 定义表格组件 -->
    <el-table :data="tableData">
      <el-table-column  prop="ip" label="操作IP"></el-table-column>
      <el-table-column prop="optime" label="操作时间"></el-table-column>
      <el-table-column prop="methods" label="操作方法"></el-table-column>
      <el-table-column prop="ddesc" label="方法描述"></el-table-column>


    </el-table>
    <el-pagination @size-change='handleSizeChange'
    @current-change='handleCurrentChange'
    :current-page="pager.page"
    :page-size="pager.size"
    :page-sizes="[5, 10,15,20]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="pager.total"
    ></el-pagination>


  </el-card>
</template>

<script>
  export default{ //对外暴露vue对象
    data(){//定义的是vue对象的属性
      return{
        addFlag:false, //定义显示对话框
        editFlag:false,//定义修改对话框
        tableData:[],//定义表格中的数据，是一个数组
        pager:{
          page:1,
          size:5,
          total:0
        }
      }
    },
    methods:{ //用户自定义方法
    handleCurrentChange(val){
      console.info('改变分页起始数据'+val)
      this.pager.page=val;
      this.listPage();
    },
    handleSizeChange(val){
      console.info("改变每页显示最大条数"+val)
      this.pager.size=val;
      this.listPage();
    },
    listPage(){
      this.$http.get('http://127.0.0.1:8081/logs/listPage',{
        params:{
          page:this.pager.page,
          size:this.pager.size,

        }
      }).then(res => {
        console.info(res);
        this.tableData=res.data.data.data;//查询出的数据
        this.pager.total=res.data.data.count;
      })
    }
    },
    created(){//浏览器在渲染时立即执行该方法中代码
      this.listPage();
    }
  }
</script>

<style>
  .el-breadcrumb{
    padding-bottom: 12px;
  }
  .el-pagination{
    text-align: center;
    padding-top: 20px;
  }
  .el-table{
    padding-top: 10px;
  }
</style>
