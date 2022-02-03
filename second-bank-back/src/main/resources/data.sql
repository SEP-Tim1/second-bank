insert into cards(id, card_holder_name, pan, security_code, expiration_date, account_id) values (0, 'Emina', 'UVOCpxetJ71Eof79PsfKOU8jLsWUmWJuVoZy+umar9s=', 'WJDbTKvG4mZo1BiasoxGhA==', '2028-01-11', 0);
insert into cards(id, card_holder_name, pan, security_code, expiration_date, account_id) values (1, 'Emina', 'AP1tGGR3rgDjfERi/Rpxq08jLsWUmWJuVoZy+umar9s=', 'WJDbTKvG4mZo1BiasoxGhA==', '2028-11-11', 0);

insert into accounts(id, balance, currency, m_id, m_password) values (0, 48965, 0, 603759, '88fl8695nQfsCxVN/JOH8w==');
insert into accounts_cards(account_id, cards_id) values (0, 0);
insert into accounts_cards(account_id, cards_id) values (0, 1);

insert into exchange_rate(id, src, dest, rate) values (1, 1, 0, 120);
insert into exchange_rate(id, src, dest, rate) values (2, 2, 0, 100);
insert into exchange_rate(id, src, dest, rate) values (3, 1, 2, 1.2);