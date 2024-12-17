import { useI18n } from '/@/hooks/web/useI18n';
import { BasicColumn, BasicTableProps, FormProps } from '/@/components/Table';
import { dataserviceExperimentListData } from '/@/api/experiment/dataserviceExperiment';

const { t } = useI18n('experiment.dataserviceExperiment');

const modalProps = {
  title: t('试验信息表选择'),
};

const searchForm: FormProps = {
  baseColProps: { md: 8, lg: 6 },
  labelWidth: 90,
  schemas: [
    {
      label: t('试验名称'),
      field: 'experimentName',
      component: 'Input',
    },
    {
      label: t('试验场ID'),
      field: 'experimentTestinggroundid',
      component: 'Input',
    },
    {
      label: t('试验场名称'),
      field: 'experimentTestinggroundname',
      component: 'Input',
    },
    {
      label: t('试验单位'),
      field: 'experimentWorkplace',
      component: 'Input',
    },
    {
      label: t('试验人员'),
      field: 'experimentStaff',
      component: 'Input',
    },
    {
      label: t('测试时间'),
      field: 'experimentTestingtime',
      component: 'DatePicker',
      componentProps: {
        format: 'YYYY-MM-DD HH:mm',
        showTime: { format: 'HH:mm' },
      },
    },
    {
      label: t('创建用户id'),
      field: 'experimentUserid',
      component: 'Input',
    },
  ],
};

const tableColumns: BasicColumn[] = [
  {
    title: t('试验名称'),
    dataIndex: 'experimentName',
    key: 'a.experiment_name',
    sorter: true,
    width: 230,
    align: 'left',
    slot: 'firstColumn',
  },
  {
    title: t('试验场ID'),
    dataIndex: 'experimentTestinggroundid',
    key: 'a.experiment_testinggroundid',
    sorter: true,
    width: 130,
    align: 'center',
  },
  {
    title: t('试验场名称'),
    dataIndex: 'experimentTestinggroundname',
    key: 'a.experiment_testinggroundname',
    sorter: true,
    width: 130,
    align: 'left',
  },
  {
    title: t('试验单位'),
    dataIndex: 'experimentWorkplace',
    key: 'a.experiment_workplace',
    sorter: true,
    width: 130,
    align: 'left',
  },
  {
    title: t('试验人员'),
    dataIndex: 'experimentStaff',
    key: 'a.experiment_staff',
    sorter: true,
    width: 130,
    align: 'left',
  },
  {
    title: t('测试时间'),
    dataIndex: 'experimentTestingtime',
    key: 'a.experiment_testingtime',
    sorter: true,
    width: 130,
    align: 'center',
  },
  {
    title: t('创建用户id'),
    dataIndex: 'experimentUserid',
    key: 'a.experiment_userid',
    sorter: true,
    width: 130,
    align: 'left',
  },
];

const tableProps: BasicTableProps = {
  api: dataserviceExperimentListData,
  beforeFetch: (params) => {
    params['isAll'] = true;
    return params;
  },
  columns: tableColumns,
  formConfig: searchForm,
  rowKey: 'id',
};

export default {
  modalProps,
  tableProps,
  itemCode: 'id',
  itemName: 'id',
  isShowCode: false,
};
