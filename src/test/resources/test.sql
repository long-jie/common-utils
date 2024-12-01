INSERT INTO test.`user`
(user_id, username, password, email, created_at)
VALUES(1, 'u1', 'u1;fdjofj,@#$#', 'test@foxmail.com', '2024-11-27 22:21:05');
INSERT INTO test.`user`
(user_id, username, password, email, created_at)
VALUES(2, 'u2', 'fere\ndfdfd', '8@foxmail.com', '2024-12-01 08:31:45');
INSERT INTO test.`user`
(user_id, username, password, email, created_at)
VALUES(3, 'u3', 'fere
 dfdfd', '3@foxmail.com', '2024-12-01 08:31:45');


SELECT user_id, username, password, email, created_at
FROM test.`user`
WHERE user_id IN (1,2,3);


UPDATE test.`user`
SET username='u3', password='fere
 dfdfd', email='3@foxmail.com', created_at='2024-12-01 08:31:45'
WHERE user_id=3;

DELETE FROM test.`user`
WHERE user_id=3;