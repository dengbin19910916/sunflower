-- 系统操作日志表
create table system_option_log
(
    id             serial8 primary key,
    operator_id    varchar(100) not null,
    operator_name  varchar(100) not null,
    operation_name varchar(100) not null,
    data           jsonb,
    log            text,
    created_time   timestamp    not null
) partition by range (created_time);

create table system_option_log_2019 partition of system_option_log for values from ('2019-01-01') to ('2020-01-01');
create table system_option_log_2020 partition of system_option_log for values from ('2020-01-01') to ('2021-01-01');
create table system_option_log_2021 partition of system_option_log for values from ('2021-01-01') to ('2022-01-01');

comment on table system_option_log is '系统操作日志表';
comment on column system_option_log.operator_id is '操作人ID';
comment on column system_option_log.operator_name is '操作人名称';
comment on column system_option_log.operation_name is '操作名称';
comment on column system_option_log.data is '数据';
comment on column system_option_log.log is '日志';
comment on column system_option_log.created_time is '创建时间';


-- 品牌信息表
create table brand
(
    id           serial8 primary key,
    name         varchar(100) not null,
    created_time timestamp    not null
);


-- 会员信息表
create table member
(
    id           serial8 primary key,
    card_no      varchar(100),
    name         varchar(100),
    mobile       varchar(100),
    gender       int2,
    birthday     timestamp,
    exp          bigint  default 0 not null,
    point        integer default 0 not null,
    tags         integer[],
    created_time timestamp         not null
);


-- 标签信息表
create table tag
(
    id           serial8 primary key,
    type         int2         not null,
    name         varchar(100) not null,
    created_time timestamp    not null
);


-- 会员积分详情表
create table point_detail
(
    id           serial8 primary key,
    member_id    bigint    not null,
    period       tsrange   not null,
    value        integer   not null,
    created_time timestamp not null
);


-- 会员积分流水表
create table member_point_statement
(
    id           serial8 primary key,
    member_id    bigint    not null,
    period       tsrange   not null,
    type         int2      not null,
    value        integer   not null,
    reason_type  integer   not null,
    reason       jsonb,
    created_time timestamp not null
);


-- 经验区间表
create table exp_range
(
    id           serial8 primary key,
    type         integer   not null,
    period       int8range not null,
    level        integer,
    name         varchar(200),
    created_time timestamp not null
);


-- 商品信息表
create table product
(
    id           serial8 primary key,
    name         varchar(100) not null,
    created_time timestamp    not null
);


-- 商品吊牌价表
create table product_tag_price
(
    id           serial8 primary key,
    product_id   bigint         not null,
    price        decimal(16, 2) not null,
    period       tsrange        not null,
    created_time timestamp      not null
);


-- 商品售价表
create table product_sale_price
(
    id           serial8 primary key,
    product_id   bigint         not null,
    price        decimal(16, 2) not null,
    period       tsrange        not null,
    created_time timestamp      not null
);


-- 仓库信息表
create table warehouse
(
    id           serial8 primary key,
    name         varchar(200) not null,
    location     geometry     not null,
    created_time timestamp    not null
);


-- 仓库库位信息表
create table warehouse_location
(
    id           serial8 primary key,
    name         varchar(200) not null,
    created_time timestamp    not null
);


-- 门店信息表
create table store
(
    brand_id bigint,
    exp      bigint default 0 not null
) inherits (warehouse);


-- 订单信息表
create table "order"
(
    id           serial8 primary key,
    pid          bigint,
    channel      int2           not null,
    store_id     bigint         not null,
    member_id    bigint,
    amount       decimal(16, 2) not null,
    created_time timestamp      not null
);


-- 订单明细表
create table order_item
(
    id             serial8 primary key,
    channel        int2           not null,
    product_id     bigint         not null,
    sale_type      int2 default 0 not null,
    purchase_price decimal(16, 2) not null,
    given          boolean,
    created_time   timestamp      not null
);


-- 历史订单表
create table history_order
(
    id           bigint,
    pid          bigint,
    channel      int2           not null,
    store_id     bigint         not null,
    member_id    bigint,
    amount       decimal(16, 2) not null,
    created_time timestamp      not null
) partition by range (channel);

create table history_order_tm partition of history_order for values in (1) partition by range (created_time);
create table history_order_default partition of history_order default partition by range (created_time);

create table history_order_tm_2019 partition of history_order_tm for values from ('2019-01-01') to ('2020-01-01');
create table history_order_tm_2020 partition of history_order_tm for values from ('2020-01-01') to ('2021-01-01');
create table history_order_tm_2021 partition of history_order_tm for values from ('2021-01-01') to ('2022-01-01');
create table history_order_default_2019 partition of history_order_default for values from ('2019-01-01') to ('2020-01-01');
create table history_order_default_2020 partition of history_order_default for values from ('2020-01-01') to ('2021-01-01');
create table history_order_default_2021 partition of history_order_default for values from ('2021-01-01') to ('2022-01-01');


-- 历史订单明细表
create table history_order_item
(
    id             serial8 primary key,
    channel        int2           not null,
    product_id     bigint         not null,
    sale_type      int2 default 0 not null,
    purchase_price decimal(16, 2) not null,
    given          boolean,
    created_time   timestamp      not null
) partition by range (channel);

create table history_order_item_tm partition of history_order_item for values in (1) partition by range (created_time);
create table history_order_item_default partition of history_order_item default partition by range (created_time);

create table history_order_item_tm_2019 partition of history_order_item_tm for values from ('2019-01-01') to ('2020-01-01');
create table history_order_item_tm_2020 partition of history_order_item_tm for values from ('2020-01-01') to ('2021-01-01');
create table history_order_item_tm_2021 partition of history_order_item_tm for values from ('2021-01-01') to ('2022-01-01');
create table history_order_item_default_2019 partition of history_order_item_default for values from ('2019-01-01') to ('2020-01-01');
create table history_order_item_default_2020 partition of history_order_item_default for values from ('2020-01-01') to ('2021-01-01');
create table history_order_item_default_2021 partition of history_order_item_default for values from ('2021-01-01') to ('2022-01-01');


-- 库存信息表
create table stock
(
    id           serial8 primary key,
    warehouse_id bigint    not null,
    product_id   bigint    not null,
    amount       integer   not null,
    created_time timestamp not null
);