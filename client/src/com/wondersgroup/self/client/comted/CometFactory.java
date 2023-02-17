package com.wondersgroup.self.client.comted;

import java.util.HashMap;
import java.util.Map;

import org.cometd.bayeux.Channel;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSession;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.ClientTransport;
import org.cometd.client.transport.LongPollingTransport;
import org.eclipse.jetty.client.HttpClient;


import wfc.service.config.Config;
import wfc.service.log.Log;

public class CometFactory {
	
	//接受消息的通道
	private static final String CHANNEL = "/client/"+Config.get("client.mac");
    private final ClientSessionChannel.MessageListener fooListener = new FooListener();
	private static CometFactory thisInstance = null;
	private HttpClient httpClient = null;
	private ClientTransport transport = null;
	private ClientSession clientSession = null;
	private boolean isConnection = false;

	private boolean lastFalse = true;
	private long lastTime = System.currentTimeMillis();

	private CometFactory() {
		httpClient = new HttpClient();
		try {
			httpClient.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> options = new HashMap<String, Object>();
		transport = new LongPollingTransport(options, httpClient);
		clientSession = new BayeuxClient(getConfig(), transport);
		init();
	}

	public static CometFactory getInstance() {
		if (thisInstance == null) {
			thisInstance = new CometFactory();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return thisInstance;
	}
	//连接服务器的地址配置
	private String getConfig() {
		String url = Config.get("server.padinteraction.cometd.url");
		if (url == null)
			url = Config.get("server.padinteraction.url");
		return url;
	}

	private void init() {
		handshake();
		clientSession.getChannel(Channel.META_CONNECT).addListener(
				new ClientSessionChannel.MessageListener() {
					public void onMessage(ClientSessionChannel channel,
							Message message) {
						if (!message.isSuccessful()) {
							isConnection = false;
							Log.info("已从Cometd服务器断开，稍后再次重连！【" + getConfig()
									+ "】");
							System.out.println("服务器连接断开");
						}
						if (!isConnection) {
							handshake();
						}
					}
				});
	}

	private void reset() {
		Map<String, Object> options = new HashMap<String, Object>();
		if (transport != null) {
			transport.terminate();
			transport = new LongPollingTransport(options, httpClient);
		}
		clientSession = new BayeuxClient(getConfig(), transport);
		init();
	}
	//握手，通道连通
	private void handshake() {
		Map<String, Object> data = new HashMap<String, Object>();
		clientSession.handshake(data,
				new ClientSessionChannel.MessageListener() {
					public void onMessage(ClientSessionChannel channel,
							Message message) {
						if (message.isSuccessful()) {
							//监听接受服务器通道上面的消息并且接收
							clientSession.getChannel(CHANNEL).subscribe(fooListener);
							isConnection = true;
							lastFalse = false;
							Log.info("连接Cometd服务器成功！【" + getConfig() + "】");
							System.out.println("服务器连接成功");
							//attach();
						}
					}
				});
	}
	//发送消息
	public void send(String channelId, Map<String, Object> data) {
		if (isConnection) {
			Log.info("准备往Cometd服务器" + channelId + "发送:" + data.toString());
			clientSession.getChannel(channelId).publish(data,
					new ClientSessionChannel.MessageListener() {
						@Override
						public void onMessage(ClientSessionChannel channel,
								Message message) {
							if (message.isSuccessful()) {
								System.out.println("服务器发送消息成功");
								//attach();
							} else {
								Log.error("往Cometd服务器发送消息出错！");
								System.out.println("服务器连接消息出错");
							}
						}
					});
		} else {
			if (lastFalse) {
				if ((System.currentTimeMillis() - lastTime) > 5 * 60 * 1000) {
					Log.info("由于和服务器连接早已超过了5分钟，所以需要重新连接，防止‘"
							+ Channel.META_CONNECT + "’" + "莫名失效。");
					System.out.println("服务器连接超时");
					reset();
				}
			} else {
				lastFalse = true;
				lastTime = System.currentTimeMillis();
				Log.info("由于和服务器连接失败，所以记录第一次失败时间");
				System.out.println("由于和服务器连接失败，所以记录第一次失败时间");
			}
			Log.error("已和Cometd服务器断开连接，无法发送消息！");
			System.out.println("已和Cometd服务器断开连接，无法发送消息！");
			
		}
	}
	
	/**
	 * 测试方法
	 */
	public static void main(String[] args) {
		Methods methods = new Methods();
		methods.resFiled();
	}
	
	//输出消息
	 @SuppressWarnings("all")
    private static class FooListener implements ClientSessionChannel.MessageListener
    {
		public void onMessage(ClientSessionChannel channel, Message message)
        {
			//System.out.println(message);
        	String opt = ((Map<String, Object>) message.getDataAsMap().get("data")).get("opt").toString();
        	String source = message.getDataAsMap().get("source").toString();
        	Log.info("进入订阅成功，执行方法"+opt);
        	System.out.println("进入订阅成功，执行方法"+opt);
        	Methods methods = new Methods();
        	if(opt.equals("snapshots")){
        		//截图
        		methods.snapshots(source);
        	}else if(opt.equals("reboot")){
        		//重启
        		methods.restart();
        	}else if(opt.equals("shutdown")){
        		//关机
        		methods.shutdown();
        	}else if(opt.equals("download")){
        		//根据指定URL将文件下载到指定目标位置
        		methods.downloadFile("http://zwdt.huangpuqu.sh.cn/hshall/gongzhong/images/word/1111102032131.docx", "C:/myFiles/");
        	}else if(opt.equals("runexe")){
        		//调用系统的exe文件，并运行
        		methods.Runexe();
        	}else if(opt.equals("rundebug")){
        		//调用系统的debug文件，并运行
        		methods.RunDebug();
        	}else if(opt.equals("redebug")){
        		//重启debug
        		methods.restartDebug();
        	}else if(opt.equals("stopexe")){
        		//调用系统的exe文件，并停止
        		methods.Stopexe();
        	}else if(opt.equals("modify")){
        		//修改xml
        		methods.modify();
        	}else if(opt.equals("xmlContext")){
        		methods.xmlContent();
        	}else if(opt.equals("upLoadDebug")){
        		methods.debugZip();
        	}else if(opt.equals("resFiled")){
        		methods.resFiled();
        	}
        }
    }
}
