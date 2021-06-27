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
                <el-table-column prop="sectionId" label="申请部门" :formatter="stateFormat">
                </el-table-column>
                <el-table-column prop="labId" label="实验室名称" :formatter="stateFormat2">
                </el-table-column>
                <el-table-column prop="status" label="状态" :formatter="TableStateFormat">
                </el-table-column>
                <el-table-column fixed="right" label="操作">
                    <template slot-scope="scope">
                        <div style="margin-bottom: 10px">
                            <el-button @click="entry(scope.row,2)" type="primary" icon="el-icon-edit">审核通过</el-button>
                        </div>
                        <el-button @click="entry(scope.row,1)" type="danger" icon="el-icon-delete">审核失败</el-button>
                    </template>
                </el-table-column>
            </el-table>


        </el-card>


    </div>
</template>

<script>
    export default {
        methods: {

            entry(row, num) {
                const _this = this
                this.$confirm('此操作将录入面试者, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.get('http://localhost:8083/recruit/check/' + row.id + '/' + num).then(function (resp) {
                        window.location.reload();

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


            handleClick(row) {
                console.log(row);
            },


        },

        data() {
            return {
                TableStateFormat(row, column) {
                    if (row.status === null) {
                        return '未审核'
                    } else if (row.status === 1) {
                        return '审核失败'
                    } else if (row.status === 2) {
                        return '审核通过'
                    }

                },
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


                total: null,
                tableData: [],

            }
        },
        created() {
            const _this = this
            this.$axios.get('http://localhost:8083/recruit/list').then(function (resp) {
                _this.tableData = resp.data.data
                console.log(resp.data.data);

            })
        }


    }
</script>

<style scoped>

</style>
