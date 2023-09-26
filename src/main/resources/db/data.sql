INSERT INTO building(name)
VALUES ('미왕빌딩'),
       ('그랑서울'),
       ('프롭테크빌리지'),
       ('서초프라자'),
       ('로이어즈타워'),
       ('대륭타워'),
       ('한승아스트라'),
       ('법률센터');


INSERT INTO tenant(name, building_id)
VALUES ('스타트23', '1');
INSERT INTO tenant(name, building_id)
VALUES ('스타트23', '2');
INSERT INTO tenant(name, building_id)
VALUES ('스타트23', '3');
INSERT INTO tenant(name, building_id)
VALUES ('아무건설', '1');
INSERT INTO tenant(name, building_id)
VALUES ('아무건설', '2');
INSERT INTO tenant(name, building_id)
VALUES ('아무건설', '3');
INSERT INTO tenant(name, building_id)
VALUES ('법무', '1');
INSERT INTO tenant(name, building_id)
VALUES ('법무', '2');
INSERT INTO tenant(name, building_id)
VALUES ('법무', '3');
insert into tenant (name, building_id)
values ('오세안무역', 1);
insert into tenant (name, building_id)
values ('법무법인 율천', 1);
insert into tenant (name, building_id)
values ('영광공인중개사사무소', 4);
insert into tenant (name, building_id)
values ('연세위드이비인후과', 5);
insert into tenant (name, building_id)
values ('8층약국', 5);
insert into tenant (name, building_id)
values ('삼성생명', 6);
insert into tenant (name, building_id)
values ('이경영 변호사사무소', 7);
insert into tenant (name, building_id)
values ('법무법인 흑룡', 8);



INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id)
VALUES ('201호', 2, 'A동', 1, 1);
INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id)
VALUES ('302호', 3, 'A동', 2, 2);
INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id)
VALUES ('507호', 5, 'B동', 5, 2);
INSERT INTO office_room (room_number, floor, wing, tenant_id, building_id)
VALUES ('102호', 1, 'C동', 7, 3);
-- 호실
insert into office_room (room_number, floor, wing, building_id, tenant_id)
values ('101', 1, '-', 1, 11);
insert into office_room (room_number, floor, wing, building_id, tenant_id)
values ('807', 8, '-', 1, 12);
insert into office_room (room_number, floor, wing, building_id, tenant_id)
values ('b127', -1, '-', 4, 13);
insert into office_room (room_number, floor, wing, building_id, tenant_id)
values ('809', 8, '-', 5, 14);
insert into office_room (room_number, floor, wing, building_id, tenant_id)
values ('808', 8, '-', 6, 15);
insert into office_room (room_number, floor, wing, building_id, tenant_id)
values ('508', 5, '1동', 7, 16);
insert into office_room (room_number, floor, wing, building_id, tenant_id)
values ('208', 2, 'B동', 8, 17);

-- password : abc1234#
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('yohan@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '김요한', '요한이', '01020003000',
        '안녕하세요', '20000214', 'MALE', 'EMPLOYEE', 'DESIGN', now(), now(), 1, 1, 1);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('watcha@naver.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '김사원', '사원킴', '01011113117',
        '안녕하세요', '20000714', 'FEMALE', 'ASSISTANTMANAGER', 'BUSINESS', now(), now(), 2, 2, 2);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('yumi@daum.net', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '이유미', '유미', '01021113123',
        '하이하이', '19970214', 'FEMALE', 'SENIORMANAGER', 'HR', now(), now(), 4, 3, 7);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('naza@daum.net', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '우철수', '철수', '01022376025',
        '법률쪽맡은사람입니다.', '19921225', 'MALE', 'GENERALMANAGER', 'LAW', now(), now(), 3, 2, 5);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('work99@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '도배만', '도베르만',
        '01033328888', '안녕하세요', '19930707', 'MALE', 'ASSISTANTMANAGER', 'RESEARCH', now(), now(), 1, 1, 1);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('pakka9@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '한아름', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, now(), now(), 2, 2, 2);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('jigubon9@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '지구본', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, now(), now(), 4, 3, 7);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('bakgu@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '백구식', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, now(), now(), 3, 2, 5);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('host77@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '유호식', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, now(), now(), 1, 1, 1);
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('dklss8@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '남궁민', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, now(), now(), 2, 2, 2);
-- 멤버
INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('glory@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '정성민', '정성민123',
        '01066789982', '정성민입니다.', '19890812', 'MALE',
        'SENIORMANAGER',
        'CUSTOMER', now(),
        now(), 7, 4, 12);

INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('doctorchoi@yonseiwith.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '최원장', 'dr.choi',
        '01012311233', '연세위드 이비인후과 원장 최원장입니다.', '19800101',
        'MALE', 'ETC', 'MEDICAL', now(),
        now(), 8, 5, 14);

INSERT INTO member (email, password, name, nickname, phone_number, bio, birthday, sex, position, job, created_at,
                    updated_at, office_room_id, building_id, tenant_id)
VALUES ('acd@gmail.com', '$2a$12$iTiQm3XDjKfbu3boF1j3DO4S.udgMy4wKPMyb3aQMScnw9YaBYdMq', '김민정', 'min_vely',
        '01063329182', '', '19901112', 'FEMALE', 'BOSS', 'MEDICAL',
        now(),
        now(), 9, 6, 15);


--공동구매
insert into purchase (member_id, title, category, shop_url, start_date, end_date, head_count, price, description,
                      closing_type, is_closing, hits, bank, account_number, account_owner)
values (1, '코드제로 A9', 'HOUSEHOLD', 'https://www.lge.co.kr/vacuum-cleaners/as9202wd', '2023-10-01', '2023-10-31', 5,
        500000, '이거 같이 삽시다 넘 좋아요 ㅜ',
        'DATETIME',
        false, 25, '신한은행', '110351316910',
        '김요한');
insert into purchase (member_id, title, category, shop_url, start_date, end_date, head_count, price, description,
                      closing_type, is_closing, hits, bank, account_number, account_owner)
values (2, '로지텍 mx master 3s', 'OFFICE_SUPPLIES', 'https://www.funshop.co.kr/goods/detail/183875?t=sc', '2023-10-01',
        '2023-09-19', 10, 110000,
        '가성비 너무 좋아요! 같이 삽시다',
        'HEAD_COUNT', false, 100, '신한은행', '111352316910',
        '김사원');
insert into purchase (member_id, title, category, shop_url, start_date, end_date, head_count, price, description,
                      closing_type, is_closing, hits, bank, account_number, account_owner)
values (3, 'WH-1000XM5', 'ELECTRONICS', 'https://www.musinsa.com/app/goods/2608456', '2023-10-01', '2023-10-11', 10,
        390000, '소니 wh-1000xm5 공구합니다',
        'HEAD_COUNT', false, 150, '국민은행', '03192619100',
        '이유미');
insert into purchase (member_id, title, category, shop_url, start_date, end_date, head_count, price, description,
                      closing_type, is_closing, hits, bank, account_number, account_owner)
values (5, '맥북에어 m3 15인치 같이사용', 'ELECTRONICS', 'https://www.coupang.com/vp/products/7400323975', '2023-10-15',
        '2023-10-31', 30, 1690000,
        '맥북에어 15인치 m3',
        'HEAD_COUNT', false, 300, '카카오뱅크', '123512310901',
        '도배만');
insert into purchase (member_id, title, category, shop_url, start_date, end_date, head_count, price, description,
                      closing_type, is_closing, hits, bank, account_number, account_owner)
values (10, '믹서기마냥 갈아버리는 블렌더 보틀 같이 사실분?', 'HOUSEHOLD', 'https://www.coupang.com/vp/products/7075531344', '2023-10-01',
        '2023-10-11', 10, 15000,
        '블렌더 보틀 텀블러 같이사요~~',
        'HEAD_COUNT', false, 123, '우리은행', '100399721231',
        '김민정');

--공동구매 미디어
insert into purchase_media (url, purchase_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/purchase/23415.png', 1);
insert into purchase_media (url, purchase_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/purchase/vs_image800.jpg', 2);
insert into purchase_media (url, purchase_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/purchase/2608456_16908576712947_500.jpg', 3);
insert into purchase_media (url, purchase_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/purchase/images.jpeg', 3);
insert into purchase_media(url,purchase_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/purchase/vs_image800.jpg', 4);
insert into purchase_media(url,purchase_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/purchase/air15.jpeg', 4);
insert into purchase_media(url,purchase_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/purchase/air152.jpeg', 4);
insert into purchase_media (url, purchase_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/purchase/a123.png', 5);

-- 공동구매 북마크
insert into purchase_bookmark (member_id, purchase_id, created_at, updated_at)
values (1, 2, now(), now());
insert into purchase_bookmark (member_id, purchase_id, created_at, updated_at)
values (2, 1, now(), now());
insert into purchase_bookmark (member_id, purchase_id, created_at, updated_at)
values (10, 5, now(), now());
insert into purchase_bookmark (member_id, purchase_id, created_at, updated_at)
values (10, 1, now(), now());
insert into purchase_bookmark (member_id, purchase_id, created_at, updated_at)
values (5, 4, now(), now());

insert into group_purchase_participant (purchase_id)
values (1);
insert into group_purchase_participant (purchase_id)
values (1);
insert into group_purchase_participant (purchase_id)
values (1);
insert into group_purchase_participant (purchase_id)
values (2);
insert into group_purchase_participant (purchase_id)
values (2);
insert into group_purchase_participant (purchase_id)
values (3);
insert into group_purchase_participant (purchase_id)
values (4);
insert into group_purchase_participant (purchase_id)
values (4);
insert into group_purchase_participant (purchase_id)
values (4);
insert into group_purchase_participant (purchase_id)
values (5);
insert into group_purchase_participant (purchase_id)
values (5);

insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (1, 5);
insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (1, 4);
insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (2, 5);
insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (2, 4);
insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (3, 4);
insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (4, 3);
insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (7, 1);
insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (8, 1);
insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (9, 1);
insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (10, 2);
insert into group_purchase_participant_member (member_id, purchase_participant_id)
values (5, 2);

-- 중고거래
insert into secondhand (member_id, title, category, transaction_start_time,
                        transaction_end_time, price, description,
                        hits, is_free, created_at, updated_at)
values (1, '메가커피 기프티콘 팝니다', 'TICKET', '00:00:00', '23:59:59', 1000, '메가커피 아메리카노 깊티입니다.', 25, false, now(), now());
insert into secondhand (member_id, title, category, transaction_start_time, transaction_end_time, price, description,
                        hits, is_free, created_at, updated_at)
values (1, '아이폰 13미니', 'ELECTRONICS', '00:00:00', '23:59:59', 500000, '아이폰 13미니 128gb 미드나잇 블루', 25, false, now(),
        now());
insert into secondhand (member_id, title, category, transaction_start_time,
                        transaction_end_time, price, description, hits,
                        is_free, created_at, updated_at)
values (3, '티쏘 젠틀맨 팝니다', 'FASHION', '00:00:00', '23:59:59', 300000, '넘예쁘네여', 39,
        false, now(), now());
insert into secondhand (member_id, title, category, transaction_start_time,
                        transaction_end_time, price, description, hits,
                        is_free, created_at, updated_at)
values (2, '몽블랑 머니클립', 'FASHION', '13:00:00', '20:00:00', 200000, '머니클립 너무 편해용', 19,
        false, now(), now());
insert into secondhand (member_id, title, category, transaction_start_time,
                        transaction_end_time, price, description, hits,
                        is_free, created_at, updated_at)
values (10, '셀린느 카드지갑', 'FASHION', '11:00:00', '20:00:00', 300000, '산지 얼마 되지 않아 상태 아주 좋습니다', 51,
        false, now(), now());
insert into secondhand (member_id, title, category, transaction_start_time,
                        transaction_end_time, price, description, hits,
                        is_free, created_at, updated_at)
values (7, '투퍼데이 멀티비타민', 'PROCESSED_WELLNESS_FOODS', '10:00:00', '22:00:00', 20000, '투퍼데이 멀티비타인 미개봉 판매해요', 11,
        false, now(), now());
insert into secondhand (member_id, title, category, transaction_start_time,
                        transaction_end_time, price, description, hits,
                        is_free, created_at, updated_at)
values (7, '홈키파 훈증기 무료나눔', 'HOUSEHOLD', '10:00:00', '22:00:00', 0, '액상형으로 교체해 안쓰는 훈증기 무료나눔합니다~~', 11,
        true, now(), now());

-- 중고거래 이미지
insert into secondhand_media (url, secondhand_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/secondhand/1000.png', 1);
insert into secondhand_media (url, secondhand_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/secondhand/13.jpeg', 2);
insert into secondhand_media (url, secondhand_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/secondhand/7899.jpeg', 3);
insert into secondhand_media (url, secondhand_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/secondhand/14121.jpeg', 4);
insert into secondhand_media (url, secondhand_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/secondhand/1362.png', 5);
insert into secondhand_media (url, secondhand_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/secondhand/120.jpeg', 6);
insert into secondhand_media (url, secondhand_id)
values ('https://fc-palette.s3.ap-northeast-2.amazonaws.com/secondhand/356.png', 7);

-- 중고거래 북마크
insert into secondhand_bookmark (member_id, secondhand_id, created_at, updated_at)
values (1, 2, now(), now());
insert into secondhand_bookmark (member_id, secondhand_id, created_at, updated_at)
values (2, 1, now(), now());
insert into secondhand_bookmark (member_id, secondhand_id, created_at, updated_at)
values (10, 5, now(), now());
insert into secondhand_bookmark (member_id, secondhand_id, created_at, updated_at)
values (10, 1, now(), now());
insert into secondhand_bookmark (member_id, secondhand_id, created_at, updated_at)
values (5, 4, now(), now());
