'''
Author: 码上talk|RC
Date: 2020-12-02 11:36:00
LastEditTime: 2020-12-02 15:14:20
LastEditors: 码上talk|RC
Description: 
FilePath: /tacomall-springcloud/_db/db_sync.py
微信:  13680065830
邮箱:  3189482282@qq.com
oops: Just do what I think it is right
'''
import os
import time
import random
import subprocess

from utils.mysql_util import MysqlUtil


def execute_cmd(cmd):
    popen = subprocess.Popen(cmd, stdout=subprocess.PIPE,
                             stderr=subprocess.PIPE, shell=True)
    print('执行命令： ' + cmd)
    for i in iter(popen.stdout.readline, ''):
        if len(i) < 1:
            break
        print(i.decode('gbk').strip())


def main():
    is_new = input('是否重新导出数据？ yes/no：')
    version = time.time()
    if is_new == 'no':
        version = input('请输入历史版本 ps:1601260075.6850908：')
        if not os.path.exists('./.sql/dump/{0}'.format(version)):
            raise Exception('版本不存在')
    path_dump = './.sql/dump/{0}'.format(version)
    if not os.path.exists(path_dump):
        os.makedirs(path_dump)
    db_from_config = {
        'ip': '',
        'port': 3306,
        'user': '',
        'passwd': '',
        'db': ''
    }
    db_to_config = {
        'ip': '',
        'port': 3306,
        'user': '',
        'passwd': '',
        'db': ''
    }
    if 'rds' in db_to_config['ip']:
        raise Exception('导入数据库存在敏感地址，请再次验证')
    all_tables = []
    mysql_util = MysqlUtil(config=db_from_config)
    sql_all_tables = 'SHOW TABLES'
    sql_all_tables_result = mysql_util.query_sql(sql_all_tables)
    for table in list(sql_all_tables_result):
        all_tables.append(table[0])

    if is_new == 'yes':
        for t in all_tables:
            print('----> from database <{0}>'.format(db_from_config['ip']))
            print('----> dump table <{0}>'.format(t))
            cmd_dump_sql = 'mysqldump --set-gtid-purged=off -h{h} -P{P} -u{u} -p{p} {db} --tables  {tb}>{path_dump}/{tb}.sql'.format(
                h=db_from_config['ip'], P=db_from_config['port'], u=db_from_config['user'], p=db_from_config['passwd'], db=db_from_config['db'], path_dump=path_dump, tb=t)
            execute_cmd(cmd_dump_sql)
    verify_code = ''.join(random.sample(['z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q',
                                         'p', 'o', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'], 5))
    print('----------WARNING START----------')
    print('----------infomation below must be readed carefully!----------')
    print('----------or it will make a unexpectable and harmful issue----------')
    print('----------FROM DATABASE INFO----------')
    print('----------IP {0}----------'.format(db_from_config['ip']))
    print('----------DATABASE {0}----------'.format(db_from_config['db']))
    print('----------TO DATABASE INFO----------')
    print('----------IP {0}----------'.format(db_to_config['ip']))
    print('----------DATABASE {0}----------'.format(db_to_config['db']))
    print('----------WARNING END----------')
    print('----------VERIFY CODE: {0}------'.format(verify_code))
    is_confirm_do = input('已阅读警告 yes/no：')
    if is_confirm_do == 'no':
        print('程序退出')
        return
    if is_confirm_do == 'yes':
        check_verify_code = input('输入上述验证码：')
        if check_verify_code != verify_code:
            raise Exception('验证码错误')
        else:
            for t in all_tables:
                print('----> to database <{0}>'.format(db_to_config['ip']))
                print('----> import table <{0}>'.format(t))
                cmd_import_sql = 'mysql -h{h} -P{P} -u{u} -p{p} {db}<{path_dump}/{tb}.sql'.format(
                    h=db_to_config['ip'], P=db_to_config['port'], u=db_to_config['user'], p=db_to_config['passwd'], db=db_to_config['db'], path_dump=path_dump, tb=t)
                execute_cmd(cmd_import_sql)


if __name__ == '__main__':
    main()
