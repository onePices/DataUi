new Vue({
	el: '#app',
	data: {
		timer: null,
		nowWeek: '',
		nowDate: '',
		nowTime: '',
		chooses: ["照 明", "视频监控", "环境监测", "广告机", "广 播", "充电桩", "一键报警", "垃圾桶", "井 盖", "无线AP", "交通信号灯"],
		statistics: ["照 明", "视频监控", "环境监测", "广告机", "广 播", "充电桩", "一键报警", "垃圾桶", "井 盖", "无线AP", "交通信号灯"],
		lamp: [],
		lampOn: [],
		lights: [],
		lightsOn: [],
		energy: [],
		energyDay: [],
		customColors: "#3CD8FF",
		percent: 70,
		percents: 18.3,
		linecap: "butt",
		form: {
			type: [0],

		},
		consumption: {
			type: [0],

		},
		dnum:0,
		arrFalse: [true, false, false, false, false, false, false, false, false, false, false],
		tableData: [{
			equipment: '',
			number: '',
			text: '',
			address: '',
			content: '',
			level: '',
			Treatment: '',
			related: '',
		}],
		energyList: [],
		imgs: [{
			url:'',
			content:''
		}],
		shuL:[{
			name:"灯杆总数",
			number:"1235",
			date:''
		},{
			name:"在线数",
			number:"935",
			date:''
		},{
			name:"预计耗能",
			number:"8.5",
			date:'万kwh'
		},{
			name:"实际耗能",
			number:"1235",
			date:'万kwh'
		}],
		controlFrom:{
			On_off:false,
			
		},
		value1:'',
		message:'<el-slider v-model="value1"></el-slider> '
	},
	methods: {

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

			axios.get(ServerBaseUrl + ':8011/getWarningInfo/warninginfo', { //params参数必写 , 如果没有参数传{}也可以
				params: {
					start:"1",
					stop:"4",
				}
			}).then(res => {
				if (res.status == 200) {
					
					this.tableData = res.data.data;
					
				} else {}
			}).catch(error => {
				console.log(err);
			});

		},

		//将多位数拆分成个位数
		separate(num) {
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
				var list = parseInt(sum = (num % i) / j) + "";
				arrs.unshift(list)
				//控制台输出
				i *= 10
				j *= 10
			};
			return arrs
		},
		light() {
			// 基于准备好的dom，初始化echarts实例
			/* 照明数据 */
			var that = this
			axios.get(ServerBaseUrl + ':8011/getLightInfo/lightInfo', { //params参数必写 , 如果没有参数传{}也可以
				params: {
                
				}
			}).then(function(res) {
				if (res.status == 200) {
					var list_one = res.data[0];
					var list_two = res.data[1];
					var list_three = res.data[2];
					var num0 = list_one[0];
					var num1 = list_one[1];
					var num2 = list_two[0];
					var num3 = list_two[1];
					var num4 = parseInt(list_three[0]);
					var num5 = parseInt(list_three[1]);
					that.lamp = that.separate(num0);
					that.lampOn = that.separate(num1);
					that.lights = that.separate(num2);
					that.lightsOn = that.separate(num3);
					that.energy = that.separate(num4);
					that.energyDay = that.separate(num5);
				} else {
					console.log(statusText)
				}
			}).catch(function(err) {
				console.log(err);
			});
		},
		callOff() {
			$(".pie_pop").hide()
		},
		more() {
			$(".pie_pop").show()
		},
		ensure() {
			var t = this.consumption.type;
			var arrf = [false, false, false, false, false, false, false, false, false, false, false];
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
		//照明数据
		drawDate(){
			var online = parseInt(this.shuL[1].number/this.shuL[0].number*100);
			var noline = 100-online;
			var dateChart = echarts.init(document.getElementById("dateT"));
			var placeHolderStyle = {
			    normal: {
			        label: {
			            show: false
			        },
			        labelLine: {
			            show: false
			        },
			        color: "rgba(0,0,0,0)",
			        borderWidth: 0
			    },
			    emphasis: {
			        color: "rgba(0,0,0,0)",
			        borderWidth: 0
			    }
			};
			
			
			var dataStyle = {
			    normal: {
			        formatter: '{c}%',
			        position: 'center',
			        show: true,
			        textStyle: {
			            fontSize: '14',
			            fontWeight: 'normal',
			            color: '#ffffff'
			        }
			    }
			};
			
			
			var option = {
			     title: [{
			        text: '亮灯率',
			        left: '15%',
			        top: '75%',
			        textAlign: 'center',
			        textStyle: {
			            fontWeight: 'normal',
			            fontSize: '12',
			            color: '#fff',
			            textAlign: 'center',
			        },
			    }, {
			        text: '在线率',
			        left: '48%',
			        top: '75%',
			        textAlign: 'center',
			        textStyle: {
			            color: '#fff',
			            fontWeight: 'normal',
			            fontSize: '12',
			            textAlign: 'center',
			        },
			    }, {
			        text: '节能率',
			        left: '82%',
			        top: '75%',
			        textAlign: 'center',
			        textStyle: {
			            color: '#fff',
			            fontWeight: 'normal',
			            fontSize: '12',
			            textAlign: 'center',
			        },
			    }],
			
			    //第一个图表
			    series: [{
			            type: 'pie',
			            hoverAnimation: false, //鼠标经过的特效
			            radius: ['85%', '100%'],
			            center: ['16%', '50%'],
			            startAngle: 225,
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            label: {
			                normal: {
			                    position: 'center'
			                }
			            },
			            data: [{
			                    value: 100,
			                    itemStyle: {
			                        normal: {
			                            color: ['rgba(176, 212, 251, 0.3)']
			                        }
			                    },
			                }, {
			                    value: 35,
			                    itemStyle: placeHolderStyle,
			                },
			
			            ]
			        },
			        //上层环形配置
			        {
			            type: 'pie',
			            hoverAnimation: false, //鼠标经过的特效
			            radius: ['85%', '100%'],
			            center: ['16%', '50%'],
			            startAngle: 225,
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            label: {
			                normal: {
			                    position: 'center'
			                }
			            },
			            data: [{
			                    value: 75,
			                    "itemStyle": {
			                        "normal": {
			                            "color": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                                "offset": 0,
			                                "color": '#00cefc'
			                            }, {
			                                "offset": 1,
			                                "color": '#367bec'
			                            }]),
			                        }
			                    },
			                    label: dataStyle,
			                }, {
			                    value: 35,
			                    itemStyle: placeHolderStyle,
			                },
			
			            ]
			        },
			
			
			        //第二个图表
			        {
			            type: 'pie',
			            hoverAnimation: false,
			            radius: ['85%', '100%'],
			            center: ['50%', '50%'],
			            startAngle: 225,
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            label: {
			                normal: {
			                    position: 'center'
			                }
			            },
			            data: [{
			                    value: 100,
			                    itemStyle: {
			                        normal: {
			                            color: ['rgba(176, 212, 251, 0.3)']
			                        }
			                    },
			
			                }, {
			                    value: 35,
			                    itemStyle: placeHolderStyle,
			                },
			
			            ]
			        },
			
			        //上层环形配置
			        {
			            type: 'pie',
			            hoverAnimation: false,
			            radius: ['85%', '100%'],
			            center: ['50%', '50%'],
			            startAngle: 225,
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            label: {
			                normal: {
			                    position: 'center'
			                }
			            },
			            data: [{
			                    value: online,
			                    "itemStyle": {
			                        "normal": {
			                            "color": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                                "offset": 0,
			                                "color": '#9FE6B8'
			                            }, {
			                                "offset": 1,
			                                "color": '#32C5E9'
			                            }]),
			                        }
			                    },
			                    label: dataStyle,
			                }, {
			                    value: noline,
			                    itemStyle: placeHolderStyle,
			                },
			
			            ]
			        },
			        //第三个图表
			        {
			            type: 'pie',
			            hoverAnimation: false,
			            radius: ['85%', '100%'],
			            center: ['84%', '50%'],
			            startAngle: 225,
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            label: {
			                normal: {
			                    position: 'center'
			                }
			            },
			            data: [{
			                    value: 100,
			                    itemStyle: {
			                        normal: {
			                            color: ['rgba(176, 212, 251, 0.3)']
			                        }
			                    },
			
			                }, {
			                    value: 35,
			                    itemStyle: placeHolderStyle,
			                },
			
			            ]
			        },
			
			        //上层环形配置
			        {
			            type: 'pie',
			            hoverAnimation: false,
			            radius: ['85%', '100%'],
			            center: ['84%', '50%'],
			            startAngle: 225,
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            label: {
			                normal: {
			                    position: 'center'
			                }
			            },
			            data: [{
			                    value: 65,
			                    "itemStyle": {
			                        "normal": {
			                            "color": new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                                "offset": 0,
			                                "color": '#FDFF5C'
			                            }, {
			                                "offset": 1,
			                                "color": '#FFDB5C'
			                            }]),
			                        }
			                    },
			                    label: dataStyle,
			                }, {
			                    value: 55,
			                    itemStyle: placeHolderStyle,
			                },
			
			            ]
			        },
			    ]
			};
			dateChart.setOption(option);
			let sizeFun = function() {
				dateChart.resize();
			}
			window.addEventListener('resize', sizeFun)
		},
		// 能耗统计
		drawChart() {
			var myChart = echarts.init(document.getElementById("energy_item"));
			let data = [{
			        level: '广播',
			        landArea: 120,
					total:200
			    },
			    {
			        level: '监控',
			        landArea: 200,
					total:240
			    },
			    {
			        level: '网关',
			        landArea: 230,
					total:240
			    },
			    {
			        level: '照明',
			        landArea: 280,
					total:300
			    },
			    {
			        level: '显示',
			        landArea: 300,
					total:300
			    },
			    {
			        level: '其他',
			        landArea: 200,
					total:280
			    },
			    
			]
			const CubeLeft = echarts.graphic.extendShape({
			    shape: {
			        x: 0,
			        y: 0
			    },
			    buildPath: function(ctx, shape) {
			        const xAxisPoint = shape.xAxisPoint
			        const c0 = [shape.x, shape.y]
			        const c1 = [shape.x - 20, shape.y - 4]
			        const c2 = [xAxisPoint[0] - 20, xAxisPoint[1] - 4]
			        const c3 = [xAxisPoint[0], xAxisPoint[1]]
			        ctx.moveTo(c0[0], c0[1]).lineTo(c1[0], c1[1]).lineTo(c2[0], c2[1]).lineTo(c3[0], c3[1]).closePath()
			    }
			})
			const CubeRight = echarts.graphic.extendShape({
			    shape: {
			        x: 0,
			        y: 0
			    },
			    buildPath: function(ctx, shape) {
			        const xAxisPoint = shape.xAxisPoint
			        const c1 = [shape.x, shape.y]
			        const c2 = [xAxisPoint[0], xAxisPoint[1]]
			        const c3 = [xAxisPoint[0] + 8, xAxisPoint[1] - 4]
			        const c4 = [shape.x + 8, shape.y - 4]
			        ctx.moveTo(c1[0], c1[1]).lineTo(c2[0], c2[1]).lineTo(c3[0], c3[1]).lineTo(c4[0], c4[1]).closePath()
			    }
			})
			const CubeTop = echarts.graphic.extendShape({
			    shape: {
			        x: 0,
			        y: 0
			    },
			    buildPath: function(ctx, shape) {
			        // 逆时针 角 y 负数向上  X 负数向左
			        const c1 = [shape.x, shape.y]
			        const c2 = [shape.x + 8, shape.y - 4]
			        const c3 = [shape.x - 11, shape.y - 8]
			        const c4 = [shape.x - 20, shape.y - 4]
			        ctx.moveTo(c1[0], c1[1]).lineTo(c2[0], c2[1]).lineTo(c3[0], c3[1]).lineTo(c4[0], c4[1]).closePath()
			    }
			})
			echarts.graphic.registerShape('CubeLeft', CubeLeft)
			echarts.graphic.registerShape('CubeRight', CubeRight)
			echarts.graphic.registerShape('CubeTop', CubeTop)
			let MAX = []
			let VALUE = []
			let LEV = []
			let chartData = [].concat(data)
			chartData.forEach(item => {
			    VALUE.push(item.landArea)
			    LEV.push(item.level)
				MAX.push(item.total)
			});
			var option = {
			   tooltip: {
			        trigger: 'item',
			        confine: false,
			        position: 'top',
			        textStyle: {
			            fontSize: 12
			        },
			        // extraCssText: 'box-shadow: 0 0 20px #00C7FF inset',
			        // backgroundColor: 'rgba(0,155,206,0.5)',
			        backgroundColor: 'transparent',
			        formatter: function(params) {
			            let percentage = (VALUE[params.dataIndex] / MAX[params.dataIndex] * 100).toFixed(2)
			            return `<div class="tooltip">${VALUE[params.dataIndex]}万/kwh (${percentage}%)</div>`
			        },
			        extraCssText: 'box-shadow: 0 0 20px #00C7FF inset;'
			    },
			    grid: {
			        show: false,
			        left: 0,
			        right: 0,
			        bottom: 8,
			        top: 28,
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        data: LEV,
			        axisLine: {
			            show: true,
			            lineStyle: {
			                color:'#FFF',
			            }
			        },
			        offset: 10,
			        axisTick: {
			            show: false
			        },
			        axisLabel: {
			            fontSize: 12,
			            color: "#fff"
			        }
			    },
			    yAxis: {
			        show: true,
			        type: 'value',
					name:'万/kwh',
			        axisLine: {
			            show: true,
			            lineStyle: {
			                color: '#FFF'
			            }
			        },
			        splitLine: {
			            show: false
			        },
			        axisTick: {
			            show: false
			        },
			        axisLabel: {
			            fontSize: 10
			        },
			        boundaryGap: ['20%', '20%']
			    },
			    series: [{
			        type: 'custom',
			        renderItem: function(params, api) {
			            const location = api.coord([api.value(0), api.value(1)])
						return {
			                type: 'group',
			                children: [{
			                    type: 'CubeLeft',
							
			                    shape: {
			                        api,
			                        xValue: api.value(0),
			                        yValue: api.value(1),
			                        x: location[0],
			                        y: location[1],
			                        xAxisPoint: api.coord([api.value(0), 0])
			                    },
			                    style: {
			                        fill: 'rgba(1,17,33,.5)'
			                    }
			                }, {
			                    type: 'CubeRight',
			                    shape: {
			                        api,
			                        xValue: api.value(0),
			                        yValue: api.value(1),
			                        x: location[0],
			                        y: location[1],
			                        xAxisPoint: api.coord([api.value(0), 0])
			                    },
			                    style: {
			                        fill: 'rgba(1,17,33,.5)'
			                    }
			                }, {
			                    type: 'CubeTop',
			                    shape: {
			                        api,
			                        xValue: api.value(0),
			                        yValue: api.value(1),
			                        x: location[0],
			                        y: location[1],
			                        xAxisPoint: api.coord([api.value(0), 0])
			                    },
			                    style: {
			                        fill: 'rgba(1,17,33,.5)'
			                    }
			                }]
			            }
			        },
			        data: MAX
			    }, {
			        type: 'custom',
			        renderItem: (params, api) => {
			            const location = api.coord([api.value(0), api.value(1)])
			            return {
			                type: 'group',
			                children: [{
			                    type: 'CubeLeft',
			                    shape: {
			                        api,
			                        xValue: api.value(0),
			                        yValue: api.value(1),
			                        x: location[0],
			                        y: location[1],
			                        xAxisPoint: api.coord([api.value(0), 0])
			                    },
			                    style: {
			                        fill: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                            offset: 0,
			                            color: '#1477BD'
			                        }, {
			                            offset: 1,
			                            color: '#00FFFE'
			                        }])
			                    }
			                }, {
			                    type: 'CubeRight',
			                    shape: {
			                        api,
			                        xValue: api.value(0),
			                        yValue: api.value(1),
			                        x: location[0],
			                        y: location[1],
			                        xAxisPoint: api.coord([api.value(0), 0])
			                    },
			                    style: {
			                        fill: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                            offset: 0,
			                            color: '#002E75' // 顶部
			                        }, {
			                            offset: 1,
			                            color: '#00B0D0' // 底部
			                        }])
			                    }
			                }, {
			                    type: 'CubeTop',
			                    shape: {
			                        api,
			                        xValue: api.value(0),
			                        yValue: api.value(1),
			                        x: location[0],
			                        y: location[1],
			                        xAxisPoint: api.coord([api.value(0), 0])
			                    },
			                    style: {
			                        fill: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                            offset: 0,
			                            color: '#33F7FB'
			                        }, {
			                            offset: 1,
			                            color: '#00FFFE'
			                        }])
			                    }
			                }]
			            }
			        },
			        data: VALUE
			    }, {
			        type: 'bar',
			        label: {
			            normal: {
			                show: true,
			                position: 'top',
			                formatter: (e) => {
			                    switch (e.name) {			                        case '广播':			                            return MAX[0]			                        case '监控':			                            return MAX[1]			                        case '网关':			                            return MAX[2]			                        case '照明':			                            return MAX[3]			                        case '显示屏':			                            return MAX[4]			                        case '其他':			                            return MAX[5]			                    }
			                },
			                fontSize: 10,
			                color: '#fff',
			                offset: [-5, -5]
			            }
			        },
			        itemStyle: {
			            color: 'transparent'
			        },
			        data: MAX
			    }]
			}
			myChart.setOption(option);
			let sizeFun = function() {
				myChart.resize();
			}
			window.addEventListener('resize', sizeFun)
			},
		onOff(){
			this.dnum = this.dnum+1;
			var nheight =$(".middle_bottom").height()*-1-10;
			 if (this.dnum % 2 != 0) {
			        $(".content_left").animate({left: "-20%"}, 500);
					$(".content_right").animate({right: "-20%"}, 500);
					$(".middle_bottom").animate({bottom: nheight}, 500);
					$(".btn_on").animate({right:"4%"}, 500);
					$(".btn_on").children("img").attr("src","../img/on2.gif");
					$(".img_btn").animate({left:"4%"}, 500);
					$(".img_btn").animate({top:"95px"}, 500);
					$(".dimension").animate({right:"4%",top:"150px"}, 500);
					setTimeout(function () {
					$(".right_on").animate({right: "0"}, 500);
					$(".left_on").animate({left: "0"}, 500);
					$(".bottom_on").animate({bottom: "0"}, 500);
					}, 600);
					$(".btn_on").children("img").attr("src","../img/on2.gif");
			    } else {
				setTimeout(function () {
			        $(".content_left").stop(true, true).animate({left: "0"}, 500);
					$(".content_right").stop(true, true).animate({right: "0"}, 500);
					$(".middle_bottom").stop(true, true).animate({bottom: "0"}, 500);
					$(".btn_on").stop(true, true).animate({right: "21%"}, 500);
					$(".btn_on").children("img").attr("src","../img/on1.gif");
					$(".img_btn").stop(true, true).animate({left: "22%"}, 500);
					$(".dimension").stop(true, true).animate({right:"24%",top:"120px"}, 500);
					}, 600);
					$(".right_on").stop(true, true).animate({right: "-20%"}, 500);
					$(".left_on").stop(true, true).animate({left: "-20%"}, 500);
					$(".bottom_on").stop(true, true).animate({bottom: "-20%"}, 500);
					
			    }
				
		},
		videos() {
			var widthv = $(".monitoring").width();
			console.log(widthv)
			
			setTimeout(()=>{
				var heightv = $(".monitoring").height();//widthv* 10 /16
				console.log(heightv)
				var player = cyberplayer("playercontainer").setup({
						
					file: "rtmp://rtmp01open.ys7.com/openlive/ac04fff8445444efb4bf215598e18b20", // <—rtmp直播地址
					width: widthv,
					height: heightv,
					autostart: true,
					stretching: "uniform",
					volume: 100,
					controls: false,
					rtmp: {
						reconnecttime: 5, // rtmp直播的重连次数
						bufferlength: 1 // 缓冲多少秒之后开始播放 默认1秒
					},
					ak: "87a966ddaf2e45d6a0c4a5930c4d98ef" // 公有云平台注册即可获得accessKey
				});
			},200)
			
		},
		swipers() {
			const that = this;
			axios.get(ServerBaseUrl + ':8011/getWarningInfo/warninginfo', { //params参数必写 , 如果没有参数传{}也可以
				params: {
					start:"1",
					stop:"4",
				}
			}).then(function(res) {

				if (res.status == 200) {
					console.log(res)
					that.imgs = res.data.data;
					
					
					for(var i= 0;i<4;i++){
						that.imgs[i].url = ServerBaseUrl+":80/" + that.imgs[i].url;
						
					}
					console.log(that.imgs)
				} else {
					console.log(statusText)
				}
			}).catch(function(err) {
				console.log(err);
			});

		},
		
	},

	created() {
		this.getTable();
		this.light();
		this.swipers();
		this.videos()
		this.timer = setInterval(() => {
			this.setNowTimes();
		}, 500);
		
		setTimeout(() => {
			this.drawChart();
			this.drawDate()
		},200);
		/* this.separate() */
		this.timer = setInterval(() => {this.getTable()
		}, 500000);
		
		$(function() {
			
			var viewH = $(window).height();
			$(".bodys").height(viewH)
			var mainHeight = (viewH-131)/4;
			$(".main_ul").children("li").height(mainHeight);
			$(".main_ul1").children("li").height(mainHeight-10);
			var bheight  =(mainHeight-75)/2;
			$(".environment li").height(bheight);
			var cheight  =(mainHeight-203)/6;
			$(".overview li").css("margin-top", cheight)
			$(".overview li").css("margin-bottom", cheight)
			$(".middle_bottom").height(mainHeight);
			var nheight =$(".nav_bottom").height();
			var taheight =mainHeight-nheight-40;
			$(".bottom_box").height(taheight);
			var imgh = mainHeight-80;
			$(".zuxi").height(imgh);
			
			/* 绘制2D地图 */
			Shapes();
			$(".screen").click(function() {
				$(".input-card").show();
			});
			$(".cancel").click(function() {
				$(".input-card").hide();
			});
			$(".shapes").click(function() {
				Shapes();
				$(".shapes").addClass("inChecked");
				$(".modeling").removeClass("inChecked");
			});
			$(".modeling").click(function() {
				modeling();
				$(".modeling").addClass("inChecked");
				$(".shapes").removeClass("inChecked");
				
			});
		});
		/* 绘制2D地图 */
		function Shapes(){
			  map && map.destroy();
			  
			  
			var map = new AMap.Map('allMap', {
					zoom: 16, //级别
					mapStyle: 'amap://styles/31f8e758bcafa2d0aa535571d1a8616e',
					resizeEnable: true, //是否监控地图容器尺寸变化
					zooms:[16,20],
					rotateEnable:true,
					pitchEnable:true,
					center: [121.613776,31.303634], //中心点坐标
				});
				/* map.setMapStyle('amap://styles/31f8e758bcafa2d0aa535571d1a8616e');	 */
				var winds =`<div class="window_box" style="padding:15px 20px;width:355px;height:182px;display: block; "> <div class="window_top"> <h4>照明:MD-25</h4> <div class="fromRight"><input type="checkbox" name="check-1" value="4" class="lcs_check" autocomplete="off" /></div> </div> <div class="window_middle"> <div class="middle_item"> <span>在线</span> <p>在线状态</p> </div> <div class="middle_item"> <span>开</span> <p>设备状态</p> </div> <div class="middle_item"> <span>135.25kwh</span> <p>当日耗能</p> </div> </div> <div class="window_bottom"> <span>亮度调节：</span> <input type="range" min="1" max="99" step="1" value="99" data-rangeslider><span><output class="output"></output>Lux</span> </div> </div>`;
				 
				var lnglat = lnglats1[0];
				var windows ='<div class="windox"></div>'
				
				var marker = [];
				var  preIcon, clickIcon,markers = [];
				var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
				preIcon = new AMap.Icon({
				        image: "../img/icon0.png",
				        size: new AMap.Size(59, 71), //图标大小
				       anchor:'bottom-center',
				       zIndex:-100
				    });
				    clickIcon = new AMap.Icon({
				        image: "../img/icom0-1.png",
				        size: new AMap.Size(76, 86), //图标大小
				       anchor:'top-center',
				       zIndex:-100
				    });

			
			for (var i = 0; i < lnglat.length; i++) {
				var lng = lnglat[i];
				marker = new AMap.Marker({
						position: new AMap.LngLat(lng[0], lng[1]),
						icon: preIcon,
						extData: {
							id:"a"+(i + 1)
						},
						
					});
					
					markers.push(marker);
					marker.con = winds ;
					marker.emit('click', {target: marker});
					marker.on('click', markerClick);
					
					}
					map.add(new AMap.OverlayGroup(markers));
				function markerClick(e) {
						  
				       
					   setTimeout(function(){
						   infoWindow.setContent(e.target.con);
						   $('input').lc_switch();
						   $('body').delegate('.lcs_check', 'lcs-statuschange', function() {
						   	var status = ($(this).is(':checked')) ? 'checked' : 'unchecked';
						   	console.log('field changed status: '+ status );
							if(status =="checked"){
							var onstatus = "on";	
							}else{
							var onstatus = "off";		
							};
							axios.get(ServerBaseUrl + ':8011/getHeatMap/getLight?ctrlType=switch', { //params参数必写 , 如果没有参数传{}也可以
								params: {
									value: onstatus,
								},
							}).then(function() {
							
							}).catch(function(err) {
								
							});
						   });
						   $('body').delegate('.lcs_check', 'lcs-on', function() {
						   	console.log('field is checked');
						   });
						   $('body').delegate('.lcs_check', 'lcs-off', function() {
						   	console.log('field is unchecked');
						   });
							var $document   = $(document);
							var selector    = '[data-rangeslider]';
							var $inputRange = $(selector);
							
							// Example functionality to demonstrate a value feedback
							// and change the output's value.
							function valueOutput(element) {
							    var value = element.value;
							    var output = element.parentNode.getElementsByTagName('output')[0];
							
							    output.innerHTML = value;
							}
							
							// Initial value output
							for (var i = $inputRange.length - 1; i >= 0; i--) {
							    valueOutput($inputRange[i]);
							};
							
							// Update value output
							$document.on('input', selector, function(e) {
							    valueOutput(e.target);
								var text = $('.output').text()
								console.log(text)
								axios.get(ServerBaseUrl + ':8011/getHeatMap/getLight?ctrlType=pwm&value=' + text, { //params参数必写 , 如果没有参数传{}也可以
									params: {
										
									},
								
								}).then(function(res) {
								
								}).catch(function(err) {
									console.log(err);
								});
							});
						 },100)
				       infoWindow.open(map, e.target.getPosition());
					  
					    for (var i = 0; i < markers.length; i++) {
					               markers[i].setIcon(preIcon);
								 
					           }
				
						e.target.setIcon(clickIcon);
										 var tyoe = e.target.getExtData();
										 console.log(tyoe)
				   }
				$("#btn_box").click(function() {
								var text = $("#types").val();
								console.log(text)
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
														size: new AMap.Size(59, 71), //图标大小
														image: "../img/icon" + indexIcon + ".png",
														anchor:'bottom-center',
														zIndex:-100
													}),
													extData: {
														id: i + 1
													},
													
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
														size: new AMap.Size(59, 71), //图标大小
														image: "../img/icon" + indexs + ".png",
														anchor:'bottom-center',
														zIndex:-100
													}),
													extData: {
														id: i + 1
													},
													
												});
												markers.push(marker[i]);
												map.add(new AMap.OverlayGroup(markers));
												
											
											};
											
										}
										
				
									};
				
								}
								
				
							});
				
			    map.setFitView();
		};
		/* 绘制3D地图 */
			function modeling(){
				  map && map.destroy();
				var map = new AMap.Map('allMap', {
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
					      right:'22%',
					      top:'178px'
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
		//根据预览器的窗口改变宽高
		$(window).resize(function() {
			var viewH = $(window).height();
			$(".bodys").height(viewH)
			var mainHeight = (viewH-131)/4;
			$(".main_ul").children("li").height(mainHeight);
			var bheight  =(mainHeight-75)/2;
			$(".environment li").height(bheight);
			var cheight  =(mainHeight-203)/6;
			$(".overview li").css("margin-top", cheight)
			$(".overview li").css("margin-bottom", cheight)
			$(".middle_bottom").height(mainHeight);
			var nheight =$(".nav_bottom").height();
			var taheight =mainHeight-nheight-35;
			$(".bottom_box").height(taheight);
			var imgh = mainHeight-80;
			$(".zuxi").height(imgh);
		});
		

	},
	beforeDestroy() {
		clearInterval(this.timers);
		clearInterval(this.timer);
	}

})
