insert into book(library_id,date_of_creation,is_bestseller,name)
values (1,'1984-01-01',true,'1984');
insert into authors(book_id, authors)
values (1,'Orwell');
insert into authors(book_id, authors)
values (1,'Nikulin');
insert into book_genres(book_id, genres)
values (1,'CLASSIC');
insert into book_genres(book_id, genres)
values (1,'NOVEL');
insert into publishers(book_id, publishers) values (1,'ATM');
