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
				<el-col :span="4" style="margin-bottom: 10px">
					<el-button type="primary" @click="export2Excel()">导出设备维修</el-button>
				</el-col>
			</el-row>

			<!-- 列表区域 -->
			<el-table :data="tableData" border style="width: 100%">
				<el-table-column fixed prop="id" label="维修编号">
				</el-table-column>
				<el-table-column prop="eName" label="设备名称">
				</el-table-column>

				<el-table-column prop="descrip" label="损坏描述">
				</el-table-column>
				<el-table-column prop="fixTime" label="报修时间">
				</el-table-column>


			</el-table>
		</el-card>
	</div>
</template>


<script>
	export default {
		methods: {

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
			export2Excel() {
				require.ensure([], () => {
					const {
						export_json_to_excel
					} = require('@/vendor/Export2Excel');
					//头
					const tHeader = ['维修编号', '设备名称', '损坏描述', '报修时间'];
					//对应的标签
					const filterVal = ['id', 'eName', 'descrip', 'fixTime'];
					//标签对应的内容  是一个数组结构            
					const list = this.tableData;
					//一个方法 我也不知道干嘛的
					const data = this.formatJson(filterVal, list);
					export_json_to_excel(tHeader, data, '导出的报修表格');
				})
			},
			formatJson(filterVal, jsonData) {
				return jsonData.map(v => filterVal.map(j => v[j]))
			}

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

			}
		},
		created() {
			const userId = this.$session.get("userId")
			const _this = this
			this.$axios.get('http://localhost:8083/repair/list').then(function(resp) {
				_this.tableData = resp.data.data
				console.log(resp.data.data);

			})
		}


	}
</script>

<style scoped>

</style>
