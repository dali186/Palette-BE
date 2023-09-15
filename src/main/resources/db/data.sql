
INSERT INTO building(name) VALUES('미왕빌딩'),('그랑서울'),('프롭테크빌리지')

INSERT INTO tenant(name, building_id) VALUES('스타트23', '1')
INSERT INTO tenant(name, building_id) VALUES('스타트23', '2')
INSERT INTO tenant(name, building_id) VALUES('스타트23', '3')
INSERT INTO tenant(name, building_id) VALUES('아무건설', '1')
INSERT INTO tenant(name, building_id) VALUES('아무건설', '2')
INSERT INTO tenant(name, building_id) VALUES('아무건설', '3')
INSERT INTO tenant(name, building_id) VALUES('법무', '1')
INSERT INTO tenant(name, building_id) VALUES('법무', '2')
INSERT INTO tenant(name, building_id) VALUES('법무', '3')




INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id) VALUES('201호', 2 ,'A동', 1, 1 )
INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id) VALUES('302호', 3 ,'A동', 2, 2 )
INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id) VALUES('507호', 5 ,'B동', 5, 2 )
INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id) VALUES('102호', 1 ,'C동', 7, 3 )


-- INSERT INTO member (email, password, name, nickname, image, phone_number, bio, birthday, sex, position, job, created_at, updated_at, room_id, building_id, tenant_id )
-- VALUES ('yohan@gmail.com', 'abc1234#', '김요한', '요한이', NULL, '01020003000', '안녕하세요', '2000-02-14', '남성', '사원', '디자인', now(), now(), 1, 1, 1)
