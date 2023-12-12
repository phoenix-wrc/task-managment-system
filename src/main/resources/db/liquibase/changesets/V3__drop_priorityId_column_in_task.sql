ALTER TABLE task
    DROP COLUMN priority_id CASCADE;
DROP TABLE task_priority;
CREATE TABLE IF NOT EXISTS task_priority
(
    task_id       bigint       not null,
    priority_name varchar(255) not null,
    primary key (task_id, priority_name),
    constraint fk_task_task_priority foreign key (task_id) references task(id) on delete cascade on update no action
);
INSERT INTO "task-management-system".task_priority(task_id, priority_name)
VALUES (1, 'HIGH');
VALUES (2, 'MEDIUM');
VALUES (3, 'HIGH');
VALUES (4, 'LOW');