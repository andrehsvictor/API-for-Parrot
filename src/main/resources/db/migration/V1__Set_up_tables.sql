create table if not exists users (
    id serial primary key,
    name varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null
);

create table if not exists posts (
    id serial primary key,
    title varchar(255) not null,
    content text not null,
    author_id int not null,
    created_at timestamp default current_timestamp,
    foreign key (author_id) references users(id)
);

create table if not exists comments (
    id serial primary key,
    content text not null,
    author_id int not null,
    post_id int not null,
    created_at timestamp default current_timestamp,
    foreign key (author_id) references users(id),
    foreign key (post_id) references posts(id)
);