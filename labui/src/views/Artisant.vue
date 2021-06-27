<template>
    <el-container class="home-container">
        <!-- 头部区域-->
        <el-header>
            <div>
                <span>实验室系统------>技术人员</span>
            </div>
            <el-button type="danger" @click="returnIndex"  >退出</el-button>

        </el-header>
        <!--页面主体区域-->
        <el-container>
            <!--侧边栏-->
            <el-aside width="200px">
                <!--侧边栏菜单区域-->
                <el-menu background-color="#f2f9fd" router>
                    <!-- 一级菜单 -->
					 <el-submenu v-for="(item,index) in $router.options.artisanRouter" :index="index+''" >
                   
                        <!--一级菜单的模板区域-->
                        <template slot="title">
                            <!--图标-->
                            <i :class="item.icon"></i>
                            <!-- 文本 -->
                            <span>{{item.name}}</span>
                        </template>

                        <!-- 二级菜单 -->
                            <el-menu-item v-for="(item2,index2) in item.children" :index="item2.path">{{item2.name}}</el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <!--右侧内容主体-->
            <el-main>
                <!--路由占位符-->
                <router-view ></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
   

    export default {
        methods:{
            returnIndex(){
				this.$session.destroy()
                window.location.href="/"
            }

        },
        data(){
            return{
                msg: this.$route.query.data.roleId
                
            }
        }
       
    }
</script>

<style lang="less" scoped>
    .home-container{
        height:100%;
		
    }
    .el-header{
        background-color: #0e78ac;
        display: flex;
        justify-content: space-between;
        padding-left: 0;
        align-items: center;
        color: #fff;
        font-size: 24px;

        span{
            margin-left: 15px;
        }
    }
    
    .el-aside{
        background-color: #f2f9fd;
    }

    .el-main{
        background-color: #EAEDF1;
		
    }

</style>