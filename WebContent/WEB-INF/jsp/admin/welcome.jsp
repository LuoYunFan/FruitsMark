<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>主页</title>
    <link rel="stylesheet" href="resources/css/layui.css">
    <link rel="stylesheet" href="resources/css/admin.css">
    <script src="resources/js/jquery.1.12.4.min.js"></script>
    <script src="resources/js/layui.js"></script>
    <script src="resources/js/echarts.js"></script>
    <script src="resources/js/wonderland.js"></script>
    <style type="text/css">
        .layui-col-xs6 {
            width: 220px;
            height: 90px;
        }

        .layadmin-backlog-body {
            width: 180px;
            height: 90px;
        }
    </style>
</head>
<body style="background-color:#F2F2F2;padding-top:15px;padding-left:15px;padding-bottom:0px;">
<div class="layui-card" style="width: 530px; height: 195px;float:left;">
    <div class="layui-card-header">待办事件</div>
    <div class="layui-card-body" id="todo" style="width: 440px; height: 120px;">
        <div class="layadmin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none"
             style="width: 100%; height: 80px;">
            <li class="layui-col-xs6">
                <a class="layadmin-backlog-body">
                    <h3>总订单量</h3>
                    <p><cite style="line-height:45px;" id="totalOrder"></cite></p>
                </a>
            </li>
            <li class="layui-col-xs6">
                <a class="layadmin-backlog-body">
                    <h3>待发货</h3>
                    <p><cite style="line-height:45px;" id="toDoOrder"></cite></p>
                </a>
            </li>
        </div>
    </div>
</div>
<div class="layui-card" style="width: 600px; height: 195px;float:left;margin-left:15px;">
    <!-- <div class="layui-card-header">热门商品浏览量</div> -->
    <div class="layui-card-body" id="hot" style="width: 560px; height:180px;"></div>
</div>
<div class="layui-card" style="width: 530px; height: 310px;float:left;">
    <div class="layui-card-header">销售额及订单量趋势</div>
    <div class="layui-card-body" id="order" style="width: 530px; height: 260px;"></div>
</div>
<div class="layui-card" style="width: 600px; height: 310px;float:left;margin-left:15px;">
    <div class="layui-card-header">手机销量排行</div>
    <div class="layui-card-body" id="volume" style="width: 580px; height: 260px;"></div>
</div>
</body>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    $(function () {
        showToDo();
        var myChart = echarts.init(document.getElementById('volume'), 'wonderland');
        var orderChart = echarts.init(document.getElementById('order'), 'wonderland');
        var hotChart = echarts.init(document.getElementById('hot'), 'wonderland');
        var layer;
        layui.use('layer', function () {  //弹出层
            layer = layui.layer;
        });
        // 指定图表的配置项和数据
        myChart.setOption({
            tooltip: {},//提示框组件
            legend: {},//图例组件
            xAxis: { //直角坐标系 grid 中的 x 轴
                data: [],
                axisLabel: {//坐标轴刻度标签的相关设置
                    interval: 0//横轴信息全部显示
                }
            },

            yAxis: {},//直角坐标系 grid 中的 y 轴

            series: [{  //系列列表。每个系列通过 type 决定自己的图表类型
                type: 'bar', //柱状/条形图
                data: []
            }]
        });

        orderChart.setOption({
            tooltip: {
                trigger: 'axis'  //坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
            },
            //图例名
            legend: {
                data: ['销售额', '订单量']
            },
            //工具框，可以选择
            toolbox: {},
            //x轴信息样式
            xAxis: {
                type: 'category',//类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据
                boundaryGap: false, //类目轴中 boundaryGap 可以配置为 true 和 false。默认为 true，这时候刻度只是作为分隔线，标签和数据点都会在两个刻度之间的带(band)中间
                data: [],
                axisLabel: {
                    interval: 0
                },
            },

            yAxis: [
                {
                    type: 'value' //数值轴，适用于连续数据
                }
            ],
            series: [
                //虚线
                {
                    name: '订单量',
                    type: 'line',//折线图是用折线将各个数据点标志连接起来的图表，用于展现数据的变化趋势
                    symbolSize: 4,   //拐点圆的大小
                    data: [],
                    smooth: false,   //关键点，为true是不支持虚线的，实线就用true
                    itemStyle: {//折线拐点标志的样式
                        normal: {
                            lineStyle: {
                                width: 2,
                                type: 'dotted'  //'dotted'虚线 'solid'实线
                            }
                        }
                    }
                },
                //实线
                {
                    name: '销售额',
                    type: 'line',
                    symbol: 'circle',//ECharts 提供的标记类型包括 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow', 'none'
                    symbolSize: 4,
                    data: []
                }
            ]
        });

        hotChart.setOption({
            title: {
                text: '热门商品浏览量',
                x: 'center'
            },
            tooltip: {
                trigger: 'item', //数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用
                formatter: "{a} <br/>{b} : {c} ({d}%)" //提示框浮层内容格式器，支持字符串模板和回调函数两种形式
                //饼图、仪表盘、漏斗图: {a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
                //浏览量(换行)iphone7 Plus:20(10%)
            },
            legend: {
                orient: 'vertical', //图例列表的布局朝向，垂直
                x: 'left',
                data: []
            },
            toolbox: {
                show: true,//是否显示工具栏组件
                feature: {//各工具配置项
                    mark: {show: true},
                    magicType: {
                        show: true,
                        type: ['pie', 'funnel'],
                        option: {//各个类型的专有配置项
                            funnel: {
                                x: '25%',
                                width: '50%',
                                funnelAlign: 'left',
                                max: 1548
                            }
                        }
                    },
                    restore: {show: true} //配置项还原
                }
            },
            series: [{
                type: 'pie',
                name: "浏览量",
                radius: '55%',//半径
                center: ['50%', '60%'],//极坐标系的中心（圆心）坐标，数组的第一项是横坐标，第二项是纵坐标
                data: []
            }]
        });

        myChart.showLoading(); //商品销量柱状图
        $.ajax({
            type: "post",
            url: "goods/findGoodsByVolume",
            dataType: "json",
            success: function (res) {
                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({        //加载数据图表
                    xAxis: {
                        data: res.name
                    },
                    series: [{
                        type: 'bar',
                        data: res.volume
                    }]
                });
            },
            error: function () {
                layer.msg("图表数据请求失败！", {icon: 5, anim: 6, time: 5000});
                myChart.hideLoading();
            }
        });
        $.ajax({
            type: "post",
            url: "order/findTotalOrder",
            dataType: "json",
            success: function (res) {
                orderChart.setOption({        //加载数据图表
                    xAxis: {
                        data: res.month
                    },
                    series: [{
                        name: '订单量',
                        type: 'line',
                        data: res.sheets
                    },
                        {
                            name: '销售额',
                            type: 'line',
                            symbol: 'circle',
                            data: res.total
                        }
                    ]
                });
            }
        });
        var names = [];
        var brower = [];
        $.ajax({
            type: "post",
            url: "guess/findMostHotGoods",
            dataType: "json",
            success: function (res) {
                $.each(res, function (index, item) {
                    names.push(item.name);    //挨个取出类别并填入类别数组
                    brower.push({
                        name: item.name,
                        value: item.num
                    });
                });
                hotChart.setOption({        //加载数据图表
                    legend: {
                        data: names
                    },
                    series: [{
                        data: brower
                    }]
                });
            }
        });
    });

    function showToDo() {
        $.ajax({                  //ajax() 方法用于执行 AJAX（异步 HTTP）请求
            type: "post",
            url: "order/findToDo",
            dataType: "json",
            success: function (data) {  //success(result,status,xhr) 请求成功时运行的函数
                $("#totalOrder").html(data.total);
                $("#toDoOrder").html(data.deliver);
            }
        });
    }
</script>
</html>