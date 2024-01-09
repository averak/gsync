CREATE TABLE gsync_echo
(
    echo_id    STRING(36) NOT NULL,
    message    STRING( MAX) NOT NULL,
    timestamp  TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (echo_id);
ALTER TABLE gsync_echo
    ADD ROW DELETION POLICY (OLDER_THAN(timestamp, INTERVAL 1 DAY));

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
