package com.wondersgroup.selfapi.service;


import java.util.ArrayList;
import java.util.List;

import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.PlaceInfo;
import com.wondersgroup.selfapi.bean.WindowGroupInfo;
import com.wondersgroup.selfapi.bean.WindowHall;

public interface ReservationService {


	List<WindowHall> getAllHallList();

	List<WindowHall> getAllHallListForNet();

	List<OrganNodeInfo> getAllOrganNetListByHallCode(String stHallCode);

	ArrayList<ItemSet> getAllItemListByIdForPage(int pageSize, int currentPage,
			String hallCode, String placeId);

	ArrayList<ItemSet> getAllItemListById(String hallCode, String placeId);



	
	/**
	 * 方法描述：获取所有的办理点信息
	 * @param part
	 * @return List<PlaceInfo>
	 */
	public List<PlaceInfo> getAllPlaceInfo(String part);

	
	/**
	 * 方法描述：根据办理点的父节点获取所有的办理点(根据WINDOW_AREA办理点信息表)
	 * @param stParentAreaId
	 * @return List<PlaceInfo>
	 */
	public List<PlaceInfo> getAllPlaceInfos(String stParentAreaId);


	/**
	 * 方法描述：根据办理点id获取其下的业务组别集合
	 * @param stPlaceId
	 * @return List<WindowGroupInfo>
	 */
	public List<WindowGroupInfo> getAllWindowGroupInfoByPlaceId(String stPlaceId);


	/**
	 * 方法描述：获取所有的部门（根据部门的编码和办理点的id）
	 * @param dept
	 * @param placeId
	 * @return List<OrganNodeInfo>
	 */
	public List<OrganNodeInfo> getAllOrgansByPlaceId(String dept, String placeId);


	/**
	 * 方法描述：获取所有的部门（根据部门的编码）
	 * @param dept
	 * @return List<OrganNodeInfo>
	 */
	public List<OrganNodeInfo> getAllOrgans(String dept);


	/**
	 * 方法描述：获取部门下所有的可预约的事项(分页)
	 * @param organId
	 * @param pageSize
	 * @param currentPage
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByOrganId(String organId, Integer pageSize,
			Integer currentPage);


	/**
	 * 方法描述：获取部门下所有的可预约的事项
	 * @param organId
	 * @return
	 */
	public List<ItemSet> getAllItemsByOrganId(String organId);


	/**
	 * 方法描述：获取部门下所有的可预约的事项(分页)
	 * @param organId
	 * @param placeId
	 * @param pageSize
	 * @param currentPage
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByOrganIdAndPlaceId(String organId,
			String placeId, Integer pageSize, Integer currentPage);


	/**
	 * 方法描述：获取部门下所有的可预约的事项
	 * @param organId
	 * @param placeId
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByOrganIdAndPlaceId(String organId,
			String placeId);


	/**
	 * 方法描述：根据部门id获取部门下对应的所有组别信息
	 * @param organId
	 * @return List<WindowGroupInfo>
	 */
	public List<WindowGroupInfo> getAllWindowGroupInfo(String organId);


	/**
	 * 方法描述：根据组别编码或者id获取其下可预约的所有事项
	 * @param stGroupCode
	 * @param pageSize
	 * @param currentPage
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByGroupCode(String stGroupCode,
			Integer pageSize, Integer currentPage);


	/**
	 * 方法描述：根据事项名称模糊查询可预约的事项
	 * @param stItemName
	 * @param pageSize
	 * @param currentPage
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByItemName(String stItemName,
			Integer pageSize, Integer currentPage);


	/**
	 * 方法描述：根据事项名称和办理点id模糊查询有可预约的事项
	 * @param stItemName
	 * @param placeId
	 * @param pageSize
	 * @param currentPage
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByItemNameAndPlaceId(String stItemName,
			String placeId, Integer pageSize, Integer currentPage);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

	
	