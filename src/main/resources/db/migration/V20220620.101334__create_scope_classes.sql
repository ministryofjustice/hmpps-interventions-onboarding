CREATE TABLE service_categories
(
    id         uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    name       text      not null,
    created_at timestamp not null,
    updated_at timestamp
);

CREATE TABLE desired_outcomes
(
    id                  uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    service_category_id uuid      not null references service_categories,
    description         text      not null,
    created_at          timestamp not null,
    updated_at          timestamp
);

CREATE TYPE complexity_level AS ENUM ('LOW', 'MEDIUM', 'HIGH');
CREATE TABLE complexity_levels
(
    id                  uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    service_category_id uuid             not null references service_categories,
    complexity          complexity_level not null,
    description         text             not null,
    created_at          timestamp        not null,
    updated_at          timestamp
);
