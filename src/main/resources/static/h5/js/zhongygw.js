
var dd = null;//保存上次树状结构展开的节点id

function close() {
    window.close();
}

//隐藏所有div
function cleanAllDiv(id) {
    document.getElementById("chushi").hidden = true
    document.getElementById("chushi").innerHTML="<div style=\"padding: 100px 0 0 0;\">欢迎使用全州教育系统主要岗位人员信息管理系统！</div>\n" +
        "        <div style=\"padding:  0 0 200px 0 ;\">请输入单位/学校或人员姓名关键字进行查询！</div>";
    document.getElementById("message").hidden = true
    document.getElementById("danweixx").hidden = true
    document.getElementById("treediv").hidden = true
    document.getElementById("table0").hidden = true
    document.getElementById("table0").style.height="40%";

    if (id != null && id != "") {
        document.getElementById(id).hidden = false
    }
    document.getElementById("bto01").innerHTML = "机构列表"
    document.getElementById("bto01").onclick = function () {
        getJiGou();
    }

}

//树状结构的展开与收缩
function show(d1) {
    if ($("#" + d1).length > 0) {
        if (dd == null || dd == d1) {//第一次点开树状结构，或者点击的是上次的节点
            if (document.getElementById(d1).style.display == 'none') {
                document.getElementById(d1).style.display = 'block'; //显示
                document.getElementById(d1+"brfore").className="divbeforedown";
            } else {
                document.getElementById(d1).style.display = 'none'; //隐藏
                document.getElementById(d1+"brfore").className="divbefore";
            }
        } else {
            //比较上次与本次节点所在的层数，同一层的展开新的，收缩旧的节点。下一层的直接展开，上一层的则收缩旧的展开新的
            var ddi = document.getElementById(dd.substring(0, dd.length - 1)).getAttribute("index")
            var d1i = document.getElementById(d1.substring(0, dd.length - 1)).getAttribute("index")
            if (Number(ddi) == Number(d1i)) {//同一层的展开与收回
                document.getElementById(dd).style.display = 'none'
                document.getElementById(dd+"brfore").className="divbefore";
                document.getElementById(d1).style.display = 'block'
                document.getElementById(d1+"brfore").className="divbeforedown";
            } else if (Number(ddi) < Number(d1i)) {//上一层不变，下一层展开
                document.getElementById(d1).style.display = 'block'
                document.getElementById(d1+"brfore").className="divbeforedown";
            } else if (Number(ddi) > Number(d1i)) {
                document.getElementById(dd).style.display = 'none';
                document.getElementById(dd+"brfore").className="divbefore";
                if (document.getElementById(d1).style.display == 'none') {
                    document.getElementById(d1).style.display = 'block'; //显示
                    document.getElementById(d1+"brfore").className="divbeforedown";
                } else {
                    document.getElementById(d1).style.display = 'none'; //隐藏
                    document.getElementById(dd+"brfore").className="divbefore";
                }
            }
        }
        dd = d1;
        // var treemenu=document.getElementById("treemenu")
        // var treemenu2=document.getElementById(d1)
        // alert(treemenu.scrollTop)
        // alert(treemenu2.scrollTop)
        // treemenu.scrollTo(0,0)
    }
}

//加载事件，如果是有人员姓名信息，则直接查询
window.onload = function () {

    var value = window.location.search;
    value = decodeURIComponent(value);
    //判断是人员还是单位以及是否有数据
    if (value.indexOf("uname") != -1) {
        var id = value.substring(value.indexOf("uname") + 6)
        getOne(id);
    } else if (value.indexOf("org") != -1) {
        var id = value.substring(value.indexOf("uname") + 6)
        getOne(id);
    }
    var screenHeight;
    if (window.innerHeight){
        screenHeight = window.innerHeight;
    } else if ((document.body) && (document.body.clientHeight)){
        screenHeight = document.body.clientHeight;
    }
    $("html,body").height(screenHeight);

}

//机构信息，对应的单位人员列表
function getJGRYXX(orgname) {
    // alert(document.getElementById("1705022102490001").getAttribute("index"))

    $.ajax({
        cache: true,
        type: "POST",
        data: {"org": orgname},
        url: "/dzgzzh/basedata/getDataByOrg",
        success: function (data) {
            document.getElementById("")
            var html = "<tr class='pure-table-odd'><td>单位</td><td>部门</td><td>姓名</td><td>职务</td></tr>"
            for (var i = 0; i < data.length; i++) {
                var classs = ""
                if (i % 2 == 1) {
                    classs = "class='pure-table-odd'"
                }
                html = html + "<tr " + classs + ">" +
                    "<td>" + data[i].org + "</td>" +
                    "<td>" + data[i].bumen + "</td>" +
                    "<td style='color: #0066cc' onclick=\"getOne(\'" + data[i].id + "\')\">" + data[i].uname + "</td>" +
                    "<td>" + data[i].zhiwu + "</td>" +
                    "</tr>"
            }
            document.getElementById("xgry").innerHTML = html
        }
    });

}

//机构信息树状结构
function getJiGou(gjz) {

    //document.getElementById("gjz").value=null
    $.ajax({
            cache: true,
            type: "POST",
            url: "/dzgzzh/basedata/getJiGou",
            success: function (data) {
                var keyList = Object.keys(data)

                //判断机构数据本身有数据
                if (keyList.length > 0) {
                    var hidden = ""
                    if (gjz != null) {
                        hidden = "hidden";
                    }
                    //存储有父节点的id，后续进行添加三角标记
                    var fuid=new Array();

                    //有查询的值的时候gjz，默认所有机构不显示，然后一一对比是否包含gjz，有则显示（包括显示父级机构）
                    for (var i = 0; i < keyList.length; i++) {
                        var org = data[keyList[i]]
                        //首节点,就是用户所在的单位
                        if (i == 0) {
                            var innerhtml = "<ul class=\"domtree\" >"
                            for (var j = 0; j < org.length; j++) {
                                innerhtml = innerhtml + "<li id='" + org[j].orgId + "'  index='" + i + "' >" +
                                    "<a href=\"javascript:onClick=show('" + org[j].orgId + "X')\" " +
                                    "onclick=\"getJGRYXX(\'" + org[j].orgName + "\')\">"
                                    + org[j].orgName + "</a></li>"

                            }
                            innerhtml = innerhtml + "</ul>"
                            cleanAllDiv()
                            document.getElementById("treediv").hidden = false
                            document.getElementById("treemenu").innerHTML = innerhtml
                        } else {//子节点，都包含
                            var map = new Map();
                            for (var j = 0; j < org.length; j++) {
                                var innerhtml;
                                if (map.containsKey(org[j].parentOrgId) == false) {//判断是否有父级节点，添加ul，否则直接添加li
                                    innerhtml = "<ul id=\"" + org[j].parentOrgId + "X\" style='display:none;' >"
                                        + "<li id='" + org[j].orgId + "' index='" + i + "' " + hidden + ">" +
                                        "<a href=\"javascript:onClick=show('" + org[j].orgId + "X')\" " +
                                        "onclick=\"getJGRYXX(\'" + org[j].orgName + "\')\">"
                                        + org[j].orgName + "</a></li>"
                                    map.put(org[j].parentOrgId, innerhtml);
                                    fuid.push(org[j].parentOrgId)
                                } else {
                                    var value = map.get(org[j].parentOrgId)
                                    innerhtml = "<li id='" + org[j].orgId + "' index='" + i + "' " + hidden + ">" +
                                        "<a href=\"javascript:onClick=show('" + org[j].orgId + "X')\" " +
                                        "onclick=\"getJGRYXX(\'" + org[j].orgName + "\')\">"
                                        + org[j].orgName + "</a></li>"
                                    map.remove(org[j].parentOrgId);
                                    map.put(org[j].parentOrgId, value + innerhtml);
                                }
                            }
                            var keys = map.keys();
                            for (var key in keys) {
                                document.getElementById(keys[key]).innerHTML =
                                    document.getElementById(keys[key]).innerHTML + map.get(keys[key]) + "</ul>"
                            }
                        }
                    }

                    //document.getElementById("1710180908100001X").style.display="block"

                    cleanAllDiv()
                    document.getElementById("treediv").hidden = false
                    document.getElementById("table0").hidden = false
                    //document.getElementById("titles").hidden = true

                    document.getElementById("titlesdiv").hidden = true
                    var html = "<tr class='pure-table-odd'><td>单位</td><td>部门</td><td>姓名</td><td>职务</td></tr>"
                    document.getElementById("xgry").innerHTML = html

                    var ids = new Array();
                    var pareids = new Array();

                    if (gjz != null) {
                        for (var i = keyList.length - 1; i >= 0; i--) {//从最底层开始判断，tmp保存父级id，用于上一层的判断。
                            var org = data[keyList[i]]
                            var tmpids = new Array();//存储上一次保存的父id，即本次判断是否存在的id

                            for (var t = 0; t < pareids.length; t++) {
                                if (t != 0 && pareids[t] != tmpids[tmpids.length - 1]) {
                                    tmpids.push(pareids[t]);
                                }
                            }
                            pareids = new Array();

                            for (var j = 0; j < org.length; j++) {
                                if (org[j].orgName.indexOf(gjz) != -1) {
                                    ids.push(org[j].orgId)
                                    pareids.push(org[j].parentOrgId)
                                }
                                if (tmpids.length > 0 && tmpids[0] != null) {
                                    for (var y = 0; y < tmpids.length; y++) {
                                        if (tmpids[y] == org[j].orgId) {
                                            ids.push(org[j].orgId)
                                            pareids.push(org[j].parentOrgId)
                                        }
                                    }
                                }
                            }
                        }


                        //父级节点
                        for (var m = 0; m < ids.length; m++) {
                            document.getElementById(ids[m]).hidden = false;
                        }
                        // document.getElementById("1709131012280001").hidden = false;

                        //document.getElementById(ids[0]+"X").style.display = 'block'; //显示
                        if (ids.length > 0) {


                            for (var i = 0; i < keyList.length; i++) {
                                var org = data[keyList[i]];
                                if (i == 0) {
                                    document.getElementById(org[0].orgId + "X").style.display = "block"
                                } else if (org.length == 1) {
                                    document.getElementById(org[0].orgId + "X").style.display = "block"
                                }
                            }
                        } else {
                            cleanAllDiv()
                            var chushi = "<div style='padding: 100px 0 222px 0;color: red'>未查询到相关的人员或者单位的具体信息！</div>"
                            document.getElementById("chushi").innerHTML = chushi
                            document.getElementById("chushi").hidden = false
                        }
                    }

                    //有下级结构的加上三角标记
                    for(var f=0;f<fuid.length;f++){
                        var ht=document.getElementById(fuid[f]).innerHTML
                        document.getElementById(fuid[f]).innerHTML= "<a class='divbefore' id='" + fuid[f] + "Xbrfore'" +
                            "href=\"javascript:onClick=show('" + fuid[f] + "X')\"></a>" +ht
                    }


                    document.getElementById("bto01").innerHTML = "返回首页"
                    document.getElementById("bto01").onclick = function () {
                        cleanAllDiv("chushi")
                    }
                }
            }
        }
    )
}

//自定义实现map
function Map() {
    this.elements = new Array();
    //获取MAP元素个数
    this.size = function () {
        return this.elements.length;
    }
    //判断MAP是否为空
    this.isEmpty = function () {
        return (this.elements.length < 1);
    }
    //删除MAP所有元素
    this.clear = function () {
        this.elements = new Array();
    }
    //向MAP中增加元素（key, value)
    this.put = function (_key, _value) {
        this.elements.push({
            key: _key,
            value: _value
        });
    }
    //删除指定KEY的元素，成功返回True，失败返回False
    this.remove = function (_key) {
        var bln = false;
        try {
            for (var i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    }
    //获取指定KEY的元素值VALUE，失败返回NULL
    this.get = function (_key) {
        try {
            for (var i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch (e) {
            return null;
        }
    }
    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
    this.element = function (_index) {
        if (_index < 0 || _index >= this.elements.length) {
            return null;
        }
        return this.elements[_index];
    }
    //判断MAP中是否含有指定KEY的元素
    this.containsKey = function (_key) {
        var bln = false;
        try {
            for (var i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    }
    //判断MAP中是否含有指定VALUE的元素
    this.containsValue = function (_value) {
        var bln = false;
        try {
            for (var i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    }
    //获取MAP中所有VALUE的数组（ARRAY）
    this.values = function () {
        var arr = new Array();
        for (var i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].value);
        }
        return arr;
    }
    //获取MAP中所有KEY的数组（ARRAY）
    this.keys = function () {
        var arr = new Array();
        for (var i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].key);
        }
        return arr;
    }
}

//input框回车事件
function keyup_submit(e) {
    var evt = window.event || e;
    if (evt.keyCode == 13) {
        //回车事件
        getList()
    }
}


//根据id精确查找人员信息
function getOne(id) {
    $.ajax({
            cache: true,
            type: "POST",
            url: "/dzgzzh/basedata/getTongByOne",
            data: {'id': id},
            success: function (data) {
                if (data.length == 0) {
                    var chushi = "<div style='padding: 100px 0 222px 0;color: red'>未查询到相关的人员或者单位的具体信息！</div>"
                    document.getElementById("chushi").innerHTML = chushi
                } else {
                    setData(data);
                }

            }
        }
    )
}

//设置人员详细信息
function setData(data) {


    cleanAllDiv()
    document.getElementById("message").hidden = false
    document.getElementById("table0").hidden = false
    document.getElementById("titlesdiv").hidden=false
    document.getElementById("titles").innerText = "本单位相关人员"

    document.getElementById("uname").innerText = data.uname
    document.getElementById("org").innerText = data.org

    var year = data.idcard.substring(6, 10)
    var month = data.idcard.substring(10, 12)
    var day = data.idcard.substring(12, 14)
    document.getElementById("year").innerText = year + "年" + month + "月" + day + "日"


    var mzxb = data.idcard.split("|");
    if(mzxb[1]!=null){
        document.getElementById("xingbie").innerText = mzxb[1]
    }
    if(mzxb[2]!=null){
        document.getElementById("minzu").innerText = mzxb[2]
    }


    if (data.zhiwu != null && data.zhiwu != "") {
        document.getElementById("zhiwu").innerText = data.zhiwu
    } else {
        document.getElementById("uname4").style.display = "none";
    }
    //xingbie minzu

    document.getElementById("phone").innerText = data.phone
    document.getElementById("orgphne").innerText = data.orgphne
    document.getElementById("img").src = "\\img\\picture\\" + data.userid + ".png"

    getDWpeople(data.org,40);

    //让两个并排的div的长度一致，避免下方div紧挨着短的div导致错位
    $("#message02").css("height", $("#message01").height())
}

//设置单位详细信息
function setDWData(data) {
    cleanAllDiv()
    document.getElementById("danweixx").hidden = false
    document.getElementById("titlesdiv").hidden=false
    document.getElementById("titles").innerText = "主要人员列表"

    document.getElementById("danwei").innerText = data.org
    document.getElementById("dwdh").innerText = data.orgphne

    //地址数据？
    document.getElementById("dz").innerText = data.orgplace

    getDWpeople(data.org,65);
}


//模糊查询
function getList() {
    document.getElementById("bto01").innerHTML = "机构列表"
    document.getElementById("bto01").onclick = function () {
        getJiGou();
    }
    var gjz = document.getElementById("gjz").value

    //去除所有空格
    gjz = gjz.replaceAll(" ", "")

    if (gjz == null || gjz == "") {
        layer.msg("请输入需要查询的人员或者单位名称！", {icon: 0})
        return false;
    }
    if (gjz.length < 2) {
        layer.msg("请输入至少2个汉字", {icon: 0})
        return false;
    }

    $.ajax({
        cache: true,
        type: "POST",
        url: "/dzgzzh/basedata/getList",
        data: {'gjz': gjz},
        success: function (data) {
            //type=0 1 2 分别表示人名、单位名、部门名

            //没有数据时
            if (data["data"].list.length == 0 && data["data"].type == 1) {
                getJiGou(gjz);
            }
            if (data["data"].list.length == 0) {
                cleanAllDiv()
                document.getElementById("chushi").hidden = false
                var chushi = "<div style='padding: 100px 0 222px 0;color: red'>未查询到相关的人员或者单位信息！</div>"
                document.getElementById("chushi").innerHTML = chushi
            } else if (data["data"].type == 0) {
                if (data["data"].list.length == 1) {
                    setData(data["data"].list[0]);
                } else if (data["data"].list.length > 1) {
                    cleanAllDiv()
                    document.getElementById("table0").hidden = false
                    document.getElementById("table0").style.height="75%";

                    document.getElementById("titlesdiv").hidden=false
                    document.getElementById("titles").innerText = "查询到多条结果，请选择具体人员进行查看";
                    var html = "<tr class='pure-table-odd'><td >姓名</td><td>单位</td><td>部门</td><td>职务</td><td>操作</td></tr>"
                    for (var i = 0; i < data["data"].list.length; i++) {
                        var classs = ""
                        if (i % 2 == 1) {
                            classs = "class='pure-table-odd'"
                        }
                        html = html + "<tr " + classs + ">" +
                            "<td>" + data["data"].list[i].uname + "</td>" +
                            "<td>" + data["data"].list[i].org + "</td>" +
                            "<td>" + data["data"].list[i].bumen + "</td>" +
                            "<td>" + data["data"].list[i].zhiwu + "</td>" +
                            "<td><a href='/h5/index2?uname=" + data["data"].list[i].id + "' >查看详情</a></td>" +
                            "</tr>"
                    }
                    document.getElementById("xgry").innerHTML = html
                }
            } else if (data["data"].type == 1) {
                //判断是否是一个单位，同一个单位多条数据就显示单位数据，多个单位则显示树状结构
                var orgname=data["data"].list[0].org;
                var boo=true;
                for(var x=0;x<data["data"].list.length;x++){
                    if(data["data"].list[x].org!=orgname){
                        boo=false;
                    }
                }
                if (boo == true) {
                    // if (data["data"].list.length == 1) {
                    setDWData(data["data"].list[0]);

                } else if (data["data"].list.length > 1) {
                    cleanAllDiv()
                    document.getElementById("treediv").hidden = false;
                    var hh = ""
                    document.getElementById("treemenu").innerHTML = hh
                    getJiGou(gjz);
                }
            }
        }
    })
}

//获取同单位其他人员信息并显示到页面下方表格中
function getDWpeople(orgname,height) {
    //根据单位名称获取同单位的其他人员
    $.ajax({
        cache: true,
        type: "POST",
        url: "/dzgzzh/basedata/getTongDanWeiList",
        data: {'orgname': orgname},
        success: function (data) {
            var html = "<tr class='pure-table-odd'><td>姓名</td><td>部门</td><td>职务</td><td>操作</td></tr>"
            for (var i = 0; i < data.length; i++) {
                var classs = ""
                if (i % 2 == 1) {
                    classs = "class='pure-table-odd'"
                }
                html = html + "<tr " + classs + ">" +
                    "<td>" + data[i].uname + "</td>" +
                    "<td>" + data[i].bumen + "</td>" +
                    "<td>" + data[i].zhiwu + "</td>" +
                    "<td><a href='/h5/index2?uname=" + data[i].id + "' >查看详情</a></td>" +
                    "</tr>"
            }
            document.getElementById("xgry").innerHTML = html
            document.getElementById("table0").hidden = false
            if(height!=null&&height!=""){
                document.getElementById("table0").style.height=height+"%";
            }
        }
    });
}

//图片放大
$(function () {
    $(".pic").click(function () {
        var _this = $(this);//将当前的pimg元素作为_this传入函数
        imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
    });
});

function imgShow(outerdiv, innerdiv, bigimg, _this) {
    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性
    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function () {
        var windowW = $(window).width();//获取当前窗口宽度
        var windowH = $(window).height();//获取当前窗口高度
        var realWidth = this.width;//获取图片真实宽度
        var realHeight = this.height;//获取图片真实高度
        var imgWidth, imgHeight;
        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
        if (realHeight > windowH * scale) {//判断图片高度
            imgHeight = windowH * scale;//如大于窗口高度，图片高度进行缩放
            imgWidth = imgHeight / realHeight * realWidth;//等比例缩放宽度
            if (imgWidth > windowW * scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW * scale;//再对宽度进行缩放
            }
        } else if (realWidth > windowW * scale) {//如图片高度合适，判断图片宽度
            imgWidth = windowW * scale;//如大于窗口宽度，图片宽度进行缩放
            imgHeight = imgWidth / realWidth * realHeight;//等比例缩放高度
        } else {//如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
        $(bigimg).css("width", imgWidth);//以最终的宽度对图片缩放
        var w = (windowW - imgWidth) / 2;//计算图片与窗口左边距
        var h = (windowH - imgHeight) / 2;//计算图片与窗口上边距
        $(innerdiv).css({"top": h, "left": w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
    });
    $(outerdiv).click(function () {//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
}

$(function () {

    var winH = $(window).height(); //获取当前视口高度

    var htmlH = $(document).innerHeight(); //获取当前html高度

    var headerH = $('header').innerHeight(); //获取当前头部高度

    var footerH = $('footer').innerHeight(); //获取当前底部高度
    //alert(winH + " " + htmlH + " " + headerH + " " + footerH)

    //document.getElementById("div0").style="height: "+winH +"px;"

    if (htmlH < winH) {  //进行判断
        $('main').height(winH - headerH - footerH); //设置主体内容高度
    }

})

