-- 系统操作日志表
create table system_option_log
(
    id             serial8,
    operator_id    varchar(100)                        not null,
    operator_name  varchar(100)                        not null,
    operation_name varchar(100)                        not null,
    data           jsonb,
    log            text,
    created_time   timestamp default current_timestamp not null,
    constraint pk_system_option_log primary key (id, created_time)
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


-- 枚举字典表
create table enum_dict
(
    id     serial8 primary key,
    code   varchar(100) not null,
    value  integer      not null,
    name   varchar(200) not null,
    serial integer      not null,
    constraint uq_enum_dict unique (code, value, serial)
);

comment on table enum_dict is '枚举字典表';
comment on column enum_dict.code is '编码';
comment on column enum_dict.value is '值';
comment on column enum_dict.name is '名称';
comment on column enum_dict.serial is '序号';


-- 品牌信息表
create table brand
(
    id           serial8 primary key,
    name         varchar(100)                        not null,
    created_time timestamp default current_timestamp not null
);

comment on table brand is '品牌信息表';
comment on column brand.name is '品牌名';
comment on column brand.created_time is '创建时间';


-- 会员信息表
create table member
(
    id           serial8 primary key,
    card_no      varchar(100),
    name         varchar(100),
    mobile       varchar(100),
    gender       int2,
    birthday     timestamp,
    exp          bigint    default 0                 not null,
    point        integer   default 0                 not null,
    tags         integer[],
    created_time timestamp default current_timestamp not null
);

comment on table member is '会员信息表';
comment on column member.card_no is '卡号';
comment on column member.name is '姓名';
comment on column member.mobile is '手机号';
comment on column member.gender is '性别';
comment on column member.birthday is '出生日期';
comment on column member.exp is '经验值';
comment on column member.point is '积分';
comment on column member.tags is '标签组';
comment on column member.created_time is '创建时间';


-- 通用标签信息表
create table tag
(
    id           serial8 primary key,
    type         int2                                not null,
    name         varchar(100)                        not null,
    created_time timestamp default current_timestamp not null
);

comment on table tag is '通用标签信息表';
comment on column tag.type is '类型：TAG_TYPE';
comment on column tag.name is '名称';
comment on column tag.created_time is '创建时间';


-- 通用积分详情表
create table point_detail
(
    id           serial8 primary key,
    target_id    bigint                              not null,
    period       tsrange                             not null,
    type         int2                                not null,
    value        integer                             not null,
    created_time timestamp default current_timestamp not null
);

comment on table point_detail is '通用积分详情表';
comment on column point_detail.target_id is '目标ID';
comment on column point_detail.period is '时间范围';
comment on column point_detail.type is '类型：POINT_DETAIL_TYPE';
comment on column point_detail.value is '值';
comment on column point_detail.created_time is '创建时间';


-- 通用积分流水表
create table point_statement
(
    opt_type integer not null,
    opt      jsonb
) inherits (point_detail);

comment on table point_statement is '通用积分流水表';
comment on column point_statement.target_id is '目标ID';
comment on column point_statement.period is '时间范围';
comment on column point_statement.type is '类型：POINT_STATEMENT_TYPE';
comment on column point_statement.value is '值';
comment on column point_statement.opt_type is '操作类型';
comment on column point_statement.opt is '操作';
comment on column point_statement.created_time is '创建时间';


-- 通用经验区间表
create table exp_range
(
    id           serial8 primary key,
    type         integer                             not null,
    range        int8range                           not null,
    level        integer,
    name         varchar(200),
    created_time timestamp default current_timestamp not null
);

comment on table exp_range is '通用经验区间表';
comment on column exp_range.type is '类型：EXP_RANGE_TYPE';
comment on column exp_range.range is '数值范围';
comment on column exp_range.level is '等级';
comment on column exp_range.name is '名称';
comment on column exp_range.created_time is '创建时间';


-- 商品信息表
create table product
(
    id           serial8 primary key,
    name         varchar(100)                        not null,
    created_time timestamp default current_timestamp not null
);

comment on table product is '商品信息表';
comment on column product.name is '名称';
comment on column product.created_time is '创建时间';


-- 商品价格表
create table product_price
(
    id           serial8 primary key,
    type         int2                                not null,
    product_id   bigint                              not null,
    price        decimal(16, 2)                      not null,
    period       tsrange                             not null,
    created_time timestamp default current_timestamp not null
);

comment on table product_price is '商品价格表';
comment on column product_price.type is '类型：PRODUCT_PRICE_TYPE';
comment on column product_price.product_id is '商品ID';
comment on column product_price.price is '价格';
comment on column product_price.period is '时间范围';
comment on column product_price.created_time is '创建时间';


-- 仓库信息表
create table warehouse
(
    id           serial8 primary key,
    name         varchar(200)                        not null,
    type         int2                                not null,
    created_time timestamp default current_timestamp not null
);

comment on table warehouse is '仓库信息表';
comment on column warehouse.name is '名称';
comment on column warehouse.type is '类型：WAREHOUSE_TYPE';
comment on column warehouse.created_time is '创建时间';


-- 门店信息表
create table store
(
    brand_id bigint,
    exp      bigint default 0 not null
) inherits (warehouse);

comment on table store is '门店信息表';
comment on column store.brand_id is '品牌ID';
comment on column store.exp is '经验值';


-- 库存信息表
create table stock
(
    id           serial8 primary key,
    warehouse_id bigint                              not null,
    product_id   bigint                              not null,
    amount       integer                             not null,
    created_time timestamp default current_timestamp not null
);

comment on table stock is '库存信息表';
comment on column stock.warehouse_id is '仓库ID';
comment on column stock.product_id is '商品ID';
comment on column stock.amount is '数量';
comment on column stock.created_time is '创建时间';


-- 订单信息表
create table "order"
(
    id           serial8 primary key,
    pid          bigint,
    channel      int2                                not null,
    store_id     bigint                              not null,
    member_id    bigint,
    amount       decimal(16, 2)                      not null,
    created_time timestamp default current_timestamp not null
);

comment on table "order" is '订单信息表';
comment on column "order".pid is '父单号';
comment on column "order".channel is '渠道';
comment on column "order".store_id is '门店ID';
comment on column "order".member_id is '会员ID';
comment on column "order".amount is '金额';
comment on column "order".created_time is '创建时间';


-- 订单明细表
create table order_item
(
    id             serial8 primary key,
    channel        int2                                not null,
    serial         integer                             not null,
    product_id     bigint                              not null,
    sale_type      int2      default 0                 not null,
    purchase_price decimal(16, 2)                      not null,
    given          boolean,
    created_time   timestamp default current_timestamp not null
);

comment on table order_item is '订单明细表';
comment on column order_item.channel is '渠道';
comment on column order_item.serial is '序号（从1开始，不重复）';
comment on column order_item.product_id is '商品ID';
comment on column order_item.sale_type is '销售类型';
comment on column order_item.purchase_price is '购买价格';
comment on column order_item.given is '是否赠送';
comment on column order_item.created_time is '创建时间';


-- 历史订单表
create table history_order
(
    id            bigint         not null,
    pid           bigint,
    channel       int2           not null,
    store_id      bigint         not null,
    member_id     bigint,
    price         decimal(16, 2) not null,
    point         integer,
    amount        decimal(16, 2),
    change_amount decimal(16, 2),
    created_time  timestamp      not null
) partition by range (created_time);

create table history_order_2019 partition of history_order for values from ('2019-01-01') to ('2020-01-01');
create table history_order_2020 partition of history_order for values from ('2020-01-01') to ('2021-01-01');
create table history_order_2021 partition of history_order for values from ('2021-01-01') to ('2022-01-01');

comment on table history_order is '历史订单表';
comment on column history_order.pid is '父单号';
comment on column history_order.channel is '渠道';
comment on column history_order.store_id is '门店ID';
comment on column history_order.member_id is '会员ID';
comment on column history_order.amount is '金额';
comment on column history_order.created_time is '创建时间';


-- 历史订单明细表
create table history_order_item
(
    id             bigint         not null,
    channel        int2           not null,
    serial         integer        not null,
    product_id     bigint         not null,
    sale_type      int2 default 0 not null,
    purchase_price decimal(16, 2) not null,
    given          boolean,
    created_time   timestamp      not null
) partition by range (created_time);

create table history_order_item_2019 partition of history_order_item for values from ('2019-01-01') to ('2020-01-01');
create table history_order_item_2020 partition of history_order_item for values from ('2020-01-01') to ('2021-01-01');
create table history_order_item_2021 partition of history_order_item for values from ('2021-01-01') to ('2022-01-01');

comment on table history_order_item is '历史订单明细表';
comment on column history_order_item.channel is '渠道';
comment on column history_order_item.serial is '序号（从1开始，不重复）';
comment on column history_order_item.product_id is '商品ID';
comment on column history_order_item.sale_type is '销售类型';
comment on column history_order_item.purchase_price is '购买价格';
comment on column history_order_item.given is '是否赠送';
comment on column history_order_item.created_time is '创建时间';


-- 券模板表
create table coupon_template
(
    id           serial8 primary key,
    name         varchar(200)                        not null,
    code         char(4) unique                      not null,
    period       tsrange                             not null,
    created_time timestamp default current_timestamp not null
);

comment on table coupon_template is '券模板表';
comment on column coupon_template.name is '券名称';
comment on column coupon_template.code is '券码前缀';
comment on column coupon_template.period is '券有效期';
comment on column coupon_template.created_time is '创建时间';


-- 券方案表
create table coupon_plan
(
    id                 serial8 primary key,
    coupon_template_id bigint                              not null,
    total_num          integer                             not null,
    get_num            integer   default 1                 not null,
    period             tsrange                             not null,
    created_time       timestamp default current_timestamp not null
);

comment on table coupon_plan is '券方案表';
comment on column coupon_plan.coupon_template_id is '券模板ID';
comment on column coupon_plan.total_num is '券总数量';
comment on column coupon_plan.get_num is '每人可领取数量';
comment on column coupon_plan.period is '券有效期';
comment on column coupon_plan.created_time is '创建时间';


-- 券表
create table coupon
(
    id                 serial8 primary key,
    coupon_template_id bigint                              not null,
    number             varchar(16)                         not null,
    created_time       timestamp default current_timestamp not null
);

comment on table coupon is '券表';
comment on column coupon.coupon_template_id is '券模板ID';
comment on column coupon.number is '券号';
comment on column coupon.created_time is '创建时间';


-- 历史券表
create table history_coupon
(
    id                 serial8 primary key,
    coupon_template_id bigint                              not null,
    number             varchar(16)                         not null,
    created_time       timestamp default current_timestamp not null
);

comment on table history_coupon is '历史券表';
comment on column history_coupon.coupon_template_id is '券模板ID';
comment on column history_coupon.number is '券号';
comment on column history_coupon.created_time is '创建时间';


-- 会员卡券表
create table member_coupon
(
    id           serial8 primary key,
    member_id    bigint                              not null,
    coupon_no    varchar(16)[]                       not null,
    created_time timestamp default current_timestamp not null
);

comment on table history_coupon is '历史券表';
comment on column history_coupon.coupon_template_id is '券模板ID';
comment on column history_coupon.number is '券号';
comment on column history_coupon.created_time is '创建时间';


-- 支付方式表
create table payment_method
(
    id   serial8 primary key,
    name varchar(20) not null
);