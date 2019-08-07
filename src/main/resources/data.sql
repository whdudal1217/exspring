-- 실습용으로 배포된 mysql 실행 (포트 3306)
-- 실행방법 : C:\eGovFrame-3.7.0\bin\mysql-5.6.21\startup.bat 더블클릭
-- 종료방법 : C:\eGovFrame-3.7.0\bin\mysql-5.6.21\stop.bat 더블클릭
-- 접속 정보 (Database/username/password) : com/com/com01 , hyb/hyb/hyb01, mobile/mobile/mobile01 
-- Data Source Explorer에 이미 com 커넥션 정보가 등록되어 있음

-- MEMBER 테이블 생성 
-- MEM_ID, MEM_PASS, MEM_NAME, MEM_POINT 컬럼을 정의
CREATE TABLE member
( mem_id VARCHAR(50) PRIMARY KEY,
  mem_pass VARCHAR(50),
  mem_name VARCHAR(50),
  mem_point NUMERIC(10,0)
-- , mem_reg_date DATETIME DEFAULT CURRENT_TIMESTAMP
-- , PRIMARY KEY ('mem_id')
);
delete from member where mem_img is null cascade;

select * from member;

alter table member add mem_img VARCHAR(100);
-- BBS 테이블 생성  
-- MySQL 5.6.5, MariaDB 10.0 버전부터 DEFAULT CURRENT_TIMESTAMP, ON UPDATE CURRENT_TIMESTAMP 설정 가능

--게시판 테이블
CREATE TABLE bbs
( bbs_no INT PRIMARY KEY AUTO_INCREMENT, 
  bbs_title VARCHAR(100),
  bbs_content TEXT,
  bbs_writer VARCHAR(50),
  bbs_reg_date DATETIME DEFAULT CURRENT_TIMESTAMP 
-- , bbs_mod_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 ,bbs_count NUMERIC(10,0) DEFAULT 0
 ,FOREIGN KEY (bbs_writer) REFERENCES member (mem_id)
);


select * from bbs;

-- member 테이블에 레코드 1개 추가
-- mem_id는 'a001', mem_pass는 '1234', 
-- mem_name은 '고길동', mem_point는 100로 저장
INSERT INTO member 
( mem_id, mem_pass, mem_name, mem_point )
VALUES ( 'a001', '1234', '고길동', 100 );

INSERT INTO bbs ( bbs_title, bbs_content, bbs_writer  ) VALUES ( '제목60', '내용111', 'a001' );
commit;

select * from bbs;

delete from bbs where bbs_no = 6;

SELECT bbs_no, bbs_title, bbs_content, bbs_writer, bbs_reg_date FROM bbs WHERE bbs_title like CONCAT('%', '1', '%')limit , 10; 

SELECT bbs_no, bbs_title, bbs_content, bbs_writer, bbs_reg_date FROM bbs WHERE bbs_title like CONCAT('%', '1', '%')limit , 10; 

-- 첨부파일 테이블 생성  
CREATE TABLE attach
( 
  att_no INT PRIMARY KEY AUTO_INCREMENT, 
  att_org_name VARCHAR(255), 
  att_new_name VARCHAR(255),
  att_bbs_no INT REFERENCES bbs (bbs_no)
--  ,PRIMARY KEY (att_no) 
--  ,FOREIGN KEY (att_bbs_no) REFERENCES attach (att_no)
);

select * from attach;

-- 해당 글과 그 글의 첨부파일 정보가 함께 조회되도록
SELECT bbs_no, bbs_title, bbs_content, bbs_writer, bbs_reg_date,att_no, att_org_name, att_new_name, att_bbs_no
FROM bbs  left outer join attach
on  bbs_no = att_bbs_no 
order by bbs_no; 

-- 댓글 테이블 생성  
CREATE TABLE reply
( rep_no INT PRIMARY KEY AUTO_INCREMENT, 
  rep_content TEXT,
  rep_writer VARCHAR(50) REFERENCES member (mem_id),
  rep_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  rep_bbs_no INT REFERENCES bbs (bbs_no)
--  ,PRIMARY KEY (bbs_no) 
--  ,FOREIGN KEY (rep_bbs_no) REFERENCES bbs (bbs_no)
--  ,FOREIGN KEY (rep_writer) REFERENCES member (mem_id)
);
SELECT * FROM reply;

--상품테이블 : 상품번호, 상품명, 상품가격, 상품설명, 제조사 (제조일자, 유통기한, 상품이미지)
CREATE TABLE product
( prod_no INT PRIMARY KEY AUTO_INCREMENT, 
  prod_name VARCHAR(100),
  prod_price INT
);

SELECT * FROM product;

delete from product where prod_price = 0;

insert into member (mem_id, mem_pass, mem_name, mem_point) values ('cpote0', 'kqWZiC', 'Cacilie Pote', 52);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('atrustie1', 'PjcxJfmPJtx', 'Alis Trustie', 96);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('llorand2', '9Ep8h1Bt', 'Leyla Lorand', 1);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('sfrounks3', 'YNVV5tJ', 'Sadella Frounks', 91);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('vspillane4', 'S6s2lj', 'Vivienne Spillane', 79);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('gpeniman5', 'BR62oCcZLIun', 'Ginny Peniman', 93);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('hspat6', 'kZr7Hst', 'Heindrick Spat', 57);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('skitchenham7', 'GBwVy6n9mgn', 'Shoshana Kitchenham', 66);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('aatwater8', 'lIe129dQ', 'Avrit Atwater', 95);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('blate9', 'wj6aN93axf', 'Bonny Late', 65);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('lpalaya', 'raMPylMHcqX', 'Lyle Palay', 53);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('lalpesb', 'IWe94WA321', 'Layton Alpes', 98);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('psutcliffec', 'k7t5xG01bG', 'Pierrette Sutcliffe', 8);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('ageatord', '9SFAOd', 'Aldridge Geator', 8);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('slilleee', 'fXkuRmGYg', 'Shantee Lillee', 63);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('vcomerf', 'KTACHHOcVZq', 'Vernon Comer', 54);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('wcozensg', 'oKQqpRjl89R', 'Warren Cozens', 48);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('afordyceh', 'JmF1BN8qnt', 'Adolph Fordyce', 45);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('pecclestonei', 'xZP7001', 'Patty Ecclestone', 97);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('jtethcotej', '6Vcf7BaYm2', 'Joete Tethcote', 74);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('lbullask', '3Ub6eGv', 'Lukas Bullas', 92);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('dklinckl', '4s6FiwG0', 'Derril Klinck', 97);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('cgisbournm', 'dITm3mtdbpZx', 'Clemence Gisbourn', 73);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('mmansonn', 'MPg9D4Os1', 'Mollie Manson', 88);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('dabbyo', 'SPl3GLdZr', 'Devin Abby', 86);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('eberriep', 'wSjRRRvvy7q', 'Esme Berrie', 70);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('jbonhommeq', 'Wa1XCfG7pG4a', 'Jakie Bonhomme', 10);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('astovellr', '60ZtuG', 'Allegra Stovell', 14);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('mrykerts', 'qRqE9lO0iGbg', 'Maximo Rykert', 45);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('mlandrieut', 'KqmdTh', 'Mickie Landrieu', 12);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('gwringeu', 'oNeZ6c', 'Gottfried Wringe', 26);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('fperesv', 'oWGCark75', 'Fredi Peres', 32);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('ezanioliniw', 'iVpyEm', 'Ewell Zaniolini', 53);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('chumfreyx', 'KsVdVy', 'Cece Humfrey', 59);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('bworvilly', 'SOBN6yWkW5c', 'Bree Worvill', 65);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('rsucklingz', 'okGc8Y', 'Rosalyn Suckling', 97);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('jloud10', '91kv76Buu6c', 'Jacinta Loud', 31);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('svenour11', 'GePIl0nQksb', 'Sharla Venour', 48);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('lwedmore12', 'HrcW2H', 'Liz Wedmore.', 89);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('ashowering13', '4WNkalw5U', 'Agatha Showering', 32);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('kletixier14', 'TXm0HNv', 'Kizzee Letixier', 100);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('ghacard15', '9VHNJUWGd2c', 'Guglielmo Hacard', 20);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('etarr16', '3q4EDw', 'Erroll Tarr', 14);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('mroadknight17', 'jywXLyIhBF', 'Milli Roadknight', 61);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('sgabrieli18', '7X7M5b', 'Stevena Gabrieli', 61);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('arehm19', 'BS5G3RXoU', 'Ancell Rehm', 61);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('tsage1a', '6cmDhqA7D', 'Trevor Sage', 10);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('tfurbank1b', 'EMNXsi8', 'Terrill Furbank', 92);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('mstanhope1c', '6bGsMjLnW', 'Maddi Stanhope', 14);
insert into member (mem_id, mem_pass, mem_name, mem_point) values ('gturneaux1d', 'TPNPEM1', 'Gustaf Turneaux', 76);

