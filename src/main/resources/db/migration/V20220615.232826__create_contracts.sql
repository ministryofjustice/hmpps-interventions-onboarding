CREATE TABLE crs_contracts
(
    id                        uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    reference                 varchar(30) not null,
    contact_at_moj_email      text,
    start_date                date,
    end_date                  date,
    created_at                timestamp   not null,
    updated_at                timestamp
);

CREATE UNIQUE INDEX ix_unique_contract_reference ON crs_contracts (reference);
