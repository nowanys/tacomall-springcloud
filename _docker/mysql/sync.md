select version(), @@sql_mode;
SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

GRANT REPLICATION SLAVE ON *.* TO 'syncuser'@'%' IDENTIFIED BY '123456';


mysqldump -h127.0.0.1 -P21000 -uroot -p123456 --single-transaction  --master-data=2 --databases tacomall > ./tacomall.dmp;

mysql -h127.0.0.1 -P21001 -uroot -p123456 tacomall < ./tacomall.dmp;

change master to master_host='192.168.0.38',master_user='syncuser',master_password='123456',master_port=21000,master_log_file='mysql-bin.000002',master_log_pos=154;

start slave

show slave status