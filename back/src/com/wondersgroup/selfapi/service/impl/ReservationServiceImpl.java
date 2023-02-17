package com.wondersgroup.selfapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.PlaceInfo;
import com.wondersgroup.selfapi.bean.WindowGroupInfo;
import com.wondersgroup.selfapi.bean.WindowHall;
import com.wondersgroup.selfapi.dao.ReservationDao;

import com.wondersgroup.selfapi.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao reservationDao;

	/**
	 * 获取预约大厅列表
	 */
	@Override
	public List<WindowHall> getAllHallList() {
		return reservationDao.getAllHallList();
	}

	/**
	 * 获取所有包含可预约事项信息的大厅信息
	 */
	@Override
	public List<WindowHall> getAllHallListForNet() {
		return reservationDao.getAllHallListForNet();
	}

	/**
	 * 根据预约大厅的编码获取其下的包含可预约事项的部门
	 */
	@Override
	public List<OrganNodeInfo> getAllOrganNetListByHallCode(String stHallCode) {

		return reservationDao.getAllOrganNetListByHallCode(stHallCode);
	}

	/**
	 * 根据大厅编码和办理点的ID获取其下所有的可预约事项
	 */
	@Override
	public ArrayList<ItemSet> getAllItemListByIdForPage(int pageSize,
			int currentPage, String hallCode, String placeId) {
		if (pageSize == 0) {
			pageSize = 10;
		}
		if (currentPage == 0) {
			currentPage = 1;
		}
		return reservationDao.getAllItemListByCodeAndIdForPage(pageSize,
				currentPage, hallCode, placeId);
	}

	@Override
	public ArrayList<ItemSet> getAllItemListById(String hallCode, String placeId) {

		return reservationDao.getAllItemListByCodeAndId(hallCode, placeId);
	}

	/**
	 * 获取所有的办理点
	 */
	@Override
	public List<PlaceInfo> getAllPlaceInfo(String part) {
		return reservationDao.getAllPlaceInfo(part);
	}

	/**
	 * 根据办理点的父节点获取所有的办理点(根据WINDOW_AREA办理点信息表)
	 */
	@Override
	public List<PlaceInfo> getAllPlaceInfos(String stParentAreaId) {
		return reservationDao.getAllPlaceInfos(stParentAreaId);
	}

	/**
	 * 根据办理点id获取其下的业务组别集合
	 */
	@Override
	public List<WindowGroupInfo> getAllWindowGroupInfoByPlaceId(String stPlaceId) {
		return reservationDao.getAllWindowGroupInfoByPlaceId(stPlaceId);
	}

	/**
	 * 获取所有的部门（根据部门的编码和办理点的id）
	 */
	@Override
	public List<OrganNodeInfo> getAllOrgansByPlaceId(String dept, String placeId) {
		return reservationDao.getAllOrgansByPlaceId(dept, placeId);
	}

	/**
	 * 根据部门编码获取其下的所有部门
	 */
	@Override
	public List<OrganNodeInfo> getAllOrgans(String dept) {
		return reservationDao.getAllOrgans(dept);
	}

	/**
	 * 获取部门下的所有的可预约的事项(分页)
	 */
	@Override
	public List<ItemSet> getAllItemsByOrganId(String organId, Integer pageSize,
			Integer currentPage) {
		return reservationDao.getAllItemsByOrganId(organId, pageSize,
				currentPage);
	}

	/**
	 * 获取部门下所有的可预约的事项
	 */
	@Override
	public List<ItemSet> getAllItemsByOrganId(String organId) {
		return reservationDao.getAllItemsByOrganId(organId);
	}

	/**
	 * 根据办理点id和部门id获取部门下的所有的可预约的事项(分页)
	 */
	@Override
	public List<ItemSet> getAllItemsByOrganIdAndPlaceId(String organId,
			String placeId, Integer pageSize, Integer currentPage) {
		return reservationDao.getAllItemsByOrganIdAndPlaceId(organId, placeId,
				pageSize, currentPage);
	}

	/**
	 * 根据办理点id和部门id获取部门下的所有的可预约的事项
	 */
	@Override
	public List<ItemSet> getAllItemsByOrganIdAndPlaceId(String organId,
			String placeId) {
		return reservationDao.getAllItemsByOrganIdAndPlaceId(organId, placeId);
	}

	/**
	 * 根据部门id获取部门下对应的所有组别信息
	 */
	@Override
	public List<WindowGroupInfo> getAllWindowGroupInfo(String organId) {
		return reservationDao.getAllWindowGroupInfo(organId);
	}

	/**
	 * 根据组别编码或者id获取其下可预约的所有事项
	 */
	@Override
	public List<ItemSet> getAllItemsByGroupCode(String stGroupCode,
			Integer pageSize, Integer currentPage) {
		return reservationDao.getAllItemsByGroupCode(stGroupCode, pageSize,
				currentPage);
	}

	/**
	 * 根据事项名称模糊查询有可预约的事项
	 */
	@Override
	public List<ItemSet> getAllItemsByItemName(String stItemName,
			Integer pageSize, Integer currentPage) {
		return reservationDao.getAllItemsByItemName(stItemName, pageSize,
				currentPage);
	}

	/**
	 * 根据事项名称和办理点id模糊查询可预约的事项
	 */
	@Override
	public List<ItemSet> getAllItemsByItemNameAndPlaceId(String stItemName,
			String placeId, Integer pageSize, Integer currentPage) {
		return reservationDao.getAllItemsByItemNameAndPlaceId(stItemName,
				placeId, pageSize, currentPage);
	}
}
