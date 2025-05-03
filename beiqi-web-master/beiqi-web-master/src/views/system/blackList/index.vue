<template>
  <div class="app-container">
    <el-form ref="form" label-width="100px">
      <el-row>
        <el-col :span="12">
          <p>注意:填写每个禁止ip时以换行分割</p>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="禁止IP名单">
            <el-input v-model="form.ipListStr" type="textarea" rows="20" placeholder="请输入禁止IP,每个IP以换行分割" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm">新增</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import { listBlack, addBlack } from '@/api/system/ipList'

export default {
  name: 'BlackIp',
  dicts: ['sys_yes_no'],
  data() {
    return {
      // 表单参数
      form: {
        ipListStr: ''
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询参数列表 */
    getList() {
      listBlack().then(response => {
        this.form.ipListStr = response.data
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      addBlack(this.form).then(response => {
        this.$modal.msgSuccess(response.msg)
        this.getList()
      })
    }
  }
}
</script>
