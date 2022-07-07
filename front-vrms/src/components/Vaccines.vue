<template>
  <!-- 卡片组件 -->
  <el-card>
    <!-- 面包屑组件 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{path:'/welcome'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>预约信息管理</el-breadcrumb-item>
      <el-breadcrumb-item>疫苗信息管理</el-breadcrumb-item>
    </el-breadcrumb>
<!-- 行列 -->
    <el-row :gutter="14">
      <el-col :span=6>
        <!-- 定义输入框 -->
        <el-input  v-model="vacname" placeholder="请输入" clearable @clear='listPage' @change='listPage'></el-input>
      </el-col>
      <el-col :span=6>
        <el-button type="primary" icon="el-icon-search"
        @click="listPage">查询</el-button>
        <el-button type="success" icon="el-icon-plus"
        @click="openAddDialog">添加</el-button>
      </el-col>
    </el-row>
    <!-- 定义表格组件 -->
    <el-table :data="tableData">
    <el-table-column prop="instructions" type="expand">
            <template slot-scope="scope">
              <div style="color: greenyellow;font-weight: 20px;">
              <el-tag type="success">使用说明：{{scope.row.instructions}}</el-tag>
              </div>
            </template>
          </el-table-column>
      <el-table-column prop="vacname" label="疫苗名称"></el-table-column>
      <el-table-column prop="manufactor" label="生产厂家"></el-table-column>
      <el-table-column prop="mdate" label="生产日期"></el-table-column>
      <el-table-column prop="price" label="价格"></el-table-column>
      <!-- <el-table-column prop="instructions" label="说明"></el-table-column> -->

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
    <el-dialog :visible.sync="addFlag" title="添加疫苗信息" :close-on-click-modal="false">
      <el-form :model="vaccines">

        <el-form-item>
          <el-input v-model="vaccines.vacname" placeholder="请输入疫苗名称" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="vaccines.manufactor" placeholder="请输入生产厂家" clearable></el-input>
        </el-form-item>
        <el-form-item>生产日期 ：
          <el-date-picker
                v-model="vaccines.mdate"
                align="right"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                placeholder="选择日期"
                :picker-options="pickerOptions"
                value-format="yyyy-MM-dd"
                >
              </el-date-picker>
        </el-form-item>
        <el-form-item>疫苗价格：
          <!-- <el-input v-model="vaccines.price" placeholder="请输入价格" clearable></el-input> -->
          <el-input-number v-model="vaccines.price" controls-position="right"
           :min="0" :max="500"></el-input-number>
        </el-form-item>
        <!-- 用文本域 -->
        <el-form-item>
          <el-input v-model="vaccines.instructions" type="textarea" placeholder="请输入使用说明" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="disadd">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 定义修改对话框 -->
    <el-dialog title="修改疫苗信息" :visible.sync="editFlag" :close-on-click-modal="false">
      <el-form :model="vaccines">
        <el-form-item>
          <el-input v-model="vaccines.vacname" placeholder="请输入疫苗名称" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="vaccines.manufactor" placeholder="请输入生产厂家" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <!-- <el-input v-model="vaccines.mdate" placeholder="请输入生产日期" clearable></el-input> -->
          <el-date-picker
                v-model="vaccines.mdate"
                align="right"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                :picker-options="pickerOptions"
                value-format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
        </el-form-item>
        <el-form-item>
            <el-input-number v-model="vaccines.price" controls-position="right"
           :min="0" :max="500"></el-input-number>
        </el-form-item>
        <!-- 用文本域 -->
        <el-form-item>
          <el-input v-model="vaccines.instructions" type="textarea" placeholder="请输入使用说明" clearable></el-input>
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
        pickerOptions: {
                  disabledDate(time) {
                    return time.getTime() > Date.now();
                  }},
        addFlag:false, //定义显示对话框
        editFlag:false,//定义修改对话框
        tableData:[],//定义表格中的数据，是一个数组
        pager:{
          page:1,
          size:5,
          total:0
        },
        vacname:'',//根据名称进行查询
        vaccines:{
          vacid:0,
          vacname:'',
          manufactor:'',
          mdate:'',
          price:'',
          instructions:''
        }
      }
    },
    methods:{ //用户自定义方法
    update(){
      if(this.vaccines.vacname!='' && this.vaccines.manufactor!=''
      &&this.vaccines.vacname!=null && this.vaccines.manufactor!=null){
      this.$http.post('http://127.0.0.1:8081/vaccines/update',this.vaccines)
        .then(res=>{
          if(res.data.code==200){
            this.$message({
              message:res.data.message,
              type:'success'
            });
            this.editFlag=!this.editFlag;
            this.listPage();
            this.vaccines={ };
          }
        })}
        else
          alert("信息输入不完整！！")
    },
    disupdate(){
      this.editFlag=false;
      this.vaccines={};
    },
    disadd(){
      this.addFlag=false;
      this.vaccines={};
    },
    save(){
      if(this.vaccines.vacname!='' && this.vaccines.manufactor!=''
      &&this.vaccines.vacname!=null && this.vaccines.manufactor!=null){
      this.$http.post('http://127.0.0.1:8081/vaccines/save',this.vaccines)
        .then(res=>{
          if(res.data.code==200){
            this.$message({
              message:res.data.message,
              type:'success'
            });
            this.addFlag=!this.addFlag;
            this.listPage();
            this.vaccines={ };
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
      this.vaccines=row; //填充修改对象数据
    },
    remove(row){
      this.$http.post('http://127.0.0.1:8081/vaccines/remove?vacid='+row.vacid)
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
      this.$http.get('http://127.0.0.1:8081/vaccines/listPage',{
        params:{
          page:this.pager.page,
          size:this.pager.size,
          vacname:this.vacname
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
