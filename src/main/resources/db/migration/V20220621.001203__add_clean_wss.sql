-- add a variant of Women's Support Services in Greater Manchester, one linking to the "normal"
-- "Family and Significant Others" service category
INSERT INTO crs_contract_types (id, name, code, created_at) VALUES
('d2be0730-39b4-4988-a8d2-e17ea63bd72b', 'Women''s Support Services', 'WSM2', '2022-06-20 23:08:57.149522+00');

INSERT INTO crs_contract_type_service_categories (crs_contract_type_id, service_category_id, created_at) VALUES
('d2be0730-39b4-4988-a8d2-e17ea63bd72b', 'ca374ac3-84eb-4b91-bea7-9005398f426f', '2022-01-06 11:30:57.921677+00'),
('d2be0730-39b4-4988-a8d2-e17ea63bd72b', 'b84f4eb7-4db0-477e-8c59-21027b3262c5', '2022-01-06 11:30:57.921677+00'),
('d2be0730-39b4-4988-a8d2-e17ea63bd72b', '8221a81c-08b2-4262-9c1a-0ab3c82cec8c', '2022-01-06 11:30:57.921677+00'),
('d2be0730-39b4-4988-a8d2-e17ea63bd72b', 'c036826e-f077-49a5-8b33-601dca7ad479', '2022-01-06 11:30:57.921677+00'),
('d2be0730-39b4-4988-a8d2-e17ea63bd72b', '9556a399-3529-4993-8030-41db2090555e', '2022-01-06 11:30:57.921677+00');

-- track extra (non-CRS) desired outcomes; this is to avoid creating _new_ service categories
-- e.g. "Family and Significant Others (GM)" was created solely due to an extra outcome
CREATE TABLE crs_contract_type_extra_desired_outcomes
(
    id                   uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    crs_contract_type_id uuid      not null references crs_contract_types,
    service_category_id  uuid      not null references service_categories,
    description          text,
    created_at           timestamp not null,
    updated_at           timestamp
);
CREATE INDEX ix_extra_outcomes_by_contract_type ON crs_contract_type_extra_desired_outcomes (crs_contract_type_id);

-- load the extra desired outcome under Women's Services in Greater Manchester under the correct (CRS) service category
INSERT INTO crs_contract_type_extra_desired_outcomes (id, crs_contract_type_id, service_category_id, description, created_at)
VALUES ('04c08ffb-7f02-4851-b959-f5a52f29c24b', 'd2be0730-39b4-4988-a8d2-e17ea63bd72b', '9556a399-3529-4993-8030-41db2090555e',
        'Offer the family or significant other guidance, support, and signposting regarding Finance Benefits Debt, accommodation, children’s services and service user’s release from custody to family home.',
        '2022-01-06 11:30:57.921677+00');

-- update existing contracts to 'use' the replacement contract type
UPDATE crs_contracts
SET crs_contract_type_id = 'd2be0730-39b4-4988-a8d2-e17ea63bd72b'
WHERE crs_contract_type_id = 'a93c152c-ed56-48f9-92e8-401ff7aa2fa8';
