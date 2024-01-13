CREATE TABLE gsync_tenant
(
    tenant_id  STRING(36) NOT NULL,
    name       STRING( MAX) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (tenant_id);

CREATE TABLE gsync_operator
(
    operator_id STRING(36) NOT NULL,
    email       STRING(255) NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL,
) PRIMARY KEY (operator_id);

CREATE TABLE gsync_r_tenant_operator
(
    operator_id STRING(36) NOT NULL,
    tenant_id   STRING(36) NOT NULL,
    is_admin    BOOL      NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL,
) PRIMARY KEY (operator_id, tenant_id),
INTERLEAVE IN PARENT gsync_operator ON
DELETE
CASCADE;

CREATE TABLE gsync_master_version
(
    version    STRING(36) NOT NULL,
    is_enabled BOOL      NOT NULL,
    comment    STRING( MAX) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (version);
CREATE INDEX gsync_master_version__is_enabled ON gsync_master_version (is_enabled);

CREATE TABLE gsync_required_client_version
(
    master_version STRING(36) NOT NULL,
    client_version STRING( MAX) NOT NULL,
    platform       INT64     NOT NULL,
    created_at     TIMESTAMP NOT NULL,
    updated_at     TIMESTAMP NOT NULL,
) PRIMARY KEY (master_version, client_version, platform);

CREATE TABLE gsync_player
(
    player_id  STRING(36) NOT NULL,
    friend_id  STRING(36) NOT NULL,
    is_banned  BOOL      NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (player_id);
CREATE UNIQUE INDEX gsync_player__friend_id ON gsync_player (friend_id);

CREATE TABLE gsync_player_profile
(
    player_id  STRING(36) NOT NULL,
    nickname   STRING( MAX) NOT NULL,
    icon_id    STRING(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (player_id),
INTERLEAVE IN PARENT gsync_player ON
DELETE
CASCADE;

CREATE TABLE gsync_player_login
(
    player_id         STRING(36) NOT NULL,
    total_login_days  INT64     NOT NULL,
    last_logged_in_at TIMESTAMP NOT NULL,
    created_at        TIMESTAMP NOT NULL,
    updated_at        TIMESTAMP NOT NULL,
) PRIMARY KEY (player_id),
INTERLEAVE IN PARENT gsync_player ON
DELETE
CASCADE;

CREATE TABLE gsync_player_storage_entry
(
    player_id  STRING(36) NOT NULL,
    tenant_id  STRING(36) NOT NULL,
    key        STRING( MAX) NOT NULL,
    value      BYTES( MAX) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (player_id, tenant_id, key),
INTERLEAVE IN PARENT gsync_player ON
DELETE
CASCADE;

CREATE TABLE gsync_player_storage_revision
(
    player_id                  STRING(36) NOT NULL,
    tenant_id                  STRING(36) NOT NULL,
    player_storage_revision_id STRING(36) NOT NULL,
    idempotency_key            STRING(36) NOT NULL,
    created_at                 TIMESTAMP NOT NULL,
    updated_at                 TIMESTAMP NOT NULL,
) PRIMARY KEY (player_id, tenant_id, player_storage_revision_id),
INTERLEAVE IN PARENT gsync_player ON
DELETE
CASCADE;
CREATE INDEX gsync_player_storage_revision__player_id__tenant_id__created_at ON gsync_player_storage_revision (player_id, tenant_id, created_at DESC);
CREATE UNIQUE INDEX gsync_player_storage_revision__idempotency_key ON gsync_player_storage_revision (idempotency_key);

CREATE TABLE gsync_echo
(
    echo_id    STRING(36) NOT NULL,
    message    STRING( MAX) NOT NULL,
    timestamp  TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (echo_id),
    ROW DELETION POLICY (OLDER_THAN(timestamp, INTERVAL 1 DAY));
