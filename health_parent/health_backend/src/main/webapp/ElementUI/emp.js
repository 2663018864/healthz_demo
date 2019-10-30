new Vue({
    el:"#app",
    data:{
        emp:{},
        empList:[]
    },
    methods:{
        findAll:function () {
            var _this = this;
            axios.get("/emp/findAll.do").then(function (response) {
                _this.empList = response.data;
            }).catch(function () {

            });
        },
        findOne:function (id) {
            var _this = this;
            axios.get("/emp/findOne.do",{
                params:{
                    id:id
                }
            }).then(function (response) {
                _this.emp = response.data;
                $("#myModal").modal("show");//让模态框显示
            });
        },
        save:function () {
            var _this = this;
            axios.post("/emp/save.do", _this.emp).then(function () {
                _this.findAll();
            });
        },
        update:function () {
            alert("haha");
            var _this = this;
            axios.post("/emp/update.do",{"emp":_this.emp}).then(function () {
                _this.findAll();
            });
        },
        delete:function (id) {
            var _this = this;
            axios.post("/emp/update.do",{"id":id}).then(function () {
                _this.findAll();
            });
        }
    },
    created(){
        this.findAll();
    }
});
