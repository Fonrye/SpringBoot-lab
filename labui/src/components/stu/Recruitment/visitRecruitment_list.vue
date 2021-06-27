<template>

	<div>

		<!-- 面包屑导航区域 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>系统设置</el-breadcrumb-item>
			<el-breadcrumb-item>招聘计划管理{{userId}}</el-breadcrumb-item>
		</el-breadcrumb>
		<!-- 卡片视图区域 -->
		<el-card style="width: 1450px;margin-top: 20px">

			<!-- 用户列表区域 -->
			<el-table :data="tableData" border style="width: 100%">
				<el-table-column fixed prop="id" label="招聘计划编号">
				</el-table-column>
				<el-table-column prop="planName" label="招聘计划名称">
				</el-table-column>
				<el-table-column prop="required" label="招聘计划要求">
				</el-table-column>
				<el-table-column prop="requireNum" label="招聘人数">
				</el-table-column>
				<el-table-column prop="sectionId" label="申请部门":formatter="stateFormat">
				</el-table-column>
				<el-table-column prop="labId" label="实验室名称":formatter="stateFormat2">
				</el-table-column>

				<el-table-column fixed="right" label="操作">
					<template slot-scope="scope">
						<el-button @click="signup(scope.row,userId)" type="primary" icon="el-icon-edit">报名</el-button>
					</template>
				</el-table-column>
			</el-table>


		</el-card>
		<!--修改用户对话框-->
		<el-dialog title="报名招聘信息" :visible.sync="editDialogVisble" width="70%" @close="addDialogClosed">
			<!-- 内容主体区域 -->
			<el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="150px">
				<el-form-item label="招聘计划名称" prop="planName">
					<el-input v-model="editForm.planName"></el-input>
				</el-form-item>
				<el-form-item label="招聘计划要求" prop="required">
					<el-input v-model="editForm.required"></el-input>
				</el-form-item>
				<el-form-item label="招聘人数" prop="requireNum">
					<el-input v-model="editForm.requireNum"></el-input>
				</el-form-item>

			</el-form>
			<!-- 底部区域 -->
			<span slot="footer" class="dialog-footer">
				<el-button type="primary" @click="submitEditForm('editFormRef')">修 改</el-button>
				<el-button @click="editDialogVisble=false">取 消</el-button>
			</span>
		</el-dialog>

	</div>
</template>

<script>
	export default {
		methods: {
			stateFormat(row, column) {
				if (row.sectionId === 1) {
					return '开发部'
				} else {
					return '后勤部'
				}

			},
			stateFormat2(row, column) {
				if (row.labId === 10010) {
					return '软件开发中心'
				} else if (row.labId ===101) {
					return '网络科学'
				} else if (row.labId ===105) {
					return '数字媒体实验室'
				} else if (row.labId ===108) {
					return '计算机科学实验室'
				}else {
					return '通用实验室'
				}
			},
			text() {
				console.log(this.addForm)
			}, //打开删除对话框方法
			signup(row, userId) {
				const _this = this;
						this.$confirm('报名此招聘, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.get('http://localhost:8083/form/signUp/' + row.id + '/' + userId).then(function(resp) {
						if (resp.data === 'success') {
							_this.$message.success('报名成功');
							window.location.reload();
						} else if (resp.data === 'isExist') {
							_this.$message.success('重复报名，报名失败');

						}

					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消报名'
					});
				});
			}

			,
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
			//监听修改用户对话框的关闭事件

		},

		data() {
			return {
				userId: this.$session.get("userId"),
				total: null,
				tableData: [],
				//控制添加用户对话框的显示和隐藏
				addDialogVisble: false,

				//控制修改用户对话框的显示和隐藏
				editDialogVisble: false,

				// 添加用户的表单数据
				addForm: {


				},
				// 修改用户的表单数据
				editForm: {

				},
				// 添加表单的验证规则对象
				addFormRules: {
					planName: [{
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
							min: 1,
							max: 100,
							message: '招聘计划名称长度在2个~到100个字符之间',
							trigger: 'blur'
						}
					],
					requireNum: [{
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
					planName: [{
							required: true,
							message: '请输入招聘计划名称',
							trigger: 'blur'
						},
						{
							min: 1,
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
							min: 1,
							max: 100,
							message: '招聘计划名称长度在2个~到100个字符之间',
							trigger: 'blur'
						}
					],
					requireNum: [{
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
			const _this = this;
			this.$axios.get('http://localhost:8083/recruit/studentgetlist').then(function(resp) {
				_this.tableData = resp.data.data;
				console.log(resp.data.data);

			})
		}


	}
</script>

<style scoped>

</style>
