<template>
  <!-- <el-row :gutter="10">
    欢迎使用该系统
  </el-row> -->

      <el-card :body-style="{ padding: '0px' }">
        <time class="time">{{ currentDate }}</time>
        <img src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp0.itc.cn%2Fq_70%2Fimages03%2F20210610%2F040b3a9175d045baae831db79769b391.png&refer=http%3A%2F%2Fp0.itc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644065091&t=96abed1bbaab83814779322d616db251" class="image">
        <div style="padding: 14px;">
          <span>及时接种疫苗，保护家人健康</span>
        </div>
      </el-card>

</template>

<script>
  export default {
    data() {
      return {
        currentDate: new Date()
      };
    }
  }

</script>

<style>
</style>
