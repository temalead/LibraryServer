insert into book(id,name,library_id,description,is_bestseller) values (1,'Best Book',1,'Just best book',true);
insert into book(id,name,library_id,description,is_bestseller) values (2,'Worst Book',1,'Just worst book',false);
insert into book(id,name,library_id,description,is_bestseller) values (3,'Best Book',2,'Just best book',true);
insert into book_genres(book_id, genres) VALUES (1,'NOVEL');
insert into book_genres(book_id, genres) VALUES (1,'CLASSIC');
insert into authors(book_id, authors) VALUES (1,'Nikulin');
insert into authors(book_id, authors) VALUES (2,'Mi');
insert into authors(book_id, authors) VALUES (3,'Li');
insert into publishers(book_id, publishers) VALUES (1,'ATM');

alter sequence book_id_seq restart with 4;
