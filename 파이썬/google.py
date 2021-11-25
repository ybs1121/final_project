from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import urllib.request
import os
import time
from selenium import webdriver
import socket
#
# options = webdriver.ChromeOptions()
# options.add_argument('headless')
# options.add_argument('window-size=1920x1080')
# options.add_argument("disable-gpu")


name = input("품종 : ")
english_name = input("품종(영어): ")
os.mkdir("D:/name_of_cat/" + english_name)

#driver = webdriver.Chrome('chromedriver',options=options)
driver = webdriver.Chrome()
driver.get("https://www.google.co.kr/imghp?hl=ko&ogbl")
# assert "Python" in driver.title2
elem = driver.find_element_by_name("q") #검색창 찾기
# elem.clear()

elem.send_keys(name)
elem.send_keys(Keys.RETURN) #엔터키 입력

#sroll

SCROLL_PAUSE_TIME = 1.0

# Get scroll height
last_height = driver.execute_script("return document.body.scrollHeight")

while True:
    # Scroll down to bottom
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")

    # Wait to load page
    time.sleep(SCROLL_PAUSE_TIME)

    # Calculate new scroll height and compare with last scroll height
    new_height = driver.execute_script("return document.body.scrollHeight")
    if new_height == last_height:
        #break
        try:
            driver.find_element_by_css_selector(".mye4qd").click()
        except:
            break
    last_height = new_height

# driver.find_elements_by_css_selector(".rg_i.Q4LuWd").click()
# time.sleep(3) # 이미지 로딩 시간 기다려주기
# imgae_URL = driver.find_element_by_css_selector(".n3VNCb").get_attribute("src")
# urllib.request.urlretrieve(imgae_URL,"test.jpg")

images = driver.find_elements_by_css_selector(".rg_i.Q4LuWd")
count = 1
print(len(images))

timeout = 20
socket.setdefaulttimeout(timeout)




for image in images:


    try:

        image.click()
        print("A")
        time.sleep(1.0) # 이미지 로딩 시간 기다려주기
        print("B")

        imgae_URL = driver.find_element_by_xpath("/html/body/div[2]/c-wiz/div[3]/div[2]/div[3]/div/div/div[3]/div[2]/c-wiz/div/div[1]/div[1]/div[2]/div[1]/a/img").get_attribute("src")
        print("C")
        urllib.request.urlretrieve(imgae_URL,"D:/name_of_cat/" +english_name+ "/"+str(count)+".jpg")
        print("D")
        count = count + 1
        print("E")
        print(str(count)+".jpg")

    except Exception as e:
        print("CAN'T DOWNLOAD - ")



# assert "No results found." not in driver.page_source
driver.close()
