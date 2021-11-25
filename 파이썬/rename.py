import os

images = os.listdir("C:/Users/YBS/Desktop/테스트 모음/Test")
#자음
ㄱ=0
ㄴ=0
ㄷ=0
ㄹ=0
ㅁ=0
ㅂ=0
ㅅ=0
ㅇ=0
ㅈ=0
ㅊ=0
ㅋ=0
ㅌ=0
ㅍ=0
ㅎ=0
ㄲ=0
ㄸ=0
ㅃ=0
ㅆ=0
ㅉ=0

#모음
ㅣ=0
ㅔ=0
ㅐ=0
ㅏ=0
ㅜ=0
ㅗ=0
ㅓ=0
ㅡ=0
ㅟ=0
ㅚ=0
ㅑ=0
ㅕ=0
ㅛ=0
ㅠ=0
ㅒ=0
ㅖ=0
ㅘ=0
ㅝ=0
ㅙ=0
ㅞ=0


#겹받침
ㄳ=0
ㄵ=0
ㄶ=0
ㄺ=0
ㄻ=0
ㄼ=0
ㄽ=0
ㄾ=0
ㄿ=0
ㅀ=0
ㅄ=0

for image in images:
    for i in image:
        if i == 'ㄱ':
            ㄱ = ㄱ + 1

        elif i == 'ㄴ':
            ㄴ = ㄴ + 1

        elif i == 'ㄷ':
            ㄷ = ㄷ + 1

        elif i == 'ㄹ':
            ㄹ = ㄹ + 1

        elif i == 'ㅁ':
            ㅁ = ㅁ + 1

        elif i == 'ㅂ':
            ㅂ = ㅂ + 1

        elif i == 'ㅅ':
            ㅅ = ㅅ + 1

        elif i == 'ㅇ':
            ㅇ = ㅇ + 1

        elif i == 'ㅈ':
            ㅈ = ㅈ + 1

        elif i == 'ㅊ':
            ㅊ = ㅊ + 1

        elif i == 'ㅋ':
            ㅋ = ㅋ + 1

        elif i == 'ㅌ':
            ㅌ = ㅌ + 1

        elif i == 'ㅍ':
            ㅍ = ㅍ + 1

        elif i == 'ㅎ':
            ㅎ = ㅎ + 1

        elif i == 'ㄲ':
            ㄲ = ㄲ + 1

        elif i == 'ㄸ':
            ㄸ = ㄸ + 1

        elif i == 'ㅃ':
            ㅃ = ㅃ + 1

        elif i == 'ㅆ':
            ㅆ = ㅆ + 1

        elif i == 'ㅉ':
            ㅉ = ㅉ + 1

        elif i == 'ㅣ':
            ㅣ = ㅣ + 1

        elif i == 'ㅔ':
            ㅔ = ㅔ + 1

        elif i == 'ㅐ':
            ㅐ = ㅐ + 1

        elif i == 'ㅏ':
            ㅏ = ㅏ + 1

        elif i == 'ㅜ':
            ㅜ = ㅜ + 1

        elif i == 'ㅗ':
            ㅗ = ㅗ + 1

        elif i == 'ㅓ':
            ㅓ = ㅓ + 1

        elif i == 'ㅡ':
            ㅡ = ㅡ + 1

        elif i == 'ㅟ':
            ㅟ = ㅟ + 1

        elif i == 'ㅚ':
            ㅚ = ㅚ + 1

        elif i == 'ㅑ':
            ㅑ = ㅑ + 1

        elif i == 'ㅕ':
            ㅕ = ㅕ + 1

        elif i == 'ㅛ':
            ㅛ = ㅛ + 1

        elif i == 'ㅠ':
            ㅠ = ㅠ + 1

        elif i == 'ㅒ':
            ㅒ = ㅒ + 1

        elif i == 'ㅖ':
            ㅖ = ㅖ + 1

        elif i == 'ㅘ':
            ㅘ = ㅘ + 1

        elif i == 'ㅝ':
            ㅝ = ㅝ + 1

        elif i == 'ㅙ':
            ㅙ = ㅙ + 1

        elif i == 'ㅘ':
            ㅞ = ㅞ + 1

        elif i == 'ㄳ':
            ㄳ = ㄳ + 1

        elif i == 'ㄵ':
            ㄵ = ㄵ + 1

        elif i == 'ㄶ':
            ㄶ = ㄶ + 1

        elif i == 'ㄺ':
            ㄺ = ㄺ + 1

        elif i == 'ㄻ':
            ㄻ = ㄻ + 1

        elif i == 'ㄽ':
            ㄽ = ㄽ + 1

        elif i == 'ㄾ':
            ㄾ = ㄾ + 1

        elif i == 'ㄿ':
            ㄿ = ㄿ + 1

        elif i == 'ㅀ':
            ㅀ = ㅀ + 1

        elif i == 'ㅄ':
            ㅄ = ㅄ + 1




print("ㄱ: %d" %ㄱ ,end=" ")
print("ㄴ: %d" %ㄴ, end=" ")
print("ㄷ: %d" %ㄷ ,end=" ")
print("ㄹ: %d" %ㄹ, end=" ")
print("ㅁ: %d" %ㅁ ,end=" ")
print("ㅂ: %d" %ㅂ, end=" ")
print("ㅅ: %d" %ㅅ ,end=" ")
print("ㅇ: %d" %ㅇ, end=" ")
print("ㅈ: %d" %ㅈ ,end=" ")
print("ㅊ: %d" %ㅊ, end=" ")
print("ㅋ: %d" %ㅋ, end=" ")
print("ㅌ: %d" %ㅌ ,end=" ")
print("ㅍ: %d" %ㅍ, end=" ")
print("ㄲ: %d" %ㄲ ,end=" ")
print("ㄸ: %d" %ㄸ, end=" ")
print("ㅃ: %d" %ㅃ ,end=" ")
print("ㅆ: %d" %ㅆ, end=" ")
print("ㅉ: %d" %ㅉ ,end=" ")

print()


print("ㅣ: %d" %ㅣ ,end=" ")
print("ㅔ: %d" %ㅔ, end=" ")
print("ㅐ: %d" %ㅐ ,end=" ")
print("ㅏ: %d" %ㅏ, end=" ")
print("ㅜ: %d" %ㅜ ,end=" ")
print("ㅗ: %d" %ㅗ, end=" ")
print("ㅓ: %d" %ㅓ ,end=" ")
print("ㅡ: %d" %ㅡ, end=" ")
print("ㅟ: %d" %ㅟ ,end=" ")
print("ㅚ: %d" %ㅚ, end=" ")
print("ㅑ: %d" %ㅑ, end=" ")
print("ㅕ: %d" %ㅕ ,end=" ")
print("ㅛ: %d" %ㅛ, end=" ")
print("ㅠ: %d" %ㅠ ,end=" ")
print("ㅒ: %d" %ㅒ, end=" ")
print("ㅖ: %d" %ㅖ ,end=" ")
print("ㅘ: %d" %ㅘ, end=" ")
print("ㅝ: %d" %ㅝ ,end=" ")
print("ㅞ: %d" %ㅞ ,end=" ")



print()

print("ㄳ: %d" %ㄳ, end=" ")
print("ㄵ: %d" %ㄵ ,end=" ")
print("ㄶ: %d" %ㄶ, end=" ")
print("ㄺ: %d" %ㄺ ,end=" ")
print("ㄻ: %d" %ㄻ, end=" ")
print("ㄼ: %d" %ㄼ ,end=" ")
print("ㄽ: %d" %ㄽ, end=" ")
print("ㄾ: %d" %ㄾ ,end=" ")
print("ㄿ: %d" %ㄿ, end=" ")
print("ㅀ: %d" %ㅀ ,end=" ")
print("ㅄ: %d" %ㅄ, end=" ")



j =  0

for image in images:

    j = j + 1

    for i in image:

        if i == 'ㄱ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/'+image,'C:/Users/YBS/Desktop/테스트 모음/Test/' +'ㄱ(' + str(j) + ').png')
                print("ㄱ(%d).png" %j)
            except:
                pass

        elif i == 'ㄴ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄴ(' + str(j) + ').png')
                print("ㄴ(%d).png" % j)
            except:
                pass


        elif i == 'ㄷ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄷ(' + str(j) + ').png')
                print("ㄷ(%d).png" % j)
            except:
                pass

        elif i == 'ㄹ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄹ(' + str(j) + ').png')
                print("ㄹ(%d).png" % j)
            except:
                pass

        elif i == 'ㅁ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅁ(' + str(j) + ').png')
                print("ㅁ(%d).png" % j)
            except:
                pass

        elif i == 'ㅂ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅂ(' + str(j) + ').png')
                print("ㅂ(%d).png" % j)
            except:
                pass

        elif i == 'ㅅ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅅ(' + str(j) + ').png')
                print("ㅅ(%d).png" % j)
            except:
                pass

        elif i == 'ㅇ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅇ(' + str(j) + ').png')
                print("ㅇ(%d).png" % j)
            except:
                pass

        elif i == 'ㅈ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅈ(' + str(j) + ').png')
                print("ㅈ(%d).png" % j)
            except:
                pass

        elif i == 'ㅊ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅊ(' + str(j) + ').png')
                print("ㅊ(%d).png" % j)
            except:
                pass

        elif i == 'ㅋ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅋ(' + str(j) + ').png')
                print("ㅋ(%d).png" % j)
            except:
                pass

        elif i == 'ㅌ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅌ(' + str(j) + ').png')
                print("ㅌ(%d).png" % j)
            except:
                pass

        elif i == 'ㅍ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅍ(' + str(j) + ').png')
                print("ㅍ(%d).png" % j)
            except:
                pass

        elif i == 'ㅎ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅎ(' + str(j) + ').png')
                print("ㅎ(%d).png" % j)
            except:
                pass

        elif i == 'ㄲ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄲ(' + str(j) + ').png')
                print("ㄲ(%d).png" % j)
            except:
                pass

        elif i == 'ㄸ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄸ(' + str(j) + ').png')
                print("ㄸ(%d).png" % j)
            except:
                pass

        elif i == 'ㅃ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅃ(' + str(j) + ').png')
                print("ㅃ(%d).png" % j)
            except:
                pass

        elif i == 'ㅆ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅆ(' + str(j) + ').png')
                print("ㅆ(%d).png" % j)
            except:
                pass

        elif i == 'ㅉ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅉ(' + str(j) + ').png')
                print("ㅉ(%d).png" % j)
            except:
                pass

        elif i == 'ㅣ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅣ(' + str(j) + ').png')
                print("ㅣ(%d).png" % j)
            except:
                pass

        elif i == 'ㅔ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅔ(' + str(j) + ').png')
                print("ㅔ(%d).png" % j)
            except:
                pass

        elif i == 'ㅐ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅐ(' + str(j) + ').png')
                print("ㅐ(%d).png" % j)
            except:
                pass

        elif i == 'ㅏ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅏ(' + str(j) + ').png')
                print("ㅏ(%d).png" % j)
            except:
                pass

        elif i == 'ㅜ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅜ(' + str(j) + ').png')
                print("ㅜ(%d).png" % j)
            except:
                pass

        elif i == 'ㅗ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅗ(' + str(j) + ').png')
                print("ㅗ(%d).png" % j)
            except:
                pass

        elif i == 'ㅓ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅓ(' + str(j) + ').png')
                print("ㅓ(%d).png" % j)
            except:
                pass

        elif i == 'ㅡ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅡ(' + str(j) + ').png')
                print("ㅡ(%d).png" % j)
            except:
                pass

        elif i == 'ㅟ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅟ(' + str(j) + ').png')
                print("ㅟ(%d).png" % j)
            except:
                pass

        elif i == 'ㅚ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅚ(' + str(j) + ').png')
                print("ㅚ(%d).png" % j)
            except:
                pass

        elif i == 'ㅑ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅑ(' + str(j) + ').png')
                print("ㅑ(%d).png" % j)
            except:
                pass

        elif i == 'ㅕ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅕ(' + str(j) + ').png')
                print("ㅕ(%d).png" % j)
            except:
                pass

        elif i == 'ㅛ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅛ(' + str(j) + ').png')
                print("ㅛ(%d).png" % j)
            except:
                pass

        elif i == 'ㅠ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅠ(' + str(j) + ').png')
                print("ㅠ(%d).png" % j)
            except:
                pass

        elif i == 'ㅒ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅒ(' + str(j) + ').png')
                print("ㅒ(%d).png" % j)
            except:
                pass

        elif i == 'ㅖ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅖ(' + str(j) + ').png')
                print("ㅖ(%d).png" % j)
            except:
                pass

        elif i == 'ㅘ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅘ(' + str(j) + ').png')
                print("ㅘ(%d).png" % j)
            except:
                pass

        elif i == 'ㅝ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅝ(' + str(j) + ').png')
                print("ㅝ(%d).png" % j)
            except:
                pass

        elif i == 'ㅙ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅙ(' + str(j) + ').png')
                print("ㅙ(%d).png" % j)
            except:
                pass

        elif i == 'ㅘ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅘ(' + str(j) + ').png')
                print("ㅘ(%d).png" % j)
            except:
                pass

        elif i == 'ㄳ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄳ(' + str(j) + ').png')
                print("ㄳ(%d).png" % j)
            except:
                pass

        elif i == 'ㄵ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄵ(' + str(j) + ').png')
                print("ㄵ(%d).png" % j)
            except:
                pass
        elif i == 'ㄶ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄶ(' + str(j) + ').png')
                print("ㄶ(%d).png" % j)
            except:
                pass
        elif i == 'ㄺ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄺ(' + str(j) + ').png')
                print("ㄺ(%d).png" % j)
            except:
                pass

        elif i == 'ㄻ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄻ(' + str(j) + ').png')
                print("ㄻ(%d).png" % j)
            except:
                pass

        elif i == 'ㄽ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄽ(' + str(j) + ').png')
                print("ㄽ(%d).png" % j)
            except:
                pass

        elif i == 'ㄾ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄾ(' + str(j) + ').png')
                print("ㄾ(%d).png" % j)
            except:
                pass

        elif i == 'ㄿ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㄿ(' + str(j) + ').png')
                print("ㄿ(%d).png" % j)
            except:
                pass

        elif i == 'ㅀ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅀ(' + str(j) + ').png')
                print("ㅀ(%d).png" % j)
            except:
                pass

        elif i == 'ㅄ':
            try:
                os.rename('C:/Users/YBS/Desktop/테스트 모음/Test/' + image,
                          'C:/Users/YBS/Desktop/테스트 모음/Test/' + 'ㅄ(' + str(j) + ').png')
                print("ㅄ(%d).png" % j)
            except:
                pass






