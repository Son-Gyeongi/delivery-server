# 테스트 데이터 생성

### 백만 데이터 생성하기

### 참고
[현경님 백만 데이터 추가 방법(테스트 데이터 2000개 등록 코드 추가)](https://github.com/ChaChaMong/delivery-server/pull/7)
```sql
### 이 프로시저는 총 499번 반복되며, 
### 각 반복마다 orders 테이블에서 id가 1에서 2000 사이인 모든 레코드를 
### DAY 일만큼 날짜를 감소시킨 후 새로운 레코드를 추가합니다. 
### 따라서 총 999,500개의 레코드가 추가됩니다(500 * 2000 = 1,000,000, 처음 레코드 제외)
# 하루에 데이터 2000개씩 500일전까지 데이터 넣기
DELIMITER $$ # 구분자를 $$ 변경
CREATE PROCEDURE update_orders_dates() # update_orders_dates라는 이름의 프로시저를 생성
BEGIN # 프로시저의 시작
    DECLARE DAY INT DEFAULT 1; # DAY라는 정수형 변수를 선언하고 기본값을 1로 설정
    WHILE DAY < 500 DO # DAY 변수가 500보다 작을 동안 아래의 명령어들을 반복 실행
	     # orders 테이블에 새로운 레코드를 삽입
        INSERT INTO orders (restaurant_id, content, price, STATUS, address, address_detail, postcode, phone, orderer_name, created_at, updated_at)
        # orders 테이블에서 데이터를 선택
        # DATE_SUB(created_at, INTERVAL DAY DAY), DATE_SUB(updated_at, INTERVAL DAY DAY) : created_at과 updated_at 날짜에서 DAY 만큼 일수를 뺍니다.
        SELECT restaurant_id, content, price, STATUS, address, address_detail, postcode, phone, orderer_name, DATE_SUB(created_at, INTERVAL DAY DAY), DATE_SUB(updated_at, INTERVAL DAY DAY)
        FROM orders
        WHERE id > 0 AND id <= 2000; # id가 1에서 2000 사이인 레코드만 선택
        SET DAY = DAY + 1; # DAY 변수를 1씩 증가
    END WHILE; # WHILE 루프의 끝
END$$ # 프로시저의 끝
DELIMITER ; # 구분자를 세미콜론(;)으로 다시 변경

CALL update_orders_dates(); # 생성한 프로시저를 호출하여 실행
```

<br>

### 나에게 맞게 프로시저 바꾸기

```sql
# 나한테 맞춰서 프로시저 돌려보기 - orders 테이블 추가
DELIMITER $$
CREATE PROCEDURE update_orders_dates()
BEGIN
    DECLARE DAY INT DEFAULT 1;
    WHILE DAY < 500 DO
        INSERT INTO orders (price, total_price, created_at, deleted_at, restaurant_id, updated_at, address, address_detail, description, order_no, phone, recipient, `status`)
        SELECT price, total_price, DATE_SUB(created_at, INTERVAL DAY DAY), deleted_at, restaurant_id, DATE_SUB(updated_at, INTERVAL DAY DAY), address, address_detail, description, order_no, phone, recipient, `status`
        FROM orders
        WHERE id > 0 AND id <= 2000;
        SET DAY = DAY + 1;
    END WHILE;
END$$
DELIMITER ;

CALL update_orders_dates();
```

<br>

```sql
# 나한테 맞춰서 프로시저 돌려보기 - orderItems 테이블 추가
DELIMITER $$
CREATE PROCEDURE update_order_items_dates()
BEGIN
    DECLARE DAY INT DEFAULT 1;
    DECLARE BASE_ORDER_ID INT DEFAULT 2000;
    DECLARE ORDER_INCREMENT INT DEFAULT 0;
    WHILE DAY < 500 DO
        INSERT INTO order_items (created_at, deleted_at, order_id, quantity, updated_at, food_name)
        SELECT DATE_SUB(created_at, INTERVAL DAY DAY), deleted_at, 
		  						BASE_ORDER_ID + ORDER_INCREMENT + id, quantity, DATE_SUB(updated_at, INTERVAL DAY DAY), food_name
        FROM order_items
        WHERE id > 0 AND id <= 2000;
        
        # 데이터 2000개 넣은 후에
        SET DAY = DAY + 1;
        SET ORDER_INCREMENT = ORDER_INCREMENT + 2000; -- 총 레코드 수 만큼 증가
    END WHILE;
END$$
DELIMITER ;

# update_order_items_dates 프로시저가 존재하는 경우 제거
# DROP PROCEDURE IF EXISTS update_order_items_dates;

CALL update_order_items_dates();
```

<br>

```sql
# 나한테 맞춰서 프로시저 돌려보기 - order_item_options 테이블 추가
DELIMITER $$
CREATE PROCEDURE update_order_item_options_dates()
BEGIN
    DECLARE DAY INT DEFAULT 1;
    DECLARE BASE_ORDER_ID INT DEFAULT 2000;
    DECLARE ORDER_INCREMENT INT DEFAULT 0;
    WHILE DAY < 500 DO
        INSERT INTO order_item_options (created_at, deleted_at, order_item_id, updated_at, food_option_name)
        SELECT DATE_SUB(created_at, INTERVAL DAY DAY), deleted_at, 
		  						BASE_ORDER_ID + ORDER_INCREMENT + id, DATE_SUB(updated_at, INTERVAL DAY DAY), food_option_name
        FROM order_item_options
        WHERE id > 0 AND id <= 2000;
        
        # 데이터 2000개 넣은 후에
        SET DAY = DAY + 1;
        SET ORDER_INCREMENT = ORDER_INCREMENT + 2000; -- 총 레코드 수 만큼 증가
    END WHILE;
END$$
DELIMITER ;

CALL update_order_item_options_dates();
```
