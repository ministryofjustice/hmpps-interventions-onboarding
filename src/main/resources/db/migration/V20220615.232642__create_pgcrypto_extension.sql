-- this extension provides gen_random_uuid(), to replace uuid_generate_v4() from uuid-ossp
-- as suggested by the official postgresql docs
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
