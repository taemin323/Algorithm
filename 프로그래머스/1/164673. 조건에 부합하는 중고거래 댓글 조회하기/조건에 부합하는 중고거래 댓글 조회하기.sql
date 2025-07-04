-- 코드를 입력하세요
SELECT B.TITLE, R.BOARD_ID, R.REPLY_ID, R.WRITER_ID, R.CONTENTS, DATE_FORMAT(R.CREATED_DATE, '%Y-%m-%d') as CREATED_DATE
FROM USED_GOODS_REPLY as R 
JOIN USED_GOODS_BOARD as B ON B.BOARD_ID = R.BOARD_ID
WHERE B.CREATED_DATE BETWEEN '2022-10-01' AND '2022-10-31'
ORDER BY
  R.CREATED_DATE ASC,
  B.TITLE ASC;