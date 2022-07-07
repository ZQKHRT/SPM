<template>
  <div>
    <div class="roll">
      <el-carousel :interval="2000" type="card" height="700px" autoplay="true" >
          <el-carousel-item v-for="item in urlList" :key="item">
            <el-image :src="item" :preview-src-list="urlList"></el-image>
          </el-carousel-item>

          <div class="login-form">
            <el-card class="box-card">
              <div slot="header" class="header-card">
              <span style="text-align: center;">疫苗预约后台管理系统</span>
              </div>
              <el-form :model="user" :rules="rules" ref="user">
                <el-form-item prop="username">
                  <el-input suffix-icon="el-icon-user-solid" v-model="user.username" clearable placeholder="请输入用户名称">
                  </el-input>
                </el-form-item>
                <el-form-item prop="password">
                  <el-input suffix-icon="el-icon-s-goods" v-model="user.password" clearable type="password"
                    placeholder="请输入密码">
                  </el-input>
                </el-form-item>
                <el-button type="primary" @click="login">登录</el-button>
                <el-button type="primary" @click="login">注册</el-button>
              </el-form>
            </el-card>
          </div>
        </el-carousel>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        urlList: [
          'https://img2.baidu.com/it/u=1358402407,3126321204&fm=26&fmt=auto',
          'https://img2.baidu.com/it/u=3759737237,1692907964&fm=26&fmt=auto',
          'https://img1.baidu.com/it/u=1122656784,57901039&fm=253&fmt=auto&app=138&f=JPEG?w=1069&h=500',
          'https://img2.baidu.com/it/u=1540318239,1446769210&fm=253&fmt=auto&app=138&f=JPG?w=500&h=201',
          'https://img1.baidu.com/it/u=473341220,78109478&fm=26&fmt=auto',
          'https://img2.baidu.com/it/u=791238811,400284572&fm=26&fmt=auto'
        ],
        rules: {
          username: [{
            required: true,
            message: '请输入用户名称',
            trigger: 'blur'
          }],
          password: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }],
        },
        user: {
          username: '',
          password: ''
        }
      }
    },
    methods: {
      login() {
        this.$refs['user'].validate((valid) => {
          if (valid) {
            this.$http.get('http://127.0.0.1:8081/administrators/login', {
                params: this.user
              })
              .then(res => {
                if (res.data.data == null) {
                  this.$message.error('你输入的用户名称或者密码不正确，或者账号被锁定，请联系管理员');
                } else {
                  sessionStorage.setItem("username", res.data.data)
                  this.$router.push('/welcome');
                }

              }).catch(e => {
                this.$message.error('错了哦，服务器端未启动');
              })

          }
        });

      }
    }

  }
</script>

<style scoped>
  .box-card {
    width: 550px;
    height: 350px;
    border-radius: 10px;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    box-shadow: 10px 20px 30px #888888;
  }

  .login-form {
    top: 50%;
    position: absolute;
    width: 100%;
    padding: 0px 20px;
    box-sizing: border-box;
    z-index: 999;
  }

  .el-button {
    display: flex;
    justify-content: center;
    width: 45%;
    float: left;
    font-weight: bold;
    height: 40px;
    font-size: 18px;
    border-radius: 10px;
  }

  .header-card {
    display: flex;
    justify-content: center;
    font-size: 18px;
  }

  .el-input {
    border-radius: 10px;
    height: 50px;
    font-size: 18px;
    border-bottom-color: green;

  }



  .el-image {
    width: 875px;
    height: 600px;
    margin-top: 10%;
    margin-left: 0.5%;
  }

</style>
