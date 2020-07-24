/* For allocating extra namespaces - optional */
create schema if not exists ssita;

/* For setting default search_path on database level - optional */
alter database marathon set search_path to ssita;

create TABLE marathon(
  id BIGINT PRIMARY KEY,
  title VARCHAR(255) NOT NULL
);

create TABLE users (
  id BIGINT PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255)
);

create TABLE marathon_user (
    marathon_id BIGINT,
    user_id BIGINT
    );

alter table marathon_user add FOREIGN KEY (marathon_id) REFERENCES marathon(id);
alter table marathon_user add FOREIGN KEY (user_id) REFERENCES users(id);

create TABLE sprint (
  id BIGINT PRIMARY KEY,
  finish DATE,
  start_date DATE,
  title VARCHAR(255),
  marathon_id BIGINT
);

alter table sprint add FOREIGN KEY (marathon_id) REFERENCES marathon(id);

create TABLE task (
  id BIGINT PRIMARY KEY,
  created DATE NOT NULL,
  title VARCHAR(255),
  updated DATE,
  sprint_id BIGINT
);

alter table task add FOREIGN KEY (sprint_id) REFERENCES sprint(id);

create TABLE progress (
  id BIGINT PRIMARY KEY,
  started DATE,
  status VARCHAR(255) NOT NULL,
  updated DATE,
  task_id BIGINT,
  trainee_id BIGINT
);

alter table progress add FOREIGN KEY (task_id) REFERENCES task(id);
alter table progress add FOREIGN KEY (trainee_id) REFERENCES users(id);
