import Vue from 'vue'
import VueRoute from 'vue-router'
import Welcome from '../views/Welcome.vue'

Vue.use(VueRoute);

const routes = [
    //配置默认的路径，默认显示登录页
    {
        path: '/',
        component: () => import('@/views/login')
    },


    {
        path: '/Stu',
        component: () => import('@/views/Stu'),
        /* 		  redirect:'/welcome',*/
        children: [{
            path: '/welcome',
            component: Welcome
        },
            {
                path: '/visitRecruitment_list',
                component: () => import('@/components/stu/Recruitment/visitRecruitment_list')
            }
        ]
    },

    {
        path: '/Artisant',
        component: () => import('@/views/Artisant'),
        /*redirect:'/welcome',*/
        children: [{
            path: '/welcome',
            component: Welcome
        },
            {
                path: '/Recruitment_list',
                component: () => import('@/components/artisan/Recruitment/Recruitment_list')
            },
            {
                path: '/ArtCheckRepair_list',
                component: () => import('@/components/artisan/repair/repair_list')
            },
            {
                path: '/ArtCheckJob_list',
                component: () => import('@/components/artisan/dailyjob/daily_list')
            },
            {
                path: '/manage_list',
                component: () => import('@/components/artisan/manage/manage_list')
            }
        ]
    },

    {
        path: '/Assitant',
        component: () => import('@/views/Assitant'),
        /*redirect:'/welcome',*/
        children: [{
            path: '/daily_list',
            component: () => import('@/components/assitant/dailyjob/daily_list')
        },
            {
                path: '/repair_list',
                component: () => import('@/components/assitant/repair/repair_list')
            }
        ]
    },

    {
        path: '/Admin',
        component: () => import('@/views/Admin'),
        /*redirect:'/welcome',*/
        children: [{
            path: '/user_list',
            component: () => import('@/components/admin/user_list')
        },
            {
                path: '/check_list',
                component: () => import('@/components/admin/check_list')
            },
            {
                path: '/lab_list',
                component: () => import('@/components/admin/lab_list')
            }

        ]
    },

    //配置登录失败页面，使用时需要使用 path 路径来实现跳转
    {
        path: '/error',
        component: () => import('@/views/error'),
        hidden: true
    },


]

const artisanRouter = [{
    path: '/Artisant',
    name: "招聘管理",
    icon: "el-icon-setting",
    children: [{
        path: '/Recruitment_list',
        name: "发布招聘计划",

    }, {
        path: '/ArtCheckRepair_list',
        name: "查看设备申请维修",
    }, {
        path: '/ArtCheckJob_list',
        name: "查看助理工作",
    },
        {
            path: '/manage_list',
            name: "人员档案管理",
        }
    ]
}]

const stuRouter = [{
    path: '/Stu',
    name: "招聘信息列表",
    icon: "el-icon-setting",
    children: [{
        path: '/visitRecruitment_list',
        name: "查看招聘计划",

    }

    ]
}]

const assitantRouter = [

    {
        path: '/Assitant',
        name: "助理工作登记",
        icon: "el-icon-setting",
        children: [{
            path: '/daily_list',
            name: "每日工作记录",

        },
            {
                path: '/repair_list',
                name: "设备维修",

            }

        ]
    }
]
const adminRouter = [

    {
        name: "管理模块",
        icon: "el-icon-setting",
        children: [{
            path: '/user_list',
            name: "用户管理",
        },
            {
                path: '/check_list',
                name: "审核招聘计划",

            },
            {
                path: '/lab_list',
                name: "实验室管理",

            }

        ]
    }
]

const router = new VueRoute({
    mode: 'history', // 去掉url中的#
    routes,
    artisanRouter,
    stuRouter,
    assitantRouter,
    adminRouter,
})

export default router

