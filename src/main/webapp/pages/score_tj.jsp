<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>search page</title>
		<link href="../css/search.css" rel="stylesheet" type="text/css" />
		<script src="../js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="../js/score.js" type="text/javascript"></script>
		<script src="../js/search.js" type="text/javascript"></script>
	</head>
<body>
<center>
	<div class="searchbox tit">
					成绩统计：
					<select id="search_type">
						<c:forEach items="${list_subject}" var="subject">
									<option value="${subject.id}"  ${subject.id eq id?'selected':''}>
										${subject.name}
									</option>
								</c:forEach>
					</select>

					<input id="score_tj" type="button" value=" 查询 " />
				</div>

			</center>
    
	<div id="main2" style="width:80%;height:400px;margin: 0 auto"></div>
	<center><h3>${pingjun}</h3></center>
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
    <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
				'echarts/chart/line'
            ],
            function (ec) {
				var myChart2 = ec.init(document.getElementById('main2'));
                
                
								
								var option2 = {
								                    title: {
								                        text: '课程名： ${text} ',
								                        textStyle: { //主标题文本样式{"fontSize": 18,"fontWeight": "bolder","color": "#333"}
								                            fontSize: 14,
								                            fontStyle: 'normal',
								                            fontWeight: 'bold',
								                        },
								                    },
								                    tooltip: {
								                        trigger: 'axis'
								                    },
								                    legend: {
								                        data: ['平时分', '考试分','总分']
								                    },
								                    toolbox: {
								                        show: true,
								                        feature: {
								                            dataView: {
								                                show: true,
								                                readOnly: false
								                            },
								                            magicType: {
								                                show: true,
								                                type: ['line', 'bar']
								                            },
								                            restore: {
								                                show: true
								                            },
								                            saveAsImage: {
								                                show: true
								                            }
								                        }
								                    },
								                    calculable: true,
								                    xAxis: [{
								                        type: 'category',
														//name: 'min_sup(%)'
								                        data: ['0~59', '60~69', '70~79', '80~89','90~100'],
														name: '分数段',
														position: 'left'
								                    }],
								                    yAxis: [{
								                        type: 'value',
														name: '人数',
														position: 'left'
								                    }],
								                    series: [{
								                            name: '平时分',
								                            type: 'bar',
								                            data: [${daily_d}],
								                            color: '#CC0066'
								                        },
								                        {
								                            name: '考试分',
								                            type: 'bar',
								                            data: [${exam_d}],
								                            color: '#009999' // 设置颜色
								                        },
								                        {
								                            name: '总分',
								                            type: 'bar',
								                            data: [${count_d}],
								                            color: '#009999' // 设置颜色
								                        }
								                    ]
								                };
								
				myChart2.setOption(option2);
                
            }
        );
    </script>
</body>