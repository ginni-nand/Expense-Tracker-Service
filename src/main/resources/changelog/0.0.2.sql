Create table Accounts(
account_id serial primary key ,
balance numeric(4,2) not null,
user_id int,
        CONSTRAINT fk_userid
        FOREIGN KEY(user_id)
        REFERENCES users(id)
        on delete cascade
        on update cascade
)