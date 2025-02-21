use jeesite;
CREATE TABLE dataservice_device_data_condition (
                                                   id INT AUTO_INCREMENT PRIMARY KEY,    -- 自增主键
                                                   structure_device_id varchar(100) NOT NULL,  -- 设备名
                                                   device_param_name   varchar(512)    NOT NULL, -- 设备参数名
                                                   condition_type int NOT NULL, -- 状态类型 0 正常 1 可疑 2 异常
                                                   nums int not null default 0,
                                                   remarks varchar(1024)
);

CREATE INDEX idx_deviceid_paramname_type ON dataservice_device_data_condition(structure_device_id, device_param_name, condition_type);