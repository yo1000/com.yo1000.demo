CREATE TABLE NOTE (
  NOTE_ID IDENTITY,
  TITLE   VARCHAR(40)   NOT NULL,
  NOTE    VARCHAR(200)          ,
  CREATED DATETIME      NOT NULL,
  PRIMARY KEY (NOTE_ID)
);

INSERT INTO NOTE (TITLE , NOTE  , CREATED) VALUES ('ももたろう'     , 'だんごで　どうぶつを　つる', '2016-07-25 15:48:00.0');
INSERT INTO NOTE (TITLE , NOTE  , CREATED) VALUES ('きんたろう'     , 'すもうで　くまを　なげる', '2016-07-25 15:48:00.0');
INSERT INTO NOTE (TITLE , NOTE  , CREATED) VALUES ('うらしまたろう' , 'じじいに　なる', '2016-07-25 15:48:00.0');
INSERT INTO NOTE (TITLE , NOTE  , CREATED) VALUES ('ちからたろう'   , 'きったない', '2016-07-25 15:48:00.0');
INSERT INTO NOTE (TITLE         , CREATED) VALUES ('なるたろう'    , '2016-07-25 15:48:00.0');

