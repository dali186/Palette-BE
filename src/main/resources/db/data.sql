
INSERT INTO building(name) VALUES('미왕빌딩'),('그랑서울'),('프롭테크빌리지');

INSERT INTO tenant(name, building_id) VALUES('스타트23', '1');
INSERT INTO tenant(name, building_id) VALUES('스타트23', '2');
INSERT INTO tenant(name, building_id) VALUES('스타트23', '3');
INSERT INTO tenant(name, building_id) VALUES('아무건설', '1');
INSERT INTO tenant(name, building_id) VALUES('아무건설', '2');
INSERT INTO tenant(name, building_id) VALUES('아무건설', '3');
INSERT INTO tenant(name, building_id) VALUES('법무', '1');
INSERT INTO tenant(name, building_id) VALUES('법무', '2');
INSERT INTO tenant(name, building_id) VALUES('법무', '3');




INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id) VALUES('201호', 2 ,'A동', 1, 1 );
INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id) VALUES('302호', 3 ,'A동', 2, 2 );
INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id) VALUES('507호', 5 ,'B동', 5, 2 );
INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id) VALUES('102호', 1 ,'C동', 7, 3 );


INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('yohan@gmail.com', 'abc1234#', '김요한', '요한이', '01020003000', '안녕하세요', '20000214', 'MALE', 'EMPLOYEE', 'DESIGN', now(), now(), 1, 1, 1);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('watcha@naver.com', 'abc5678#', '김사원', '사원킴', '01011113117', '안녕하세요', '20000714', 'FEMALE', 'ASSISTANTMANAGER', 'BUSINESS', now(), now(), 2, 2 , 2);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('yumi@daum.net', 'def1234#', '이유미', '유미', '01021113123', '하이하이', '19970214', 'FEMALE', 'SENIORMANAGER', 'HR', now(), now(), 4, 3, 7);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('naza@daum.net', 'def5678#', '우철수', '철수', '01022376025', '법률쪽맡은사람입니다.', '19921225', 'MALE', 'GENERALMANAGER', 'LAW', now(), now(), 3, 2, 5);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('work99@gmail.com', 'ghi1234*', '도배만', '도베르만', '01033328888', '안녕하세요', '19930707', 'MALE', 'ASSISTANTMANAGER', 'RESEARCH', now(), now(), 1, 1, 1 );
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('pakka9@gmail.com', 'ghi5678*', '한아름', NULL, NULL, NULL, NULL, NULL, NULL, NULL, now(), now(), 2, 2, 2 );
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('jigubon9@gmail.com', 'a123477*', '지구본', NULL, NULL, NULL, NULL, NULL, NULL, NULL, now(), now(), 4, 3, 7 );
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('bakgu@gmail.com', 'b56789*', '백구식', NULL, NULL, NULL, NULL, NULL, NULL, NULL, now(), now(), 3, 2, 5 );
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('host77@gmail.com', 'gh778#', '유호식', NULL, NULL, NULL, NULL, NULL, NULL, NULL, now(), now(), 1, 1, 1 );
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('dklss8@gmail.com', 'gi9872*', '남궁민', NULL, NULL, NULL, NULL, NULL, NULL, NULL, now(), now(), 2, 2, 2 );
