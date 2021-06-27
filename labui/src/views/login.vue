<template>
    <div id="mybg">
    <div>
        <el-card class="login-form-layout">
            <el-form autocomplete="on" :model="loginForm" ref="loginForm" label-position="left">
                <div style="text-align: center">
                    <svg-icon icon-class="login-mall" style="width: 56px;height: 56px;color: #409EFF"></svg-icon>
                </div>
                <h2 class="login-title color-main">实验室管理</h2>
                <el-form-item prop="userId">
                    <el-input name="userId" type="text" v-model="loginForm.userId" autocomplete="on"
                              placeholder="请输入用户名">
                        <!-- prop 和loginForm.userId 与name保持一致 -->
                        <span slot="prefix">
							<svg-icon icon-class="user" class="color-main"></svg-icon>
						</span>
                    </el-input>
                </el-form-item>

                <el-form-item prop="pwd">
                    <el-input name="pwd" :type="pwdType" @keyup.enter.native="handleLogin" v-model="loginForm.pwd"
                              autocomplete="on"
                              placeholder="请输入密码">
						<span slot="prefix">
							<svg-icon icon-class="password" class="color-main"></svg-icon>
						</span>
                        <span slot="suffix" @click="showPwd">
							<svg-icon icon-class="eye" class="color-main"></svg-icon>
						</span>
                    </el-input>
                </el-form-item>
                <el-form-item style="margin-bottom: 30px">
                    <el-button style="width: 100%" type="primary" :loading="loading"
                               @click.native.prevent="handleLogin">登录
                    </el-button>
                </el-form-item>
                <el-form-item style="margin-bottom: 30px">
                    <el-button style="width: 100%" type="primary" @click="regDialogVisble = true">注册</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <!-- 添加用户对话框 -->
        <el-dialog
                title="注册"
                :visible.sync="regDialogVisble"
                width="70%"
                @close="regDialogClosed">
            <!-- 内容主体区域 -->
            <el-form :model="regForm" ref="regFormRef" label-width="150px">
                <el-form-item label="用户学号" prop="userId">
                    <el-input v-model="regForm.userId"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名" prop="userName">
                    <el-input v-model="regForm.userName"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pwd">
                    <el-input v-model="regForm.pwd"></el-input>
                </el-form-item>
                <el-form-item label="照片" prop="picture">
                    <el-upload
                            class="avatar-uploader"
                            action="http://localhost:8083/userlogin/upload"
                            :show-file-list="true"
                            :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload">
                        <img v-if="picture" :src="picture" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>

                    <el-input v-model="regForm.picture"></el-input>
                </el-form-item>
                <el-form-item label="专业" prop="major">
                    <el-input v-model="regForm.major"></el-input>
                </el-form-item>
                <el-form-item label="学院" prop="college">
                    <el-input v-model="regForm.college"></el-input>
                </el-form-item>
                <el-form-item label="班级" prop="clas">
                    <el-input v-model="regForm.clas"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="regForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="regForm.email"></el-input>
                </el-form-item>
                <el-form-item label="年级" prop="grade">
                    <el-input v-model="regForm.grade"></el-input>
                </el-form-item>


            </el-form>
            <!-- 底部区域 -->
            <span slot="footer" class="dialog-footer">
				<el-button type="primary" @click="submitForm('regFormRef')">注册</el-button>
		        <el-button @click="resetForm('regFormRef')">重 置</el-button>
				<el-button @click="regDialogVisble=false">取 消</el-button>
				
		    </span>

        </el-dialog>
    </div>
    </div>
</template>

<script>
    export default {
        props: {},
        data() {
            return {
                file: null,
                f: null,
                loadImage: "",
                name: "login",
                loginForm: {
                    userId: "admin",
                    pwd: "123"
                },
                loading: false,
                pwdType: "password",
                tableData: [],
                // 注册用户的表单数据
                regForm: {
                    userId: '',
                    userName: '',
                    pwd: '',
                    picture: '',
                    major: '',
                    college: '',
                    clas: '',
                    phone: '',
                    email: '',
                    grade: '',

                },
                regDialogVisble: false,
                picture: '',


            };
        },
        methods: {
            // 图片上传
            handleAvatarSuccess(res, file) {
                this.picture = URL.createObjectURL(file.raw);
                this.regForm.picture = res
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            // 图片上传
			showPwd() {
                if (this.pwdType === "password") {
                    this.pwdType = "";
                } else {
                    this.pwdType = "password";
                }
            },
            handleLogin() {
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        this.loading = true;
                        this.$store /* 在user.js有关于 Login的方法 */
                            .dispatch("Login", this.loginForm)
                            /* then 指定回调函数之后，在回调函数中科院拿到数据 */
                            .then(response => {
                                this.loading = false;
                                let code = response.data.code;
                                let roleId = response.data.data.roleId
                                let userId = response.data.data.userId
                                this.$session.set("userId", userId)
                                //console.log(response.data.data);
                                if (code == 200) {
                                    if (roleId == 1) {

                                        console.log(roleId)
                                        this.$router.push({
                                            path: "/Stu",
                                            query: {
                                                data: response.data.data
                                            }

                                        });
                                    } else if (roleId == 2) {
                                        console.log(roleId);
                                        this.$router.push({
                                            path: "/Assitant",
                                            query: {
                                                data: response.data.data
                                            }

                                        });
                                    } else if (roleId == 3) {
                                        console.log(roleId);
                                        this.$router.push({
                                            path: "/Artisant",
                                            query: {
                                                data: response.data.data
                                            }

                                        });
                                    } else if (roleId == 4) {
                                        console.log(roleId);
                                        this.$router.push({
                                            path: "/Admin",
                                            query: {
                                                data: response.data.data
                                            }

                                        });
                                    }


                                } else {
                                    this.$router.push({
                                        path: "/error",
                                        query: {
                                            message: response.data.message
                                        }
                                    });
                                }
                            })
                            .catch(() => {
                                this.loading = false;
                            });
                    } else {
                        // eslint-disable-next-line no-console
                        console.log("参数验证不合法！");
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post('http://localhost:8083/User/reg', this.regForm).then(function (resp) {
                            if (resp.data.code == 200) {
                                _this.$message.success('注册成功');
                                _this.regDialogVisble = false
                                let userId = resp.data.data.userId
                                _this.$session.set("userId", userId)
                                _this.$router.push({
                                    path: "/Stu",
                                    query: {
                                        data: resp.data.data
                                    }

                                });
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },

		},



    };
</script>

<style scoped>
    #mybg{
        background:url("../img/1.jpg");
        width:100%;
        height:100%;
        position:fixed;
        background-size:100% 100%;}

    .login-form-layout {
        position: absolute;
        left: 0;
        right: 0;
        width: 360px;
        margin: 140px auto;
        border-top: 10px solid #409eff;
    }

    .login-title {
        text-align: center;
    }

    .login-center-layout {
        background: #409eff;
        width: auto;
        height: auto;
        max-width: 100%;
        max-height: 100%;
        margin-top: 200px;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>
