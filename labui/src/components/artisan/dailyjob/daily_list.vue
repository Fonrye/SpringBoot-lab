<template>
<!--生成维修表，大的表格，具体明细以及总故障-->
	<div>
		<!-- 面包屑导航区域 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>系统设置</el-breadcrumb-item>
			<el-breadcrumb-item>日常工作管理{{userId}}</el-breadcrumb-item>
		</el-breadcrumb>

		<!-- 卡片视图区域 -->
		<el-card style="margin-top: 20px">
			 <el-row :gutter="20">
				 <div class="block" style="margin-bottom: 10px">
					<span class="demonstration" >
						<el-button type="primary" >根据系统日期查看工作表</el-button> </span>
					  <el-date-picker
							 v-model="time"
							 type="date"
							 @change="queryDataByTime()"
							 placeholder="选择日期">
					 </el-date-picker>
				 </div>
                </el-row>
			<!-- 用户列表区域 -->
			<el-table :data="tableData" border style="width: 100%">
				<el-table-column fixed prop="id" label="日常工作表id">
				</el-table-column>
				<el-table-column prop="weekly" label="周次">
				</el-table-column>
				<el-table-column prop="time" label="日期">
				</el-table-column>
				<el-table-column prop="userId" label="助理编号">
				</el-table-column>
				<el-table-column prop="content" label="今日工作内容">
				</el-table-column>
				<el-table-column prop="systemTime" label="系统时间">
				</el-table-column>
			</el-table>
		</el-card>
	</div>
</template>
<script>
	export default {
		methods: {

			queryDataByTime(){
				if (typeof(this.time) === "undefined"){	// 判断时间是否输入，为空的话显示提示
					console.log('没有选择日期，出现错误')
				} else {

					var oiTme = this.time.getFullYear()+"-"+this.time.getMonth()+"-"+this.time.getDay();
					console.log(oiTme);
					this.$axios.get('http://localhost:8083/daily/timeSearchLog/' + oiTme).then((resp) => {
						console.log(resp)
						this.tableData = resp.data.data
					})
				}
			},
			edit(row) {
				this.editDialogVisble = true;
				this.editForm = row

			},
			submitForm(formName) {
				const _this = this
				const userId = this.$session.get("userId")
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$axios.post('http://localhost:8083/daily/add/' + userId, this.addForm).then(function(resp) {
							if (resp.data == 'success') {
								_this.$message.success('添加成功');
								_this.addDialogVisble = false
								window.location.reload();

							}
						})
					} else {
						return false;
					}
				});
			},
			submitEditForm(formName) {
				const _this = this
				const userId = this.$session.get("userId")
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$axios.post('http://localhost:8083/daily/update', this.editForm).then(function(resp) {
							if (resp.data == 'success') {
								_this.$message.success('修改成功');
								_this.editDialogVisble = false

							}
						})
					} else {
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			handleClick(row) {
				console.log(row);
			},
			// 监听添加用户对话框的关闭事件
			addDialogClosed() {
				this.$refs.addFormRef.resetFields();
			},
			reviewDialogClosed() {
				this.reviewDialogVisble = false;
			},
		},

		data() {
			return {
				time: '',
				value1: '',
				userId: this.$session.get("userId"),
				total: null,
				tableData: [],
				//控制添加用户对话框的显示和隐藏
				addDialogVisble: false,
				//控制修改用户对话框的显示和隐藏
				editDialogVisble: false,
				// 添加用户的表单数据
				addForm: {
					recruitName: '',
					required: '',
					requirePeople: '',
					sectionId: '',
					laboratoryId: ''

				},
				// 修改用户的表单数据
				editForm: {
					recruitName: '',
					required: '',
					requirePeople: ''
				},
				// 添加表单的验证规则对象
				addFormRules: {
					recruitName: [{
							required: true,
							message: '请输入招聘计划名称',
							trigger: 'blur'
						},
						{
							min: 2,
							max: 50,
							message: '招聘计划名称长度在2个~到50个字符之间',
							trigger: 'blur'
						}
					],
					required: [{
							required: true,
							message: '请输入招聘要求',
							trigger: 'blur'
						},
						{
							min: 2,
							max: 100,
							message: '招聘计划名称长度在2个~到100个字符之间',
							trigger: 'blur'
						}
					],
					requirePeople: [{
							required: true,
							message: '请输入招聘人数',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 1000,
							message: '不能少于1个字符',
							trigger: 'blur'
						}
					]
				},
				// 修改表单的验证规则对象
				editFormRules: {
					recruitName: [{
							required: true,
							message: '请输入招聘计划名称',
							trigger: 'blur'
						},
						{
							min: 2,
							max: 50,
							message: '招聘计划名称长度在2个~到50个字符之间',
							trigger: 'blur'
						}
					],
					required: [{
							required: true,
							message: '请输入招聘要求',
							trigger: 'blur'
						},
						{
							min: 2,
							max: 100,
							message: '招聘计划名称长度在2个~到100个字符之间',
							trigger: 'blur'
						}
					],
					requirePeople: [{
							required: true,
							message: '请输入招聘人数',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 1000,
							message: '不能少于1个字符',
							trigger: 'blur'
						}
					]
				}
			}
		},
		created() {
			const _this = this
			const userId = this.$session.get("userId")
			this.$axios.get('http://localhost:8083/daily/list').then(function(resp) {
				_this.tableData = resp.data.data
				console.log(resp.data.data);
			})
		}


	}
</script>

<style scoped>

</style>
