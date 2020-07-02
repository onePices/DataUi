package com.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.web.entity.CommonVar;

public class TcpReceiveMsg implements Runnable {

	/**
	 * 测试远程控制粤控设备
	 */
	public void run() {
		try { 
			ServerSocket ss = new ServerSocket(6789);
			Socket s = ss.accept();
			byte[] recData = null;
			String[] lightValue = new String[5];
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			byte[] lightport = CommonVar.getLightPort();
			byte[] ip = CommonVar.getLocalIpAddress();
			while(true) {
				recData = new byte[100];
				int r = in.read(recData);
				if(r>-1) {
					String lightid = new String(recData);
					System.out.println(lightid);
					String lightkey = lightid.length()>=18?lightid.substring(6,18):lightid.substring(6);
					if(CommonVar.Light.get(lightkey)==null) {
						lightValue[0]=lightid.substring(0, 6);
						lightValue[1]=lightid.substring(6, 9);
						lightValue[2]=lightid.substring(9, 12);
						lightValue[3]=lightid.substring(12, 15);
						lightValue[4]=lightid.substring(15, 18);
						CommonVar.BuildTcpMsg[1]=(byte) Integer.parseInt(lightValue[1]);
						CommonVar.BuildTcpMsg[2]=(byte) Integer.parseInt(lightValue[2]);
						CommonVar.BuildTcpMsg[3]=(byte) Integer.parseInt(lightValue[3]);
						CommonVar.BuildTcpMsg[9]=(byte) Integer.parseInt(lightValue[4]);
						if(lightid.substring(0,6).equals("@##000") || lightid.substring(0,6).equals("#&&000")) {
							CommonVar.BuildTcpMsg[17]=ip[0];
							CommonVar.BuildTcpMsg[18]=ip[1];
							CommonVar.BuildTcpMsg[19]=ip[2];
							CommonVar.BuildTcpMsg[20]=ip[3];
							CommonVar.BuildTcpMsg[21]=lightport[0];
							CommonVar.BuildTcpMsg[22]=lightport[1];
							out.write(CommonVar.BuildTcpMsg);
							out.flush();
							System.out.println("与路灯建立长连接成功！"+lightkey);
							System.out.println(CommonVar.setLight(lightkey, lightValue));
						}
					}
				}
				if(CommonVar.LightOperate.size()>0) {
					for (String key : CommonVar.LightOperate.keySet()) {
						if(CommonVar.LightOperate.get(key).equals("开灯") && CommonVar.Light.get(key)!=null) {
							CommonVar.TurnOnLightMsg[1] = (byte) Integer.parseInt(CommonVar.Light.get(key)[1]);
							CommonVar.TurnOnLightMsg[2] = (byte) Integer.parseInt(CommonVar.Light.get(key)[2]);
							CommonVar.TurnOnLightMsg[3] = (byte) Integer.parseInt(CommonVar.Light.get(key)[3]);
							CommonVar.TurnOnLightMsg[9] = (byte) Integer.parseInt(CommonVar.Light.get(key)[4]);
							out.write(CommonVar.TurnOnLightMsg);
							out.flush();
						}else if(CommonVar.LightOperate.get(key).equals("关灯") && CommonVar.Light.get(key)!=null) {
							CommonVar.TurnOffLightMsg[1] = (byte) Integer.parseInt(CommonVar.Light.get(key)[1]);
							CommonVar.TurnOffLightMsg[2] = (byte) Integer.parseInt(CommonVar.Light.get(key)[2]);
							CommonVar.TurnOffLightMsg[3] = (byte) Integer.parseInt(CommonVar.Light.get(key)[3]);
							CommonVar.TurnOffLightMsg[9] = (byte) Integer.parseInt(CommonVar.Light.get(key)[4]);
							out.write(CommonVar.TurnOffLightMsg);
							out.flush();
						}else {
							System.out.println("路灯已离线 或 指令错误");
						}
						CommonVar.Light.remove(key);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
