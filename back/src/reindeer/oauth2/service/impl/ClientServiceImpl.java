package reindeer.oauth2.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reindeer.oauth2.bean.Oauth2Client;
import reindeer.oauth2.dao.Oauth2ClientDao;
import reindeer.oauth2.service.ClientService;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private Oauth2ClientDao onetOauth2ClientDao;

	@Override
	public Oauth2Client createClient(Oauth2Client client) {
		client.setStOauth2Id(UUID.randomUUID().toString());
		client.setStClientId(UUID.randomUUID().toString());
		client.setStClientSecret(UUID.randomUUID().toString());
		onetOauth2ClientDao.add(client);
		return client;
	}

	@Override
	public Oauth2Client updateClient(Oauth2Client client) {
		onetOauth2ClientDao.update(client);
		return client;
	}

	@Override
	public void deleteClient(String clientId) {
		onetOauth2ClientDao.delete(clientId);
	}

	@Override
	public Oauth2Client findOne(String clientId) {
		return onetOauth2ClientDao.get(clientId);
	}

	@Override
	public List<Oauth2Client> findAll() {
		return onetOauth2ClientDao.query(null, null);
	}

	@Override
	public Oauth2Client findByClientId(String clientId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_CLIENT_ID", Condition.OT_EQUAL, clientId));
		List<Oauth2Client> list = onetOauth2ClientDao.query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Oauth2Client findByClientSecret(String clientSecret) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_CLIENT_SECRET", Condition.OT_EQUAL,
				clientSecret));
		List<Oauth2Client> list = onetOauth2ClientDao.query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public Oauth2Client add(HttpReqRes httpReqRes) {
		String clientId = httpReqRes.getParameter(Oauth2Client.ST_CLIENT_ID);
		String clientName = httpReqRes
				.getParameter(Oauth2Client.ST_CLIENT_NAME);
		Oauth2Client client = null;
		if (StringUtils.isNotBlank(clientName)
				&& StringUtils.isNotBlank(clientId)) {
			try {
				client = new Oauth2Client();
				client.setStOauth2Id(UUID.randomUUID().toString());
				client.setStClientId(clientId);
				client.setStClientName(clientName);
				client.setStInterfaceUser(clientName);
				OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(
						new MD5Generator());
				client.setStClientSecret(oauthIssuerImpl.authorizationCode());
				onetOauth2ClientDao.add(client);
			} catch (OAuthSystemException e) {
				e.printStackTrace();
			}
		}
		return client;
	}
}
