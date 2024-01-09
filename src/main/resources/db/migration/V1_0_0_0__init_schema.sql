CREATE TABLE gsync_echo
(
    echo_id   STRING(36) NOT NULL,
    message   STRING(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
) PRIMARY KEY (echo_id);
ALTER TABLE gsync_echo ADD ROW DELETION POLICY (OLDER_THAN(timestamp, INTERVAL 1 DAY));
