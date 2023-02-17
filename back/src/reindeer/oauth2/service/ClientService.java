package reindeer.oauth2.service;

import java.util.List;

import reindeer.oauth2.bean.Oauth2Client;
import tw.ecosystem.reindeer.web.HttpReqRes;

public interface ClientService {

	public Oauth2Client createClient(Oauth2Client client);// 創建客戶端

	public Oauth2Client updateClient(Oauth2Client client);// 更新客戶端

	public void deleteClient(String clientId); // 刪除客戶端

	Oauth2Client findOne(String clientId); // 根據客戶端id查詢客戶端信息

	List<Oauth2Client> findAll(); // 查找所有的客戶端信息

	Oauth2Client findByClientId(String clientId); // 根據客戶端id查詢客戶端信息

	Oauth2Client findByClientSecret(String clientSecret);// 根據客戶端安全KEY查詢客戶端信息

	public Oauth2Client add(HttpReqRes httpReqRes);
}
