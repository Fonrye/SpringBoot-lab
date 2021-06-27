<template>

	<div>

		<!-- 面包屑导航区域 -->
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>系统设置</el-breadcrumb-item>
			<el-breadcrumb-item>用户管理{{userId}}</el-breadcrumb-item>
		</el-breadcrumb>
		<!-- 卡片视图区域 -->
		<el-card style="margin-top: 20px">
			<!-- 搜索与添加区域 -->

			<el-row :gutter="20">

				<el-col :span="4">
					<el-button type="primary" @click="addDialogVisble = true">添加用户</el-button>
				</el-col>
			</el-row>

			<!-- 用户列表区域 -->
			<el-table :data="tableData" border style="width: 100%">
				<el-table-column fixed prop="id" label="id">
				</el-table-column>
				<el-table-column prop="userId" label="用户id">
				</el-table-column>
				<el-table-column fixed prop="userName" label="用户姓名">
				</el-table-column>
				<el-table-column prop="pwd" label="用户密码">
				</el-table-column>
				<el-table-column prop="roleId" label="用户类型":formatter="stateFormat">
				</el-table-column>
				<el-table-column fixed="right" label="操作">
					<template slot-scope="scope">
						<el-button @click="edit(scope.row)" type="primary" icon="el-icon-edit">修改</el-button>
						<el-button @click="opendelete(scope.row)" type="danger" icon="el-icon-delete">删除</el-button>
					</template>
				</el-table-column>
			</el-table>


		</el-card>
		<!-- 添加用户对话框 -->
		<el-dialog title="添加用户" :visible.sync="addDialogVisble" width="70%" @close="addDialogClosed">
			<!-- 内容主体区域 -->
			<el-form :model="addForm" ref="addFormRef" label-width="150px">

				<el-form-item label="用户id" prop="userId">
					<el-input v-model="addForm.userId"></el-input>
				</el-form-item>
				<el-form-item label="用户姓名" prop="userName">
					<el-input v-model="addForm.userName"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="pwd">
					<el-input v-model="addForm.pwd"></el-input>
				</el-form-item>
				<el-form-item label="用户类型" prop="roleId">
					<el-input disabled="disabled" v-model="addForm.roleId"></el-input>
					  <el-select v-model="addForm.roleId" placeholder="请选择">
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

			</span>

		</el-dialog>

		<!-- 修改用户对话框 -->
		<el-dialog title="修改用户" :visible.sync="editDialogVisble" width="70%" @close="addDialogClosed">
			<!-- 内容主体区域 -->
			<el-form :model="editForm" ref="editFormRef" label-width="150px">
		
				<el-form-item label="用户id" prop="userId">
					<el-input disabled="disabled"v-model="editForm.userId"></el-input>
				</el-form-item>
				<el-form-item label="用户姓名" prop="userName">
					<el-input v-model="editForm.userName"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="pwd">
					<el-input v-model="editForm.pwd"></el-input>
				</el-form-item>
				<el-form-item label="用户类型" prop="roleId">
					<el-input disabled="disabled" v-model="editForm.roleId"></el-input>
					  <el-select v-model="editForm.roleId" placeholder="请选择">
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
				<el-button type="primary" @click="submitEditForm('editFormRef')">修 改</el-button>
				
				<el-button @click="editDialogVisble=false">取 消</el-button>
		
			</span>
		
		</el-dialog>



	</div>
</template>


<script>
	export default {
		methods: {

			edit(row) {
				this.editDialogVisble = true;
				this.editForm = row

			},
			//打开删除对话框方法
			opendelete(row) {
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete('http://localhost:8083/User/deleteById/' + row.userId).then(function(resp) {

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

			submitForm(formName) {
				const _this = this
				const userId = this.$session.get("userId")
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$axios.post('http://localhost:8083/User/save', this.addForm).then(function(resp) {
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
						this.$axios.post('http://localhost:8083/User/update', this.editForm).then(function(resp) {
							if (resp.data == 'success') {
								_this.$message.success('修改成功');
								_this.editDialogVisble = false
								window.location.reload();

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
				          value: '1',
				          label: '学生'
				        }, {
				          value: '2',
				          label: '助理'
				        }, {
				          value: '3',
				          label: '实验室技术人员'
				        }, {
				          value: '4',
				          label: '管理员'
				        }],
				        value: ''
						,
				stateFormat(row, column) {
				    if (row.roleId === 1) {
				      return '学生'
				    } else if(row.roleId === 2) {
				      return '助理'
				    } else if(row.roleId === 3) {
				      return '实验室技术人员'
				    }  else if(row.roleId === 4) {
				      return '管理员'
				    } 
					
				},
				userId: this.$session.get("userId"),

				total: null,
				tableData: [],
				//控制添加用户对话框的显示和隐藏
				addDialogVisble: false,

				//控制修改用户对话框的显示和隐藏
				editDialogVisble: false,


				// 添加用户的表单数据
				addForm: {
					// recruitName: '',
					// required: '',
					// requirePeople: '',
					// sectionId: '',
					// laboratoryId: ''
				},

				// 修改用户的表单数据
				editForm: {
					// recruitName: '',
					// required: '',
					// requirePeople: ''
				}
			}
		},
		created() {
			const _this = this
			const userId = this.$session.get("userId")
			this.$axios.get('http://localhost:8083/User/getUserList').then(function(resp) {
				_this.tableData = resp.data
				console.log(resp.data);

			})
		}


	}
</script>

<style scoped>

</style>
