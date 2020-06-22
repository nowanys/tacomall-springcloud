'''
@Author: 码上talk|RC
@Date: 2020-06-22 10:16:15
@LastEditTime: 2020-06-22 18:17:33
@LastEditors: 码上talk|RC
@Description: 
@FilePath: /jd/index.py
@Just do what I think it is right
'''
from selenium import webdriver
from selenium.webdriver import ChromeOptions
from selenium.webdriver.common.keys import Keys
from time import sleep


class Jd():

    def __init__(self):
        self.limited_category_num = 1
        self.limited_goods_num = 5
        self.jd_start_url = 'https://m.jd.com/'
        self.driver = self.__get_anti_block_driver()
        self.json_category = []
        self.lst_goods = []

    def __get_anti_block_driver(self):
        options = ChromeOptions()
        options.add_experimental_option(
            'mobileEmulation', {'deviceName': 'Galaxy S5'})
        return webdriver.Chrome(options=options)

    def _open(self, url):
        self.driver.get(url)
        sleep(1)
        
    def _quit(self):
        self.driver.quit()

    def _new_tab(self, url):
        new_tab_js='window.open("{url}");'.format(url = url)
        self.driver.execute_script(new_tab_js)
        handlers = self.driver.window_handles
        self.driver.switch_to.window(handlers[len(handlers) - 1]) 
        
    def _close_tab(self):
        close_tab_js='window.close()'
        self.driver.execute_script(close_tab_js)
        handlers = self.driver.window_handles
        self.driver.switch_to.window(handlers[len(handlers) - 1]) 

    def __get_json_category(self):
        self._open(self.jd_start_url)
        
        self.driver.find_element_by_css_selector(
            'a[id="mCommonType"]').click()

        lst_first_category = []
        while True:
            lst_first_category = self.driver.find_elements_by_xpath(
                    "//ul[@id='category2']/li")
            if len(lst_first_category) > 0:
                break
            sleep(1)
        index = 0
        for index_fc, fc in enumerate(lst_first_category):
            if index >= self.limited_category_num:
                break
            self.json_category.append({
                'name': fc.find_element_by_tag_name('a').text,
                'lst': []
            })
            fc.click()
            sleep(2)
            lst_second_category = self.driver.find_elements_by_xpath(
                    '//div[@id="branchList"]/div[@class="jd-category-div cur"]')
            for index_sc, sc in enumerate(lst_second_category):
                self.json_category[index_fc]['lst'].append({
                    'name': sc.find_element_by_tag_name('h4').text,
                    'lst': []
                })
                lst_third_category = sc.find_elements_by_xpath('.//ul//li')
                for tc in lst_third_category:
                    self.json_category[index_fc]['lst'][index_sc]['lst'].append({
                        'name': tc.find_element_by_tag_name('span').text,
                        'img': tc.find_element_by_tag_name('img').get_attribute('src'),
                        'url': tc.find_element_by_tag_name('a').get_attribute('href')
                    })
            index = index + 1
    
    def _get_category_goods(self, url):
         self._new_tab(url)
         sleep(1)
         for g in self.driver.find_elements_by_xpath('//div[@id="itemList"]/div[@class="search_prolist_item"]'):
             self.lst_goods.append({
                 'name': g.find_element_by_xpath('.//div[@class="search_prolist_title"]').text,
                 'url': g.find_element_by_xpath('.//div').get_attribute('pure_item_link')
             })
         self._close_tab()

    def _get_goods_detail(self, url):
        self._new_tab(url)
        sleep(1)
        self._close_tab()
        
    def run(self):
        self.__get_json_category()
        for fc in self.json_category:
            for sc in fc['lst']:
                for tc in sc['lst']:
                    self._get_category_goods(tc['url'])
        for g in self.lst_goods:
            self._get_goods_detail(g['url'])
                    
            


if __name__ == '__main__':
    jd = Jd()
    jd.run()
