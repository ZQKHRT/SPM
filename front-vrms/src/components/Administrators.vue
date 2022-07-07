<template>
  <!-- 卡片组件 -->
  <el-card>
    <!-- 面包屑组件 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{path:'/welcome'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统信息管理</el-breadcrumb-item>
      <el-breadcrumb-item>系统用户管理</el-breadcrumb-item>
    </el-breadcrumb>
<!-- 行列 -->
    <el-row :gutter="14">
      <el-col :span=6>
        <!-- 定义输入框 -->
        <el-input  v-model="username" placeholder="请输入" clearable
        @clear='listPage' @change='listPage'></el-input>
      </el-col>
      <el-col :span=6>
        <el-button type="primary" icon="el-icon-search"
        @click="listPage">查询</el-button>
        <el-button type="success" icon="el-icon-plus"
        @click="openAddDialog()">添加</el-button>
      </el-col>
    </el-row>
    <!-- 定义表格组件 -->
    <el-table :data="tableData">
      <el-table-column prop="username" label="管理员名称"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
      <el-table-column prop="images" label="头像">
        <template slot-scope="a">
        <!-- 要加冒号: -->
          <img :src="a.row.images" style="width: 40px;height: 20px;">
        </template>
      </el-table-column>
      <el-table-column prop="stats" label="状态">
        <template slot-scope="scope">
          <el-switch
          @change="changeStats(scope.row)"
            v-model="scope.row.stats"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="0"
            :inactive-value="1">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-tooltip content="点击修改" placement="left">
            <el-button type="warning" circle icon="el-icon-edit"
            @click="openEditDialog(scope.row)"></el-button>
          </el-tooltip>
          <el-tooltip content="点击删除" placement="right">
            <el-button type="danger" circle icon="el-icon-delete"
            @click="remove(scope.row)"></el-button>
          </el-tooltip>
        </template>
      </el-table-column>

    </el-table>
    <el-pagination @size-change='handleSizeChange'
    @current-change='handleCurrentChange'
    :current-page="pager.page"
    :page-size="pager.size"
    :page-sizes="[5, 10,15,20]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="pager.total"
    ></el-pagination>

    <!-- 添加对话框 -->
    <el-dialog :visible.sync="addFlag" title="添加管理员信息" :close-on-click-modal="false">
      <el-form :model="administrators">
        <el-form-item>
          <el-input v-model="administrators.username" placeholder="请输入管理员名称" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="administrators.password" placeholder="请输入密码" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="administrators.images" placeholder="请输入头像地址" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="disadd">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 定义修改对话框 -->
    <el-dialog title="修改管理员信息" :visible.sync="editFlag" :close-on-click-modal="false">
      <el-form :model="administrators">
        <el-form-item>
          <el-input v-model="administrators.username" placeholder="请输入管理员名称(必填)" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="administrators.password" placeholder="请输入密码(必填)" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="administrators.images" placeholder="请输入头像地址" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="disupdate">取 消</el-button>
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog>
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
        },
        username:'',//根据名称进行查询
        administrators:{
          aid:0,
          username:'',
          password:'',
          images:'',
          stats:''
        }
      }
    },
    methods:{ //用户自定义方法
    disupdate(){
      this.editFlag=false;
      this.administrators={};
    },
    update(){
      if(this.administrators.username!=''&& this.administrators.username!=null
       && this.administrators.password!='' &&this.administrators.password!=null){
      this.$http.post('http://127.0.0.1:8081/administrators/update',this.administrators)
        .then(res=>{
          if(res.data.code==200){
            this.$message({
              message:res.data.message,
              type:'success'
            });
            this.editFlag=!this.editFlag;
            this.listPage();
            this.administrators={ };
          }
        })}
        else
          alert("信息输入不完整！！")
    },
    disadd(){
      this.addFlag=false;
      this.administrators={};
    },
    save(){
      if(this.administrators.username!=''&& this.administrators.username!=null
       && this.administrators.password!='' &&this.administrators.password!=null){
      this.$http.post('http://127.0.0.1:8081/administrators/save',this.administrators)
        .then(res=>{
          if(res.data.code==200){
            this.$message({
              message:res.data.message,
              type:'success'
            });
            this.addFlag=!this.addFlag;
            this.listPage();
            this.administrators={ };
          }
        })}
        else
          alert("信息输入不完整！！")
    },
    openAddDialog(){ //打开添加对话框
    this.addFlag=!this.addFlag;
    },
    openEditDialog(row){
      this.editFlag=!this.editFlag;
      this.administrators=row; //填充修改对象数据
    },
    remove(row){
      this.$http.post('http://127.0.0.1:8081/administrators/remove?aid='+row.aid)
      .then(res=>{
        if(res.data.code==200){
          this.$message({
            message:res.data.message,
            type:'success'
          });
          this.listPage();
        }
      })
    },
    changeStats(row){
      this.administrators=row;
      this.$http.post('http://127.0.0.1:8081/administrators/changeStats',this.administrators)
      .then(res=>{
        if(res.data.code==200){
          this.$message({
            message:res.data.message,
            type:'success'
          });
          this.listPage();
        }

      })
    },
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
      this.$http.get('http://127.0.0.1:8081/administrators/listPage',{
        params:{
          page:this.pager.page,
          size:this.pager.size,
          username:this.username
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
