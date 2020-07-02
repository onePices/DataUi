package com.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.entity.CommonVar;
import com.web.entity.ScreenVideo;
import com.web.service.ScreenVideoService;

@RestController
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,RequestMethod.POST}, origins="*")
public class TestInfoController {
	@Autowired
	private ScreenVideoService screenVideoService;
	
	


	/**
	 *（展示用）获取首页路灯信息
	 */
	@RequestMapping(value="getLightInfo/lightInfo", method=RequestMethod.GET)
	public String[][] lightInfo() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dstr="2020-4-24";
		Date date = null;
		try {
			date = sdf.parse(dstr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Double en = (double) ((new Date().getTime()-date.getTime())/1000/3600);
		en = en *5.2;
		Calendar calendar = Calendar.getInstance();
		int curHour24 = calendar.get(calendar.HOUR_OF_DAY);
		Double todayEnery = curHour24*5.2;
		Double other = en - todayEnery;
		String[][] result = new String[3][3];
		result[0][0]="40";
		result[0][1]="40";
		result[0][2]="0";	
		result[1][0]="80";
		result[1][1]="79";
		result[1][2]="1";
		result[2][0]= en.toString();
		result[2][1]= todayEnery.toString();
		result[2][2]= other.toString();
		return result;
	}
	


	/**
	 *（展示用）获取首页7天能耗折线图
	 */
	@RequestMapping(value="getEnergyConsu/getEnergyChart", method=RequestMethod.GET)
	public Double[][] getRecentlyFour() {
		if(CommonVar.SevenDayEnergy == null) {
			Double[][] result = new Double[11][7];
			for(int j = 0; j<7; j++) {
				for(int i=0; i<7; i++) {
					if(i==6) {
						Calendar calendar = Calendar.getInstance();
						int curHour24 = calendar.get(calendar.HOUR_OF_DAY);
						result[j][i] = curHour24 * (Double.parseDouble(String.format("%.1f", Math.random()))+CommonVar.HourEnergy[j]);
					}else {
						result[j][i] = 24.0* (Double.parseDouble(String.format("%.1f", Math.random()))+CommonVar.HourEnergy[j]);
					}
				}
			}
			for(int j = 7; j<9; j++) {
				for(int i=0; i<7; i++) {
					if(i==6) {
						Calendar calendar = Calendar.getInstance();
						int curHour24 = calendar.get(calendar.HOUR_OF_DAY);
						result[j][i] = curHour24 * (Double.parseDouble(String.format("%.1f", Math.random()))/1000+CommonVar.HourEnergy[j]);
					}else {
						result[j][i] = 24.0* (Double.parseDouble(String.format("%.1f", Math.random()))/1000+CommonVar.HourEnergy[j]);
					}
				}
			}
			for(int j = 9; j<11; j++) {
				for(int i=0; i<7; i++) {
					if(i==6) {
						Calendar calendar = Calendar.getInstance();
						int curHour24 = calendar.get(calendar.HOUR_OF_DAY);
						result[j][i] = curHour24 * (Double.parseDouble(String.format("%.1f", Math.random()))/10+CommonVar.HourEnergy[j]);
					}else {
						result[j][i] = 24.0* (Double.parseDouble(String.format("%.1f", Math.random()))/10+CommonVar.HourEnergy[j]);
					}
				}
			}
			CommonVar.SevenDayEnergy = result;
			return result;
		}else {
			return CommonVar.SevenDayEnergy;
		}
	}
	


	/**
	 *（展示用）获取首页警告信息
	 */
	@RequestMapping(value="getWarningInfo/warninginfo", method=RequestMethod.GET)
	@ResponseBody
	public Object warninginfo() {
		if(CommonVar.WarningInfoTeam[0] == null) {
			String[] result = new String[4];
			Random random = new Random();
			List<String> param = new ArrayList<String>();
			while(param.size()<4) {
				Integer a=random.nextInt(7);
				if(param.contains(a.toString()))
					continue;
				else
					param.add(a.toString());
			}
			for(int j=0; j<4; j++) {
				result[j]=CommonVar.WarningInfo[Integer.parseInt(param.get(j))];
				CommonVar.WarningInfoTeamIndex[j] = Integer.parseInt(param.get(j));
				result[j] = result[j].replace("待替换图片", CommonVar.Pictrue[Integer.parseInt(param.get(j))]);
				result[j] = result[j].replace("等待替换", CommonVar.dateToString(new Date(new Date().getTime()-j*1000*60*5), new SimpleDateFormat("yyyy-MM-dd HH:mm")));
			}
			CommonVar.WarningInfoTeam = result;
		}else {
			CommonVar.WarningInfoTeam[0] = null;
			CommonVar.WarningInfoTeamIndex[0]=null;
			List<Integer> param = new ArrayList<Integer>();
			Random random = new Random();
			Integer a=null;
			for(int i=1; i<4; i++) {
				param.add(CommonVar.WarningInfoTeamIndex[i]);
			}
			while(true) {
				a = random.nextInt(7);
				if(param.contains(a))
					continue;
				else
					break;
			}
			Integer[] wii = CommonVar.WarningInfoTeamIndex;
			String[] wit = CommonVar.WarningInfoTeam;
			for(int i=0; i<4; i++) {
				if(i == 3) {
					
				}else {
					CommonVar.WarningInfoTeamIndex[i] = wii[i+1];
					CommonVar.WarningInfoTeam[i] = wit[i+1];
				}
			}
			CommonVar.WarningInfoTeamIndex[3]=a;
			CommonVar.WarningInfoTeam[3] = CommonVar.WarningInfo[CommonVar.WarningInfoTeamIndex[3]];
			CommonVar.WarningInfoTeam[3] = CommonVar.WarningInfoTeam[3].replace("待替换图片", CommonVar.Pictrue[a]);
			CommonVar.WarningInfoTeam[3] = CommonVar.WarningInfoTeam[3].replace("等待替换", CommonVar.dateToString(new Date(new Date().getTime()-1000*60*4), new SimpleDateFormat("yyyy-MM-dd HH:mm")));
		}
		return CommonVar.WarningInfoTeam;
	}
	
	

	/**
	 *（展示用）获取首页温度云图
	 */
	@RequestMapping(value="getHeatMap/getHeatMap", method=RequestMethod.GET)
	@ResponseBody
	public String getHeatMap() {
		String result = "[";
		int index = 1;
		Double value1=null, value2=null;
		for(int i=0;i<666; i++) {
			value1 = 121.150000+Double.valueOf(String.format("%.6f", Math.random()/2));
			value2 = 30.900000+Double.valueOf(String.format("%.6f", Math.random()/2));
			if(i==13 || i==29 || i==120 || i==134 || i==176 || i==203 || i==229 || i==251 || i==308 || i==337 || i==402 || i==419 || i==440 || i==457 || i==471 || i==490 || i==517|| i==535 || i==576 || i==600 || i==617)
				index=1;
			if(i==665)
				result+="{\"lng\":"+value1+",\"lat\":"+value2+",\"count\":"+index+"}]";
			else
				result+="{\"lng\":"+value1+",\"lat\":"+value2+",\"count\":"+index+"},";
			index++;
		}
		return result;
	}
	
	/**
	 *（展示用）获取首页摄像头截图
	 */
	@RequestMapping(value="getHeatMap/getCameroPic", method=RequestMethod.GET)
	@ResponseBody
	public String[] getCameroPic() {
		return CommonVar.CameroPic;
	}
	
	/**
	 *	添加视频素材信息
	 */
	@RequestMapping(value="screenvideo/addVideoInfo", method=RequestMethod.GET)
	public boolean addVideoInfo(@ModelAttribute("screenVideo") ScreenVideo screenVideo) {
		screenVideoService.insert(screenVideo);
		return true;
	}
	
	/**
	 *	根据条件分页查询视频素材信息
	 */
	@RequestMapping(value="screenvideo/getVideoInfoByPage", method=RequestMethod.GET)
	public List<ScreenVideo> getVideoInfoByPage(@ModelAttribute("screenVideo") ScreenVideo screenVideo){
		return screenVideoService.selectScreenVideoByPage(screenVideo);
	}
	
	
	
	
	/**
	 *	模拟登录灵信SDK
	 */
	@RequestMapping(value="screenvideo/loginSDK", method=RequestMethod.GET)
	public void loginSDK() throws IOException {
		URL url = new URL("http://127.0.0.1:8080/ListenSdkService/sdkNew/loginIn");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		conn.setDoOutput(true);
		conn.setDoInput(true);

		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		String data = "username=\"1qElmsZfk7s=\"&password=\"HWCJ8d5epYEqERA63acr2w==\"";
		out.write(data);
		out.flush();
		InputStream is = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = "";
		String result = "";
		while ((str = br.readLine()) != null){
			result += str;
		}
		System.out.println(result);
		String session_value = conn.getHeaderField("Set-Cookie");
		CommonVar.SessionId = session_value.split(";")[0];
		is.close();
		conn.disconnect();
	}
	
	
	
	
	
	/**
	 *	设置灵信LED屏幕 展示选中的视频素材
	 */
	@RequestMapping(value="screenvideo/setLedScreen", method=RequestMethod.GET)
	public void setLedScreen(String data){
		OutputStreamWriter out = null;
		BufferedReader br = null;
		String result = "";
		data = "devCode=QC202006190005&strProgramList={\"programId\":\"11\",\"programName\":\"11\",\"pageResolution\":[{\"resolution\":\"1920X1080\"}],\"playTimesFlag\":\"1\",\"playTimes\":\"0\",\"page\":[{\"name\":\"页面1\",\"pagTime\":10,\"areaList\":{\"pageEle\":[{\"bgImg\":\"\",\"bgColor\":\"\",\"bgName\":\"\",\"pageTime\":10,\"eqType\":\"q5\"}],\"mediaEle\":[{\"id\":2,\"type\":0,\"top\":0,\"left\":0,\"width\":1920,\"height\":1080,\"zIndex\":2,\"siderType\":\"0\",\"pauseTime\":7,\"borderSW\":0,\"borderType\":1,\"borderEffect\":1,\"borderSpeed\":1,\"rotation\":0,\"srcGroup\":[{\"name\":\"720p.mp4\",\"id\":123,\"size\":8400241,\"time\":25,\"src\":\"http://39.99.166.20:8080/video/video1.mp4\",\"playTimes\":0,\"dateSW\":0,\"endDate\":0,\"startDate\":0},{\"name\":\"2.mp4\",\"id\":123,\"size\":727442,\"time\":10,\"src\":\"http://39.99.166.20:8080/video/video1.mp4\",\"playTimes\":0,\"dateSW\":0,\"endDate\":0,\"startDate\":0}]}]}}],\"programPlan\":[{\"defaultPid\":\"\",\"onlyCut\":1,\"program\":[{\"createDate\":1557244800000,\"date\":[{\"dateEnd\":1559923200000,\"dateStart\":1557244800000}],\"isDefault\":0,\"planWeekDay\":[1,1,1,1,1,1,1],\"programId\":\"11\",\"programName\":\"11\",\"programType\":2,\"time\":[{\"timeEnd\":\"22:59:59\",\"timeStart\":\"00:00:00\"}],\"updateDate\":1557244800000}]}]}";
		try {
			URL url = new URL("http://127.0.0.1:8080/ListenSdkService/sdkNew/lsPublishProgram.fgl");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Cookie", CommonVar.SessionId);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			conn.setDoOutput(true);
			conn.setDoInput(true);

			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(data);
			out.flush();
			InputStream is = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null){
				result += str;
			}
			System.out.println(result);
			is.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (out != null){
					out.close();
				}
				if (br != null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
