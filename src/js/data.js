new Vue({
	el: '#app',
	data: {
		timer: null,
		nowWeek: '',
		nowDate: '',
		nowTime: '',
		chooses: ["照 明", "视频监控", "环境监测", "广告机", "广 播", "充电桩", "一键报警", "垃圾桶", "井 盖", "无线AP", "交通信号灯"],
		statistics:["照 明", "视频监控", "环境监测", "广告机", "广 播", "充电桩", "一键报警", "垃圾桶", "井 盖", "无线AP", "交通信号灯"],
		lamp:[],
		lampOn:[],
		lights:[],
		lightsOn:[],
		energy:[],
		energyDay:[],
		customColors:"#3CD8FF",
		percent:70,
		percents:18.3,
		linecap:"butt",
		form: {
			type: [0],

		},
		consumption: {
			type: [0],
		
		},
		arrFalse:[true,false,false,false,false,false,false,false,false,false,false],
		tableData: [
			{
				equipment: '',
				number: '',
				text: '',
				address: '',
				content: '',
				level: '',
				Treatment: '',
				related: '',
			}
		],
		energyList: [],
		imgs:[],
		options: [{
		          value: '1',
		          label: '折线图'
		        }, {
		          value: '2',
		          label: '热力图'
		        }],
		value:"1"
	},
	methods: {

		tableRow({
			row,
			rowIndex
		}) {
			if (rowIndex === 0) {
				return 'row_1 ';
			} else if (rowIndex === 1) {
				return 'row_2 row_td';

			} else if (rowIndex === 3) {
				return 'row_td';
			}

		},
		setNowTimes() {
		
			let myDate = new Date()
			// console.log(myDate)
			let wk = myDate.getDay()
			let yy = String(myDate.getFullYear())
			let mm = myDate.getMonth() + 1
			let dd = String(myDate.getDate() < 10 ? '0' + myDate.getDate() : myDate.getDate())
			let hou = String(myDate.getHours() < 10 ? '0' + myDate.getHours() : myDate.getHours())
			let min = String(myDate.getMinutes() < 10 ? '0' + myDate.getMinutes() : myDate.getMinutes())
			let sec = String(myDate.getSeconds() < 10 ? '0' + myDate.getSeconds() : myDate.getSeconds())
			let weeks = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
			let week = weeks[wk]
			this.nowDate = yy + '-' + mm + '-' + dd
			this.nowTime = hou + ':' + min + ':' + sec
			this.nowWeek = week
		},
		
		onSubmit() {

			var t = this.form.type.join(",")
			$("#types").val(t)

		},
		getTable() {
			
			axios.get(ServerBaseUrl+':8011/getWarningInfo/warninginfo', { //params参数必写 , 如果没有参数传{}也可以
				params: {
					
				}
			}).then(res => {
				 if (res.status == 200) {
					 var result = [];
					 
					 for(var i=0; i<res.data.length; i++){
						 result[i] = JSON.parse(res.data[i]);
						 result[i].url = ServerBaseUrl+result[i].url
					 };
					this.tableData = result;
					this.imgs = result;
				 } else {}
			}).catch(error => {
				console.log(err);
			}); 
		
		},
		
		temperature(){
			this.drawHot();
			$(".hot_one").addClass("f1");
			    $(".hot_two").removeClass("f2");
			    $(".hot_three").removeClass("f3");
			    $(".hot_four").removeClass("f4");
		},
		humidity(){
			this.drawHot();
			$(".hot_two").addClass("f2");
			    $(".hot_one").removeClass("f1");
			    $(".hot_three").removeClass("f3");
			    $(".hot_four").removeClass("f4");
		},
		PMtwo(){
			this.drawHot();
			$(".hot_three").addClass("f3");
			    $(".hot_one").removeClass("f1");
			    $(".hot_two").removeClass("f2");
			    $(".hot_four").removeClass("f4");
		},
		PMten(){
			this.drawHot();
			$(".hot_four").addClass("f4");
			    $(".hot_one").removeClass("f1");
			    $(".hot_two").removeClass("f2");
			    $(".hot_three").removeClass("f3");
		},
		//将多位数拆分成个位数
		separate(num){
			var num = c = num; //输入数值
						//计算数值位数
						var a = 0; 
						while (num >= 1) {
							a++;
							num = num / 10;
						};
			
						num = c; //对num重新赋值
						var i = 10;
						var j = 1;
						var sum = 1;
						var arrs = [];
						for (b = 1; b <= 4; b++) {
							var list  = parseInt(sum = (num % i) / j)+"";
							arrs.unshift(list)
							 //控制台输出
							i *= 10
							j *= 10
						};
					return arrs
		},
		light(){
			// 基于准备好的dom，初始化echarts实例
			/* 照明数据 */
			var that = this
			axios.get(ServerBaseUrl+':8011/getLightInfo/lightInfo', { //params参数必写 , 如果没有参数传{}也可以
				params: {
			
				}
			}).then(function(res) {
				if (res.status == 200) {
					var list_one = res.data[0];
					var list_two = res.data[1];
					var list_three = res.data[2];
					var num0= list_one[0];
					var num1= list_one[1];
					var num2=list_two[0];
					var num3=list_two[1];
					var num4=parseInt(list_three[0]);
					var num5=parseInt(list_three[1]);
					that.lamp=that.separate(num0);
					that.lampOn=that.separate(num1);
					that.lights=that.separate(num2);
					that.lightsOn=that.separate(num3);
					that.energy=that.separate(num4);
					that.energyDay=that.separate(num5);
				} else {
					console.log(statusText)
				}
			}).catch(function(err) {
				console.log(err);
			});
		},
		callOff(){
			$(".pie_pop").hide()
			},
		more(){
			$(".pie_pop").show()
		},
		ensure(){
			var t = this.consumption.type;
			var arrf = [false,false,false,false,false,false,false,false,false,false,false];
			console.log(this.consumption.type)
			$(".pie_pop").hide();
			
			for (i = 0; i < t.length; i++) { 
				var index = t[i]
				arrf[index] = true;
				
			 }
			 this.arrFalse = arrf;
			 console.log(this.arrFalse)
			 this.drawChart();
			 
		},
		drawChart() {
			const that = this
			// 能耗统计
			axios.get(ServerBaseUrl+':8011/getEnergyConsu/getEnergyChart', { //params参数必写 , 如果没有参数传{}也可以
				params: {
		
				}
			}).then(function(res) {
				if (res.status == 200) {
					
					function getDay(day) {
						var today = new Date();
						var targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
						today.setTime(targetday_milliseconds); //注意，这行是关键代码
						var tDate = today.getDate();
		
						tDate = doHandleMonth(tDate);
						return tDate;
					};
		
					function doHandleMonth(month) {
						var m = month;
						if (month.toString().length == 1) {
							m = "0" + month;
						}
						return m;
					};
					var twoChart = echarts.init(document.getElementById("energy_item"));
					var arrtxt = [];
					var arrtxt = that.arrFalse;
					var optionA = {
						/* title: {
						        text: '折线图堆叠'
						    },*/
						tooltip: {
							trigger: 'item',
							formatter: '{a} <br/>{b} : {c}' + 'kw.h'
						},
						legend: {
		
							// 修改legend的高度宽度
							itemHeight: 12,
							itemWidth: 12,
							data: [{
									name: '照明',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#FF7E3D' // 图例文字颜色
									}
								},
								{
									name: '视频监控',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#C13DFF' // 图例文字颜色
									}
								},
								{
									name: '广播',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#c23531' // 图例文字颜色
									}
								},
								{
									name: '广告机',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#61a0a8' // 图例文字颜色
									}
								},
								{
									name: '环境监测',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#d48265' // 图例文字颜色
									}
								},
								{
									name: '充电桩',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#91c7ae' // 图例文字颜色
									}
								},
								{
									name: '一键报警',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#749f83' // 图例文字颜色
									}
								},
								{
									name: '垃圾桶',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#bda29a' // 图例文字颜色
									}
								},
								{
									name: '井盖',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#39c53b' // 图例文字颜色
									}
								},
								{
									name: '无线AP',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#2cf7ff' // 图例文字颜色
									}
								},
								{
									name: '交通信号灯',
									icon: 'rect', // ledend的icon
									textStyle: {
										color: '#fff700' // 图例文字颜色
									}
								}
							],
							textStyle: {
								color: '#FFFFFF'
							},
							/* right: "5%",
							left: "15%",
							top: "5%", */
							show:false,
							// 选择关闭的legend
							selected: {
								'照明':arrtxt[0],
								'视频监控': arrtxt[1],
								'环境监测':arrtxt[2],
								'广告机': arrtxt[3],
								'广播': arrtxt[4],
								'充电桩':arrtxt[5],
								'一键报警': arrtxt[6],
								'垃圾桶':arrtxt[7],
								'井盖': arrtxt[8],
								'无线AP': arrtxt[9],
								'交通信号灯': arrtxt[10],
							},
		
						},
						grid: {
							left: '3%',
							right: '12%',
							top:"20%",
							bottom:"0%",
							containLabel: true
						},
						/* toolbox: {
						     feature: {
						         saveAsImage: {}
						     }
						 },*/
						xAxis: {
							type: 'category',
							boundaryGap: false,
							data: [getDay(-6), getDay(-5), getDay(-4), getDay(-3), getDay(-2), getDay(-1), getDay(0)],
							name: "/ 天",
							nameTextStyle: {
								color: "#3CD8FF",
								fontSize: "10"
							},
							axisLine: {
								lineStyle: {
									color: '#ffffff',
		
								}
							}
		
						},
						yAxis: {
							type: 'value',
							name: "/ kw.h",
							nameTextStyle: {
								color: "#3CD8FF",
								fontSize: "10"
							},
							splitLine: {
								show: false
							},
							axisLine: {
								lineStyle: {
									color: '#ffffff',
		
								}
							}
		
						},
						series: [
							{
								name: '照明',
								type: 'line',
								data: res.data[0],
								color: "#FF7E3D",
							},
							{
								name: '视频监控',
								type: 'line',
								data: res.data[1],
								color: '#C13DFF'
							},
							{
								name: '广播',
								type: 'line',
								data: res.data[2],
								color: '#c23531'
							},
							{
								name: '广告机',
								type: 'line',
								data: res.data[3],
								color: '#61a0a8'
							},
							{
								name: '环境监测',
								type: 'line',
								data: res.data[4],
								color: '#d48265'
							},
							{
								name: '充电桩',
								type: 'line',
								data: res.data[5],
								color: '#91c7ae'
							},
							{
								name: '一键报警',
								type: 'line',
								data: res.data[6],
								color: '#749f83'
							},
							{
								name: '垃圾桶',
								type: 'line',
								data: res.data[7],
								color: '#bda29a'
							},
							{
								name: '井盖',
								type: 'line',
								data: res.data[8],
								color: '#39c53b'
							},
							{
								name: '无线AP',
								type: 'line',
								data: res.data[9],
								color: '#2cf7ff'
							},
							{
								name: '交通信号灯',
								type: 'line',
								data: res.data[10],
								color: '#fff700'
							},
		
						]
					};
					twoChart.setOption(optionA);
					let sizeFun = function() {
						twoChart.resize();
					}
					window.addEventListener('resize', sizeFun)
				} else {
					console.log(statusText)
				}
			}).catch(function(err) {
				console.log(err);
			});
		},
		swipers(){
			const that = this;
			axios.get(ServerBaseUrl+':8011/getWarningInfo/warninginfo', { //params参数必写 , 如果没有参数传{}也可以
				params: {
			
				}
			}).then(function(res){
			
				if (res.status == 200) {
				var result = [];
				for(var i=0; i<res.data.length; i++){
					result[i] = ServerBaseUrl+res.data[i];
				};
				that.items = result;
				
				} else {
					console.log(statusText)
				}
			}).catch(function(err) {
				console.log(err);
			});
			
		},
		//环境监测
		lineChart(){
			var myChart  = echarts.init(document.getElementById("lineT"));
			/* const hexToRgba = (hex, opacity) => {
							let rgbaColor = "";
							let reg = /^#[\da-f]{6}$/i;
							if (reg.test(hex)) {
								rgbaColor =
									`rgba(${parseInt("0x" + hex.slice(1, 3))},${parseInt(
						      "0x" + hex.slice(3, 5)
						    )},${parseInt("0x" + hex.slice(5, 7))},${opacity})`;
							}
							return rgbaColor;
						} */
						var option = {
							tooltip: {
								trigger: 'item',
								formatter: '{a} <br/>{b} : {c}'
							},
							legend: {
			
								// 修改legend的高度宽度
								itemHeight: 12,
								itemWidth: 12,
								data: [{
										name: '温度',
										icon: 'circle', // ledend的icon
										textStyle: {
											color: '#FF7E3D' // 图例文字颜色
										}
									},
									{
										name: '湿度',
										icon: 'circle', // ledend的icon
										textStyle: {
											color: '#C13DFF' // 图例文字颜色
										}
									},
									{
										name: 'PM2.5',
										icon: 'circle', // ledend的icon
										textStyle: {
											color: '#c23531' // 图例文字颜色
										}
									},
									{
										name: 'PM10',
										icon: 'circle', // ledend的icon
										textStyle: {
											color: '#61a0a8' // 图例文字颜色
										}
									}
								],
								textStyle: {
									color: '#FFFFFF'
								},
								right: "9%",
								top: "2%",
								// 选择关闭的legend
								selectedMode: 'single'
			
							},
							grid: {
								left: '3%',
								right: '10%',
								top: '15%',
								bottom: '0%',
								containLabel: true
							},
							
							xAxis: {
								type: 'category',
								boundaryGap: false,
								data: ['3:00', '6:00', '9:00', '12:00', '15:00', '18:00', '21:00', '24:00'],
								name: "/ h",
								nameTextStyle: {
									color: "#3CD8FF",
									fontSize: "10"
								},
								axisLine: {
									lineStyle: {
										color: '#ffffff',
			
									}
								}
			
							},
							yAxis: {
								type: 'value',
								 name: "/ kw.h", 
								 
								 nameTextStyle:{
								     color:"#3CD8FF",
								     fontSize:"10"
								 },
								splitLine: {
									show: false
								},
								axisLine: {
									lineStyle: {
										color: '#ffffff',
									}
								}
			
							},
							series: [
			
								{
									name: '温度',
									type: 'line',
									data: [50, 132, 101, 134, 90, 230, 210, 210],
									color: "#FF7E3D",
									smooth: true,
								},
								{
									name: '湿度',
									type: 'line',
									data: [220, 182, 191, 134, 290, 330, 310, 310],
									color: '#C13DFF',
									smooth: true,
								},
								{
									name: 'PM2.5',
									type: 'line',
									data: [150, 232, 201, 154, 190, 330, 410, 410],
									color: '#c23531',
									smooth:true,
								},
								{
									name: 'PM10',
									type: 'line',
									data: [320, 332, 301, 334, 390, 330, 320, 320],
									color: '#61a0a8',
									smooth: true,
								}
			
							]
						};
						myChart.setOption(option);
			
			
						//图标自适应
						let sizeFun = function() {
							myChart.resize();
						} 
						window.addEventListener('resize', sizeFun)
		},
		changeChart(){
			var val =this.value;
			if(val == 1){
				$(".hot_ul").hide();
				$("#lineT").show();
				$("#hotT").hide();
			}else if(val == 2){
				$(".hot_ul").show();
				$("#hotT").show();
				$("#lineT").hide();
				this.drawHot();
			}
		},
		/* 绘制热力图 */
		drawHot() {
			
			axios.get(ServerBaseUrl+':8011/getHeatMap/getHeatMap', { //params参数必写 , 如果没有参数传{}也可以
				params: {
					
				}
			}).then(res => {
					 var result = res.data;
					 if (!isSupportCanvas()) {
					     alert('热力图仅对支持canvas的浏览器适用,您所使用的浏览器不能使用热力图功能,请换个浏览器试试~')
					 }
					
					 /* 热力图 */
					var map = new AMap.Map("hotT", {
					        resizeEnable: true,
					        center: [121.469211,31.238164],
							mapStyle: 'amap://styles/darkblue',
							zoom: 11	
					    });
					
					   
					
					    var heatmap;
					    map.plugin(["AMap.Heatmap"], function () {
					        //初始化heatmap对象
					        heatmap = new AMap.Heatmap(map, {
					            radius: 25, //给定半径
					            opacity: [0, 0.85]
					            /*,
					            gradient:{
					                0.5: 'blue',
					                0.65: 'rgb(117,211,248)',
					                0.7: 'rgb(0, 255, 0)',
					                0.9: '#ffea00',
					                1.0: 'red'
					            }
					             */
					        });
					        //设置数据集
					        heatmap.setDataSet({
					            data: result,
					            max: 666
					        });
					    });
					
					    //判断浏览区是否支持canvas
					    function isSupportCanvas() {
					        var elem = document.createElement('canvas');
					        return !!(elem.getContext && elem.getContext('2d'));
					    }
				
			}).catch(error => {
				console.log(err);
			}); 
		
		},
	},
	
	created() {
		this.getTable();
		this.light();
		this.swipers()
		this.timer = setInterval(() => {
			this.setNowTimes();
			this.lineChart();
			this.drawChart();
		}, 500);
		/* this.separate() */
		this.timer =setInterval(this.drawHot,100000000);
		this.timers =setInterval(this.getTable,5000);
		
		$(function() {
			var viewH = $(window).height(); 
			var maxHeight =viewH-93;
			$(".body_box .el-col").height(maxHeight);
			$(".body_box .grid-content").height(maxHeight);
			$(".body_box .grid-content .main_ul").height(maxHeight);
			var listHeight = $(".item_list").height();
			$(".item_list .left_txt").css("line-height",listHeight+"px");
			$(".item_list .right_txt").css("line-height",listHeight+"px");
			var vWidth = $(".video_box").width();
			var vHeight= vWidth*4/5;
			var totalH =$(".right_frame.box2 .pie_box").height();
			var imgH = totalH-45-vHeight;
			$(".pie_img").height(imgH);
			$(".video_box").height(vHeight);
			var tableH = $(".bottom_box .el-table").height();
			var mapH = maxHeight-tableH-40;
			$(".main_middle").height(mapH);
			// 设置flash路径,用于在videojs发现浏览器不支持HTML5播放器的时候自动唤起flash播放器
			videojs.options.flash.swf = 'https://cdn.bootcss.com/videojs-swf/5.4.1/video-js.swf';
			var player = videojs('my-player'); //my-player为页面video元素的id
			player.play(); //播放
			/* 绘制2D地图 */	
			Shapes();
			$(".screen").click(function() {
				
				$(".input-card").show();
				var imgHeight = $(".cancel").height();
				$(".cancel").width(imgHeight);
				var theight = $(".check-box").height() + 50;
				var total = parseInt(imgHeight + theight);
				$(".input-card").height(total);
			
			});
			$(".cancel").click(function(){
				$(".input-card").hide();
			
			});
			$(".shapes").click(function(){
				Shapes();
				$(".shapes").addClass("inChecked");
				$(".modeling").removeClass("inChecked");
			});
			$(".modeling").click(function(){
				modeling();
				$(".modeling").addClass("inChecked");
				$(".shapes").removeClass("inChecked");
				$(".screen").hide()
			});
		});
		/* 绘制2D地图 */
		function Shapes(){
			  map && map.destroy();
			var map = new AMap.Map('container', {
					zoom: 15, //级别
					mapStyle: 'amap://styles/31f8e758bcafa2d0aa535571d1a8616e',
					resizeEnable: true, //是否监控地图容器尺寸变化
					rotateEnable:true,
					pitchEnable:true,
					center: [121.613776,31.303634], //中心点坐标
				});
				/* map.setMapStyle('amap://styles/31f8e758bcafa2d0aa535571d1a8616e');	 */
				 
				
				var lnglat = lnglats1[0];
				var marker = [];
				var markers = [];
				
			for (var i = 0; i < lnglat.length; i++) {
				var lng = lnglat[i];
				marker = new AMap.Marker({
						position: new AMap.LngLat(lng[0], lng[1]),
						icon: new AMap.Icon({
							size: new AMap.Size(36, 50), //图标大小
							image: "../img/icon0.png"
						}),
						extData: {
							id: i + 1
						},
						
					});
					markers.push(marker);}
					map.add(new AMap.OverlayGroup(markers));
				 
				$("#btn_box").click(function() {
								var text = $("#types").val();
								var array = text.split(",");
								var colloctionTeam = [0,1,2,3,4,6,9];
								var colloctionTeamIndex = 0;
								for(var i = 0; i<array.length; i++){
									for(var j =0; j<colloctionTeam.length; j++){
										if(array[i] == colloctionTeam[j]){
											colloctionTeamIndex++;
										}
									}
								}
								if (array.length == "") {
									map.clearMap();
									$(".input-card").hide();
								} else {
									$(".input-card").hide();
									map.clearMap();
									for (var j = 0; j < array.length; j++) {
										if(colloctionTeamIndex>1){
											var indexs = array[j];
											var indexIcon = array[j];
											for(var a=0; a<colloctionTeam.length; a++){
												if(array[j]==colloctionTeam[a]){
													indexIcon = 11;
												}
											}
											var lnglat = lnglats1[indexs];
											var marker = [];
											var markers = [];
											for (var i = 0; i < lnglat.length; i++) {
												var lng = lnglat[i];
												// 创建点实例
												marker[i] = new AMap.Marker({
													position: new AMap.LngLat(lng[0], lng[1]),
													icon: new AMap.Icon({
														size: new AMap.Size(36, 50), //图标大小
														image: "../img/icon" + indexIcon + ".png",
														anchor:'bottom-center',
														zIndex:-100
													}),
													extData: {
														id: i + 1
													}
												});
												markers.push(marker[i]);
												map.add(new AMap.OverlayGroup(markers));
											};
										}else{
											var indexs = array[j];
											var lnglat = lnglats1[indexs];
											var marker = [];
											var markers = [];
											for (var i = 0; i < lnglat.length; i++) {
												var lng = lnglat[i];
												// 创建点实例
												marker[i] = new AMap.Marker({
													position: new AMap.LngLat(lng[0], lng[1]),
													icon: new AMap.Icon({
														size: new AMap.Size(36, 50), //图标大小
														image: "../img/icon" + indexs + ".png",
														anchor:'bottom-center',
														zIndex:-100
													}),
													extData: {
														id: i + 1
													}
												});
												markers.push(marker[i]);
												map.add(new AMap.OverlayGroup(markers));
											};
										}
										
				
									};
				
								}
								
				
							});
							
		};
		/* 绘制3D地图 */
		function modeling(){
			  map && map.destroy();
			var map = new AMap.Map('container', {
					zoom: 17, //级别
					mapStyle: 'amap://styles/31f8e758bcafa2d0aa535571d1a8616e',
					resizeEnable: true, //是否监控地图容器尺寸变化
					rotateEnable:true,
					pitchEnable:true,
					pitch:75,
					rotation:-15,
					viewMode:'3D',//开启3D视图,默认为关闭
					  showBuildingBlock:true,
					buildingAnimation:true,//楼块出现是否带动画
					 expandZoomRange:true,
					    zooms:[17,20],
					center: [121.613776,31.303634], //中心点坐标
				});
				/* map.setMapStyle('amap://styles/31f8e758bcafa2d0aa535571d1a8616e');	 */
				 map.addControl(new AMap.ControlBar({
				    showZoomBar:false,
				    showControlButton:true,
				    position:{
				      left:'-80px',
				      top:'10px'
				    }
				  }))
				 // 创建Object3DLayer图层
				    var object3Dlayer = new AMap.Object3DLayer();
				    map.add(object3Dlayer);	
					 var lnglat = lnglats1[0];
					/*var gltfDuck = []; */
					
					map.plugin(["AMap.GltfLoader"], function () {
								var urlDuck = 'file.gltf';
								var paramDucks  =[];
					           for (var i = 0; i < lnglat.length; i++) {
					           
					           	var lng = lnglat[i];
								var paramDuck =[];
								paramDuck[i]= {
					           	    position: new AMap.LngLat(lng[0], lng[1]), // 必须
					           	    scale:1, // 非必须，默认1
					           	    height: 0,  // 非必须，默认0
					           	    scene: 0, // 非必须，默认0
					           	};
								paramDucks.push(paramDuck[i]);
					      };
							  
							   
					        var gltfObj = new AMap.GltfLoader();
					          gltfObj.load(urlDuck, function (gltfDuck) {
								  for(var i = 0; i < paramDucks.length; i++){
								gltfDuck.setOption(paramDucks[i]);
					            gltfDuck.rotateX(90);
					            gltfDuck.rotateZ(-20);
								object3Dlayer.add(gltfDuck);
									  
								  }
					              
					          });
					         
					         
					
					        });
		};
	},
	mounted() {
		$(window).resize(function() {
			var viewH = $(window).height();
			var maxHeight =viewH-93;
			$(".body_box .el-col").height(maxHeight);
			$(".body_box .grid-content").height(maxHeight);
			$(".body_box .grid-content .main_ul").height(maxHeight);
			var listHeight = $(".item_list").height();
			$(".item_list .left_txt").css("line-height",listHeight+"px");
			$(".item_list .right_txt").css("line-height",listHeight+"px");
			var vWidth = $(".video_box").width();
			var vHeight= vWidth*4/5;
			$(".video_box").height(vHeight);
			var totalH =$(".right_frame.box2 .pie_box").height();
			var imgH = totalH-45-vHeight;
			$(".pie_img").height(imgH);
			$(".video_box").height(vHeight);
			var tableH = $(".bottom_box .el-table").height();
			var mapH = maxHeight-tableH-40;
			$(".main_middle").height(mapH);
		});
		setTimeout(()=>{
			var swiper = new Swiper('.swiper-container', {
				slidesPerView: 3,
			    spaceBetween: 10,
			    slidesPerGroup:3,
			    loop: true,
			    loopFillGroupWithBlank: true,
			    pagination: {
			      el: '.swiper-pagination',
			      clickable: true,
			    },
			    navigation: {
			      nextEl: '.swiper-button-next',
			      prevEl: '.swiper-button-prev',
			    },
			  });
		},300)

	},
	beforeDestroy() {
		clearInterval(this.timer);
		clearInterval(this.timers);
	}

})
