INSERT INTO f_user (address, email, gender, join_date, name, password, phone_number, role, birth_date)
VALUES ('부산광역시 금정구', '1@gmail.com', 'male', CURRENT_TIMESTAMP, '김덕배', '1', '010-1111-1111', 'ROLE_USER', '1995-04-28');

INSERT INTO f_user (address, email, gender, join_date, name, password, phone_number, role, birth_date)
VALUES ('부산광역시 남구', '2@gmail.com', 'male', CURRENT_TIMESTAMP, '황덕구', '1', '010-4242-1919', 'ROLE_USER', '2002-07-11');

INSERT INTO f_user (address, email, gender, join_date, name, password, phone_number, role, birth_date)
VALUES ('서울특별시 강남구', '3@gmail.com', 'female', CURRENT_TIMESTAMP, '이숙녀', '1', '010-2222-2222', 'ROLE_USER', '2002-05-11');

INSERT INTO f_center (address, closing_time, daily_pass_price, name, open_time, phone_number, image_path)
VALUES ('부산 부산진구 가야대로 772' , '18:30' , 10000, '굿케어헬스', '09:00' , '010-1234-5678' , 'health1.png');

INSERT INTO f_center (address, closing_time, daily_pass_price, name, open_time, phone_number, image_path)
VALUES ('부산광역시 부산진구 중앙대로 694 쥬디스태화' , '20:30' , 12000, '나이스헬스', '08:30' , '010-1234-5678'  , 'health2.png');

INSERT INTO f_center (address, closing_time, daily_pass_price, name, open_time, phone_number, image_path)
VALUES ('부산광역시 부산진구 서전로10번길 61 쥬디스태화신관' , '21:00' , 8000, '람보헬스', '09:00' , '010-1234-5678' , 'health3.png');

INSERT INTO f_center (address, closing_time, daily_pass_price, name, open_time, phone_number, image_path)
VALUES ('부산광역시 부산진구 중앙대로 718 우리은행서면지점' , '23:00' , 10000, '진짜로피트니스', '07:00' , '010-1234-5678' , 'health4.png');

INSERT INTO f_center (address, closing_time, daily_pass_price, name, open_time, phone_number, image_path)
VALUES ('부산광역시 부산진구 동천로 92' , '23:30' , 10000, '몸짱피트니스', '06:00' , '010-1234-5678 ', 'health5.png');

INSERT INTO f_center (address, closing_time, daily_pass_price, name, open_time, phone_number, image_path)
VALUES ('부산 부산진구 전포대로 294' , '23:30' , 10000, '휘휘휘트니스', '06:00' , '010-1234-5678 ', 'health6.png');

INSERT INTO f_center (address, closing_time, daily_pass_price, name, open_time, phone_number, image_path)
VALUES ('부산 부산진구 중앙대로783번길 34 부전마켓타운 공영주차장' , '23:30' , 10000, '약수터헬스장', '06:00' , '010-1234-5678 ', 'health7.png');

INSERT INTO f_owner (address, email, gender, join_date, name, password, phone_number, role, business_registration_number, center_id)
VALUES ('부산광역시 부산진구', '5@gmail.com', 'male', CURRENT_TIMESTAMP, '박용우', '1', '010-1111-1111', 'ROLE_OWNER', '111-2222-555-456212', 1);

INSERT INTO f_owner (address, email, gender, join_date, name, password, phone_number, role, business_registration_number, center_id)
VALUES ('부산광역시 부산진구', '6@gmail.com', 'female', CURRENT_TIMESTAMP, '박말순', '1', '010-1111-1111', 'ROLE_OWNER', '111-23232-555-424212', 2);

INSERT INTO  f_owner (address, email, gender, join_date, name, password, phone_number, role, business_registration_number, center_id)
VALUES ('대구광역시 달서구', '7@gmail.com', 'male', CURRENT_TIMESTAMP, '이춘삼', '1', '010-1111-1111', 'ROLE_OWNER', '221-2222-55355-456212', 3);

INSERT INTO  f_owner (address, email, gender, join_date, name, password, phone_number, role, business_registration_number, center_id)
VALUES ('울산광역시 중구', '8@gmail.com', 'male', CURRENT_TIMESTAMP, '곽두팔', '1', '010-1111-1111', 'ROLE_OWNER', '111-267823-54335-456212', 4);

INSERT INTO  f_owner (address, email, gender, join_date, name, password, phone_number, role, business_registration_number, center_id)
VALUES ('부산광역시 동래구', '9@gmail.com', 'male', CURRENT_TIMESTAMP, '차은우', '1', '010-1111-1111', 'ROLE_OWNER', '111-65722-55885-456212', 5);