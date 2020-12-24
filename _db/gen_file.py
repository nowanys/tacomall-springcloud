'''
Author: 码上talk|RC
Date: 2020-12-02 11:35:17
LastEditTime: 2020-12-02 15:57:10
LastEditors: 码上talk|RC
Description:
FilePath: /tacomall-springcloud/_db/gen_entity.py
微信:  13680065830
邮箱:  3189482282@qq.com
oops: Just do what I think it is right
'''
from utils.path_util import PathUtil
from utils.mysql_util import MysqlUtil


class GenFile():
    def __init__(self):
        self.path_tpl_entity = PathUtil.root('templates') + '/entity.tpl'
        self.path_tpl_mapper = PathUtil.root('templates') + '/mapper.tpl'
        self.project_java = 'test'
        self.mysql_util = MysqlUtil({
            'ip': 'localhost',
            'user': 'root',
            'passwd': '123456',
            'db': 'tacomall'
        })
        self.all_tables = []
        self._query_all_tables()

    def _query_all_tables(self):
        sql_all_tables = '''
        SELECT
            table_name
        FROM
            information_schema.`TABLES`
        WHERE
            TABLE_SCHEMA = 'tacomall';
        '''
        result_sql_all_tables = self.mysql_util.query_sql(sql_all_tables)
        self.all_tables = list(map(lambda i: i[0], result_sql_all_tables))

    def _load_tpl(self, tpl_type=1):
        path = ''
        if tpl_type == 1:
            path = self.path_tpl_entity
        if tpl_type == 2:
            path = self.path_tpl_mapper
        tpl = ''
        with open(path) as f_tpl:
            tpl = f_tpl.read()
            f_tpl.close()
        return tpl

    def _2camel(self, s, capitalize=False):
        s = s.lower()
        class_name = ''
        for i, v in enumerate(s.split('_')):
            if i == 0 and not capitalize:
                class_name = v
                continue
            class_name = class_name + v.capitalize()
        return class_name

    def _gen_entity(self):
        tpl = self._load_tpl(1)
        for table in self.all_tables:
            new_tpl = ''
            spl_table = table.split('_')
            package = 'store.tacomall.db.entity.{0}'.format(spl_table[0])
            class_name = self._2camel(table, capitalize=True)
            fields_content = ''
            ignore_columns = ['id', 'is_delete',
                              'create_time', 'update_time', 'delete_time']
            filename_gen = PathUtil.root(
                self.project_java + '/entity/{0}/'.format(spl_table[0])) + '{0}.java'.format(class_name)
            sql_table_structure = '''
            SELECT
                DATA_TYPE,
                column_name,
                column_comment
            FROM
                information_schema.COLUMNS
            WHERE
                table_name = '{0}';
            '''.format(table)
            if PathUtil.is_exists(filename_gen):
                print('实体类{0}.java文件已存在'.format(class_name))
                continue
            result_sql_table_structure = self.mysql_util.query_sql(
                sql_table_structure)
            for structure in list(map(lambda i: {'type': i[0], 'column': i[1], 'comment': i[2]}, result_sql_table_structure)):
                if structure['column'] in ignore_columns:
                    continue
                java_data_type = ''
                if structure['type'] == 'int':
                    java_data_type = 'int'
                if structure['type'] == 'varchar':
                    java_data_type = 'String'
                if structure['type'] == 'text':
                    java_data_type = 'String'
                if structure['type'] == 'datetime':
                    java_data_type = 'Date'
                fields_content = fields_content + \
                    '   private {0} {1}; // {2}\n\n'.format(
                        java_data_type, self._2camel(structure['column']), structure['comment'])
            new_tpl = tpl.replace('${PACKAGE}', package)
            new_tpl = new_tpl.replace(
                '${CLASS}', class_name)
            new_tpl = new_tpl.replace('${FIELDS}', fields_content)
            with open(filename_gen, 'w+', encoding='utf8') as f_gen:
                f_gen.write(new_tpl)
                f_gen.close()

    def _gen_mapper(self):
        tpl = self._load_tpl(2)
        for table in self.all_tables:
            new_tpl = ''
            spl_table = table.split('_')
            package = 'store.tacomall.db.mapper.{0}'.format(spl_table[0])
            class_name = self._2camel(table, capitalize=True) + 'Mapper'
            filename_gen = PathUtil.root(
                self.project_java + '/mappper/{0}/'.format(spl_table[0])) + '{0}.java'.format(class_name)
            if PathUtil.is_exists(filename_gen):
                print('mapper类{0}.java文件已存在'.format(class_name))
                continue
            new_tpl = tpl.replace('${PACKAGE}', package)
            new_tpl = new_tpl.replace(
                '${CLASS}', class_name)
            new_tpl = new_tpl.replace('${ENTITY_DOMAIN}', spl_table[0])
            new_tpl = new_tpl.replace(
                '${ENTITY_CLASS}',  self._2camel(table, capitalize=True))
            with open(filename_gen, 'w+', encoding='utf8') as f_gen:
                f_gen.write(new_tpl)
                f_gen.close()

    def run(self):
        self._gen_entity()
        self._gen_mapper()
