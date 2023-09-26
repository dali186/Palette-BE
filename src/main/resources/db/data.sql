
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

-- password : abc1234#
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('yohan@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '김요한', '요한이', '01020003000', '안녕하세요', '20000214', 'MALE', 'EMPLOYEE', 'DESIGN', now(), now(), 1, 1, 1);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('watcha@naver.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '김사원', '사원킴', '01011113117', '안녕하세요', '20000714', 'FEMALE', 'ASSISTANTMANAGER', 'BUSINESS', now(), now(), 2, 2 , 2);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('yumi@daum.net', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '이유미', '유미', '01021113123', '하이하이', '19970214', 'FEMALE', 'SENIORMANAGER', 'HR', now(), now(), 4, 3, 7);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('naza@daum.net', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '우철수', '철수', '01022376025', '법률쪽맡은사람입니다.', '19921225', 'MALE', 'GENERALMANAGER', 'LAW', now(), now(), 3, 2, 5);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('work99@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '도배만', '도베르만', '01033328888', '안녕하세요', '19930707', 'MALE', 'ASSISTANTMANAGER', 'RESEARCH', now(), now(), 1, 1, 1 );
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('pakka9@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '한아름', NULL, NULL, NULL, NULL, NULL, NULL, NULL, now(), now(), 2, 2, 2 );
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('jigubon9@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '지구본', NULL, NULL, NULL, NULL, NULL, NULL, NULL, now(), now(), 4, 3, 7 );
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('bakgu@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '백구식', NULL, NULL, NULL, NULL, NULL, NULL, NULL, now(), now(), 3, 2, 5 );
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('host77@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '유호식', NULL, NULL, NULL, NULL, NULL, NULL, NULL, now(), now(), 1, 1, 1 );
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at, updated_at, office_room_id, building_id, tenant_id )
VALUES('dklss8@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '남궁민', NULL, NULL, NULL, NULL, NULL, NULL, NULL, now(), now(), 2, 2, 2 );


INSERT INTO meeting (member_id, category, type, sex, title, description, head_count, start_date, end_date, on_off, place, week, time, progress_time, accept_type, is_closing, hits, likes, recruited_personnel, created_at)
VALUES
    (1, 'CAREER', 'STUDY', 'FEMALE', '제목', '성장해요', 20, '2023-10-01', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 10, 20, 0, now()),
    (1, 'CAREER', 'PROJECT', 'FEMALE', '제목', '성장해요', 20, '2023-10-02', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 20, 10, 0, now()),
    (1, 'CAREER', 'AMITY', 'FEMALE', '제목', '성장해요', 20, '2023-10-03', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 110, 40, 0, now()),
    (1, 'CAREER', 'STUDY', 'MALE', '제목', '성장해요', 20, '2023-10-04', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 14, 15, 0, now()),
    (1, 'CAREER', 'PROJECT', 'MALE', '제목', '성장해요', 20, '2023-10-05', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 15, 17, 0, now()),
    (1, 'CAREER', 'AMITY', 'MALE', '제목', '성장해요', 20, '2023-10-06', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 16, 19, 0, now()),
    (1, 'CAREER', 'STUDY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-07', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 17, 20, 0, now()),
    (1, 'CAREER', 'PROJECT', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-08', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 10, 50, 0, now()),
    (1, 'CAREER', 'AMITY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-09', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 20, 60, 0, now()),
    (1, 'CAREER', 'STUDY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-10', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 30, 120, 0, now()),
    (2, 'CAREER', 'STUDY', 'FEMALE', '제목', '성장해요', 20, '2023-10-01', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 40, 80, 0, now()),
    (2, 'CAREER', 'PROJECT', 'FEMALE', '제목', '성장해요', 20, '2023-10-02', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 50, 90, 0, now()),
    (2, 'CAREER', 'AMITY', 'FEMALE', '제목', '성장해요', 20, '2023-10-03', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 10, 10, 0, now()),
    (2, 'CAREER', 'STUDY', 'MALE', '제목', '성장해요', 20, '2023-10-04', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 150, 12, 0, now()),
    (2, 'CAREER', 'PROJECT', 'MALE', '제목', '성장해요', 20, '2023-10-05', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 160, 22, 0, now()),
    (2, 'CAREER', 'AMITY', 'MALE', '제목', '성장해요', 20, '2023-10-06', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 110, 17, 0, now()),
    (2, 'CAREER', 'STUDY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-07', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 10, 111, 0, now()),
    (2, 'CAREER', 'PROJECT', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-08', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (2, 'CAREER', 'AMITY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-09', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (2, 'CAREER', 'STUDY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-10', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (3, 'CAREER', 'STUDY', 'FEMALE', '제목', '성장해요', 20, '2023-10-01', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (3, 'CAREER', 'PROJECT', 'FEMALE', '제목', '성장해요', 20, '2023-10-02', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (3, 'CAREER', 'AMITY', 'FEMALE', '제목', '성장해요', 20, '2023-10-03', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (3, 'CAREER', 'STUDY', 'MALE', '제목', '성장해요', 20, '2023-10-04', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (3, 'CAREER', 'PROJECT', 'MALE', '제목', '성장해요', 20, '2023-10-05', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (3, 'CAREER', 'AMITY', 'MALE', '제목', '성장해요', 20, '2023-10-06', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (3, 'CAREER', 'STUDY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-07', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (3, 'CAREER', 'PROJECT', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-08', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (3, 'CAREER', 'AMITY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-09', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (3, 'CAREER', 'STUDY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-10', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (4, 'CAREER', 'STUDY', 'FEMALE', '제목', '성장해요', 20, '2023-10-01', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (4, 'CAREER', 'PROJECT', 'FEMALE', '제목', '성장해요', 20, '2023-10-02', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (4, 'CAREER', 'AMITY', 'FEMALE', '제목', '성장해요', 20, '2023-10-03', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (4, 'CAREER', 'STUDY', 'MALE', '제목', '성장해요', 20, '2023-10-04', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (4, 'CAREER', 'PROJECT', 'MALE', '제목', '성장해요', 20, '2023-10-05', '2023-10-15', true, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0,now()),
    (4, 'CAREER', 'AMITY', 'MALE', '제목', '성장해요', 20, '2023-10-06', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (4, 'CAREER', 'STUDY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-07', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (4, 'CAREER', 'PROJECT', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-08', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (4, 'CAREER', 'AMITY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-09', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now()),
    (4, 'CAREER', 'STUDY', 'IRRELEVANT', '제목', '성장해요', 20, '2023-10-10', '2023-10-15', false, '장소', 'EVERY_WEEK', '3:00 PM', '1 시간', 'APPROVAL', false, 0, 0, 0, now());

insert Into meeting_job (meeting_id, job)
values
    (1, 'BUSINESS'), (1, 'SERVICE'), (1, 'DEVELOPMENT'),
    (2, 'BUSINESS'), (2, 'SERVICE'), (2, 'DATA'),
    (3, 'MARKETING'), (3, 'ECOMMERCE'), (3, 'DATA'),
    (4, 'MARKETING'), (4, 'DESIGN'), (4, 'DATA'),
    (5, 'MARKETING'), (5, 'EDUCATION'), (5, 'FINANCE'),
    (6, 'ECOMMERCE'), (6, 'FINANCE'), (6, 'ACCOUNTING'),
    (7, 'CUSTOMER'), (7, 'HR'), (7, 'GAMEDEV'),
    (8, 'DISTRIBUTION'), (8, 'MEDICAL'), (8, 'GAMEDEV'),
    (9, 'MEDICAL'), (9, 'RESEARCH'), (9, 'LAW'),
    (10, 'ENGINEERING'), (10, 'PRODUCTION'), (10, 'LAW'),
    (11, 'BUSINESS'), (11, 'SERVICE'), (11, 'DEVELOPMENT'),
    (12, 'BUSINESS'), (12, 'SERVICE'), (12, 'DATA'),
    (13, 'MARKETING'), (13, 'ECOMMERCE'), (13, 'DATA'),
    (14, 'MARKETING'), (14, 'DESIGN'), (14, 'DATA'),
    (15, 'MARKETING'), (15, 'EDUCATION'), (15, 'FINANCE'),
    (16, 'ECOMMERCE'), (16, 'FINANCE'), (16, 'ACCOUNTING'),
    (17, 'CUSTOMER'), (17, 'HR'), (17, 'GAMEDEV'),
    (18, 'DISTRIBUTION'), (18, 'MEDICAL'), (18, 'GAMEDEV'),
    (19, 'MEDICAL'), (19, 'RESEARCH'), (19, 'LAW'),
    (20, 'ENGINEERING'), (20, 'PRODUCTION'), (20, 'LAW'),
    (21, 'BUSINESS'), (21, 'SERVICE'), (21, 'DEVELOPMENT'),
    (22, 'BUSINESS'), (22, 'SERVICE'), (22, 'DATA'),
    (23, 'MARKETING'), (23, 'ECOMMERCE'), (23, 'DATA'),
    (24, 'MARKETING'), (24, 'DESIGN'), (24, 'DATA'),
    (25, 'MARKETING'), (25, 'EDUCATION'), (25, 'FINANCE'),
    (26, 'ECOMMERCE'), (26, 'FINANCE'), (26, 'ACCOUNTING'),
    (27, 'CUSTOMER'), (27, 'HR'), (27, 'GAMEDEV'),
    (28, 'DISTRIBUTION'), (28, 'MEDICAL'), (28, 'GAMEDEV'),
    (29, 'MEDICAL'), (29, 'RESEARCH'), (29, 'LAW'),
    (30, 'ENGINEERING'), (30, 'PRODUCTION'), (30, 'LAW'),
    (31, 'BUSINESS'), (31, 'SERVICE'), (31, 'DEVELOPMENT'),
    (32, 'BUSINESS'), (32, 'SERVICE'), (32, 'DATA'),
    (33, 'MARKETING'), (33, 'ECOMMERCE'), (33, 'DATA'),
    (34, 'MARKETING'), (34, 'DESIGN'), (34, 'DATA'),
    (35, 'MARKETING'), (35, 'EDUCATION'), (35, 'FINANCE'),
    (36, 'ECOMMERCE'), (36, 'FINANCE'), (36, 'ACCOUNTING'),
    (37, 'CUSTOMER'), (37, 'HR'), (37, 'GAMEDEV'),
    (38, 'DISTRIBUTION'), (38, 'MEDICAL'), (38, 'GAMEDEV'),
    (39, 'MEDICAL'), (39, 'RESEARCH'), (39, 'LAW'),
    (40, 'ENGINEERING'), (40, 'PRODUCTION'), (40, 'LAW');

insert Into meeting_position (meeting_id, position)
values
    (1, 'INTERN'), (1, 'EMPLOYEE'), (1, 'ASSISTANTMANAGER'),
    (2, 'INTERN'), (2, 'SENIORMANAGER'), (2, 'DEPUTYMANAGER'),
    (3, 'EMPLOYEE'), (3, 'ASSISTANTMANAGER'), (3, 'SENIORMANAGER'),
    (4, 'EMPLOYEE'), (4, 'DEPUTYMANAGER'), (4, 'GENERALMANAGER'),
    (5, 'BOSS'), (5, 'MANAGER'), (5, 'ETC'),
    (6, 'BOSS'), (6, 'DEPUTYMANAGER'), (6, 'ETC'),
    (7, 'BOSS'), (7, 'EMPLOYEE'), (7, 'ETC'),
    (8, 'INTERN'), (8, 'EMPLOYEE'), (8, 'GENERALMANAGER'),
    (9, 'GENERALMANAGER'), (9, 'BOSS'), (9, 'ASSISTANTMANAGER'),
    (10, 'INTERN'), (10, 'GENERALMANAGER'), (10, 'BOSS'),
    (11, 'INTERN'), (11, 'EMPLOYEE'), (11, 'ASSISTANTMANAGER'),
    (12, 'INTERN'), (12, 'SENIORMANAGER'), (12, 'DEPUTYMANAGER'),
    (13, 'EMPLOYEE'), (13, 'ASSISTANTMANAGER'), (13, 'SENIORMANAGER'),
    (14, 'EMPLOYEE'), (14, 'DEPUTYMANAGER'), (14, 'GENERALMANAGER'),
    (15, 'BOSS'), (15, 'MANAGER'), (15, 'ETC'),
    (16, 'BOSS'), (16, 'DEPUTYMANAGER'), (16, 'ETC'),
    (17, 'BOSS'), (17, 'DEPUTYMANAGER'), (17, 'ETC'),
    (18, 'INTERN'), (18, 'GENERALMANAGER'), (18, 'MANAGER'),
    (19, 'MANAGER'), (19, 'INTERN'), (19, 'GENERALMANAGER'),
    (20, 'GENERALMANAGER'), (20, 'EMPLOYEE'), (20, 'ASSISTANTMANAGER'),
    (21, 'INTERN'), (21, 'EMPLOYEE'), (21, 'ASSISTANTMANAGER'),
    (22, 'INTERN'), (22, 'SENIORMANAGER'), (22, 'DEPUTYMANAGER'),
    (23, 'EMPLOYEE'), (23, 'ASSISTANTMANAGER'), (23, 'SENIORMANAGER'),
    (24, 'EMPLOYEE'), (24, 'DEPUTYMANAGER'), (24, 'GENERALMANAGER'),
    (25, 'BOSS'), (25, 'MANAGER'), (25, 'ETC'),
    (26, 'BOSS'), (26, 'DEPUTYMANAGER'), (26, 'ETC'),
    (27, 'BOSS'), (27, 'DEPUTYMANAGER'), (27, 'ETC'),
    (28, 'INTERN'), (28, 'GENERALMANAGER'), (28, 'MANAGER'),
    (29, 'MANAGER'), (29, 'INTERN'), (29, 'GENERALMANAGER'),
    (30, 'GENERALMANAGER'), (30, 'EMPLOYEE'), (30, 'ASSISTANTMANAGER'),
    (31, 'INTERN'), (31, 'EMPLOYEE'), (31, 'ASSISTANTMANAGER'),
    (32, 'INTERN'), (32, 'SENIORMANAGER'), (32, 'DEPUTYMANAGER'),
    (33, 'EMPLOYEE'), (33, 'ASSISTANTMANAGER'), (33, 'SENIORMANAGER'),
    (34, 'EMPLOYEE'), (34, 'DEPUTYMANAGER'), (34, 'GENERALMANAGER'),
    (35, 'BOSS'), (35, 'MANAGER'), (35, 'ETC'),
    (36, 'BOSS'), (36, 'DEPUTYMANAGER'), (36, 'ETC'),
    (37, 'BOSS'), (37, 'DEPUTYMANAGER'), (37, 'ETC'),
    (38, 'INTERN'), (38, 'GENERALMANAGER'), (38, 'MANAGER'),
    (39, 'MANAGER'), (39, 'INTERN'), (39, 'GENERALMANAGER'),
    (40, 'GENERALMANAGER'), (40, 'EMPLOYEE'), (40, 'ASSISTANTMANAGER');
