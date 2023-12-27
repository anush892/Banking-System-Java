--
-- ################################################################
--
-- DataBase used by the main system / application 

create database projectJEE;

use projectJEE;

create table user_passw (    
      id_user INT NOT NULL auto_increment,    
      username VARCHAR(20) default NULL,    
      password  VARCHAR(20) NOT NULL,  
      email VARCHAR(20),
      address VARCHAR(150),
      currentBalance DOUBLE(13,2) SIGNED default 50000,
      PRIMARY KEY (id_user) 
   );

INSERT INTO user_passw (username, password, email, address, currentBalance) VALUES ('Mansi Mishra' ,'12345', 'mansi@gmail.com'  , 'Street Road 74, 93641 CityLand');

INSERT INTO user_passw (username, password, email, address, currentBalance) VALUES ('Anush Sharma' ,'12345', 'anush@gmail.com'  , 'Street Road 74, 93641 CityLand', '47966.90');

INSERT INTO user_passw (username, password, email, address, currentBalance) VALUES ( 'Priyanshi Singh', '67890' , 'priyanshi@gmx.de', 'Street Road 66, 20183 CityLand', '50112.15' );

INSERT INTO user_passw (username, password, email, address, currentBalance) VALUES ( 'Manish Jangid', '01234', 'manish@gmx.de', 'Street Road 92, 6475 CityLand', '50350.75' );

INSERT INTO user_passw (username, password, email, address) VALUES ( 'Pushpender Singh', '12345', 'pushh@gmx.de', 'Street Road 23, 19287 VD CH' );

--
-- ---------------------------------------------------------------------
--

create table transactions (    
      id_trsc INT NOT NULL auto_increment,    
      user_id INT NOT NULL,    
      trsc_date  DATETIME NOT NULL,         
      trsc_type  VARCHAR(20) NOT NULL,   
      trsc_amount  DOUBLE(13,2) SIGNED NOT NULL, 
      av_amount  DOUBLE(13,2) SIGNED NOT NULL, 

      PRIMARY KEY(id_trsc),
      FOREIGN KEY (user_id)
      REFERENCES USER(id_user)
      ON UPDATE CASCADE ON DELETE CASCADE
 );

select * from  transactions where user_id=1 and trsc_date > '2017-11-18' and trsc_date <  '2017-11-19';

--
-- ---------------------------------------------------------------------
--
-- 

create table credit_cards (    
      id INT NOT NULL,    
      card_numb VARCHAR(16) NOT NULL,
      cvv_cvc VARCHAR(4) NOT NULL,

      PRIMARY KEY(card_numb),
      FOREIGN KEY (id)
      REFERENCES user_passw(id_user)
      ON UPDATE CASCADE ON DELETE CASCADE
 );

insert into credit_cards values 
('1', '4012345678912124', '349' ),
('2', '4845746458456458', '528' ),
('3', '4837645273540891', '610' ),
('4', '4967142748798473', '937' ) ;

--
-- ################################################################
--
-- DataBase used by the RESTful Web Service 

create database credit_card_service;

use credit_card_service;

create table credit_cards (    
      email_id VARCHAR(20) NOT NULL,    
      card_numb VARCHAR(16) NOT NULL,
      cvv_cvc VARCHAR(4) NOT NULL,

      PRIMARY KEY(email_id)
 );

insert into credit_cards values 
('akam@gmx.de', '4012345678912124', '349' ),
('tomy@gmx.de', '4845746458456458', '528' ),
('john@gmx.de', '4837645273540891', '610' ),
('alice@gmx.de', '4967142748798473', '937' ) ;