CREATE TABLE crs_contract_types
(
    id         uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    code       varchar(10) not null,
    name       text        not null,
    created_at timestamp   not null,
    updated_at timestamp
);

CREATE TABLE crs_contract_type_service_categories
(
    crs_contract_type_id uuid      not null references crs_contract_types,
    service_category_id  uuid      not null references service_categories,
    created_at           timestamp not null
);

ALTER TABLE crs_contracts
    ADD COLUMN crs_contract_type_id uuid references crs_contract_types;
