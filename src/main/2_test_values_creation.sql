insert into Enemy (id, name, path_to_image, Hp, is_boss)
values (1, 'clime', '---', 100, 0);

--insert into usr (id, username, password, current_enemy_id, role, rating, score, gametime, user_level)
--values (1, 'first_player', '123', 1, 'admin', 1, 0, 0, 1);


insert into Hero (id, damage, hero_level, name, cost, hero_order)
values (1, 1, 1, 'first_hero', 0, 1);

insert into Item (id, name, damage, rarity_level, cost, is_bought_by_donat)
values (1, 'wood_sword', 30, 1, 50, 0);

--insert into usr_hero (hero_id, user_id)
--values (1, 1);
--
--insert into usr_item (item_id, user_id)
--values (1, 1);