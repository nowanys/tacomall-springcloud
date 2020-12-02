'''
Author: 码上talk|RC
Date: 2020-12-02 14:40:34
LastEditTime: 2020-12-02 15:16:17
LastEditors: 码上talk|RC
Description: 
FilePath: /tacomall-springcloud/_db/utils/path_util.py
微信:  13680065830
邮箱:  3189482282@qq.com
oops: Just do what I think it is right
'''
import os
import shutil
import zipfile


class PathUtil():

    @staticmethod
    def is_exists(path):
        return os.path.exists(path)

    @staticmethod
    def root(path):
        basepath = os.path.abspath('.')
        full_path = os.path.join(basepath, path)
        if not os.path.exists(full_path):
            os.makedirs(full_path)
        return full_path

    @staticmethod
    def rm_path(path):
        if not os.path.exists(path):
            return
        shutil.rmtree(path, ignore_errors=False, onerror=None)

    @staticmethod
    def make_zip(source_dir, output_filename):
        zipf = zipfile.ZipFile(output_filename, 'w')
        pre_len = len(os.path.dirname(source_dir))
        for parent, dirnames, filenames in os.walk(source_dir):
            for filename in filenames:
                pathfile = os.path.join(parent, filename)
                arcname = pathfile[pre_len:].strip(os.path.sep)
                zipf.write(pathfile, arcname)
        zipf.close()
