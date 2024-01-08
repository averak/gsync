CREATE TABLE gsync_echo
(
    echo_id   STRING(36) NOT NULL,
    message   STRING(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
) PRIMARY KEY (echo_id);
