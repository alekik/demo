create sequence clicker_hero_sequence start 1 increment 1;

create table usr
(
    id              int8 not null,
    username        varchar(255) not null,
    password        varchar(255) not null,
    current_enemy_id  int8 not null,
    role            varchar(255) not null,
    rating          int8 not null,
    score  	    int8 not null,
    gametime 	    int8 not null,
    user_level 	    int8,
    primary key (id)
);

create table Enemy
(
    id          int8 not null,
    name	varchar(255) not null,
    path_to_image	varchar(255) not null,
    Hp		int8 not null,
    is_boss	int8 not null,
    primary key (id)
);

create table Hero
(
    id          int8 not null,
    damage      int8 not null,
    hero_level       int8 not null,
    name	varchar(4096) not null,
    cost	int8 not null,
    hero_order	int8 not null,
    primary key (id)
);
create table Item
(
    id          	int8 not null,
    name		varchar(255) not null,
    damage      	int8 not null,
    rarity_level 	int8 not null,
    cost		int8 not null,
    is_bought_by_donat	int8 not null,
    primary key (id)
);
create table usr_hero
(
    hero_id 	int8 not null,
    user_id   	int8 not null
);
create table usr_item
(
    item_id 	int8 not null,
    user_id   	int8 not null
);

ALTER TABLE usr ADD CONSTRAINT fk_enemy_id foreign key (current_enemy_id) references Enemy (id);
ALTER TABLE usr_hero ADD CONSTRAINT fk_hero_id foreign key (hero_id) references Hero (id);
ALTER TABLE usr_hero ADD CONSTRAINT fk_user_id foreign key (user_id) references usr (id);

ALTER TABLE usr_item ADD CONSTRAINT fk_item_id foreign key (item_id) references Item (id);
ALTER TABLE usr_item ADD CONSTRAINT fk_item_user_id foreign key (user_id) references usr (id);