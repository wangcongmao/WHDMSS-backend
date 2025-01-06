/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 * No deletion without permission, or be held responsible to law.
 * @author wangcm
 */
import { defHttp } from '/@/utils/http/axios';
import { useGlobSetting } from '/@/hooks/setting';
import { BasicModel, Page } from '/@/api/model/baseModel';

const { adminPath } = useGlobSetting();

export interface DataserviceDeviceStructure extends BasicModel<DataserviceDeviceStructure> {
  structureDeviceId?: string; // 设备编号
  structureData?: string; // 设备数据结构
}

export const dataserviceDeviceStructureList = (params?: DataserviceDeviceStructure | any) =>
  defHttp.get<DataserviceDeviceStructure>({ url: adminPath + '/dataservice/deviceStructure/list', params });

export const dataserviceDeviceStructureListData = (params?: DataserviceDeviceStructure | any) =>
  defHttp.post<Page<DataserviceDeviceStructure>>({ url: adminPath + '/dataservice/deviceStructure/listData', params });

export const dataserviceDeviceStructureForm = (params?: DataserviceDeviceStructure | any) =>
  defHttp.get<DataserviceDeviceStructure>({ url: adminPath + '/dataservice/deviceStructure/form', params });

export const dataserviceDeviceStructureSave = (params?: any, data?: DataserviceDeviceStructure | any) =>
  defHttp.postJson<DataserviceDeviceStructure>({ url: adminPath + '/dataservice/deviceStructure/save', params, data });

export const dataserviceDeviceStructureDelete = (params?: DataserviceDeviceStructure | any) =>
  defHttp.get<DataserviceDeviceStructure>({ url: adminPath + '/dataservice/deviceStructure/delete', params });
