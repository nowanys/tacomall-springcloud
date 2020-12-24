'''
Author: 码上talk|RC
Date: 2020-12-02 11:33:02
LastEditTime: 2020-12-02 11:33:11
LastEditors: 码上talk|RC
Description: 
FilePath: /tacomall-springcloud/_db-tool/utils/mysql_util.py
微信:  13680065830
邮箱:  3189482282@qq.com
oops: Just do what I think it is right
'''

import pymysql


class MysqlUtil():

    def __init__(self, config, is_debug=True):
        self.ip = config['ip']
        self.user = config['user']
        self.passwd = config['passwd']
        self.db = config['db']
        self.conn = None
        self.cursor = None
        self.is_debug = is_debug
        self.is_auto_commit = True
        self.__connect()

    def __connect(self):
        try:
            self.conn = pymysql.connect(
                self.ip, user=self.user, passwd=self.passwd, db=self.db)
        except Exception:
            raise Exception('>>>>>mysql connect error')

    def execute_sql(self, sql, auto_commit=True):
        if self.is_debug:
            print('debug sql >>>>>>> {sql}'.format(sql=sql))
        self.cursor = self.conn.cursor()
        try:
            self.cursor.execute(sql)
        except Exception as e:
            raise Exception('>>>>>>sql error:{e}'.format(e=e))
        if self.is_auto_commit:
            self.conn.commit()
        return self.cursor.lastrowid

    def query_sql(self, sql):
        if self.is_debug:
            print('debug sql >>>>>>> {sql}'.format(sql=sql))
        self.cursor = self.conn.cursor()
        try:
            self.cursor.execute(sql)
        except Exception as e:
            raise Exception('>>>>>>sql error:{e}'.format(e=e))
        self.cursor.close()
        return self.cursor.fetchall()

    def start_transaction(self):
        self.is_auto_commit = False

    def commit(self):
        self.conn.commit()
        self.cursor.close()

    def rollback(self):
        self.conn.rollback()
        self.cursor.close()
