 create table transaction(
t_id serial,
title varchar not null,
amount numeric(4,2) not null,
t_type varchar not null,
t_date Date not null,
account_id int,
primary key(account_id,t_id),
        CONSTRAINT fk_accid
        FOREIGN KEY(account_id)
        REFERENCES Accounts(account_id)
        on delete cascade
        on update cascade
)