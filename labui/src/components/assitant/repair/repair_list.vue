<template>

	<div>

		<!-- 面包屑导航区域 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>系统设置</el-breadcrumb-item>
			<el-breadcrumb-item>设备维修管理{{userId}}</el-breadcrumb-item>
		</el-breadcrumb>
		<!-- 卡片视图区域 -->
		<el-card style="margin-top: 20px">
			<!-- 搜索与添加区域 -->

			<el-row :gutter="20">
				<el-col :span="7">

				</el-col>
				<el-col :span="4">
					<el-button type="primary" @click="addDialogVisble = true">添加设备维修</el-button>
				</el-col>
			</el-row>

			<!-- 用户列表区域 -->
			<el-table :data="tableData" border style="width: 100%">
				<el-table-column fixed prop="id" label="维修编号">
				</el-table-column>
				<el-table-column prop="eName" label="设备名称">
				</el-table-column>

				<el-table-column prop="descrip" label="损坏描述">
				</el-table-column>
				<el-table-column prop="fixTime" label="报修时间">
				</el-table-column>
				<el-table-column prop="userId" label="报修人id">
				</el-table-column>
				<el-table-column prop="userName" label="报修人姓名">
				</el-table-column>


				<el-table-column fixed="right" label="操作">
					<template slot-scope="scope">
						<el-button @click="edit(scope.row)" type="primary" icon="el-icon-edit">修改</el-button>
					</template>
				</el-table-column>
			</el-table>


		</el-card>
		<!-- 添加用户对话框 -->
		<el-dialog title="添加维修信息" :visible.sync="addDialogVisble" width="70%" @close="addDialogClosed">
			<!-- 内容主体区域 -->
			<el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="150px">
				<el-form-item label="设备名称" prop="eName">
					<el-input v-model="addForm.eName"></el-input>
				</el-form-item>

				<el-form-item label="描述" prop="descrip">
					<el-input v-model="addForm.descrip"></el-input>
				</el-form-item>

			</el-form>
			<!-- 底部区域 -->
			<span slot="footer" class="dialog-footer">
				<el-button type="primary" @click="submitForm('addFormRef')">添 加</el-button>
				<el-button @click="resetForm('addFormRef')">重 置</el-button>
				<el-button @click="addDialogVisble=false">取 消</el-button>
				<el-button @click="text()">text</el-button>
			</span>

		</el-dialog>

		<!--修改用户对话框-->
		<el-dialog title="修改招聘信息" :visible.sync="editDialogVisble" width="70%" @close="addDialogClosed">
			<!-- 内容主体区域 -->
			<el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="150px">

				<el-form-item label="设备名称" prop="eName">
					<el-input v-model="editForm.eName"></el-input>
				</el-form-item>

				<el-form-item label="描述" prop="descrip">
					<el-input v-model="editForm.descrip"></el-input>
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

<el-pagination :page-size="20" :pager-count="11" layout="prev, pager, next" :total="1000">
</el-pagination>
<script>
	export default {
		methods: {

			edit(row) {
				this.editDialogVisble = true;
				this.editForm = row

			},


			submitForm(formName) {
				const _this = this
				const userId = this.$session.get("userId")
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$axios.post('http://localhost:8083/repair/add/' + userId, this.addForm).then(function(resp) {
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
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$axios.post('http://localhost:8083/repair/update', this.editForm).then(function(resp) {
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
				userId: this.$session.get("userId"),
				total: null,
				tableData: [{
						id: '1',
						recruitName: '招收助理1',
						required: '吃苦耐劳'
					},
					{
						id: '2',
						recruitName: '招收助理2',
						required: '有一定的工作经验'
					},
				],
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
			const userId = this.$session.get("userId")
			const _this = this
			this.$axios.get('http://localhost:8083/repair/list/' + userId).then(function(resp) {
				_this.tableData = resp.data.data
				console.log(resp.data.data);

			})
		}


	}
</script>

<style scoped>

</style>
