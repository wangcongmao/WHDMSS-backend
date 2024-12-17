<!--
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 * No deletion without permission, or be held responsible to law.
 * @author wangcm
-->
<template>
  <BasicDrawer
    v-bind="$attrs"
    :showFooter="true"
    :okAuth="'experiment:dataserviceExperiment:edit'"
    @register="registerDrawer"
    @ok="handleSubmit"
    width="60%"
  >
    <template #title>
      <Icon :icon="getTitle.icon" class="m-1 pr-1" />
      <span> {{ getTitle.value }} </span>
    </template>
    <BasicForm @register="registerForm" />
  </BasicDrawer>
</template>
<script lang="ts" setup name="ViewsExperimentDataserviceExperimentForm">
  import { ref, unref, computed } from 'vue';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { router } from '/@/router';
  import { Icon } from '/@/components/Icon';
  import { BasicForm, FormSchema, useForm } from '/@/components/Form';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import { DataserviceExperiment, dataserviceExperimentSave, dataserviceExperimentForm } from '/@/api/experiment/dataserviceExperiment';

  const emit = defineEmits(['success', 'register']);

  const { t } = useI18n('experiment.dataserviceExperiment');
  const { showMessage } = useMessage();
  const { meta } = unref(router.currentRoute);
  const record = ref<DataserviceExperiment>({} as DataserviceExperiment);

  const getTitle = computed(() => ({
    icon: meta.icon || 'i-ant-design:book-outlined',
    value: record.value.isNewRecord ? t('新增试验信息表') : t('编辑试验信息表'),
  }));

  const inputFormSchemas: FormSchema[] = [
    {
      label: t('试验名称'),
      field: 'experimentName',
      component: 'Input',
      componentProps: {
        maxlength: 512,
      },
      required: true,
    },
    {
      label: t('试验场ID'),
      field: 'experimentTestinggroundid',
      component: 'Input',
      componentProps: {
        maxlength: 18,
      },
      rules: [{ pattern: /^\d+$/, message: t('请输入一个正整数') }],
    },
    {
      label: t('试验场名称'),
      field: 'experimentTestinggroundname',
      component: 'Input',
      componentProps: {
        maxlength: 512,
      },
      required: true,
    },
    {
      label: t('试验单位'),
      field: 'experimentWorkplace',
      component: 'Input',
      componentProps: {
        maxlength: 512,
      },
    },
    {
      label: t('试验人员'),
      field: 'experimentStaff',
      component: 'Input',
      componentProps: {
        maxlength: 512,
      },
    },
    {
      label: t('测试时间'),
      field: 'experimentTestingtime',
      component: 'DatePicker',
      componentProps: {
        format: 'YYYY-MM-DD HH:mm',
        showTime: { format: 'HH:mm' },
      },
      required: true,
    },
    {
      label: t('创建用户id'),
      field: 'experimentUserid',
      component: 'Input',
      componentProps: {
        maxlength: 100,
      },
      required: true,
    },
  ];

  const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
    labelWidth: 120,
    schemas: inputFormSchemas,
    baseColProps: { md: 24, lg: 24 },
  });

  const [registerDrawer, { setDrawerProps, closeDrawer }] = useDrawerInner(async (data) => {
    setDrawerProps({ loading: true });
    await resetFields();
    const res = await dataserviceExperimentForm(data);
    record.value = (res.dataserviceExperiment || {}) as DataserviceExperiment;
    record.value.__t = new Date().getTime();
    setFieldsValue(record.value);
    setDrawerProps({ loading: false });
  });

  async function handleSubmit() {
    try {
      const data = await validate();
      setDrawerProps({ confirmLoading: true });
      const params: any = {
        isNewRecord: record.value.isNewRecord,
        id: record.value.id,
      };
      // console.log('submit', params, data, record);
      const res = await dataserviceExperimentSave(params, data);
      showMessage(res.message);
      setTimeout(closeDrawer);
      emit('success', data);
    } catch (error: any) {
      if (error && error.errorFields) {
        showMessage(error.message || t('common.validateError'));
      }
      console.log('error', error);
    } finally {
      setDrawerProps({ confirmLoading: false });
    }
  }
</script>
