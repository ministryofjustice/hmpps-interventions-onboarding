CREATE TABLE providers
(
    id            uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    auth_group_id varchar(30) not null,
    name          text        not null,
    created_at    timestamp   not null,
    updated_at    timestamp
);

CREATE UNIQUE INDEX ix_unique_provider_group ON providers (auth_group_id);
