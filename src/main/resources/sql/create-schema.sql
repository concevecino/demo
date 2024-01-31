 drop user myorderspro;
 drop user myorderspro@localhost;
 flush privileges;
create user myorderspro@localhost identified by 'myorderspro';
 grant all on myorderspro.* to myorderspro@localhost  identified by 'myorderspro';
 
  GRANT SUPER ON *.* TO myorderspro@localhost  identified by 'myorderspro';