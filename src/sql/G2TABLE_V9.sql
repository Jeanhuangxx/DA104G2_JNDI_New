exp DA104G2/123456 BUFFER=4096 FILE=DA104G2.DMP OWNER=DA104G2
imp DA104G2/123456 BUFFER=4096 FILE=DA104G2.DMP FROMUSER=DA104G2 TOUSER=DA104G2
/*******************************************************************初始化********************************************************/

----------------------------論壇留言------------------------
DROP TABLE POST_REPLY;
DROP SEQUENCE post_reply_seq;
----------------------------文章檢舉------------------------
DROP TABLE POST_REPORT;
DROP SEQUENCE post_re_seq;
----------------------------論壇文章------------------------
DROP TABLE FORUM;
DROP SEQUENCE forum_seq;
----------------------------追蹤行程------------------------
DROP TABLE TOUR_LIKE;
----------------------------行程相片------------------------
DROP TABLE TOUR_IMAGE;
DROP SEQUENCE TOUR_IMAGE_SEQ;
----------------------------房間相片------------------------
DROP TABLE ROOM_IMAGE;
DROP SEQUENCE ROOM_IMAGE_SEQ;
----------------------------行程房間詳情------------------------
DROP TABLE TOUR_ROOM;
DROP SEQUENCE TOUR_ROOM_SEQ;
----------------------------行程檢舉------------------------
DROP TABLE TOUR_REPORT;
DROP SEQUENCE TOUR_REPORT_SEQ;
----------------------------行程訂單------------------------
DROP TABLE TOUR_ORDER;
DROP SEQUENCE TOUR_ORDER_SEQ;
----------------------------行程------------------------
DROP TABLE TOUR;
DROP SEQUENCE TOUR_SEQ;
----------------------------揪團留言------------------------
DROP TABLE GROUP_MESSAGE;
DROP SEQUENCE GROUP_ME_SEQ;
----------------------------報名揪團------------------------
DROP TABLE GROUP_JOIN;
----------------------------揪團檢舉------------------------
DROP TABLE GROUP_REPORT;
DROP SEQUENCE GROUP_RE_SEQ;
----------------------------揪團------------------------
DROP TABLE DIVEGROUP;
DROP SEQUENCE GROUP_SEQ;
----------------------------潛點留言------------------------
DROP TABLE DIVEMESSAGE;
DROP SEQUENCE SEQ_DIVEMESSAGEL_NO;
----------------------------潛點---------------------------
DROP TABLE DIVE;
DROP SEQUENCE SEQ_DIVE_NO;
----------------------------氣象區域------------------------
DROP TABLE WEATHER_AREA;
DROP SEQUENCE SEQ_WEATHER_AREA_NO;
----------------------------氣象地點------------------------
DROP TABLE WEATHER_LOCAL;
DROP SEQUENCE SEQ_WT_LOCAL_NO;
-----------------------裝備訂單明細--------------------
DROP TABLE EQUIP_ODLIST;
-----------------------裝備訂單----------------------------
DROP TABLE EQUIP_ORDER;
DROP SEQUENCE EQUIP_ORDER_NO_SEQ;
---------------------追蹤裝備清單-------------------
DROP TABLE EQUIP_FAVLIST;
-----------------------------裝備---------------------------
DROP TABLE EQUIPMENT;
DROP SEQUENCE EQUIP_NO_SEQ;
-------------------------相片檢舉----------------------
DROP TABLE PHOTO_REPORT;
DROP SEQUENCE SEQ_PHOTO_RE_NO;
--------------------相片----------------------
DROP TABLE PHOTO;
DROP SEQUENCE SEQ_PHOTO_NO;
-----------------------相簿---------------------
DROP TABLE ALBUM;
DROP SEQUENCE SEQ_ALBUM_NO;
----------------------會員--------------------
DROP TABLE MEM;
DROP  SEQUENCE SEQ_MEM_NO;
----------------------權限--------------------
DROP TABLE PE; 
DROP SEQUENCE SEQ_PE_NO;
----------------------功能--------------------
DROP TABLE FUN; 
DROP SEQUENCE SEQ_FUN_NO;
----------------------管理員--------------------
DROP TABLE ADM; 
DROP SEQUENCE SEQ_ADM_NO;


/********************************************************************管理員.權限.功能************************************************************/

----------------------------管理員表格----------------------
CREATE TABLE ADM(
ADM_NO    VARCHAR2(7 CHAR) PRIMARY KEY NOT NULL,
ADM_IMG   BLOB,
ADM_ID    VARCHAR2(20 CHAR) NOT NULL,
ADM_PSW   VARCHAR2(20 CHAR) NOT NULL,
ADM_NAME  VARCHAR2(20 CHAR) NOT NULL,
ADM_TEL   NUMBER(10) NOT NULL,
ADM_EMAIL VARCHAR2(50 CHAR) NOT NULL
);
------------------------管理員編號計數器---------------------
CREATE SEQUENCE SEQ_ADM_NO
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCACHE;

-----------------------------管理員假資料----------------------
INSERT INTO ADM (ADM_NO, ADM_ID, ADM_PSW, ADM_NAME, ADM_TEL, ADM_EMAIL, ADM_IMG)
VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'),'ADM001','ADM001','NAME01','0900000001','ADM001@gmail.com','0');

INSERT INTO ADM (ADM_NO, ADM_ID, ADM_PSW, ADM_NAME, ADM_TEL, ADM_EMAIL, ADM_IMG)
VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'),'ADM002','ADM002','NAME02','0900000002','ADM002@gmail.com','0');

INSERT INTO ADM (ADM_NO, ADM_ID, ADM_PSW, ADM_NAME, ADM_TEL, ADM_EMAIL, ADM_IMG)
VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'),'ADM003','ADM003','NAME03','0900000003','ADM003@gmail.com','0');

INSERT INTO ADM (ADM_NO, ADM_ID, ADM_PSW, ADM_NAME, ADM_TEL, ADM_EMAIL, ADM_IMG)
VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'),'ADM004','ADM004','NAME04','0900000004','ADM004@gmail.com','0');

INSERT INTO ADM (ADM_NO, ADM_ID, ADM_PSW, ADM_NAME, ADM_TEL, ADM_EMAIL, ADM_IMG)
VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'),'ADM005','ADM005','NAME05','0900000005','ADM005@gmail.com','0');

INSERT INTO ADM (ADM_NO, ADM_ID, ADM_PSW, ADM_NAME, ADM_TEL, ADM_EMAIL, ADM_IMG)
VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'),'ADM006','ADM006','NAME06','0900000006','ADM006@gmail.com','0');

INSERT INTO ADM (ADM_NO, ADM_ID, ADM_PSW, ADM_NAME, ADM_TEL, ADM_EMAIL, ADM_IMG)
VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'),'ADM007','ADM007','NAME07','0900000007','ADM007@gmail.com','0');

INSERT INTO ADM (ADM_NO, ADM_ID, ADM_PSW, ADM_NAME, ADM_TEL, ADM_EMAIL, ADM_IMG)
VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'),'ADM008','ADM008','NAME08','0900000008','ADM008@gmail.com','0');

INSERT INTO ADM (ADM_NO, ADM_ID, ADM_PSW, ADM_NAME, ADM_TEL, ADM_EMAIL, ADM_IMG)
VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'),'ADM009','ADM009','NAME09','0900000009','ADM009@gmail.com','0');

INSERT INTO ADM (ADM_NO, ADM_ID, ADM_PSW, ADM_NAME, ADM_TEL, ADM_EMAIL, ADM_IMG)
VALUES ('A'||LPAD(to_char(SEQ_ADM_NO.NEXTVAL),3,'0'),'ADM010','ADM010','NAME10','0900000010','ADM010@gmail.com','0');


------------------------------功能表格---------------------------
CREATE TABLE FUN(
FUN_NO   VARCHAR2(7 CHAR) PRIMARY KEY NOT NULL,
FUN_NAME VARCHAR2(20 CHAR) NOT NULL
);
-------------------------功能編號計數器--------------------------
CREATE SEQUENCE SEQ_FUN_NO
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCACHE;

----------------------------功能假資料---------------------------
INSERT INTO FUN (FUN_NO, FUN_NAME)VALUES ('F'||LPAD(to_char(SEQ_FUN_NO.NEXTVAL),3,'0'),'管理員系統');
INSERT INTO FUN (FUN_NO, FUN_NAME)VALUES ('F'||LPAD(to_char(SEQ_FUN_NO.NEXTVAL),3,'0'),'一般會員');
INSERT INTO FUN (FUN_NO, FUN_NAME)VALUES ('F'||LPAD(to_char(SEQ_FUN_NO.NEXTVAL),3,'0'),'潛店會員');
INSERT INTO FUN (FUN_NO, FUN_NAME)VALUES ('F'||LPAD(to_char(SEQ_FUN_NO.NEXTVAL),3,'0'),'訂單管理');
INSERT INTO FUN (FUN_NO, FUN_NAME)VALUES ('F'||LPAD(to_char(SEQ_FUN_NO.NEXTVAL),3,'0'),'裝備管理');
INSERT INTO FUN (FUN_NO, FUN_NAME)VALUES ('F'||LPAD(to_char(SEQ_FUN_NO.NEXTVAL),3,'0'),'檢舉審核');
INSERT INTO FUN (FUN_NO, FUN_NAME)VALUES ('F'||LPAD(to_char(SEQ_FUN_NO.NEXTVAL),3,'0'),'潛點資訊維護');
INSERT INTO FUN (FUN_NO, FUN_NAME)VALUES ('F'||LPAD(to_char(SEQ_FUN_NO.NEXTVAL),3,'0'),'氣象爬蟲');
INSERT INTO FUN (FUN_NO, FUN_NAME)VALUES ('F'||LPAD(to_char(SEQ_FUN_NO.NEXTVAL),3,'0'),'推廣揪團');


------------------------------權限表格------------------------------
CREATE TABLE PE(
ADM_NO VARCHAR2(7 CHAR) NOT NULL,
FUN_NO VARCHAR2(20 CHAR) NOT NULL,
PRIMARY KEY (ADM_NO, FUN_NO),
CONSTRAINT FK_PE_ADM
FOREIGN KEY (ADM_NO) REFERENCES ADM (ADM_NO),
CONSTRAINT FK_PE_FUN
FOREIGN KEY (FUN_NO) REFERENCES FUN (FUN_NO)
);

---------------------------權限編號計數器----------------------
CREATE SEQUENCE SEQ_PE_NO
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCACHE;

---------------------------權限假資料------------------------------
INSERT INTO PE (ADM_NO, FUN_NO)VALUES ('A001','F001');
INSERT INTO PE (ADM_NO, FUN_NO)VALUES ('A001','F002');
INSERT INTO PE (ADM_NO, FUN_NO)VALUES ('A001','F003');
INSERT INTO PE (ADM_NO, FUN_NO)VALUES ('A001','F004');
INSERT INTO PE (ADM_NO, FUN_NO)VALUES ('A001','F005');
INSERT INTO PE (ADM_NO, FUN_NO)VALUES ('A001','F006');
INSERT INTO PE (ADM_NO, FUN_NO)VALUES ('A001','F007');
INSERT INTO PE (ADM_NO, FUN_NO)VALUES ('A001','F008');
INSERT INTO PE (ADM_NO, FUN_NO)VALUES ('A001','F009');
INSERT INTO PE (ADM_NO, FUN_NO)VALUES ('A002','F001');


/******************************************************************************會員********************************************************/
--------------------會員表格-------------
CREATE TABLE MEM(
MEM_NO             VARCHAR2(7) PRIMARY KEY NOT NULL,
MEM_NAME           VARCHAR2(20),
MEM_ID             VARCHAR2(20) NOT NULL,
MEM_PSW            VARCHAR2(20) NOT NULL,
MEM_GENERAL_GEN    NUMBER(1),
MEM_GENERAL_BD     DATE,
MEM_TEL            NUMBER(10),
MEM_EMAIL          VARCHAR2(50),
MEM_ADD            VARCHAR2(100),
MEM_IMG            BLOB,
MEM_PER            NUMBER(1) NOT NULL,
MEM_TYPE           NUMBER(1) NOT NULL,
MEM_STORE_BUSINESS BLOB,
MEM_STORE_OWNER    VARCHAR2(20),
MEM_STORE_TAXID    VARCHAR2(8)
);
-------------------會員編號計數------------    
CREATE SEQUENCE SEQ_MEM_NO
START WITH 1
INCREMENT BY 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

-----------------------會員假資料----------------
INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,
MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE)
VALUES ('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),'魯夫','LUFY','123456','0',
to_date('1995-05-05','yyyy-mm-dd'),'0900123456','LUFY@gmail.com','中壢區中央路232巷50號',
'0','0');

INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,
MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE)
VALUES ('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),'索隆','Zoro','123456','0',
to_date('1995-11-11','yyyy-mm-dd'),'0900123456','Zoro@gmail.com','中壢區中央路232巷50號',
'0','0');

INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,
MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE)
VALUES ('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),'烏索普','Usopp','123456','0',
to_date('1995-04-01','yyyy-mm-dd'),'0900123456','Usopp@gmail.com','中壢區中央路232巷50號',
'0','0');

INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,
MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE)
VALUES ('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),'艾斯','Ace','123456','0',
to_date('1995-01-01','yyyy-mm-dd'),'0900123456','Ace@gmail.com','中壢區中央路232巷50號',
'0','0');

INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,
MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE)
VALUES ('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),'喬巴','Chopper','123456','0',
to_date('1995-12-24','yyyy-mm-dd'),'0900123456','Chopper@gmail.com','中壢區中央路232巷50號',
'0','0');

INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_GENERAL_GEN,MEM_GENERAL_BD,
MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE)
VALUES ('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),'羅賓','Robin','123456','1',
to_date('1995-02-06','yyyy-mm-dd'),'0900123456','Robin@gmail.com','中壢區中央路232巷50號',
'0','0');

INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE,MEM_STORE_OWNER,MEM_STORE_TAXID)
VALUES ('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),'芒果潛水店','mango','123456',
'0900123456','Robin@gmail.com','929屏東縣琉球鄉三民路198-7號','0','1','芒果','12345678');

INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE,MEM_STORE_OWNER,MEM_STORE_TAXID)
VALUES ('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),'芭樂潛水店','mango','123456',
'0900123456','Robin@gmail.com','228新北市貢寮區龍洞街64號','0','1','芭樂','12345678');

INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE,MEM_STORE_OWNER,MEM_STORE_TAXID)
VALUES ('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),'西瓜潛水店','mango','123456',
'0900123456','Robin@gmail.com','946屏東縣恆春鎮南灣路467號','0','1','西瓜','12345678');

INSERT INTO MEM (MEM_NO,MEM_NAME,MEM_ID,MEM_PSW,MEM_TEL,MEM_EMAIL,MEM_ADD,MEM_PER,MEM_TYPE,MEM_STORE_OWNER,MEM_STORE_TAXID)
VALUES ('M'||LPAD(to_char(SEQ_MEM_NO.nextval),6,'0'),'葡萄潛水店','mango','123456',
'0900123456','Robin@gmail.com','951台東縣綠島鄉南寮村渔港路1-2號','0','1','葡萄','12345678');


/********************************************************************相簿.相片.相片檢舉******************************************************************/
-----------相簿編號計數器------------
CREATE SEQUENCE SEQ_ALBUM_NO
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE
  NOCACHE;

------------相片編號計數器-----------
CREATE SEQUENCE SEQ_PHOTO_NO
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE
  NOCACHE;

---------相片檢舉編號計數器---------
CREATE SEQUENCE SEQ_PHOTO_RE_NO
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE
  NOCACHE;

-------------------相簿------------------
CREATE TABLE ALBUM
  (
     ALBUM_NO   VARCHAR2(7) PRIMARY KEY NOT NULL,
     MEM_NO     VARCHAR2(7),
     ALBUM_NAME VARCHAR(20) NOT NULL,
     ALBUM_TIME TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
     ALBUM_PIC  BLOB NOT NULL,
     ALBUM_NOTE VARCHAR(300),
     ALBUM_STATUS NUMBER(1) NOT NULL,
     CONSTRAINT FK_ALBUM_MEM FOREIGN KEY (MEM_NO) REFERENCES MEM(MEM_NO)
  );

------------------相片------------------
CREATE TABLE PHOTO
  (
     PHOTO_NO     VARCHAR2(7) PRIMARY KEY NOT NULL,
     ALBUM_NO     VARCHAR2(7) NOT NULL,
     PHOTO_NAME   VARCHAR2(20),
     PHOTO_TIME   TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
     PHOTO_PIC    BLOB NOT NULL,
     PHOTO_NOTE   VARCHAR2(300),
     PHOTO_STATUS NUMBER(1) NOT NULL,
     CONSTRAINT FK_PHOTO_ALBUM FOREIGN KEY (ALBUM_NO) REFERENCES ALBUM(ALBUM_NO)
  );

----------------------相片檢舉--------------------
CREATE TABLE PHOTO_REPORT
  (
     PHOTO_RE_NO     VARCHAR2(7) PRIMARY KEY NOT NULL,
     PHOTO_NO        VARCHAR2(7) NOT NULL,
     MEM_NO          VARCHAR2(7) NOT NULL,
     PHOTO_RE_TIME   TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
     PHOTO_RE_NOTE   VARCHAR2(200) NOT NULL,
     PHOTO_RE_STATUS NUMBER(1) NOT NULL,
     CONSTRAINT FK_REPORT_MEMBER FOREIGN KEY (MEM_NO) REFERENCES MEM(MEM_NO),
     CONSTRAINT FK_REPORT_PHOTO FOREIGN KEY (PHOTO_NO) REFERENCES PHOTO(PHOTO_NO
     )
  ); 
  
  --------------------相簿假資料-------------------
  INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)
  VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),'M000001','魯夫特輯',SYSTIMESTAMP,'fffff','我好長',0);
  INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)
  VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),'M000001','索龍特輯',SYSTIMESTAMP,'fffff','我好帥',0);
  INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)
  VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),'M000002','??特輯',SYSTIMESTAMP,'fffff','我好正',1);
  INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)
  VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),'M000003','懶了特輯',SYSTIMESTAMP,'fffff','我好強',1);
  INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)
  VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),'M000004','潛水特輯',SYSTIMESTAMP,'fffff','我好猛',0);
  INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)
  VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),'M000005','深潛特輯',SYSTIMESTAMP,'fffff','我好高',0);
  INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)
  VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),'M000006','!!!特輯',SYSTIMESTAMP,'fffff','我好美',0);
  INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)
  VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),'M000006','!!特輯',SYSTIMESTAMP,'fffff','我好白',0);
  INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)
  VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),'M000006','!!特輯',SYSTIMESTAMP,'fffff','我好窮',1);
  INSERT INTO ALBUM(album_no,mem_no,album_name,album_time,album_pic,album_note,album_status)
  VALUES('A'||LPAD(to_char(SEQ_ALBUM_NO.NEXTVAL), 6, '0'),'M000006','專題特輯',SYSTIMESTAMP,'fffff','我好累',0);
  --------------------相片假資料---------------------
  INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)
  VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),'A000001','潛水照1',SYSTIMESTAMP,'fffff','天天做專題',0);
  INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)
  VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),'A000002','潛水照2',SYSTIMESTAMP,'fffff','天天天做專題',0);
  INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)
  VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),'A000003','潛水照3',SYSTIMESTAMP,'fffff','天天天天做專題',0);
  INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)
  VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),'A000004','潛水照4',SYSTIMESTAMP,'fffff','天天天天天做專題',0);
  INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)
  VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),'A000005','潛水照5',SYSTIMESTAMP,'fffff','天天天天天天做專題',0);
  INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)
  VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),'A000006','潛水照6',SYSTIMESTAMP,'fffff','天天天天天天做專題',0);
  INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)
  VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),'A000007','潛水照7',SYSTIMESTAMP,'fffff','天天天天天做專題',0);
  INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)
  VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),'A000008','潛水照8',SYSTIMESTAMP,'fffff','天天天天做專題',0);
  INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)
  VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),'A000009','潛水照9',SYSTIMESTAMP,'fffff','天天天做專題',0);
  INSERT INTO PHOTO(photo_no,album_no,photo_name,photo_time,photo_pic,photo_note,photo_status)
  VALUES(('P'||LPAD(to_char(SEQ_PHOTO_NO.NEXTVAL), 6, '0')),'A000010','潛水照10',SYSTIMESTAMP,'fffff','天天做專題',0);
  --------------------相片檢舉假資料-------------------
  INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)
  VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),'P000001','M000001','亂貼照片',SYSTIMESTAMP,0);
  INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)
  VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),'P000002','M000002','亂貼照片',SYSTIMESTAMP,0);
  INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)
  VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),'P000003','M000003','亂貼照片',SYSTIMESTAMP,0);
  INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)
  VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),'P000004','M000004','亂貼照片',SYSTIMESTAMP,0);
  INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)
  VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),'P000005','M000004','亂貼照片',SYSTIMESTAMP,0);
  INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)
  VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),'P000006','M000005','亂貼照片',SYSTIMESTAMP,0);
  INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)
  VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),'P000001','M000005','亂貼照片',SYSTIMESTAMP,0);
  INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)
  VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),'P000002','M000006','亂貼照片',SYSTIMESTAMP,0);
  INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)
  VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),'P000003','M000006','亂貼照片',SYSTIMESTAMP,0);
  INSERT INTO PHOTO_REPORT(photo_re_no,photo_no,mem_no,photo_re_note,photo_re_time,photo_re_status)
  VALUES(('PR'||LPAD(to_char(SEQ_PHOTO_RE_NO.NEXTVAL), 5, '0')),'P000004','M000006','亂貼照片',SYSTIMESTAMP,0);


/************************************************************裝備.裝備訂單.裝備訂單明細.裝備追蹤清單**********************************************/

-------------------裝備編號SEQ----------------
CREATE SEQUENCE EQUIP_NO_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--------------------裝備表格-----------------
CREATE TABLE EQUIPMENT (
EQUIP_NO VARCHAR2(7) PRIMARY KEY NOT NULL,
EQUIP_NAME VARCHAR2(50) NOT NULL,
EQUIP_DES CLOB NOT NULL,
EQUIP_IMG1 BLOB,
EQUIP_IMG2 BLOB,
EQUIP_IMG3 BLOB,
EQUIP_STATUS NUMBER(1) NOT NULL,
EQUIP_PRICE NUMBER(10) NOT NULL,
EQUIP_UPDATE DATE NOT NULL
);

--------------------裝備假資料----------------
INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_status, equip_price, equip_update) 
VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),'裝備1','裝備詳情1',1,100,TO_DATE('2019/12/01', 'yyyy/mm/dd'));
INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_status, equip_price, equip_update) 
VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),'裝備2','裝備詳情2',1,200,TO_DATE('2019/12/02', 'yyyy/mm/dd'));
INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_status, equip_price, equip_update) 
VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),'裝備3','裝備詳情3',1,300,TO_DATE('2019/12/03', 'yyyy/mm/dd'));
INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_status, equip_price, equip_update) 
VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),'裝備4','裝備詳情4',1,400,TO_DATE('2019/12/04', 'yyyy/mm/dd'));
INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_status, equip_price, equip_update) 
VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),'裝備5','裝備詳情5',1,500,TO_DATE('2019/12/05', 'yyyy/mm/dd'));
INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_status, equip_price, equip_update) 
VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),'裝備6','裝備詳情6',1,600,TO_DATE('2019/12/06', 'yyyy/mm/dd'));
INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_status, equip_price, equip_update) 
VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),'裝備7','裝備詳情7',1,700,TO_DATE('2019/12/07', 'yyyy/mm/dd'));
INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_status, equip_price, equip_update) 
VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),'裝備8','裝備詳情8',1,800,TO_DATE('2019/12/08', 'yyyy/mm/dd'));
INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_status, equip_price, equip_update) 
VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),'裝備9','裝備詳情9',1,900,TO_DATE('2019/12/09', 'yyyy/mm/dd'));
INSERT INTO EQUIPMENT (equip_no, equip_name, equip_des, equip_status, equip_price, equip_update) 
VALUES ('EQ'||LPAD(to_char(equip_no_seq.NEXTVAL),5,'0'),'裝備10','裝備詳情10',1,1000,TO_DATE('2019/12/10', 'yyyy/mm/dd'));

---------------------訂單編號SEQ--------------------------
CREATE SEQUENCE EQUIP_ORDER_NO_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-----------------------裝備訂單--------------------------
CREATE TABLE EQUIP_ORDER (
EQUIP_ORDER_NO VARCHAR2(15) PRIMARY KEY NOT NULL,
MEM_NO VARCHAR2(7) NOT NULL,
EQUIP_ORDER_TIME TIMESTAMP,
EQUIP_ORDER_PRICE NUMBER(7) NOT NULL,
EQUIP_SHIPPING_DATE DATE,
EQUIP_ORDER_STATUS NUMBER(1) NOT NULL,
EQUIP_NOTE VARCHAR2(100),
CUS_NAME VARCHAR2(20) NOT NULL,
CUS_TEL NUMBER(10) NOT NULL,
CUS_ADD VARCHAR2(100) NOT NULL,
CONSTRAINT FK_EQUIP_ORDER_MEMBER FOREIGN KEY (MEM_NO) REFERENCES MEM (MEM_NO),
CREATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

----------------------訂單假資料-----------------------------
INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, cus_name, cus_tel, cus_add) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), 'M000001', CURRENT_TIMESTAMP, 100, TO_DATE('2020/01/01', 'yyyy/mm/dd'), 0, '魯夫', 900123456, '中壢區中央路232巷50號');
INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, cus_name, cus_tel, cus_add) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), 'M000002', CURRENT_TIMESTAMP, 200, TO_DATE('2020/01/01', 'yyyy/mm/dd'), 0, '索隆', 900123456, '中壢區中央路232巷50號');
INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, cus_name, cus_tel, cus_add) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), 'M000003', CURRENT_TIMESTAMP, 300, TO_DATE('2020/01/01', 'yyyy/mm/dd'), 0, '烏索普', 900123456, '中壢區中央路232巷50號');
INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, cus_name, cus_tel, cus_add) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), 'M000004', CURRENT_TIMESTAMP, 400, TO_DATE('2020/01/01', 'yyyy/mm/dd'), 0, '艾斯', 900123456, '中壢區中央路232巷50號');
INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, cus_name, cus_tel, cus_add) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), 'M000005', CURRENT_TIMESTAMP, 500, TO_DATE('2020/01/01', 'yyyy/mm/dd'), 0, '喬巴', 900123456, '中壢區中央路232巷50號');
INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, cus_name, cus_tel, cus_add) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), 'M000006', CURRENT_TIMESTAMP, 600, TO_DATE('2020/01/01', 'yyyy/mm/dd'), 0, '羅賓', 900123456, '中壢區中央路232巷50號');
INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, cus_name, cus_tel, cus_add) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), 'M000001', CURRENT_TIMESTAMP, 700, TO_DATE('2020/01/01', 'yyyy/mm/dd'), 0, '魯夫', 900123456, '中壢區中央路232巷50號');
INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, cus_name, cus_tel, cus_add) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), 'M000002', CURRENT_TIMESTAMP, 800, TO_DATE('2020/01/01', 'yyyy/mm/dd'), 0, '索隆', 900123456, '中壢區中央路232巷50號');
INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, cus_name, cus_tel, cus_add) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), 'M000003', CURRENT_TIMESTAMP, 900, TO_DATE('2020/01/01', 'yyyy/mm/dd'), 0, '烏索普', 900123456, '中壢區中央路232巷50號');
INSERT INTO EQUIP_ORDER (equip_order_no, mem_no, equip_order_time, equip_order_price, equip_shipping_date, equip_order_status, cus_name, cus_tel, cus_add) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(equip_order_no_seq.NEXTVAL),5,'0'), 'M000004', CURRENT_TIMESTAMP, 1000, TO_DATE('2020/01/01', 'yyyy/mm/dd'), 0, '艾斯', 900123456, '中壢區中央路232巷50號');

------------------------------裝備訂單明細------------------------------
CREATE TABLE EQUIP_ODLIST(
EQUIP_ORDER_NO VARCHAR2(15) NOT NULL,
EQUIP_NO VARCHAR2(7) NOT NULL,
EQUIP_NAME VARCHAR2(50) NOT NULL,
BUY_AMT NUMBER(7) NOT NULL,
EQUIP_PRICE NUMBER(10) NOT NULL,
PRIMARY KEY (EQUIP_ORDER_NO,EQUIP_NO),
CONSTRAINT FK_EQUIP_ODLIST_EQUIP_ORDER 
FOREIGN KEY (EQUIP_ORDER_NO) REFERENCES EQUIP_ORDER(EQUIP_ORDER_NO),
CONSTRAINT FK_EQUIP_ODLIST_EQUIPMENT 
FOREIGN KEY (EQUIP_NO) REFERENCES EQUIPMENT(EQUIP_NO)
);

---------------------------訂單明細假資料------------------------------
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00001','EQ00001','裝備1',1,100);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00002','EQ00002','裝備2',1,200);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00003','EQ00001','裝備1',1,100);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00003','EQ00002','裝備2',1,200);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00004','EQ00002','裝備2',2,400);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00005','EQ00002','裝備2',1,200);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00005','EQ00003','裝備3',1,300);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00006','EQ00001','裝備1',1,100);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00006','EQ00002','裝備2',1,200);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00006','EQ00003','裝備3',1,300);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00007','EQ00002','裝備2',2,400);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00007','EQ00003','裝備3',1,300);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00008','EQ00002','裝備2',4,800);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00009','EQ00003','裝備3',3,900);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00010','EQ00002','裝備2',1,200);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00010','EQ00003','裝備3',1,300);
INSERT INTO EQUIP_ODLIST (equip_order_no, equip_no, equip_name, buy_amt, equip_price) 
VALUES (to_char(sysdate,'yyyymmdd')||'-'||'00010','EQ00005','裝備5',1,500);

--------------------追蹤裝備清單------------------------
CREATE TABLE EQUIP_FAVLIST(
MEM_NO VARCHAR2(7) NOT NULL,
EQUIP_NO VARCHAR2(7) NOT NULL,
PRIMARY KEY (MEM_NO, EQUIP_NO ),
CONSTRAINT FK_EQUIP_FAVLIST_MEMBER 
FOREIGN KEY (MEM_NO) REFERENCES MEM (MEM_NO),
CONSTRAINT FK_EQUIP_FAVLIST_EQUIPMENT 
FOREIGN KEY (EQUIP_NO) REFERENCES EQUIPMENT(EQUIP_NO)
);

-------------------追蹤清單假資料-----------------------
INSERT INTO EQUIP_FAVLIST (mem_no, equip_no) 
VALUES ('M000001','EQ00005');
INSERT INTO EQUIP_FAVLIST (mem_no, equip_no) 
VALUES ('M000001','EQ00007');
INSERT INTO EQUIP_FAVLIST (mem_no, equip_no) 
VALUES ('M000001','EQ00010');
INSERT INTO EQUIP_FAVLIST (mem_no, equip_no) 
VALUES ('M000002','EQ00010');
INSERT INTO EQUIP_FAVLIST (mem_no, equip_no) 
VALUES ('M000003','EQ00003');
INSERT INTO EQUIP_FAVLIST (mem_no, equip_no) 
VALUES ('M000003','EQ00007');
INSERT INTO EQUIP_FAVLIST (mem_no, equip_no) 
VALUES ('M000006','EQ00006');
INSERT INTO EQUIP_FAVLIST (mem_no, equip_no) 
VALUES ('M000008','EQ00008');
INSERT INTO EQUIP_FAVLIST (mem_no, equip_no) 
VALUES ('M000010','EQ00008');
INSERT INTO EQUIP_FAVLIST (mem_no, equip_no) 
VALUES ('M000010','EQ00010');


/**************************************************潛點.氣象區域.氣象地點.潛點留言**********************************************/

-------------------------------氣象地點-----------------------------
CREATE TABLE WEATHER_LOCAL (
    WT_LOCAL VARCHAR2(7) NOT NULL,
    WT_TEMP VARCHAR2(10),
    WT_WATERTEMP VARCHAR2(10),
    WT_RAINRATE VARCHAR2(10),
    WT_WAVEHEIGHT VARCHAR2(10),
    WT_SUNRISE TIMESTAMP,
    WT_SUNDOWN TIMESTAMP,
    WT_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP Not Null,
    CONSTRAINT WEATHER_LOCAL PRIMARY KEY (WT_LOCAL)
);

-------------------------------氣象地點SEQ-----------------------------
CREATE SEQUENCE SEQ_WT_LOCAL_NO
START WITH 1
INCREMENT BY 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

-------------------------------氣象區域-----------------------------
CREATE TABLE WEATHER_AREA (
    WEATHER_AREA_NO VARCHAR2(7) PRIMARY KEY NOT NULL,
    WT_LOCAL VARCHAR2(7) NOT NULL,
    CONSTRAINT WEATHER_AREA_TO_WEATHER_LOCAL FOREIGN KEY (WT_LOCAL) REFERENCES WEATHER_LOCAL(WT_LOCAL)
);

-------------------------------氣象區域SEQ-----------------------------
CREATE SEQUENCE SEQ_WEATHER_AREA_NO
START WITH 1
INCREMENT BY 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

-------------------------------潛點-----------------------------
CREATE TABLE DIVE (
    DIVE_NO VARCHAR2(7) PRIMARY KEY NOT NULL,
    WEATHER_AREA_NO VARCHAR2(7) NOT NULL,
    DIVE_NAME VARCHAR2(50) NOT NULL,
    DIVE_DES VARCHAR2(200),
    DIVE_IMG BLOB,
    DIVE_STATUS NUMBER(7),
    DIVE_LAT VARCHAR2(20) NOT NULL,
    DIVE_LANG VARCHAR2(20) NOT NULL,

    CONSTRAINT DIVE_TO_WEATHER_AREA FOREIGN KEY (WEATHER_AREA_NO) REFERENCES WEATHER_AREA(WEATHER_AREA_NO)
);

-------------------------------潛點SEQ-----------------------------
CREATE SEQUENCE SEQ_DIVE_NO
START WITH 1
INCREMENT BY 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

-------------------------------潛點留言----------------------------
CREATE TABLE DIVEMESSAGE (
    DIVMEG_NO VARCHAR2(7) PRIMARY KEY NOT NULL,
    DIVE_NO VARCHAR2(7) NOT NULL,
    MEM_NO VARCHAR2(7) NOT NULL,
    DIVMEG_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP Not Null,
    DIVMEG_NOTE VARCHAR2(200) NOT NULL,
    CONSTRAINT DIVEMESSAGE_TO_DIVE FOREIGN KEY (DIVE_NO) REFERENCES DIVE(DIVE_NO),
    CONSTRAINT DIVEMESSAGE_TO_MEM FOREIGN KEY (MEM_NO) REFERENCES MEM(MEM_NO)
);

-------------------------------潛點留言SEQ----------------------------
CREATE SEQUENCE SEQ_DIVEMESSAGEL_NO
START WITH 1
INCREMENT BY 1
NOMAXVALUE 
NOCYCLE
NOCACHE;

----------------------WEATHER_LOCAL假資料-----------------------
INSERT INTO WEATHER_LOCAL (WT_LOCAL , WT_TEMP , WT_WATERTEMP , WT_RAINRATE , WT_WAVEHEIGHT , WT_SUNRISE , WT_SUNDOWN , WT_TIME  )
VALUES ('WL'||LPAD(to_char(SEQ_WT_LOCAL_NO.NEXTVAL), 5, '0'),'21' ,'24', '80',
'110','2019-08-11 05:24:31', '2019-08-11 18:24:31', CURRENT_TIMESTAMP);

INSERT INTO WEATHER_LOCAL (WT_LOCAL , WT_TEMP , WT_WATERTEMP , WT_RAINRATE , WT_WAVEHEIGHT , WT_SUNRISE , WT_SUNDOWN , WT_TIME  )
VALUES ('WL'||LPAD(to_char(SEQ_WT_LOCAL_NO.NEXTVAL), 5, '0'),'24' ,'23', '85',
'120','2019-08-12 05:24:31', '2019-08-12 18:24:31', CURRENT_TIMESTAMP);

INSERT INTO WEATHER_LOCAL (WT_LOCAL , WT_TEMP , WT_WATERTEMP , WT_RAINRATE , WT_WAVEHEIGHT , WT_SUNRISE , WT_SUNDOWN , WT_TIME  )
VALUES ('WL'||LPAD(to_char(SEQ_WT_LOCAL_NO.NEXTVAL), 5, '0'),'21' ,'22', '75',
'130','2019-08-13 05:24:31', '2019-08-13 18:24:31', CURRENT_TIMESTAMP);

INSERT INTO WEATHER_LOCAL (WT_LOCAL , WT_TEMP , WT_WATERTEMP , WT_RAINRATE , WT_WAVEHEIGHT , WT_SUNRISE , WT_SUNDOWN , WT_TIME  )
VALUES ('WL'||LPAD(to_char(SEQ_WT_LOCAL_NO.NEXTVAL), 5, '0'),'25' ,'24', '60',
'140','2019-08-14 05:24:31', '2019-08-14 18:24:31', CURRENT_TIMESTAMP);

INSERT INTO WEATHER_LOCAL (WT_LOCAL , WT_TEMP , WT_WATERTEMP , WT_RAINRATE , WT_WAVEHEIGHT , WT_SUNRISE , WT_SUNDOWN , WT_TIME  )
VALUES ('WL'||LPAD(to_char(SEQ_WT_LOCAL_NO.NEXTVAL), 5, '0'),'26' ,'24', '90',
'150','2019-08-15 05:24:31', '2019-08-15 18:24:31', CURRENT_TIMESTAMP);


-------------------------------WEATHER_AREA假資料---------------------------------
INSERT INTO WEATHER_AREA (WEATHER_AREA_NO , WT_LOCAL )
VALUES ('WA'||LPAD(to_char(SEQ_WEATHER_AREA_NO.NEXTVAL), 5, '0'),'WL00001');

INSERT INTO WEATHER_AREA (WEATHER_AREA_NO , WT_LOCAL )
VALUES ('WA'||LPAD(to_char(SEQ_WEATHER_AREA_NO.NEXTVAL), 5, '0'),'WL00002');

INSERT INTO WEATHER_AREA (WEATHER_AREA_NO , WT_LOCAL )
VALUES ('WA'||LPAD(to_char(SEQ_WEATHER_AREA_NO.NEXTVAL), 5, '0'),'WL00003');

INSERT INTO WEATHER_AREA (WEATHER_AREA_NO , WT_LOCAL )
VALUES ('WA'||LPAD(to_char(SEQ_WEATHER_AREA_NO.NEXTVAL), 5, '0'),'WL00004');

INSERT INTO WEATHER_AREA (WEATHER_AREA_NO , WT_LOCAL )
VALUES ('WA'||LPAD(to_char(SEQ_WEATHER_AREA_NO.NEXTVAL), 5, '0'),'WL00005');

--------------------------------------------DIVE假資料--------------------------------
INSERT INTO DIVE (DIVE_NO, WEATHER_AREA_NO, DIVE_NAME, DIVE_DES, DIVE_IMG, DIVE_STATUS, DIVE_LAT, DIVE_LANG )
VALUES ('D'||LPAD(to_char(SEQ_DIVE_NO.NEXTVAL), 6, '0'),'WA00001' ,'DiveName1', 'Beautifull Place',
null,'0', '125.0215648', '25.5646846');

INSERT INTO DIVE (DIVE_NO, WEATHER_AREA_NO, DIVE_NAME, DIVE_DES, DIVE_IMG, DIVE_STATUS, DIVE_LAT, DIVE_LANG )
VALUES ('D'||LPAD(to_char(SEQ_DIVE_NO.NEXTVAL), 6, '0'),'WA00002' ,'DiveName2', 'Beautifull Place',
null,'0', '125.0215648', '25.5646846');

INSERT INTO DIVE (DIVE_NO, WEATHER_AREA_NO, DIVE_NAME, DIVE_DES, DIVE_IMG, DIVE_STATUS, DIVE_LAT, DIVE_LANG )
VALUES ('D'||LPAD(to_char(SEQ_DIVE_NO.NEXTVAL), 6, '0'),'WA00003' ,'DiveName3', 'Beautifull Place',
null,'0', '125.0215648', '25.5646846');

INSERT INTO DIVE (DIVE_NO, WEATHER_AREA_NO, DIVE_NAME, DIVE_DES, DIVE_IMG, DIVE_STATUS, DIVE_LAT, DIVE_LANG )
VALUES ('D'||LPAD(to_char(SEQ_DIVE_NO.NEXTVAL), 6, '0'),'WA00004' ,'DiveName4', 'Beautifull Place',
null,'0', '125.0215648', '25.5646846');

INSERT INTO DIVE (DIVE_NO, WEATHER_AREA_NO, DIVE_NAME, DIVE_DES, DIVE_IMG, DIVE_STATUS, DIVE_LAT, DIVE_LANG )
VALUES ('D'||LPAD(to_char(SEQ_DIVE_NO.NEXTVAL), 6, '0'),'WA00005' ,'DiveName5', 'Beautifull Place',null,'0', '125.0215648', '25.5646846');

------------------------------------DIVEMESSAGE假資料----------------------------
INSERT INTO DIVEMESSAGE (DIVMEG_NO , DIVE_NO  , MEM_NO  , DIVMEG_TIME  , DIVMEG_NOTE )
VALUES ('DM'||LPAD(to_char(SEQ_DIVEMESSAGEL_NO.NEXTVAL), 5, '0'),'D000001' ,'M000001', '2008-08-15 18:24:31','FHISFHIUSEHIFHESGIGHAIHAWEGHLAEWGWGH');

INSERT INTO DIVEMESSAGE (DIVMEG_NO , DIVE_NO  , MEM_NO  , DIVMEG_TIME  , DIVMEG_NOTE )
VALUES ('DM'||LPAD(to_char(SEQ_DIVEMESSAGEL_NO.NEXTVAL), 5, '0'),'D000002' ,'M000002', '2008-08-15 18:24:31','FHISFHIUSEHIFHESGIGHAIHAWEGHLAEWGWGH');

INSERT INTO DIVEMESSAGE (DIVMEG_NO , DIVE_NO  , MEM_NO  , DIVMEG_TIME  , DIVMEG_NOTE )
VALUES ('DM'||LPAD(to_char(SEQ_DIVEMESSAGEL_NO.NEXTVAL), 5, '0'),'D000003' ,'M000001', '2008-08-15 18:24:31','FHISFHIUSEHIFHESGIGHAIHAWEGHLAEWGWGH');

INSERT INTO DIVEMESSAGE (DIVMEG_NO , DIVE_NO  , MEM_NO  , DIVMEG_TIME  , DIVMEG_NOTE )
VALUES ('DM'||LPAD(to_char(SEQ_DIVEMESSAGEL_NO.NEXTVAL), 5, '0'),'D000004' ,'M000001', '2008-08-15 18:24:31','FHISFHIUSEHIFHESGIGHAIHAWEGHLAEWGWGH');

INSERT INTO DIVEMESSAGE (DIVMEG_NO , DIVE_NO  , MEM_NO  , DIVMEG_TIME  , DIVMEG_NOTE )
VALUES ('DM'||LPAD(to_char(SEQ_DIVEMESSAGEL_NO.NEXTVAL), 5, '0'),'D000004' ,'M000001', '2008-08-15 18:24:31','FHISFHIUSEHIFHESGIGHAIHAWEGHLAEWGWGH');


/**************************************************揪團.揪團留言.報名揪團.揪團檢舉	**********************************************/

---------------------------------揪團SEQ----------------------------------------
CREATE SEQUENCE group_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-------------------------------揪團留言SEQ---------------------------------------
CREATE SEQUENCE group_me_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

---------------------------------揪團檢舉SEQ----------------------------------------
CREATE SEQUENCE group_re_seq
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--CREATE
----------------------------------------揪團---------------------------------------
CREATE TABLE DIVEGROUP(
 GROUP_NO               VARCHAR2(7) PRIMARY KEY NOT NULL,--揪團編號
 MEM_NO					VARCHAR2(7) NOT NULL,--會員編號
 DIVE_NO				VARCHAR2(7) NOT NULL,--潛點編號
 GROUP_NAME             VARCHAR2(200) NOT NULL,--揪團名稱
 GROUP_CT_TIME          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,--開團時間
 GROUP_TOUR_STOP_TIME   TIMESTAMP NOT NULL, --揪團報名截止日期
 GROUP_BEGIN_TIME       TIMESTAMP NOT NULL,--出發時間
 GROUP_END_TIME         TIMESTAMP NOT NULL,--結束時間
 GROUP_MODIFY_TIME      TIMESTAMP,--編輯時間
 GROUP_MAX_NUM          NUMBER(2) NOT NULL,--揪團上限人數
 GROUP_DES              CLOB NOT NULL,--揪團描述
 GROUP_STATUS           NUMBER(1) DEFAULT 0 NOT NULL CHECK(GROUP_STATUS <=3),--揪團狀態,0-未成團,1-已成團,2-團主取消,3-因被檢舉而下架
 GROUP_TEL              VARCHAR2(10) NOT NULL,--連絡電話
 GROUP_MP               VARCHAR2(200) NOT NULL,--集合地點
 GROUP_TOUR_NUM         NUMBER(2) DEFAULT 1 NOT NULL,--揪團參加人數(揪團主審核通過自動修改)			
 GROUP_PHOTO            BLOB, --揪團封面照片(之後要補上NOT NULL)
 CONSTRAINT DIVEGROUP_MEM_NO_FK FOREIGN KEY (MEM_NO) REFERENCES MEM (MEM_NO),
 CONSTRAINT DIVEGROUP_DIVE_NO_FK FOREIGN KEY (DIVE_NO) REFERENCES DIVE (DIVE_NO)
 );
 
 -----------------------------------------揪團留言--------------------------------
CREATE TABLE GROUP_MESSAGE(
 GROUP_ME_NO                VARCHAR2(7) PRIMARY KEY NOT NULL,--揪團留言編號
 GROUP_NO					VARCHAR2(7) NOT NULL,--揪團編號
 MEM_NO				        VARCHAR2(7) NOT NULL,--會員編號
 GROUP_ME_NOTE              CLOB NOT NULL,--留言內容
 GROUP_ME_TIME              TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,--留言時間
 CONSTRAINT GROUP_MESSAGE_GROUP_NO_FK FOREIGN KEY (GROUP_NO) REFERENCES DIVEGROUP (GROUP_NO),
 CONSTRAINT GROUP_MESSAGE_MEM_NO_FK FOREIGN KEY (MEM_NO) REFERENCES MEM (MEM_NO)
 ); 
 
 ----------------------------------------報名揪團----------------------------------
 CREATE TABLE GROUP_JOIN(
 GROUP_NO                   VARCHAR2(7) NOT NULL,--揪團編號
 MEM_NO                     VARCHAR2(7) NOT NULL,--會員編號
 GROUP_JO_DATE              TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,--報名日期
 GROUP_JO_STATUS            NUMBER(1) DEFAULT 0 NOT NULL CHECK(GROUP_JO_STATUS <= 2),--揪團報名狀態,0-未審核,1-已審核通過,2-已審核未通過
 GROUP_QR_STATUS            NUMBER(1) DEFAULT 0 NOT NULL CHECK(GROUP_QR_STATUS <= 1),--報到狀態(手機用),0-未報到,1-已報到
 PRIMARY KEY (GROUP_NO, MEM_NO),
 CONSTRAINT GROUP_JOIN_MEM_NO_FK
 FOREIGN KEY (MEM_NO) REFERENCES MEM (MEM_NO),
 CONSTRAINT GROUP_JOIN_GROUP_NO_FK 
 FOREIGN KEY (GROUP_NO) REFERENCES DIVEGROUP (GROUP_NO)
 );
 
 ----------------------------------------揪團檢舉------------------------------------
 CREATE TABLE GROUP_REPORT(
 GROUP_RE_NO                   VARCHAR2(7) PRIMARY KEY NOT NULL,--揪團檢舉編號
 GROUP_NO                      VARCHAR2(7) NOT NULL,--揪團編號
 MEM_NO                        VARCHAR2(7) NOT NULL,--會員編號
 GROUP_RE_NOTE                 CLOB NOT NULL,--檢舉原因
 GROUP_RE_TIME                 TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,--檢舉時間
 GROUP_RE_STATUS               NUMBER(1) DEFAULT 0 NOT NULL CHECK(GROUP_RE_STATUS <=1),--審核狀態,0-未審核,1-已審核
 CONSTRAINT GROUP_REPORT_GROUP_NO_FK FOREIGN KEY (GROUP_NO) REFERENCES DIVEGROUP (GROUP_NO),
 CONSTRAINT GROUP_REPORT_MEM_NO_FK FOREIGN KEY (MEM_NO) REFERENCES MEM (MEM_NO)
 );
 
 --INSERT
 -------------------------------------揪團假資料(尚未新增圖片)-----------------------------
INSERT INTO DIVEGROUP(GROUP_NO, MEM_NO, DIVE_NO, GROUP_NAME, GROUP_TOUR_STOP_TIME, GROUP_BEGIN_TIME, GROUP_END_TIME,
 GROUP_MODIFY_TIME, GROUP_MAX_NUM, GROUP_DES, GROUP_STATUS, GROUP_TEL, GROUP_MP, GROUP_TOUR_NUM)
 VALUES ('G'||LPAD(TO_CHAR(GROUP_SEQ.nextval),6,'0') , 'M000001' , 'D000001' , '澎湖潛水趣' , to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS') , to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS') ,
 to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS') , to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS'), 5, '一起潛水吧！', 0, '0988205866', '澎湖縣', 0);
 
 INSERT INTO DIVEGROUP(GROUP_NO, MEM_NO, DIVE_NO, GROUP_NAME, GROUP_TOUR_STOP_TIME, GROUP_BEGIN_TIME, GROUP_END_TIME,
 GROUP_MODIFY_TIME, GROUP_MAX_NUM, GROUP_DES, GROUP_STATUS, GROUP_TEL, GROUP_MP, GROUP_TOUR_NUM)
 VALUES ('G'||LPAD(TO_CHAR(GROUP_SEQ.nextval),6,'0') , 'M000002' , 'D000002' , '蝙蝠洞潛水趣' , to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS') , to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS') ,
 to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS') , to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS'), 5, '潛起來！', 0, '0988205866', '瑞芳', 0);
 
 INSERT INTO DIVEGROUP(GROUP_NO, MEM_NO, DIVE_NO, GROUP_NAME, GROUP_TOUR_STOP_TIME, GROUP_BEGIN_TIME, GROUP_END_TIME,
 GROUP_MODIFY_TIME, GROUP_MAX_NUM, GROUP_DES, GROUP_STATUS, GROUP_TEL, GROUP_MP, GROUP_TOUR_NUM)
 VALUES ('G'||LPAD(TO_CHAR(GROUP_SEQ.nextval),6,'0') , 'M000003' , 'D000003' , '小琉球潛水趣' , to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS') , to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS') ,
 to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS') , to_date('1988-08-13 20:10:10','yyyy-mm-dd HH24:MI:SS'), 5, '大家都進來吧！', 0, '0988205866', '小琉球', 0);
 
 -------------------------------------揪團留言假資料-------------------------------------
 INSERT INTO GROUP_MESSAGE(GROUP_ME_NO , GROUP_NO , MEM_NO , GROUP_ME_NOTE)
 VALUES ('GM'||LPAD(TO_CHAR(GROUP_ME_SEQ.nextval), 5,'0'),'G000001' , 'M000001' , '關東煮好吃'); 
 INSERT INTO GROUP_MESSAGE(GROUP_ME_NO , GROUP_NO , MEM_NO , GROUP_ME_NOTE)
 VALUES ('GM'||LPAD(TO_CHAR(GROUP_ME_SEQ.nextval), 5,'0'),'G000001' , 'M000002' , '關東煮好吃'); 
 INSERT INTO GROUP_MESSAGE(GROUP_ME_NO , GROUP_NO , MEM_NO , GROUP_ME_NOTE)
 VALUES ('GM'||LPAD(TO_CHAR(GROUP_ME_SEQ.nextval), 5,'0'),'G000001' , 'M000003' , '關東煮好吃');
 
 ------------------------------------報名揪團假資料----------------------------------
 INSERT INTO GROUP_JOIN(MEM_NO , GROUP_NO , GROUP_JO_STATUS , GROUP_QR_STATUS)
 VALUES ('M000001' , 'G000001' , '0' , '0');
 INSERT INTO GROUP_JOIN(MEM_NO , GROUP_NO , GROUP_JO_STATUS , GROUP_QR_STATUS)
 VALUES ('M000002' , 'G000002' , '0' , '0');
 INSERT INTO GROUP_JOIN(MEM_NO , GROUP_NO , GROUP_JO_STATUS , GROUP_QR_STATUS)
 VALUES ('M000003' , 'G000003' , '0' , '0');
 
------------------------------------------揪團檢舉假資料-----------------------------
 INSERT INTO GROUP_REPORT(GROUP_RE_NO , GROUP_NO , MEM_NO , GROUP_RE_NOTE , GROUP_RE_STATUS)
 VALUES ('GR'||LPAD(TO_CHAR(GROUP_RE_SEQ.nextval),5,'0') , 'G000001' , 'M000001' , '團主太帥', '0');
 INSERT INTO GROUP_REPORT(GROUP_RE_NO , GROUP_NO , MEM_NO , GROUP_RE_NOTE , GROUP_RE_STATUS)
 VALUES ('GR'||LPAD(TO_CHAR(GROUP_RE_SEQ.nextval),5,'0') , 'G000002' , 'M000002' , '人數太少', '0');
 INSERT INTO GROUP_REPORT(GROUP_RE_NO , GROUP_NO , MEM_NO , GROUP_RE_NOTE , GROUP_RE_STATUS)
 VALUES ('GR'||LPAD(TO_CHAR(GROUP_RE_SEQ.nextval),5,'0') , 'G000003' , 'M000003' , '缺東缺西', '0');
 
 
 /**************************************************行程.行程訂單.行程檢舉.追蹤行程.行程相片.行程房間詳情.房間相片**********************************************/

-------------------------------------------行程-------------------------------
CREATE TABLE TOUR(
    TOUR_NO VARCHAR2(7) NOT NULL,
    CONSTRAINT PK_TOUR_NO PRIMARY KEY (TOUR_NO),
    MEM_NO VARCHAR2(7),
    CONSTRAINT FK_TOUR_MEM_NO
    FOREIGN KEY (MEM_NO) REFERENCES MEM(MEM_NO),
    TOUR_NAME VARCHAR2(20) NOT NULL,
    TOUR_BGN_DATE DATE NOT NULL,
    TOUR_END_DATE DATE NOT NULL,
    TOUR_PRICE NUMBER(8) CHECK (TOUR_PRICE > 0) NOT NULL,
    TOUR_MAX_NUM NUMBER(8) NOT NULL,
    TOUR_PLACE VARCHAR2(50) NOT NULL,
    TOUR_DIVES NUMBER(8) NOT NULL,
    TOUR_STOP_DATE DATE NOT NULL,
    TOUR_STATUS NUMBER(1) NOT NULL,
    TOUR_NUM NUMBER(8) DEFAULT 0,
    TOUR_REV_AVG NUMBER(2,1) DEFAULT 0
);

CREATE SEQUENCE TOUR_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

------------------------------新增行程資料(MEM_NO店家會員)-----------------------------
INSERT INTO TOUR(TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, 
TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE,
TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG)
VALUES('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), 'M000007', '小琉球之旅', 
to_date('2020-03-19', 'yyyy-mm-dd'), to_date('2020-03-21', 'yyyy-mm-dd'), '55000', '10', '小琉球',
'7', to_date('2020-03-17', 'yyyy-mm-dd'), '1', '0', '3.5');

INSERT INTO TOUR(TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, 
TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE,
TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG)
VALUES('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), 'M000007', '小琉球之旅2', 
to_date('2020-02-19', 'yyyy-mm-dd'), to_date('2020-02-21', 'yyyy-mm-dd'), '55000', '10', '小琉球',
'7', to_date('2020-02-17', 'yyyy-mm-dd'), '0', '0', '3.5');

INSERT INTO TOUR(TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, 
TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE,
TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG)
VALUES('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), 'M000007', '墾丁之旅', 
to_date('2020-02-18', 'yyyy-mm-dd'), to_date('2020-02-21', 'yyyy-mm-dd'), '65000', '6', '墾丁',
'9', to_date('2020-02-16', 'yyyy-mm-dd'), '1', '5', '5.0');

INSERT INTO TOUR(TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, 
TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE,
TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG)
VALUES('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), 'M000008', '綠島之旅', 
to_date('2020-05-01', 'yyyy-mm-dd'), to_date('2020-05-05', 'yyyy-mm-dd'), '85000', '6', '綠島',
'12', to_date('2020-04-28', 'yyyy-mm-dd'), '0', '6', '4.3');

INSERT INTO TOUR(TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, 
TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE,
TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG)
VALUES('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), 'M000008', '蘭嶼之旅2', 
to_date('2020-04-01', 'yyyy-mm-dd'), to_date('2020-04-05', 'yyyy-mm-dd'), '85000', '6', '蘭嶼',
'12', to_date('2020-03-22', 'yyyy-mm-dd'), '1', '6', '4.3');

INSERT INTO TOUR(TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, 
TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE,
TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG)
VALUES('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), 'M000008', '蘭嶼之旅', 
to_date('2020-05-01', 'yyyy-mm-dd'), to_date('2020-05-05', 'yyyy-mm-dd'), '85000', '6', '蘭嶼',
'12', to_date('2020-04-28', 'yyyy-mm-dd'), '1', '6', '3.6');

INSERT INTO TOUR(TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, 
TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE,
TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG)
VALUES('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), 'M000009', '北海岸之旅1', 
to_date('2020-04-03', 'yyyy-mm-dd'), to_date('2020-04-05', 'yyyy-mm-dd'), '75000', '12', '北海岸',
'5', to_date('2020-03-30', 'yyyy-mm-dd'), '1', '3', '4.2');

INSERT INTO TOUR(TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, 
TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE,
TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG)
VALUES('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), 'M000009', '墾丁之旅2', 
to_date('2020-03-03', 'yyyy-mm-dd'), to_date('2020-03-05', 'yyyy-mm-dd'), '45000', '12', '墾丁',
'5', to_date('2020-02-29', 'yyyy-mm-dd'), '0', '3', '4.2');

INSERT INTO TOUR(TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, 
TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE,
TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG)
VALUES('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), 'M000010', '綠島之旅2', 
to_date('2020-03-03', 'yyyy-mm-dd'), to_date('2020-03-05', 'yyyy-mm-dd'), '45000', '12', '綠島',
'5', to_date('2020-02-29', 'yyyy-mm-dd'), '1', '3', '4.2');

INSERT INTO TOUR(TOUR_NO, MEM_NO, TOUR_NAME, TOUR_BGN_DATE, 
TOUR_END_DATE, TOUR_PRICE, TOUR_MAX_NUM, TOUR_PLACE, TOUR_DIVES, TOUR_STOP_DATE,
TOUR_STATUS, TOUR_NUM, TOUR_REV_AVG)
VALUES('T'||LPAD(TO_CHAR(TOUR_SEQ.NEXTVAL), 6, '0'), 'M000009', '靠北海岸之旅', 
to_date('2020-04-18', 'yyyy-mm-dd'), to_date('2020-04-22', 'yyyy-mm-dd'), '95000', '10', '北海岸',
'18', to_date('2020-04-15', 'yyyy-mm-dd'), '1', '8', '2.5');

-------------------------------------------------行程訂單--------------------------------------------
CREATE TABLE TOUR_ORDER(
    TOUR_ORDER_NO VARCHAR2(10),
    CONSTRAINT PK_TOUR_ORDER PRIMARY KEY (TOUR_ORDER_NO),
    TOUR_NO VARCHAR2(7),
    CONSTRAINT FK_TOUR_ORDER_TOUR_NO
    FOREIGN KEY (TOUR_NO) REFERENCES TOUR(TOUR_NO),
    MEM_NO VARCHAR2(7) NOT NULL,
    CONSTRAINT FK_TOUR_ORDER_MEM_NO
    FOREIGN KEY (MEM_NO) REFERENCES MEM(MEM_NO),
    TOUR_ORDER_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    TOUR_ORDER_STATUS NUMBER(10) DEFAULT 0 NOT NULL,
    TTL_PRICE NUMBER(10) CHECK (TTL_PRICE > 0) NOT NULL,
    TOUR_REV_NOTE VARCHAR2(200),
    TOUR_REV NUMBER(1)
);

CREATE SEQUENCE TOUR_ORDER_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--------------------------------------新增行程訂單資料---------------------------------
INSERT INTO TOUR_ORDER(TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_STATUS, TTL_PRICE, TOUR_REV_NOTE, TOUR_REV)
VALUES('TO'||LPAD(TO_CHAR(TOUR_ORDER_SEQ.NEXTVAL), 6, '0'), 'T000001', 'M000001', '1', '55000', '棒棒棒', '4');

INSERT INTO TOUR_ORDER(TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_STATUS, TTL_PRICE)
VALUES('TO'||LPAD(TO_CHAR(TOUR_ORDER_SEQ.NEXTVAL), 6, '0'), 'T000002', 'M000002', '1', '65000');

INSERT INTO TOUR_ORDER(TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_STATUS, TTL_PRICE)
VALUES('TO'||LPAD(TO_CHAR(TOUR_ORDER_SEQ.NEXTVAL), 6, '0'), 'T000003', 'M000003', '1', '85000');

INSERT INTO TOUR_ORDER(TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_STATUS, TTL_PRICE, TOUR_REV_NOTE, TOUR_REV)
VALUES('TO'||LPAD(TO_CHAR(TOUR_ORDER_SEQ.NEXTVAL), 6, '0'), 'T000002', 'M000003', '1', '65000', 'GOODGOOD', '5');

INSERT INTO TOUR_ORDER(TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_STATUS, TTL_PRICE)
VALUES('TO'||LPAD(TO_CHAR(TOUR_ORDER_SEQ.NEXTVAL), 6, '0'), 'T000006', 'M000005', '1', '95000');

INSERT INTO TOUR_ORDER(TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_STATUS, TTL_PRICE, TOUR_REV_NOTE, TOUR_REV)
VALUES('TO'||LPAD(TO_CHAR(TOUR_ORDER_SEQ.NEXTVAL), 6, '0'), 'T000004', 'M000001', '1', '85000', '很糟糕', '2');

INSERT INTO TOUR_ORDER(TOUR_ORDER_NO, TOUR_NO, MEM_NO, TOUR_ORDER_STATUS, TTL_PRICE)
VALUES('TO'||LPAD(TO_CHAR(TOUR_ORDER_SEQ.NEXTVAL), 6, '0'), 'T000005', 'M000004', '1', '75000');

---------------------------------------------行程檢舉-----------------------------------------------
CREATE TABLE TOUR_REPORT(
    TOUR_RE_NO VARCHAR2(10),
    CONSTRAINT PK_TOUR_RE_NO PRIMARY KEY (TOUR_RE_NO),
    TOUR_NO VARCHAR2(7) NOT NULL,
    CONSTRAINT FK_TOUR_REPORT_TOUR_NO
    FOREIGN KEY (TOUR_NO) REFERENCES TOUR(TOUR_NO),
    MEM_NO VARCHAR2(7) NOT NULL,
    CONSTRAINT FK_TOUR_REPORT_MEM_NO
    FOREIGN KEY (MEM_NO) REFERENCES MEM(MEM_NO),
    TOUR_RE_NOTE VARCHAR2(200) NOT NULL,
    TOUR_RE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    TOUR_RE_STATUS NUMBER(1) DEFAULT 0 NOT NULL
);

CREATE SEQUENCE TOUR_REPORT_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-------------------------------------------新增行程檢舉資料---------------------------------------
INSERT INTO TOUR_REPORT (TOUR_RE_NO, TOUR_NO, MEM_NO, TOUR_RE_NOTE, TOUR_RE_STATUS) 
VALUES ('TR'||LPAD(TO_CHAR(TOUR_REPORT_SEQ.NEXTVAL), 6, '0'), 'T000004', 'M000001', '哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈', '0');

INSERT INTO TOUR_REPORT (TOUR_RE_NO, TOUR_NO, MEM_NO, TOUR_RE_NOTE, TOUR_RE_STATUS) 
VALUES ('TR'||LPAD(TO_CHAR(TOUR_REPORT_SEQ.NEXTVAL), 6, '0'), 'T000002', 'M000002', '兮兮兮兮兮兮兮兮兮兮兮兮兮兮兮兮兮兮兮兮兮', '1');

INSERT INTO TOUR_REPORT (TOUR_RE_NO, TOUR_NO, MEM_NO, TOUR_RE_NOTE, TOUR_RE_STATUS) 
VALUES ('TR'||LPAD(TO_CHAR(TOUR_REPORT_SEQ.NEXTVAL), 6, '0'), 'T000005', 'M000004', 'ㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎㄎ', '1');

INSERT INTO TOUR_REPORT (TOUR_RE_NO, TOUR_NO, MEM_NO, TOUR_RE_NOTE, TOUR_RE_STATUS) 
VALUES ('TR'||LPAD(TO_CHAR(TOUR_REPORT_SEQ.NEXTVAL), 6, '0'), 'T000001', 'M000001', 'ㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅㄅ', '0');

--------------------------------------------------追蹤行程---------------------------------------
CREATE TABLE TOUR_LIKE(
    TOUR_NO VARCHAR2(7),
    MEM_NO VARCHAR2(7),
    CONSTRAINT PK_TOUR_LIKE PRIMARY KEY (TOUR_NO, MEM_NO),
    CONSTRAINT FK_TOUR_LIKE_TOUR_NO FOREIGN KEY (TOUR_NO) REFERENCES TOUR(TOUR_NO),
    CONSTRAINT FK_TOUR_LIKE_MEM_NO FOREIGN KEY (MEM_NO) REFERENCES MEM(MEM_NO)
);

-------------------------------------------------新增追蹤行程-----------------------------------
INSERT INTO TOUR_LIKE (TOUR_NO, MEM_NO) VALUES ('T000001', 'M000006');
INSERT INTO TOUR_LIKE (TOUR_NO, MEM_NO) VALUES ('T000002', 'M000006');
INSERT INTO TOUR_LIKE (TOUR_NO, MEM_NO) VALUES ('T000003', 'M000001');
INSERT INTO TOUR_LIKE (TOUR_NO, MEM_NO) VALUES ('T000004', 'M000003');
INSERT INTO TOUR_LIKE (TOUR_NO, MEM_NO) VALUES ('T000003', 'M000003');
INSERT INTO TOUR_LIKE (TOUR_NO, MEM_NO) VALUES ('T000002', 'M000003');
INSERT INTO TOUR_LIKE (TOUR_NO, MEM_NO) VALUES ('T000008', 'M000003');
INSERT INTO TOUR_LIKE (TOUR_NO, MEM_NO) VALUES ('T000010', 'M000003');

-----------------------------------------------行程相片--------------------------------------
CREATE TABLE TOUR_IMAGE(
    TOUR_IMG_NO VARCHAR2(10),
    CONSTRAINT PK_TOUR_IMG_NO PRIMARY KEY (TOUR_IMG_NO), 
    TOUR_NO VARCHAR2(7) NOT NULL,
    CONSTRAINT FK_TOUR_IMAGE_TOUR_NO
    FOREIGN KEY (TOUR_NO) REFERENCES TOUR(TOUR_NO),
    TOUR_IMG BLOB
);

CREATE SEQUENCE TOUR_IMAGE_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-----------------------------------------新增行程相片-------------------------------------
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000001');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000001');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000001');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000002');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000002');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000002');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000003');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000003');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000003');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000004');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000004');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000005');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000005');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000006');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000006');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000006');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000007');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000007');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000008');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000008');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000009');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000009');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000009');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000009');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000010');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000010');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000010');
INSERT INTO TOUR_IMAGE (TOUR_IMG_NO, TOUR_NO) VALUES ('TI'||LPAD(TO_CHAR(TOUR_IMAGE_SEQ.NEXTVAL), 6, '0'), 'T000010');

---------------------------------------行程房間詳情-------------------------------------------
CREATE TABLE TOUR_ROOM(
    TOUR_ROOM_NO VARCHAR2(10),
    CONSTRAINT PK_TOUR_ROOM_NO PRIMARY KEY (TOUR_ROOM_NO),
    TOUR_NO VARCHAR2(7) NOT NULL,
    CONSTRAINT FK_TOUR_ROOM_TOUR_NO
    FOREIGN KEY (TOUR_NO) REFERENCES TOUR(TOUR_NO),
    BED_SIZE NUMBER(1) DEFAULT 0,
    ROOM_PPL NUMBER(2),
    ROOM_PRIV_BR NUMBER(1) DEFAULT 0,
    ROOM_AIRCON NUMBER(1) DEFAULT 0
);

CREATE SEQUENCE TOUR_ROOM_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

---------------------------------新增行程房間詳情--------------------------------------
INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) 
VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), 'T000001', '0', '4', '1', '1');
INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) 
VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), 'T000002', '0', '2', '0', '1');
INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) 
VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), 'T000003', '0', '1', '0', '1');
INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) 
VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), 'T000004', '1', '4', '1', '0');
INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) 
VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), 'T000005', '1', '6', '1', '1');
INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) 
VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), 'T000006', '0', '2', '1', '1');
INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) 
VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), 'T000007', '0', '2', '1', '1');
INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) 
VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), 'T000008', '0', '2', '1', '1');
INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) 
VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), 'T000009', '0', '2', '1', '1');
INSERT INTO TOUR_ROOM (TOUR_ROOM_NO, TOUR_NO, BED_SIZE, ROOM_PPL, ROOM_PRIV_BR, ROOM_AIRCON) 
VALUES ('TRM'||LPAD(TO_CHAR(TOUR_ROOM_SEQ.NEXTVAL), 6, '0'), 'T000010', '0', '2', '1', '1');

-------------------------------------------房間相片-------------------------------
CREATE TABLE ROOM_IMAGE(
    ROOM_IMG_NO VARCHAR2(20),
    CONSTRAINT PK_ROOM_IMG_NO PRIMARY KEY (ROOM_IMG_NO),
    TOUR_ROOM_NO VARCHAR2(10) NOT NULL,
    CONSTRAINT FK_ROOM_IMAGE_TOUR_ROOM_NO
    FOREIGN KEY (TOUR_ROOM_NO) REFERENCES TOUR_ROOM(TOUR_ROOM_NO),
    ROOM_IMG BLOB -------修改12/08------
);

CREATE SEQUENCE ROOM_IMAGE_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

--------------------------------------新增行程房間相片--------------------------------
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000001');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000001');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000002');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000002');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000003');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000003');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000004');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000004');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000005');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000005');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000006');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000006');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000007');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000007');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000008');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000008');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000009');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000009');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000010');
INSERT INTO ROOM_IMAGE (ROOM_IMG_NO, TOUR_ROOM_NO) VALUES ('RI'||LPAD(TO_CHAR(ROOM_IMAGE_SEQ.NEXTVAL), 6, '0'), 'TRM000010');

 /**************************************************論壇文章.論壇留言.文章檢舉**********************************************/

-------------------------------------------論壇文章-------------------------------
CREATE TABLE FORUM
(
POST_NO     VARCHAR2(7) NOT NULL,
MEM_NO      VARCHAR2(7) NOT NULL,
POST_TITLE  VARCHAR2(65) NOT NULL,
POST_TIME   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
POST_CONT   CLOB NOT NULL,
POST_STATUS NUMBER(1) NOT NULL,
CONSTRAINT FORUM_MEM_NO_FK FOREIGN KEY (MEM_NO) REFERENCES MEM (MEM_NO),
CONSTRAINT FORUM_PRIMARY_KEY PRIMARY KEY (POST_NO)
);

----------------------------論壇文章SEQ---------------
CREATE SEQUENCE forum_seq
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;
 
 -------------------------------------論壇假資料 5筆------------------------
 INSERT INTO FORUM (POST_NO,MEM_NO,POST_TITLE,POST_TIME,POST_CONT,POST_STATUS)VALUES
 ('PT'||LPAD(to_char(forum_seq.nextval),5,'0'),'M000001','第一篇文章Title-M000001會員發布','2019-12-01 21:12:22','第一篇文章Context-M000001會員發布',0);
  INSERT INTO FORUM (POST_NO,MEM_NO,POST_TITLE,POST_TIME,POST_CONT,POST_STATUS)VALUES
 ('PT'||LPAD(to_char(forum_seq.nextval),5,'0'),'M000002','第二篇文章Title-M000002會員發布','2019-12-02 21:12:22','第二篇文章Context-M000002會員發布',0);
  INSERT INTO FORUM (POST_NO,MEM_NO,POST_TITLE,POST_TIME,POST_CONT,POST_STATUS)VALUES
 ('PT'||LPAD(to_char(forum_seq.nextval),5,'0'),'M000003','第三篇文章Title-M000003會員發布','2019-12-03 21:12:22','第三篇文章Context-M000003會員發布',0);
  INSERT INTO FORUM (POST_NO,MEM_NO,POST_TITLE,POST_TIME,POST_CONT,POST_STATUS)VALUES
 ('PT'||LPAD(to_char(forum_seq.nextval),5,'0'),'M000004','第四篇文章Title-M000004會員發布','2019-12-04 21:12:22','第四篇文章Context-M000004會員發布',0);
  INSERT INTO FORUM (POST_NO,MEM_NO,POST_TITLE,POST_TIME,POST_CONT,POST_STATUS)VALUES
 ('PT'||LPAD(to_char(forum_seq.nextval),5,'0'),'M000005','第五篇文章Title-M000005會員發布','2019-12-05 21:12:22','第五篇文章Context-M000005會員發布',0);

----------------------------論壇留言-------------------------------
CREATE TABLE POST_REPLY
(
REPLY_NO    VARCHAR2(7) NOT NULL,
MEM_NO      VARCHAR2(7) NOT NULL,
POST_NO     VARCHAR2(7) NOT NULL,
REPLY_TIME  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
REPLY_CONT  CLOB NOT NULL,
CONSTRAINT POST_REPLY_MEM_NO_FK FOREIGN KEY (MEM_NO) REFERENCES MEM (MEM_NO),
CONSTRAINT POST_REPLY_POST_NO_FK FOREIGN KEY (POST_NO) REFERENCES FORUM (POST_NO),
CONSTRAINT POST_REPLY_PRIMARY_KEY PRIMARY KEY (REPLY_NO)
);

-------------------------------論壇留言SEQ-------------------
CREATE SEQUENCE post_reply_seq
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

---------------------------------論壇留言假資料 5筆-------------------------
 INSERT INTO POST_REPLY (REPLY_NO,MEM_NO,POST_NO,REPLY_TIME,REPLY_CONT)VALUES
 ('PR'||LPAD(to_char(post_reply_seq.nextval),5,'0'),'M000001','PT00001','2019-12-01 21:31:22','第一篇回文Context-M000001會員發布');
  INSERT INTO POST_REPLY (REPLY_NO,MEM_NO,POST_NO,REPLY_TIME,REPLY_CONT)VALUES
 ('PR'||LPAD(to_char(post_reply_seq.nextval),5,'0'),'M000002','PT00002','2019-12-01 21:32:22','第二篇回文Context-M000002會員發布');
  INSERT INTO POST_REPLY (REPLY_NO,MEM_NO,POST_NO,REPLY_TIME,REPLY_CONT)VALUES
 ('PR'||LPAD(to_char(post_reply_seq.nextval),5,'0'),'M000003','PT00003','2019-12-01 21:33:22','第三篇回文Context-M000003會員發布');
  INSERT INTO POST_REPLY (REPLY_NO,MEM_NO,POST_NO,REPLY_TIME,REPLY_CONT)VALUES
 ('PR'||LPAD(to_char(post_reply_seq.nextval),5,'0'),'M000004','PT00004','2019-12-01 21:34:22','第四篇回文Context-M000004會員發布');
  INSERT INTO POST_REPLY (REPLY_NO,MEM_NO,POST_NO,REPLY_TIME,REPLY_CONT)VALUES
 ('PR'||LPAD(to_char(post_reply_seq.nextval),5,'0'),'M000005','PT00005','2019-12-01 21:35:22','第五篇回文Context-M000005會員發布');

-----------------------------文章檢舉----------------------------------------
 CREATE TABLE POST_REPORT
( 
POST_RE_NO       VARCHAR2(7) NOT NULL,
MEM_NO           VARCHAR2(7) NOT NULL,
POST_NO          VARCHAR2(7) NOT NULL,
POST_RE_TIME     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
POST_RE_NOTE     VARCHAR2(200) NOT NULL,
POST_RE_STATUS   NUMBER(1) NOT NULL,   
CONSTRAINT POST_REPORT_MEM_NO_FK FOREIGN KEY (MEM_NO) REFERENCES MEM (MEM_NO),
CONSTRAINT POST_REPORT_POST_NO_FK FOREIGN KEY (POST_NO) REFERENCES FORUM (POST_NO),
CONSTRAINT POST_REPORT_PRIMARY_KEY PRIMARY KEY (POST_RE_NO)
);
 
---------------------文章檢舉SEQ-------------------------------
CREATE SEQUENCE post_re_seq
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCYCLE
NOCACHE;

-------------------------------論壇文章檢舉假資料 5筆-----------------------
 INSERT INTO POST_REPORT (POST_RE_NO,MEM_NO,POST_NO,POST_RE_TIME,POST_RE_NOTE,POST_RE_STATUS)VALUES
 ('PRN'||LPAD(to_char(post_re_seq.nextval),4,'0'),'M000001','PT00001','2019-12-01 21:41:22','第一篇檢舉Context-M000001會員發布',0);
  INSERT INTO POST_REPORT (POST_RE_NO,MEM_NO,POST_NO,POST_RE_TIME,POST_RE_NOTE,POST_RE_STATUS)VALUES
 ('PRN'||LPAD(to_char(post_re_seq.nextval),4,'0'),'M000001','PT00001','2019-12-01 21:41:22','第一篇檢舉Context-M000001會員發布',0);
  INSERT INTO POST_REPORT (POST_RE_NO,MEM_NO,POST_NO,POST_RE_TIME,POST_RE_NOTE,POST_RE_STATUS)VALUES
 ('PRN'||LPAD(to_char(post_re_seq.nextval),4,'0'),'M000001','PT00001','2019-12-01 21:41:22','第一篇檢舉Context-M000001會員發布',0);
  INSERT INTO POST_REPORT (POST_RE_NO,MEM_NO,POST_NO,POST_RE_TIME,POST_RE_NOTE,POST_RE_STATUS)VALUES
 ('PRN'||LPAD(to_char(post_re_seq.nextval),4,'0'),'M000001','PT00001','2019-12-01 21:41:22','第一篇檢舉Context-M000001會員發布',0);
  INSERT INTO POST_REPORT (POST_RE_NO,MEM_NO,POST_NO,POST_RE_TIME,POST_RE_NOTE,POST_RE_STATUS)VALUES
 ('PRN'||LPAD(to_char(post_re_seq.nextval),4,'0'),'M000001','PT00001','2019-12-01 21:41:22','第一篇檢舉Context-M000001會員發布',0);

commit;