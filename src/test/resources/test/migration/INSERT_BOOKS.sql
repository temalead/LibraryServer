insert into address(id,street,city,number_of_house) values (1,'test','test',42);
insert into address(id,street,city,number_of_house) values (2,'test2','test2',43);
insert into library(id,address_id) values (1,1);
insert into library(id,address_id) values (2,2);
insert into book(id,name,library_id,description,is_bestseller) values (1,'Test',1,'Just best book',true);
insert into book(id,name,library_id,description,is_bestseller) values (2,'Worst Book',1,'Just worst book',false);
insert into book(id,name,library_id,description,is_bestseller) values (3,'Best Book',2,'Just best book',true);
insert into book_genres(book_id, genres) VALUES (1,'NOVEL');
insert into book_genres(book_id, genres) VALUES (1,'CLASSIC');
insert into authors(book_id, authors) VALUES (1,'Test');
insert into authors(book_id, authors) VALUES (2,'Mi');
insert into authors(book_id, authors) VALUES (3,'Li');
insert into publishers(book_id, publishers) VALUES (1,'ATM');

alter sequence book_id_seq restart with 4;
