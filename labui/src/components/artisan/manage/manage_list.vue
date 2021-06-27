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

            <!-- 列表区域 -->
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column fixed prop="id" label="编号">
                </el-table-column>
                <el-table-column prop="userName" label="真实姓名">
                </el-table-column>
                <el-table-column prop="picture" label="照片">
                    <template slot-scope="scope">
                        <div class="block"  >
                            <el-image
                                    style="width: 100px; height: 100px"
                                    :src="'http://localhost:8083/userlogin/getImage/'+scope.row.picture"
                                    :fit="fit">

                            </el-image>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="major" label="专业">
                </el-table-column>
                <el-table-column fixed prop="college" label="学院">
                </el-table-column>
                <el-table-column prop="clas" label="班级">
                </el-table-column>
                <el-table-column prop="phone" label="电话">
                </el-table-column>
                <el-table-column prop="email" label="邮箱">
                </el-table-column>
                <el-table-column fixed prop="grade" label="年级">
                </el-table-column>
                <el-table-column fixed prop="sectionId" label="年级" :formatter="stateFormat">
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

                stateFormat(row, column) {
                    if (row.sectionId === 1) {
                        return '开发部'
                    } else {
                        return '后勤部'
                    }

                },

            }
        },
        created() {
            const userId = this.$session.get("userId")
            const _this = this
            this.$axios.get('http://localhost:8083/User/getUserList2').then(function (resp) {
                _this.tableData = resp.data;
                console.log(resp.data);

            })
        }


    }
</script>

<style scoped>

</style>
