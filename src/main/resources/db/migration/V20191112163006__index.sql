select 1;
-- create index idx_system_option_log_operator_id on system_option_log using btree (operator_id);
-- create index idx_system_option_log_operator_name on system_option_log using gin (operator_name gin_trgm_ops);
-- create index idx_system_option_log_operation_name on system_option_log using gin (operation_name gin_trgm_ops);
-- create index idx_system_option_log_data on system_option_log using gin (data);
-- create index idx_system_option_log_created_time on system_option_log using btree (created_time);
--
-- create index idx_brand_name on brand using btree (name);
-- create index idx_brand_created_time on brand using btree (created_time);
--
-- create index idx_member_card_no on member using btree (card_no);
-- create index idx_member_name on member using gin (name gin_trgm_ops);
-- create index idx_member_mobile on member using btree (mobile);
-- create index idx_member_birthday on member using btree (birthday);
-- create index idx_member_created_time on member using btree (created_time);
--
-- create index idx_point_detail_period on point_detail using spgist (period);
--
-- create index idx_stock_warehouse_id on stock using btree (warehouse_id);
-- create index idx_stock_product_id on stock using btree (product_id);
--
-- create index idx_order_pid on "order" using btree (pid);
-- create index idx_order_store_id on "order" using btree (store_id);
-- create index idx_order_member_id on "order" using btree (member_id);
--
-- create index idx_order_item_product_id on order_item using btree (product_id);
--
-- create index idx_history_order_id on history_order using btree (id);
-- create index idx_history_order_pid on history_order using btree (pid);
-- create index idx_history_order_store_id on history_order using btree (store_id);
-- create index idx_history_order_member_id on history_order using btree (member_id);
--
-- create index idx_history_order_item_product_id on history_order_item using btree (product_id);
--
-- create index idx_coupon_template_name on coupon_template using gin (name gin_trgm_ops);