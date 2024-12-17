<!--
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 * No deletion without permission, or be held responsible to law.
 * @author wangcm
-->
<template>
  <div>
    <BasicTable @register="registerTable">
      <template #tableTitle>
        <Icon :icon="getTitle.icon" class="m-1 pr-1" />
        <span> {{ getTitle.value }} </span>
      </template>
      <template #toolbar>
        <a-button type="primary" @click="handleForm({})" v-auth="'experiment:dataserviceExperiment:edit'">
          <Icon icon="i-fluent:add-12-filled" /> {{ t('新增') }}
        </a-button>
      </template>
      <template #firstColumn="{ record }">
        <a @click="handleForm({ id: record.id })">
          {{ record.experimentName }}
        </a>
      </template>
    </BasicTable>
    <InputForm @register="registerDrawer" @success="handleSuccess" />
  </div>
</template>
<script lang="ts" setup name="ViewsExperimentDataserviceExperimentList">
  import { unref } from 'vue';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { router } from '/@/router';
  import { Icon } from '/@/components/Icon';
  import { BasicTable, BasicColumn, useTable } from '/@/components/Table';
  import { dataserviceExperimentDelete, dataserviceExperimentListData } from '/@/api/experiment/dataserviceExperiment';
  import { useDrawer } from '/@/components/Drawer';
  import { FormProps } from '/@/components/Form';
  import InputForm from './form.vue';

  const { t } = useI18n('experiment.dataserviceExperiment');
  const { showMessage } = useMessage();
  const { meta } = unref(router.currentRoute);

  const getTitle = {
    icon: meta.icon || 'i-ant-design:book-outlined',
    value: meta.title || t('试验信息表管理'),
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

  const actionColumn: BasicColumn = {
    width: 160,
    actions: (record: Recordable) => [
      {
        icon: 'i-clarity:note-edit-line',
        title: t('编辑试验信息表'),
        onClick: handleForm.bind(this, { id: record.id }),
        auth: 'experiment:dataserviceExperiment:edit',
      },
      {
        icon: 'i-ant-design:delete-outlined',
        color: 'error',
        title: t('删除试验信息表'),
        popConfirm: {
          title: t('是否确认删除试验信息表'),
          confirm: handleDelete.bind(this, record),
        },
        auth: 'experiment:dataserviceExperiment:edit',
      },
    ],
  };

  const [registerTable, { reload }] = useTable({
    api: dataserviceExperimentListData,
    beforeFetch: (params) => {
      return params;
    },
    columns: tableColumns,
    actionColumn: actionColumn,
    formConfig: searchForm,
    showTableSetting: true,
    useSearchForm: true,
    canResize: true,
  });

  const [registerDrawer, { openDrawer }] = useDrawer();

  function handleForm(record: Recordable) {
    openDrawer(true, record);
  }

  async function handleDelete(record: Recordable) {
    const params = { id: record.id };
    const res = await dataserviceExperimentDelete(params);
    showMessage(res.message);
    handleSuccess(record);
  }

  function handleSuccess(record: Recordable) {
    reload({ record });
  }
</script>
