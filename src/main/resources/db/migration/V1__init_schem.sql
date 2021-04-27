CREATE TABLE user_data
(
    id               uuid    NOT NULL,
    created_data     date    not null,
    device_id        uuid    not null,
    operation_system integer not null,
    role_data        integer not null,
    updated_data     date    not null,
    user_id          uuid    not null,
    PRIMARY KEY (id)
);


INSERT INTO user_data(
    id,
    created_data,
    device_id,
    operation_system,
    role_data,
    updated_data,
    user_id)
VALUES (
    gen_random_uuid(),
    '2021-04-27',
    '939f5aac-1e67-476d-a184-2b4e167a9e3e',
    '0',
    '0',
    '2021-04-27',
    '9199f5aac-1e67-476d-a184-2b4e167a9e34');

INSERT INTO user_data(
    id,
    created_data,
    device_id,
    operation_system,
    role_data,
    updated_data,
    user_id)
VALUES (
    gen_random_uuid(),
    '2021-04-27',
    '999f5aac-1e67-476d-a184-2b4e167a9e3e',
    '0',
    '1',
    '2021-04-27',
    '999f5aac-1e67-476d-a184-2b4e167a9e34');





