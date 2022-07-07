<template>
  <!-- 卡片组件 -->
  <el-card>
    <!-- 面包屑组件 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{path:'/welcome'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>预约用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>预约用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 行列 -->
    <el-row :gutter="14">
      <el-col :span=6>
        <!-- 定义输入框 -->
        <el-input v-model="username" placeholder="请输入" clearable @clear='listPage' @change='listPage'></el-input>
      </el-col>
      <el-col :span=6>
        <el-button type="primary" icon="el-icon-search" @click="listPage">查询</el-button>
        <el-button type="success" icon="el-icon-plus" @click="openAddDialog()">添加</el-button>
      </el-col>
    </el-row>
    <!-- 定义表格组件 -->
    <el-table :data="tableData">
      <el-table-column prop="address" type="expand">
        <template slot-scope="scope">
          <div style="color: greenyellow;font-weight: 20px;">
            <el-tag type="success">住址：{{scope.row.address}}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="username" label="用户名称"></el-table-column>
      <el-table-column prop="password" label="密码"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="idcard" label="身份证号"></el-table-column>
      <el-table-column prop="tel" label="电话"></el-table-column>

      <el-table-column prop="stats" label="状态">
        <template slot-scope="scope">
          <el-switch @change="changeStats(scope.row)" v-model="scope.row.stats" active-color="#13ce66"
            inactive-color="#ff4949" :active-value="0" :inactive-value="1">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-tooltip content="点击修改" placement="left">
            <el-button type="warning" circle icon="el-icon-edit" @click="openEditDialog(scope.row)"></el-button>
          </el-tooltip>
          <el-tooltip content="点击删除" placement="right">
            <el-button type="danger" circle icon="el-icon-delete" @click="remove(scope.row)"></el-button>
          </el-tooltip>
        </template>
      </el-table-column>

    </el-table>
    <el-pagination @size-change='handleSizeChange' @current-change='handleCurrentChange' :current-page="pager.page"
      :page-size="pager.size" :page-sizes="[5, 10,15,20]" layout="total, sizes, prev, pager, next, jumper"
      :total="pager.total"></el-pagination>

    <!-- 添加对话框 -->
    <el-dialog :visible.sync="addFlag" title="添加用户信息" :close-on-click-modal="false">
      <el-form :rules="rule" ref="us" :model="users" >
        <el-form-item prop="username">
          <el-input v-model="users.username" placeholder="请输入用户名称" clearable></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="users.password" placeholder="请输入密码" clearable></el-input>
        </el-form-item>
        <el-form-item prop="age">
          <el-input v-model="users.age" placeholder="请输入年龄" clearable></el-input>
        </el-form-item>
        <el-form-item prop="sex">
          <template>
            <el-radio v-model="users.sex" label="男"> 男 </el-radio>
            <el-radio v-model="users.sex" label="女"> 女 </el-radio>
          </template>
        </el-form-item>
        <el-form-item prop="idcard">
          <el-input v-model="users.idcard" placeholder="请输入身份证号" clearable></el-input>
        </el-form-item>
        <el-form-item prop="tel">
          <el-input v-model="users.tel" placeholder="请输入电话" clearable></el-input>
        </el-form-item>
        <el-form-item prop="address">
          <el-input v-model="users.address" type="textarea" placeholder="请输入住址" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="disadd">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 定义修改对话框 -->
    <el-dialog title="修改用户信息" :visible.sync="editFlag" :close-on-click-modal="false">
      <el-form :rules="rule" ref="us" :model="users" >
        <el-form-item prop="username">
          <el-input v-model="users.username" placeholder="请输入用户名称" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="users.password" placeholder="请输入密码" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="users.age" placeholder="请输入年龄" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <!-- <el-input v-model="users.sex" placeholder="请输入性别" clearable></el-input>-->
          <template>
            <el-radio v-model="users.sex" label="男"> 男 </el-radio>
            <el-radio v-model="users.sex" label="女"> 女 </el-radio>
          </template>
        </el-form-item>
        <el-form-item>
          <el-input v-model="users.idcard" placeholder="请输入身份证号" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="users.tel" placeholder="请输入电话" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="users.address" placeholder="请输入住址" clearable></el-input>
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
  export default { //对外暴露vue对象
    data() { //定义的是vue对象的属性
      return {
        addFlag: false, //定义显示对话框
        editFlag: false, //定义修改对话框
        tableData: [], //定义表格中的数据，是一个数组
        pager: {
          page: 1,
          size: 5,
          total: 0
        },
        username: '', //根据名称进行查询
        users: {
          uid: 0,
          username: '',
          password: '',
          sex: '',
          age: '',
          tel: '',
          idcard: '',
          address: '',
          stats: ''
        },
        rule: {
          username: [{
            required: true,
            message: '请输入名称',
            trigger: 'blur'
          }],
          password: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }],
          age: [{
            required: true,
            message: '请输入年龄',
            trigger: 'blur'
          }],
          sex: [{
            required: false
          }],
          idcard: [{
            required: true,
            message: '请输入身份证',
            trigger: 'blur'
          }],
          tel: [{
            required: true,
            message: '请输入电话',
            trigger: 'blur'
          }],
          address: [{
            required: true,
            message: '请输入地址',
            trigger: 'blur'
          }],
        },
        us:{
          username:'',
          password:'',
          age:'',
          sex:'',
          idcard:'',
          tel:'',
          address:'',
        }
      }
    },
    methods: { //用户自定义方法
      disupdate() {
        this.editFlag = false;
        this.users = {};
        this.listPage();
      },
      update() {
      this.$refs['user'].validate((valid) => {
          this.$http.post('http://127.0.0.1:8081/users/update', this.users)
            .then(res => {
                if (res.data.code == 200) {
                  this.$message({
                    message: res.data.message,
                    type: 'success'
                  });
                  this.editFlag = !this.editFlag;
                  this.listPage();
                  this.users = {};
                }})
})
              },
              disadd() {
                this.addFlag = false;
                this.users = {};
                this.listPage();
              },
              save() {
                this.$refs['user'].validate((valid) => {
                  this.$http.post('http://127.0.0.1:8081/users/save', this.users)
                    .then(res => {
                      if (res.data.code == 200) {
                        this.$message({
                          message: res.data.message,
                          type: 'success'
                        });
                        this.addFlag = !this.addFlag;
                        this.listPage();
                        this.users = {};

                    }

                })})},
                openAddDialog() { //打开添加对话框
                    this.addFlag = !this.addFlag;
                  },
                  openEditDialog(row) {
                    this.editFlag = !this.editFlag;
                    this.users = row; //填充修改对象数据
                  },
                  remove(row) {
                    this.$http.post('http://127.0.0.1:8081/users/remove?uid=' + row.uid)
                      .then(res => {
                        if (res.data.code == 200) {
                          this.$message({
                            message: res.data.message,
                            type: 'success'
                          });
                          this.listPage();
                        }
                      })
                  },
                  changeStats(row) {
                    this.users = row;
                    this.$http.post('http://127.0.0.1:8081/users/changeStats', this.users)
                      .then(res => {
                        if (res.data.code == 200) {
                          this.$message({
                            message: res.data.message,
                            type: 'success'
                          });
                          this.listPage();
                        }

                      })
                  },
                  handleCurrentChange(val) {
                    console.info('改变分页起始数据' + val)
                    this.pager.page = val;
                    this.listPage();
                  },
                  handleSizeChange(val) {
                    console.info("改变每页显示最大条数" + val)
                    this.pager.size = val;
                    this.listPage();
                  },
                  listPage() {
                    this.$http.get('http://127.0.0.1:8081/users/listPage', {
                      params: {
                        page: this.pager.page,
                        size: this.pager.size,
                        username: this.username
                      }
                    }).then(res => {
                      console.info(res);
                      this.tableData = res.data.data.data; //查询出的数据
                      this.pager.total = res.data.data.count;
                    })
                  }
              },
              created() { //浏览器在渲染时立即执行该方法中代码
                this.listPage();
              }
            }
</script>

<style>
  .el-breadcrumb {
    padding-bottom: 12px;
  }

  .el-pagination {
    text-align: center;
    padding-top: 20px;
  }

  .el-table {
    padding-top: 10px;
  }
</style>
