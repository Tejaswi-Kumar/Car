# -*- coding: utf-8 -*-
"""
Created on Thu Apr 30 22:55:35 2020

@author: tejaswi
"""

"""Web Scraping"""

from bs4 import BeautifulSoup as soup
from urllib.request import urlopen as urll
from urllib.request import Request
import pandas as pd
myurl='https://www.cardekho.com/filter/new-cars'
req = Request(myurl, headers = {"User-Agent": "Mozilla/5.0"})
uclient=urll(req)
page=uclient.read()
uclient.close()
psoup=soup(page,"html.parser")
containers=psoup.findAll("div",{"gsc_col-md-12 gsc_col-sm-12 gsc_col-xs-12 append_list"})
cars=[]
prices=[]
engines=[]
mileages=[]
for i in containers:
    cars.append(i.div.img["alt"])
    price=i.findAll("div",{"class":"price"})
    q=price[0].text
    s=""
    for h in q:
        if h!="*":
            s+=h
        else:
            break
    prices.append(s)
    m=i.findAll("div",{"class":"dotlist"})
    f=m[0].findAll("span",{"title":"Mileage"})
    if len(f)!=0:
        mileages.append(f[0].text)
    else:
        mileages.append(" ")
    e=m[0].findAll("span",{"title":"Engine Displacement"})
    if len(e)!=0:
        engines.append(e[0].text)
    else:
        engines.append(" ")
df = pd.DataFrame({'Car Name':cars,'Price':prices,'Engine':engines,'Mileage':mileages}) 
df.to_csv('carscrap.csv', index=False, encoding='utf-8')
"""cars=[]
price=[]
engine=[]
mileage=[]
for j in range(0,78):
    l=[i for i in containers[j].text]
    s=""
    for k in l:
        if k!=")":
            s+=k
        else:
            s+=k
            break
    cars.append(s)
class====score
"""
            
        
            