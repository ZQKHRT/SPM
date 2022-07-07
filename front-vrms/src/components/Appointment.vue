<template>
  <!-- 卡片组件 -->
  <el-card>
    <!-- 面包屑组件 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{path:'/welcome'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>预约信息管理</el-breadcrumb-item>
      <el-breadcrumb-item>预约信息管理</el-breadcrumb-item>
    </el-breadcrumb>
<!-- 行列 -->
    <el-row :gutter="14">
      <el-col :span=6>
        <!-- 定义输入框 -->
        <el-input  v-model="username" placeholder="请输入预约人名称查询" clearable
        @clear='listPage' @change='listPage'></el-input>
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
      <el-table-column prop="apptime" type="expand">
        <template slot-scope="scope">
          <div style="color: greenyellow;font-weight: 20px;">
          <el-tag type="success">预约时间：{{scope.row.apptime}}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="username" label="姓名"></el-table-column>
      <el-table-column prop="address" label="预约地点"></el-table-column>

      <el-table-column prop="vacname" label="疫苗名称"></el-table-column>
      <el-table-column prop="money" label="接种价格"></el-table-column>

      <el-table-column prop="appstats" label="预约状态">
        <template slot-scope="s">
          <el-tag v-if="s.row.appstats==0" type="success">已预约</el-tag>
          <el-tag v-else-if="s.row.appstats==1" type="danger">未预约</el-tag>
          <el-tag v-else type="warning">未知</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="stats" label="接种状态">
        <template slot-scope="s">
          <el-tag v-if="s.row.stats==0" type="danger">未接种</el-tag>
          <el-tag v-else-if="s.row.stats==1" type="success">已接种</el-tag>
          <el-tag v-else type="warning">未知</el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="remark" label="备注"></el-table-column>

      <el-table-column label="操作" >
        <template slot-scope="scope">
            <el-button circle icon="el-icon-setting"
            @click="openEditDialog(scope.row)">修改预约信息</el-button>
          <el-button circle icon="el-icon-bell"
          @click="changeStats(scope.row)">改变接种状态</el-button>
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

    <!-- 修改对话框定义 -->
    <el-dialog :visible.sync="editFlag" title="修改预约信息">
      <el-form>
        <el-form-item>
          <el-select v-model="appointment.uid" filterable placeholder="请选择预约人姓名">
            <el-option v-for="item in userList" :key="item.uid" :label="item.username" :value="item.uid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="appointment.addid" filterable placeholder="请选择预约地址">
            <el-option v-for="item in addressList" :key="item.addid" :label="item.address" :value="item.addid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="appointment.vacid" filterable placeholder="请选择疫苗">
            <el-option v-for="item in vaccinesList" :key="item.vacid" :label="item.vacname" :value="item.vacid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>预约状态  ：
          <template>
            <el-radio v-model="appointment.appstats" label="0"> 已预约 </el-radio>
            <el-radio v-model="appointment.appstats" label="1"> 未预约</el-radio>
          </template>
        </el-form-item>
        <el-form-item>预约时间：
          <el-date-picker
                v-model="appointment.apptime"
                align="right"
                type="date"
                :picker-options="pickerOptions"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
        </el-form-item>
        <el-form-item>接种价格
            <el-input-number v-model="appointment.money" controls-position="right"
           :min="0" :max="500"></el-input-number>
        </el-form-item>
        <el-form-item>
          <el-input v-model="appointment.remark" type="textarea" placeholder="备注" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="disupdate()">取 消</el-button>
        <el-button type="primary" @click="update()">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 添加对话框定义 -->
    <el-dialog :visible.sync="addFlag" title="添加预约信息">
      <el-form>
        <el-form-item>
          <el-select v-model="appointment.uid" filterable placeholder="请选择预约人姓名">
            <el-option v-for="item in userList" :key="item.uid" :label="item.username" :value="item.uid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="appointment.addid" filterable placeholder="请选择预约地址">
            <el-option v-for="item in addressList" :key="item.addid" :label="item.address" :value="item.addid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="appointment.vacid" filterable placeholder="请选择疫苗">
            <el-option v-for="item in vaccinesList" :key="item.vacid" :label="item.vacname" :value="item.vacid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>预约状态  ：
          <template>
            <el-radio v-model="appointment.appstats" label="0"> 已预约 </el-radio>
            <el-radio v-model="appointment.appstats" label="1"> 未预约</el-radio>
          </template>
        </el-form-item>
        <el-form-item>预约时间：
          <el-date-picker
                v-model="appointment.apptime"
                align="right"
                type="date"
                :picker-options="pickerOptions"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
        </el-form-item>
        <el-form-item>接种价格
            <el-input-number v-model="appointment.money" controls-position="right"
           :min="0" :max="500"></el-input-number>
        </el-form-item>
        <el-form-item>
          <el-input v-model="appointment.remark" type="textarea" placeholder="备注" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="disadd">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
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
                    return time.getTime() <= Date.now();
                  }},
        addFlag:false, //定义显示对话框
        editFlag:false,//定义修改对话框
        tableData:[],//定义表格中的数据，是一个数组
        pager:{
          page:1,
          size:5,
          total:0
        },
        username:'',
        appointment:{
          appid:0,
          uid:'',
          addid:'',
          vacid:'',
          username:'',
          vacname:'',
          address:'',
          apptime:'',
          money:'',
          appstats:'',
          stats:'',
          remark:''
        },
        userList: [], //预约人的数组，初始化下拉列表
        vaccinesList: [], //疫苗数组，初始化下拉列表
        addressList: [] //预约地址，初始化下拉列表

      }
    },
    methods:{ //用户自定义方法
    changeStats(row){
      this.appointment=row;
      this.$http.post('http://127.0.0.1:8081/appointment/changeStats',this.appointment)
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
    disupdate(){
      this.editFlag=false;
      this.appointment={};
    },
    update(){
        if (this.appointment.uid != '' && this.appointment.uid != null &&
          this.appointment.addid != '' && this.appointment.addid != null&&
          this.appointment.vacid !=''&& this.appointment.vacid !=null) {
      this.$http.post('http://127.0.0.1:8081/appointment/update',this.appointment)
      .then(res=>{
        if(res.data.code==200){
          this.$message({
            message:res.data.message,
            type:'success'
          });
          this.editFlag = !this.editFlag;
          this.listPage();
          this.appointment={};
        }
      })}else alert("不能把信息修改为空！")
    },
    disadd(){
      this.addFlag=false;
      this.appointment={};
    },
    save(){
      if (this.appointment.uid != '' && this.appointment.uid != null &&
        this.appointment.addid != '' && this.appointment.addid != null&&
        this.appointment.vacid !=''&& this.appointment.vacid !=null) {
      this.$http.post('http://127.0.0.1:8081/appointment/save', this.appointment)
        .then(res => {
          if (res.data.code == 200) {
            this.$message({
              message: res.data.message,
              type: 'success'
            });
            this.addFlag = !this.addFlag; //关闭对话代码
            this.listPage();
            this.appointment={};
          }
        })
    }else alert("输入信息不能为空")},
    openAddDialog(){ //打开添加对话框
    this.addFlag=!this.addFlag;
    },
    openEditDialog(row){
      this.editFlag=!this.editFlag;
      this.appointment=row; //填充修改对象数据
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
      this.$http.get('http://127.0.0.1:8081/appointment/listPage',{
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
    },
    //查询所有预约注册人信息
      listUsers() {
        this.$http.get('http://127.0.0.1:8081/users/list').then(res => {
          this.userList = res.data.data;
        }).catch(err => {
          this.$message.error('出错了，服务器端未启动');
        })
      },
      listVaccines() {
        this.$http.get('http://127.0.0.1:8081/vaccines/list').then(res => {
          this.vaccinesList = res.data.data;
        }).catch(err => {
          this.$message.error('出错了，服务器端未启动');
        })
      },
      listAddress() {
        this.$http.get('http://127.0.0.1:8081/address/list').then(res => {
          this.addressList = res.data.data;
        }).catch(err => {
          this.$message.error('出错了，服务器端未启动');
        })
      }
    },
    created(){//浏览器在渲染时立即执行该方法中代码
      this.listPage();
      this.listUsers();
      this.listAddress();
      this.listVaccines();
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
