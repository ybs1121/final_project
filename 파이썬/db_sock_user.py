import socket
import time
import json
import pymysql
import datetime
from io import BytesIO
from PIL import Image
import base64


host = '172.31.5.100'# 호스트 ip를 적어주세요
port = 7007           # 포트번호를 임의로 설정해주세요


while True: #안드로이드에서 연결 버튼 누를 때까지 기다림

    server_sock = socket.socket(socket.AF_INET)
    server_sock.bind((host, port))
    server_sock.listen(5)
    print("실종 DB 기다리는 중..")

    client_sock, addr = server_sock.accept() # 연결 승인

    if client_sock: #client_sock 가 null 값이 아니라면 (연결 승인 되었다면)
        print('Connected by?!', addr) #연결주소 print
        in_data = client_sock.recv(1024) #안드로이드에서 "refresh" 전송

        data = in_data

        while in_data:
            in_data = client_sock.recv(1024)
            data = in_data + data






        print('rcv :', data.decode(), len(data)) #전송 받은값 디코딩
        print(data)

        dict = json.loads(data.decode());
        print("tttt")
        print(dict['password'])

        list = data.decode('utf-8');
        print(type(list))

        client_sock.close()
        server_sock.close()

        dogcat = dict['kind']
        area = dict['area']
        passwd = dict['password']
        kind = dict['animal_kind']

        uplode_date = str(datetime.datetime.now())

        buffer = BytesIO()
        img = Image.open("./socket_user/test.jpg")
        img.save(buffer, format='jpeg')
        img_str = base64.b64encode(buffer.getvalue())

        # image = img_str
        sql = "INSERT INTO user_dog_cat (uplode_date,passwd, dogcat, kind, area, image ) VALUES(%s,%s,%s,%s,%s,%s)"

        conn = pymysql.connect(host='dogcat.ctokivcp0aft.ap-northeast-2.rds.amazonaws.com', user='admin', password='didqudtjr', db='dog_cat_db')
        cursor = conn.cursor()
        cursor.execute("set names utf8")
        conn.commit()

        cursor.execute(sql,(uplode_date,passwd ,dogcat ,kind ,area ,img_str))
        conn.commit()
        conn.close()







