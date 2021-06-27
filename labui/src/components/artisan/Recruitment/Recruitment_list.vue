<template>

	<div>

		<!-- 面包屑导航区域 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>系统设置</el-breadcrumb-item>
			<el-breadcrumb-item>招聘计划管理</el-breadcrumb-item>
		</el-breadcrumb>
		<!-- 卡片视图区域 -->
		<el-card style="margin-top: 20px">
			<!-- 搜索与添加区域 -->

			<el-row :gutter="20">

				<el-col :span="4" style="margin-bottom: 10px">
					<el-button type="primary" @click="addDialogVisble = true">发布招聘计划</el-button>
				</el-col>
			</el-row>

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
				<el-table-column prop="sectionId" label="申请部门":formatter="stateFormat2">
				</el-table-column>
				<el-table-column prop="labId" label="实验室名称":formatter="stateFormat3">
				</el-table-column>
				<el-table-column prop="status" label="状态" :formatter="TableStateFormat">
				</el-table-column>

				<el-table-column fixed="right" label="操作">
					<template slot-scope="scope">
						<div style="margin-bottom: 10px">
							<el-button @click="edit(scope.row)" type="primary" icon="el-icon-edit">修改</el-button>
						</div>
						<div style="margin-bottom: 10px">
							<el-button @click="opendelete(scope.row)" type="danger" icon="el-icon-delete">删除</el-button>
						</div>
							<el-button @click="review(scope.row)" type="warning" icon="el-icon-check">查看报名情况</el-button>
					</template>
				</el-table-column>
			</el-table>


		</el-card>
		<!-- 添加用户对话框 -->
		<el-dialog title="添加招聘信息" :visible.sync="addDialogVisble" width="70%" @close="addDialogClosed">
			<!-- 内容主体区域 -->
			<el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="150px">
				<el-form-item label="招聘计划名称" prop="planName">
					<el-input v-model="addForm.planName"></el-input>
				</el-form-item>
				<el-form-item label="招聘计划要求" prop="required">
					<el-input v-model="addForm.required"></el-input>
				</el-form-item>
				<el-form-item label="招聘人数" prop="requireNum">
					<el-input v-model="addForm.requireNum"></el-input>
				</el-form-item>
				<el-form-item label="申请部门" prop="sectionId">
					<el-input v-model="addForm.sectionId"></el-input>
					<el-select v-model="addForm.sectionId" placeholder="请选择" value="部门">
						<el-option
								v-for="item in options2"
								:key="item.value"
								:label="item.label"
								:value="item.value">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="实验室" prop="labId">
					<el-input v-model="addForm.labId"></el-input>
					<el-select v-model="addForm.labId" placeholder="请选择" value="实验室">
						<el-option
								v-for="item in options"
								:key="item.value"
								:label="item.label"
								:value="item.value">
						</el-option>
					</el-select>
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
		<!-- 求职人员 -->

		<el-dialog title="面试人员名单" :visible.sync="reviewDialogVisble" width="70%" @close="addDialogClosed">
			<!-- 内容主体区域 -->
			<el-table :data="reviewtableData" border style="width: 100%">
				<el-table-column fixed prop="id" label="招聘计划编号">
				</el-table-column>
				<el-table-column fixed prop="planName" label="招聘计划名称">
				</el-table-column>
				<el-table-column prop="userId" label="用户id">
				</el-table-column>
				<el-table-column prop="userName" label="面试者名字">
				</el-table-column>
				<el-table-column prop="status" label="状态" :formatter="stateFormat">
				</el-table-column>

				<el-table-column fixed="right" label="操作">
					<template slot-scope="scope">

						<div style="margin-bottom: 10px">
						<el-button @click="entry(scope.row,2)" type="primary" icon="el-icon-edit">同意</el-button>
						</div>
						<el-button @click="entry(scope.row,1)" type="danger" icon="el-icon-delete">拒绝</el-button>
					</template>
				</el-table-column>
			</el-table>

		</el-dialog>

	</div>
</template>

<script>
	export default {
		methods: {
			text() {
				console.log(this.addForm)
			},
			//打开删除对话框方法
			opendelete(row) {
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete('http://localhost:8083/recruit/deleteById/' + row.id).then(function(resp) {

						}),
						window.location.reload();
					this.$message({
						type: 'success',
						message: '删除成功!'
					});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			edit(row) {
				this.editDialogVisble = true;
				this.editForm = row

			},
			review: function(row) {
				this.reviewDialogVisble = true
				/* this问题，函数不是=>箭头函数，获取到的this不是vue实例*/
				this.$axios.get('http://localhost:8083/form/review/' + row.id).then(resp => {
					/* this.$axios.get('http://localhost:8083/recruit/review/'+row.id).then(function(resp){ */
					const _this = this

					_this.reviewtableData = resp.data.data
					console.log(resp.data.data);
				});


			},
			entry(row, num) {
				const _this = this
				this.$confirm('此操作将录入面试者, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.get('http://localhost:8083/form/interview/' + row.planId + '/' + row.userId + '/' + num).then(
							function(resp) {
								row.id = row.planId
								_this.review(row);

							}),

						this.$message({
							type: 'success',
							message: '录入成功!'
						});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消录入'
					});
				});

			},

			submitForm(formName) {
				const _this = this
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$axios.post('http://localhost:8083/recruit/add', this.addForm).then(function(resp) {
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
						this.$axios.post('http://localhost:8083/recruit/update', this.editForm).then(function(resp) {
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
				options: [{
					value: '101',
					label: '网络科学'
				}, {
					value: '102',
					label: '软件开发中心'
				}, {
					value: '105',
					label: '数字媒体实验室'
				}, {
					value: '108',
					label: '计算机科学实验室'
				}],
				options2: [{
					value: '1',
					label: '后勤部'
				}, {
					value: '2',
					label: '开发部'
				}],
				stateFormat2(row, column) {
					if (row.sectionId === 2) {
						return '开发部'
					}else {
						return '后勤部'
					}

				},
				stateFormat3(row, column) {
					if (row.labId === 101) {
						return '网络科学'
					} else if (row.labId === 102) {
						return '软件开发中心'
					}else if (row.labId === 105) {
						return '数字媒体实验室'
					}else if (row.labId === 108) {
						return '计算机科学实验室'
					}
				},
				stateFormat(row, column) {
					if (row.status === null) {
						return '未录取'
					} else if (row.status === 1) {
						return '面试失败'
					} else if (row.status === 2) {
						return '面试通过'
					}

				},
				TableStateFormat(row, column) {
					if (row.status === null) {
						return '未审核'
					} else if (row.status === 1) {
						return '审核失败'
					} else if (row.status === 2) {
						return '审核通过'
					}

				},


				total: null,
				tableData: [{
					id: '1',
					planName: '招收助理1',
					required: '吃苦耐劳'
				}, {
					id: '2',
					planName: '招收助理2',
					required: '有一定的工作经验'
				}, ],
				reviewtableData: [{
					id: '1',
					planId: '1',
					planName: '假数据',
					userId: '1',
					userName: 'lcj',
					status: '无'
				}],
				//控制添加用户对话框的显示和隐藏
				addDialogVisble: false,

				//控制修改用户对话框的显示和隐藏
				editDialogVisble: false,
				//面试人员对话框的显示和隐藏
				reviewDialogVisble: false,

				// 添加用户的表单数据
				addForm: {
					planName: '',
					required: '',
					requireNum: '',
					sectionId: '',
					labId: ''

				},
				// 修改用户的表单数据
				editForm: {
					planName: '',
					required: '',
					requireNum: ''
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
							min: 2,
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
			const _this = this
			this.$axios.get('http://localhost:8083/recruit/list').then(function(resp) {
				_this.tableData = resp.data.data
				console.log(resp.data.data);

			})
		}


	}
</script>

<style scoped>

</style>
