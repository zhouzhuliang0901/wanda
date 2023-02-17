package com.wondersgroup.infopub.util;

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

import tw.tool.helper.LogHelper;
import wfc.service.config.Config;

public class CometFactory {

	private static CometFactory thisInstance = null;
	private HttpClient httpClient = null;
	private ClientTransport transport = null;
	private ClientSession clientSession = null;
	private boolean isConnection = false;

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

	private String getConfig() {
		String url = Config.get("server.infopub.cometd.url");
		if (url == null)
			url = Config.get("server.infopub.url");
		return url;
	}

	private void init() {
		handshake();
		clientSession.getChannel(Channel.META_CONNECT).addListener(new ClientSessionChannel.MessageListener() {
			public void onMessage(ClientSessionChannel channel, Message message) {
				if (!message.isSuccessful()) {
					isConnection = false;
					LogHelper.info("已从Cometd服务器断开，稍后再次重连！【" + getConfig() + "】");
				}
				if (!isConnection) {
					handshake();
				}
			}
		});
	}

	private void handshake() {
		Map<String, Object> data = new HashMap<String, Object>();
		clientSession.handshake(data, new ClientSessionChannel.MessageListener() {
			public void onMessage(ClientSessionChannel channel, Message message) {
				if (message.isSuccessful()) {
					isConnection = true;
					LogHelper.info("连接Cometd服务器成功！【" + getConfig() + "】");
				} else {
					LogHelper.info("连接Cometd服务器失败！【" + message.toString()
							+ "】");
				}
			}
		});
	}

	public void send(String channelId, Map<String, Object> data) {
		if (isConnection) {
			LogHelper.info("准备往Cometd服务器" + channelId + "发送:" + data.toString());
			clientSession.getChannel(channelId).publish(data, new ClientSessionChannel.MessageListener() {
				@Override
				public void onMessage(ClientSessionChannel channel, Message message) {
					if (message.isSuccessful()) {
						System.out.println(message + "消息发送成功-*****************************");
					} else {
						LogHelper.error("往Cometd服务器发送消息出错！");
					}
				}
			});
		} else {
			LogHelper.error("已和Cometd服务器断开连接，无法发送消息！");
		}
	}
}
