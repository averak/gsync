CREATE TABLE gsync_game
(
    game_id    STRING(36) NOT NULL,
    name       STRING( MAX) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (game_id);

CREATE TABLE gsync_operator
(
    operator_id STRING(36) NOT NULL,
    email       STRING(255) NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL,
) PRIMARY KEY (operator_id);

CREATE TABLE gsync_r_game_operator
(
    operator_id STRING(36) NOT NULL,
    game_id     STRING(36) NOT NULL,
    is_admin    BOOL      NOT NULL,
    created_at  TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP NOT NULL,
) PRIMARY KEY (operator_id, game_id),
INTERLEAVE IN PARENT gsync_operator ON
DELETE
CASCADE;

CREATE TABLE gsync_master_version
(
    version    STRING(36) NOT NULL,
    is_valid   BOOL      NOT NULL,
    comment    STRING( MAX) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (version);
CREATE INDEX idx__gsync_master_version__is_valid ON gsync_master_version (is_valid);

CREATE TABLE gsync_required_client_version
(
    master_version STRING(36) NOT NULL,
    client_version STRING( MAX) NOT NULL,
    os             INT64     NOT NULL,
    created_at     TIMESTAMP NOT NULL,
    updated_at     TIMESTAMP NOT NULL,
) PRIMARY KEY (master_version, client_version, os);

CREATE TABLE gsync_player
(
    player_id  STRING(36) NOT NULL,
    is_banned  BOOL      NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (player_id);

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

CREATE TABLE gsync_player_friend
(
    player_id        STRING(36) NOT NULL,
    friend_player_id STRING(36) NOT NULL,
    become_friend_at TIMESTAMP NOT NULL,
    created_at       TIMESTAMP NOT NULL,
    updated_at       TIMESTAMP NOT NULL,
    CONSTRAINT fk__gsync_player_friend__friend_player_id FOREIGN KEY (friend_player_id) REFERENCES gsync_player (player_id),
) PRIMARY KEY (player_id, friend_player_id),
INTERLEAVE IN PARENT gsync_player ON
DELETE
CASCADE;

CREATE TABLE gsync_player_friend_request
(
    player_id          STRING(36) NOT NULL,
    receiver_player_id STRING(36) NOT NULL,
    sent_at            TIMESTAMP NOT NULL,
    created_at         TIMESTAMP NOT NULL,
    updated_at         TIMESTAMP NOT NULL,
    CONSTRAINT fk__gsync_player_friend_request__receiver_player_id FOREIGN KEY (receiver_player_id) REFERENCES gsync_player (player_id),
) PRIMARY KEY (player_id, receiver_player_id),
INTERLEAVE IN PARENT gsync_player ON
DELETE
CASCADE;

CREATE TABLE gsync_friend_setting_master
(
    master_version           STRING(36) NOT NULL,
    max_friend_count         INT64     NOT NULL,
    max_friend_request_count INT64     NOT NULL,
    created_at               TIMESTAMP NOT NULL,
    updated_at               TIMESTAMP NOT NULL,
) PRIMARY KEY (master_version);

CREATE TABLE gsync_player_storage_entry
(
    player_id  STRING(36) NOT NULL,
    game_id    STRING(36) NOT NULL,
    key        STRING( MAX) NOT NULL,
    value      BYTES( MAX) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (player_id, game_id, key),
INTERLEAVE IN PARENT gsync_player ON
DELETE
CASCADE;

CREATE TABLE gsync_player_storage_revision
(
    player_id                  STRING(36) NOT NULL,
    game_id                    STRING(36) NOT NULL,
    player_storage_revision_id STRING(36) NOT NULL,
    idempotency_key            STRING(36) NOT NULL,
    created_at                 TIMESTAMP NOT NULL,
    updated_at                 TIMESTAMP NOT NULL,
) PRIMARY KEY (player_id, game_id, player_storage_revision_id),
INTERLEAVE IN PARENT gsync_player ON
DELETE
CASCADE;
CREATE INDEX idx__gsync_player_storage_revision__player_id__game_id__created_at ON gsync_player_storage_revision (player_id, game_id, created_at DESC);
CREATE UNIQUE INDEX uq__gsync_player_storage_revision__idempotency_key ON gsync_player_storage_revision (idempotency_key);

CREATE TABLE gsync_echo
(
    echo_id    STRING(36) NOT NULL,
    message    STRING( MAX) NOT NULL,
    timestamp  TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (echo_id),
    ROW DELETION POLICY (OLDER_THAN(timestamp, INTERVAL 1 DAY));
