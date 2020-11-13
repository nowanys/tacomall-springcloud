'''
@Author: 码上talk|RC
@Date: 2020-06-22 10:16:15
@LastEditTime: 2020-06-24 11:07:06
@LastEditors: 码上talk|RC
@Description: 
@FilePath: /tacomall-springboot/_doc/jd/index.py
@Just do what I think it is right
'''
import sys
import json
import pymysql
from selenium import webdriver
from selenium.webdriver import ChromeOptions
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
from time import sleep

def process_exit():
    print('>>>>>process will exit')
    sys.exit()
    

class Mysql():

    def __init__(self, is_debug = True):
        self.ip = 'localhost'
        self.user = 'root'
        self.passwd = '123456'
        self.db = 'tacomall'
        self.conn = None
        self.is_debug = is_debug
        self.__connect()

    def __connect(self):
        try:
            self.conn = pymysql.connect(
                self.ip, user=self.user, passwd=self.passwd, db=self.db)
        except Exception:
            print('>>>>>mysql connect error')
            process_exit()

    def execute_sql(self, sql):
        if self.is_debug:
            print('debug sql >>>>>>> {sql}'.format(sql = sql))
        cursor = self.conn.cursor()
        try:
            cursor.execute(sql)
        except Exception as e:
            print('>>>>>>sql error:{e}'.format(e = e))
            process_exit()
        cursor.close()
        self.conn.commit()
        return cursor.lastrowid
        

    def quit(self):
        self.conn.close()


class Jd():

    def __init__(self, mysql):
        self.mysql = mysql
        self.limited_category_num = 1
        self.limited_lst_goods_num = 20
        self.jd_start_url = 'https://m.jd.com/'
        self.driver = self.__get_anti_block_driver()
    def __get_anti_block_driver(self):
        options = ChromeOptions()
        options.add_experimental_option(
            'mobileEmulation', {'deviceName': 'Galaxy S5'})
        return webdriver.Chrome(options=options)

    def _wait_for_ele(self, xpath):
        wait = WebDriverWait(self.driver, 10)
        try:
            return wait.until(
                EC.presence_of_element_located((By.XPATH, xpath))
            )
        except TimeoutException:
            print('>>>>>> ele not found')

    def _wait_for_eles(self, xpath):
        wait = WebDriverWait(self.driver, 10)
        try:
            return wait.until(
                EC.presence_of_all_elements_located((By.XPATH, xpath))
            )
        except TimeoutException:
            print('>>>>>> eles not found')

    def _open(self, url):
        self.driver.get(url)
        sleep(1)

    def _quit(self):
        self.driver.quit()

    def _new_tab(self, url):
        new_tab_js = 'window.open("{url}");'.format(url=url)
        self.driver.execute_script(new_tab_js)
        handlers = self.driver.window_handles
        self.driver.switch_to.window(handlers[len(handlers) - 1])

    def _close_tab(self):
        close_tab_js = 'window.close()'
        self.driver.execute_script(close_tab_js)
        handlers = self.driver.window_handles
        self.driver.switch_to.window(handlers[len(handlers) - 1])
    
    def _get_goods_item_detail(self):
        goods_item_detail = {}
        ele_album_ul = self.driver.find_elements_by_xpath('//ul[@id="loopImgUl"]/li')
        goods_item_detail = {
            'goods_item_name': self.driver.find_element_by_xpath('//div[@id="itemName"]').text,
            'goods_item_description': self.driver.find_element_by_xpath('//div[@id="itemDesc"]').text,
            'goods_item_price': self.driver.find_element_by_xpath('//span[@id="priceSale"]/em').text,
            'goods_item_album_images': list(map(lambda e: e.find_element_by_xpath('.//img').get_attribute('src'), ele_album_ul))
        }
        return goods_item_detail
    
    def _recursion_goods_item_attr(self, init = False, s = 1, j = {'k': None, 'e': None, 'v': None}, ele = []):
        if init:
            s = len(ele)
        jc = j.copy()
        current_index_ele = len(ele) - s
        jc['k'] = ele[current_index_ele].text
        jc['e'] = self.driver.find_elements_by_xpath('//div[@id="popupSkuArea"]/div[@id="skuPop{index_elak}"]/span'.format(index_elak = current_index_ele + 1))
        if s == 1:
            return jc
        s = s - 1
        jc['v'] = self._recursion_goods_item_attr(s = s, j = j.copy(), ele = ele)
        return jc
    
    def _recursion_goods_item_attr_click(self, json_attr, lst_goods_item_detail):
        for e in json_attr['e']:
            self.driver.execute_script("arguments[0].click();", e)
            sleep(1)
            if json_attr['v'] is not None:
                self._recursion_goods_item_attr_click(json_attr['v'], lst_goods_item_detail)
            lst_goods_item_detail.append(
                self._get_goods_item_detail()
            )

    def __get_category(self):

        self._open(self.jd_start_url)

        self.driver.find_element_by_css_selector(
            'a[id="mCommonType"]').click()
        category = []
        lst_first_category = self._wait_for_eles('//ul[@id="category2"]/li')
        index = 0
        for index_fc, fc in enumerate(lst_first_category):
            if index >= self.limited_category_num:
                break
            category.append({
                'name': fc.find_element_by_tag_name('a').text,
                'lst': []
            })
            fc.click()
            sleep(2)
            lst_second_category = self.driver.find_elements_by_xpath(
                '//div[@id="branchList"]/div[@class="jd-category-div cur"]')
            for index_sc, sc in enumerate(lst_second_category):
                category[index_fc]['lst'].append({
                    'name': sc.find_element_by_tag_name('h4').text,
                    'lst': []
                })
                lst_third_category = sc.find_elements_by_xpath('.//ul//li')
                for tc in lst_third_category:
                    category[index_fc]['lst'][index_sc]['lst'].append({
                        'name': tc.find_element_by_tag_name('span').text,
                        'img': tc.find_element_by_tag_name('img').get_attribute('src'),
                        'url': tc.find_element_by_tag_name('a').get_attribute('href')
                    })
            index = index + 1
        return category

    def _get_lst_goods(self, url):
        lst_goods = []
        self._new_tab(url)
        sleep(1)
        ele_lst_goods = []
        while True:
            ele_lst_goods = self.driver.find_elements_by_xpath(
                '//div[@id="itemList"]/div[@class="search_prolist_item"]')
            if len(ele_lst_goods) >= self.limited_lst_goods_num:
                break
            scroll_down_js = 'window.scrollBy(0,500);'
            self.driver.execute_script(scroll_down_js)
            sleep(2)
        for g in ele_lst_goods:
            lst_goods.append({
                'name': g.find_element_by_xpath('.//div[@class="search_prolist_title"]').text,
                'url': g.find_element_by_xpath('.//div').get_attribute('pure_item_link')
            })
        self._close_tab()
        return lst_goods

    def _get_goods_detail(self, url):
        goods_detail = {}
        self._new_tab(url)
        sleep(1)
        goods_detail = self._get_goods_item_detail()
        self._close_tab()
        return goods_detail
    
    def _get_lst_goods_item_detail(self, url):
        lst_goods_item_detail = []
        self._new_tab(url)
        sleep(1)
        ele_sku = self.driver.find_element_by_xpath('//div[@id="skuWindow"]')
        self.driver.execute_script("arguments[0].click();", ele_sku)
        sleep(1)
        ele_lst_attr_key = self._wait_for_eles('//div[@id="popupSkuArea"]/div[@class="sku_kind"]')

        if ele_lst_attr_key is not None:
            json_attr = self._recursion_goods_item_attr(init = True, ele = ele_lst_attr_key)
            self._recursion_goods_item_attr_click(json_attr, lst_goods_item_detail)
        else:
            lst_goods_item_detail.append(
                self._get_goods_item_detail()
            )
        self._close_tab()
        return lst_goods_item_detail

    def run(self):
        category = self.__get_category()
        for fc in category:
            insert_fc_sql = '''
            INSERT INTO goods_category ( p_id, name )
                       VALUES
                       ( 0,  '{name}');
            '''.format(name = fc['name'])
            insert_fc_sql_last_row_id = self.mysql.execute_sql(insert_fc_sql)
            for sc in fc['lst']:
                insert_sc_sql = '''
                INSERT INTO goods_category ( p_id, name )
                        VALUES
                        ( {id},  '{name}');
                '''.format(id = insert_fc_sql_last_row_id, name = sc['name'])
                insert_sc_sql_last_row_id = self.mysql.execute_sql(insert_sc_sql)
                for tc in sc['lst']:
                    insert_tc_sql = '''
                    INSERT INTO goods_category ( p_id, name )
                            VALUES
                            ( {id},  '{name}');
                    '''.format(id = insert_sc_sql_last_row_id, name = tc['name'])
                    insert_tc_sql_last_row_id = self.mysql.execute_sql(insert_tc_sql)
                    lst_goods = self._get_lst_goods(tc['url'])
                    for g in lst_goods:
                        goods_detail = self._get_goods_detail(g['url'])
                        insert_goods_sql = '''
                        INSERT INTO goods ( goods_category_Id, name, description )
                                VALUES
                                ( {id},  '{name}', '{description}');
                        '''.format(id = insert_tc_sql_last_row_id, name = goods_detail['goods_item_name'], description = goods_detail['goods_item_description'])
                        insert_goods_sql_last_row_id = self.mysql.execute_sql(insert_goods_sql)
                        lst_goods_item_detail = self._get_lst_goods_item_detail(g['url'])
                        print(lst_goods_item_detail)
                        for gi in lst_goods_item_detail:
                            insert_goods_item_sql = '''
                            INSERT INTO goods_item ( goods_id, name, description )
                                    VALUES
                                    ( {goods_id},  '{name}', '{description}');
                            '''.format(goods_id = insert_goods_sql_last_row_id, name = gi['goods_item_name'], description = gi['goods_item_description'])
                            self.mysql.execute_sql(insert_goods_item_sql)




if __name__ == '__main__':
    jd = Jd(Mysql())
    jd.run()
