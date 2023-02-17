package com.wondersgroup.selfapi.service;



import org.json.JSONObject;



import reindeer.base.utils.RequestWrapper;

public interface MaterialUpService {

	JSONObject organList(RequestWrapper wrapper);

	JSONObject itemByOrganId(RequestWrapper wrapper);

	JSONObject queryItem(RequestWrapper wrapper);

	JSONObject queryStatusList(RequestWrapper wrapper);

	JSONObject queryStuffList(RequestWrapper wrapper);

	JSONObject save(RequestWrapper wrapper);

	JSONObject uploadFile(RequestWrapper wrapper);

	JSONObject toSubmit(RequestWrapper wrapper);

}
