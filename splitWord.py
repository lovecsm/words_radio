# encoding:utf-8
import re
count = 1
part = 1
f = open(r'C:\Users\ASUS\Desktop\CET4.txt', 'r', encoding='UTF-8')
fp = open(r'C:\Users\ASUS\Desktop\part_%s.wds'%part, 'w', encoding='UTF-8')
try:
    while(True):
        tmp = f.readline()
        if tmp == '':
            break
        s = str(count) + '\t' + tmp
        fp.write(s)
        count += 1
        if count > 100:
            count = 1
            part += 1
            fp.close()
            fp = open(r'C:\Users\ASUS\Desktop\part_%s.wds'%part, 'w', encoding='UTF-8')
except:
    f.close()
    fp.close()
f.close()
fp.close()
del f
del fp
