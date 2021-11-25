import socket
from PIL import Image
import os, glob, numpy as np
from tensorflow.keras.models import load_model
import pandas as pd
from keras_preprocessing.image import ImageDataGenerator



host = '172.31.5.100'  # 호스트 ip를 적어주세요
port = 8080            # 포트번호를 임의로 설정해주세요

#f = open('test.jpg','wb')

count = 1;
while True: #안드로이드에서 연결 버튼 누를 때까지 기다림


    server_sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    server_sock.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)
    server_sock.bind((host, port))
    server_sock.listen(5)
    
    print("실종 Model 기다리는 중..")
    client_sock, addr = server_sock.accept()  # 연결 승인

    data = None


    if client_sock:  # client_sock 가 null 값이 아니라면 (연결 승인 되었다면)
        print('Connected by?!', addr)  # 연결주소 print

        img_data = client_sock.recv(1024)
        data = img_data
        print(img_data)
        #print(img_data.decode())

        dog_cat_list = list()
        try:
            if img_data.decode() == "강아지":
                dog_cat_list.append(1)
            else:
                dog_cat_list.append(2)

            data = None


            check = 1
        except:
            client_sock1, addr = server_sock.accept()  # 연결 승인
            
            pre_ans_str = "fail"
            client_sock1.send(pre_ans_str.encode("utf-8"))
            count = count + 1

            client_sock1.close()
            client_sock.close()
            server_sock.close()
            continue
            
        


        if img_data:
            while img_data:
                if check == 1:
                    img_data = client_sock.recv(1024)
                    data = img_data
                    check = 2
                else:


                    print("recving Img")
                    img_data = client_sock.recv(1024)
                    data = data + img_data

        else:
            break

        img_fileName  = './socket_user/test.jpg'

        img_file = open(img_fileName,"wb")


        img_file.write(data)
        img_file.close()



        print("1")









#         test_df['category'] = np.argmax()


#         test_df['category'] = test_df['category'].replace({'dog': 1, 'cat': 0})




#         for index, row in test_df.iterrows():

#             filename = row['filename']
#             category = row['category']

#             if category == 1:
#                 print("dog")
#                 dog_cat_list.append(1)
#             else:
#                 print("cat")
#                 dog_cat_list.append(0)

        caltech_dir = "./socket_user"




        # prediction = model_dog.predict(X)
        np.set_printoptions(formatter={'float': lambda x: "{0:0.3f}".format(x)})

        count = 0
        count2 = 0

        image_w = 128
        image_h = 128

        pixels = image_h * image_w * 3

        X = []
        filenames = []
        files = glob.glob(caltech_dir + "/*.*")
        cnt = 0
        

        for i, f in enumerate(files):

            img = Image.open(f)
            img = img.convert("RGB")
            img = img.resize((image_w, image_h))
            data = np.asarray(img)
            filenames.append(f)
            X.append(data)
            X = np.array(X)

            if dog_cat_list[count] == 1:
                print("들어왔다")
                model_dog = None
                model_dog = load_model("dog2.h5")
                    
                print("들어왔다2")
                prediction = model_dog.predict(X)
                print("들어왔다3")
                pre_ans = prediction.argmax()
                print("들어왔다4")


                pre_ans_str = ''
                if pre_ans == 0:
                    pre_ans_str = "Husky"
                elif pre_ans == 1:
                    pre_ans_str = "Maltese"
                elif pre_ans == 2:
                    pre_ans_str = "Welsh Corgi"
                elif pre_ans == 3:
                    pre_ans_str = "Shih Tzu"
                elif pre_ans == 4:
                    pre_ans_str = "Pomeranian"

                print(prediction)

                if prediction[0][0] >= 0.8: print(
                    "해당 " + filenames[cnt].split(".")[1] + "이미지는 " + pre_ans_str + "로 추정됩니다.")

                if prediction[0][1] >= 0.8: print(
                    "해당 " + filenames[cnt].split(".")[1] + "이미지는 " + pre_ans_str + "으로 추정됩니다.")
                if prediction[0][2] >= 0.8: print(
                    "해당 " + filenames[cnt].split(".")[1] + "이미지는 " + pre_ans_str + "로 추정됩니다.")

                if prediction[0][3] >= 0.8: print(
                    "해당 " + filenames[cnt].split(".")[1] + "이미지는 " + pre_ans_str + "로 추정됩니다.")
                if prediction[0][4] >= 0.8: print(
                    "해당 " + filenames[cnt].split(".")[1] + "이미지는 " + pre_ans_str + "로 추정됩니다.")
                X = []
                filenames = []

                client_sock1, addr = server_sock.accept()  # 연결 승인

                client_sock1.send(pre_ans_str.encode("utf-8"))
                count = count + 1

                client_sock1.close()
                client_sock.close()
                server_sock.close()

                #client_sock.close()

            else:
                model_cat = load_model("cat3.h5")
                prediction = model_cat.predict(X)
                pre_ans = prediction.argmax()


                pre_ans_str = ''
                if pre_ans == 0:
                    pre_ans_str = "American_Shorthair"
                elif pre_ans == 1:
                    pre_ans_str = "Persian_cat"
                elif pre_ans == 2:
                    pre_ans_str = "Siamese_cat"
                elif pre_ans == 3:
                    pre_ans_str = "Sphinx_cat"


                print(prediction)

                if prediction[0][0] >= 0.8: print(
                    "해당 " + filenames[cnt].split(".")[1] + "이미지는 " + pre_ans_str + "로 추정됩니다.")

                if prediction[0][1] >= 0.8: print(
                    "해당 " + filenames[cnt].split(".")[1] + "이미지는 " + pre_ans_str + "으로 추정됩니다.")
                if prediction[0][2] >= 0.8: print(
                    "해당 " + filenames[cnt].split(".")[1] + "이미지는 " + pre_ans_str + "로 추정됩니다.")

                if prediction[0][3] >= 0.8: print(
                    "해당 " + filenames[cnt].split(".")[1] + "이미지는 " + pre_ans_str + "로 추정됩니다.")

                X = []
                filenames = []

                client_sock1, addr = server_sock.accept()  # 연결 승인

                client_sock1.send(pre_ans_str.encode("utf-8"))

                count = count + 1
                client_sock1.close()
                client_sock.close()
                server_sock.close()
 

#
#
#
#
#         #model_dog = load_model("dog2.h5")
#
#         #체크포인트



