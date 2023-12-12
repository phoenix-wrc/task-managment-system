ALTER TABLE task_priority
    RENAME COLUMN priority_name TO priority;

ALTER TABLE user_task_owner
    RENAME COLUMN user_id TO author_id;

ALTER TABLE user_task_executor
    RENAME COLUMN user_id TO executor_id;
