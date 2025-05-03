<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
      </div>
      <div>
        <div class="text-center">
          <userAvatar :user="userInfo" />
        </div>
        <ul class="list-group list-group-striped">
          <li class="list-group-item">
            <svg-icon icon-class="user" />用户名称
            <div class="pull-right">{{ userInfo.userName }}</div>
          </li>
          <li class="list-group-item">
            <svg-icon icon-class="phone" />手机号码
            <div class="pull-right">{{ userInfo.phonenumber }}</div>
          </li>
          <li class="list-group-item">
            <svg-icon icon-class="email" />用户邮箱
            <div class="pull-right">{{ userInfo.email }}</div>
          </li>
          <li class="list-group-item">
            <svg-icon icon-class="tree" />所属部门
            <div v-if="userInfo.dept" class="pull-right">{{ userInfo.dept.deptName }} / {{ postGroup }}</div>
          </li>
          <li class="list-group-item">
            <svg-icon icon-class="peoples" />所属角色
            <div class="pull-right">{{ roleGroup }}</div>
          </li>
          <li class="list-group-item">
            <svg-icon icon-class="date" />创建日期
            <div class="pull-right">{{ userInfo.createTime }}</div>
          </li>
        </ul>
      </div>
    </el-card>
  </div>
</template>

<script>
import userAvatar from './userAvatar'
import { mapState } from 'vuex'

export default {
  name: 'Profile',
  components: { userAvatar },
  data() {
    return {
      user: {}
    }
  },
  computed: {
    ...mapState(['userInfo'])
  },
  created() {
    this.user = this.userInfo
  },
  methods: {}
}
</script>
